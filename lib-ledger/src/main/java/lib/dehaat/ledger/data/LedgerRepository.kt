package lib.dehaat.ledger.data

import com.cleanarch.base.entity.result.api.APIResultEntity
import javax.inject.Inject
import lib.dehaat.ledger.data.source.ILedgerDataSource
import lib.dehaat.ledger.domain.ILedgerRepository
import lib.dehaat.ledger.entities.detail.invoice.InvoiceDetailDataEntity
import lib.dehaat.ledger.entities.transactions.TransactionEntity

class LedgerRepository @Inject constructor(private val networkSource: ILedgerDataSource) :
    ILedgerRepository {

    override suspend fun getCreditSummary(partnerId: String) =
        networkSource.getCreditSummary(partnerId)

    override suspend fun getCreditSummaryV2(
        partnerId: String
    ) = networkSource.getCreditSummaryV2(partnerId)

    override suspend fun getTransactionSummary(
        partnerId: String
    ) = networkSource.getTransactionSummary(partnerId)

    override suspend fun getTransactions(
        partnerId: String,
        limit: Int,
        offset: Int,
        onlyPenaltyInvoices: Boolean,
        fromDate: Long?,
        toDate: Long?,
    ) = networkSource.getTransactions(
        partnerId,
        limit,
        offset,
        onlyPenaltyInvoices,
        fromDate,
        toDate
    )

    override suspend fun getTransactionsV2(
        partnerId: String,
        limit: Int,
        offset: Int,
        fromDate: Long?,
        toDate: Long?
    ) = networkSource.getTransactionsV2(
        partnerId,
        limit,
        offset,
        fromDate,
        toDate
    )

    override suspend fun getCreditLines(
        partnerId: String
    ) = networkSource.getCreditLines(
        partnerId
    )

    override suspend fun getInvoiceDetail(
        ledgerId: String
    ) = networkSource.getInvoiceDetail(
        ledgerId
    )

    override suspend fun getInvoiceDetails(
        ledgerId: String
    ) = networkSource.getInvoiceDetails(
        ledgerId
    )

    override suspend fun getInvoiceDownload(
        identityId: String,
        source: String
    ) = networkSource.getInvoiceDownload(
        identityId,
        source
    )

    override suspend fun getPaymentDetail(
        ledgerId: String
    ) = networkSource.getPaymentDetail(
        ledgerId
    )

    override suspend fun getCreditNoteDetail(
        ledgerId: String
    ) = networkSource.getCreditNoteDetail(
        ledgerId
    )
}
