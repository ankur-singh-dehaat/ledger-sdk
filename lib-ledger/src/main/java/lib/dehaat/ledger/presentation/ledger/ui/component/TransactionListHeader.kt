package lib.dehaat.ledger.presentation.ledger.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import lib.dehaat.ledger.R
import lib.dehaat.ledger.resources.Neutral80
import lib.dehaat.ledger.resources.notoSans
import lib.dehaat.ledger.resources.textSubHeadingS3

@Composable
fun TransactionListHeader(
    onFilterClick: () -> Unit
) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
) {
    Divider()
    Text(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp, bottom = 12.dp),
        text = stringResource(id = R.string.all_transactions_list),
        style = textSubHeadingS3(
            textColor = Neutral80,
            fontFamily = notoSans
        )
    )

    Divider()

    Spacer(modifier = Modifier.height(8.dp))

    FilterHeader(onFilterClick)
}
