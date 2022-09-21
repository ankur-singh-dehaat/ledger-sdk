package lib.dehaat.ledger.framework.model.transactionsummary

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TransactionDetailData(
    @Json(name = "purchase_amount")
    val purchaseAmount: String,
    @Json(name = "payment_amount")
    val paymentAmount: String
)

@JsonClass(generateAdapter = true)
data class TransactionDetailDataV2(
    @Json(name = "purchase_amount")
    val purchaseAmount: String,
    @Json(name = "payment_amount")
    val paymentAmount: String,
    @Json(name = "interest_amount")
    val interestAmount: String?
)
