<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
  
 
<div class="col-sm-3 col-md-3 vertical_menu_fixed" style="padding-left: 0px;">
<div id="menuwrapper">
<ul class="nav navbar-nav">
				
						<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-file-text-o"></i>&nbsp;<spring:message code="label.menuMasterPlan"/>&nbsp;<i class="fa fa-angle-down"></i></a>
						<ul class="dropdown-menu">
						<li><a href="tclientMasterPlanPendingDetails"><spring:message code="label.mpAppMngMenu"/></a></li>
						<li><a href="tmasterPlanClientFullList"><spring:message code="label.listOfMps"/></a></li>	
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="label.menuDetails"/></a>
						<ul class="dropdown-menu">
			
									<li><a href="buyerCustomerTradeList"><spring:message code="label.listOfBuyerApp"/></a></li>
									<li><a href="supplierCustomerTradeList"><spring:message code="label.listOfSupplierApp"/></a></li>
									
									</ul>
							</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-file-text-o"></i>&nbsp;<spring:message code="label.menuRepay"/>&nbsp;<i class="fa fa-angle-down"></i></a>
						<ul class="dropdown-menu">
						<li><a href="tmasterPlanRePaymentAppList"><spring:message code="label.repayAppMngApp"/></a></li>
						<li><a href="tmasterPlanRePaymentFullList"><spring:message code="label.listOfRepay"/></a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="label.menuUs"/></a>
						<ul class="dropdown-menu">
							<li><a href="tradeAppMngShowMail"><spring:message code="label.menuMail"/></a></li>
									<li><a href="tradeAppMngShowCurrency"><spring:message code="label.menuCurrency"/></a></li>
									<li><a href="tradeAppMngShowcurrencyConversion"><spring:message code="label.menuCurrencyConversion"/></a></li>
						</ul>
					</li>
				
			</ul>
			</div>
			</div>