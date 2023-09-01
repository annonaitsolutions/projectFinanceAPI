<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">

	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
				<form:form name="amDetails" action="updateAMDetails" commandName="endUserForm"
					onsubmit="return validateForm()">
					<table align="center">
						<form:hidden path="id" />
						

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.userName"/></b></td>
							<td class="col-sm-6"><form:input path="userName" id="userName" value=""
									readonly="true" />
						</tr>

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.displayName"/></b></td>
							<td class="col-sm-6"><form:input path="displayName" id="displayName" value=""
									readonly="true" />
						</tr>


						<tr>
							<td class="col-sm-6"><b><spring:message code="label.contactNumber"/></b></td>
							<td class="col-sm-6"><form:input path="contactNo" value="" readonly="true" /></td>

						</tr>

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.altcontactNumber"/></b></td>
							<td class="col-sm-6"><form:input path="altContactNo" value="" readonly="true" /></td>

						</tr>

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.email"/></b></td>
							<td class="col-sm-6"><form:input path="email" value="" readonly="true" /></td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.altEmail"/></b></td>
							<td class="col-sm-6"><form:input path="altEmail" value="" readonly="true" /></td>
						</tr>

						<form:hidden path="transactionId" value="" />
					</table>

					<div class="col-sm-12">
						<table align="center">
							<tr>
								<td class="col-sm-8"><input type="submit"  value="<spring:message code="label.update"/>" class="btn btn-primary"></td>		</td>
									<td><a href="apprMng" class="btn btn-success" ><spring:message code="label.back"/></a></td>
							</tr>
						</table>
					</div>

				</form:form>
			</div>
		</div>

