package lib.dehaat.ledger.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.cleanarch.base.entity.result.api.APIResultEntity
import com.dehaat.androidbase.helper.callInViewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import lib.dehaat.ledger.domain.usecases.GetCreditSummaryUseCase
import lib.dehaat.ledger.entities.revamp.creditsummary.CreditSummaryEntityV2
import lib.dehaat.ledger.presentation.common.BaseViewModel
import lib.dehaat.ledger.presentation.ledger.revamp.state.LedgerViewModelState
import lib.dehaat.ledger.presentation.mapper.ViewDataMapper
import lib.dehaat.ledger.util.processAPIResponseWithFailureSnackBar

@HiltViewModel
class RevampLedgerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCreditSummaryUseCase: GetCreditSummaryUseCase,
    private val mapper: ViewDataMapper
) : BaseViewModel() {

    val partnerId by lazy { savedStateHandle.get<String>(LedgerConstants.KEY_PARTNER_ID) ?: "" }
    val dcName by lazy { savedStateHandle.get<String>(LedgerConstants.KEY_DC_NAME) ?: "" }

    private val viewModelState = MutableStateFlow(LedgerViewModelState())

    val uiState = viewModelState
        .map { it.toUIState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUIState()
        )

    init {
        getCreditSummaryFromServer()
    }

    fun showFilterBottomSheet() = viewModelState.update {
        it.copy(showFilterSheet = true)
    }

    private fun getCreditSummaryFromServer() {
        callInViewModelScope {
            callingAPI()
            val response = getCreditSummaryUseCase.getCreditSummary(partnerId)
            calledAPI()
            processCreditSummaryResponse(response)
        }
    }

    private fun processCreditSummaryResponse(
        result: APIResultEntity<CreditSummaryEntityV2?>
    ) = result.processAPIResponseWithFailureSnackBar(::sendFailureEvent) {
        it?.let { creditSummaryEntity ->
            val creditSummaryViewData = mapper.toCreditSummaryViewData(creditSummaryEntity)
            viewModelState.update { state ->
                state.copy(
                    summaryViewData = creditSummaryViewData,
                    isSuccess = true
                )
            }
        }
    }

    private fun sendFailureEvent(message: String) {
        viewModelState.update {
            it.copy(
                isError = true,
                errorMessage = message
            )
        }
    }

    private fun calledAPI() = updateProgressDialog(false)

    private fun callingAPI() = updateProgressDialog(true)

    fun updateProgressDialog(show: Boolean) = viewModelState.update {
        it.copy(isLoading = show)
    }

}
