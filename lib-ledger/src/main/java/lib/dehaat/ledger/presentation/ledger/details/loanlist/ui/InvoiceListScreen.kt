package lib.dehaat.ledger.presentation.ledger.details.loanlist.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lib.dehaat.ledger.R
import lib.dehaat.ledger.initializer.themes.LedgerColors
import lib.dehaat.ledger.initializer.toDateMonthName
import lib.dehaat.ledger.presentation.common.uicomponent.CommonContainer
import lib.dehaat.ledger.presentation.common.uicomponent.HorizontalSpacer
import lib.dehaat.ledger.presentation.common.uicomponent.VerticalSpacer
import lib.dehaat.ledger.presentation.ledger.components.NoDataFound
import lib.dehaat.ledger.presentation.ledger.components.ShowProgressDialog
import lib.dehaat.ledger.presentation.ledger.details.invoice.ui.InvoiceInformationChip
import lib.dehaat.ledger.presentation.ledger.details.loanlist.InvoiceListViewModel
import lib.dehaat.ledger.presentation.ledger.revamp.state.UIState
import lib.dehaat.ledger.presentation.model.invoicelist.InvoiceListViewData
import lib.dehaat.ledger.resources.Background
import lib.dehaat.ledger.resources.Error100
import lib.dehaat.ledger.resources.LedgerTheme
import lib.dehaat.ledger.resources.Neutral10
import lib.dehaat.ledger.resources.Neutral60
import lib.dehaat.ledger.resources.Neutral80
import lib.dehaat.ledger.resources.Neutral90
import lib.dehaat.ledger.resources.Pumpkin120
import lib.dehaat.ledger.resources.Warning10
import lib.dehaat.ledger.resources.textCaptionCP1
import lib.dehaat.ledger.resources.textParagraphT1Highlight
import lib.dehaat.ledger.resources.textSubHeadingS3
import lib.dehaat.ledger.util.getAmountInRupees

@Preview(
    showBackground = true,
    name = "Invoice List Screen Preview"
)
@Composable
private fun InvoiceListScreenPreview() = LedgerTheme {
    InvoiceList(
        interestDueDate = 623784623,
        amountDue = "40000",
        interestApproached = null,
        interestApproaching = null
    )
}

@Composable
fun InvoiceListScreen(
    viewModel: InvoiceListViewModel,
    ledgerColors: LedgerColors,
    onBackPress: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val interestApproached = uiState.interestApproachedInvoices
    val interestApproaching = uiState.interestApproachingInvoices

    CommonContainer(
        title = stringResource(id = R.string.invoice_list),
        onBackPress = onBackPress,
        scaffoldState = rememberScaffoldState(),
        backgroundColor = Background
    ) {
        when (uiState.state) {
            UIState.SUCCESS -> {
                InvoiceList(
                    viewModel.dueDate,
                    viewModel.amountDue,
                    interestApproached,
                    interestApproaching
                )
            }
            UIState.LOADING -> {
                ShowProgressDialog(ledgerColors) {
                    viewModel.updateProgressDialog(false)
                }
            }
            is UIState.ERROR -> {
                Column {
                    SaveInterestHeader(
                        interestDueDate = viewModel.dueDate,
                        amountDue = viewModel.amountDue
                    )
                    NoDataFound((uiState.state as? UIState.ERROR)?.message)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun InvoiceList(
    interestDueDate: Long?,
    amountDue: String?,
    interestApproached: List<InvoiceListViewData>?,
    interestApproaching: List<InvoiceListViewData>?
) = Column(
    modifier = Modifier
        .fillMaxWidth()
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        stickyHeader {
            SaveInterestHeader(
                interestDueDate = interestDueDate,
                amountDue = amountDue
            )
            VerticalSpacer(height = 16.dp)
        }

        interestApproached?.let {
            stickyHeader {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .padding(top = 22.dp, bottom = 14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_attention),
                            contentDescription = stringResource(id = R.string.accessibility_icon),
                            tint = Error100
                        )
                        HorizontalSpacer(width = 10.dp)
                        Text(
                            text = stringResource(id = R.string.invoice_on_which_interest_is_charged),
                            style = textSubHeadingS3(Error100)
                        )
                    }
                    Divider()
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .background(Color.White)
                ) {}
            }
            items(interestApproached) {
                InvoiceWithAccumulatedInterest {}
            }
        }

        item {
            VerticalSpacer(height = 16.dp)
        }

        interestApproaching?.let {
            stickyHeader {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .padding(top = 20.dp, bottom = 12.dp),
                        text = stringResource(id = R.string.invoices_not_attracting_interest),
                        style = textSubHeadingS3(Neutral80)
                    )

                    Divider()

                    VerticalSpacer(height = 16.dp)
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .background(Color.White)
                ) {}
            }
            items(interestApproaching) {
                InvoiceWithUpcomingInterest {}
            }
        }
    }

    VerticalSpacer(height = 16.dp)
}

@Composable
private fun SaveInterestHeader(
    interestDueDate: Long?,
    amountDue: String?,
) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .background(Warning10)
        .padding(horizontal = 20.dp)
        .padding(top = 24.dp, bottom = 16.dp)
) {
    Text(
        text = stringResource(id = R.string.save_interest),
        style = textSubHeadingS3(Pumpkin120)
    )

    VerticalSpacer(height = 4.dp)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        interestDueDate?.let {
            Text(
                text = stringResource(
                    id = R.string.pay_till_date,
                    it.toDateMonthName()
                ),
                style = textParagraphT1Highlight(Neutral90)
            )
        }

        amountDue?.let {
            Text(
                text = it.getAmountInRupees(),
                style = textSubHeadingS3(Neutral90)
            )
        }
    }
}

@Composable
private fun InvoiceWithAccumulatedInterest(
    onClick: () -> Unit
) = Column(
    modifier = Modifier
        .clickable(onClick = onClick)
        .background(Color.White)
        .fillMaxWidth()
        .padding(horizontal = 20.dp)
        .padding(top = 12.dp)
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_revamp_invoice),
            contentDescription = stringResource(id = R.string.accessibility_icon)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.invoice),
                    style = textParagraphT1Highlight(Neutral80)
                )
                Text(
                    text = "बकाया राशि: ₹ 18,550",
                    style = textParagraphT1Highlight(Error100)
                )
            }
            VerticalSpacer(height = 4.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "18-जुलाई-2022 से आज तक",
                    style = textCaptionCP1(Neutral60)
                )

                InvoiceInformationChip(
                    title = "इनवॉइस राशि: ₹ 2,25,800",
                    backgroundColor = Neutral10,
                    textColor = Neutral80
                )
            }
        }
    }
    VerticalSpacer(height = 16.dp)
    Divider()
}

@Composable
fun InvoiceWithUpcomingInterest(
    onClick: () -> Unit
) = Column(
    modifier = Modifier
        .clickable(onClick = onClick)
        .background(Color.White)
        .fillMaxWidth()
        .padding(horizontal = 20.dp)
        .padding(top = 12.dp)
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_revamp_invoice),
            contentDescription = stringResource(id = R.string.accessibility_icon)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.invoice),
                    style = textParagraphT1Highlight(Neutral80)
                )
                Text(
                    text = "+ ₹ 5,160",
                    style = textParagraphT1Highlight(Neutral80)
                )
            }
            VerticalSpacer(height = 4.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "18-जुलाई-2022 से आज तक",
                    style = textCaptionCP1(Neutral60)
                )

                InvoiceInformationChip(
                    title = "2 दिन में ब्याज शुरू होगा",
                    backgroundColor = Warning10,
                    textColor = Pumpkin120
                )
            }
        }
    }
    VerticalSpacer(height = 16.dp)
    Divider()
}
