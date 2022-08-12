package lib.dehaat.ledger.datasource

import android.content.Context
import lib.dehaat.ledger.R
import lib.dehaat.ledger.initializer.LedgerParentApp
import lib.dehaat.ledger.initializer.LedgerSDK
import lib.dehaat.ledger.initializer.callbacks.LedgerCallBack
import lib.dehaat.ledger.presentation.ledger.details.invoice.ui.CreditNote
import lib.dehaat.ledger.presentation.ledger.ui.component.ProductDetails
import lib.dehaat.ledger.presentation.ledger.ui.component.RevampProduct
import lib.dehaat.ledger.presentation.model.creditlines.CreditLineViewData
import lib.dehaat.ledger.presentation.model.creditsummary.CreditSummaryViewData
import lib.dehaat.ledger.presentation.model.creditsummary.CreditViewData
import lib.dehaat.ledger.presentation.model.creditsummary.InfoViewData
import lib.dehaat.ledger.presentation.model.creditsummary.OverdueViewData
import lib.dehaat.ledger.presentation.model.transactions.TransactionViewData

object DummyDataSource {

    private val creditViewData by lazy {
        CreditViewData(
            externalFinancierSupported = true,
            totalCreditLimit = "000001",
            totalAvailableCreditLimit = "-000002",
            totalOutstandingAmount = "000003",
            principalOutstandingAmount = "000004",
            interestOutstandingAmount = "000005",
            overdueInterestOutstandingAmount = "000006",
            penaltyOutstandingAmount = "000007"
        )
    }

    private val infoViewData by lazy {
        InfoViewData(
            totalPurchaseAmount = "000008",
            totalPaymentAmount = "000009",
            undeliveredInvoiceAmount = "000010"
        )
    }
    private val overdueViewData by lazy {
        OverdueViewData(
            totalOverdueLimit = "1000",
            totalOverdueAmount = "1000",
            minPaymentAmount = "1000",
            minPaymentDueDate = 78
        )
    }
    val creditSummaryViewData by lazy {
        CreditSummaryViewData(
            creditViewData,
            overdueViewData,
            infoViewData
        )
    }
    val creditLineViewData by lazy {
        CreditLineViewData(
            belongsToGapl = true,
            lenderViewName = "lenderViewName",
            creditLimit = "000011",
            availableCreditLimit = "000012",
            totalOutstandingAmount = "000013",
            principalOutstandingAmount = "000014",
            interestOutstandingAmount = "000015",
            overdueInterestOutstandingAmount = "000016",
            penaltyOutstandingAmount = "000017",
            advanceAmount = "000018"
        )
    }
    val transactionViewData by lazy {
        TransactionViewData(
            ledgerId = "ledgerId",
            type = "type",
            date = 67384543,
            amount = "000019",
            erpId = "erpId",
            locusId = "locusId",
            creditNoteReason = "creditNoteReason",
            paymentMode = "paymentMode",
            source = "SAP"
        )
    }

    private val revampProduct = RevampProduct(
        name = "Triticum - 30 ( 10 Kg)",
        quantity = "10",
        price = "₹ 1,500",
        size = "1 X 10 Kg"
    )

    val productDetails = ProductDetails(
        totalCount = "2",
        products = listOf(revampProduct, revampProduct, revampProduct),
        purchaseAmount = "₹ 30,000",
        discount = "₹ 30,000",
        gst = "₹ 30,000",
        totalAmount = "₹ 30,000"
    )

    private val creditNote = CreditNote(
        title = "क्रेडिट नोट: वॉल्यूम रेट कैशबैक",
        date = "18-जून-2022",
        amount = "₹ 5,000"
    )

    val creditNotes = listOf(creditNote, creditNote, creditNote)

    private val dbaApp by lazy {
        LedgerParentApp.DBA(
            ledgerCallBack = LedgerCallBack({}, {}, { _, _ -> }, { context, path -> null }
            )
        )
    }
    private val aimsApp by lazy {
        LedgerParentApp.AIMS(
            downloadInvoiceClick = {},
            downloadInvoiceIntent = { context, path -> null }
        )
    }

    fun initDBA(context: Context) = LedgerSDK.init(
        context,
        dbaApp,
        "bucket",
        R.drawable.ic_info_icon
    )

    fun initAIMS(context: Context) = LedgerSDK.init(
        context,
        aimsApp,
        "bucket",
        R.drawable.ic_info_icon
    )
}
