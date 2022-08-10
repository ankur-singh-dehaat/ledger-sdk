package lib.dehaat.ledger.navigation

interface DetailPageNavigationCallback {
    fun navigateToInvoiceDetailPage(
        legerId: String,
        erpId: String?,
        locusId: String?,
        source: String,
        isLMSActivated: Boolean
    )

    fun navigateToCreditNoteDetailPage(legerId: String, erpId: String?, locusId: String?)
    fun navigateToPaymentDetailPage(
        legerId: String,
        erpId: String?,
        locusId: String?,
        mode: String?,
        isLMSActivated: Boolean
    )

    fun navigateToOutstandingDetailPage()

    fun navigateToInvoiceListPage()

    fun navigateToOtherPaymentModesScreen()

    fun navigateToAvailableCreditLimitDetailPage()

    fun navigateToRevampInvoiceDetailPage()

    fun navigateToRevampCreditNoteDetailPage()

    fun navigateToRevampPaymentDetailPage(
        ledgerId: String,
        onError: (String) -> Unit
    )

    fun navigateToRevampInterestDetailPage()
}
