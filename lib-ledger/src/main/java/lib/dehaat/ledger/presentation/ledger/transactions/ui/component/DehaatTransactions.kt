package lib.dehaat.ledger.presentation.ledger.transactions.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lib.dehaat.ledger.presentation.ledger.transactions.constants.TransactionType
import lib.dehaat.ledger.presentation.ledger.ui.Transaction
import lib.dehaat.ledger.presentation.ledger.ui.TransactionCallbacks
import lib.dehaat.ledger.presentation.ledger.ui.TransactionCard
import lib.dehaat.ledger.presentation.ledger.ui.TransactionUiState

@Preview(
    name = "DehaatTransactions Preview DBA",
    showBackground = true
)
@Composable
fun DehaatTransactionsPreviewDBA() {
    DehaatTransactions(
        transactionCallbacks = TransactionCallbacks({}, {}, {})
    )
}

@Composable
fun DehaatTransactions(
    transactionCallbacks: TransactionCallbacks
) = LazyColumn(
    modifier = Modifier.padding(horizontal = 20.dp)
) {
    items(listOf(0, 1, 2)) { item ->
        Divider()
        Spacer(modifier = Modifier.height(12.dp))
        when (item) {
            0 -> {
                TransactionCard(
                    transactions = TransactionUiState(
                        icon = lib.dehaat.ledger.presentation.ledger.ui.provideTransactionIcon(
                            TransactionType.PAYMENT
                        ),
                        tint = Color(0XFFFFA11F),
                        transaction = Transaction(
                            title = "ब्लैक सोईल द्वारा देहात को भुगतान",
                            amount = "₹ 40,000",
                            date = "10-जून-2022"
                        )
                    ),
                    onClick = transactionCallbacks.paymentCallBack
                )
            }
            1 -> {
                TransactionCard(
                    transactions = TransactionUiState(
                        icon = lib.dehaat.ledger.presentation.ledger.ui.provideTransactionIcon(
                            TransactionType.CREDIT_NOTE
                        ),
                        tint = Color(0XFF528FCC),
                        transaction = Transaction(
                            title = "क्रेडिट नोट: सामान वापसी ",
                            amount = "₹ 4,000",
                            date = "10-जून-2022"
                        )
                    ),
                    onClick = transactionCallbacks.creditNodeCallBack
                )
            }
            2 -> {
                TransactionCard(
                    transactions = TransactionUiState(
                        icon = lib.dehaat.ledger.presentation.ledger.ui.provideTransactionIcon(
                            TransactionType.INVOICE
                        ),
                        tint = Color(0XFF1F9954),
                        transaction = Transaction(
                            title = "इनवॉइस",
                            amount = "₹ 4,000",
                            date = "10-जून-2022"
                        )
                    ),
                    onClick = transactionCallbacks.invoiceCallBack
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
