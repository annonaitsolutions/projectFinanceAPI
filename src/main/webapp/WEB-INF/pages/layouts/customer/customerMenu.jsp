<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<div class="col-sm-3 col-md-3 vertical_menu_fixed" style="padding-left: 0px;">
    <div id="menuwrapper">
        <ul class="nav navbar-nav">

<%--            <li><a href="loginDate">Login Date</a></li>--%>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="label.creteNew"/></a>
                <ul class="dropdown-menu">
                    <li><a href="newSupplierPage"><spring:message code="label.supplierApp"/></a></li>
                    <li><a href="newBuyerPage"><spring:message code="label.buyerApp"/></a></li>
                    <li><a href="createWareHouseMng"><spring:message code="label.whmng"/></a></li>
                    <li><a href="createCustWareHouse"><spring:message code="label.menuWareHouse"/></a></li>
                    <%--<li><a href="wareHouseMngList"><spring:message code="label.addwhmngList"/></a></li>
                    <li><a href="custWareHouseReceiveList"><spring:message code="label.goodsReceive"/></a></li>
                    <li><a href="custWareHouseInvoiceList"><spring:message code="label.manfGoodsDel"/></a></li>
                    <li><a href="selectbuyerList"><spring:message code="label.listOfBuyerApp"/></a></li>
                    <li><a href="selectdocumentApprovalList"><spring:message code="label.listOfUploadDocs"/></a></li>
                    <li><a href="selectsupplierList"><spring:message code="label.listOfSupplierApp"/></a></li>
                    <li><a href="headBuyerPoList"><spring:message code="label.listOfBuyerPos"/></a></li>
                    <li><a href="poApprovedList"><spring:message code="label.uploadPoDocs"/>s</a></li>
                    <li><a href="poDocumnetList"><spring:message code="label.listOfPoDocs"/></a></li>
                    <li><a href="invoiceApprovedList"><spring:message code="label.uploadInvoiceDocs"/></a></li>
                    <li><a href="invoiceDocumnetList"><spring:message code="label.listOfInvoiceDocs"/></a></li>--%>


                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message
                        code="label.menuMasterPlan"/></a>
                <ul class="dropdown-menu">
                    <li><a href="masterPlanCurrency"><spring:message code="label.createMp"/></a></li>
<%--                    <li><a href="masterPlanDraftsList"><spring:message code="label.mpDrafts"/></a></li>--%>
                   <%-- <li><a href="masterPlanPending"><spring:message code="label.menuColl"/></a></li>
                    --%>
                    <li><a href="masterPlanAccept"><spring:message code="label.acceptMp"/></a></li>
                    <li><a href="masterPlanDetails"><spring:message code="label.listOfMps"/></a></li>
                    <li><a href="masterPlanPending"><spring:message code="label.addColl"/></a></li>
                    <li><a href="masterPlanFunds"><spring:message code="label.menuFundsDist"/></a></li>
                   <%-- <li><a href="salesPurchaseOrder?productType=PurchaseOrder"><spring:message code="label.createPurchaseOrder"/></a></li>
                    <li><a href="licenseOnRestricted"><spring:message code="label.licenseUpdate"/></a></li>--%>
                    <li><a href="fullFundsDistributeStatement"><spring:message code="label.fullFundsState"/></a></li>
                    <%--<li><a href="customerHeadlPoList"><spring:message code="label.listOfPos"/></a></li>
                    <li><a href="generateInvoice"><spring:message code="label.generateInvoice"/></a></li>
                    <li><a href="customerHeadInvoiceList"><spring:message code="label.listOfInvoice"/></a></li>--%>
<%--                    <li><a href="masterPlanLimitBurstList"><spring:message code="label.menuLimit"/></a></li>--%>
<%--                    <li><a href="limitBurstCustList"><spring:message code="label.listOfLimit"/></a></li>--%>
                    <li><a href="licenseOnRestricted"><spring:message code="label.licenseUpdate"/></a></li>
                    <li><a href="licenseOnRestrictedHeadList"><spring:message code="label.listOfLicenseUpdate"/></a>
                    </li>


                </ul>
            </li>

            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message
                        code="label.purchaseOrder"/></a>
                <ul class="dropdown-menu">
                    <li><a href="salesPurchaseOrder?productType=PurchaseOrder"><spring:message code="label.createPurchaseOrder"/></a></li>
                    <li><a href="poApprovedList"><spring:message code="label.listOfPos"/></a></li>
                    <li><a href="cancelPoList"><spring:message code="label.menuCancelPO"/></a></li>
                    <li><a href="closePoList"><spring:message code="label.menuClosePo"/></a></li>

            </li>
        </ul>
        </li>
            </li>

            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message
                        code="label.salesOrder"/></a>
                <ul class="dropdown-menu">
                    <li><a href="salesPurchaseOrder?productType=SalesOrder"><spring:message code="label.createSalesOrder"/></a></li>
                    <li><a href="listOfSalesOrder"><spring:message code="label.listOfSalesOrder"/></a></li>
                    </li>
                </ul>
            </li>

        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message
                    code="label.invoice"/></a>
            <ul class="dropdown-menu">
                <li><a href="headBuyerPoList"><spring:message code="label.listOfBuyerPos"/></a></li>
                <li><a href="generateInvoice"><spring:message code="label.generateInvoice"/></a></li>
                <li><a href="customerHeadInvoiceList"><spring:message code="label.listOfInvoice"/></a></li>
                <li><a href="closeinvoiceList"><spring:message code="label.menuCloseInvoice"/></a></li>
        </li>
        </ul>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message
                    code="label.docs"/></a>
            <ul class="dropdown-menu">
                <li><a href="poApprovedList"><spring:message code="label.uploadPoDocs"/></a></li>
                <li><a href="poDocumnetList"><spring:message code="label.listOfPoDocs"/></a></li>
                <li><a href="invoiceApprovedList"><spring:message code="label.uploadInvoiceDocs"/></a></li>
                <li><a href="invoiceDocumnetList"><spring:message code="label.listOfInvoiceDocs"/></a></li>
                <li><a href="fileUploadForm"><spring:message code="label.uploadDocsForBuyer"/></a></li>
                <li><a href="selectdocumentApprovalList"><spring:message code="label.listOfUploadDocs"/></a></li>
                </li>

            </ul>
        </li>

            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message
                        code="label.menuInventory"/></a>
                <ul class="dropdown-menu">
                    <li><a href="custWareHouseReceiveList"><spring:message code="label.goodsReceiveFromSupplier"/></a></li>
                   <%--  <li><a href="custWareHouseInvoiceList"><spring:message code="label.goodsDeliverytoBuyer"/></a></li>  --%>
                    <%--<li><a href="createCustWareHouse"><spring:message code="label.addWareHouseMenu"/></a></li>
                    <li><a href="custWareHouseFullList"><spring:message code="label.listOfMenuWH"/></a></li>--%>
                    <li><a href="InventoryPoList"><spring:message code="label.buyingInv"/></a></li>
                    <li><a href="inventoryListCustApproved"><spring:message code="label.listOfImMng"/></a></li>
                    <li><a href="inventoryInvoiceList"><spring:message code="label.updateSellingInventory"/></a></li>
                    <li><a href="inventoryInvoiceListCustApproved"><spring:message code="label.listOfImMngSell"/></a>
                    </li>
                </ul>
            </li>

        <li><a href="#"><spring:message code="label.stock"/></a>
            <ul class="dropdown-menu">
                <li><a href="existingStockHeadPo"><spring:message code="label.updateBuyingStock"/></a></li>
                <li><a href="existingStockHeadPoFullList"><spring:message code="label.listOfBuyingStock"/></a></li>
                <li><a href="existingStockHeadInvoice"><spring:message code="label.updateSellingStock"/></a></li>
                <li><a href="existingStockHeadInvoiceFullList"><spring:message code="label.listOfSellingStock"/></a></li>
            </ul>
        </li>

        <li><a href="#"><spring:message code="label.displayList"/></a>
            <ul class="dropdown-menu">
                <li><a href="selectsupplierList"><spring:message code="label.listOfSupplierApp"/></a></li>
                <li><a href="selectbuyerList"><spring:message code="label.listOfBuyerApp"/></a></li>
                <li><a href="custWareHouseFullList"><spring:message code="label.listOfMenuWH"/></a></li>
                <li><a href="wareHouseMngList"><spring:message code="label.addwhmngList"/></a></li>
            </ul>
        </li>

        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message
                    code="label.menuDispute"/></a>
            <ul class="dropdown-menu">
                <li><a href="disputeHeadList"><spring:message code="label.menuCreateDispute"/></a></li>
                <li><a href="disputeHeadUpdateList"><spring:message code="label.menuUpdateDispute"/></a></li>
                <li><a href="addOrModifyReportList"><spring:message code="label.reportsPo"/></a></li>
                <li><a href="addOrModifyReportCustList"><spring:message code="label.inAcceptorAddRep"/></a></li>
                <li><a href="addOrModifyReportInvoiceFullList"><spring:message code="label.inViewOrCompare"/></a>
                </li>
                <li><a href="invoiceListForRenewel"><spring:message code="label.insRenewal"/></a></li>
            </ul>
        </li>

        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="label.menuPay"/></a>
            <ul class="dropdown-menu">
                <li><a href="#"><spring:message code="label.menuRepay"/></a>
                    <ul class="dropdown-menu">
                        <li><a href="masterPlanRePayment"><spring:message code="label.menuRepay"/></a></li>
                        <li><a href="masterPlanRePaymentAcceptList"><spring:message code="label.acceptRepay"/></a>
                        </li>
                        <li><a href="masterPlanRePaymentList"><spring:message code="label.listOfRepay"/></a></li>
                    </ul>

                </li>
                <li><a href="#"><spring:message code="label.menuPoPay"/></a>
                    <ul class="dropdown-menu">
                        <li><a href="poPaymentHeadList"><spring:message code="label.payPo"/></a></li>
                        <li><a href="poPaymentHeadFullList"><spring:message code="label.listOfPoPyMents"/></a></li>
                        <li><a href="poMultiplePayment"><spring:message code="label.multiplePayment"/></a></li>

                    </ul>
                </li>
                <li><a href="#"><spring:message code="label.requestMoneyMenu"/></a>
                    <ul class="dropdown-menu">
                        <li><a href="requestMoneyOnInvoice"><spring:message code="label.mnyReqOnInvoice"/></a></li>
                        <li><a href="requestMoneyAccepted"><spring:message code="label.menuPayAccept"/></a></li>
                        <li><a href="requestMoneyInvoiceHeadFullList"><spring:message
                                code="label.invoiceMnyPayList"/></a></li>
                    </ul>
                </li>
            </ul>
        </li>

        <li><a href="#"><spring:message code="label.poEvents"/>&nbsp;<i class="fa fa-caret-right"></i></a>
            <ul class="dropdown-menu">

                <li><a tabindex="-1" href="newEvent"><spring:message code="label.menuCreateEvent"/></a></li>

                <li class="dropdown-submenu"><a tabindex="-1" href="#"><spring:message code="label.menuSb"/></a>
                    <ul class="dropdown-menu">
                        <li><a href="viewAllEvents"><spring:message code="label.menuUpdateEvents"/></a></li>
                        <li><a href="viewAllEvents3"><spring:message code="label.menuCheckStatus"/></a></li>
                        <!-- 	<li><a href="graphBuyerSamebankCost">Cost Exposure
                                    Graph</a></li>
                            <li><a href="graphBuyerSamebankDate">Time Exposure
                                    Graph</a></li> -->

                    </ul>
                </li>
                <li class="dropdown-submenu"><a tabindex="-1" href="#"><spring:message code="label.menuDb"/></a>
                    <ul class="dropdown-menu">

                        <li><a href="buyerDiffViewAllEvents"><spring:message code="label.menuUpdateEvents"/></a>
                        </li>
                        <li><a href="buyerDiffViewAllEvents3"><spring:message code="label.menuCheckStatus"/></a>
                        </li>

                    </ul>
                </li>
                <li><a href="comparisonPO"><spring:message code="label.menuComparison"/></a></li>
            </ul>

        </li>

        <li><a href="#"><spring:message code="label.invoiceEvents"/>&nbsp;<i class="fa fa-caret-right"></i></a>
            <ul class="dropdown-menu">
                <li><a tabindex="-1" href="sellerNewEvent"><spring:message code="label.menuCreateEvent"/></a></li>
                <li class="dropdown-submenu"><a tabindex="-1" href="#"><spring:message code="label.menuSb"/></a>
                    <ul class="dropdown-menu">

                        <li><a href="sellerSameViewAllEvents"><spring:message code="label.menuUpdateEvents"/></a>
                        </li>
                        <li><a href="sellerSameViewAllEvents3"><spring:message code="label.menuCheckStatus"/></a>
                        </li>
                        <!-- 	<li><a href="graphSellerSamebankCost">Cost Exposure
                                    Graph</a></li>
                            <li><a href="graphsellersamebankDate">Time Exposure
                                    Graph</a></li> -->
                    </ul>
                </li>
                <li class="dropdown-submenu"><a tabindex="-1" href="#"><spring:message code="label.menuDb"/></a>
                    <ul class="dropdown-menu">
                        <li><a href="sellerDiffViewAllEvents"><spring:message code="label.menuUpdateEvents"/></a>
                        </li>
                        <li><a href="sellerDiffViewAllEvents3"><spring:message code="label.menuCheckStatus"/></a>
                        </li>

                    </ul>
                <li><a href="comparisonInvoice"><spring:message code="label.menuComparison"/></a></li>
            </ul>
        </li>

            <%--<li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message
                        code="label.menuCancel/Close"/></a>
                <ul class="dropdown-menu">
                    &lt;%&ndash;<li><a href="cancelPoList"><spring:message code="label.menuCancelPO"/></a></li>
                    <li><a href="closePoList"><spring:message code="label.menuClosePo"/></a></li>&ndash;%&gt;
                    <li><a href="closeinvoiceList"><spring:message code="label.menuCloseInvoice"/></a></li>

                </ul>
            </li>--%>
            <%--<li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message
                        code="label.menuExisStock"/></a>
                <ul class="dropdown-menu">
                    <li><a href="existingStockHeadPo"><spring:message code="label.menuBuyingStock"/></a></li>
                    <li><a href="existingStockHeadPoFullList"><spring:message code="label.listOfBuyingStock"/></a></li>
                    <li><a href="existingStockHeadInvoice"><spring:message code="label.menuSellingStock"/></a></li>
                    <li><a href="existingStockHeadInvoiceFullList"><spring:message code="label.listOfSellingStock"/></a>
                    </li>

                </ul>
            </li>--%>

            <li class="dropdown-submenu"><a tabindex="-1" href="#"><spring:message code="label.menuUs"/></a>
                <ul class="dropdown-menu">
                    <li><a href="downloadfile"><spring:message code="label.downloadSampleFile"/></a></li>
                    <li><a href="snapShotCustHead"><spring:message code="label.menuSnp"/></a></li>
                    <li><a href="showMail"><spring:message code="label.menuMail"/></a></li>
                    <li><a href="showCurrency"><spring:message code="label.menuCurrency"/></a></li>
                    <li><a href="showcurrencyConversion"><spring:message code="label.menuCurrencyConversion"/></a></li>

                </ul>
            </li>


            <li class="dropdown-submenu"><a tabindex="-1" href="#"><spring:message code="label.menuCharts"/></a>
                <ul class="dropdown-menu">
                    <li><a href="generateWarehouseChart"><spring:message code="label.poInventory"/></a></li>
                    <li><a href="generateInvoiceInventoryChartBranch"><spring:message
                            code="label.invoiceInventory"/></a></li>
                    <li><a href="existingStockPOInventoryChart"><spring:message code="label.exPoChart"/></a></li>
                    <li><a href="existingStockInvoiceInventoryChart"><spring:message code="label.exInvoiceChart"/></a>
                    </li>
                    <li><a href="uploadImageForGoods"><spring:message code="label.imageUploadForInventory"/></a></li>
                    <li><a href="wareHouseImageDisplay"><spring:message code="label.wareHouseDetails"/></a></li>
                </ul>
            </li>

        <li><a href="#"><spring:message code="label.menuReports"/></a>
            <ul class="dropdown-menu">
                <li><a href="reportsListHead"><spring:message code="label.stsReportBuying"/></a></li>
                <li><a href="reportsListHeadBying"><spring:message code="label.stsReportSelling"/></a></li>
            </ul>
        </li>


        </ul>
    </div>
</div>