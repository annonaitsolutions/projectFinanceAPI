<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<div class="col-sm-3 col-md-3 vertical_menu_fixed"
	style="padding-left: 0px;">
	<div id="menuwrapper">
		<ul class="nav navbar-nav">
			<li><a href="#"><spring:message code="label.creteNew" />&nbsp;<i
					class="fa fa-caret-right" style="left: 38px;"></i></a>
				<ul>
					<li><a href="tCreateClientAdmin"><spring:message
								code="label.custAdminApp" /></a></li>
					<li><a href="tCreateCompany"><spring:message
								code="label.company" /></a></li>
				</ul></li>


			<li><a href="#"><spring:message code="label.menuMasterPlan" />&nbsp;<i
					class="fa fa-caret-right"></i></a>
				<ul>
					<li><a href="tbankMasterPlanPendingDetails"><spring:message
								code="label.mpAppMngMenu" /></a></li>
					<li><a href="tbankMasterPlanDetails"><spring:message
								code="label.cr/wcAss" /></a></li>
					<li><a href="tmasterPlanFullList"><spring:message
								code="label.listOfMps" /></a></li>
				</ul></li>
			<li><a href="#"><spring:message code="label.menuRepay" /></a>
				<ul>
					<li><a href="tmasterPlanRePaymentBank"><spring:message
								code="label.menuRepay" /></a></li>
					<li><a href="tmasterPlanRePaymentMadeList"><spring:message
								code="label.repayClear" /></a></li>
					<li><a href="tmasterPlanRePaymentBankList"><spring:message
								code="label.listOfRepay" /></a></li>
				</ul></li>

			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"><spring:message code="label.details" /></a>
				<ul class="dropdown-menu">

					<li><a href="buyerTradeBankList"><spring:message
								code="label.listBuyer" /></a></li>
					<li><a href="supplierTradeBankList"><spring:message
								code="label.listSupplier" /></a></li>
				</ul></li>
			<li><a href="#"><spring:message code="label.menuUs" /></a>
				<ul class="dropdown-menu">
					<li><a href="tradeBankTransactionList"><spring:message
								code="label.menuTransDet" /></a></li>
				</ul></li>

		</ul>
	</div>
</div>