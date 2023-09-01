<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
  
 
<div class="col-sm-3 col-md-3 vertical_menu_fixed" style="padding-left: 0px;">
<div id="menuwrapper" class="scrollbar">
  		<ul>
            <li><a href="#"><spring:message code="label.creteNew"/>&nbsp;<i class="fa fa-caret-right" style="left:38px;"></i></a>
            <ul>
                            <li><a href="createCompany"><spring:message code="label.customerCompany"/></a></li>
                            <li><a href="createClientAdmin"><spring:message code="label.custAdminApp"/></a></li>
							
						</ul>
            
            
            </li>
            <li><a href="#"><spring:message code="label.menuMasterPlan"/>&nbsp;<i class="fa fa-caret-right"></i></a>
               <ul>
							<li><a href="bankMasterPlanPendingDetails"><spring:message code="label.approveMsp"/></a></li>
							<li><a href="bankMasterPlanDetails"><spring:message code="label.cr/wcAss"/></a></li>
						    
						</ul>
            </li>
            
            <li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="label.displayList"/>&nbsp;<i class="fa fa-caret-right" style="left: 105px;"></i></a>
				<ul class="dropdown-menu">
				<li><a href="companyList"><spring:message code="label.companyList"/></a></li> 
				<li><a href="custAdminList"><spring:message code="label.listPfCustAdmin"/></a></li>
				<li><a href="clientAppMngFullList"><spring:message code="label.listOfCustAppMng"/></a></li>
				<li><a href="bankCustomerHeadList"><spring:message code="label.listCustomerHead"/></a></li>
				<li><a href="supplierPageList"><spring:message code="label.listOfSupplierApp"/></a></li>
				<li><a href="buyerPageList"><spring:message code="label.listBuyer"/></a></li>
				<li><a href="wareHouseMngBankFullList"><spring:message code="label.addwhmngList"/></a></li>
				<li><a href="masterPlanFullList"><spring:message code="label.listOfMps"/></a></li>
				<li><a href="banlFullPoList"><spring:message code="label.listOfPos"/></a></li>
				<li><a href="BankFullInvoiceList"><spring:message code="label.listOfInvoice"/></a></li>
				<li><a href="buyerDocumentList"><spring:message code="label.buyerDocuments"/></a></li>
				 <li><a href="poDocumentListAll"><spring:message code="label.listOfPoDocs"/></a></li> 
			     <li><a href="inventoryBankList"><spring:message code="label.listOfBuyingInv"/></a></li>
				<li><a href="inventoryInvoiceBankList"><spring:message code="label.listOfSellingInv"/></a></li>
				 <li><a href="bankExistingStockPoList"><spring:message code="label.listOfBuyingStock"/></a></li>
				<li><a href="bankExistingStockInvoiceList"><spring:message code="label.listOfSellingStock"/></a></li>
				 <li><a href="disputeBankList"><spring:message code="label.listOfDispute"/></a></li> 
                     
				    
					<%--<li><a href="bankCustomerBranchList"><spring:message code="label.listCustomerBranch"/></a></li>
					<li><a href="bankCustomerSubsidiaryList"><spring:message code="label.listCustomerSubsidiary"/></a></li>
					
					
					<li><a href="limitBurstBankList"><spring:message code="label.listOfLimit"/></a></li>
                     <li><a href="invoiceDocumnetListAll"><spring:message code="label.listOfInvoiceDocs"/></a></li> 
                    <li><a href="PoBankEmpList"><spring:message code="label.menuUploadDocs"/></a></li>
					<li><a href="companyList"><spring:message code="label.companyList"/></a></li> --%>


				</ul>
            </li>
            
            
             <li><a href="#"><spring:message code="label.menuPay"/>&nbsp;<i class="fa fa-caret-right"></i></a>
             			
             			<ul>
							<li><a href="#"><spring:message code="label.menuRepay"/></a>
								<ul>
									<li><a href="masterPlanRePaymentBank"><spring:message code="label.menuRepay"/></a></li>
									<li><a href="masterPlanRePaymentMadeList"><spring:message code="label.repayClear"/></a></li>
									<li><a href="masterPlanRePaymentBankList"><spring:message code="label.listOfRepay"/></a></li>
								</ul>
							</li>
							<li><a href="#"><spring:message code="label.menuPoPay"/></a>
								<ul>
									<li><a href="poPaymentClearList"><spring:message code="label.menuPoPayClear"/></a></li>
									<li><a href="poPaymentBankList"><spring:message code="label.listOfPoPyMents"/></a></li>
									<li><a href="poMultiplePaymentList"><spring:message code="label.multiplePayment"/></a></li>
								</ul>
							</li>
						</ul>
            </li>
           <%--  <li><a href="#"><spring:message code="label.notif"/>&nbsp;<i class="fa fa-caret-right"></i></a>
             	 <ul>
					<li><a href="bankCustomerHeadNotification"><spring:message code="label.custHeadApp"/></a></li>
					<li><a href="bankCustomerBranchNotification"><spring:message code="label.custBr/SubsApp"/></a></li>
					<!-- <li><a href="bankCustomerSubsidiaryNotification">Customer Subsidiary</a></li> -->					
				</ul>
			</li> --%>


	 
        
			<li><a href="#"><spring:message code="label.bankSetRates"/>&nbsp;<i class="fa fa-caret-right"></i></a>
           		<ul>
					<!-- <li><a href="rateList">Po List</a></li>
							<li><a href="rateInvoiceList">Invoice List</a></li>-->
<%--							<li><a href="setLimitBurstRatesList"><spring:message code="label.menuLimit"/></a></li>--%>
							<li><a href="requestedInvoiceBank"><spring:message code="label.menuInvoice"/></a></li>
							<li><a href="requestMoneyCustAccepted"><spring:message code="label.invoiceFundsClear"/></a></li>
							<li><a href="invoicePaymentForClear"><spring:message code="label.bPayClear"/></a></li>
							<li><a href="requestMoneyInvoiceBankFullList"><spring:message code="label.invoiceMnyPayList"/></a></li>
					
				</ul>
				
			<li><a href="#"><spring:message code="label.poEvents"/>&nbsp;<i class="fa fa-caret-right"></i></a>
			<ul class="dropdown-menu">
			
							<li ><a tabindex="-1" href="viewAllEvents3"><spring:message code="label.menuSb"/></a></li>
									<li ><a tabindex="-1" href="buyerDiffViewAllEvents3"><spring:message code="label.menuDb"/></a></li>
								<li><a href="masterKeyApprovedList"><spring:message code="label.menuGraph"/></a></li>
									<!-- <li><a href="masterKeyApprovedDateList">Time Exposure
											Graph</a></li> -->
							<li><a href="comparisonBankPO"><spring:message code="label.menuComparison"/></a></li>
						</ul>
			
			</li>
			<li><a href="#"><spring:message code="label.invoiceEvents"/>&nbsp;<i class="fa fa-caret-right"></i></a>
              <ul class="dropdown-menu">

							<li><a tabindex="-1" href="sellerSameViewAllEvents3"><spring:message code="label.menuSb"/></a></li>
									<li><a tabindex="-1" href="sellerDiffViewAllEvents3"><spring:message code="label.menuDb"/></a></li>
								<!-- <li><a href="masterKeyApprovedSellercostList">Cost Exposure
											Graph</a></li> -->
									<li><a href=masterKeyApprovedSellerList><spring:message code="label.menuGraph"/></a></li>
								   <li><a href="comparisonBankInvoice"><spring:message code="label.menuComparison"/></a></li>
								
								</ul>
         </li>
			<li><a href="#"><spring:message code="label.menuRatings"/></a>
			<ul class="dropdown-menu">
							<li><a href="supplierRatingList"><spring:message code="label.supplierApp"/></a></li>
							<li><a href="buyerRatingList"><spring:message code="label.buyerApp"/></a></li>
						</ul>
			
			</li>
			<li><a href="#"><spring:message code="label.menuReports"/></a>
			<ul class="dropdown-menu">
							<li><a href="reportsListBank"><spring:message code="label.repBuying"/></a></li>
							<li><a href="reportsListBankBying"><spring:message code="label.repSelling"/></a></li>
						</ul>
			
			</li>
			<li><a href="#"><spring:message code="label.insDetails"/></a>
			             <ul class="dropdown-menu">
							<li><a href="poInsFullBankList"><spring:message code="label.insList"/></a></li>
                        <li><a href="addOrModifyReportInvoiceBankFullList"><spring:message code="label.insInvoiceList"/></a></li>
						</ul>
			
			</li>
			<li><a href="#"><spring:message code="label.menuUs"/></a>
			             <ul class="dropdown-menu">
							<li><a href="generalLedgerBank"><spring:message code="label.menuGl"/></a></li>
							<li><a href="snapShotBank"><spring:message code="label.menuSnp"/></a></li>
							<li><a href="getUsersForBlockUnblock"><spring:message code="label.menuBlock&Un"/></a></li>
			                <li><a href="generateMasterPlanChart"><spring:message code="label.menuMpChart"/></a></li>	
			                <li><a href="masterPlanStatusDetails"><spring:message code="label.menuStatusTable"/></a></li>
			                <li><a href="bankTransactionList"><spring:message code="label.menuTransDet"/></a></li>
						</ul>
			
			</li>
			
			
			
			
							
			
		</ul>
        </div>
 </div>
