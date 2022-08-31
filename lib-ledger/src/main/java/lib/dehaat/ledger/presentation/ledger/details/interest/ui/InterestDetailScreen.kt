package lib.dehaat.ledger.presentation.ledger.details.interest.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import lib.dehaat.ledger.R
import lib.dehaat.ledger.initializer.themes.DBAColors
import lib.dehaat.ledger.initializer.themes.LedgerColors
import lib.dehaat.ledger.initializer.toDateMonthYear
import lib.dehaat.ledger.presentation.common.uicomponent.CommonContainer
import lib.dehaat.ledger.presentation.common.uicomponent.VerticalSpacer
import lib.dehaat.ledger.presentation.ledger.details.interest.InterestDetailsViewModel
import lib.dehaat.ledger.resources.Background
import lib.dehaat.ledger.resources.LedgerTheme
import lib.dehaat.ledger.resources.Neutral80
import lib.dehaat.ledger.resources.Neutral90
import lib.dehaat.ledger.resources.Pumpkin10
import lib.dehaat.ledger.resources.textHeadingH3
import lib.dehaat.ledger.resources.textParagraphT1Highlight
import lib.dehaat.ledger.resources.textParagraphT2Highlight
import lib.dehaat.ledger.util.getAmountInRupees

@Preview(
    showBackground = true,
    name = "InterestDetailScreen Preview"
)
@Composable
private fun InterestDetailScreenPreview() = LedgerTheme {
    InterestDetailScreen(
        viewModel = hiltViewModel(),
        ledgerColors = DBAColors(),
        onBackPress = {},
        onError = {}
    )
}

@Composable
fun InterestDetailScreen(
    viewModel: InterestDetailsViewModel,
    ledgerColors: LedgerColors,
    onError: (Exception) -> Unit,
    onBackPress: () -> Unit
) {
    CommonContainer(
        title = stringResource(id = R.string.interest_amount),
        onBackPress = onBackPress,
        backgroundColor = Background
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 20.dp)
                    .padding(top = 24.dp, bottom = 16.dp)
            ) {
                Text(
                    modifier = Modifier
                        .background(color = Pumpkin10, shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    text = stringResource(
                        id = R.string.from_to,
                        viewModel.interestStartDate.toDateMonthYear(),
                        viewModel.interestEndDate.toDateMonthYear()
                    ),
                    style = textParagraphT2Highlight(Neutral80)
                )

                VerticalSpacer(height = 20.dp)

                Text(
                    text = stringResource(id = R.string.interest_amount),
                    style = textParagraphT1Highlight(Neutral90)
                )

                VerticalSpacer(height = 4.dp)

                Text(
                    text = viewModel.interestAmount.getAmountInRupees(),
                    style = textHeadingH3(Neutral80)
                )
            }

            /*VerticalSpacer(height = 16.dp)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    stickyHeader {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                        ) {
                            VerticalSpacer(height = 20.dp)

                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 20.dp),
                                text = stringResource(id = R.string.daily_interest_details),
                                style = textSubHeadingS3(Neutral80)
                            )

                            VerticalSpacer(height = 12.dp)

                            Divider()

                            VerticalSpacer(height = 16.dp)
                        }
                    }

                    items(listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)) { num ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                                .padding(top = 15.dp, bottom = 19.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "16-जुलाई-2022",
                                style = textParagraphT2Highlight(Neutral80)
                            )
                            Text(
                                text = "+ ₹ 480",
                                style = textButtonB2(Neutral80)
                            )
                        }

                        Divider(modifier = Modifier.padding(horizontal = 20.dp))
                    }
                }
            }*/
        }
    }
}
