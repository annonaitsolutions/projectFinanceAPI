<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="col-sm-9 col-md-9 body_fixed">	
	<div class="col-sm-12 col-md-12 header_customer">
		<h3 align="center"><spring:message code="label.poPayment"/></h3>
	</div>

<form:form  action="poPaymentHeadLcPageConfirm" name="msplan" commandName="purchaseOrderForm" onsubmit="return val();">

		<div class="col-sm-12 col-md-12">	
					<div class="col-sm-12 col-md-6">
								<table>
						<form:hidden path="id"  />
										<form:hidden path="customerHeadEmail"  />
										<form:hidden path="customerBranchEmail"  />
										<form:hidden path="supplierEmail"  />
									<form:hidden path="transactionId" />
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerName"/>:</b></td>
										<td class="col-sm-6"><form:input path="customerName" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerHeadName"/>:</b></td>
										<td class="col-sm-6"><form:input path="customerHeadName" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.supplierName"/>:</b></td>
										<td class="col-sm-6"><form:input path="supplierName" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
										
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.poKey"/>:</b></td>
										<td class="col-sm-6"><form:input path="poKey" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
								
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.amount"/>:</b></td>
										<td class="col-sm-6"><form:input path="amount" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.balance"/>:</b></td>
										<td class="col-sm-6"><form:input path="payBalance" readonly="true"  id="customerPrefix"></form:input>
											
									</tr>
								<tr>
										<td class="col-sm-6"><b><spring:message code="label.typeOfPayment"/>:</b></td>
										<td class="col-sm-6"><form:input path="typeOfTrans" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
																							
								<tr>
											<td class="col-sm-6"><b><spring:message code="label.typeOfLc"/>:</b><span style="color:red">*</span></td>
											
											<td class="col-sm-6"><form:select path="typeOfLc" id="typeOfLc" style="width:205px;">
											    <form:option value=""><spring:message code="label.selectValue"/></form:option> 
											    <form:option value="documentory"><spring:message code="label.docLc"/></form:option>
												<form:option value="standBy"><spring:message code="label.standByLc"/></form:option>
												
												</form:select>
										<td id="typeOfLcError" class="error" style="display:none;"><font color="red"><spring:message code="label.plzSelectValue"/></font></td>
					               <td id="typeOfLcError" class="error" style="display:none"><spring:message code="label.plzSelectValue"/></td>
						
																									
									</tr>
						
									
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.typeOfBank"/>:</b><span style="color:red">*</span></td>
											
											<td class="col-sm-6"><form:select path="bankType" id="bankType" style="width:205px;">
											    <form:option value=""><spring:message code="label.selectValue"/></form:option> 
											    <form:option value="International"><spring:message code="label.international"/></form:option>
												<form:option value="Domestic"><spring:message code="label.domestic"/></form:option>
												
												</form:select>
										<td id="bankTypeError" class="error" style="display:none;"><font color="red"><spring:message code="label.plzSelectValue"/></font></td>
					               <td id="bankTypeError" class="error" style="display:none"><spring:message code="label.plzSelectValue"/></td>
																		
									</tr>

									</table>
									</div>
								<div class="col-sm-12 col-md-6">		
									<table class="theader">
						
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.bankName"/>:</b>
										<td class="col-sm-6"><form:input path="bankName" id="bankName" placeholder="Enter Bank Name"></form:input>
											<td id="bankNameError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
					               <td id="bankNameError" class="error" style="display:none"><spring:message code="label.validation"/></td>
																			
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.bankBranch"/>:</b></td>
										<td class="col-sm-6"><form:input path="bankBranch" id="bankBranch" placeholder="Enter Bank Branch" ></form:input>
					               <td id="bankBranchError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
					               <td id="bankBranchError" class="error" style="display:none"><spring:message code="label.validation"/></td>
																			
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.bankAddress"/>:</b></td>
										<td class="col-sm-6"><form:input path="bankAddress"  id="bankAddress" placeholder="Enter Bank Address" ></form:input>
											 <td id="bankAddressError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
					               <td id="bankAddressError" class="error" style="display:none"><spring:message code="label.validation"/></td>
																							
									</tr>
										
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.ifscOrSwift"/>:</b></td>
										<td class="col-sm-6"><form:input path="swiftCode"  placeholder="Enter Swift Code" id="swiftCode"></form:input>
											 <td id="swiftCodeError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
					               <td id="swiftCodeError" class="error" style="display:none"><spring:message code="label.validation"/></td>
											
									</tr>
								
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.accountNumber"/>:</b></td>
										<td class="col-sm-6"><form:input path="accNo"  placeholder="Enter account Number" id="accNo"></form:input>
												 <td id="accNoError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
					               <td id="accNoError" class="error" style="display:none"><spring:message code="label.validation"/></td>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.contactNumber"/>:</b></td>
										<td class="col-sm-6"><form:input path="contactNum"  placeholder="Enter contact number" id="contactNum"></form:input>
												 <td id="contactNumError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
					               <td id="contactNumError" class="error" style="display:none"><spring:message code="label.validation"/></td>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.contactPerson"/>:</b></td>
										<td class="col-sm-6"><form:input path="contactPerson"  placeholder="Enter contact Person" id="contactPerson"></form:input>
												 <td id="contactPersonError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
					               <td id="contactPersonError" class="error" style="display:none"><spring:message code="label.validation"/></td>
											
									</tr>
									
									</table>
							</div>		
									
							<div class="col-sm-12 col-md-12 col-lg-12">	
								<p align="center" style="padding-top: 10px;">
					
					<input type="submit" class="btn btn-primary" value="Save">
								<a href="poPaymentHeadList" class="btn btn-success" name="back"  onclick="javascript:window.location='#';">Back</a>
								</p>
							</div>
									
	</div>

</form:form>
	</div>
<style>
.link p a{
color: tomato;
}
</style>

 <script>
				function val(){
					
					var userName  = document.getElementById('bankName');

					var canSubmit = true; 
					
					
				 	    if (document.getElementById('bankName').value == ''){
				       		document.getElementById('bankNameError').style.display='block';
				       		canSubmit = false;
				       	}
				       	else{
				       		document.getElementById('bankNameError').style.display='none';
				       	}
				 	   if (document.getElementById('bankBranch').value == ''){
			 	       		document.getElementById('bankBranchError').style.display='block';
			 	       		canSubmit = false;
			 	       	}
			 	       	else{
			 	       		document.getElementById('bankBranchError').style.display='none';
			 	       	}
			 	      if (document.getElementById('bankAddress').value == ''){
				       		document.getElementById('bankAddressError').style.display='block';
				       		canSubmit = false;
				       	}
				       	else{
				       		document.getElementById('bankAddressError').style.display='none';
				       	}
			 	     if (document.getElementById('contactPerson').value == ''){
				       		document.getElementById('contactPersonError').style.display='block';
				       		canSubmit = false;
				       	}
				       	else{
				       		document.getElementById('contactPersonError').style.display='none';
				       	}
			 	       
				 	    if (document.getElementById('swiftCode').value == ''){
				 	       		document.getElementById('swiftCodeError').style.display='block';
				 	       		canSubmit = false;
				 	       	}
				 	       	else{
				 	       		document.getElementById('swiftCodeError').style.display='none';
				 	       	}
				 	      if (document.getElementById('accNo').value == ''){
					       		document.getElementById('accNoError').style.display='block';
					       		canSubmit = false;
					       	}
					       	else{
					       		document.getElementById('accNoError').style.display='none';
					       	}
				 	     if (document.getElementById('contactNum').value == ''){
					       		document.getElementById('contactNumError').style.display='block';
					       		canSubmit = false;
					       	}
					       	else{
					       		document.getElementById('contactNumError').style.display='none';
					       	}
				 	     
				 		if (document.getElementById('typeOfLc').value == ''){
							document.getElementById('typeOfLcError').style.display='block';
							canSubmit = false;
						}
						else{
							document.getElementById('typeOfLcError').style.display='none';
						}
						 if (document.getElementById('bankType').value == ''){
				 	       		document.getElementById('bankTypeError').style.display='block';
				 	       		canSubmit = false;
				 	       	}
				 	       	else{
				 	       		document.getElementById('bankTypeError').style.display='none';
				 	       	}

						if(canSubmit == false){
							return false;
						}
					}
				 	    </script>

