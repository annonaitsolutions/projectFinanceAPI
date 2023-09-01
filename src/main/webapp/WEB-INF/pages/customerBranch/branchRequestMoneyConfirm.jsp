<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<section>
	<script type="text/javascript">
	function validateForm() {

	
}
</script>	
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-16 col-md-16 col-lg-16 header_customer">
		<h3><spring:message code="label.confirmationScreen"/></h3>
	</div>
<form:form action="branchRequestMoneyPost" name="approveCustomerHeadSave" commandName="requestMoneyForm" onsubmit="return validateForm();">

			<div class="content">
								<table align="center">
								<form:hidden path="id"  />
								<form:hidden path="customerEmail"  />
						
								<form:hidden path="transactionId" value="" />
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.requestBy"/>:</b></td>
										<td class="col-sm-6"><form:input path="requestedBy" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
									
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.requestTo"/>:</b></td>
										<td class="col-sm-6"><form:input path="requestedFrom" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.masterKey"/>:</b></td>
										<td class="col-sm-6"><form:input path="masterKey" readonly="true" placeholder="Enter Company Name" id="companyName"></form:input>
																									
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.availableAmount"/>:</b></td>
										<td class="col-sm-6"><form:input path="availAmount" readonly="true" placeholder="Enter Amount" id="companyName"></form:input>
									
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.amount"/>:</b></td>
										<td class="col-sm-6"><form:input path="amount" readonly="true"   placeholder="Enter Amount" id="companyName"></form:input>
									
									</tr>
							
									</table>
											<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="branchFundsRequest" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
									</div>
								


</form:form>

</div>
