package lib.dehaat.ledger.presentation.ledger.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import lib.dehaat.ledger.resources.notoSans
import lib.dehaat.ledger.resources.text14Sp
import lib.dehaat.ledger.resources.text16Sp
import lib.dehaat.ledger.resources.textLarge

@Preview(
    name = "AvailableCreditScreen Preview DBA",
    showBackground = true
)
@Composable
fun AvailableCreditScreenPreviewDBA() {
    AvailableCreditScreen {

    }
}

@Composable
fun AvailableCreditScreen(
    onCreditSummaryClick: () -> Unit
) = Column {
    Text(
        text = "ऑर्डर करने के लिए उपलब्ध क्रेडिट",
        style = text16Sp(fontFamily = notoSans)
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = "₹ 1,60,000",
            style = textLarge(
                fontFamily = notoSans,
            )
        )
        Text(
            modifier = Modifier.clickable(onClick = onCreditSummaryClick),
            text = "विवरण देखे",
            style = text14Sp(
                fontFamily = notoSans,
                textDecoration = TextDecoration.Underline,
                fontWeight = FontWeight.Bold
            )
        )
    }
}