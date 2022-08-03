package lib.dehaat.ledger.util

import com.cleanarch.base.entity.result.api.APIResultEntity
import com.dehaat.androidbase.helper.tryCatchWithReturn
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import lib.dehaat.ledger.framework.model.BaseAPIErrorResponse

fun <D> APIResultEntity<D>.processAPIResponseWithFailureSnackBar(
    onFailure: (message: String) -> Unit,
    handleSuccess: (data: D) -> Unit
) = when (this) {
    is APIResultEntity.Success -> handleSuccess(this.data)
    is APIResultEntity.Failure.ErrorException -> onFailure(
        this.exceptionError.message ?: ""
    )
    is APIResultEntity.Failure.ErrorFailure -> onFailure(this.responseMessage)
}

fun APIResultEntity.Failure.getFailureError() = when (this) {
    is APIResultEntity.Failure.ErrorException -> this.exceptionError.message.nullToValue("API Exception")
    is APIResultEntity.Failure.ErrorFailure -> parseAPIErrorBody(
        this.responseErrorBody,
        this.responseMessage
    )
}

fun parseAPIErrorBody(
    errorBodyResponse: String?,
    defaultError: String = "Error!!"
) = errorBodyResponse?.let { errorBody ->
    tryCatchWithReturn(defaultError) {
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<BaseAPIErrorResponse> =
            moshi.adapter(BaseAPIErrorResponse::class.java)
        jsonAdapter.fromJson(errorBody)?.error?.message ?: defaultError
    }
} ?: defaultError