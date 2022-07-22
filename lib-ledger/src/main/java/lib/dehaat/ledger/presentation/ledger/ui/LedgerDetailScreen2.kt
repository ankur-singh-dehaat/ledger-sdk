@file:OptIn(ExperimentalPagerApi::class)

package lib.dehaat.ledger.presentation.ledger.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import lib.dehaat.ledger.initializer.themes.LedgerColors
import lib.dehaat.ledger.navigation.DetailPageNavigationCallback
import lib.dehaat.ledger.presentation.LedgerDetailViewModel
import lib.dehaat.ledger.presentation.common.uicomponent.CommonContainer
import lib.dehaat.ledger.presentation.ledger.bottomsheets.DaysToFilterContent
import lib.dehaat.ledger.presentation.ledger.bottomsheets.LenderOutStandingDetails
import lib.dehaat.ledger.presentation.ledger.bottomsheets.OverAllOutStandingDetails
import lib.dehaat.ledger.presentation.ledger.credits.ui.CreditsScreen
import lib.dehaat.ledger.presentation.ledger.outstanding.TotalOutstandingCalculation
import lib.dehaat.ledger.presentation.ledger.state.BottomSheetType
import lib.dehaat.ledger.presentation.ledger.transactions.ui.TransactionsListScreen
import lib.dehaat.ledger.presentation.ledger.transactions.ui.component.DehaatTransactions
import lib.dehaat.ledger.presentation.ledger.ui.component.DCFinancedHeader
import lib.dehaat.ledger.presentation.ledger.ui.component.FilterScreen
import lib.dehaat.ledger.presentation.ledger.ui.component.FilterStrip
import lib.dehaat.ledger.presentation.ledger.ui.component.Header
import lib.dehaat.ledger.presentation.ledger.ui.component.Tabs
import lib.dehaat.ledger.presentation.ledger.ui.component.TransactionSummary
import lib.dehaat.ledger.presentation.ledger.ui.component.TransactionTabs
import moe.tlaster.nestedscrollview.VerticalNestedScrollView
import moe.tlaster.nestedscrollview.rememberNestedScrollViewState

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun LedgerDetailScreen2(
    viewModel: LedgerDetailViewModel,
    isDCFinanced: Boolean,
    ledgerColors: LedgerColors,
    onBackPress: () -> Unit,
    detailPageNavigationCallback: DetailPageNavigationCallback,
    isLmsActivated: () -> Boolean,
    onPayNowClick: () -> Unit,
    onPaymentOptionsClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = {
            if (it == ModalBottomSheetValue.Hidden) {
            }
            true
        })

    val bottomBarVisibility = rememberSaveable { mutableStateOf(true) }
    if (uiState.isFilteringWithRange) {
        RangeFilterDialog(
            ledgerColors = ledgerColors,
            filtered = {
                viewModel.getTransactionSummaryFromServer()
                viewModel.showDaysRangeFilterDialog(false)
            }
        )
    }

    CommonContainer(
        title = viewModel.dcName,
        onBackPress = onBackPress,
        scaffoldState = scaffoldState,
        bottomBar = {
            if (isDCFinanced) {
                TotalOutstandingCalculation()
            } else {
                AnimatedVisibility(
                    visible = bottomBarVisibility.value
                ) {
                    TransactionSummary(viewModel, ledgerColors)
                }
            }
        }
    ) {
        ModalBottomSheetLayout(
            modifier = Modifier.padding(it),
            sheetContent = {

                when (val bottomSheetType = uiState.bottomSheetType) {
                    is BottomSheetType.LenderOutStanding -> LenderOutStandingDetails(
                        data = bottomSheetType.data,
                        ledgerColors = ledgerColors
                    )
                    is BottomSheetType.OverAllOutStanding ->
                        OverAllOutStandingDetails(
                            data = bottomSheetType.data,
                            ledgerColors = ledgerColors
                        )
                    is BottomSheetType.DaysFilterTypeSheet -> DaysToFilterContent(
                        selectedFilter = bottomSheetType.selectedFilter,
                        ledgerColors = ledgerColors,
                        onFilterSelected = { daysToFilter ->
                            viewModel.updateSelectedFilter(daysToFilter)
                            viewModel.getTransactionSummaryFromServer()
                            scope.launch {
                                sheetState.animateTo(ModalBottomSheetValue.Hidden)
                            }
                        }
                    )
                    is BottomSheetType.Filters -> FilterScreen() // TODO incomplete
                }

            },
            sheetBackgroundColor = Color.White,
            sheetState = sheetState,
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        ) {
            val nestedScrollViewState = rememberNestedScrollViewState()
            VerticalNestedScrollView(
                state = nestedScrollViewState,
                header = {
                    if (isDCFinanced) {
                        DCFinancedHeader(
                            showLoanList = true,
                            outstandingNotApplicable = true,
                            onOutstandingLimitClick = {
                                detailPageNavigationCallback.navigateToTotalOutstandingDetailPage(
                                    title = "कुल बकाया विवरण"
                                )
                            },
                            onCreditSummaryClick = {
                                detailPageNavigationCallback.navigateToTotalOutstandingDetailPage(
                                    title = "उपलब्ध क्रेडिट लिमिट"
                                )
                            },
                            onLoanListClick = {},
                            payNowClick = {},
                            otherPaymentMethodClick = {

                            }
                        )
                    } else {
                        Header(
                            creditSummaryData = uiState.creditSummaryViewData,
                            ledgerColors = ledgerColors,
                            isLmsActivated = isLmsActivated,
                            onPayNowClick = onPayNowClick,
                            onClickTotalOutstandingInfo = {
                                scope.launch {
                                    viewModel.openAllOutstandingModal()
                                    sheetState.animateTo(ModalBottomSheetValue.Expanded)
                                }
                            },
                            onPaymentOptionsClick = onPaymentOptionsClick
                        )
                    }
                },
                content = {
                    val pagerState = rememberPagerState(pageCount = 2)
                    LaunchedEffect(key1 = pagerState) {
                        snapshotFlow { pagerState.currentPage }.collect { currentPage ->
                            bottomBarVisibility.value = currentPage == 0
                        }
                    }
                    Column(modifier = Modifier.background(ledgerColors.TransactionAndCreditScreenBGColor)) {
                        if (isDCFinanced) {
                            TransactionTabs(pagerState) { currentPage ->
                                scope.launch {
                                    pagerState.animateScrollToPage(currentPage)
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            FilterStrip {
                                viewModel.showFiltering()
                                scope.launch { sheetState.animateTo(ModalBottomSheetValue.Expanded) }
                            }

                            Spacer(modifier = Modifier.height(18.dp))
                        } else {
                            Tabs(pagerState, ledgerColors) { currentPage ->
                                scope.launch {
                                    pagerState.animateScrollToPage(page = currentPage)
                                }
                            }
                        }
                        HorizontalPager(
                            modifier = Modifier
                                .weight(1f)
                                .background(color = ledgerColors.TransactionAndCreditScreenBGColor),
                            state = pagerState,
                            verticalAlignment = Alignment.Top
                        ) { index ->
                            when (index) {
                                1 -> CreditsScreen(
                                    ledgerDetailViewModel = viewModel,
                                    ledgerColors = ledgerColors,
                                    isLmsActivated = isLmsActivated,
                                ) {
                                    viewModel.showLenderOutstandingModal(it)
                                    scope.launch { sheetState.animateTo(ModalBottomSheetValue.Expanded) }
                                }
                                else -> {
                                    if (isDCFinanced) {
                                        DehaatTransactions(TransactionCallbacks(
                                            {}, {}, {}
                                        ))
                                    } else {
                                        TransactionsListScreen(
                                            ledgerColors = ledgerColors,
                                            detailPageNavigationCallback = detailPageNavigationCallback,
                                            ledgerDetailViewModel = viewModel,
                                            openDaysFilter = {
                                                viewModel.showDaysFilterBottomSheet()
                                                scope.launch {
                                                    sheetState.animateTo(
                                                        ModalBottomSheetValue.Expanded
                                                    )
                                                }
                                            },
                                            openRangeFilter = {
                                                viewModel.showDaysRangeFilterDialog(true)
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}
