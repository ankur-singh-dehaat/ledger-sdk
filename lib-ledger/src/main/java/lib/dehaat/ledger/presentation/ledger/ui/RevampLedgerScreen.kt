package lib.dehaat.ledger.presentation.ledger.ui

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import lib.dehaat.ledger.initializer.themes.LedgerColors
import lib.dehaat.ledger.navigation.DetailPageNavigationCallback
import lib.dehaat.ledger.presentation.RevampLedgerViewModel
import lib.dehaat.ledger.presentation.common.uicomponent.CommonContainer
import lib.dehaat.ledger.presentation.ledger.ui.component.TotalOutstandingCalculation
import lib.dehaat.ledger.resources.Background

@Composable
fun RevampLedgerScreen(
    viewModel: RevampLedgerViewModel,
    ledgerColors: LedgerColors,
    onBackPress: () -> Unit,
    detailPageNavigationCallback: DetailPageNavigationCallback,
    onPayNowClick: () -> Unit,
    onPaymentOptionsClick: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    CommonContainer(
        title = viewModel.dcName,
        onBackPress = onBackPress,
        scaffoldState = scaffoldState,
        backgroundColor = Background,
        bottomBar = { TotalOutstandingCalculation() }
    ) {

    }
}
