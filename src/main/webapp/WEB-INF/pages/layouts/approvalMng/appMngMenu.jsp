<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
  
 
<div class="col-sm-3 col-md-3 vertical_menu_fixed" style="padding-left: 0px;">
<div id="menuwrapper">
<ul class="nav navbar-nav">
				<li class="dropdown"> <a href="#"><spring:message code="label.Approvals"/></a>
								<ul class="dropdown-menu">
								<li><a href="companyList"><spring:message code="label.company"/></a></li>
								<li><a href="custAdminApprovalList"><spring:message code="label.custAdminApp"/></a></li>
								<li><a href="custAppMngApprovalList"><spring:message code="label.custAppMngAPp"/></a></li>
								<li><a href="customerHeadApprovalList"><spring:message code="label.custHeadApp"/></a></li>
								<li><a href="customerBranchApprovalList"><spring:message code="label.custBr/SubsApp"/></a></li>
								<li><a href="whMngBankApprovalList"><spring:message code="label.whmng"/></a></li>
								<li><a href="supplierPage"><spring:message code="label.supplierApp"/></a></li>
								<li><a href="buyerPage"><spring:message code="label.buyerApp"/></a></li>
								<li><a href="appMasterPlanDetails"><spring:message code="label.mpAppMngMenu"/></a></li>
								<li><a href="uploadDocument"><spring:message code="label.menuUploadedDocs"/></a></li>
								 </ul>
								
							</li>
									
									
									<%-- <li><a href="bankEmpApprov"><spring:message code="label.bankEmpApp"/></a></li> --%>
									
				<li class="dropdown"> <a href="#"><spring:message code="label.displayList"/></a>					
									<ul class="dropdown-menu">
									<li><a href="viewCompanyList"><spring:message code="label.companyList"/></a></li>
									<li><a href="apprCustAdminList"><spring:message code="label.listPfCustAdmin"/></a></li>
									<li><a href="apprCustAppMngList"><spring:message code="label.listOfCustAppMng"/></a></li>
									<li><a href="apprCustomerHeadList"><spring:message code="label.listOfCustHead"/></a></li>
								     <li><a href="apprCustomerBranchList"><spring:message code="label.listOfCustBr/Subs"/></a></li>
								     <li><a href="wareHouseMngBankAppFullList"><spring:message code="label.addwhmngList"/></a></li>	
								     <li><a href="appSupplierPageList"><spring:message code="label.listOfSupplierApp"/></a></li>
								     <li><a href="appBuyerPageList"><spring:message code="label.listOfBuyerApp"/></a></li>
								     
								     <li><a href="appMasterPlanFullList"><spring:message code="label.listOfMps"/></a></li>
								      <li><a href="uploadDocumentList"><spring:message code="label.listOfUploadDocs"/></a></li>
									<%-- <li><a href="licenseOnRestrictedAppMngList"><spring:message code="label.listOfLicenseUpdate"/></a></li> --%>
								    <%--  <li><a href="bankEmpApprovList"><spring:message code="label.listOfBeApp"/></a></li> --%>
								     
								     	
								       
									<%-- <li><a href="customerSubsidiaryApprovalList">Customer Subsidiary</a></li> --%>
									
								     </ul>
								
							</li>
							<li class="dropdown"> <a href="#"><spring:message code="label.menuDetails"/></a>
								<ul class="dropdown-menu">
									
								     <!-- <li><a href="apprCustomerSubsidiaryList">List Of Customer Subsidiary</a></li> -->
								      <li><a href="appPODocumentList"><spring:message code="label.listOfPoDoc"/></a></li>
								    <li><a href="appMngExistingStockInvoiceList"><spring:message code="label.listOfSellingStock"/></a></li>
								      <li><a href="appMngExistingStockPoList"><spring:message code="label.listOfBuyingStock"/></a></li>
								      <%-- <li><a href="uploadDocumentList"><spring:message code="label.listOfUploadDocs"/></a></li> --%>
								    </ul>
							</li>
							<%-- <li class="dropdown"> <a href="#"><spring:message code="label.menuMasterPlan"/></a> --%>
							<li><a href="licenseOnRestrictedApp"><spring:message code="label.licenseDetails"/></a></li>
								<%-- <ul class="dropdown-menu">
									
									<li><a href="licenseOnRestrictedApp"><spring:message code="label.licenseDetails"/></a></li>
									
									
									</ul> --%>
							</li>
							<li class="dropdown"> <a href="#"><spring:message code="label.menuRepay"/></a>
								<ul class="dropdown-menu">
									<li><a href="masterPlanRePaymentAppMngList"><spring:message code="label.repayAppMngApp"/></a></li>
									<li><a href="requestedInvoiceAppMng"><spring:message code="label.invoiceLoanApp"/></a></li>
									<li><a href="requestMoneyInvoiceAppMngFullList"><spring:message code="label.listOfInvoiveLoan"/></a></li>
								      <li><a href="masterPlanRePaymentAppMngFullList"><spring:message code="label.listOfRepay"/></a></li>
									
									</ul>
							</li>
							
							<li class="dropdown"> <a href="#"><spring:message code="label.menuBp"/></a>
								<ul class="dropdown-menu">
									<!-- <li><a href="poListForApproval">Purchase Order</a></li>
									<li><a href="invoiceListForApproval">Invoice</a></li> -->
<%--										<li><a href="limitBurstRatesApprList"><spring:message code="label.limitBurstRate"/></a></li>--%>
									<li><a href="fullPoList"><spring:message code="label.listOfPos"/></a></li>
									<li><a href="fullInvoiceList"><spring:message code="label.listOfInvoice"/></a></li>
									<li><a href="inventoryAppList"><spring:message code="label.listOfImMng"/></a></li>
									<li><a href="inventoryInvoiceAppMngList"><spring:message code="label.listOfImMngSell"/></a></li>
									<li><a href="disputeAppMngList"><spring:message code="label.listOfDispute"/></a></li>
<%--								    <li><a href="limitBurstAppList"><spring:message code="label.listOfLimit"/></a></li>--%>
									<li><a href="poPaymentAppMngList"><spring:message code="label.listOfPoPyMents"/></a></li>
									<li><a href="poDocumnetListApproval"><spring:message code="label.listOfPoDocs"/></a></li>
									<li><a href="invoiceDocumnetListApproval"><spring:message code="label.listOfInvoiceDocs"/></a></li>
									
									</ul>
							</li>
					
					<li><a href="#"><spring:message code="label.menuReports"/></a>
			                         <ul class="dropdown-menu">
							<li><a href="reportsListAppMng"><spring:message code="label.repBuying"/></a></li>
							<li><a href="reportsListAppMngBying"><spring:message code="label.repSelling"/></a></li>
						</ul>
			
			</li>
			<li><a href="#"><spring:message code="label.insDetails"/></a>
			             <ul class="dropdown-menu">
							<li><a href="poInsFullAppList"><spring:message code="label.insList"/></a></li>
                              <li><a href="addOrModifyReportInvoiceAppFullList"><spring:message code="label.insInvoiceList"/></a></li>
						</ul>
			
			</li>
					<li><a href="#"><spring:message code="label.menuUs"/></a>
			<ul class="dropdown-menu">
							<li><a href="generalLedgerAppMng"><spring:message code="label.menuGl"/></a></li>
							<li><a href="snapShotAppMng"><spring:message code="label.menuSnp"/></a></li>
									<li><a href="transcationList"><spring:message code="label.menuTransDet"/></a></li>
									<li><a href="getPedingBlockUnblockUsers"><spring:message code="label.menuBlock&Un"/></a></li>
						<li><a href="swapAccountForApproval"><spring:message code="label.menuSwap&Revert"/></a></li>
							<li><a href="generateMasterPlanChart"><spring:message code="label.menuMpChart"/></a></li>
						</ul>
			
			</li>
					
						
					</ul>
					
</div>
</div>