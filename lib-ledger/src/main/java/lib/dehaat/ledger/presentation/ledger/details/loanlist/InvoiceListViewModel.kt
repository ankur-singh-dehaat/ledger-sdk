package lib.dehaat.ledger.presentation.ledger.details.loanlist

import android.os.Bundle
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
import lib.dehaat.ledger.domain.revamp.usecase.GetInvoiceListUseCase
import lib.dehaat.ledger.entities.revamp.invoicelist.InvoiceListEntity
import lib.dehaat.ledger.presentation.common.BaseViewModel
import lib.dehaat.ledger.presentation.ledger.revamp.state.invoicelist.InvoiceListViewModelState
import lib.dehaat.ledger.presentation.mapper.LedgerViewDataMapper
import lib.dehaat.ledger.util.processAPIResponseWithFailureSnackBar

@HiltViewModel
class InvoiceListViewModel @Inject constructor(
    private val getInvoiceListUseCase: GetInvoiceListUseCase,
    private val mapper: LedgerViewDataMapper,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    val dueDate by lazy { savedStateHandle.get<String>(KEY_DUE_DATE)?.toLongOrNull() }
    val amountDue by lazy { savedStateHandle.get<String>(KEY_DUE_AMOUNT) }
    private val partnerId by lazy { savedStateHandle.get<String>(KEY_PARTNER_ID) ?: "" }

    private val viewModelState = MutableStateFlow(InvoiceListViewModelState())
    val uiState = viewModelState
        .map { it.toUIState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUIState()
        )

    private var limit = 1
    private var offset = 1

    init {
        getInterestApproachedInvoicesFromServer()
        getInterestApproachingInvoicesFromServer()
    }

    private fun getInterestApproachedInvoicesFromServer() {
        callInViewModelScope {
            updateProgressDialog(true)
            val response = getInvoiceListUseCase.getInvoices(
                partnerId = partnerId,
                limit = limit,
                offset = offset,
                isInterestApproached = true
            )
            updateProgressDialog(false)
            processInterestApproachedInvoicesResponse(response)
        }
    }

    private fun processInterestApproachedInvoicesResponse(
        result: APIResultEntity<List<InvoiceListEntity>?>
    ) = result.processAPIResponseWithFailureSnackBar(::sendFailureEvent) { entity ->
        val viewData = mapper.toInvoiceListViewData(entity)
        viewModelState.update {
            it.copy(
                interestApproachedInvoices = viewData,
                isSuccess = true
            )
        }
    }

    private fun getInterestApproachingInvoicesFromServer() {
        callInViewModelScope {
            updateProgressDialog(true)
            val response = getInvoiceListUseCase.getInvoices(
                partnerId = partnerId,
                limit = limit,
                offset = offset,
                isInterestApproached = false
            )
            updateProgressDialog(false)
            processInterestApproachingInvoicesResponse(response)
        }
    }

    private fun processInterestApproachingInvoicesResponse(
        result: APIResultEntity<List<InvoiceListEntity>?>
    ) = result.processAPIResponseWithFailureSnackBar(::sendFailureEvent) { entity ->
        val viewData = mapper.toInvoiceListViewData(entity)
        viewModelState.update {
            it.copy(
                interestApproachingInvoices = viewData,
                isSuccess = true
            )
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

    fun updateProgressDialog(show: Boolean) = viewModelState.update {
        it.copy(isLoading = show)
    }

    companion object {
        private const val KEY_DUE_DATE = "KEY_DUE_DATE"
        private const val KEY_DUE_AMOUNT = "KEY_DUE_AMOUNT"
        private const val KEY_PARTNER_ID = "KEY_PARTNER_ID"

        fun getBundle(
            outstandingDueDate: Long?,
            outstandingAmountDue: String?,
            partnerId: String
        ) = Bundle().apply {
            putString(KEY_DUE_DATE, outstandingDueDate.toString())
            putString(KEY_DUE_AMOUNT, outstandingAmountDue)
            putString(KEY_PARTNER_ID, partnerId)
        }
    }
}
