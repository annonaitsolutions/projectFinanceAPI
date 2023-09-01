<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
  
 
<div class="col-sm-3 col-md-3 vertical_menu_fixed" style="padding-left: 0px;">
<div id="menuwrapper">
<ul class="nav navbar-nav">
				
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="label.creteNew"/></a>
						<ul class="dropdown-menu">
							<li><a href="tfileUploadForm"><spring:message code="label.menuUploadDocs"/></a></li>
							<li><a href="addnewBuyerTrade"><spring:message code="label.buyerApp"/></a></li>
							<li><a href="newSupplierPageTrade"><spring:message code="label.supplierApp"/></a></li>
							 <li><a href="buyerTradeUserList"><spring:message code="label.listOfBuyerApp"/></a></li>
							<li><a href="supplierTradeUserList"><spring:message code="label.listOfSupplierApp"/></a></li>
								</ul>
					</li>
					
						<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="label.menuMasterPlan"/></a>
						<ul class="dropdown-menu">
							<li><a href="tmasterPlan"><spring:message code="label.createMp"/></a></li>
							<li><a href="tmasterPlanDraftsList"><spring:message code="label.menuDrafts"/></a></li>
							<li><a href="tmasterPlanPending"><spring:message code="label.menuColl"/></a></li>
							<li><a href="tmasterPlanDetails"><spring:message code="label.listOfMps"/></a></li>
							<li><a href="tmasterPlanAccept"><spring:message code="label.acceptMp"/></a></li>
							
							
						
						</ul>
					</li>
					<li><a href="#"><spring:message code="label.menuRepay"/></a>
								<ul class="dropdown-menu">
								<li><a href="tmasterPlanRePayment"><spring:message code="label.menuRepay"/></a></li>
									<li><a href="tmasterPlanRePaymentAcceptList"><spring:message code="label.acceptRepay"/></a></li>
							<li><a href="tmasterPlanRePaymentList"><spring:message code="label.listOfRepay"/></a></li>
								</ul>
								
							</li>
							<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="label.menuUs"/></a>
						<ul class="dropdown-menu">
							<li><a href="tradeShowMail"><spring:message code="label.menuMail"/></a></li>
									<li><a href="tradeShowCurrency"><spring:message code="label.menuCurrency"/></a></li>
									<li><a href="tradeShowcurrencyConversion"><spring:message code="label.menuCurrencyConversion"/></a></li>
						</ul>
					</li>
			</ul>
			</div>
			</div>