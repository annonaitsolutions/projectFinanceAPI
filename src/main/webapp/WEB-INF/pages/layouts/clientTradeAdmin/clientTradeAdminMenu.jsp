<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<div class="col-sm-3 col-md-3 vertical_menu_fixed"
	style="padding-left: 0px;">
	<div id="menuwrapper">

		<ul class="nav navbar-nav">
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"><i class="fa fa-file-text-o"></i>&nbsp;<spring:message
						code="label.creteNew" /> &nbsp;<i class="fa fa-angle-down"></i></a>
				<ul class="dropdown-menu">
					<li><a href="tCreateClientAppMng"><spring:message
								code="label.creteAppMng" /></a></li>
					<li><a href="tCreateCustomerHead"><spring:message
								code="label.custHeadApp" /></a></li>
								<li><a href="tBankCustomerHeadList"><spring:message code="label.listOfCustHead"/></a></li>
							<li><a href="tClientAppMngFullList"><spring:message code="label.listOfCustAppMng"/></a></li>
				</ul></li>
				

			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"><i class="fa fa-file-text-o"></i>&nbsp;<spring:message
						code="label.menuDetails" /> &nbsp;<i class="fa fa-angle-down"></i></a>
				<ul class="dropdown-menu">
					<li><a href="tAdminMasterPlanFullList"><spring:message
								code="label.listOfMps" /></a></li>
					<li><a href="tmasterPlanRePaymentAdminList"><spring:message
								code="label.listOfRepay" /></a></li>
				</ul></li>
		</ul>
	</div>
</div>
