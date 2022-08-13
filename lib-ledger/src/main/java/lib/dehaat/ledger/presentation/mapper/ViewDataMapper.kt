package lib.dehaat.ledger.presentation.mapper

import javax.inject.Inject
import lib.dehaat.ledger.entities.revamp.creditsummary.CreditSummaryEntityV2
import lib.dehaat.ledger.presentation.ledger.revamp.state.UIState
import lib.dehaat.ledger.presentation.ledger.revamp.state.credits.availablecreditlimit.AvailableCreditLimitViewState
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

    fun toAvailableCreditLimitViewState(entity: CreditSummaryEntityV2) = with(entity) {
        AvailableCreditLimitViewState(
            totalAvailableCreditLimit = totalAvailableCreditLimit,
            totalCreditLimit = totalCreditLimit,
            outstandingAndDeliveredAmount = (totalOutstandingAmount.toDouble() + undeliveredInvoiceAmount.toDouble()).toString(),
            permanentCreditLimit = permanentCreditLimit,
            bufferLimit = bufferLimit,
            totalLimit = (permanentCreditLimit.toDouble() + bufferLimit.toDouble()).toString()
        )
    }
}
