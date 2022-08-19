package lib.dehaat.ledger.presentation.ledger.revamp.state.invoicelist

import lib.dehaat.ledger.presentation.ledger.revamp.state.UIState
import lib.dehaat.ledger.presentation.model.invoicelist.InvoiceListViewData

data class InvoiceListViewModelState(
    val interestApproachingInvoices: List<InvoiceListViewData>? = null,
    val interestApproachedInvoices: List<InvoiceListViewData>? = null,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false
) {
    fun toUIState() = InvoiceListUIState(
        interestApproachingInvoices = interestApproachingInvoices,
        interestApproachedInvoices = interestApproachedInvoices,
        state = when {
            isSuccess -> UIState.SUCCESS
            isError -> UIState.ERROR(errorMessage)
            isLoading -> UIState.LOADING
            else -> UIState.SUCCESS
        }
    )
}

data class InvoiceListUIState(
    val interestApproachingInvoices: List<InvoiceListViewData>?,
    val interestApproachedInvoices: List<InvoiceListViewData>?,
    val state: UIState
)
