package lib.dehaat.ledger.presentation.ledger.details.invoice.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import lib.dehaat.ledger.R
import lib.dehaat.ledger.datasource.DummyDataSource
import lib.dehaat.ledger.initializer.themes.LedgerColors
import lib.dehaat.ledger.presentation.common.uicomponent.CommonContainer
import lib.dehaat.ledger.presentation.common.uicomponent.VerticalSpacer
import lib.dehaat.ledger.presentation.ledger.details.invoice.RevampInvoiceDetailViewModel
import lib.dehaat.ledger.presentation.ledger.ui.component.ProductDetailsScreen
import lib.dehaat.ledger.presentation.ledger.ui.component.RevampKeyValuePair
import lib.dehaat.ledger.presentation.model.invoicedownload.InvoiceDownloadData
import lib.dehaat.ledger.resources.Background
import lib.dehaat.ledger.resources.Error100
import lib.dehaat.ledger.resources.Neutral60
import lib.dehaat.ledger.resources.Neutral80
import lib.dehaat.ledger.resources.Neutral90
import lib.dehaat.ledger.resources.Pumpkin10
import lib.dehaat.ledger.resources.Pumpkin120
import lib.dehaat.ledger.resources.notoSans
import lib.dehaat.ledger.resources.textButtonB2
import lib.dehaat.ledger.resources.textCaptionCP1
import lib.dehaat.ledger.resources.textParagraphT1Highlight
import lib.dehaat.ledger.resources.textParagraphT2Highlight

@Composable
fun RevampInvoiceDetailScreen(
    viewModel: RevampInvoiceDetailViewModel,
    ledgerId: String?,
    ledgerColors: LedgerColors,
    onDownloadInvoiceClick: (InvoiceDownloadData) -> Unit,
    onBackPress: () -> Unit
) {
    CommonContainer(
        title = stringResource(id = R.string.invoice_details),
        onBackPress = onBackPress,
        backgroundColor = Background,
        bottomBar = {
            AnimatedVisibility(visible = false) {

            }
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 20.dp)
            ) {
                VerticalSpacer(height = 24.dp)
                InvoiceInformationChip(
                    title = "2 दिन में ब्याज शुरू होगा",
                    backgroundColor = Pumpkin10,
                    textColor = Pumpkin120
                )

                VerticalSpacer(height = 20.dp)
                RevampKeyValuePair(
                    pair = Pair(stringResource(id = R.string.outstanding_amount), "₹ 34,100"),
                    style = Pair(
                        textParagraphT2Highlight(
                            textColor = Error100
                        ),
                        textButtonB2(
                            textColor = Error100
                        )
                    )
                )

                VerticalSpacer(height = 12.dp)
                RevampKeyValuePair(
                    pair = Pair(stringResource(id = R.string.invoice_amount), "₹ 34,100"),
                    style = Pair(
                        textParagraphT2Highlight(
                            textColor = Neutral90
                        ),
                        textButtonB2(
                            textColor = Neutral90
                        )
                    )
                )

                VerticalSpacer(height = 12.dp)
                RevampKeyValuePair(
                    pair = Pair(stringResource(id = R.string.invoice_id), "₹ 34,100"),
                    style = Pair(
                        textParagraphT2Highlight(
                            textColor = Neutral80
                        ),
                        textParagraphT2Highlight(
                            textColor = Neutral90
                        )
                    )
                )

                VerticalSpacer(height = 12.dp)
                RevampKeyValuePair(
                    pair = Pair(stringResource(id = R.string.invoice_date), "₹ 34,100"),
                    style = Pair(
                        textParagraphT2Highlight(
                            textColor = Neutral80
                        ),
                        textButtonB2(
                            textColor = Neutral90
                        )
                    )
                )

                VerticalSpacer(height = 12.dp)
                RevampKeyValuePair(
                    pair = Pair(stringResource(id = R.string.interest_start_date), "₹ 34,100"),
                    style = Pair(
                        textParagraphT2Highlight(
                            textColor = Neutral80
                        ),
                        textButtonB2(
                            textColor = Neutral90
                        )
                    )
                )

                VerticalSpacer(height = 16.dp)
            }

            VerticalSpacer(height = 16.dp)

            CreditNoteDetails(DummyDataSource.creditNotes)

            VerticalSpacer(height = 16.dp)

            ProductDetailsScreen()
        }
    }
}

@Composable
private fun CreditNoteDetails(
    creditNotes: List<CreditNote>
) = Column(
    modifier = Modifier.background(Color.White)
) {
    VerticalSpacer(height = 20.dp)

    Text(
        modifier = Modifier.padding(horizontal = 20.dp),
        text = stringResource(id = R.string.credit_note_received)
    )
    VerticalSpacer(height = 12.dp)

    Divider()

    creditNotes.forEach {
        CreditNoteCard(it)
    }
}

@Composable
fun CreditNoteCard(
    creditNote: CreditNote
) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp)
) {
    Spacer(modifier = Modifier.height(12.dp))
    Row(modifier = Modifier.fillMaxWidth()) {
        Image(
            modifier = Modifier
                .height(32.dp)
                .width(32.dp),
            painter = painterResource(id = R.drawable.ic_transactions_credit_note),
            contentDescription = stringResource(id = R.string.accessibility_icon)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = creditNote.title,
                    style = textParagraphT1Highlight(
                        textColor = Neutral80,
                        fontFamily = notoSans
                    )
                )
                Text(
                    text = creditNote.amount,
                    style = textParagraphT1Highlight(
                        textColor = Neutral80,
                        fontFamily = notoSans
                    )
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = creditNote.date,
                style = textCaptionCP1(
                    textColor = Neutral60
                )
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
    Divider()
}

data class CreditNote(
    val title: String,
    val date: String,
    val amount: String
)
