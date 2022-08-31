package lib.dehaat.ledger.presentation.model.invoicelist

data class InvoiceListViewData(
    val amount: String,
    val date: Long,
    val interestStartDate: Long?,
    val interestFreePeriodEndDate: Long?,
    val ledgerId: String,
    val locusId: Int,
    val outstandingAmount: String,
    val partnerId: String,
    val source: String,
    val type: String
)