<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 align="center"><spring:message code="label.buyerDetails"/></h3>
		</div>
				<form:form name="selectBuyerForCustomerBranchUpdate3" action="selectBuyerForCustomerBranchUpdate3"
					commandName="newBuyerForm" onsubmit="return validateForm()">
					<table align="center">

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.id"/>:</b></td>
							<td class="col-sm-6"><form:input path="id" id="id"
									value="${model.newBuyerForm.id}" readonly="true" />
									</td>
						</tr>

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.contactNumber"/>:</b></td>
							<td class="col-sm-6"><form:input path="contactNum"
									value="${model.newBuyerForm.contactNum}" readonly="true" /></td>
						</tr>
							<tr>
							<td class="col-sm-6"><b><spring:message code="label.customerName"/>:</b></td>
							<td class="col-sm-6"><form:input path="name"
									value="${model.newBuyerForm.name}" readonly="true" /></td>
						</tr>
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.pincode"/>:</b></td>
							<td class="col-sm-6"><form:input path="pinCode"
									value="${model.newBuyerForm.pinCode}" readonly="true" /></td>
						</tr>
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.country"/>:</b></td>
							<td class="col-sm-6"><form:input path="country"
									value="${model.newBuyerForm.country}" readonly="true" /></td>
						</tr>
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.state"/>:</b></td>
							<td class="col-sm-6"><form:input path="state"
									value="${model.newBuyerForm.state}" readonly="true" /></td>
						</tr>
						
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.city"/>:</b></td>
							<td class="col-sm-6"><form:input path="city"
									value="${model.newBuyerForm.city}" readonly="true" /></td>
						</tr>
						
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.email"/>:</b></td>
							<td class="col-sm-6"><form:input path="email"
									value="${model.newBuyerForm.email}" readonly="true" /></td>
						</tr>
						
						
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.buyerName"/>:</b></td>
							<td class="col-sm-6"><form:input path="buyerName"
									value="${model.newBuyerForm.buyerName}" readonly="true" /></td>
						</tr>
						
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.buyerComName"/>:</b></td>
							<td class="col-sm-6"><form:input path="companyName"
									value="${model.newBuyerForm.companyName}" readonly="true" /></td>
						</tr>
						
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.buyerBank"/>:</b></td>
							<td class="col-sm-6"><form:input path="bank"
									value="${model.newBuyerForm.bank}" readonly="true" /></td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.ifscOrSwift"/>:</b></td>
							<td class="col-sm-6"><form:input path="bank"
									value="${model.newBuyerForm.ifsc}" readonly="true" /></td>
						</tr>
						
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.buyerBankEmail"/>:</b></td>
							<td class="col-sm-6"><form:input path="bankEmail"
									value="${model.newBuyerForm.bankEmail}" readonly="true" /></td>
						</tr>
						
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.currencyDealt"/>:</b></td>
							<td class="col-sm-6"><form:input path="currencydeal"
									value="${model.newBuyerForm.currencydeal}" readonly="true" /></td>
						</tr>
						


						<tr>
							<td class="col-sm-6"><b><spring:message code="label.address"/>:</b></td>
							<td class="col-sm-6"><form:input path="address"
									value="${model.newBuyerForm.address}" readonly="true" /> <form:hidden
									path="transactionId" value="" /></td>


						</tr>
						<tr>
							<td class="col-sm-6" class="heading_text"><b><spring:message code="label.buyerBankBranch"/></b></td>
							<td class="col-sm-6"><form:input path="branch" id="branch"
									value="${model.newBuyerForm.branch}" readonly="true" />
						</tr>
						
							</table>

					<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.update"/>" class="btn btn-primary"></td>
							<td><a href="userBranch" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>

				</form:form>
			</div>