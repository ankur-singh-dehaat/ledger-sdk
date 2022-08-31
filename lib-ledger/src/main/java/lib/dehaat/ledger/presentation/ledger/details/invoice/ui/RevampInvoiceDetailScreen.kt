package lib.dehaat.ledger.presentation.ledger.details.invoice.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import lib.dehaat.ledger.R
import lib.dehaat.ledger.initializer.themes.LedgerColors
import lib.dehaat.ledger.initializer.toDateMonthYear
import lib.dehaat.ledger.presentation.common.uicomponent.CommonContainer
import lib.dehaat.ledger.presentation.common.uicomponent.HorizontalSpacer
import lib.dehaat.ledger.presentation.common.uicomponent.VerticalSpacer
import lib.dehaat.ledger.presentation.ledger.components.NoDataFound
import lib.dehaat.ledger.presentation.ledger.components.ShowProgressDialog
import lib.dehaat.ledger.presentation.ledger.details.invoice.RevampInvoiceDetailViewModel
import lib.dehaat.ledger.presentation.ledger.revamp.state.UIState
import lib.dehaat.ledger.presentation.ledger.ui.component.ProductDetailsScreen
import lib.dehaat.ledger.presentation.ledger.ui.component.RevampKeyValuePair
import lib.dehaat.ledger.presentation.model.revamp.invoice.CreditNoteViewData
import lib.dehaat.ledger.presentation.model.revamp.invoice.ProductsInfoViewDataV2
import lib.dehaat.ledger.presentation.model.revamp.invoice.SummaryViewDataV2
import lib.dehaat.ledger.resources.Background
import lib.dehaat.ledger.resources.Error10
import lib.dehaat.ledger.resources.Error100
import lib.dehaat.ledger.resources.Neutral60
import lib.dehaat.ledger.resources.Neutral80
import lib.dehaat.ledger.resources.Neutral90
import lib.dehaat.ledger.resources.Primary80
import lib.dehaat.ledger.resources.Pumpkin10
import lib.dehaat.ledger.resources.Pumpkin120
import lib.dehaat.ledger.resources.SeaGreen100
import lib.dehaat.ledger.resources.Success10
import lib.dehaat.ledger.resources.textButtonB2
import lib.dehaat.ledger.resources.textCaptionCP1
import lib.dehaat.ledger.resources.textParagraphT1Highlight
import lib.dehaat.ledger.resources.textParagraphT2Highlight
import lib.dehaat.ledger.util.getAmountInRupees

@Composable
fun RevampInvoiceDetailScreen(
    viewModel: RevampInvoiceDetailViewModel,
    ledgerColors: LedgerColors,
    onDownloadInvoiceClick: (String, String) -> Unit,
    onError: (Exception) -> Unit,
    onBackPress: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    CommonContainer(
        title = stringResource(id = R.string.invoice_details),
        onBackPress = onBackPress,
        backgroundColor = Background,
        bottomBar = {
            AnimatedVisibility(visible = false) {}
        }
    ) {
        when (uiState.state) {
            UIState.SUCCESS -> {
                InvoiceDetailScreen(
                    uiState.invoiceDetailsViewData?.summary,
                    uiState.invoiceDetailsViewData?.creditNotes,
                    uiState.invoiceDetailsViewData?.productsInfo
                ) {
                    onDownloadInvoiceClick(it, viewModel.source)
                }
            }
            UIState.LOADING -> {
                ShowProgressDialog(ledgerColors) {
                    viewModel.updateProgressDialog(false)
                }
            }
            is UIState.ERROR -> {
                NoDataFound((uiState.state as? UIState.ERROR)?.message, onError)
            }
        }
    }
}

@Composable
private fun InvoiceDetailScreen(
    summary: SummaryViewDataV2?,
    creditNotes: List<CreditNoteViewData>?,
    productsInfo: ProductsInfoViewDataV2?,
    onDownloadInvoiceClick: (String) -> Unit
) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())
) {
    val undeliveredInvoice = summary?.interestStartDate == null
    val interestPaid = summary?.totalOutstandingAmount?.toDoubleOrNull() == 0.0
    val interestRunning = summary?.interestBeingCharged == true
    val interestStarting = summary?.interestBeingCharged == false
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 20.dp)
    ) {
        VerticalSpacer(height = 24.dp)
        when {
            interestRunning -> summary?.interestDays?.let {
                InvoiceInformationChip(
                    title = stringResource(
                        id = R.string.interest_running,
                        it.toString()
                    ),
                    backgroundColor = Error10,
                    textColor = Error100
                )
            }
            interestStarting -> summary?.interestDays?.let {
                InvoiceInformationChip(
                    title = stringResource(
                        id = R.string.interest_starting,
                        it.toString()
                    ),
                    backgroundColor = Pumpkin10,
                    textColor = Pumpkin120
                )
            }
            interestPaid -> {
                InvoiceInformationChip(
                    title = stringResource(id = R.string.full_payment_complete),
                    backgroundColor = Success10,
                    textColor = Neutral90
                )
            }
            else -> Unit
        }

        summary?.totalOutstandingAmount?.let {
            if (!summary.interestBeingCharged && summary.invoiceAmount != it && it.toDoubleOrNull() == 0.0) {
                VerticalSpacer(height = 20.dp)
                RevampKeyValuePair(
                    pair = Pair(
                        stringResource(id = R.string.outstanding_amount),
                        it.getAmountInRupees()
                    ),
                    style = Pair(
                        textParagraphT2Highlight(Error100),
                        textButtonB2(Error100)
                    )
                )
            }
        }

        VerticalSpacer(height = 12.dp)
        RevampKeyValuePair(
            pair = Pair(
                stringResource(id = R.string.invoice_amount),
                summary?.invoiceAmount.getAmountInRupees()
            ),
            style = Pair(
                textParagraphT2Highlight(Neutral90),
                textButtonB2(Neutral90)
            )
        )

        VerticalSpacer(height = 12.dp)
        RevampKeyValuePair(
            pair = Pair(
                stringResource(id = R.string.invoice_id),
                summary?.invoiceId ?: ""
            ),
            style = Pair(
                textParagraphT2Highlight(Neutral80),
                textParagraphT2Highlight(Neutral90)
            )
        )

        VerticalSpacer(height = 12.dp)
        RevampKeyValuePair(
            pair = Pair(
                stringResource(id = R.string.invoice_date),
                summary?.invoiceDate.toDateMonthYear()
            ),
            style = Pair(
                textParagraphT2Highlight(Neutral80),
                textButtonB2(Neutral90)
            )
        )

        summary?.interestStartDate?.let {
            VerticalSpacer(height = 12.dp)
            RevampKeyValuePair(
                pair = Pair(
                    stringResource(id = R.string.interest_start_date),
                    it.toDateMonthYear()
                ),
                style = Pair(
                    textParagraphT2Highlight(Neutral80),
                    textButtonB2(Neutral90)
                )
            )
        }

        VerticalSpacer(height = 16.dp)
    }

    VerticalSpacer(height = 16.dp)

    CreditNoteDetails(creditNotes)

    VerticalSpacer(height = 16.dp)

    ProductDetailsScreen(productsInfo)

    DownloadInvoiceButton {
        summary?.invoiceId?.let {
            onDownloadInvoiceClick(it)
        }
    }
}

@Composable
private fun CreditNoteDetails(
    creditNotes: List<CreditNoteViewData>?
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

    creditNotes?.forEach {
        CreditNoteCard(it)
    }
}

@Composable
private fun CreditNoteCard(
    creditNote: CreditNoteViewData
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
                    text = stringResource(id = R.string.credit_note, creditNote.creditNoteType),
                    style = textParagraphT1Highlight(Neutral80)
                )
                Text(
                    text = creditNote.creditNoteAmount.getAmountInRupees(),
                    style = textParagraphT1Highlight(Neutral80)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = creditNote.creditNoteDate.toDateMonthYear(),
                style = textCaptionCP1(Neutral60)
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
    Divider()
}

@Composable
fun DownloadInvoiceButton(
    onClick: () -> Unit
) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .background(Color.White),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(top = 16.dp)
            .background(shape = RoundedCornerShape(48.dp), color = Color.White)
            .border(
                width = 1.dp,
                color = Primary80,
                shape = RoundedCornerShape(48.dp)
            )
            .padding(vertical = 16.dp, horizontal = 40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_download),
            contentDescription = stringResource(id = R.string.accessibility_icon),
            tint = SeaGreen100
        )
        HorizontalSpacer(width = 6.dp)
        Text(
            text = stringResource(id = R.string.download_invoice),
            color = SeaGreen100
        )
    }

    VerticalSpacer(height = 16.dp)
}
