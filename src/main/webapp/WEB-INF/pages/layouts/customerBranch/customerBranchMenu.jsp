<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="col-sm-3 col-md-3 vertical_menu_fixed" style="padding-left: 0px;">
<div id="menuwrapper">

<ul class="nav navbar-nav">
				
						
							<li class="dropdown-submenu"><a tabindex="-1" href="#"><spring:message code="label.creteNew"/></a>
								<ul class="dropdown-menu">
									<li><a href="addnewBuyerCustomerBranch"><spring:message code="label.buyerApp"/></a></li>
								     <li><a href="supplierForCustomerBranch"><spring:message code="label.supplierApp"/></a></li>
									<li><a href="createBranchWareHouseMng"><spring:message code="label.addwhmng"/></a></li>
									<li><a href="fileUploadForm"><spring:message code="label.menuUploadDocs"/></a></li>
							        <li><a href="selectcustomerBranchdocumentApprovalList"><spring:message code="label.listOfUploadDocs"/></a></li>
									<li><a href="customerBranchBuyerList"><spring:message code="label.listOfBuyerApp"/></a></li>
									<li><a href="customerBranchSupplierList"><spring:message code="label.listOfSupplierApp"/></a></li>
									<li><a href="branchWareHouseMngList"><spring:message code="label.addwhmngList"/></a></li>
									</ul>
							</li>
							<li class="dropdown-submenu"> <a tabindex="-1" href="#"><spring:message code="label.menuDetails"/></a>
								<ul class="dropdown-menu">
									
									<li><a href="customerBranchPODocumentList"><spring:message code="label.listOfPoDocs"/></a></li>
									<li><a href="poApprovedListBranch"><spring:message code="label.uploadPoDocs"/></a></li>
							     <li><a href="poDocumnetListBranch"><spring:message code="label.listOfPoDocs"/></a></li>
						        <li><a href="invoiceApprovedListBranch"><spring:message code="label.uploadInvoiceDocs"/></a></li>
						         <li><a href="invoiceDocumnetListBranch"><spring:message code="label.listOfInvoiceDocs"/></a></li>
						         <li><a href="branchBuyerPoList"><spring:message code="label.listOfBuyerPos"/></a></li>
								</ul>
							</li>
							<li class="dropdown-submenu"> <a tabindex="-1" href="#"><spring:message code="label.menuPoPay"/></a>
								<ul class="dropdown-menu">
									<li><a href="poPaymentBranchList"><spring:message code="label.payPo"/></a></li>
								<li><a href="poPaymentBranchFullList"><spring:message code="label.listOfPoPyMents"/></a></li>
								<li><a href="poMultiplePayment"><spring:message code="label.multiplePayment"/></a></li>
								</ul>
							</li>
							<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="label.menuDispute"/></a>
						<ul class="dropdown-menu">
							<li><a href="disputeBranchList"><spring:message code="label.menuCreateDispute"/></a></li>
							<li><a href="disputeBranchUpdateList"><spring:message code="label.menuUpdateDispute"/></a></li>
							<li><a href="addOrModifyReportBranchList"><spring:message code="label.reportsPo"/></a></li>	
							<li><a href="addOrModifyReportCustBranchList"><spring:message code="label.inAcceptorAddRep"/></a></li>
							<li><a href="addOrModifyReportInvoiceBranchFullList"><spring:message code="label.inViewOrCompare"/></a></li>
							<li><a href="invoiceBranchListForRenewel"><spring:message code="label.insRenewal"/></a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="label.menuInventory"/></a>
						<ul class="dropdown-menu">
							<li><a href="createBranchWareHouse"><spring:message code="label.addWareHouseMenu"/></a></li>
							<li><a href="branchWareHouseFullList"><spring:message code="label.listOfMenuWH"/></a></li>
							<li><a href="inventoryPoListForBranch"><spring:message code="label.poInventory"/></a></li>
							<li><a href="inventoryPoListForBranchApproved"><spring:message code="label.listOfImMng"/></a></li>
							<li><a href="inventoryInvoiceBranchList"><spring:message code="label.invoiceInventory"/></a></li>
							<li><a href="inventoryInvoiceListForBranchApproved"><spring:message code="label.listOfImMngSell"/></a></li>
							<li><a href="branchWareHouseReceiveList"><spring:message code="label.goodsDel"/></a></li>
							<li><a href="branchWareHouseInvoiceList"><spring:message code="label.manfGoodsDel"/></a></li>
						</ul>
					</li>
					<li><a href="#"><spring:message code="label.menuReports"/></a>
			                         <ul class="dropdown-menu">
							<li><a href="reportsListBranch"><spring:message code="label.repBuying"/></a></li>
							<li><a href="reportsListBranchBying"><spring:message code="label.repSelling"/></a></li>
						</ul>
			            </li>
							<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="label.menuCancel/Close"/></a>
						<ul class="dropdown-menu">
							<li><a href="cancelPoBranchList"><spring:message code="label.menuCancelPO"/></a></li>
							<li><a href="closePoBranchList"><spring:message code="label.menuClosePo"/></a></li>
							<li><a href="closeinvoiceBranchList"><spring:message code="label.menuCloseInvoice"/></a></li>
						</ul>
					</li>
						<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="label.menuExisStock"/></a>
						<ul class="dropdown-menu">
							<li><a href="existingStockPo"><spring:message code="label.menuBuyingStock"/></a></li>
							<li><a href="existingStockPoFullList"><spring:message code="label.listOfBuyingStock"/></a></li>
							<li><a href="existingStockInvoice"><spring:message code="label.menuSellingStock"/></a></li>
							<li><a href="existingStockInvoiceFullList"><spring:message code="label.listOfSellingStock"/></a></li>
							
						</ul>
					</li>
							<li class="dropdown-submenu"> <a tabindex="-1" href="#"><spring:message code="label.menuReq"/></a>
								<ul class="dropdown-menu">
									<li><a href="branchFundsRequest"><spring:message code="label.requestMoneyMenu"/></a></li>
							<li><a href="viewBranchRequestSent"><spring:message code="label.menuReqSentList"/></a></li>
							<li><a href="viewBranchRequestRecieved"><spring:message code="label.menuReqReceviedList"/></a></li>
							<li><a href="branchFundsTransfer"><spring:message code="label.menuTransferMny"/></a></li>
							<li><a href="fullBranchFundsDistributeStatement"><spring:message code="label.fullFundsState"/></a></li>
								</ul>
							</li>
							<li class="dropdown-submenu"> <a tabindex="-1" href="#"><spring:message code="label.menuBusPlan"/></a>
								<ul class="dropdown-menu">
									<li><a href="branchBusinessPlan"><spring:message code="label.createBusPlan"/></a></li>
									<li><a href="licenseOnRestrictedBranch"><spring:message code="label.licenseUpdate"/></a></li>
									<li><a href="customerBranchPoList"><spring:message code="label.listOfPos"/></a></li>
									<li><a href="customerBranchInvoiceList"><spring:message code="label.listOfInvoice"/></a></li>
									<li><a href="licenseOnRestrictedBranchList"><spring:message code="label.listOfLicenseUpdate"/></a></li>
								</ul>
							</li>
							<li><a href="#"><spring:message code="label.poEvents"/>&nbsp;<i class="fa fa-caret-right"></i></a>
			                   <ul class="dropdown-menu">
			                   <li><a tabindex="-1" href="newEvent"><spring:message code="label.menuCreateEvent"/></a></li>
                                <li class="dropdown-submenu"><a tabindex="-1" href="#"><spring:message code="label.menuSb"/></a>
								<ul class="dropdown-menu">
									<li><a href="viewAllEvents"><spring:message code="label.menuUpdateEvents"/></a></li>
									<li><a href="viewAllEvents3"><spring:message code="label.menuCheckStatus"/></a></li>
									<!-- <li><a href="customerBranchgraphBuyerSamebankCost">Cost Exposure
											Graph</a></li>
									<li><a href="customerBranchgraphBuyerSamebankDate">Time Exposure
											Graph</a></li> -->
								</ul></li>
							<li class="dropdown-submenu"><a tabindex="-1" href="#"><spring:message code="label.menuDb"/></a>
								<ul class="dropdown-menu">
									
									<li><a href="buyerDiffViewAllEvents"><spring:message code="label.menuUpdateEvents"/></a></li>
									<li><a href="buyerDiffViewAllEvents3"><spring:message code="label.menuCheckStatus"/></a></li>
									<!-- <li><a href="customerBrnchgraphBuyerDiffbankCost">Cost Exposure
											Graph</a></li>
									<li><a href="customerBranchbuyerDiffbankDategraph">Time Exposure
											Graph</a></li> -->
								</ul>
								</li>
								<li><a href="comparisonBranchPO"><spring:message code="label.menuComparison"/></a></li>
						</ul>
			
			</li>
			<li><a href="#"><spring:message code="label.invoiceEvents"/>&nbsp;<i class="fa fa-caret-right"></i></a>
              <ul class="dropdown-menu">
                            <li><a tabindex="-1" href="sellerNewEvent"><spring:message code="label.menuCreateEvent"/></a></li>
							<li class="dropdown-submenu"><a tabindex="-1" href="#"><spring:message code="label.menuSb"/></a>
								<ul class="dropdown-menu">
									
									<li><a href="sellerSameViewAllEvents"><spring:message code="label.menuUpdateEvents"/></a></li>
									<li><a href="sellerSameViewAllEvents3"><spring:message code="label.menuCheckStatus"/></a></li>
									
								</ul></li>
							<li class="dropdown-submenu"><a tabindex="-1" href="#"><spring:message code="label.menuDb"/></a>
								<ul class="dropdown-menu">
									<li><a href="sellerDiffViewAllEvents"><spring:message code="label.menuUpdateEvents"/></a></li>
									<li><a href="sellerDiffViewAllEvents3"><spring:message code="label.menuCheckStatus"/></a></li>
									
								</ul>
								<li><a href="comparisonBranchInvoice"><spring:message code="label.menuComparison"/></a></li>
								</ul>
								</li>
								
    
							
							<li class="dropdown-submenu"> <a tabindex="-1" href="#"><spring:message code="label.menuUs"/></a>
								<ul class="dropdown-menu">
						             <li><a href="branchShowMail"><spring:message code="label.menuMail"/></a></li>
									<li><a href="branchShowCurrency"><spring:message code="label.menuCurrency"/></a></li>
									<li><a href="branchShowcurrencyConversion"><spring:message code="label.menuCurrencyConversion"/></a></li>
									<li><a href="downloadBranchFile"><spring:message code="label.downloadSampleFile"/></a></li>
								</ul>
							</li>
							<li class="dropdown-submenu"> <a tabindex="-1" href="#"><spring:message code="label.menuCharts"/></a>
								<ul class="dropdown-menu">
						             <li><a href="generateWarehouseChart"><spring:message code="label.poInventory"/></a></li>
									<li><a href="generateInvoiceInventoryChartBranch"><spring:message code="label.invoiceInventory"/></a></li>	
									<li><a href="existingStockPOInventoryChart"><spring:message code="label.exPoChart"/></a></li>
							        <li><a href="existingStockInvoiceInventoryChart"><spring:message code="label.exInvoiceChart"/></a></li>	
							        <li><a href="wareHouseImageDisplay"><spring:message code="label.wareHouseDetails"/></a></li>							
								</ul>
							</li>						
						</ul>
						</div>
						</div>
