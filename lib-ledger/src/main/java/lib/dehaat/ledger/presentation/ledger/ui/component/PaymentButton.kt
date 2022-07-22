package lib.dehaat.ledger.presentation.ledger.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lib.dehaat.ledger.resources.TextWhite
import lib.dehaat.ledger.resources.notoSans
import lib.dehaat.ledger.resources.text16Sp

@Preview(
    name = "PaymentButton Preview DBA",
    showBackground = true
)
@Composable
fun PaymentButtonPreviewDBA() {
    PaymentButton {}
}

@Composable
fun PaymentButton(
    payNowClick: () -> Unit
) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = payNowClick)
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