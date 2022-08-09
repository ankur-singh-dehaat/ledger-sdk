package lib.dehaat.ledger.presentation.ledger.details.availablecreditlimit

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import lib.dehaat.ledger.presentation.common.BaseViewModel

@HiltViewModel
class AvailableCreditLimitViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel()
