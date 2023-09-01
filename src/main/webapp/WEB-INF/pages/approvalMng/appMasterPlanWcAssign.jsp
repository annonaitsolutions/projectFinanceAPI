<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">	
		<h3 align="center"><spring:message code="label.wcAssignment"/></h3>
	</div>
<form:form commandName="masterPlanForm" onsubmit="return val();">

			<div class="col-sm-12 col-md-12 col-lg-12">
								<table align="center">

									<tr>
										<td class="col-sm-6"><b><spring:message code="label.workingCapital"/></b></td>
										<td class="col-sm-6"><form:input path="workingCapital" value="${model.masterPlanForm.workingCapital}"  readonly="true"></form:input>
																								
									</tr>
									
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.tenure"/></b></td>
										<td class="col-sm-6"><form:input path="wcTenure" value="${model.masterPlanForm.wcTenure}"  readonly="true"  id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.sanctionedAmount"/></b></td>
										<td class="col-sm-6"><form:input path="wcSancAmount" value="${model.masterPlanForm.wcSancAmount}"  readonly="true"  id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.interestCharge"/></b></td>
										<td class="col-sm-6"><form:input path="wcSancInterest" value="${model.masterPlanForm.wcSancInterest}" readonly="true"  id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.totalInterestAmount"/></b></td>
										<td class="col-sm-6"><form:input path="wcTotalInterest" value="${model.masterPlanForm.wcTotalInterest}" readonly="true"  id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.totalAmount"/></b></td>
										<td class="col-sm-6"><form:input path="wcTotalAmount" value="${model.masterPlanForm.wcTotalAmount}" readonly="true"  id="customerPrefix"></form:input>
						
								
									<form:hidden path="id"  />
								</td>
								</tr>
								</table>
								</div>
									<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							
							<td><a href="appMasterPlanDetails" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>


</form:form>
</div>

