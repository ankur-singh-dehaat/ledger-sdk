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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lib.dehaat.ledger.R
import lib.dehaat.ledger.resources.SeaGreen100
import lib.dehaat.ledger.resources.TextWhite
import lib.dehaat.ledger.resources.notoSans
import lib.dehaat.ledger.resources.textButtonB1

@Preview(
    name = "PaymentButton Preview",
    showBackground = true
)
@Composable
private fun PaymentButtonPreviewDBA() {
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
        text = stringResource(id = R.string.pay_now),
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = SeaGreen100,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(vertical = 8.dp),
        textAlign = TextAlign.Center,
        style = textButtonB1(
            textColor = TextWhite,
            fontFamily = notoSans
        )
    )
}
