<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
  
 
<div class="col-sm-3 col-md-3 vertical_menu_fixed" style="padding-left: 0px;">
	<div id="menuwrapper">
		<ul class="nav navbar-nav">
						<li class="dropdown"><a href="#" class="dropdown-toggle" tabindex="-1"
						data-toggle="dropdown"><spring:message code="label.creteNew"/>
							&nbsp;<i class="fa fa-caret-right"></i></a>
							<ul class="dropdown-menu">
									<li><a href="purchaseOrder"><spring:message code="label.menuPo"/></a></li>
									<li><a href="custWareHouseBuyerList"><spring:message code="label.goodsReceive"/></a></li>
									<li><a href="buyerPoList"><spring:message code="label.listOfPos"/></a></li>
									
							</ul>
						</li>
						
						 <li><a href="#" tabindex="-1"><spring:message code="label.menuDetails"/>&nbsp;<i class="fa fa-caret-right" style="left:38px;"></i></a>
          			    <ul>
                          	 <li><a href="buyerInvoiceList"><spring:message code="label.listOfInvoice"/></a></li>
							 
						</ul>
                         </li>
			<li class="dropdown-submenu"> <a tabindex="-1" href="#"><spring:message code="label.menuPay"/></a>
				<ul class="dropdown-menu">
					<li><a href="invoicePaymentBuyerList"><spring:message code="label.menuInvoicePay"/></a></li>
					<li><a href="requestMoneyInvoiceBuyerFullList"><spring:message code="label.invoiceMnyPayList"/></a></li>

				</ul>
			</li>

                         <li><a href="#" tabindex="-1"><spring:message code="label.insDetails"/>&nbsp;<i class="fa fa-caret-right" style="left:38px;"></i></a>
          			    <ul>
                          	 <li><a href="invoiceDisputeReports"><spring:message code="label.addReport"/></a></li>
							 <li><a href="addOrModifyBuyerReportList"><spring:message code="label.viewOrCompare"/></a></li>
						</ul>
                         </li>
				
		</ul>
	</div>
</div>