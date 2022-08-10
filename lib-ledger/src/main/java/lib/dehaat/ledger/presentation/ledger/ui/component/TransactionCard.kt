package lib.dehaat.ledger.presentation.ledger.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lib.dehaat.ledger.R
import lib.dehaat.ledger.resources.LedgerTheme
import lib.dehaat.ledger.resources.Neutral60
import lib.dehaat.ledger.resources.Neutral80
import lib.dehaat.ledger.resources.Pumpkin120
import lib.dehaat.ledger.resources.SeaGreen110
import lib.dehaat.ledger.resources.notoSans
import lib.dehaat.ledger.resources.textCaptionCP1
import lib.dehaat.ledger.resources.textParagraphT1Highlight

@Preview(
    name = "TransactionCard Invoice Preview",
    showBackground = true
)
@Composable
private fun TransactionCardInvoicePreview() = LedgerTheme {
    TransactionCard(transactionType = TransactionType.Invoice) {}
}

@Preview(
    name = "TransactionCard CreditNote Preview",
    showBackground = true
)
@Composable
private fun TransactionCardCreditNotePreview() = LedgerTheme {
    TransactionCard(transactionType = TransactionType.CreditNote) {}
}

@Preview(
    name = "TransactionCard Payment Preview",
    showBackground = true
)
@Composable
private fun TransactionCardPaymentPreview() = LedgerTheme {
    TransactionCard(transactionType = TransactionType.Payment) {}
}

@Preview(
    name = "TransactionCard Interest Preview",
    showBackground = true
)
@Composable
private fun TransactionCardInterestPreview() = LedgerTheme {
    TransactionCard(transactionType = TransactionType.Interest) {}
}

@Composable
fun TransactionCard(
    transactionType: TransactionType,
    onClick: () -> Unit
) = Column(
    modifier = Modifier
        .clickable(onClick = onClick)
        .background(Color.White)
        .fillMaxWidth()
        .padding(horizontal = 20.dp)
) {
    Spacer(modifier = Modifier.height(12.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()

    ) {

        Image(
            modifier = Modifier
                .height(32.dp)
                .width(32.dp),
            painter = painterResource(id = transactionType.getIcon()),
            contentDescription = stringResource(id = R.string.accessibility_icon)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Transaction Name",
                    style = textParagraphT1Highlight(
                        textColor = Neutral80,
                        fontFamily = notoSans
                    )
                )
                Text(
                    text = "+ ₹ 5,160",
                    style = textParagraphT1Highlight(
                        textColor = transactionType.amountColor(),
                        fontFamily = notoSans
                    )
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "18-जुलाई-2022 से आज तक",
                style = textCaptionCP1(
                    textColor = Neutral60
                )
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
    Divider()
}

sealed class TransactionType {
    object Invoice : TransactionType()
    object CreditNote : TransactionType()
    object Payment : TransactionType()
    object Interest : TransactionType()
}

@DrawableRes
fun TransactionType.getIcon() = when (this) {
    TransactionType.Invoice -> R.drawable.ic_revamp_invoice
    TransactionType.CreditNote -> R.drawable.ic_transactions_credit_note
    TransactionType.Payment -> R.drawable.ic_transactions_payment
    TransactionType.Interest -> R.drawable.ic_transactions_interest
}

fun TransactionType.amountColor() = when (this) {
    TransactionType.Invoice -> Pumpkin120
    TransactionType.CreditNote -> SeaGreen110
    TransactionType.Payment -> SeaGreen110
    TransactionType.Interest -> Pumpkin120
}
