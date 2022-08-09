package lib.dehaat.ledger.presentation.ledger.details.totaloutstanding

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import lib.dehaat.ledger.presentation.common.BaseViewModel

@HiltViewModel
class TotalOutstandingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel()
