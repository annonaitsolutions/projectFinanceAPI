<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
  
 
<div class="col-sm-3 col-md-3 vertical_menu_fixed" style="padding-left: 0px;">
<div id="menuwrapper">
<ul class="nav navbar-nav">

					<li><a href="loginDate">Login Date</a></li>
                    <li><a href="bankDetails"><spring:message code="label.bankDe"/></a></li>
					<li><a href="createRole"><spring:message code="label.createBankUser"/> </a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">
<%--						<i class="fa fa-desktop"></i>--%>
						&nbsp;<spring:message code="label.listOfBUser"/> &nbsp;
						<i class="fa fa-angle-down"></i>
					</a>
						<ul class="dropdown-menu">
							<li><a href="bankemployeeList"><spring:message code="label.listOfBe"/></a></li>
							<li><a href="approvalManagerList"><spring:message code="label.listOfAm"/></a></li>

						</ul></li>
					
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">
<%--						<i class="fa fa-file-text-o"></i>&nbsp;--%>
						<spring:message code="label.notif"/>
							&nbsp;<i class="fa fa-angle-down"></i></a>
						<ul class="dropdown-menu">
							<li><a href="bankEmpList"><spring:message code="label.be"/></a></li>
							<li><a href="appMngList"><spring:message code="label.am"/></a></li>
						</ul></li>
<%--					<li><a href="regulationPage"><spring:message code="label.regu"/></a></li>--%>
					
					

					
					
					<li><a href="getUsersForBlockUnblockRenew"><spring:message code="label.blockOrRenewAcc"/></a></li>
<%--					<li><a href="usersForSwapRevert"><spring:message code="label.swapOrRewertAcc"/></a></li>--%>
					<li><div class="hidden"><a href="generateMasterPlanChart"><spring:message code="label.mpChart"/></a></li>
					</ul>
					</div>
					</div>