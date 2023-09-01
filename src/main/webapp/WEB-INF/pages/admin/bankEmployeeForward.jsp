<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script type="text/javascript">
	function validateForm() {

	}
</script>


  <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
				</div>
				<div class="col-sm-12 col-md-12 col-lg-12">
					<div class="successMsg" style="text-align: center; color: green; font-size: 18px;">${success}</div>
				</div>
				<form:form name="role" action="roleApprovalList"
					commandName="endUserForm" onsubmit="return validateForm()">
				<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">


						<tr>
							<td class="col-sm-6"><b><spring:message code="label.id"/></b></td>
							<td class="col-sm-6"><form:input path="id" id="id"  readonly="true"/>  </td>
						</tr>

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.role"/></b></td>
							<td class="col-sm-6"><form:input path="role" id="currentRole"
									value="${model.endUserForm.currentRole}" readonly="true" />
						</tr>


						<tr>
							<td class="col-sm-6"><b><spring:message code="label.contactNumber"/></b></td>
							<td class="col-sm-6"><form:input path="contactNo" id="contactNo"
									value="${model.endUserForm.contactNo}" readonly="true" />
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.email"/></b></td>
							<td class="col-sm-6"><form:input path="email"
									value="${model.endUserForm.email}" readonly="true" /></td>
						</tr>

<%--						<tr>--%>
<%--							<td class="col-sm-6"><b><spring:message code="label.displayName"/></b></td>--%>
<%--							<td class="col-sm-6">--%>
<%--								<form:input path="displayName" value="${model.endUserForm.displayName}" readonly="true"/>--%>
<%--							</td>--%>
<%--						</tr>--%>

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.userName"/></b></td>
							<td class="col-sm-6"><form:input path="userName"
									value="${model.endUserForm.userName}" readonly="true" /></td>
							<form:hidden path="transactionId" value="" />
							<form:hidden path="role" value="2" />
							<form:hidden path="accExpiryDateStr" value="${model.endUserForm.accExpiryDateStr}" />
							<form:hidden path="password" />
						</tr>

					</table>
				</div>
					<div class="col-sm-12 col-md-12 col-lg-12">
						<table align="center">
							<tr>
								<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
								<td><a href="createRole" class="btn btn-success"><spring:message code="label.back"/></a></td>
							</tr>
						</table>
					</div>

				</form:form>
			</div>
		

