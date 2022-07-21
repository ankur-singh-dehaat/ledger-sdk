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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lib.dehaat.ledger.resources.TextWhite
import lib.dehaat.ledger.resources.notoSans
import lib.dehaat.ledger.resources.text14Sp
import lib.dehaat.ledger.resources.text16Sp
import lib.dehaat.ledger.resources.textLarge

@Preview(
    name = "DCFinancedHeader Preview DBA",
    showBackground = true
)
@Composable
fun DCFinancedHeaderPreviewDBA() {
    DCFinancedHeader(
        showLoanList = true,
        outstandingNotApplicable = true,
        onOutstandingLimitClick = { },
        onCreditSummaryClick = {},
        onLoanListClick = {},
        otherPaymentMethodClick = {}
    )
}

@Composable
fun DCFinancedHeader(
    showLoanList: Boolean,
    outstandingNotApplicable: Boolean,
    onOutstandingLimitClick: () -> Unit,
    onCreditSummaryClick: () -> Unit,
    onLoanListClick: () -> Unit,
    otherPaymentMethodClick: () -> Unit
) = Column(
    modifier = Modifier
        .padding(horizontal = 20.dp)
) {
    Text(
        text = "कुल बकाया",
        style = text16Sp(fontFamily = notoSans)
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = "₹ 3,20,000",
            style = textLarge(
                fontFamily = notoSans,
                fontWeight = FontWeight.Bold
            )
        )

        Text(
            modifier = Modifier.clickable(onClick = onOutstandingLimitClick),
            text = "विवरण देखे",
            style = text14Sp(
                textDecoration = TextDecoration.Underline,
                fontFamily = notoSans,
                fontWeight = FontWeight.Bold
            )
        )
    }

    Divider(modifier = Modifier.padding(vertical = 16.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "ब्लैकसोईल का बकाया",
            style = text14Sp(fontFamily = notoSans)
        )

        Text(
            text = "₹ 2,40,000",
            style = text14Sp(fontFamily = notoSans)
        )
    }

    Spacer(modifier = Modifier.height(12.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "बकाया जो फ़ाइनैन्स होना बाक़ी है ",
            style = text14Sp(fontFamily = notoSans)
        )

        Text(
            text = "₹ 80,000",
            style = text14Sp(fontFamily = notoSans)
        )
    }

    if (showLoanList) {
        Divider(modifier = Modifier.padding(vertical = 16.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "आपके कुछ लोन की ब्याज शुरू तिथि निकट है -\nजल्द भुगतान करें",
            style = text14Sp(textColor = Color(0xFFDC3C3D), fontFamily = notoSans)
        )

        Text(
            modifier = Modifier.clickable(onClick = onLoanListClick),
            text = "लोन लिस्ट देखें",
            style = text14Sp(
                fontFamily = notoSans,
                textDecoration = TextDecoration.Underline,
                fontWeight = FontWeight.Bold
            )
        )
    }

    if (outstandingNotApplicable) {
        Divider(modifier = Modifier.padding(vertical = 16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "ब्लैकसोईल का बकाया",
                style = text14Sp(
                    fontFamily = notoSans,
                    lineHeight = 20.sp
                )
            )

            Text(
                text = "₹ 0",
                style = text14Sp(fontFamily = notoSans)
            )
        }

    }

    Spacer(modifier = Modifier.height(24.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "ब्लैकसोईल को ऐप द्वारा भुगतान करें",
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFF0F8040),
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable {}
                .padding(vertical = 16.dp),
            textAlign = TextAlign.Center,
            style = text16Sp(
                textColor = TextWhite,
                fontFamily = notoSans,
                fontWeight = FontWeight.Bold
            )
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = otherPaymentMethodClick),
        text = "भुगतान के अन्य तरीक़े जानिए",
        textAlign = TextAlign.End,
        style = text14Sp(fontFamily = notoSans, textDecoration = TextDecoration.Underline)
    )

    AvailableCreditScreen(
        onCreditSummaryClick = onCreditSummaryClick
    )
    
    Spacer(modifier = Modifier.height(16.dp))
}
