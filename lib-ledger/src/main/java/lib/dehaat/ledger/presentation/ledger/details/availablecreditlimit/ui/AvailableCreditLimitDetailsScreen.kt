package lib.dehaat.ledger.presentation.ledger.details.availablecreditlimit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import lib.dehaat.ledger.presentation.common.uicomponent.HorizontalSpacer
import lib.dehaat.ledger.presentation.common.uicomponent.VerticalSpacer
import lib.dehaat.ledger.presentation.ledger.details.availablecreditlimit.AvailableCreditLimitViewModel
import lib.dehaat.ledger.presentation.ledger.ui.component.CalculationMethodScreen
import lib.dehaat.ledger.presentation.ledger.ui.component.RevampKeyValuePair
import lib.dehaat.ledger.resources.Background
import lib.dehaat.ledger.resources.BlueGreen10
import lib.dehaat.ledger.resources.LedgerTheme
import lib.dehaat.ledger.resources.Mustard10
import lib.dehaat.ledger.resources.Neutral10
import lib.dehaat.ledger.resources.Neutral60
import lib.dehaat.ledger.resources.Neutral70
import lib.dehaat.ledger.resources.Neutral80
import lib.dehaat.ledger.resources.SeaGreen100
import lib.dehaat.ledger.resources.notoSans
import lib.dehaat.ledger.resources.textButtonB2
import lib.dehaat.ledger.resources.textCaptionCP1
import lib.dehaat.ledger.resources.textHeadingH3
import lib.dehaat.ledger.resources.textParagraphT1
import lib.dehaat.ledger.resources.textParagraphT1Highlight
import lib.dehaat.ledger.resources.textParagraphT2Highlight
import lib.dehaat.ledger.resources.textSubHeadingS3

@Preview(
    showBackground = true,
    name = "TotalAvailableCreditDetailsLimitScreen Preview"
)
@Composable
private fun AvailableCreditLimitDetailsScreenPrev() = LedgerTheme {
    AvailableCreditLimitDetailsScreen(
        viewModel = hiltViewModel(),
        ledgerColors = DBAColors()
    ) {

    }
}

@Composable
fun AvailableCreditLimitDetailsScreen(
    viewModel: AvailableCreditLimitViewModel,
    ledgerColors: LedgerColors,
    onBackPress: () -> Unit
) {
    CommonContainer(
        title = stringResource(id = R.string.available_credit_limit),
        onBackPress = onBackPress,
        backgroundColor = Background
    ) {
        val state = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(state = state)
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(horizontal = 20.dp)
            ) {
                VerticalSpacer(size = 24.dp)

                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = stringResource(id = R.string.available_credit_limit),
                    style = textParagraphT1(
                        textColor = Neutral80,
                        fontFamily = notoSans
                    )
                )

                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = "₹ 1,60,000",
                    style = textHeadingH3(
                        textColor = Neutral80,
                        fontFamily = notoSans
                    )
                )

                VerticalSpacer(size = 12.dp)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RevampKeyValuePair(
                        modifier = Modifier
                            .weight(1.5F)
                            .fillMaxHeight(),
                        key = stringResource(id = R.string.total_credit_limit),
                        value = "₹ 5,00,000",
                        backgroundColor = BlueGreen10
                    )

                    Icon(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        painter = painterResource(id = R.drawable.ic_minus),
                        contentDescription = stringResource(id = R.string.accessibility_icon),
                        tint = Neutral80
                    )

                    RevampKeyValuePair(
                        modifier = Modifier
                            .weight(2.5F)
                            .fillMaxHeight(),
                        key = stringResource(id = R.string.total_outstanding_plus_products_to_be_deliver),
                        value = "₹ 3,40,000",
                        backgroundColor = Mustard10
                    )
                }

                VerticalSpacer(size = 16.dp)

                Divider()

                VerticalSpacer(size = 32.dp)

                VerticalSpacer(size = 8.dp)

                CalculationMethodScreen(
                    backgroundColor = BlueGreen10,
                    dividerColor = Color.White,
                    title = stringResource(id = R.string.total_credit_limit_calculation_method),
                    first = Pair(
                        stringResource(id = R.string.permanent_credit_limit),
                        "₹ 4,00,000"
                    ),
                    second = Pair(stringResource(id = R.string.buffer_limit), "+ ₹ 1,00,000"),
                    total = Pair(stringResource(id = R.string.total_credit_limit), "₹ 5,00,000")
                )

                VerticalSpacer(size = 16.dp)
            }

            VerticalSpacer(size = 16.dp)

            Column(
                modifier = Modifier
                    .background(Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(
                            top = 20.dp,
                            bottom = 12.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_idea_bulb),
                        contentDescription = stringResource(id = R.string.accessibility_icon),
                    )

                    HorizontalSpacer(height = 8.dp)

                    Text(
                        text = stringResource(id = R.string.how_does_payment_increase_credit_limit),
                        style = textSubHeadingS3(
                            textColor = Neutral80,
                            fontFamily = notoSans
                        )
                    )
                }

                Divider()

                Column(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
                ) {
                    InformationalScreen(true)
                    VerticalSpacer(size = 12.dp)
                    InformationalScreen(false)
                }
            }
        }
    }
}

@Composable
private fun InformationalScreen(
    isFullPaymentInfo: Boolean
) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .background(color = Neutral10, shape = RoundedCornerShape(8.dp))
) {
    var isDetailsVisible by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                isDetailsVisible = !isDetailsVisible
            }
            .padding(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(
                id = if (isFullPaymentInfo) {
                    R.string.when_does_total_payment_amount_adds_up
                } else {
                    R.string.when_does_payment_amount_not_adds_up
                }
            ),
            style = textParagraphT1Highlight(
                textColor = Neutral80,
                fontFamily = notoSans
            )
        )

        val modifier = if (isDetailsVisible) {
            Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(Color.White)
        } else {
            Modifier
                .size(24.dp)
                .clip(CircleShape)
        }

        Box(modifier = modifier) {
            Icon(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource(
                    id = if (isDetailsVisible) {
                        R.drawable.ic_up
                    } else {
                        R.drawable.ic_down
                    }
                ),
                contentDescription = stringResource(id = R.string.accessibility_icon),
                tint = SeaGreen100
            )
        }
    }

    if (isDetailsVisible) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            Text(
                text = stringResource(
                    id = if (isFullPaymentInfo) {
                        R.string.when_you_dont_owe_interest
                    } else {
                        R.string.when_you_owe_interest
                    }
                ),
                style = textButtonB2(
                    textColor = Neutral70,
                    fontFamily = notoSans
                )
            )
            if (!isFullPaymentInfo) {
                Text(
                    text = stringResource(id = R.string.payment_amount_used_to_pay_outstanding_interest),
                    style = textCaptionCP1(
                        textColor = Neutral80,
                        fontFamily = notoSans
                    )
                )
            }

            VerticalSpacer(size = 8.dp)

            Text(
                text = stringResource(id = R.string.example),
                style = textParagraphT2Highlight(
                    textColor = Neutral80,
                    fontFamily = notoSans
                )
            )

            VerticalSpacer(size = 4.dp)

            Text(
                text = stringResource(
                    id = if (isFullPaymentInfo) {
                        R.string.full_payment_example
                    } else {
                        R.string.partial_payment_example
                    }
                ),
                style = textParagraphT2Highlight(
                    textColor = Neutral60,
                    fontFamily = notoSans
                )
            )
        }
    }

    VerticalSpacer(size = 8.dp)
}
