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
		document.bankDetails.transactionId.value = uuid; */
	}
</script>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.proDetails"/></h3>
		</div>
				<form:form name="bankDetails" action="updateVendorDetails" commandName="endUserForm"
					onsubmit="return validateForm()">
					<table align="center">
						<form:hidden path="id" />
						
<tr>
							<td class="heading_text"><b><spring:message code="label.userName"/> :</b></td>
							<td><form:input path="userName" id="userName" value=""
									readonly="true" />
						</tr>

						<tr>
							<td class="heading_text"><b><spring:message code="label.displayName"/> :</b></td>
							<td><form:input path="displayName" id="displayName" value=""
									readonly="true" />
						</tr>


						<tr>
							<td><b><spring:message code="label.contactNumber"/> :</b></td>
							<td><form:input path="contactNo" value="" readonly="true" /></td>

						</tr>

						<tr>
							<td><b><spring:message code="label.altContactNo"/> :</b></td>
							<td><form:input path="altContactNo" value="" readonly="true" /></td>

						</tr>

						<tr>
							<td><b><spring:message code="label.email"/> :</b></td>
							<td><form:input path="email" value="" readonly="true" /></td>
						</tr>
						<tr>
							<td><b><spring:message code="label.altEmail"/> :</b></td>
							<td><form:input path="altEmail" value="" readonly="true" /></td>
						</tr>

						<form:hidden path="transactionId" value="" />
					</table>

					<div class="col-sm-12">
						<table align="center">
							<tr>
								<td class="col-sm-8"><input type="submit"  value="<spring:message code="label.update"/>" class="btn btn-primary"></td>
									<td><a href="vendors" class="btn btn-success" ><spring:message code="label.back"/></a></td>
							</tr>
						</table>
					</div>

				</form:form>
			</div>