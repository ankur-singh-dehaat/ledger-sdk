package lib.dehaat.ledger.presentation.ledger.details.totaloutstanding

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import lib.dehaat.ledger.presentation.common.BaseViewModel
import lib.dehaat.ledger.presentation.ledger.revamp.state.credits.outstandingcreditlimit.OutstandingCreditLimitViewState

@HiltViewModel
class TotalOutstandingViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    val viewState: OutstandingCreditLimitViewState
        get() = OutstandingCreditLimitViewState(
            totalOutstandingAmount = savedStateHandle.getNonNullString(TOTAL_OUTSTANDING_AMOUNT),
            totalPurchaseAmount = savedStateHandle.getNonNullString(TOTAL_PURCHASE_AMOUNT),
            interestTillDate = savedStateHandle.getNonNullString(INTEREST_TILL_DATE),
            paymentAmountTillDate = savedStateHandle.getNonNullString(PAYMENT_AMOUNT_TILL_DATE),
            purchaseAmountTillDate = savedStateHandle.getNonNullString(PURCHASE_AMOUNT_TILL_DATE),
            creditNoteAmountTillDate = savedStateHandle.getNonNullString(
                CREDIT_NOTE_AMOUNT_TILL_DATE
            ),
        )

    companion object {
        private const val TOTAL_OUTSTANDING_AMOUNT = "TOTAL_OUTSTANDING_AMOUNT"
        private const val TOTAL_PURCHASE_AMOUNT = "TOTAL_PURCHASE_AMOUNT"
        private const val INTEREST_TILL_DATE = "INTEREST_TILL_DATE"
        private const val PAYMENT_AMOUNT_TILL_DATE = "PAYMENT_AMOUNT_TILL_DATE"
        private const val PURCHASE_AMOUNT_TILL_DATE = "PURCHASE_AMOUNT_TILL_DATE"
        private const val CREDIT_NOTE_AMOUNT_TILL_DATE = "CREDIT_NOTE_AMOUNT_TILL_DATE"

        fun getBundle(viewState: OutstandingCreditLimitViewState?) = Bundle().apply {
            viewState?.let {
                putString(TOTAL_OUTSTANDING_AMOUNT, it.totalOutstandingAmount)
                putString(TOTAL_PURCHASE_AMOUNT, it.totalPurchaseAmount)
                putString(INTEREST_TILL_DATE, it.interestTillDate)
                putString(PAYMENT_AMOUNT_TILL_DATE, it.paymentAmountTillDate)
                putString(PURCHASE_AMOUNT_TILL_DATE, it.purchaseAmountTillDate)
                putString(CREDIT_NOTE_AMOUNT_TILL_DATE, it.creditNoteAmountTillDate)
            }
        }
    }
}

fun SavedStateHandle.getNonNullString(key: String): String = this.get<String>(key) ?: ""
