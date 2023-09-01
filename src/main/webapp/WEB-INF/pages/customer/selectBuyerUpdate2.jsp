<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.buyerDetails"/></h3>
			</div>
				<form:form name="selectBuyerUpdate3" action="selectBuyerUpdate3"
					commandName="newBuyerForm" onsubmit="return validateForm()">
					<table align="center">

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.id"/>:</b></td>
							<td class="col-sm-6"><form:input path="id" id="id"
									value="${model.newBuyerForm.id}" readonly="true" />
									</td>
						</tr>
						
						
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.customerName"/>:</b></td>
							<td class="col-sm-6"><form:input path="name" id="name"
									value="${model.newBuyerForm.name}" readonly="true" />
									</td>
						</tr>
							<tr>
							<td class="col-sm-6"><b><spring:message code="label.buyerName"/>:</b></td>
							<td class="col-sm-6"><form:input path="buyerName" id="buyerName"
									value="${model.newBuyerForm.buyerName}" readonly="true" />
									</td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.buyerComName"/>:</b></td>
							<td class="col-sm-6"><form:input path="companyName" id="companyName"
									value="${model.newBuyerForm.companyName}" readonly="true" />
									</td>
						</tr>
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.buyerBank"/>:</b></td>
							<td class="col-sm-6"><form:input path="bank" id="bank"
									value="${model.newBuyerForm.bank}" readonly="true" />
									</td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.buyerBankEmail"/>:</b></td>
							<td class="col-sm-6"><form:input path="bankEmail" id="bankEmail"
									value="${model.newBuyerForm.bankEmail}" readonly="true" />
									</td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.buyerBankBranch"/>:</b></td>
							<td class="col-sm-6"><form:input path="branch" id="branch"
									value="${model.newBuyerForm.branch}" readonly="true" />
									</td>
						</tr>
							<tr>
							<td class="col-sm-6"><b><spring:message code="label.ifscOrSwift"/>:</b></td>
							<td class="col-sm-6"><form:input path="ifsc" id="ifsc"
									value="${model.newBuyerForm.ifsc}" readonly="true" />
									</td>
						</tr>
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.pincode"/>:</b></td>
							<td class="col-sm-6"><form:input path="pinCode" id="pinCode"
									value="${model.newBuyerForm.pinCode}" readonly="true" />
									</td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.country"/>:</b></td>
							<td class="col-sm-6"><form:input path="country" id="country"
									value="${model.newBuyerForm.country}" readonly="true" />
									</td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.state"/>:</b></td>
							<td class="col-sm-6"><form:input path="state" id="state"
									value="${model.newBuyerForm.state}" readonly="true" />
									</td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.city"/>:</b></td>
							<td class="col-sm-6"><form:input path="city" id="city"
									value="${model.newBuyerForm.city}" readonly="true" />
									</td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.altContactNo"/>:</b></td>
							<td class="col-sm-6"><form:input path="altcontactNum" id="altcontactNum"
									value="${model.newBuyerForm.altcontactNum}" readonly="true" />
									</td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.email"/>:</b></td>
							<td class="col-sm-6"><form:input path="email" id="email"
									value="${model.newBuyerForm.email}" readonly="true" />
									</td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.altEmail"/>:</b></td>
							<td class="col-sm-6"><form:input path="altEmail" id="altEmail"
									value="${model.newBuyerForm.altEmail}" readonly="true" />
									</td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.currencyDealt"/>:</b></td>
							<td class="col-sm-6"><form:input path="currencydeal" id="currencydeal"
									value="${model.newBuyerForm.currencydeal}" readonly="true" />
									</td>
						</tr>

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.contactNumber"/>:</b></td>
							<td class="col-sm-6"><form:input path="contactNum"
									value="${model.newBuyerForm.contactNum}" readonly="true" /></td>
						</tr>


						<tr>
							<td class="col-sm-6"><b><spring:message code="label.address"/>:</b></td>
							<td class="col-sm-6"><form:input path="address"
									value="${model.newBuyerForm.address}" readonly="true" /> 
									
									<form:hidden path="transactionId" value="" /></td>


						</tr>
						
						
							</table>

					<div class="col-sm-12 col-md-12 col-lg-12">
								<p align="center" style="padding-top: 10px;">
								<input type="submit" class="btn btn-primary" value="<spring:message code="label.update"/>">
					
								<a href="newBuyerPage" class="btn btn-success"><spring:message code="label.back"/></a>
								</p>
		</div>

				</form:form>
			</div>