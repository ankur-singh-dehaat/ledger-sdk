package lib.dehaat.ledger.navigation

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import lib.dehaat.ledger.initializer.callbacks.LedgerCallBack
import lib.dehaat.ledger.initializer.themes.LedgerColors
import lib.dehaat.ledger.presentation.LedgerConstants
import lib.dehaat.ledger.presentation.LedgerDetailViewModel
import lib.dehaat.ledger.presentation.RevampLedgerViewModel
import lib.dehaat.ledger.presentation.ledger.details.availablecreditlimit.AvailableCreditLimitViewModel
import lib.dehaat.ledger.presentation.ledger.details.availablecreditlimit.ui.AvailableCreditLimitDetailsScreen
import lib.dehaat.ledger.presentation.ledger.details.creditnote.CreditNoteDetailViewModel
import lib.dehaat.ledger.presentation.ledger.details.creditnote.ui.CreditNoteDetailScreen
import lib.dehaat.ledger.presentation.ledger.details.interest.InterestDetailsViewModel
import lib.dehaat.ledger.presentation.ledger.details.interest.ui.InterestDetailScreen
import lib.dehaat.ledger.presentation.ledger.details.invoice.InvoiceDetailViewModel
import lib.dehaat.ledger.presentation.ledger.details.invoice.RevampInvoiceDetailViewModel
import lib.dehaat.ledger.presentation.ledger.details.invoice.ui.InvoiceDetailScreen
import lib.dehaat.ledger.presentation.ledger.details.invoice.ui.RevampInvoiceDetailScreen
import lib.dehaat.ledger.presentation.ledger.details.loanlist.InvoiceListViewModel
import lib.dehaat.ledger.presentation.ledger.details.loanlist.ui.InvoiceListScreen
import lib.dehaat.ledger.presentation.ledger.details.otherpaymentmode.OtherPaymentModesViewModel
import lib.dehaat.ledger.presentation.ledger.details.otherpaymentmode.ui.OtherPaymentModeScreen
import lib.dehaat.ledger.presentation.ledger.details.payments.PaymentDetailViewModel
import lib.dehaat.ledger.presentation.ledger.details.payments.ui.PaymentDetailScreen
import lib.dehaat.ledger.presentation.ledger.details.payments.ui.RevampPaymentDetailScreen
import lib.dehaat.ledger.presentation.ledger.details.totaloutstanding.TotalOutstandingViewModel
import lib.dehaat.ledger.presentation.ledger.details.totaloutstanding.ui.TotalOutstandingScreen
import lib.dehaat.ledger.presentation.ledger.revamp.state.creditnote.CreditNoteDetailsViewModel
import lib.dehaat.ledger.presentation.ledger.revamp.state.creditnote.ui.RevampCreditNoteDetailsScreen
import lib.dehaat.ledger.presentation.ledger.revamp.state.credits.availablecreditlimit.AvailableCreditLimitViewState
import lib.dehaat.ledger.presentation.ledger.revamp.state.credits.outstandingcreditlimit.OutstandingCreditLimitViewState
import lib.dehaat.ledger.presentation.ledger.ui.LedgerDetailScreen2
import lib.dehaat.ledger.presentation.ledger.ui.RevampLedgerScreen
import lib.dehaat.ledger.presentation.model.invoicedownload.InvoiceDownloadData
import lib.dehaat.ledger.util.withArgsPath

@Composable
fun LedgerNavigation(
    dcName: String,
    partnerId: String,
    isDCFinanced: Boolean,
    ledgerColors: LedgerColors,
    ledgerCallbacks: LedgerCallBack,
    resultLauncher: ActivityResultLauncher<Intent?>,
    viewModel: LedgerDetailViewModel,
    onDownloadClick: (InvoiceDownloadData) -> Unit,
    finishActivity: () -> Unit
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (isDCFinanced) {
            LedgerRoutes.RevampLedgerScreen.screen.withArgsPath(
                LedgerConstants.KEY_PARTNER_ID,
                LedgerConstants.KEY_DC_NAME
            )
        } else {
            LedgerRoutes.LedgerDetailScreen.screen.withArgsPath(
                LedgerConstants.KEY_PARTNER_ID
            )
        }
    ) {
        composable(
            route = LedgerRoutes.LedgerDetailScreen.screen.withArgsPath(
                LedgerConstants.KEY_PARTNER_ID
            ),
            arguments = listOf(navArgument(LedgerConstants.KEY_PARTNER_ID) {
                type = NavType.StringType
                defaultValue = partnerId
            })
        ) {
            viewModel.dcName = dcName
            LedgerDetailScreen2(
                viewModel = viewModel,
                ledgerColors = ledgerColors,
                onBackPress = finishActivity,
                detailPageNavigationCallback = provideDetailPageNavCallBacks(navController = navController),
                isLmsActivated = {
                    viewModel.isLMSActivated()
                },
                onPayNowClick = {
                    ledgerCallbacks.onClickPayNow(viewModel.uiState.value.creditSummaryViewData)
                },
                onPaymentOptionsClick = {
                    ledgerCallbacks.onPaymentOptionsClick(resultLauncher)
                }
            )
        }

        composable(
            route = LedgerRoutes.RevampLedgerScreen.screen.withArgsPath(
                LedgerConstants.KEY_PARTNER_ID,
                LedgerConstants.KEY_DC_NAME
            ),
            arguments = listOf(
                navArgument(LedgerConstants.KEY_PARTNER_ID) {
                    type = NavType.StringType
                    defaultValue = partnerId
                },
                navArgument(LedgerConstants.KEY_DC_NAME) {
                    type = NavType.StringType
                    defaultValue = dcName
                }
            )
        ) {
            val revampLedgerViewModel = hiltViewModel<RevampLedgerViewModel>()
            RevampLedgerScreen(
                viewModel = revampLedgerViewModel,
                ledgerColors = ledgerColors,
                onBackPress = finishActivity,
                detailPageNavigationCallback = provideDetailPageNavCallBacks(navController),
                onPayNowClick = { /*TODO*/ },
                onOtherPaymentModeClick = { ledgerCallbacks.onPaymentOptionsClick(resultLauncher) }
            )
        }

        composable(
            route = LedgerRoutes.TotalOutstandingDetailScreen.screen,
            arguments = listOf(
                navArgument(LedgerConstants.KEY_OUTSTANDING_CREDIT) {
                    type = NavType.ParcelableType(OutstandingCreditLimitViewState::class.java)
                    nullable = true
                }
            )
        ) {
            val totalOutstandingViewModel = hiltViewModel<TotalOutstandingViewModel>()
            TotalOutstandingScreen(
                viewModel = totalOutstandingViewModel,
                ledgerColors = ledgerColors,
                onBackPress = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = LedgerRoutes.InvoiceListScreen.screen
        ) {
            val invoiceListViewModel = hiltViewModel<InvoiceListViewModel>()
            InvoiceListScreen(
                viewModel = invoiceListViewModel,
                ledgerColors = ledgerColors
            ) {
                navController.popBackStack()
            }
        }

        composable(
            route = LedgerRoutes.TotalAvailableCreditLimitScreen.screen,
            arguments = listOf(
                navArgument(LedgerConstants.KEY_AVAILABLE_CREDIT) {
                    type = NavType.ParcelableType(AvailableCreditLimitViewState::class.java)
                    nullable = true
                }
            )
        ) {
            val availableCreditLimitViewModel = hiltViewModel<AvailableCreditLimitViewModel>()

            AvailableCreditLimitDetailsScreen(
                viewModel = availableCreditLimitViewModel,
                ledgerColors = ledgerColors
            ) {
                navController.popBackStack()
            }
        }

        composable(
            route = LedgerRoutes.OtherPaymentModesScreen.screen
        ) {
            val otherPaymentModesViewModel = hiltViewModel<OtherPaymentModesViewModel>()

            OtherPaymentModeScreen(
                viewModel = otherPaymentModesViewModel,
                ledgerColors = ledgerColors
            ) {
                navController.popBackStack()
            }
        }

        composable(
            route = LedgerRoutes.LedgerInvoiceDetailScreen.screen.withArgsPath(
                LedgerConstants.KEY_LEDGER_ID,
                LedgerConstants.KEY_ERP_ID,
                LedgerConstants.KEY_LOCUS_ID,
                LedgerConstants.KEY_SOURCE,
                LedgerConstants.KEY_LMS_ACTIVATED
            ),
            arguments = listOf(
                navArgument(LedgerConstants.KEY_LEDGER_ID) {
                    type = NavType.StringType
                },
                navArgument(LedgerConstants.KEY_ERP_ID) {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument(LedgerConstants.KEY_SOURCE) {
                    type = NavType.StringType
                },
                navArgument(LedgerConstants.KEY_LMS_ACTIVATED) {
                    type = NavType.BoolType
                }
            )
        ) {

            val erpId = it.arguments?.get(LedgerConstants.KEY_ERP_ID) as String?
            val source = it.arguments?.get(LedgerConstants.KEY_SOURCE) as String

            val invoiceDetailViewModel = hiltViewModel<InvoiceDetailViewModel>()

            InvoiceDetailScreen(
                viewModel = invoiceDetailViewModel,
                erpId = erpId,
                source = source,
                ledgerColors = ledgerColors,
                onBackPress = {
                    navController.popBackStack()
                },
                onDownloadInvoiceClick = onDownloadClick
            )
        }

        composable(
            route = LedgerRoutes.RevampLedgerInvoiceDetailScreen.screen,
            arguments = RevampInvoiceDetailViewModel.getArgs()
        ) {
            val invoiceDetailViewModel = hiltViewModel<RevampInvoiceDetailViewModel>()
            RevampInvoiceDetailScreen(
                viewModel = invoiceDetailViewModel,
                ledgerColors = ledgerColors,
                onDownloadInvoiceClick = { invoiceId, source -> }
            ) {
                navController.popBackStack()
            }
        }

        composable(
            route = LedgerRoutes.LedgerCreditNoteDetailScreen.screen.withArgsPath(
                LedgerConstants.KEY_LEDGER_ID,
                LedgerConstants.KEY_ERP_ID,
                LedgerConstants.KEY_LOCUS_ID,
            ),
            arguments = listOf(
                navArgument(LedgerConstants.KEY_LEDGER_ID) {
                    type = NavType.StringType
                },
                navArgument(LedgerConstants.KEY_ERP_ID) {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument(LedgerConstants.KEY_LOCUS_ID) {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) {

            val creditNoteDetailViewModel = hiltViewModel<CreditNoteDetailViewModel>()

            CreditNoteDetailScreen(
                viewModel = creditNoteDetailViewModel,
                ledgerColors = ledgerColors
            ) {
                navController.popBackStack()
            }

        }

        composable(
            route = LedgerRoutes.RevampLedgerCreditNoteDetailScreen.screen,
            arguments = CreditNoteDetailsViewModel.getArgs()
        ) {
            val creditNoteDetailsViewModel = hiltViewModel<CreditNoteDetailsViewModel>()
            RevampCreditNoteDetailsScreen(
                viewModel = creditNoteDetailsViewModel,
                ledgerColors = ledgerColors
            ) {
                navController.popBackStack()
            }
        }

        composable(
            route = LedgerRoutes.LedgerPaymentDetailScreen.screen.withArgsPath(
                LedgerConstants.KEY_LEDGER_ID,
                LedgerConstants.KEY_ERP_ID,
                LedgerConstants.KEY_LOCUS_ID,
                LedgerConstants.KEY_PAYMENT_MODE,
                LedgerConstants.KEY_LMS_ACTIVATED
            ),
            arguments = listOf(
                navArgument(LedgerConstants.KEY_LEDGER_ID) {
                    type = NavType.StringType
                },
                navArgument(LedgerConstants.KEY_ERP_ID) {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument(LedgerConstants.KEY_LOCUS_ID) {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument(LedgerConstants.KEY_PAYMENT_MODE) {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument(LedgerConstants.KEY_LMS_ACTIVATED) {
                    type = NavType.BoolType
                    nullable = false
                }
            )
        ) {
            val paymentDetailViewModel = hiltViewModel<PaymentDetailViewModel>()
            PaymentDetailScreen(
                viewModel = paymentDetailViewModel,
                ledgerColors = ledgerColors
            ) {
                navController.popBackStack()
            }
        }

        composable(
            route = LedgerRoutes.RevampLedgerPaymentDetailScreen.screen,
            arguments = PaymentDetailViewModel.getArgs()
        ) {
            val paymentDetailViewModel = hiltViewModel<PaymentDetailViewModel>()
            RevampPaymentDetailScreen(
                viewModel = paymentDetailViewModel,
                ledgerColors = ledgerColors
            ) {
                navController.popBackStack()
            }
        }
    }
}

fun provideDetailPageNavCallBacks(
    navController: NavHostController
) = object : DetailPageNavigationCallback {
    override fun navigateToInvoiceDetailPage(
        legerId: String,
        erpId: String?,
        locusId: String?,
        source: String,
        isLMSActivated: Boolean
    ) {
        navigateToInvoiceDetailScreen(
            navController = navController,
            ledgerId = legerId,
            erpId = erpId,
            locusId = locusId,
            source = source,
            isLMSActivated = isLMSActivated
        )
    }

    override fun navigateToCreditNoteDetailPage(
        legerId: String,
        erpId: String?,
        locusId: String?
    ) {
        navigateToCreditNoteDetailScreen(
            navController = navController,
            ledgerId = legerId,
            erpId = erpId,
            locusId = locusId
        )
    }

    override fun navigateToPaymentDetailPage(
        legerId: String,
        erpId: String?,
        locusId: String?,
        mode: String?,
        isLMSActivated: Boolean
    ) {
        navigateToPaymentDetailScreen(
            navController = navController,
            ledgerId = legerId,
            erpId = erpId,
            locusId = locusId,
            mode = mode,
            isLMSActivated = isLMSActivated
        )
    }

    override fun navigateToOutstandingDetailPage(
        viewState: OutstandingCreditLimitViewState?
    ) {
        navigateToOutstandingDetailPage(navController, viewState)
    }

    override fun navigateToInvoiceListPage() {
        navigateToInvoiceListPage(navController)
    }

    override fun navigateToOtherPaymentModesScreen() {
        navigateToOtherPaymentModesScreen(navController)
    }

    override fun navigateToAvailableCreditLimitDetailPage(
        viewState: AvailableCreditLimitViewState?
    ) {
        navigateToAvailableCreditLimitDetailPage(navController, viewState)
    }

    override fun navigateToRevampInvoiceDetailPage(bundle: Bundle) {
        navigateToRevampInvoiceDetailPage(navController, bundle)
    }

    override fun navigateToRevampCreditNoteDetailPage(bundle: Bundle) {
        navigateToRevampCreditNoteDetailPage(navController, bundle)
    }

    override fun navigateToRevampPaymentDetailPage(
        bundle: Bundle
    ) {
        navigateToRevampPaymentDetailPage(navController, bundle)
    }
}
