package lib.dehaat.ledger.presentation.ledger.credits.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import lib.dehaat.ledger.initializer.themes.LedgerColors
import lib.dehaat.ledger.presentation.common.uicomponent.SpaceMedium
import lib.dehaat.ledger.resources.text14Sp
import lib.dehaat.ledger.resources.text18Sp
import lib.dehaat.ledger.resources.textMedium14Sp

val credit_line_limit_definition_for_lms =
    "Available Credit Limit = Total Credit Limit - Outstanding\n(Note: We have excluded orders in calculation for which remains to be invoiced"
val credit_line_limit_definition_for_non_lms =
    "Available Credit Limit = Total Credit Limit - Outstanding - (Orders which remains to be invoiced"

@Composable
fun AvailableCreditLimitInfoForLmsAndNonLmsUseModal(
    title: String,
    lmsActivated: Boolean,
    ledgerColors: LedgerColors,
    onOkClick: () -> Unit,
    onDismissRequest: () -> Unit = {},
) {

    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    modifier = Modifier,
                    text = "Information",
                    style = text18Sp(fontWeight = FontWeight.Bold),
                    maxLines = 1
                )

                SpaceMedium()

                Text(
                    modifier = Modifier,
                    text = title ,
                    style = text14Sp(),
                )

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = {
                        onOkClick()
                    }) {
                        Text(
                            modifier = Modifier,
                            text = "OK",
                            style = textMedium14Sp(textColor = ledgerColors.DownloadInvoiceColor),
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}
