package lib.dehaat.ledger.presentation.ledger.details.otherpaymentmode.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import lib.dehaat.ledger.presentation.common.uicomponent.CommonContainer
import lib.dehaat.ledger.presentation.ledger.details.otherpaymentmode.OtherPaymentModesViewModel
import lib.dehaat.ledger.resources.Background
import lib.dehaat.ledger.resources.LedgerTheme
import lib.dehaat.ledger.resources.textParagraphT2

@Preview(
    showBackground = true,
    name = "OtherPaymentModeScreen Preview"
)
@Composable
private fun OtherPaymentModeScreenPreview() = LedgerTheme {
    OtherPaymentModeScreen(
        viewModel = hiltViewModel(),
        ledgerColors = DBAColors(),
        onBackPress = {}
    )
}

@Composable
fun OtherPaymentModeScreen(
    viewModel: OtherPaymentModesViewModel,
    ledgerColors: LedgerColors,
    onBackPress: () -> Unit
) {
    CommonContainer(
        title = stringResource(id = R.string.other_payment_modes),
        onBackPress = onBackPress,
        backgroundColor = Background
    ) {
        val state = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .verticalScroll(state = state)
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.other_payment_modes_header),
                style = textParagraphT2()
            )
        }
    }
}
