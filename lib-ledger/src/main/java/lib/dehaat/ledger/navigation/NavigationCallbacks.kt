package lib.dehaat.ledger.navigation

import android.os.Bundle
import lib.dehaat.ledger.presentation.ledger.revamp.state.credits.availablecreditlimit.AvailableCreditLimitViewState
import lib.dehaat.ledger.presentation.ledger.revamp.state.credits.outstandingcreditlimit.OutstandingCreditLimitViewState

interface DetailPageNavigationCallback {
    fun navigateToInvoiceDetailPage(args: Bundle)

    fun navigateToCreditNoteDetailPage(args: Bundle)

    fun navigateToPaymentDetailPage(args: Bundle)

    fun navigateToOutstandingDetailPage(args: Bundle)

    fun navigateToInvoiceListPage(
        args: Bundle
    )

    fun navigateToAvailableCreditLimitDetailPage(
        viewState: AvailableCreditLimitViewState?
    )

    fun navigateToRevampInvoiceDetailPage(args: Bundle)

    fun navigateToRevampCreditNoteDetailPage(args: Bundle)

    fun navigateToRevampPaymentDetailPage(args: Bundle)

    fun navigateToRevampWeeklyInterestDetailPage(args: Bundle)

}
