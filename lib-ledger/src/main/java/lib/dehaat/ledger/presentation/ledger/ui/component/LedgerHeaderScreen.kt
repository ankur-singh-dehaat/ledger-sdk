package lib.dehaat.ledger.presentation.ledger.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lib.dehaat.ledger.R
import lib.dehaat.ledger.datasource.DummyDataSource
import lib.dehaat.ledger.presentation.model.revamp.SummaryViewData
import lib.dehaat.ledger.resources.Neutral70
import lib.dehaat.ledger.resources.Neutral80
import lib.dehaat.ledger.resources.Neutral90
import lib.dehaat.ledger.resources.notoSans
import lib.dehaat.ledger.resources.textCaptionCP1
import lib.dehaat.ledger.resources.textHeadingH3
import lib.dehaat.ledger.resources.textParagraphT1
import lib.dehaat.ledger.resources.textParagraphT2
import lib.dehaat.ledger.util.getAmountInRupees

@Preview(
    showBackground = true,
    name = "LedgerHeaderScreen Preview"
)
@Composable
private fun LedgerHeaderScreenPreview() {
    LedgerHeaderScreen(
        summaryViewData = DummyDataSource.summaryViewData,
        saveInterest = true,
        showAdvanceAmount = true,
        showPayNowButton = true,
        onPayNowClick = {},
        onTotalOutstandingDetailsClick = {},
        onShowInvoiceListDetailsClick = {},
        onOtherPaymentModeClick = {}
    )
}

@Composable
fun LedgerHeaderScreen(
    summaryViewData: SummaryViewData?,
    saveInterest: Boolean,
    showAdvanceAmount: Boolean,
    showPayNowButton: Boolean,
    onPayNowClick: () -> Unit,
    onTotalOutstandingDetailsClick: () -> Unit,
    onShowInvoiceListDetailsClick: () -> Unit,
    onOtherPaymentModeClick: () -> Unit
) = Column(
    modifier = Modifier
        .background(Color.White)
        .padding(horizontal = 20.dp)
        .padding(top = 24.dp, bottom = 12.dp)
        .fillMaxWidth()
) {
    Text(
        text = stringResource(id = R.string.total_outstanding),
        style = textParagraphT1(
            textColor = Neutral90,
            fontFamily = notoSans
        )
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = summaryViewData?.totalOutstandingAmount.getAmountInRupees(),
            style = textHeadingH3(
                textColor = Neutral80
            )
        )

        ViewDetails(onTotalOutstandingDetailsClick)
    }

    val totalOutstandingAmount = summaryViewData?.totalOutstandingAmount?.toIntOrNull() ?: 0
    if (totalOutstandingAmount < 0) {
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(id = R.string.your_advance_amount, totalOutstandingAmount.toString().getAmountInRupees()),
            style = textCaptionCP1(
                textColor = Neutral80,
                fontFamily = notoSans
            )
        )
    }

    if (saveInterest) {

        Spacer(modifier = Modifier.height(12.dp))

        SaveInterestScreen(
            summaryViewData = summaryViewData,
            showDetails = true,
            onViewDetailsClick = onShowInvoiceListDetailsClick
        )
    }

    if (showPayNowButton) {

        Spacer(modifier = Modifier.height(12.dp))
        PaymentButton(payNowClick = onPayNowClick)
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onOtherPaymentModeClick),
            text = stringResource(id = R.string.know_other_payment_methods),
            style = textParagraphT2(
                textColor = Neutral70,
                textDecoration = TextDecoration.Underline,
                fontFamily = notoSans
            ),
            textAlign = TextAlign.End
        )
    }
}
