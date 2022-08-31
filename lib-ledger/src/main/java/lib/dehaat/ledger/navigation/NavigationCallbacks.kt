package lib.dehaat.ledger.navigation

import android.os.Bundle
import lib.dehaat.ledger.presentation.ledger.revamp.state.credits.availablecreditlimit.AvailableCreditLimitViewState
import lib.dehaat.ledger.presentation.ledger.revamp.state.credits.outstandingcreditlimit.OutstandingCreditLimitViewState

interface DetailPageNavigationCallback {
    fun navigateToInvoiceDetailPage(args: Bundle)

    fun navigateToCreditNoteDetailPage(args: Bundle)

    fun navigateToPaymentDetailPage(args: Bundle)

    fun navigateToOutstandingDetailPage(
        viewState: OutstandingCreditLimitViewState?
    )

    fun navigateToInvoiceListPage(
        bundle: Bundle
    )

    fun navigateToAvailableCreditLimitDetailPage(
        viewState: AvailableCreditLimitViewState?
    )

    fun navigateToRevampInvoiceDetailPage(bundle: Bundle)

    fun navigateToRevampCreditNoteDetailPage(bundle: Bundle)

    fun navigateToRevampPaymentDetailPage(bundle: Bundle)

    fun navigateToRevampWeeklyInterestDetailPage(bundle: Bundle)

}
