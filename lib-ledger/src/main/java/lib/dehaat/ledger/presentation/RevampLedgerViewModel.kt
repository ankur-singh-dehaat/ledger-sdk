package lib.dehaat.ledger.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RevampLedgerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val partnerId by lazy { savedStateHandle.get<String>(LedgerConstants.KEY_PARTNER_ID) ?: "" }
    val dcName by lazy { savedStateHandle.get<String>(LedgerConstants.KEY_DC_NAME) ?: "" }
}
