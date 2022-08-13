package lib.dehaat.ledger.presentation.mapper

import javax.inject.Inject
import lib.dehaat.ledger.entities.revamp.creditsummary.CreditSummaryEntityV2
import lib.dehaat.ledger.presentation.model.revamp.SummaryViewData

class ViewDataMapper @Inject constructor() {

    fun toCreditSummaryViewData(entity: CreditSummaryEntityV2) = with(entity) {
        SummaryViewData(
            bufferLimit,
            creditNoteAmountTillDate,
            externalFinancierSupported,
            interestTillDate,
            minInterestAmountDue,
            minInterestOutstandingDate,
            minOutstandingAmountDue,
            paymentAmountTillDate,
            permanentCreditLimit,
            purchaseAmountTillDate,
            totalAvailableCreditLimit,
            totalCreditLimit,
            totalOutstandingAmount,
            totalPurchaseAmount,
            undeliveredInvoiceAmount
        )
    }
}
