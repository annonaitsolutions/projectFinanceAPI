<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script type="text/javascript">
	function validateForm() {
		/* var d = new Date().getTime();
		var uuid = 'xxxxxx'.replace(/[xy]/g, function(c) {
			var r = (d + Math.random() * 16) % 16 | 0;
			d = Math.floor(d / 16);
			return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
		});
		document.newbuyer.transactionId.value = uuid; */
	}
</script>


<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
		<div class="col-sm-12 col-md-12 col-lg-12">
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
				</div>
				<div class="col-sm-12 col-md-12 col-lg-12">
			<div class="successMsg" style="text-align: center; color: green; font-size: 18px;">${success}</div>
			</div>
			
				<form:form name="newbuyer" action="newbuyerApprovalList" commandName="newBuyerForm" onsubmit="return validateForm()">
					<table align="center" width="400">


						<tr>
							<td><b><spring:message code="label.customerName"/>:</b></td>
							<td><form:input path="name" id="name"
									value="${model.newBuyerForm.name}"
									readonly="true" />
						</tr>
                         <tr>
							<td><b><spring:message code="label.buyerName"/>:</b></td>
							<td><form:input path="buyerName" id="buyerName"
									value="${model.newBuyerForm.buyerName}" readonly="true" />
						</tr>



						<tr>
							<td><b><spring:message code="label.buyerCompanyName"/>:</b></td>
							<td><form:input path="companyName" id="companyName"
									value="${model.newBuyerForm.companyName}" readonly="true" />
						</tr>
						<tr>
							<td><b><spring:message code="label.ifscOrSwift"/>:</b></td>
							<td><form:input path="ifsc" id="ifsc"
									value="${model.newBuyerForm.ifsc}" readonly="true" />
						</tr>
              
						<tr>
							<td><b><spring:message code="label.buyerBank"/>:</b></td>
							<td><form:input path="bank" id="bank"
									value="${model.newBuyerForm.bank}" readonly="true" />
						</tr>
						<tr>
							<td><b><spring:message code="label.pincode"/>:</b></td>
							<td><form:input path="pinCode" id="pinCode"
									value="${model.newBuyerForm.pinCode}" readonly="true" />
						</tr>

				<tr>
					<fmt:formatDate value="${model.newBuyerForm.accExpiryDate}" var="formatDate" pattern="dd/MM/yyyy" />
					<td><b><spring:message code="label.accountExpiryDate"/></b><span style="color: red">*</span></td>
					<td><form:input path="accExpiryDate" class="form-control" 
					    value="${formatDate}" style="width: 206px;"	readonly="true" id="datepicker" />
					</td>
				</tr>

				<tr>
							<td><b><spring:message code="label.country"/> :</b></td>
							<td><form:input path="country" id="country"
									value="${model.newBuyerForm.country}" readonly="true" />
						</tr>
						<tr>
							<td><b><spring:message code="label.state"/> :</b></td>
							<td><form:input path="state" id="state"
									value="${model.newBuyerForm.state}" readonly="true" />
						</tr>


						<tr>
							<td><b><spring:message code="label.contactNumber"/>:</b></td>
							<td><form:input path="contactNum" id="contactNum"
									value="${model.newBuyerForm.contactNum}" readonly="true" />
						</tr>


	                       <tr>
							<td><b><spring:message code="label.email"/> :</b></td>
							<td><form:input path="email" id="email"
									value="${model.newBuyerForm.email}" readonly="true" />
						</tr>
                         <tr>
							<td><b><spring:message code="label.altEmail"/>:</b></td>
							<td><form:input path="altEmail" id="altEmail"
									value="${model.newBuyerForm.altEmail}" readonly="true" />
						</tr>
						   <tr>
							<td><b><spring:message code="label.altContactNo"/>:</b></td>
							<td><form:input path="altcontactNum" id="altcontactNum"
									value="${model.newBuyerForm.altcontactNum}" readonly="true" />
						</tr>

                             <tr>
							<td><b><spring:message code="label.city"/> :</b></td>
							<td><form:input path="city" id="city"
									value="${model.newBuyerForm.city}" readonly="true" />
						</tr>
						<tr>
							<td><b><spring:message code="label.buyerBankEmail"/> :</b></td>
							<td><form:input path="bankEmail" id="bankEmail"
									value="${model.newBuyerForm.bankEmail}" readonly="true" />
						</tr>

						<tr>
							<td><b><spring:message code="label.buyerBankBranch"/>:</b></td>
							<td><form:input path="branch"
									value="${model.newBuyerForm.branch}" readonly="true" /></td>
						</tr>
						
							<tr>
							<td><b><spring:message code="label.currencyDealt"/>:</b></td>
							<td><form:input path="currencydeal"
									value="${model.newBuyerForm.currencydeal}" readonly="true" /></td>
						</tr>

						<tr>
							<td><b><spring:message code="label.address"/> :</b></td>
							<td><form:input path="address"
									value="${model.newBuyerForm.address}" readonly="true" /></td>
							<form:hidden path="transactionId" value="" />
						
						</tr>
						
						<tr>
							<td><b><spring:message code="label.rating"/> :</b></td>
							<form:hidden path="rate" value="${model.newBuyerForm.rate}" />
							<form:hidden path="companyId" value="${model.newBuyerForm.companyId}" />
							<td><div class="basic"	data-average="${model.newBuyerForm.rate}"></div>
									</td>
							
						
						</tr>

					</table>

					<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="user" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>

				</form:form>
			</div>
		</div>
		<script>
		$(document).ready(function(){
	    	 $(".basic").jRating({    		
	    		 isDisabled :true
	    	 });
		});
		</script>
