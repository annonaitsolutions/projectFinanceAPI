<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
  
 
<div class="col-sm-3 col-md-3 vertical_menu_fixed" style="padding-left: 0px;">
<div id="menuwrapper">

<ul class="nav navbar-nav">
				
						
							<li><a href="disputeMngList"><spring:message code="label.disApproval"/></a></li>
							<li><a href="disputeMngFullList"><spring:message code="label.disputeList"/></a></li>
							<li><a href="custWareHouseInvoiceList"><spring:message code="label.goodsDeliverytoBuyer"/></a></li>
							<li class="dropdown-submenu"> <a tabindex="-1" href="#"><spring:message code="label.menuUs"/></a>
								<ul class="dropdown-menu">
									
									<li><a href="showWhMngMail"><spring:message code="label.menuMail"/></a></li>
								</ul>
							</li>
							
						</ul>
						
						</div>
						</div>