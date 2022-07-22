package lib.dehaat.ledger.presentation.ledger.ui

import android.content.Context
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import lib.dehaat.ledger.R
import lib.dehaat.ledger.presentation.ledger.transactions.constants.TransactionType
import lib.dehaat.ledger.presentation.ledger.transactions.ui.component.DehaatTransactions
import lib.dehaat.ledger.presentation.ledger.ui.component.DCFinancedHeader
import lib.dehaat.ledger.presentation.ledger.ui.component.FilterStrip
import lib.dehaat.ledger.presentation.ledger.ui.component.TransactionTabs
import lib.dehaat.ledger.resources.notoSans
import lib.dehaat.ledger.resources.text14Sp
import lib.dehaat.ledger.resources.text16Sp
import lib.dehaat.ledger.resources.textMedium14Sp
import moe.tlaster.nestedscrollview.VerticalNestedScrollView
import moe.tlaster.nestedscrollview.rememberNestedScrollViewState

/*@Preview(showBackground = true)
@Composable
fun HeaderPreview() = MainBody(false)*/

@Preview(showBackground = true)
@Composable
fun HeaderPreviewLoanList() = VerticalNestedScrollView(
    state = rememberNestedScrollViewState()
) {
    MainBody(true)
}

@Composable
fun UnSelectedTab(
    modifier: Modifier,
    label: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(44.dp)
            .background(shape = RoundedCornerShape(8.dp), color = Color(0xFFFFFFFF))
            .border(
                border = BorderStroke(width = 1.2.dp, color = Color(0xFFE0E0E0)),
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.clickable(onClick = onClick),
            text = label,
            textAlign = TextAlign.Center,
            style = text14Sp()
        )
    }
}

@Composable
fun SelectedTab(
    modifier: Modifier,
    label: String,
    onClick: () -> Unit
) = Box(
    modifier = modifier
        .height(44.dp)
        .background(shape = RoundedCornerShape(8.dp), color = Color(0XFFF7FBF8))
        .border(
            border = BorderStroke(width = 1.2.dp, color = Color(0XFF0F8040)),
            shape = RoundedCornerShape(8.dp)
        ),
    contentAlignment = Alignment.Center
) {
    Text(
        modifier = Modifier.clickable(onClick = onClick),
        text = label,
        textAlign = TextAlign.Center,
        style = textMedium14Sp(textColor = Color(0XFF0F8040))
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Pagers(
    pagerState: PagerState,
    transactionCallbacks: TransactionCallbacks
) = HorizontalPager(state = pagerState) { position ->
    if (position == 0) {
        DehaatTransactions(transactionCallbacks)
    } else {
        CreditLine()
    }
}

@Composable
fun CreditLine() = LazyColumn(
    contentPadding = PaddingValues(16.dp),
    modifier = Modifier
) {
    items(listOf(0, 1, 2)) { item ->

    }
}

@Composable
fun TransactionCard(
    transactions: TransactionUiState,
    onClick: () -> Unit
) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = onClick)
) {
    Icon(
        modifier = Modifier
            .height(32.dp)
            .width(32.dp),
        painter = painterResource(id = transactions.icon),
        contentDescription = "",
        tint = transactions.tint
    )
    Spacer(modifier = Modifier.width(8.dp))
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = transactions.transaction.title,
                style = text16Sp(fontFamily = notoSans)
            )
            Text(
                text = transactions.transaction.amount,
                style = text16Sp(fontFamily = notoSans)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = transactions.transaction.date)
    }
}

@Composable
fun CreditNote(
    onCreditNoteClick: () -> Unit
) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = onCreditNoteClick)
) {

}

@Composable
fun Invoice(
    onInvoiceClick: () -> Unit
) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = onInvoiceClick)
) {

}


// TODO unnecessary
@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainBody(
    showLoanList: Boolean
) {
    val context = LocalContext.current
    val pagerState = rememberPagerState(pageCount = 2)
    LaunchedEffect(key1 = pagerState) {
        snapshotFlow { pagerState.currentPage }.collect {
            context.showToast("pageSelected $it")
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
    ) {
        DCFinancedHeader(
            showLoanList = showLoanList,
            outstandingNotApplicable = true,
            onOutstandingLimitClick = {
                context.showToast("onSummaryClick")
            },
            onCreditSummaryClick = {},
            onLoanListClick = {
                context.showToast("onLoanListClick")
            }, otherPaymentMethodClick = {}
        )
        Spacer(modifier = Modifier.height(16.dp))
        TransactionTabs(pagerState) {
            context.showToast(pagerState.currentPage.toString())
        }
        Spacer(modifier = Modifier.height(8.dp))
        FilterStrip {
            context.showToast("filter Clicked")
        }
        Spacer(modifier = Modifier.height(18.dp))
        Pagers(
            pagerState = pagerState,
            transactionCallbacks = TransactionCallbacks(
                paymentCallBack = {},
                creditNodeCallBack = {},
                invoiceCallBack = {}
            )
        )
    }
}

fun Context.showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

class TransactionCallbacks(
    val paymentCallBack: PaymentCallBack,
    val creditNodeCallBack: CreditNodeCallBack,
    val invoiceCallBack: InvoiceCallBack
)

data class TransactionUiState(
    @DrawableRes val icon: Int,
    val tint: Color,
    val transaction: Transaction
)

typealias PaymentCallBack = () -> Unit
typealias CreditNodeCallBack = () -> Unit
typealias InvoiceCallBack = () -> Unit

data class Transaction(
    val title: String = "ब्लैक सोईल द्वारा देहात को भुगतान",
    val amount: String = "₹ 40,000",
    val date: String = "10-जून-2022"
)

fun provideTransactionIcon(type: String) = when (type) {
    TransactionType.INVOICE -> R.drawable.ic_ledger_invoice
    TransactionType.PAYMENT -> R.drawable.ic_payment
    TransactionType.CREDIT_NOTE -> R.drawable.ic_transaction_credit_note
    else -> R.drawable.ic_ledger_invoice
}