package lib.dehaat.ledger.presentation.ledger.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lib.dehaat.ledger.resources.LedgerTheme
import lib.dehaat.ledger.resources.Neutral80
import lib.dehaat.ledger.resources.SeaGreen10
import lib.dehaat.ledger.resources.notoSans
import lib.dehaat.ledger.resources.textButtonB2
import lib.dehaat.ledger.resources.textParagraphT2

@Preview(
    showBackground = true,
    name = "RevampKeyValuePair Preview"
)
@Composable
fun RevampKeyValuePairPreview() = LedgerTheme {
    RevampKeyValuePair(
        modifier = Modifier,
        key = "Key",
        value = "$ 6000",
        backgroundColor = SeaGreen10
    )
}

@Composable
fun RevampKeyValuePair(
    modifier: Modifier,
    key: String,
    value: String,
    backgroundColor: Color
) = Column(
    modifier = modifier
        .fillMaxWidth()
        .background(color = backgroundColor, RoundedCornerShape(8.dp)),
    verticalArrangement = Arrangement.SpaceBetween
) {
    Text(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        text = key,
        style = textParagraphT2(
            textColor = Neutral80,
            fontFamily = notoSans
        )
    )

    Text(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        text = value,
        style = textButtonB2(
            textColor = Neutral80,
            fontFamily = notoSans
        )
    )
}
