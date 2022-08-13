package lib.dehaat.ledger.presentation.ledger.revamp.state.credits

import lib.dehaat.ledger.presentation.ledger.revamp.state.UIState
import lib.dehaat.ledger.presentation.model.revamp.SummaryViewData

data class LedgerViewModelState(
    val summaryViewData: SummaryViewData? = null,
    val showFilterSheet: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false
) {
    fun toUIState() = LedgerUIState(
        summaryViewData = summaryViewData,
        showFilterSheet = showFilterSheet,
        state = when {
            isSuccess -> UIState.SUCCESS
            isError -> UIState.ERROR(errorMessage)
            isLoading -> UIState.LOADING
            else -> UIState.SUCCESS
        }
    )
}

data class LedgerUIState(
    val summaryViewData: SummaryViewData? = null,
    val showFilterSheet: Boolean = false,
    val state: UIState
)
