<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script type="text/javascript">
	function validateForm() {
		var d = new Date().getTime();
		var uuid = 'xxxxxx'.replace(/[xy]/g, function(c) {
			var r = (d + Math.random() * 16) % 16 | 0;
			d = Math.floor(d / 16);
			return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
		});
		document.supplier.transactionId.value = uuid;
	}
</script>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
		</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div class="successMsg" style="text-align: center; color: green; font-size: 18px;">${success}</div>
			</div>
				<form:form name="supplier" action="supplierApprovalTradeList"
					commandName="supplierTradeForm" onsubmit="return validateForm()">
				<div class="col-sm-6 col-md-6">
					<table align="center">
                            <tr>
							<td class="col-sm-6"><b><spring:message code="label.customerName"/></b></td>
							<td class="col-sm-6"><form:input path="name" id="name"
									value="${model.supplierTradeForm.name}"
									readonly="true" />
						</tr>
                        <tr>
							<td class="col-sm-6"><b><spring:message code="label.supplierName"/></b></td>
							<td class="col-sm-6"><form:input path="supplierName" id="supplierName"
									value="${model.supplierTradeForm.supplierName}" readonly="true" />
						</tr>



						<tr>
							<td class="col-sm-6"><b><spring:message code="label.supplierCompanyName"/></b></td>
							<td class="col-sm-6"><form:input path="companyName" id="companyName"
									value="${model.supplierTradeForm.companyName}" readonly="true" />
						</tr>

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.supplierBank"/></b></td>
							<td class="col-sm-6"><form:input path="bank" id="bank"
									value="${model.supplierTradeForm.bank}" readonly="true" />
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.ifscOrSwift"/></b></td>
							<td class="col-sm-6"><form:input path="ifsc" id="ifsc"
									value="${model.supplierTradeForm.ifsc}" readonly="true" />
						</tr>
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.pincode"/></b></td>
							<td class="col-sm-6"><form:input path="pinCode" id="pinCode"
									value="${model.supplierTradeForm.pinCode}" readonly="true" />
						</tr>
			
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.country"/></b></td>
							<td class="col-sm-6"><form:input path="country" id="country"
									value="${model.supplierTradeForm.country}" readonly="true" />
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.state"/></b></td>
							<td class="col-sm-6"><form:input path="state" id="state"
									value="${model.supplierTradeForm.state}" readonly="true" />
						</tr>


						 <tr>
							<td class="col-sm-6"><b><spring:message code="label.contactNumber"/></b></td>
							<td class="col-sm-6"><form:input path="contactNum" id="contactNum"
									value="${model.supplierTradeForm.contactNum}" readonly="true" />
						</tr>
				</table>
			</div>
			<div class="col-sm-6 col-md-6">
			<table>
                        <tr>
							<td class="col-sm-6"><b><spring:message code="label.altContactNo"/></b></td>
							<td class="col-sm-6"><form:input path="altContactNum" id="altContactNum"
									value="${model.supplierTradeForm.altContactNum}" readonly="true" />
						</tr>
                          

                          <tr>
							<td class="col-sm-6"><b><spring:message code="label.altEmail"/></b></td>
							<td class="col-sm-6"><form:input path="altEmail" id="altEmail"
									value="${model.supplierTradeForm.altEmail}" readonly="true" />
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.supplierBankEmail"/></b></td>
							<td class="col-sm-6"><form:input path="bankEmail" id="bankEmail"
									value="${model.supplierTradeForm.bankEmail}" readonly="true" />
						</tr>
						
							<tr>
							<td class="col-sm-6"><b><spring:message code="label.email"/></b></td>
							<td class="col-sm-6"><form:input path="email" id="email"
									value="${model.supplierTradeForm.email}" readonly="true" />
						</tr>
                           <tr>
							<td class="col-sm-6"><b><spring:message code="label.city"/>:</b></td>
							<td class="col-sm-6"><form:input path="city"
									value="${model.supplierTradeForm.city}" readonly="true" /></td>
							
							
			                   	</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.supplierBankBranch"/>:</b></td>
							<td class="col-sm-6"><form:input path="branch"
									value="${model.supplierTradeForm.branch}" readonly="true" /></td>
						</tr>
						
							<tr>
							<td class="col-sm-6"><b><spring:message code="label.currencyDealt"/>:</b></td>
							<td class="col-sm-6"><form:input path="currencydeal"
									value="${model.supplierTradeForm.currencydeal}" readonly="true" /></td>
						</tr>

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.address"/>:</b></td>
							<td class="col-sm-6"><form:input path="address"
									value="${model.supplierTradeForm.address}" readonly="true" /></td>
							<form:hidden path="transactionId" value="" />
								<form:hidden path="cStatus" value=""/>
							
						</tr>
						
							

					</table>
			</div>
					<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="newSupplierPageTrade" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>

				</form:form>
			</div>
