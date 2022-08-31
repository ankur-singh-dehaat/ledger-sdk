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

fun navigateToInvoiceDetailScreen(
    navController: NavHostController,
    args: Bundle
) = navController.navigateTo(
    route = LedgerRoutes.LedgerInvoiceDetailScreen.screen,
    args = args
)

fun navigateToCreditNoteDetailScreen(
    navController: NavHostController,
    args: Bundle
) = navController.navigateTo(
    route = LedgerRoutes.LedgerCreditNoteDetailScreen.screen,
    args = args
)

fun navigateToPaymentDetailScreen(
    navController: NavHostController,
    args: Bundle
) = navController.navigateTo(
    route = LedgerRoutes.LedgerPaymentDetailScreen.screen,
    args = args
)

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
    navController: NavHostController,
    bundle: Bundle
) = navController.navigateTo(
    route = LedgerRoutes.InvoiceListScreen.screen,
    args = bundle
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
    bundle: Bundle
) = navController.navigateTo(
    route = LedgerRoutes.RevampLedgerPaymentDetailScreen.screen,
    args = bundle
)

fun navigateToRevampWeeklyInterestDetailPage(
    navController: NavHostController,
    bundle: Bundle
) = navController.navigateTo(
    route = LedgerRoutes.RevampLedgerWeeklyInterestDetailScreen.screen,
    args = bundle
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
