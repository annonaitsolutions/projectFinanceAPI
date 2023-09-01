<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
  
 
<div class="col-sm-3 col-md-3 vertical_menu_fixed" style="padding-left: 0px;">
<div id="menuwrapper">
<ul class="nav navbar-nav">
				
					
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-file-text-o"></i>&nbsp;<spring:message code="label.Approvals"/>&nbsp;<i class="fa fa-angle-down"></i></a>
						<ul class="dropdown-menu">
						<li><a href="customerHeadCApprovalList"><spring:message code="label.custHeadApp"/></a></li>
						<li><a href="customerBranchcApprovalList"><spring:message code="label.custBr/SubsApp"/></a></li>
							<li><a href="buyerClientApprovalList"><spring:message code="label.approvalbuyer"/></a></li>	
						<li><a href="supplierClientApprovalList"><spring:message code="label.approvalsupplier"/></a></li>	
						<li><a href="wareHouseMngApprovalList"><spring:message code="label.warreHouseMngApp"/></a></li>
						<!-- <li><a href="customerSubsidiarycApprovalList">Customer Subsidiary</a></li> -->	
						<li><a href="custWareHouseAppList"><spring:message code="label.menuWareHouse"/></a></li>
						<li><a href="custWareHouseAppMngList"><spring:message code="label.listOfMenuWH"/></a></li>
						<li><a href="capprCustomerHeadList"><spring:message code="label.listOfCustHead"/></a></li>
						<li><a href="capprCustomerBranchList"><spring:message code="label.listOfCustBr/Subs"/></a></li>
						<li><a href="wareHouseMngCAppFullList"><spring:message code="label.addwhmngList"/></a></li>
						<!-- <li><a href="capprCustomerSubsidiaryList">Customer Subsidiary List</a></li> -->
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-file-text-o"></i>&nbsp;<spring:message code="label.menuDetails"/>&nbsp;<i class="fa fa-angle-down"></i></a>
						<ul class="dropdown-menu">
							
					<%-- 	<li><a href="buyerClientApprovalList"><spring:message code="label.listOfBuyerApp"/></a></li>	
						<li><a href="supplierClientApprovalList"><spring:message code="label.listOfSupplierApp"/></a></li>	 --%>
						<li><a href="requestMoneyInvoiceCustAppMngFullList"><spring:message code="label.invoiceMnyPayList"/></a></li>	
						<li><a href="poDocumnetListCustomerAppMng"><spring:message code="label.listOfPoDoc"/></a></li>	
						<li><a href="invoiceDocumnetListCustomerAppMng"><spring:message code="label.listOfInvoiceDocs"/></a></li>	
						<li><a href="appExistingStockInvoiceList"><spring:message code="label.listOfSellingStock"/></a></li>	
						<li><a href="appExistingStockPoList"><spring:message code="label.listOfBuyingStock"/></a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-file-text-o"></i>&nbsp;<spring:message code="label.menuMasterPlan"/>&nbsp;<i class="fa fa-angle-down"></i></a>
						<ul class="dropdown-menu">
						<li><a href="clientMasterPlanPendingDetails"><spring:message code="label.mpAppMngMenu"/></a></li>
						<li><a href="masterPlanClientFullList"><spring:message code="label.listOfMps"/></a></li>
						<li><a href="appMngPoList"><spring:message code="label.listOfPos"/></a></li>	
						<li><a href="cPoListForApproval"><spring:message code="label.poApproval"/></a></li>
						<li><a href="cancelPoAppList"><spring:message code="label.cancelPoApproval"/></a></li>
							<li><a href="invoiceListForcApproval"><spring:message code="label.invoiceApproval"/></a></li>
						<li><a href="fullcInvoiceList"><spring:message code="label.listOfInvoice"/></a></li>	
						<li><a href="salesOrderListForcApproval"><spring:message code="label.salesOrderApp"/></a></li>
						<li><a href="salesOrderList"><spring:message code="label.listOfSalesOrder"/></a></li>	
						<li><a href="poPaymentCustAppList"><spring:message code="label.listOfPoPyMents"/></a></li>	
						
						
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-file-text-o"></i>&nbsp;<spring:message code="label.menuRepay"/>&nbsp;<i class="fa fa-angle-down"></i></a>
						<ul class="dropdown-menu">
						<li><a href="masterPlanRePaymentAppList"><spring:message code="label.repayAppMngApp"/></a></li>
						<li><a href="masterPlanRePaymentFullList"><spring:message code="label.listOfRepay"/></a></li>
							
						</ul>
					</li>
					<li class="dropdown-submenu"><a tabindex="-1" href="#"><spring:message code="label.poEvents"/></a>
								<ul class="dropdown-menu">
									<li><a href="sameBankEventPage"><spring:message code="label.menuSb"/></a></li>
									<li><a href="buyerDiffrentBankPage"><spring:message code="label.menuDb"/></a></li>
								</ul>
							</li>
							<li class="dropdown-submenu"> <a tabindex="-1" href="#"><spring:message code="label.invoiceEvents"/></a>
								<ul class="dropdown-menu">
									<li><a href="sellerBankSameEventPage"><spring:message code="label.menuSb"/></a></li>
									<li><a href="sellerBankDiffrentEventPage"><spring:message code="label.menuDb"/></a></li>
								</ul>
							</li>
                       <li><a href="snapShotCustAppMng"><spring:message code="label.menuSnp"/></a></li>
                       <li class="dropdown-submenu"> <a tabindex="-1" href="#"><spring:message code="label.insDetails"/></a>
								<ul class="dropdown-menu">
						             <li><a href="poInsApprovalList"><spring:message code="label.reportsApp"/></a></li>
									<li><a href="poInsFullList"><spring:message code="label.insList"/></a></li>
									<li><a href="addOrModifyReportInvoiceAppMngFullList"><spring:message code="label.insInvoiceList"/></a></li>
								</ul>
							</li>	
					<li class="dropdown-submenu"> <a tabindex="-1" href="#"><spring:message code="label.menuCharts"/></a>
								<ul class="dropdown-menu">
						             <li><a href="generateWarehouseChart"><spring:message code="label.poInventory"/></a></li>
									<li><a href="generateInvoiceInventoryChartBranch"><spring:message code="label.invoiceInventory"/></a></li>
									<li><a href="existingStockPOInventoryChart"><spring:message code="label.exPoChart"/></a></li>
									<li><a href="existingStockInvoiceInventoryChart"><spring:message code="label.exInvoiceChart"/></a></li>									
								</ul>
							</li>
					</ul>
</div>
</div>