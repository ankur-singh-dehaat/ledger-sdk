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
import lib.dehaat.ledger.resources.Neutral70
import lib.dehaat.ledger.resources.Neutral80
import lib.dehaat.ledger.resources.Neutral90
import lib.dehaat.ledger.resources.notoSans
import lib.dehaat.ledger.resources.textCaptionCP1
import lib.dehaat.ledger.resources.textHeadingH3
import lib.dehaat.ledger.resources.textParagraphT1
import lib.dehaat.ledger.resources.textParagraphT2

@Preview(
    showBackground = true
)
@Composable
private fun LedgerHeaderScreenPreview() {
    LedgerHeaderScreen(
        saveInterest = true,
        showAdvanceAmount = true,
        showPayNowButton = true,
        onPayNowClick = {},
        onViewDetailsClick = {}
    )
}

@Composable
fun LedgerHeaderScreen(
    saveInterest: Boolean,
    showAdvanceAmount: Boolean,
    showPayNowButton: Boolean,
    onPayNowClick: () -> Unit,
    onViewDetailsClick: () -> Unit
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
            text = "₹ 3,20,000",
            style = textHeadingH3(
                textColor = Neutral80
            )
        )

        ViewDetails(onViewDetailsClick)
    }

    if (showAdvanceAmount) {
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "₹ 20,000 आपका एडवांस राशि है",
            style = textCaptionCP1(
                textColor = Neutral80,
                fontFamily = notoSans
            )
        )
    }

    if (saveInterest) {

        Spacer(modifier = Modifier.height(12.dp))

        SaveInterestScreen(showDetails = true) {

        }
    }

    if (showPayNowButton) {

        Spacer(modifier = Modifier.height(12.dp))
        PaymentButton(payNowClick = onPayNowClick)
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = {}
                ),
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