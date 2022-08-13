package lib.dehaat.ledger.presentation.ledger.details.availablecreditlimit

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import lib.dehaat.ledger.presentation.LedgerConstants
import lib.dehaat.ledger.presentation.common.BaseViewModel
import lib.dehaat.ledger.presentation.ledger.revamp.state.credits.availablecreditlimit.AvailableCreditLimitViewState

@HiltViewModel
class AvailableCreditLimitViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    val viewState by lazy { savedStateHandle.get<AvailableCreditLimitViewState>(LedgerConstants.KEY_AVAILABLE_CREDIT) }
}
