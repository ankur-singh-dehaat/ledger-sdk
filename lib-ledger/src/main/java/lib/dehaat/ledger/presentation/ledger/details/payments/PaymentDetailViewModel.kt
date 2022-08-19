package lib.dehaat.ledger.presentation.ledger.details.payments

import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.cleanarch.base.entity.result.api.APIResultEntity
import com.dehaat.androidbase.helper.callInViewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import lib.dehaat.ledger.domain.usecases.GetPaymentDetailUseCase
import lib.dehaat.ledger.entities.detail.payment.PaymentDetailEntity
import lib.dehaat.ledger.presentation.LedgerConstants.KEY_ERP_ID
import lib.dehaat.ledger.presentation.LedgerConstants.KEY_LEDGER_ID
import lib.dehaat.ledger.presentation.LedgerConstants.KEY_LMS_ACTIVATED
import lib.dehaat.ledger.presentation.LedgerConstants.KEY_LOCUS_ID
import lib.dehaat.ledger.presentation.LedgerConstants.KEY_PAYMENT_MODE
import lib.dehaat.ledger.presentation.common.BaseViewModel
import lib.dehaat.ledger.presentation.common.UiEvent
import lib.dehaat.ledger.presentation.ledger.details.payments.state.PaymentDetailViewModelState
import lib.dehaat.ledger.presentation.mapper.LedgerViewDataMapper
import lib.dehaat.ledger.util.processAPIResponseWithFailureSnackBar

@HiltViewModel
class PaymentDetailViewModel @Inject constructor(
    private val getPaymentDetailUseCase: GetPaymentDetailUseCase,
    private val mapper: LedgerViewDataMapper,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val ledgerId by lazy {
        savedStateHandle.get<String>(KEY_LEDGER_ID) ?: throw Exception(
            "Ledger id should not null"
        )
    }

    private val locusId by lazy { savedStateHandle.get<String>(KEY_LOCUS_ID) }

    private val erpId by lazy { savedStateHandle.get<String>(KEY_ERP_ID) }

    val paymentMode by lazy { savedStateHandle.get<String>(KEY_PAYMENT_MODE) }

    private val lmsActivated by lazy { savedStateHandle.get<Boolean>(KEY_LMS_ACTIVATED) }

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> get() = _uiEvent

    private val viewModelState = MutableStateFlow(PaymentDetailViewModelState())
    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        getPaymentDetailFromServer()
    }

    fun isLmsActivated() = lmsActivated == true

    private fun getPaymentDetailFromServer() {
        callInViewModelScope {
            updateProgressDialog(true)
            val response = getPaymentDetailUseCase.invoke(ledgerId)
            updateProgressDialog(false)
            processPaymentDetailResponse(response)
        }
    }

    private fun processPaymentDetailResponse(result: APIResultEntity<PaymentDetailEntity?>) {
        result.processAPIResponseWithFailureSnackBar(::sendShowSnackBarEvent) {
            it?.let { entity ->
                val paymentDetailViewData = mapper.toPaymentDetailSummaryViewData(entity.summary)
                viewModelState.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = true,
                        paymentDetailSummaryViewData = paymentDetailViewData
                    )
                }
            }
        }
    }

    private fun sendShowSnackBarEvent(message: String) {
        viewModelState.update {
            it.copy(isError = true, errorMessage = message)
        }
        viewModelScope.launch {
            _uiEvent.emit(UiEvent.ShowSnackbar(message))
        }
    }

    fun updateProgressDialog(show: Boolean) = viewModelState.update {
        it.copy(isLoading = show)
    }

    companion object {
        fun getBundle(ledgerId: String) = bundleOf(Pair(KEY_LEDGER_ID, ledgerId))
    }
}
