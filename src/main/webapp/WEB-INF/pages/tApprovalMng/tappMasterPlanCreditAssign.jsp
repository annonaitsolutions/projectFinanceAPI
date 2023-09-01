<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center"><spring:message code="label.creditAssignment"/></h3>
		</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
			<form:form commandName="tMasterPlanForm" onsubmit="return val();">
								<table align="center">
								<form:hidden path="id" />
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.buyingCost"/>:</b></td>
										<td class="col-sm-6"><form:input path="buyingCost" value="${model.tMasterPlanForm.buyingCost}" placeholder="Enter cost" readonly="true"></form:input>
																								
									</tr>
									
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.tenure"/>:</b></td>
										<td class="col-sm-6"><form:input path="tenure" value="${model.tMasterPlanForm.tenure}" readonly="true" placeholder="Enter tenure" id="customerPrefix"></form:input>
											
									</tr>
								<tr>
										<td class="col-sm-6"><b><spring:message code="label.sanctionedBuyingAmount"/>:</b></td>
										<td class="col-sm-6"><form:input path="buyingCostSanc" value="${model.tMasterPlanForm.buyingCostSanc}" readonly="true" placeholder="Enter amount" id="customerPrefix"></form:input>
											
									</tr>
		
							</table>
							</form:form> 
		 </div>		
						<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							
							<td><a href="tappMasterPlanDetails" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
									
</div>