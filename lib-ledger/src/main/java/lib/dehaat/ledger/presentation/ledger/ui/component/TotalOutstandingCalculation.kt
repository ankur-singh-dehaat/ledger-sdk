package lib.dehaat.ledger.presentation.ledger.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lib.dehaat.ledger.R
import lib.dehaat.ledger.resources.LedgerTheme
import lib.dehaat.ledger.resources.Neutral90
import lib.dehaat.ledger.resources.Pumpkin120
import lib.dehaat.ledger.resources.SeaGreen110
import lib.dehaat.ledger.resources.TextLightGrey
import lib.dehaat.ledger.resources.notoSans
import lib.dehaat.ledger.resources.textCaptionCP1
import lib.dehaat.ledger.resources.textParagraphT2

@Preview(
    name = "TotalOutstandingCalculation Preview",
    showBackground = true
)
@Composable
private fun TotalOutstandingCalculationPreview() = LedgerTheme {
    TotalOutstandingCalculation(true)
}

@Preview(
    name = "TotalOutstandingCalculation without header Preview",
    showBackground = true
)
@Composable
private fun TotalOutstandingCalculationWithoutHeaderPreview() = LedgerTheme {
    TotalOutstandingCalculation(false)
}

@Composable
fun TotalOutstandingCalculation(
    showTopHeader: Boolean
) = Card(
    modifier = Modifier
        .fillMaxWidth(),
    shape = if (showTopHeader) {
        RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
    } else {
        RectangleShape
    },
    backgroundColor = Color.White,
    elevation = 8.dp
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
    ) {

        if (showTopHeader) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_idea_bulb),
                    contentDescription = stringResource(id = R.string.accessibility_icon)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = stringResource(id = R.string.total_outstanding_calculation_method),
                    style = textParagraphT2(
                        textColor = TextLightGrey,
                        fontFamily = notoSans
                    )
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = stringResource(id = R.string.total_outstanding_footer),
                style = textParagraphT2(
                    textColor = Neutral90,
                    fontFamily = notoSans
                ),
                textAlign = TextAlign.Center
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_equal),
                contentDescription = stringResource(id = R.string.accessibility_icon)
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_left_bracket),
                contentDescription = stringResource(id = R.string.accessibility_icon),
                tint = Pumpkin120
            )
            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                Text(
                    text = stringResource(id = R.string.total_purchase_rs),
                    style = textCaptionCP1(
                        textColor = Pumpkin120,
                        fontFamily = notoSans
                    )
                )
                Text(
                    text = "3,45,000",
                    style = textParagraphT2(
                        textColor = Pumpkin120,
                        fontFamily = notoSans
                    )
                )
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_add_24),
                contentDescription = stringResource(id = R.string.accessibility_icon),
                tint = Pumpkin120
            )
            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                Text(
                    text = stringResource(id = R.string.interest_rs),
                    style = textCaptionCP1(
                        textColor = Pumpkin120,
                        fontFamily = notoSans
                    )
                )
                Text(
                    text = "15,000",
                    style = textParagraphT2(
                        textColor = Pumpkin120,
                        fontFamily = notoSans
                    )
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_right_bracket),
                contentDescription = stringResource(id = R.string.accessibility_icon),
                tint = Pumpkin120
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_minus),
                contentDescription = stringResource(id = R.string.accessibility_icon),
                tint = SeaGreen110
            )

            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                Text(
                    text = stringResource(id = R.string.payment_rs),
                    style = textCaptionCP1(
                        textColor = SeaGreen110,
                        fontFamily = notoSans
                    )
                )
                Text(
                    text = "40,000",
                    style = textParagraphT2(
                        textColor = SeaGreen110,
                        fontFamily = notoSans
                    )
                )
            }
        }
    }
}
