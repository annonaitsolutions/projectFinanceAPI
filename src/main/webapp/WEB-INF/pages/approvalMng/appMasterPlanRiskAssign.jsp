
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3 align="center"><spring:message code="label.riskAssignment"/></h3>
	</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
						
							<table align="center">
							
							
									<tr>
										<td class="col-sm-6"><spring:message code="label.riskFactor"/></td>
										<td class="col-sm-6"><input type="text" value="" readonly="true"  id="companyName"></td>
									</tr>
									<tr>
										<td class="col-sm-6"><spring:message code="label.probabilityOfDefault"/></td>
										<td class="col-sm-6"><input type="text" value="" readonly="true"  id="companyName"></td>
									</tr>
									<tr>
										<td class="col-sm-6"><spring:message code="label.exposureAtDefault"/></td>
										<td class="col-sm-6"><input type="text" value="" readonly="true"  id="companyName"></td>
									</tr>
									<tr>
										<td class="col-sm-6"><spring:message code="label.lossGivenAtDefault"/></td>
										<td class="col-sm-6"><input type="text" value="" readonly="true"  id="companyName"></td>
									</tr>
							</table>
							<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							
							<td><a href="appMasterPlanDetails" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
</div>
</div>									