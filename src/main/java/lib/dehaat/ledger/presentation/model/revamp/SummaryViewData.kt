package lib.dehaat.ledger.presentation.model.revamp

data class SummaryViewData(
    val bufferLimit: String,
    val creditNoteAmountTillDate: String,
    val externalFinancierSupported: Boolean,
    val interestTillDate: String,
    val minInterestAmountDue: String,
    val minInterestOutstandingDate: Long,
    val minOutstandingAmountDue: String,
    val paymentAmountTillDate: String,
    val permanentCreditLimit: String,
    val purchaseAmountTillDate: String,
    val totalAvailableCreditLimit: String,
    val totalCreditLimit: String,
    val totalOutstandingAmount: String,
    val totalPurchaseAmount: String,
    val undeliveredInvoiceAmount: String,
    val totalInterestOutstanding: String,
    val totalInterestPaid: String,
    val minimumRepaymentAmount: String?,
    val repaymentDate: String,
    val isOrderingBlocked: Boolean,
    val hideMinimumRepaymentSection: Boolean,
    val showToolTipInformation: Boolean
)