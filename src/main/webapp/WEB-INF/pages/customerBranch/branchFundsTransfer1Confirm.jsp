<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<script type="text/javascript">
	function validateForm() {

	}
</script>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3><spring:message code="label.requestForFunds"/></h3>
	</div>
	<div  class="sucess"><b><font color="green">${success}</font></b></div>
<form:form action="branchFundsTransferPost" name="regulation" commandName="fundsDistributeForm" onsubmit="return validateForm()">

	 
								<table align="center">
								<form:hidden path="id"  />
								<form:hidden path="custHeadEmail"  />
								<form:hidden path="custHeadMngEmail"  />
								<form:hidden path="email"  />
								<form:hidden path="managerEmail"  />
								<form:hidden path="transactionId" value="" />
								<form:hidden path="balance"  />
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerName"/>:</b></td>
										<td class="col-sm-6"><form:input path="customerName" placeholder="Enter name" readonly="true" ></form:input>
																								
									</tr>
									
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerHeadName"/>:</b></td>
										<td class="col-sm-6"><form:input path="customerHeadName" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.masterKey"/>:</b></td>
										<td class="col-sm-6"><form:input path="masterKey" readonly="true" placeholder="Enter Company Name" id="companyName"></form:input>
																									
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.distAmt"/>:</b></td>
										<td class="col-sm-6"><form:input path="distributedAmount" readonly="true" placeholder="Enter Company Name" id="companyName"></form:input>
																									
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.utilizedAmount"/>:</b></td>
										<td class="col-sm-6"><form:input path="utilizedAmount" readonly="true" placeholder="Enter Company Name" id="companyName"></form:input>
																									
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.availableAmount"/>:</b></td>
										<td class="col-sm-6"><form:input path="busBalance" readonly="true" placeholder="Enter Amount" id="companyName"></form:input>
									
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.amount"/>:</b>
										</td>
										<td class="col-sm-6"><form:input path="amount" readonly="true"  placeholder="Enter Amount" id="amount"></form:input>
									
									</tr>
								
									</table>
										<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="branchFundsTransfer" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
		
</form:form>

</div>
