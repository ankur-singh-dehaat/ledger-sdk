package lib.dehaat.ledger.presentation.ledger.outstanding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lib.dehaat.ledger.R
import lib.dehaat.ledger.resources.TextBrown
import lib.dehaat.ledger.resources.TextGreyLight
import lib.dehaat.ledger.resources.notoSans
import lib.dehaat.ledger.resources.text12Sp
import lib.dehaat.ledger.resources.text14Sp

@Preview(
    name = "TotalOutstandingCalculation Preview DBA",
    showBackground = true
)
@Composable
fun TotalOutstandingCalculationPreviewDBA() {
    TotalOutstandingCalculation()
}

@Composable
fun TotalOutstandingCalculation() = Card(
    modifier = Modifier
        .fillMaxWidth(),
    shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
) {
    Spacer(modifier = Modifier.height(8.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_idea),
                contentDescription = ""
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "कुल बकाया कैल्क्युलेशन विधि:",
                style = text14Sp(
                    fontFamily = notoSans
                ),
                color = TextGreyLight
            )
        }

        Row(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "कुल\nबकाया",
                style = text14Sp(
                    fontFamily = notoSans
                ),
                textAlign = TextAlign.Center
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_circle_right_24),
                contentDescription = ""
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_left_bracket),
                contentDescription = "",
                tint = TextBrown
            )
            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                Text(
                    text = "कुल ख़रीदी (₹)",
                    style = text12Sp(fontFamily = notoSans, textColor = TextBrown)
                )
                Text(
                    text = "3,45,000",
                    style = text14Sp(fontFamily = notoSans, textColor = TextBrown)
                )
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_add_24),
                contentDescription = "",
                tint = TextBrown
            )
            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                Text(
                    text = "ब्याज (₹)",
                    style = text12Sp(fontFamily = notoSans, textColor = TextBrown)
                )
                Text(
                    text = "15,000",
                    style = text14Sp(fontFamily = notoSans, textColor = TextBrown)
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_right_bracket),
                contentDescription = "",
                tint = TextBrown
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_minus),
                contentDescription = "",
                tint = Color(0XFF00662C)
            )

            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                Text(
                    text = "भुगतान (₹)",
                    style = text12Sp(fontFamily = notoSans),
                    color = Color(0XFF00662C)
                )
                Text(
                    text = "40,000",
                    style = text14Sp(),
                    color = Color(0XFF00662C)
                )
            }
        }
    }
}
