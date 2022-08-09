package lib.dehaat.ledger.presentation.ledger.details.interest.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import lib.dehaat.ledger.R
import lib.dehaat.ledger.initializer.themes.DBAColors
import lib.dehaat.ledger.initializer.themes.LedgerColors
import lib.dehaat.ledger.presentation.common.uicomponent.CommonContainer
import lib.dehaat.ledger.presentation.common.uicomponent.VerticalSpacer
import lib.dehaat.ledger.presentation.ledger.details.interest.InterestDetailsViewModel
import lib.dehaat.ledger.resources.Background
import lib.dehaat.ledger.resources.LedgerTheme
import lib.dehaat.ledger.resources.Neutral80
import lib.dehaat.ledger.resources.Neutral90
import lib.dehaat.ledger.resources.Pumpkin10
import lib.dehaat.ledger.resources.notoSans
import lib.dehaat.ledger.resources.textButtonB2
import lib.dehaat.ledger.resources.textHeadingH3
import lib.dehaat.ledger.resources.textParagraphT1Highlight
import lib.dehaat.ledger.resources.textParagraphT2Highlight
import lib.dehaat.ledger.resources.textSubHeadingS3

@Preview(
    showBackground = true,
    name = "InterestDetailScreen Preview"
)
@Composable
private fun InterestDetailScreenPreview() = LedgerTheme {
    InterestDetailScreen(
        viewModel = hiltViewModel(),
        ledgerColors = DBAColors(),
        onBackPress = {}
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InterestDetailScreen(
    viewModel: InterestDetailsViewModel,
    ledgerColors: LedgerColors,
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
                    text = "11-जुलाई-2022 से 17-जुलाई-2022",
                    style = textParagraphT2Highlight(
                        textColor = Neutral80,
                        fontFamily = notoSans
                    )
                )

                VerticalSpacer(size = 20.dp)

                Text(
                    text = stringResource(id = R.string.interest_amount),
                    style = textParagraphT1Highlight(
                        textColor = Neutral90,
                        fontFamily = notoSans
                    )
                )

                VerticalSpacer(size = 4.dp)

                Text(
                    text = "₹ 6,950",
                    style = textHeadingH3(
                        textColor = Neutral80,
                        fontFamily = notoSans
                    )
                )
            }

            VerticalSpacer(size = 16.dp)

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
                            VerticalSpacer(size = 20.dp)

                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 20.dp),
                                text = stringResource(id = R.string.daily_interest_details),
                                style = textSubHeadingS3(
                                    textColor = Neutral80,
                                    fontFamily = notoSans
                                )
                            )

                            VerticalSpacer(size = 12.dp)

                            Divider()

                            VerticalSpacer(size = 16.dp)
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
                                style = textParagraphT2Highlight(
                                    textColor = Neutral80,
                                    fontFamily = notoSans
                                )
                            )
                            Text(
                                text = "+ ₹ 480",
                                style = textButtonB2(
                                    textColor = Neutral80,
                                    fontFamily = notoSans
                                )
                            )
                        }

                        Divider(modifier = Modifier.padding(horizontal = 20.dp))
                    }
                }
            }
        }
    }
}
