<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


 <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
				</div>
				<form:form action="updateCustBankDetails"
					commandName="customerBankDetailsForm" onsubmit="return validateForm()">
					<table align="center">

					<tr>
						<td class="col-sm-6"><b><spring:message code="label.id"/></b></td>
						<td class="col-sm-6"><form:input path="id" value="${model.CustomerBankDetailsForm.id}" id="id" readonly="true" />
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.bankName"/></b></td>
						<td class="col-sm-6"><form:input path="bankName" id="bankName" value="${model.CustomerBankDetailsForm.bankName}" readonly="true" />
					</tr>

					<tr>
						<td class="col-sm-6"><b><spring:message code="label.location"/></b></td>
						<td class="col-sm-6"><form:input path="location" id="location" value="${model.CustomerBankDetailsForm.location}"  readonly="true" /></td>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.branch"/></b></td>
						<td class="col-sm-6"><form:input path="branch" id="branch" value="${model.CustomerBankDetailsForm.branch}"  readonly="true"/></td>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.branchCode"/></b></td>
						<td class="col-sm-6"><form:input path="branchCode" id="branchCode" value="${model.CustomerBankDetailsForm.branchCode}"  readonly="true"/></td>
					</tr>

					<tr>
						<td class="col-sm-6"><b><spring:message code="label.contactNumber"/></b></td>
						<td class="col-sm-6"><form:input path="contactNo" id="contactNo" value="${model.CustomerBankDetailsForm.contactNo}" readonly="true"/></td>
					</tr>

					<tr>
						<td class="col-sm-6"><b><spring:message code="label.email"/></b></td>
						<td class="col-sm-6"><form:input path="email" id="email" value="${model.CustomerBankDetailsForm.email}" readonly="true"/></td>
						<form:hidden path="transactionId"/>
					</tr>

					</table>
					<div class="col-sm-12 col-md-12 col-lg-12">&nbsp;</div>
					<div class="col-sm-12 col-md-12 col-lg-12">
						<table align="center">
							<tr>
								<td class="col-sm-8"><input type="submit" size="3"
									value="<spring:message code="label.update"/>" class="btn btn-primary"></td>
								<td><a href="custBankDetails" class="btn btn-success"><spring:message code="label.back"/>
								    </a>
								</td>

							</tr>
						</table>
					</div>

				</form:form>
			</div>
