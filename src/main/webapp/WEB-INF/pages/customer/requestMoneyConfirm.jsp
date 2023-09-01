<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<section>
	<script type="text/javascript">
	function validateForm() {
		/* var d = new Date().getTime();
		var uuid = 'xxxxxx'.replace(/[xy]/g, function(c) {
			var r = (d + Math.random() * 16) % 16 | 0;
			d = Math.floor(d / 16);
			return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
		});
		document.approveCustomerHeadSave.transactionId.value = uuid; */
	
}
</script>	
<form:form action="requestMoneyPost" name="approveCustomerHeadSave" commandName="requestMoneyForm" onsubmit="return validateForm();">
<div class="heading"><spring:message code="label.confirmationScreen"/></div>
			<div class="content">
								<table class="theader">
								<form:hidden path="id"  />
								<form:hidden path="transactionId" value="" />
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.requestBy"/>:</b></td>
										<td class="col-sm-8"><form:input path="requestedBy" placeholder="Enter name" readonly="true" style="width:85%" ></form:input>
																								
									</tr>
									
									
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.requestTo"/>:</b></td>
										<td class="col-sm-8"><form:input path="requestedFrom" readonly="true" placeholder="Enter contact number" id="customerPrefix" style="width:85%"></form:input>
											
									</tr>
									
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.masterKey"/>:</b></td>
										<td class="col-sm-8"><form:input path="masterKey" readonly="true" placeholder="Enter Company Name" id="companyName" style="width:85%"></form:input>
																									
									</tr>
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.availableAmount"/>:</b></td>
										<td class="col-sm-8"><form:input path="availAmount" readonly="true" placeholder="Enter Amount" id="companyName" style="width:85%"></form:input>
									
									</tr>
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.amount"/>:</b></td>
										<td class="col-sm-8"><form:input path="amount" readonly="true"   placeholder="Enter Amount" id="companyName" style="width:85%"></form:input>
									
									</tr>
									
											<p align="center" style="padding-top: 10px;">
					<input type="submit" class="btn btn-primary" value="Confirm">
					
					<a href="bankEmp" class="btn btn-success" name="back"  onclick="javascript:window.location='#';"/>back</a>
				</p>
									</table>
									</div>
								


</form:form>

</section>

