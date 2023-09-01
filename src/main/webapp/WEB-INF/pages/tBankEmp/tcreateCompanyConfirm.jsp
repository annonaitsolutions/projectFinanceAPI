<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@include file="taglib_includes.jsp"%>

<script type="text/javascript">
	function validateForm() {
		var d = new Date().getTime();
		var uuid = 'xxxxxx'.replace(/[xy]/g, function(c) {
			var r = (d + Math.random() * 16) % 16 | 0;
			d = Math.floor(d / 16);
			return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
		});
		document.createCompanySave.transactionId.value = uuid;
	}
</script>

<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
	<form:form action="tCreateCompanySave" name="createCompanySave"
		method="post" commandName="companyForm" onsubmit="return validateForm();">
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center">
				<spring:message code="label.confirmationScreen" />
			</h3>
		</div>


		<div class="content">

			<table class="theader broad">
				<caption>
					<spring:message code="label.companyDetails" />
				</caption>
				<tr class="rowcolor">
					<td class="col-sm-6"><b><spring:message
								code="label.companyName" /></b></td>
					<td class="col-sm-6"><form:input readonly="true"
							path="companyName" placeholder="Enter Company Name"
							id="companyName"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message
								code="label.companyPrefix" /></b><span style="color: red">*</span></td>
					<td class="col-sm-6"><form:input readonly="true"
							path="companyPrefix" placeholder="Enter Company Prefix"
							id="companyPrefix"></form:input></td>

				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message
								code="label.address" /></b><span style="color: red">*</span></td>
					<td class="col-sm-6"><form:input readonly="true"
							path="address" placeholder="Enter Address" id="address"></form:input></td>

				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message
								code="label.pincode" /></b><span style="color: red">*</span></td>
					<td class="col-sm-6"><form:input readonly="true"
							path="pincode" placeholder="Enter Pincode" id="pincode"></form:input></td>
				<tr>
					<td class="col-sm-6"><b><spring:message
								code="label.country" /></b><span style="color: red">*</span></td>
					<td class="col-sm-6"><form:input path="country"
							readonly="true" placeholder="Enter country" id="country"></form:input></td>

				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.state" /></b><span
						style="color: red">*</span></td>
					<td class="col-sm-6"><form:input path="state" readonly="true"
							placeholder="Enter state" id="state"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.city" /></b><span
						style="color: red">*</span></td>
					<td class="col-sm-6"><form:input readonly="true" path="city"
							placeholder="Enter City" id="city"></form:input></td>
				</tr>
				
				<form:hidden path="transactionId" value="" />
			</table>
			<div class="col-sm-12 col-md-12 col-lg-12">
				<p align="center" style="padding-top: 10px;">
					<input type="submit" class="btn btn-primary"
						value="<spring:message code="label.save"/>"> <a
						href="createCompany" class="btn btn-success"><spring:message
							code="label.back" /></a>
				</p>
			</div>
		</div>
	</form:form>
</div>
