<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
	<div class="col-sm-12 col-md-12 col-lg-12">
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
			<div class="successMsg" style="text-align: center; color: green; font-size: 18px;">${success}</div>
		</div>	
				<form:form name="selectSupplierUpdate3"
					action="selectSupplierUpdate3" commandName="supplierForm"
					onsubmit="return validateForm()">
					<table align="center" width="400">

						<tr>
							<td class="heading_text"><b><spring:message code="label.id"/> :</b></td>
							<td><form:input path="id" id="id"
									value="${model.supplierForm.id}" readonly="true" />
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.customerName"/>:</b></td>
							<td><form:input path="name" id="name"
									value="${model.supplierForm.name}" readonly="true" />
						</tr>

                         <tr>
							<td class="heading_text"><b><spring:message code="label.supplierName"/>:</b></td>
							<td><form:input path="supplierName" id="supplierName"
									value="${model.supplierForm.supplierName}" readonly="true" />
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.supplierCompanyName"/>:</b></td>
							<td><form:input path="companyName" id="companyName"
									value="${model.supplierForm.companyName}" readonly="true" />
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.supplierBank"/>:</b></td>
							<td><form:input path="bank" id="bank"
									value="${model.supplierForm.bank}" readonly="true" />
						</tr>
						<tr>
							<td><b><spring:message code="label.ifscOrSwift"/>:</b></td>
							<td><form:input path="ifsc" id="ifsc"
									value="${model.supplierForm.ifsc}" readonly="true" />
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.supplierBankEmail"/>:</b></td>
							<td><form:input path="bankEmail" id="bankEmail"
									value="${model.supplierForm.bankEmail}" readonly="true" />
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.bankBranch"/>:</b></td>
							<td><form:input path="branch" id="branch"
									value="${model.supplierForm.branch}" readonly="true" />
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.pincode"/> :</b></td>
							<td><form:input path="pinCode" id="pinCode"
									value="${model.supplierForm.pinCode}" readonly="true" />
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.country"/> :</b></td>
							<td><form:input path="country" id="country"
									value="${model.supplierForm.country}" readonly="true" />
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.state"/> :</b></td>
							<td><form:input path="state" id="state"
									value="${model.supplierForm.name}" readonly="true" />
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.city"/> :</b></td>
							<td><form:input path="city" id="city"
									value="${model.supplierForm.city}" readonly="true" />
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.altContactNo"/> :</b></td>
							<td><form:input path="altContactNum" id="altContactNum"
									value="${model.supplierForm.altContactNum}" readonly="true" />
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.email"/> :</b></td>
							<td><form:input path="email" id="email"
									value="${model.supplierForm.email}" readonly="true" />
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.altEmail"/>:</b></td>
							<td><form:input path="altEmail" id="altEmail"
									value="${model.supplierForm.altEmail}" readonly="true" />
						</tr>

                     

						<tr>
							<td><b><spring:message code="label.contactNumber"/>:</b></td>
							<td><form:input path="contactNum"
									value="${model.supplierForm.contactNum}" readonly="true" /></td>
						</tr>
						<tr>
							<td><b><spring:message code="label.address"/> :</b></td>
							<td><form:input path="address"
									value="${model.supplierForm.address}" readonly="true" /> <form:hidden
									path="transactionId" value="" /></td>
                                 </tr>
                                 
                                 <tr>
							<td><b><spring:message code="label.currencyDealt"/>:</b></td>
							<td><form:input path="currencydeal"
									value="${model.supplierForm.currencydeal}" readonly="true" /> 
                                 </tr>
                                 

					</table>

					<div class="col-sm-12 col-md-12 col-lg-12">
								<p align="center" style="padding-top: 10px;">
								<input type="submit" class="btn btn-primary" value="<spring:message code="label.update"/>">
					
								<a href="newSupplierPage" class="btn btn-success"><spring:message code="label.back"/></a>
								</p>
		</div>
					</div>

				</form:form>
			</div>
			</div>
