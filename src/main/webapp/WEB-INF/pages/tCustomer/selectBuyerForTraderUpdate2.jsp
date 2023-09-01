<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

 <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.buyerDetails"/></h3>
			</div>
				<form:form name="selectBuyerForTraderUpdate3" action="selectBuyerForTraderUpdate3"
					commandName="buyerTradeForm" onsubmit="return validateForm()">
					<table align="center">

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.id"/>:</b></td>
							<td class="col-sm-6"><form:input path="id" id="id"
									value="${model.buyerTradeForm.id}" readonly="true" />
									</td>
						</tr>

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.contactNumber"/>:</b></td>
							<td class="col-sm-6"><form:input path="contactNum"
									value="${model.buyerTradeForm.contactNum}" readonly="true" /></td>
						</tr>
							<tr>
							<td class="col-sm-6"><b><spring:message code="label.customerName"/>:</b></td>
							<td class="col-sm-6"><form:input path="name"
									value="${model.buyerTradeForm.name}" readonly="true" /></td>
						</tr>
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.pincode"/>:</b></td>
							<td class="col-sm-6"><form:input path="pinCode"
									value="${model.buyerTradeForm.pinCode}" readonly="true" /></td>
						</tr>
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.country"/>:</b></td>
							<td class="col-sm-6"><form:input path="country"
									value="${model.buyerTradeForm.country}" readonly="true" /></td>
						</tr>
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.state"/>:</b></td>
							<td class="col-sm-6"><form:input path="state"
									value="${model.buyerTradeForm.state}" readonly="true" /></td>
						</tr>
						
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.city"/>:</b></td>
							<td class="col-sm-6"><form:input path="city"
									value="${model.buyerTradeForm.city}" readonly="true" /></td>
						</tr>
						
					
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.altContactNo"/>:</b></td>
							<td class="col-sm-6"><form:input path="altcontactNum"
									value="${model.buyerTradeForm.altcontactNum}" readonly="true" /></td>
						</tr>
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.altEmail"/>:</b></td>
							<td class="col-sm-6"><form:input path="altEmail"
									value="${model.buyerTradeForm.altEmail}" readonly="true" /></td>
						</tr>
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.email"/>:</b></td>
							<td class="col-sm-6"><form:input path="email"
									value="${model.buyerTradeForm.email}" readonly="true" /></td>
						</tr>
						
						
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.buyerName"/>:</b></td>
							<td class="col-sm-6"><form:input path="buyerName"
									value="${model.buyerTradeForm.buyerName}" readonly="true" /></td>
						</tr>
						
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.buyerComName"/> :</b></td>
							<td class="col-sm-6"><form:input path="companyName"
									value="${model.buyerTradeForm.companyName}" readonly="true" /></td>
						</tr>
						
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.buyerBank"/>:</b></td>
							<td class="col-sm-6"><form:input path="bank"
									value="${model.buyerTradeForm.bank}" readonly="true" /></td>
						</tr>
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.ifscOrSwift"/>:</b></td>
							<td class="col-sm-6"><form:input path="ifsc"
									value="${model.buyerTradeForm.ifsc}" readonly="true" /></td>
						</tr>
						
						
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.buyerBankEmail"/>:</b></td>
							<td class="col-sm-6"><form:input path="bankEmail"
									value="${model.buyerTradeForm.bankEmail}" readonly="true" /></td>
						</tr>
						
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.currencyDealt"/>:</b></td>
							<td class="col-sm-6"><form:input path="currencydeal"
									value="${model.buyerTradeForm.currencydeal}" readonly="true" /></td>
						</tr>
						


						<tr>
							<td class="col-sm-6"><b><spring:message code="label.address"/>:</b></td>
							<td class="col-sm-6"><form:input path="address"
									value="${model.buyerTradeForm.address}" readonly="true" /> 
									<form:hidden
									path="transactionId" value="" /></td>
	                            <form:hidden path="cStatus" value=""/>

						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.buyerBankBranch"/></b></td>
							<td class="col-sm-6"><form:input path="branch" id="branch"
									value="${model.buyerTradeForm.branch}" readonly="true" />
						</tr>
						
							</table>

					<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.update"/>" class="btn btn-primary"></td>
							<td><a href="addnewBuyerTrade" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>

				</form:form>
			</div>