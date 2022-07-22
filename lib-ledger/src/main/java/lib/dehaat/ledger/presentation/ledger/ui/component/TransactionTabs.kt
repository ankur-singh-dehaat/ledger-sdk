package lib.dehaat.ledger.presentation.ledger.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import lib.dehaat.ledger.presentation.ledger.ui.SelectedTab
import lib.dehaat.ledger.presentation.ledger.ui.UnSelectedTab

@OptIn(ExperimentalPagerApi::class)
@Preview(
    name = "TransactionTabs Preview DBA",
    showBackground = true
)
@Composable
fun TransactionTabsPreviewDBA() {
    TransactionTabs(
        pagerState = rememberPagerState(pageCount = 2),
        tabClickListener = {

        }
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TransactionTabs(
    pagerState: PagerState,
    tabClickListener: (Int) -> Unit
) = Row(
    modifier = Modifier.padding(vertical = 16.dp, horizontal = 20.dp)
) {
    if (pagerState.currentPage == 0) {
        SelectedTab(
            modifier = Modifier
                .weight(1f),
            label = "देहात के साथ लेनदेन",
            onClick = {
                tabClickListener(0)
            }
        )
        Spacer(modifier = Modifier.width(4.dp))
        UnSelectedTab(
            modifier = Modifier
                .weight(1f),
            label = "ब्लैकसोईल के साथ लेनदेन",
            onClick = {
                tabClickListener(1)
            }
        )
    } else {
        UnSelectedTab(
            modifier = Modifier
                .weight(1f),
            label = "देहात के साथ लेनदेन",
            onClick = {
                tabClickListener(0)
            }
        )
        Spacer(modifier = Modifier.width(4.dp))
        SelectedTab(
            modifier = Modifier
                .weight(1f),
            label = "ब्लैकसोईल के साथ लेनदेन",
            onClick = {
                tabClickListener(1)
            }
        )
    }
}