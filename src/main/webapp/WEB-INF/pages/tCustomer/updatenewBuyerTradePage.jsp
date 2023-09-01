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
		document.newbuyer.transactionId.value = uuid;
		
		var d1 = new Date().getTime();
		var uuid1 = 'xxxxxx'.replace(/[xy]/g, function(c) {
			var r1 = (d1+ Math.random() * 16) % 16 | 0;
			d1 = Math.floor(d1 / 16);
			return (c1 == 'x' ? r1 : (r1& 0x3 | 0x8)).toString(16);
		});
		document.newbuyer.transactionId.value = uuid;
	}
</script>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
			<div class="successMsg" style="text-align: center; color: green; font-size: 18px;">${success}</div>
		</div>
			<form:form name="newbuyer" action="newbuyerConfirmTardeList" commandName="buyerTradeForm" onsubmit="return validateForm()">
				<div class="col-sm-12 col-md-6 col-lg-6">
					<table align="center">


					<tr>
							<td class="heading_text"><b><spring:message code="label.customerName"/></b></td>
							<td><form:input path="name" id="name"
									value="${model.buyerTradeForm.name}"
									readonly="true" />
						</tr>
                         <tr>
							<td class="heading_text"><b><spring:message code="label.buyerName"/></b></td>
							<td><form:input path="buyerName" id="buyerName"
									value="${model.buyerTradeForm.buyerName}" readonly="true" />
						</tr>



						<tr>
							<td class="heading_text"><b><spring:message code="label.buyerComName"/></b></td>
							<td><form:input path="companyName" id="companyName"
									value="${model.buyerTradeForm.companyName}" readonly="true" />
						</tr>

						<tr>
							<td class="heading_text"><b><spring:message code="label.buyerBank"/></b></td>
							<td><form:input path="bank" id="bank"
									value="${model.buyerTradeForm.bank}" readonly="true" />
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.ifscOrSwift"/></b></td>
							<td><form:input path="ifsc" id="ifsc"
									value="${model.buyerTradeForm.ifsc}" readonly="true" />
						</tr>
						
						<tr>
							<td class="heading_text"><b><spring:message code="label.pincode"/></b></td>
							<td><form:input path="pinCode" id="pinCode"
									value="${model.buyerTradeForm.pinCode}" readonly="true" />
						</tr>

						<tr>
							<td class="heading_text"><b><spring:message code="label.country"/></b></td>
							<td><form:input path="country" id="country"
									value="${model.buyerTradeForm.country}" readonly="true" />
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.state"/></b></td>
							<td><form:input path="state" id="state"
									value="${model.buyerTradeForm.state}" readonly="true" />
						</tr>


						<tr>
							<td class="heading_text"><b><spring:message code="label.contactNumber"/></b></td>
							<td><form:input path="contactNum" id="contactNum"
									value="${model.buyerTradeForm.contactNum}" readonly="true" />
						</tr>
			</table>
			</div>
			<div class="col-sm-12 col-md-6 col-lg-6">
					<table>
	                       <tr>
							<td class="heading_text"><b><spring:message code="label.email"/></b></td>
							<td><form:input path="email" id="email"
									value="${model.buyerTradeForm.email}" readonly="true" />
						</tr>
                         <tr>
							<td class="heading_text"><b><spring:message code="label.altEmail"/></b></td>
							<td><form:input path="altEmail" id="altEmail"
									value="${model.buyerTradeForm.altEmail}" readonly="true" />
						</tr>
						   <tr>
							<td class="heading_text"><b><spring:message code="label.altContactNo"/></b></td>
							<td><form:input path="altcontactNum" id="altcontactNum"
									value="${model.buyerTradeForm.altcontactNum}" readonly="true" />
						</tr>

                             <tr>
							<td class="heading_text"><b><spring:message code="label.city"/></b></td>
							<td><form:input path="city" id="city"
									value="${model.buyerTradeForm.city}" readonly="true" />
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.buyerBankEmail"/></b></td>
							<td><form:input path="bankEmail" id="bankEmail"
									value="${model.buyerTradeForm.bankEmail}" readonly="true" />
						</tr>

						<tr>
							<td><b><spring:message code="label.buyerBankBranch"/>:</b></td>
							<td><form:input path="branch"
									value="${model.buyerTradeForm.branch}" readonly="true" /></td>
						</tr>
						
							<tr>
							<td><b><spring:message code="label.currencyDealt"/>:</b></td>
							<td><form:input path="currencydeal"
									value="${model.buyerTradeForm.currencydeal}" readonly="true" /></td>
						</tr>

						<tr>
							<td><b><spring:message code="label.address"/>:</b></td>
							<td><form:input path="address"
									value="${model.buyerTradeForm.address}" readonly="true" /></td>
							<form:hidden path="transactionId" value="" />
							<form:hidden path="cStatus" value=""/>
						
						</tr>
						
					</table>
			</div>
					<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="addnewBuyerTrade" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>

				</form:form>
			</div>
   