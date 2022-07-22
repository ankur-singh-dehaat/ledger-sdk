package lib.dehaat.ledger.presentation.ledger.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lib.dehaat.ledger.R
import lib.dehaat.ledger.initializer.themes.LedgerColors
import lib.dehaat.ledger.resources.notoSans
import lib.dehaat.ledger.resources.text14Sp

@Composable
fun FilterStrip(
    modifier: Modifier = Modifier,
    ledgerColors: LedgerColors,
    withPenalty: Boolean,
    onWithPenaltyChange: (Boolean) -> Unit,
    onDaysToFilterIconClick: () -> Unit,
    onDateRangeFilterIconClick: () -> Unit,
) {

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Invoice with Penalty")
        Switch(
            modifier = Modifier.padding(start = 12.dp),
            checked = withPenalty,
            onCheckedChange = {
                onWithPenaltyChange(it)
            }
        )

        Row(
            modifier = Modifier
                .weight(1f),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.clickable {
                    onDaysToFilterIconClick()
                },
                painter = painterResource(id = R.drawable.ic_days_filter),
                contentDescription = "Days Filter"
            )
            Image(
                modifier = Modifier.padding(start = 24.dp).clickable {
                    onDateRangeFilterIconClick()
                },
                painter = painterResource(id = R.drawable.ic_calender_filter),
                contentDescription = "Days Filter"
            )
        }
    }

}


@Preview(
    name = "FilterStrip Preview DBA",
    showBackground = true
)
@Composable
fun FilterStripPreviewDBA() {
    FilterStrip {

    }
}

@Composable
fun FilterStrip(
    onFilterClick: () -> Unit
) = Row(
    modifier = Modifier
        .padding(vertical = 4.dp, horizontal = 20.dp)
        .clickable(onClick = onFilterClick),
    verticalAlignment = Alignment.CenterVertically
) {
    Icon(
        modifier = Modifier
            .height(12.dp)
            .width(12.dp),
        painter = painterResource(id = R.drawable.ic_days_filter),
        contentDescription = "",
        tint = Color(0XFF0F8040)
    )
    Spacer(modifier = Modifier.width(8.dp))
    Text(
        text = "फ़िल्टर :",
        style = text14Sp(fontFamily = notoSans)
    )
    Text(
        text = "अब तक के लेन देन",
        style = text14Sp(fontFamily = notoSans, fontWeight = FontWeight.Bold)
    )
    Spacer(modifier = Modifier.width(2.dp))
    Icon(
        painter = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_down_24),
        contentDescription = ""
    )
}