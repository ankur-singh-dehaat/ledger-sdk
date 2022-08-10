package lib.dehaat.ledger.presentation.ledger.bottomsheets

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lib.dehaat.ledger.R
import lib.dehaat.ledger.initializer.Utils
import lib.dehaat.ledger.presentation.common.uicomponent.HorizontalSpacer
import lib.dehaat.ledger.presentation.common.uicomponent.VerticalSpacer
import lib.dehaat.ledger.resources.LedgerTheme
import lib.dehaat.ledger.resources.Neutral10
import lib.dehaat.ledger.resources.Neutral50
import lib.dehaat.ledger.resources.Neutral80
import lib.dehaat.ledger.resources.SeaGreen100
import lib.dehaat.ledger.resources.TextWhite
import lib.dehaat.ledger.resources.notoSans
import lib.dehaat.ledger.resources.textButtonB1
import lib.dehaat.ledger.resources.textHeadingH5
import lib.dehaat.ledger.resources.textParagraphT1Highlight
import lib.dehaat.ledger.resources.textParagraphT2

@Preview(
    showBackground = true,
    name = "FilterScreenPreview"
)
@Composable
private fun FilterScreenPreview() = LedgerTheme {
    FilterScreen { _, _, _ -> }
}

@Composable
fun FilterScreen(
    onFilterApply: (FilterType, String, String) -> Unit
) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp)
        .padding(top = 20.dp, bottom = 16.dp)
) {
    val context = LocalContext.current
    var selectedFilter: FilterType by remember { mutableStateOf(FilterType.AllTransactions) }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.filter),
            style = textHeadingH5(
                textColor = Neutral80,
                fontFamily = notoSans
            )
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_down),
            contentDescription = stringResource(id = R.string.accessibility_icon)
        )
    }

    Spacer(modifier = Modifier.height(32.dp))

    FilterOptions(
        filterName = stringResource(id = R.string.filter_all_transactions),
        defaultSelection = selectedFilter is FilterType.AllTransactions,
        isFilterSelected = { selectedFilter = FilterType.AllTransactions }
    )

    Spacer(modifier = Modifier.height(32.dp))

    FilterOptions(
        filterName = stringResource(id = R.string.filter_last_seven_days),
        defaultSelection = selectedFilter is FilterType.LastSevenDays,
        isFilterSelected = { selectedFilter = FilterType.LastSevenDays }
    )

    Spacer(modifier = Modifier.height(32.dp))

    FilterOptions(
        filterName = stringResource(id = R.string.filter_last_one_month),
        defaultSelection = selectedFilter is FilterType.LastOneMonth,
        isFilterSelected = { selectedFilter = FilterType.LastOneMonth }
    )

    Spacer(modifier = Modifier.height(32.dp))

    FilterOptions(
        filterName = stringResource(id = R.string.filter_last_three_months),
        defaultSelection = selectedFilter is FilterType.LastThreeMonths,
        isFilterSelected = { selectedFilter = FilterType.LastThreeMonths }
    )

    Spacer(modifier = Modifier.height(16.dp))

    Divider()

    Spacer(modifier = Modifier.height(16.dp))

    CustomFilterOption(
        filterName = stringResource(id = R.string.filter_add_custom_date),
        defaultSelection = selectedFilter is FilterType.CustomFiltering,
        startDate = {
            startDate = it
        }, endDate = {
            endDate = it
        },
        isFilterSelected = { selectedFilter = FilterType.CustomFiltering }
    )

    VerticalSpacer(height = 32.dp)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onFilterApply(selectedFilter, startDate, endDate) }
    ) {
        Text(
            text = stringResource(id = R.string.apply_filter),
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = SeaGreen100,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(vertical = 8.dp),
            textAlign = TextAlign.Center,
            style = textButtonB1(
                textColor = TextWhite,
                fontFamily = notoSans
            )
        )
    }
}

@Composable
private fun CustomFilterOption(
    filterName: String,
    defaultSelection: Boolean,
    startDate: (String) -> Unit,
    endDate: (String) -> Unit,
    isFilterSelected: () -> Unit
) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = isFilterSelected)
) {
    FilterOptions(
        filterName = filterName,
        defaultSelection = defaultSelection,
        isFilterSelected = isFilterSelected
    )

    VerticalSpacer(12.dp)

    Row(modifier = Modifier.fillMaxWidth()) {

        FilterDateSelector(
            modifier = Modifier.weight(1F),
            title = stringResource(id = R.string.from),
            onDateChange = startDate,
            onDateClick = isFilterSelected
        )

        HorizontalSpacer(width = 20.dp)

        FilterDateSelector(
            modifier = Modifier.weight(1F),
            title = stringResource(id = R.string.to),
            onDateChange = endDate,
            onDateClick = isFilterSelected
        )
    }
}

@Composable
private fun FilterDateSelector(
    modifier: Modifier,
    title: String,
    onDateChange: (String) -> Unit,
    onDateClick: () -> Unit
) = Column(
    modifier = modifier
) {
    val context = LocalContext.current
    var date by remember { mutableStateOf("") }
    Text(
        text = title,
        style = textParagraphT2(
            textColor = Neutral80,
            fontFamily = notoSans
        )
    )
    VerticalSpacer(height = 4.dp)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Neutral10,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                onDateClick()
                Utils.openDatePickerDialog(context) {
                    onDateChange(it)
                    date = it
                }
            }
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_calender_filter),
            contentDescription = stringResource(id = R.string.accessibility_icon),
            tint = Neutral50
        )
        HorizontalSpacer(width = 8.dp)
        Text(
            text = date.ifEmpty { "DD/MM/YYYY" },
            style = textParagraphT2(
                textColor = Neutral50
            ),
            fontFamily = notoSans
        )
    }
}

@Composable
private fun FilterOptions(
    filterName: String,
    defaultSelection: Boolean,
    isFilterSelected: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                isFilterSelected()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            modifier = Modifier
                .size(20.dp),
            selected = defaultSelection,
            onClick = null
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = filterName,
            style = textParagraphT1Highlight(
                textColor = Neutral80,
                fontFamily = notoSans
            )
        )
    }
}

sealed class FilterType {
    object AllTransactions : FilterType()
    object LastSevenDays : FilterType()
    object LastOneMonth : FilterType()
    object LastThreeMonths : FilterType()
    object CustomFiltering : FilterType()
}
