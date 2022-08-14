package lib.dehaat.ledger.presentation.ledger.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import lib.dehaat.ledger.initializer.LedgerSDK
import lib.dehaat.ledger.initializer.themes.LedgerColors
import lib.dehaat.ledger.navigation.DetailPageNavigationCallback
import lib.dehaat.ledger.presentation.RevampLedgerViewModel
import lib.dehaat.ledger.presentation.common.uicomponent.CommonContainer
import lib.dehaat.ledger.presentation.common.uicomponent.SpaceMedium
import lib.dehaat.ledger.presentation.ledger.bottomsheets.FilterScreen
import lib.dehaat.ledger.presentation.ledger.components.NoDataFound
import lib.dehaat.ledger.presentation.ledger.components.ShowProgressDialog
import lib.dehaat.ledger.presentation.ledger.creditlimit.AvailableCreditLimitScreen
import lib.dehaat.ledger.presentation.ledger.revamp.state.UIState
import lib.dehaat.ledger.presentation.ledger.revamp.state.transactions.ui.TransactionsScreen
import lib.dehaat.ledger.presentation.ledger.ui.component.LedgerHeaderScreen
import lib.dehaat.ledger.presentation.ledger.ui.component.TotalOutstandingCalculation
import lib.dehaat.ledger.resources.Background
import lib.dehaat.ledger.util.getAmountInRupees
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
    val nestedScrollViewState = rememberNestedScrollViewState()

    CommonContainer(
        title = viewModel.dcName,
        onBackPress = onBackPress,
        scaffoldState = scaffoldState,
        backgroundColor = Background,
        bottomBar = {
            AnimatedVisibility(
                visible = !sheetState.isVisible && uiState.state == UIState.SUCCESS,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                TotalOutstandingCalculation(
                    uiState.summaryViewData,
                    true
                )
            }
        }
    ) {
        when (uiState.state) {
            is UIState.SUCCESS -> {
                ModalBottomSheetLayout(
                    modifier = Modifier.padding(it),
                    sheetContent = {
                        if (uiState.showFilterSheet) {
                            FilterScreen(
                                onFilterApply = { _, _, _ ->
                                    scope.launch {
                                        sheetState.animateTo(ModalBottomSheetValue.Hidden)
                                    }
                                },
                                onFilterClose = {
                                    scope.launch {
                                        sheetState.animateTo(ModalBottomSheetValue.Hidden)
                                    }
                                }
                            )
                        } else {
                            Spacer(modifier = Modifier.height(1.dp))
                        }
                    },
                    sheetState = sheetState,
                    sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                ) {
                    VerticalNestedScrollView(
                        state = nestedScrollViewState,
                        header = {
                            Column {
                                LedgerHeaderScreen(
                                    summaryViewData = uiState.summaryViewData,
                                    saveInterest = true,
                                    showAdvanceAmount = true,
                                    showPayNowButton = LedgerSDK.isDBA,
                                    onPayNowClick = onPayNowClick,
                                    onTotalOutstandingDetailsClick = {
                                        detailPageNavigationCallback.navigateToOutstandingDetailPage(
                                            viewModel.outstandingCreditLimitViewState
                                        )
                                    },
                                    onShowInvoiceListDetailsClick = {
                                        detailPageNavigationCallback.navigateToInvoiceListPage()
                                    },
                                    onOtherPaymentModeClick = {
                                        detailPageNavigationCallback.navigateToOtherPaymentModesScreen()
                                    }
                                )

                                SpaceMedium()

                                uiState.summaryViewData?.totalAvailableCreditLimit?.let { amount ->
                                    AvailableCreditLimitScreen(amount.getAmountInRupees()) {
                                        detailPageNavigationCallback.navigateToAvailableCreditLimitDetailPage(
                                            viewModel.availableCreditLimitViewState
                                        )
                                    }
                                }

                                SpaceMedium()
                            }
                        },
                        content = {
                            TransactionsScreen(detailPageNavigationCallback) {
                                scope.launch {
                                    viewModel.showFilterBottomSheet()
                                    sheetState.animateTo(ModalBottomSheetValue.Expanded)
                                }
                            }
                        }
                    )
                }
            }
            is UIState.LOADING -> {
                ShowProgressDialog(ledgerColors) {
                    viewModel.updateProgressDialog(false)
                }
            }
            is UIState.ERROR -> {
                NoDataFound((uiState.state as? UIState.ERROR)?.message)
            }
        }
    }
}
