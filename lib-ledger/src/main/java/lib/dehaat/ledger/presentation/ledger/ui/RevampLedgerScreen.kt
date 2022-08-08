package lib.dehaat.ledger.presentation.ledger.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import lib.dehaat.ledger.initializer.themes.LedgerColors
import lib.dehaat.ledger.navigation.DetailPageNavigationCallback
import lib.dehaat.ledger.presentation.RevampLedgerViewModel
import lib.dehaat.ledger.presentation.common.uicomponent.CommonContainer
import lib.dehaat.ledger.presentation.common.uicomponent.SpaceMedium
import lib.dehaat.ledger.presentation.ledger.creditlimit.AvailableCreditLimitScreen
import lib.dehaat.ledger.presentation.ledger.ui.component.LedgerHeaderScreen
import lib.dehaat.ledger.presentation.ledger.ui.component.TotalOutstandingCalculation
import lib.dehaat.ledger.presentation.ledger.ui.component.TransactionListHeader
import lib.dehaat.ledger.resources.Background
import moe.tlaster.nestedscrollview.VerticalNestedScrollView
import moe.tlaster.nestedscrollview.rememberNestedScrollViewState

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
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
    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    CommonContainer(
        title = viewModel.dcName,
        onBackPress = onBackPress,
        scaffoldState = scaffoldState,
        backgroundColor = Background,
        bottomBar = { TotalOutstandingCalculation(true) }
    ) {
        ModalBottomSheetLayout(
            modifier = Modifier.padding(it),
            sheetContent = { Text(text = "") },
            sheetState = sheetState,
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ) {
            val nestedScrollViewState = rememberNestedScrollViewState()
            VerticalNestedScrollView(
                state = nestedScrollViewState,
                header = {
                    Column {
                        LedgerHeaderScreen(
                            saveInterest = true,
                            showAdvanceAmount = true,
                            showPayNowButton = true,
                            onPayNowClick = onPayNowClick
                        ) {

                        }

                        SpaceMedium()

                        AvailableCreditLimitScreen {}

                        SpaceMedium()
                    }
                },
                content = {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        stickyHeader {
                            TransactionListHeader {

                            }
                        }
                        items((0..1000).map { it }) {
                            Text(text = it.toString())
                        }
                    }
                }
            )
        }
    }
}
