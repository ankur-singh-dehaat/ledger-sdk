package lib.dehaat.ledger.presentation.ledger.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lib.dehaat.ledger.R
import lib.dehaat.ledger.presentation.common.uicomponent.CommonContainer
import lib.dehaat.ledger.resources.notoSans
import lib.dehaat.ledger.resources.textHeadingH3
import lib.dehaat.ledger.resources.textParagraphP1
import lib.dehaat.ledger.resources.textParagraphP2
import lib.dehaat.ledger.resources.textParagraphT1
import lib.dehaat.ledger.resources.textParagraphT1Highlight
import lib.dehaat.ledger.resources.textParagraphT2Highlight

@Preview(
    name = "TotalOutstandingDetails Preview DBA",
    showBackground = true
)
@Composable
fun TotalOutstandingDetailsPreviewDBA() {
    TotalOutstandingDetails("Title") {}
}

@Composable
fun TotalOutstandingDetails(
    title: String,
    onBackPress: () -> Unit
) = CommonContainer(
    title = title,
    onBackPress = onBackPress,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "कुल बकाया",
            style = textParagraphP1(fontFamily = notoSans)
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "₹ 3,20,000",
            style = textHeadingH3(fontFamily = notoSans)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Divider()

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0XFFE5FAFF))
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    Text(
                        text = "ब्लैकसोईल का बकाया",
                        style = textParagraphT1(fontFamily = notoSans)
                    )
                    Text(
                        text = "₹ 2,40,000",
                        style = textParagraphT1Highlight(fontFamily = notoSans)
                    )
                }
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_add_24),
                contentDescription = "",
                tint = Color(0XFFB3B3B3)
            )

            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "बकाया जो फ़ाइनैन्स होना बाक़ी है",
                    style = textParagraphP2(fontFamily = notoSans)
                )
                Text(
                    text = "₹ 80,000",
                    style = textParagraphT1Highlight(fontFamily = notoSans)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            modifier = Modifier.padding(horizontal = 4.dp),
            text = "ब्लैकसोईल बकाया कैल्क्युलेशन विधि:",
            style = textParagraphT1Highlight(fontFamily = notoSans)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0XFFE5FAFF))
                .padding(8.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "अबतक की लोन राशि + ब्याज",
                        style = textParagraphT2Highlight(fontFamily = notoSans)
                    )
                    Text(
                        text = "₹ 2,60,000",
                        style = textParagraphT2Highlight(fontFamily = notoSans)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "ब्लैक सोईल को अबतक भुगतान",
                        style = textParagraphT2Highlight(fontFamily = notoSans)
                    )
                    Text(
                        text = "₹ 20,000",
                        style = textParagraphT2Highlight(fontFamily = notoSans)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Divider()

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "ब्लैकसोईल का बकाया",
                        style = textParagraphT1(fontFamily = notoSans)
                    )
                    Text(
                        text = "₹ 2,40,000",
                        style = textParagraphT1Highlight(fontFamily = notoSans)
                    )
                }
            }
        }
    }
}
