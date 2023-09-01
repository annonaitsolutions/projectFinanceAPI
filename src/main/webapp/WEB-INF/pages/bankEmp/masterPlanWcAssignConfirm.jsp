<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>



 <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

 	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
 			<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
 	</div>
	<form:form action="masterPlanWcAssignPost" commandName="masterPlanForm" onsubmit="return val();">

			<div class="col-sm-12 col-md-12 col-lg-12">
								<table align="center">
								<form:hidden path="id"  />
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.workingCapitalAmount"/></b></td>
										<td class="col-sm-6"><form:input path="workingCapital" placeholder="Enter cost" readonly="true"></form:input>
																								
									</tr>
									
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.tenure"/></b></td>
										<td class="col-sm-6"><form:input  path="WcTenure" readonly="true" placeholder="Enter tenure" id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.sanctionedWcAmount"/></b></td>
										<td class="col-sm-6"><form:input path="wcSancAmount" readonly="true" placeholder="Enter tenure" id="customerPrefix"></form:input>
											
									</tr>

									<tr>
										<td class="col-sm-6"><b><spring:message code="label.interestCharge"/></b></td>
										<td class="col-sm-6"><form:input path="wcSancInterest" readonly="true" placeholder="Enter Interest" id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.totalInterestCharge"/></b></td>
										<td class="col-sm-6"><form:input path="wcTotalInterest" readonly="true" placeholder="Enter Interest" id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.totalAmount"/></b></td>
										<td class="col-sm-6"><form:input path="wcTotalAmount" readonly="true" placeholder="Enter Interest" id="customerPrefix"></form:input>
						
								
									<form:hidden path="id"  />
									<form:hidden path="transactionId"  />
				
									</table>
									
									</div>
									<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="bankMasterPlanDetails" class="btn btn-success"><spring:message code="label.back"/></a></td>
							
					
						</tr>
					</table>
						


</form:form>


<script>
    $(document).ready( function () {
        $('#bootstrap-table').bdt();
    });
</script>
</div>

