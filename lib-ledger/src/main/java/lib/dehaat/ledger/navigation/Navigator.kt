package lib.dehaat.ledger.navigation

import android.os.Bundle
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import lib.dehaat.ledger.presentation.LedgerConstants
import lib.dehaat.ledger.presentation.ledger.revamp.state.credits.availablecreditlimit.AvailableCreditLimitViewState
import lib.dehaat.ledger.presentation.ledger.revamp.state.credits.outstandingcreditlimit.OutstandingCreditLimitViewState
import lib.dehaat.ledger.util.withArgs

fun navigateToInvoiceDetailScreen(
    navController: NavHostController,
    ledgerId: String,
    erpId: String?,
    locusId: String?,
    source: String,
    isLMSActivated: Boolean
) {
    navController.navigate(
        LedgerRoutes.LedgerInvoiceDetailScreen.screen.withArgs(
            ledgerId,
            erpId,
            locusId,
            source,
            isLMSActivated
        )
    )
}

fun navigateToCreditNoteDetailScreen(
    navController: NavHostController,
    ledgerId: String,
    erpId: String?,
    locusId: String?
) {
    navController.navigate(
        LedgerRoutes.LedgerCreditNoteDetailScreen.screen.withArgs(
            ledgerId,
            erpId,
            locusId
        )
    )
}

fun navigateToPaymentDetailScreen(
    navController: NavHostController,
    ledgerId: String,
    erpId: String?,
    locusId: String?,
    mode: String?,
    isLMSActivated: Boolean
) {
    navController.navigate(
        LedgerRoutes.LedgerPaymentDetailScreen.screen.withArgs(
            ledgerId,
            erpId,
            locusId,
            mode,
            isLMSActivated
        )
    )
}

fun navigateToOutstandingDetailPage(
    navController: NavHostController,
    viewState: OutstandingCreditLimitViewState?
) = navController.navigateTo(
    LedgerRoutes.TotalOutstandingDetailScreen.screen,
    args = Bundle().apply {
        putParcelable(LedgerConstants.KEY_OUTSTANDING_CREDIT, viewState)
    }
)

fun navigateToInvoiceListPage(
    navController: NavHostController
) = navController.navigate(
    LedgerRoutes.InvoiceListScreen.screen
)

fun navigateToOtherPaymentModesScreen(
    navController: NavHostController
) = navController.navigate(
    LedgerRoutes.LedgerPaymentDetailScreen.screen
)

fun navigateToAvailableCreditLimitDetailPage(
    navController: NavHostController,
    viewState: AvailableCreditLimitViewState?
) = navController.navigateTo(
    LedgerRoutes.TotalAvailableCreditLimitScreen.screen,
    args = Bundle().apply {
        putParcelable(LedgerConstants.KEY_AVAILABLE_CREDIT, viewState)
    }
)

fun navigateToRevampInvoiceDetailPage(
    navController: NavHostController,
    bundle: Bundle
) = navController.navigateTo(
    route = LedgerRoutes.RevampLedgerInvoiceDetailScreen.screen,
    args = bundle
)

fun navigateToRevampCreditNoteDetailPage(
    navController: NavHostController,
    bundle: Bundle
) = navController.navigateTo(
    route = LedgerRoutes.RevampLedgerCreditNoteDetailScreen.screen,
    args = bundle
)

fun navigateToRevampPaymentDetailPage(
    navController: NavHostController,
    ledgerId: String
) = navController.navigate(
    LedgerRoutes.RevampLedgerPaymentDetailScreen.screen.withArgs(
        ledgerId
    )
)

fun navigateToRevampInterestDetailPage(
    navController: NavHostController,
    ledgerId: String
) = navController.navigate(
    LedgerRoutes.RevampLedgerInterestDetailScreen.screen
)

fun NavController.navigateTo(
    route: String,
    args: Bundle,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    val routeLink = NavDeepLinkRequest
        .Builder
        .fromUri(NavDestination.createRoute(route).toUri())
        .build()

    val deepLinkMatch = graph.matchDeepLink(routeLink)
    if (deepLinkMatch != null) {
        val destination = deepLinkMatch.destination
        val id = destination.id
        navigate(id, args, navOptions, navigatorExtras)
    } else {
        navigate(route, navOptions, navigatorExtras)
    }
}
