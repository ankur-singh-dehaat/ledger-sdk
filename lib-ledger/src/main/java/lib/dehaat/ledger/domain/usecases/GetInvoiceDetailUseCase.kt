package lib.dehaat.ledger.domain.usecases

import javax.inject.Inject
import lib.dehaat.ledger.domain.ILedgerRepository

class GetInvoiceDetailUseCase @Inject constructor(private val repo: ILedgerRepository) {
    suspend operator fun invoke(ledgerId: String) = repo.getInvoiceDetail(ledgerId)

    suspend fun getInvoiceDetails(ledgerId: String) = repo.getInvoiceDetails(ledgerId)
}
