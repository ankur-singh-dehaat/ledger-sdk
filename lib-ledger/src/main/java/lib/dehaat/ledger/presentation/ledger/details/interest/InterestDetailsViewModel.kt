package lib.dehaat.ledger.presentation.ledger.details.interest

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import lib.dehaat.ledger.presentation.common.BaseViewModel

@HiltViewModel
class InterestDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    val interestAmount by lazy { savedStateHandle.get<String>(KEY_INTEREST_AMOUNT) }
    val interestStartDate by lazy {
        savedStateHandle.get<String>(KEY_INTEREST_START_DATE)?.toLongOrNull()
    }
    val interestEndDate by lazy {
        savedStateHandle.get<String>(KEY_INTEREST_END_DATE)?.toLongOrNull()
    }

    companion object {
        private const val KEY_INTEREST_AMOUNT = "KEY_INTEREST_AMOUNT"
        private const val KEY_INTEREST_START_DATE = "KEY_INTEREST_START_DATE"
        private const val KEY_INTEREST_END_DATE = "KEY_INTEREST_END_DATE"
        fun getBundle(
            amount: String,
            interestStartDate: Long?,
            interestEndDate: Long?
        ) = Bundle().apply {
            putString(KEY_INTEREST_AMOUNT, amount)
            putString(KEY_INTEREST_START_DATE, interestStartDate.toString())
            putString(KEY_INTEREST_END_DATE, interestEndDate.toString())
        }
    }
}
