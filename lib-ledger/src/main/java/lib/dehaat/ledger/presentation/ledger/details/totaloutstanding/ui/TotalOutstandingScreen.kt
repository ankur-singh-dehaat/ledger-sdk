package lib.dehaat.ledger.presentation.ledger.details.totaloutstanding.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
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
import lib.dehaat.ledger.presentation.common.uicomponent.CommonContainer
import lib.dehaat.ledger.presentation.common.uicomponent.VerticalSpacer
import lib.dehaat.ledger.presentation.ledger.components.NoDataFound
import lib.dehaat.ledger.presentation.ledger.details.totaloutstanding.TotalOutstandingViewModel
import lib.dehaat.ledger.presentation.ledger.ui.component.CalculationMethodScreen
import lib.dehaat.ledger.presentation.ledger.ui.component.RevampKeyValueChip
import lib.dehaat.ledger.resources.Background
import lib.dehaat.ledger.resources.LedgerTheme
import lib.dehaat.ledger.resources.Mustard10
import lib.dehaat.ledger.resources.Neutral80
import lib.dehaat.ledger.resources.Neutral90
import lib.dehaat.ledger.resources.Pumpkin10
import lib.dehaat.ledger.resources.SeaGreen10
import lib.dehaat.ledger.resources.SeaGreen20
import lib.dehaat.ledger.resources.textHeadingH3
import lib.dehaat.ledger.resources.textParagraphT1Highlight
import lib.dehaat.ledger.util.getAmountInRupees

@Preview(
    showBackground = true,
    name = "TotalOutstandingScreen Preview"
)
@Composable
private fun TotalOutstandingPreview() = LedgerTheme {
    TotalOutstandingScreen(
        viewModel = hiltViewModel(),
        ledgerColors = DBAColors(),
        onBackPress = {}
    )
}

@Composable
fun TotalOutstandingScreen(
    viewModel: TotalOutstandingViewModel,
    ledgerColors: LedgerColors,
    onBackPress: () -> Unit
) {
    val uiState = viewModel.viewState
    val scaffoldState = rememberScaffoldState()
    CommonContainer(
        title = stringResource(id = R.string.total_outstanding_details),
        onBackPress = onBackPress,
        scaffoldState = scaffoldState,
        backgroundColor = Background
    ) {
        uiState?.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(
                        horizontal = 20.dp,
                        vertical = 24.dp
                    )
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = stringResource(id = R.string.total_outstanding),
                    style = textParagraphT1Highlight(Neutral90)
                )

                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = it.totalOutstandingAmount.getAmountInRupees(),
                    style = textHeadingH3(Neutral80)
                )

                VerticalSpacer(height = 16.dp)

                Row(
                    modifier = Modifier
                        .height(IntrinsicSize.Min)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    RevampKeyValueChip(
                        modifier = Modifier
                            .weight(1F)
                            .fillMaxHeight(),
                        key = stringResource(id = R.string.total_purchase),
                        value = it.totalPurchaseAmount.getAmountInRupees(),
                        backgroundColor = SeaGreen10
                    )

                    RevampKeyValueChip(
                        modifier = Modifier
                            .weight(1F)
                            .fillMaxHeight(),
                        key = stringResource(id = R.string.interest_till_date),
                        value = it.interestTillDate.getAmountInRupees(),
                        backgroundColor = Pumpkin10
                    )

                    RevampKeyValueChip(
                        modifier = Modifier
                            .weight(1F)
                            .fillMaxHeight(),
                        key = stringResource(id = R.string.total_payment),
                        value = it.paymentAmountTillDate.getAmountInRupees(),
                        backgroundColor = Mustard10
                    )
                }

                VerticalSpacer(height = 16.dp)

                Divider()

                VerticalSpacer(height = 24.dp)

                CalculationMethodScreen(
                    backgroundColor = SeaGreen10,
                    dividerColor = SeaGreen20,
                    title = stringResource(id = R.string.total_outstanding_calculation_method),
                    first = Pair(
                        stringResource(id = R.string.purchases_till_date),
                        it.purchaseAmountTillDate.getAmountInRupees()
                    ),
                    second = Pair(
                        stringResource(id = R.string.total_credit_note_amount),
                        it.creditNoteAmountTillDate.getAmountInRupees()
                    ),
                    total = Pair(
                        stringResource(id = R.string.total_purchase),
                        it.totalPurchaseAmount.getAmountInRupees()
                    )
                )
            }
        } ?: NoDataFound()
    }
}
