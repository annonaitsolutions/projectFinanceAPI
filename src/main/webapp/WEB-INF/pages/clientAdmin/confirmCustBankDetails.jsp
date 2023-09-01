<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
	function validateForm() {
		/* var d = new Date().getTime();
		var uuid = 'xxxxxx'.replace(/[xy]/g, function(c) {
			var r = (d + Math.random() * 16) % 16 | 0;
			d = Math.floor(d / 16);
			return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
		});
		document.customerBankDetails.transactionId.value = uuid; */
	}
</script>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
 <div class="col-sm-12 col-md-12 col-lg-12 header_customer">			
				<h3><spring:message code="label.confirmationScreen"/></h3>
		</div>
				<form:form name="customerBankDetails" action="insertCustBankDetails"
					commandName="customerBankDetailsForm" onsubmit="return validateForm()">
					<table align="center">
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.bankName"/></b></td>
							<td class="col-sm-6"><form:input path="bankName" id="bankName"
									value="${model.customerBankDetailsForm.bankName}" readonly="true" />
						</tr>

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.location"/></b></td>
							<td class="col-sm-6"><form:input path="location"
									value="${model.customerBankDetailsForm.location}" readonly="true" /></td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.branch"/></b></td>
							<td class="col-sm-6"><form:input path="branch"
									value="${model.customerBankDetailsForm.branch}" readonly="true" /></td>

						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.branchCode"/></b></td>
							<td class="col-sm-6"><form:input path="branchCode"
									value="${model.customerBankDetailsForm.branchCode}" readonly="true" /></td>

						</tr>

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.contactNumber"/></b></td>
							<td class="col-sm-6"><form:input path="contactNo"
									value="${model.customerBankDetailsForm.contactNo}" readonly="true" /></td>

						</tr>

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.email"/></b></td>
							<td class="col-sm-6"><form:input path="email"
									value="${model.customerBankDetailsForm.email}" readonly="true" /></td>
							<form:hidden path="transactionId" value="" />
						</tr>
						
					</table>

					<div class="col-sm-12 col-md-12">
						<table align="center">
							<tr>
								<td class="col-sm-8"><input type="submit" size="3"
									value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
								<td><a href="custBankDetails" class="btn btn-success"><spring:message code="label.back"/></a></td>
							</tr>
						</table>
					</div>

				</form:form>
			</div>