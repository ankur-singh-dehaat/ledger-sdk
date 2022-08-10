package lib.dehaat.ledger.presentation.ledger.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import lib.dehaat.ledger.initializer.themes.LedgerColors
import lib.dehaat.ledger.navigation.DetailPageNavigationCallback
import lib.dehaat.ledger.presentation.RevampLedgerViewModel
import lib.dehaat.ledger.presentation.common.uicomponent.CommonContainer
import lib.dehaat.ledger.presentation.common.uicomponent.SpaceMedium
import lib.dehaat.ledger.presentation.ledger.bottomsheets.FilterScreen
import lib.dehaat.ledger.presentation.ledger.creditlimit.AvailableCreditLimitScreen
import lib.dehaat.ledger.presentation.ledger.state.BottomSheetType
import lib.dehaat.ledger.presentation.ledger.ui.component.LedgerHeaderScreen
import lib.dehaat.ledger.presentation.ledger.ui.component.TotalOutstandingCalculation
import lib.dehaat.ledger.presentation.ledger.ui.component.TransactionCard
import lib.dehaat.ledger.presentation.ledger.ui.component.TransactionListHeader
import lib.dehaat.ledger.presentation.ledger.ui.component.TransactionType
import lib.dehaat.ledger.resources.Background
import lib.dehaat.ledger.showToast
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
    val uiState by viewModel.uiState.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val context = LocalContext.current

    CommonContainer(
        title = viewModel.dcName,
        onBackPress = onBackPress,
        scaffoldState = scaffoldState,
        backgroundColor = Background,
        bottomBar = {
            AnimatedVisibility(
                visible = !sheetState.isVisible,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                TotalOutstandingCalculation(true)
            }
        }
    ) {
        ModalBottomSheetLayout(
            modifier = Modifier.padding(it),
            sheetContent = {
                when (uiState.bottomSheetType) {
                    is BottomSheetType.DaysFilterTypeSheet -> {
                        FilterScreen { _, _, _ ->
                            scope.launch {
                                sheetState.animateTo(ModalBottomSheetValue.Hidden)
                            }
                        }
                    }
                    else -> Spacer(modifier = Modifier.height(1.dp))
                }
            },
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
                            onPayNowClick = onPayNowClick,
                            onTotalOutstandingDetailsClick = {
                                detailPageNavigationCallback.navigateToOutstandingDetailPage()
                            },
                            onShowInvoiceListDetailsClick = {
                                detailPageNavigationCallback.navigateToInvoiceListPage()
                            },
                            onOtherPaymentModeClick = {
                                detailPageNavigationCallback.navigateToOtherPaymentModesScreen()
                            }
                        )

                        SpaceMedium()

                        AvailableCreditLimitScreen {
                            detailPageNavigationCallback.navigateToAvailableCreditLimitDetailPage()
                        }

                        SpaceMedium()
                    }
                },
                content = {
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        stickyHeader {
                            TransactionListHeader {
                                scope.launch {
                                    viewModel.showFilterBottomSheet()
                                    sheetState.animateTo(ModalBottomSheetValue.Expanded)
                                }
                            }
                        }
                        items(listOf(0, 1, 2, 3)) { type ->
                            when (type) {
                                0 -> TransactionCard(transactionType = TransactionType.Invoice) {
                                    detailPageNavigationCallback.navigateToRevampInvoiceDetailPage()
                                }
                                1 -> TransactionCard(transactionType = TransactionType.CreditNote) {
                                    detailPageNavigationCallback.navigateToRevampCreditNoteDetailPage()
                                }
                                2 -> TransactionCard(transactionType = TransactionType.Payment) {
                                    detailPageNavigationCallback.navigateToRevampPaymentDetailPage("Id") {
                                        context.showToast("Something went wrong, Please try again")
                                    }
                                }
                                3 -> TransactionCard(transactionType = TransactionType.Interest) {
                                    detailPageNavigationCallback.navigateToRevampInterestDetailPage()
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}
