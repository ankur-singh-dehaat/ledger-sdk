package lib.dehaat.ledger.presentation.ledger.details.availablecreditlimit

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import lib.dehaat.ledger.presentation.common.BaseViewModel
import lib.dehaat.ledger.presentation.ledger.details.totaloutstanding.getNonNullString
import lib.dehaat.ledger.presentation.ledger.revamp.state.credits.availablecreditlimit.AvailableCreditLimitViewState

@HiltViewModel
class AvailableCreditLimitViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    val viewState: AvailableCreditLimitViewState
        get() = AvailableCreditLimitViewState(
            totalAvailableCreditLimit = savedStateHandle.getNonNullString(
                TOTAL_AVAILABLE_CREDIT_LIMIT
            ),
            totalCreditLimit = savedStateHandle.getNonNullString(TOTAL_CREDIT_LIMIT),
            outstandingAndDeliveredAmount = savedStateHandle.getNonNullString(
                OUTSTANDING_AND_DELIVERED_AMOUNT
            ),
            permanentCreditLimit = savedStateHandle.getNonNullString(PERMANENT_CREDIT_LIMIT),
            bufferLimit = savedStateHandle.getNonNullString(BUFFER_LIMIT),
            totalLimit = savedStateHandle.getNonNullString(TOTAL_LIMIT)
        )

    companion object {
        private const val TOTAL_AVAILABLE_CREDIT_LIMIT = "TOTAL_AVAILABLE_CREDIT_LIMIT"
        private const val TOTAL_CREDIT_LIMIT = "TOTAL_CREDIT_LIMIT"
        private const val OUTSTANDING_AND_DELIVERED_AMOUNT = "OUTSTANDING_AND_DELIVERED_AMOUNT"
        private const val PERMANENT_CREDIT_LIMIT = "PERMANENT_CREDIT_LIMIT"
        private const val BUFFER_LIMIT = "BUFFER_LIMIT"
        private const val TOTAL_LIMIT = "TOTAL_LIMIT"
        fun getArgs(
            viewState: AvailableCreditLimitViewState?
        ) = Bundle().apply {
            viewState?.let {
                putString(TOTAL_AVAILABLE_CREDIT_LIMIT, it.totalAvailableCreditLimit)
                putString(TOTAL_CREDIT_LIMIT, it.totalCreditLimit)
                putString(OUTSTANDING_AND_DELIVERED_AMOUNT, it.outstandingAndDeliveredAmount)
                putString(PERMANENT_CREDIT_LIMIT, it.permanentCreditLimit)
                putString(BUFFER_LIMIT, it.bufferLimit)
                putString(TOTAL_LIMIT, it.totalLimit)
            }
        }
    }
}
