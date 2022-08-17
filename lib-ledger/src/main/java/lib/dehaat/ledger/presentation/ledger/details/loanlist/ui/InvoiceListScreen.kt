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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import lib.dehaat.ledger.R
import lib.dehaat.ledger.initializer.themes.LedgerColors
import lib.dehaat.ledger.initializer.toDateMonthName
import lib.dehaat.ledger.presentation.common.uicomponent.CommonContainer
import lib.dehaat.ledger.presentation.common.uicomponent.HorizontalSpacer
import lib.dehaat.ledger.presentation.common.uicomponent.VerticalSpacer
import lib.dehaat.ledger.presentation.ledger.details.invoice.ui.InvoiceInformationChip
import lib.dehaat.ledger.presentation.ledger.details.loanlist.InvoiceListViewModel
import lib.dehaat.ledger.resources.Background
import lib.dehaat.ledger.resources.Error100
import lib.dehaat.ledger.resources.Neutral10
import lib.dehaat.ledger.resources.Neutral60
import lib.dehaat.ledger.resources.Neutral80
import lib.dehaat.ledger.resources.Neutral90
import lib.dehaat.ledger.resources.Pumpkin120
import lib.dehaat.ledger.resources.Warning10
import lib.dehaat.ledger.resources.notoSans
import lib.dehaat.ledger.resources.textCaptionCP1
import lib.dehaat.ledger.resources.textParagraphT1Highlight
import lib.dehaat.ledger.resources.textSubHeadingS3
import lib.dehaat.ledger.util.getAmountInRupees

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InvoiceListScreen(
    viewModel: InvoiceListViewModel,
    ledgerColors: LedgerColors,
    onBackPress: () -> Unit
) {
    CommonContainer(
        title = stringResource(id = R.string.invoice_list),
        onBackPress = onBackPress,
        backgroundColor = Background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                stickyHeader {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Warning10)
                            .padding(horizontal = 20.dp)
                            .padding(top = 24.dp, bottom = 16.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.save_interest),
                            style = textSubHeadingS3(
                                textColor = Pumpkin120,
                                fontFamily = notoSans
                            )
                        )

                        VerticalSpacer(height = 4.dp)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            viewModel.dueDate?.let {
                                Text(
                                    text = stringResource(
                                        id = R.string.pay_till_date,
                                        it.toDateMonthName()
                                    ),
                                    style = textParagraphT1Highlight(Neutral90)
                                )
                            }

                            viewModel.amountDue?.let {
                                Text(
                                    text = it.getAmountInRupees(),
                                    style = textSubHeadingS3(
                                        textColor = Neutral90,
                                        fontFamily = notoSans
                                    )
                                )
                            }
                        }
                    }

                    VerticalSpacer(height = 16.dp)
                }
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
                                style = textSubHeadingS3(
                                    textColor = Error100,
                                    fontFamily = notoSans
                                )
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
                items(listOf(1, 2, 3, 4, 5, 6, 7, 8)) {
                    InvoiceWithAccumulatedInterest {}
                }

                item {
                    VerticalSpacer(height = 16.dp)
                }

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
                            style = textSubHeadingS3(
                                textColor = Neutral80,
                                fontFamily = notoSans
                            )
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
                items(listOf(1, 2, 3, 4, 5, 6, 7, 8)) {
                    InvoiceWithUpcomingInterest {}
                }
            }

            VerticalSpacer(height = 16.dp)
        }
    }
}

@Composable
fun InvoiceWithAccumulatedInterest(
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
