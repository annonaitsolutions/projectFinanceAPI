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
			<h3 align="center"><spring:message code="label.suppDetails"/></h3>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
			<div class="successMsg" style="text-align: center; color: green; font-size: 18px;">${success}</div>
		</div>
				
				<form:form name="supplier" action="supplierPageCustomerBranchConfirmList" commandName="supplierForm" onsubmit="return validateForm()">
					<table align="center">
                            <tr>
							<td class="col-sm-6"><b><spring:message code="label.customerName"/></b></td>
							<td class="col-sm-6"><form:input path="name" id="name"
									value="${model.supplierForm.name}"
									readonly="true" />
						</tr>
                        <tr>
							<td class="col-sm-6"><b><spring:message code="label.supplierName"/></b></td>
							<td class="col-sm-6"><form:input path="supplierName" id="supplierName"
									value="${model.supplierForm.supplierName}" readonly="true" />
						</tr>



						<tr>
							<td class="col-sm-6"><b><spring:message code="label.supplierCompanyName"/></b></td>
							<td class="col-sm-6"><form:input path="companyName" id="companyName"
									value="${model.supplierForm.companyName}" readonly="true" />
						</tr>

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.supplierBank"/></b></td>
							<td class="col-sm-6"><form:input path="bank" id="bank"
									value="${model.supplierForm.bank}" readonly="true" />
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.pincode"/></b></td>
							<td class="col-sm-6"><form:input path="pinCode" id="pinCode"
									value="${model.supplierForm.pinCode}" readonly="true" />
						</tr>

                            <tr>
							<td class="col-sm-6"><b><spring:message code="label.supplierCity"/></b></td>
							<td class="col-sm-6"><form:input path="city" id="city"
									value="${model.supplierForm.city}" readonly="true" />
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.country"/></b></td>
							<td class="col-sm-6"><form:input path="country" id="country"
									value="${model.supplierForm.country}" readonly="true" />
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.state"/></b></td>
							<td class="col-sm-6"><form:input path="state" id="state"
									value="${model.supplierForm.state}" readonly="true" />
						</tr>


						<tr>
							<td class="col-sm-6"><b><spring:message code="label.contactNumber"/></b></td>
							<td class="col-sm-6"><form:input path="contactNum" id="contactNum"
									value="${model.supplierForm.contactNum}" readonly="true" />
						</tr>





						<tr>
							<td class="col-sm-6"><b><spring:message code="label.supplierBankEmail"/></b></td>
							<td class="col-sm-6"><form:input path="bankEmail" id="bankEmail"
									value="${model.supplierForm.bankEmail}" readonly="true" />
						</tr>
						
							<tr>
							<td class="col-sm-6"><b><spring:message code="label.email"/></b></td>
							<td class="col-sm-6"><form:input path="email" id="email"
									value="${model.supplierForm.email}" readonly="true" />
						</tr>

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.bankBranch"/>:</b></td>
							<td class="col-sm-6"><form:input path="branch"
									value="${model.supplierForm.branch}" readonly="true" /></td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.ifscOrSwift"/>:</b></td>
							<td class="col-sm-6"><form:input path="ifsc"
									value="${model.supplierForm.ifsc}" readonly="true" /></td>
						</tr>
						
							<tr>
							<td class="col-sm-6"><b><spring:message code="label.currencyDealt"/>:</b></td>
							<td class="col-sm-6"><form:input path="currencydeal"
									value="${model.supplierForm.currencydeal}" readonly="true" /></td>
						</tr>

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.address"/>:</b></td>
							<td class="col-sm-6"><form:input path="address"
									value="${model.supplierForm.address}" readonly="true" /></td>
							<form:hidden path="transactionId" value="" />
							
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.rating"/> :</b></td>
							<form:hidden path="rate" value="${model.supplierForm.rate}" />
							<td class="col-sm-6"><div class="basic"	data-average="${model.supplierForm.rate}"></div>
									</td>					
						
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
			
			<script>
		$(document).ready(function(){
	    	 $(".basic").jRating({    		
	    		 isDisabled :true
	    	 });
		});
		</script>
