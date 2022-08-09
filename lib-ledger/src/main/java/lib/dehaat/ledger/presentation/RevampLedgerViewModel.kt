package lib.dehaat.ledger.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import lib.dehaat.ledger.presentation.common.BaseViewModel
import lib.dehaat.ledger.presentation.ledger.state.BottomSheetType
import lib.dehaat.ledger.presentation.ledger.state.LedgerDetailViewModelState

@HiltViewModel
class RevampLedgerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    val partnerId by lazy { savedStateHandle.get<String>(LedgerConstants.KEY_PARTNER_ID) ?: "" }
    val dcName by lazy { savedStateHandle.get<String>(LedgerConstants.KEY_DC_NAME) ?: "" }

    private val viewModelState = MutableStateFlow(LedgerDetailViewModelState())

    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    fun showFilterBottomSheet() {
        viewModelState.update {
            it.copy(
                bottomSheetType = BottomSheetType.DaysFilterTypeSheet(
                    selectedFilter = it.selectedDaysFilter
                )
            )
        }
    }
}
