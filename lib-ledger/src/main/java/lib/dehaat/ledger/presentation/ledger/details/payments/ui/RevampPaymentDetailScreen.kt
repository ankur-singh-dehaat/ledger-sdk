package lib.dehaat.ledger.presentation.ledger.details.payments.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import lib.dehaat.ledger.R
import lib.dehaat.ledger.initializer.themes.DBAColors
import lib.dehaat.ledger.initializer.themes.LedgerColors
import lib.dehaat.ledger.presentation.common.uicomponent.CommonContainer
import lib.dehaat.ledger.presentation.common.uicomponent.VerticalSpacer
import lib.dehaat.ledger.presentation.ledger.details.payments.PaymentDetailViewModel
import lib.dehaat.ledger.resources.Background
import lib.dehaat.ledger.resources.LedgerTheme
import lib.dehaat.ledger.resources.Neutral80
import lib.dehaat.ledger.resources.Neutral90
import lib.dehaat.ledger.resources.notoSans
import lib.dehaat.ledger.resources.textHeadingH3
import lib.dehaat.ledger.resources.textParagraphT1Highlight
import lib.dehaat.ledger.resources.textParagraphT2Highlight

@Preview(
    showBackground = true,
    name = "RevampPaymentDetailScreen Preview"
)
@Composable
private fun RevampPaymentDetailScreenPreview() = LedgerTheme {
    RevampPaymentDetailScreen(
        viewModel = hiltViewModel(),
        ledgerColors = DBAColors(),
        onBackPress = {}
    )
}

@Composable
fun RevampPaymentDetailScreen(
    viewModel: PaymentDetailViewModel,
    ledgerColors: LedgerColors,
    onBackPress: () -> Unit
) {
    CommonContainer(
        title = stringResource(id = R.string.payment_detail),
        onBackPress = onBackPress,
        backgroundColor = Background
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 20.dp)
                    .padding(top = 24.dp, bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = stringResource(id = R.string.payment_amount),
                        style = textParagraphT1Highlight(
                            textColor = Neutral90,
                            fontFamily = notoSans
                        )
                    )

                    VerticalSpacer(height = 4.dp)

                    Text(
                        text = "₹ 1,00,000",
                        style = textHeadingH3(
                            textColor = Neutral80,
                            fontFamily = notoSans
                        )
                    )
                }
                
                Image(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = stringResource(id = R.string.accessibility_icon)
                )
            }

            VerticalSpacer(height = 16.dp)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 20.dp)
                    .padding(top = 12.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.payment_date),
                        style = textParagraphT2Highlight(
                            textColor = Neutral80,
                            fontFamily = notoSans
                        )
                    )

                    Text(
                        text = "04-जून-2022",
                        style = textParagraphT2Highlight(
                            textColor = Neutral80,
                            fontFamily = notoSans
                        )
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.payment_method),
                        style = textParagraphT2Highlight(
                            textColor = Neutral80,
                            fontFamily = notoSans
                        )
                    )

                    Text(
                        text = "04-जून-2022",
                        style = textParagraphT2Highlight(
                            textColor = Neutral80,
                            fontFamily = notoSans
                        )
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.reference_id),
                        style = textParagraphT2Highlight(
                            textColor = Neutral80,
                            fontFamily = notoSans
                        )
                    )

                    Text(
                        text = "04-जून-2022",
                        style = textParagraphT2Highlight(
                            textColor = Neutral80,
                            fontFamily = notoSans
                        )
                    )
                }
            }
        }
    }
}
