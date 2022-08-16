package lib.dehaat.ledger.presentation.ledger.details.loanlist

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import javax.inject.Inject
import lib.dehaat.ledger.presentation.common.BaseViewModel

class InvoiceListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    val dueDate by lazy { savedStateHandle.get<String>(KEY_DUE_DATE)?.toLongOrNull() }
    val amountDue by lazy { savedStateHandle.get<String>(KEY_DUE_AMOUNT) }

    companion object {
        private const val KEY_DUE_DATE = "KEY_DUE_DATE"
        private const val KEY_DUE_AMOUNT = "KEY_DUE_AMOUNT"

        fun getBundle(
            outstandingDueDate: Long?,
            outstandingAmountDue: String?
        ) = Bundle().apply {
            putString(KEY_DUE_DATE, outstandingDueDate.toString())
            putString(KEY_DUE_AMOUNT, outstandingAmountDue)
        }
    }
}
