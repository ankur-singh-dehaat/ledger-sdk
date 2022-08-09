package lib.dehaat.ledger.presentation.ledger.details.interest

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import lib.dehaat.ledger.presentation.common.BaseViewModel

@HiltViewModel
class InterestDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel()
