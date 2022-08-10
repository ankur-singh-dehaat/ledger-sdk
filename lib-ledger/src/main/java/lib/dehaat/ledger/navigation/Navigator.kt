package lib.dehaat.ledger.navigation

import androidx.navigation.NavHostController
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
    navController: NavHostController
) = navController.navigate(
    LedgerRoutes.TotalOutstandingDetailScreen.screen
)

fun navigateToInvoiceListPage(
    navController: NavHostController
) = navController.navigate(
    LedgerRoutes.InvoiceListScreen.screen
)

fun navigateToOtherPaymentModesScreen(
    navController: NavHostController
) = navController.navigate(
    LedgerRoutes.OtherPaymentModesScreen.screen
)

fun navigateToAvailableCreditLimitDetailPage(
    navController: NavHostController
) = navController.navigate(
    LedgerRoutes.TotalAvailableCreditLimitScreen.screen
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
    navController: NavHostController,
    ledgerId: String,
    onError: (String) -> Unit
) = try {
    navController.navigate(
        LedgerRoutes.RevampLedgerPaymentDetailScreen.screen.withArgs(
            ledgerId
        )
    )
} catch (e: Exception) {
    e.localizedMessage?.let(onError)
}

fun navigateToRevampInterestDetailPage(
    navController: NavHostController
) = navController.navigate(
    LedgerRoutes.RevampLedgerInterestDetailScreen.screen
)
