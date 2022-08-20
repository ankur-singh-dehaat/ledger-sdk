package lib.dehaat.ledger.presentation.ledger.revamp.state.invoicelist

import lib.dehaat.ledger.presentation.ledger.revamp.state.UIState
import lib.dehaat.ledger.presentation.model.invoicelist.InvoiceListViewData

data class InvoiceListViewModelState(
    val interestApproachingInvoices: List<InvoiceListViewData>? = null,
    val interestApproachedInvoices: List<InvoiceListViewData>? = null,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val interestApproachingLoading: Boolean = false,
    val interestApproachedLoading: Boolean = false,
    val isSuccess: Boolean = false
) {
    fun toUIState() = InvoiceListUIState(
        interestApproachingInvoices = interestApproachingInvoices,
        interestApproachedInvoices = interestApproachedInvoices,
        interestApproachingLoading = interestApproachingLoading,
        interestApproachedLoading = interestApproachedLoading,
        state = when {
            isSuccess -> UIState.SUCCESS
            isError -> UIState.ERROR(errorMessage)
            else -> UIState.SUCCESS
        }
    )
}

data class InvoiceListUIState(
    val interestApproachingInvoices: List<InvoiceListViewData>?,
    val interestApproachedInvoices: List<InvoiceListViewData>?,
    val interestApproachingLoading: Boolean,
    val interestApproachedLoading: Boolean,
    val state: UIState
)
