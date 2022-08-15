package lib.dehaat.ledger.framework.model.revamp.invoicedetails

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductsInfo(
    @Json(name = "count")
    val count: Int,
    @Json(name = "discount")
    val discount: String?,
    @Json(name = "gst")
    val gst: String,
    @Json(name = "product_list")
    val productList: List<Product>,
    @Json(name = "purchase_amount")
    val purchaseAmount: String?,
    @Json(name = "total_amount")
    val totalAmount: String?
)
