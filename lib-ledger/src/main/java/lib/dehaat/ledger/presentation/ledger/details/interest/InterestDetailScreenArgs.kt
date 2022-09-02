package lib.dehaat.ledger.presentation.ledger.details.interest

import android.os.Bundle
import lib.dehaat.ledger.presentation.ledger.details.interest.ui.InterestViewData
import lib.dehaat.ledger.presentation.model.revamp.transactions.TransactionViewDataV2

object InterestDetailScreenArgs {
    private const val KEY_INTEREST_AMOUNT = "KEY_INTEREST_AMOUNT"
    private const val KEY_INTEREST = "KEY_INTEREST"
    private const val KEY_TOTAL_PAYMENT = "KEY_TOTAL_PAYMENT"

    fun getArgs(bundle: Bundle) = InterestViewData(
        amount = bundle.getString(KEY_INTEREST_AMOUNT) ?: "",
        interest = bundle.getString(KEY_INTEREST) ?: "",
        totalPayment = bundle.getString(KEY_TOTAL_PAYMENT) ?: ""
    )

    fun getBundle(
        transaction: TransactionViewDataV2?
    ) = Bundle().apply {
        transaction?.let {
            putString(KEY_INTEREST_AMOUNT, it.amount)
            putString(KEY_INTEREST, it.interestStartDate.toString())
            putString(KEY_TOTAL_PAYMENT, it.interestEndDate.toString())
        }
    }
}