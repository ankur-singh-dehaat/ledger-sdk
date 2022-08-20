package lib.dehaat.ledger.presentation.ledger.details.loanlist

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.cleanarch.base.entity.result.api.APIResultEntity
import com.dehaat.androidbase.helper.callInViewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import lib.dehaat.ledger.domain.revamp.usecase.GetInvoiceListUseCase
import lib.dehaat.ledger.entities.revamp.invoicelist.InvoiceListEntity
import lib.dehaat.ledger.presentation.common.BaseViewModel
import lib.dehaat.ledger.presentation.ledger.revamp.state.invoicelist.InvoiceListViewModelState
import lib.dehaat.ledger.presentation.mapper.LedgerViewDataMapper
import lib.dehaat.ledger.util.processAPIResponseWithFailureSnackBar

@HiltViewModel
class InvoiceListViewModel @Inject constructor(
    private val getInvoiceListUseCase: GetInvoiceListUseCase,
    private val mapper: LedgerViewDataMapper,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    val dueDate by lazy { savedStateHandle.get<String>(KEY_DUE_DATE)?.toLongOrNull() }
    val amountDue by lazy { savedStateHandle.get<String>(KEY_DUE_AMOUNT) }
    private val partnerId by lazy { savedStateHandle.get<String>(KEY_PARTNER_ID) ?: "" }

    private val viewModelState = MutableStateFlow(InvoiceListViewModelState())
    val uiState = viewModelState
        .map { it.toUIState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUIState()
        )

    private var limit = 10
    private var approachedOffset = 0
    private var approachingOffset = 0

    init {
        updateInterestApproachedLoading(true)
        getInterestApproachedInvoicesFromServer()
        updateInterestApproachingLoading(true)
        getInterestApproachingInvoicesFromServer()
    }

    private fun getInterestApproachedInvoicesFromServer() {
        callInViewModelScope {
            val response = getInvoiceListUseCase.getInvoices(
                partnerId = partnerId,
                limit = limit,
                offset = approachedOffset,
                isInterestApproached = true
            )
            processInterestApproachedInvoicesResponse(response)
        }
    }

    private fun getInterestApproachingInvoicesFromServer() {
        callInViewModelScope {
            val response = getInvoiceListUseCase.getInvoices(
                partnerId = partnerId,
                limit = limit,
                offset = approachingOffset,
                isInterestApproached = false
            )
            processInterestApproachingInvoicesResponse(response)
        }
    }

    private fun processInterestApproachedInvoicesResponse(
        result: APIResultEntity<List<InvoiceListEntity>?>
    ) = result.processAPIResponseWithFailureSnackBar(::sendFailureEvent) { entity ->
        val viewData = mapper.toInvoiceListViewData(entity)
        val data =
            viewModelState.value.interestApproachedInvoices?.toMutableList() ?: mutableListOf()
        viewData?.let { list -> data.addAll(list) }
        viewModelState.update {
            it.copy(
                interestApproachedInvoices = data,
                isSuccess = true
            )
        }
        viewData?.let { list ->
            if (list.size == 2 && approachedOffset < 5) {
                approachedOffset += 1
                getInterestApproachedInvoicesFromServer()
            } else {
                updateInterestApproachedLoading(false)
            }
        } ?: updateInterestApproachedLoading(false)
    }

    private fun processInterestApproachingInvoicesResponse(
        result: APIResultEntity<List<InvoiceListEntity>?>
    ) = result.processAPIResponseWithFailureSnackBar(::sendFailureEvent) { entity ->
        val viewData = mapper.toInvoiceListViewData(entity)
        val data = viewModelState.value.interestApproachingInvoices?.toMutableList()
        viewData?.let { list -> data?.addAll(list) }
        viewModelState.update {
            it.copy(
                interestApproachingInvoices = data,
                isSuccess = true
            )
        }
        viewData?.let {
            if (it.size == 10 && approachingOffset < 2) {
                approachingOffset += 1
                getInterestApproachingInvoicesFromServer()
            } else {
                updateInterestApproachingLoading(false)
            }
        } ?: updateInterestApproachingLoading(false)
    }

    private fun sendFailureEvent(message: String) {
        viewModelState.update {
            it.copy(
                isError = true,
                errorMessage = message
            )
        }
    }

    private fun updateInterestApproachingLoading(show: Boolean) = viewModelState.update {
        it.copy(interestApproachingLoading = show)
    }

    private fun updateInterestApproachedLoading(show: Boolean) = viewModelState.update {
        it.copy(interestApproachedLoading = show)
    }

    companion object {
        private const val KEY_DUE_DATE = "KEY_DUE_DATE"
        private const val KEY_DUE_AMOUNT = "KEY_DUE_AMOUNT"
        private const val KEY_PARTNER_ID = "KEY_PARTNER_ID"

        fun getBundle(
            outstandingDueDate: Long?,
            outstandingAmountDue: String?,
            partnerId: String
        ) = Bundle().apply {
            putString(KEY_DUE_DATE, outstandingDueDate.toString())
            putString(KEY_DUE_AMOUNT, outstandingAmountDue)
            putString(KEY_PARTNER_ID, partnerId)
        }
    }
}
