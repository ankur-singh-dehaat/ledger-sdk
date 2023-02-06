package lib.dehaat.ledger.presentation.model.revamp.invoice

data class SummaryViewDataV2(
	val interestBeingCharged: Boolean?,
	val interestDays: Int?,
	val interestStartDate: Long?,
	val invoiceAmount: String?,
	val invoiceDate: Long,
	val invoiceId: String,
	val processingFee: String?,
	val totalOutstandingAmount: String?,
	val fullPaymentComplete: Boolean,
	val totalInterestCharged: String,
	val totalInterestPaid: String,
	val totalInterestOutstanding: String,
	val showProcessingLabel: Boolean,
	val showInterestWillBeStartingLabel: Boolean,
	val showInterestDetails: Boolean
)
