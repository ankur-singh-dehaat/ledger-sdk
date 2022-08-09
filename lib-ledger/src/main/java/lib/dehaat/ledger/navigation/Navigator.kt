package lib.dehaat.ledger.navigation

import androidx.navigation.NavHostController


fun navigateToTotalOutstandingDetailPage(
    navController: NavHostController
) = navController.navigate(
    LedgerRoutes.TotalOutstandingDetailScreen.screen
)

fun navigateToRevampInvoiceDetailPage(
    navController: NavHostController
) = navController.navigate(
    LedgerRoutes.RevampLedgerInvoiceDetailScreen.screen
)

fun navigateToRevampCreditNoteDetailPage(
    navController: NavHostController
) = navController.navigate(
    LedgerRoutes.RevampLedgerCreditNoteDetailScreen.screen
)

fun navigateToRevampPaymentDetailPage(
    navController: NavHostController
) = navController.navigate(
    LedgerRoutes.RevampLedgerPaymentDetailScreen.screen
)

fun navigateToRevampInterestDetailPage(
    navController: NavHostController
) = navController.navigate(
    LedgerRoutes.RevampLedgerInterestDetailScreen.screen
)
