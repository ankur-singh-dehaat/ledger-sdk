package lib.dehaat.ledger.presentation.ledger.details.totaloutstanding

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import lib.dehaat.ledger.presentation.LedgerConstants
import lib.dehaat.ledger.presentation.common.BaseViewModel
import lib.dehaat.ledger.presentation.ledger.revamp.state.credits.outstandingcreditlimit.OutstandingCreditLimitViewState

@HiltViewModel
class TotalOutstandingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    val viewState by lazy { savedStateHandle.get<OutstandingCreditLimitViewState>(LedgerConstants.KEY_OUTSTANDING_CREDIT) }
}
