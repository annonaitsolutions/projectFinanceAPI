<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
  
 
<div class="col-sm-3 col-md-3 vertical_menu_fixed" style="padding-left: 0px;">
<div id="menuwrapper">

<ul class="nav navbar-nav">
				
						
							
							<li class="dropdown-submenu"> <a tabindex="-1" href="#"><spring:message code="label.menuDetails"/></a>
								<ul class="dropdown-menu">
									
									<li><a href="disputeSuppList"><spring:message code="label.listOfDispute"/></a></li>
								</ul>
							</li>
							<li><a href="custWareHouseVendorList"><spring:message code="label.goodsDel"/></a></li>
							<li class="dropdown-submenu"> <a tabindex="-1" href="#"><spring:message code="label.menuPo"/></a>
								<ul class="dropdown-menu">
										<li><a href="vPoListForApproval"><spring:message code="label.poApproval"/></a></li>
										<li><a href="vPoListForRenewel"><spring:message code="label.insRenewal"/></a></li>
									<li><a href="vendorsPoList"><spring:message code="label.listOfPos"/></a></li>
									<li><a href="vendorPoApprovalList"><spring:message code="label.uploadPoDocs"/></a></li>
									<li><a href="vendorUploadedDocumentList"><spring:message code="label.listOfPoDocs"/></a></li>
									<li><a href="poPaymentVendorFullList"><spring:message code="label.listOfPoPyMents"/></a></li>
								</ul>
							</li>
						<li class="dropdown-submenu"> <a tabindex="-1" href="#"><spring:message code="label.menuPay"/></a>
								<ul class="dropdown-menu">
										<li><a href="invoicePaymentVendorList"><spring:message code="label.menuInvoicePay"/></a></li>
									<li><a href="requestMoneyInvoiceVendorFullList"><spring:message code="label.invoiceMnyPayList"/></a></li>
								
								</ul>
							</li>
							<li class="dropdown-submenu"> <a tabindex="-1" href="#"><spring:message code="label.menuDisputeRep"/></a>
								<ul class="dropdown-menu">
										<li><a href="addOrModifyReportVendorList"><spring:message code="label.acceptorAddRep"/></a></li>
									<li><a href="addOrModifyReportVendorFullList"><spring:message code="label.viewOrCompare"/></a></li>
								
								</ul>
							</li>
						</ul>
						
						</div>
						</div>