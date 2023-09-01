<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<div class="col-sm-3 col-md-3 vertical_menu_fixed"
	style="padding-left: 0px;">
	<div id="menuwrapper">
		<ul class="nav navbar-nav">

			<li class="dropdown"><a href="#"><spring:message
						code="label.menuMasterPlan" /></a>
				<ul class="dropdown-menu">
					<li><a href="tappMasterPlanDetails"><spring:message
								code="label.mpAppMngMenu" /></a></li>
					<li><a href="tappMasterPlanFullList"><spring:message
								code="label.listOfMps" /></a></li>
				</ul></li>

			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"><spring:message code="label.Approvals" /></a>
				<ul class="dropdown-menu">

					<li><a href="buyerCustomerTradeApprovalList"><spring:message
								code="label.buyerApp" /></a></li>
					<li><a href="supplierCustomerTradeApprovalList"><spring:message
								code="label.supplierApp" /></a></li>
					<li><a href="custAdminTradeApprovalList"><spring:message
								code="label.custAdminApp" /></a></li>
					<li><a href="tradeCompanyList"><spring:message
								code="label.company" /></a></li>			
					<li><a href="buyerCustomerTradeListPage"><spring:message
								code="label.listOfBuyerApp" /></a></li>
					<li><a href="supplierCustomerTradeListPage"><spring:message
								code="label.listOfSupplierApp" /></a></li>
				</ul></li>

			<li class="dropdown"><a href="#"><spring:message
						code="label.menuRepay" /></a>
				<ul class="dropdown-menu">
					<li><a href="tmasterPlanRePaymentAppMngList"><spring:message
								code="label.repayAppMngApp" /></a></li>
					<li><a href="tmasterPlanRePaymentAppMngFullList"><spring:message
								code="label.listOfRepay" /></a></li>
				</ul></li>
			<li><a href="#"><spring:message code="label.menuUs" /></a>
				<ul class="dropdown-menu">
					<li><a href="tradeAppMngTransactionList"><spring:message
								code="label.menuTransDet" /></a></li>
				</ul></li>

		</ul>
	</div>
</div>