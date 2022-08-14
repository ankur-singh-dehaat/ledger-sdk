package lib.dehaat.ledger.presentation.ledger.revamp.state.transactions.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import lib.dehaat.ledger.navigation.DetailPageNavigationCallback
import lib.dehaat.ledger.presentation.ledger.revamp.state.transactions.TransactionViewModel
import lib.dehaat.ledger.presentation.ledger.ui.component.TransactionCard
import lib.dehaat.ledger.presentation.ledger.ui.component.TransactionListHeader
import lib.dehaat.ledger.presentation.ledger.ui.component.TransactionType

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TransactionsScreen(
    detailPageNavigationCallback: DetailPageNavigationCallback,
    showFilterSheet: () -> Unit
) {
    val viewModel = hiltViewModel<TransactionViewModel>()
    val transactions = viewModel.transactionsList.collectAsLazyPagingItems()
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        stickyHeader {
            TransactionListHeader(showFilterSheet)
        }
        items(transactions) { transaction ->
            transaction?.let {
                when (transaction.type) {
                    TransactionType.Invoice().invoiceType -> TransactionCard(
                        transactionType = TransactionType.Invoice(),
                        transaction = transaction
                    ) {
                        detailPageNavigationCallback.navigateToRevampInvoiceDetailPage(
                            transaction.ledgerId
                        )
                    }
                    TransactionType.CreditNote().creditNoteType -> TransactionCard(
                        transactionType = TransactionType.CreditNote(),
                        transaction = transaction
                    ) {
                        detailPageNavigationCallback.navigateToRevampCreditNoteDetailPage(
                            transaction.ledgerId
                        )
                    }
                    TransactionType.Payment().paymentType -> TransactionCard(
                        transactionType = TransactionType.Payment(),
                        transaction = transaction
                    ) {
                        detailPageNavigationCallback.navigateToRevampPaymentDetailPage(
                            transaction.ledgerId
                        )
                    }
                    TransactionType.Interest().interestType -> TransactionCard(
                        transactionType = TransactionType.Interest(),
                        transaction = transaction
                    ) {
                        detailPageNavigationCallback.navigateToRevampInterestDetailPage(
                            transaction.ledgerId
                        )
                    }
                }
            }
        }
    }
}
