package lib.dehaat.ledger.navigation

import android.os.Bundle
import lib.dehaat.ledger.presentation.ledger.revamp.state.credits.availablecreditlimit.AvailableCreditLimitViewState
import lib.dehaat.ledger.presentation.ledger.revamp.state.credits.outstandingcreditlimit.OutstandingCreditLimitViewState

interface DetailPageNavigationCallback {
    fun navigateToInvoiceDetailPage(
        legerId: String,
        erpId: String?,
        locusId: String?,
        source: String,
        isLMSActivated: Boolean
    )

    fun navigateToCreditNoteDetailPage(legerId: String, erpId: String?, locusId: String?)
    fun navigateToPaymentDetailPage(
        legerId: String,
        erpId: String?,
        locusId: String?,
        mode: String?,
        isLMSActivated: Boolean
    )

    fun navigateToOutstandingDetailPage(
        viewState: OutstandingCreditLimitViewState?
    )

    fun navigateToInvoiceListPage()

    fun navigateToOtherPaymentModesScreen()

    fun navigateToAvailableCreditLimitDetailPage(
        viewState: AvailableCreditLimitViewState?
    )

    fun navigateToRevampInvoiceDetailPage(bundle: Bundle)

    fun navigateToRevampCreditNoteDetailPage(bundle: Bundle)

    fun navigateToRevampPaymentDetailPage(ledgerId: String)

    fun navigateToRevampInterestDetailPage(ledgerId: String)
}
