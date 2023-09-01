<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="col-sm-9 col-md-9">	
<div class="col-sm-12 col-md-12">
<div class="heading"><h3 align="center">PO Payment</h3></div>
</div>

<form:form  action="poPaymentSubsLcPageConfirm" name="msplan" commandName="purchaseOrderForm" onsubmit="return val();">

		<div class="col-sm-12 col-md-12">	
					<div class="col-sm-12 col-md-6">
								<table class="theader">
						<form:hidden path="id"  />
										<form:hidden path="customerHeadEmail"  />
										<form:hidden path="customerBranchEmail"  />
										<form:hidden path="supplierEmail"  />
									<form:hidden path="transactionId" />
									<tr>
										<td class="col-sm-5"><b> Customer:</b></td>
										<td class="col-sm-7"><form:input path="customerName" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
									<tr>
										<td class="col-sm-5"><b> Customer Head:</b></td>
										<td class="col-sm-7"><form:input path="customerHeadName" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
									<tr>
										<td class="col-sm-5"><b> Supplier Name:</b></td>
										<td class="col-sm-7"><form:input path="supplierName" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
										
									
									<tr>
										<td class="col-sm-5"><b>PO Key:</b></td>
										<td class="col-sm-7"><form:input path="poKey" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
								
									<tr>
										<td class="col-sm-5"><b>Amount:</b></td>
										<td class="col-sm-7"><form:input path="amount" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
								<tr>
										<td class="col-sm-5"><b>Type Of Payment:</b></td>
										<td class="col-sm-7"><form:input path="typeOfTrans" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
																							
								<tr>
											<td class="col-sm-2" style="width:23.666667%;"><b>Type Of LC:</b><span style="color:red">*</span></td>
											
											<td class="col-sm-8"><form:select path="typeOfLc" id="typeOfLc" style="width:86.9%">
											    <form:option value="">Select A Value</form:option> 
											    <form:option value="documentory">Documentory LC</form:option>
												<form:option value="standBy">StandBy Lc</form:option>
												
												</form:select>
										<td id="typeOfLcError" class="error" style="display:none;"><font color="red">Please Select A Value</font></td>
					               <td id="typeOfLcError" class="error" style="display:none">Please Select A Value</td>
						
											</td>	
																									
									</tr>
						
									
									<tr>
											<td class="col-sm-2" style="width:23.666667%;"><b>Type Of Bank:</b><span style="color:red">*</span></td>
											
											<td class="col-sm-8"><form:select path="bankType" id="bankType" style="width:86.9%">
											    <form:option value="">Select A Value</form:option> 
											    <form:option value="International">International</form:option>
												<form:option value="Domestic">Domestic</form:option>
												
												</form:select>
										<td id="bankTypeError" class="error" style="display:none;"><font color="red">Please Select A Value</font></td>
					               <td id="bankTypeError" class="error" style="display:none">Please Select A Value</td>
						
											</td>	
																									
									</tr>

									</table>
									</div>
									
									<table class="theader">
						
									<tr>
										<td class="col-sm-5"><b> Bank Name:</b>
										<td class="col-sm-7"><form:input path="bankName" id="bankName" placeholder="Enter name"></form:input>
											<td id="bankNameError" class="error" style="display:none;"><font color="red">Please Enter Bank Name</font></td>
					               <td id="bankNameError" class="error" style="display:none">Please Enter Bank Name</td>
						
											</td>													
									</tr>
									<tr>
										<td class="col-sm-5"><b> Bank Branch:</b></td>
										<td class="col-sm-7"><form:input path="bankBranch" id="bankBranch" placeholder="Enter name" ></form:input>
					               <td id="bankBranchError" class="error" style="display:none;"><font color="red">Please Enter Bank Branch</font></td>
					               <td id="bankBranchError" class="error" style="display:none">Please Enter Bank Branch</td>
																			
									</tr>
									<tr>
										<td class="col-sm-5"><b> Bank Address:</b></td>
										<td class="col-sm-7"><form:input path="bankAddress"  id="bankAddress" placeholder="Enter name" ></form:input>
											 <td id="bankAddressError" class="error" style="display:none;"><font color="red">Please Enter Bank Address</font></td>
					               <td id="bankAddressError" class="error" style="display:none">Please Enter Bank Address</td>
																							
									</tr>
										
									
									<tr>
										<td class="col-sm-5"><b>Swift Code:</b></td>
										<td class="col-sm-7"><form:input path="swiftCode"  placeholder="Enter contact number" id="swiftCode"></form:input>
											 <td id="swiftCodeError" class="error" style="display:none;"><font color="red">Please Enter Swift Code</font></td>
					               <td id="swiftCodeError" class="error" style="display:none">Please Enter Swift Code</td>
											
									</tr>
								
									<tr>
										<td class="col-sm-5"><b>Ac Num:</b></td>
										<td class="col-sm-7"><form:input path="accNo"  placeholder="Enter ac number" id="accNo"></form:input>
												 <td id="accNoError" class="error" style="display:none;"><font color="red">Please Enter Acc Num</font></td>
					               <td id="accNoError" class="error" style="display:none">Please Enter Acc Num</td>
											
									</tr>
									<tr>
										<td class="col-sm-5"><b>Contact Num:</b></td>
										<td class="col-sm-7"><form:input path="contactNum"  placeholder="Enter contact number" id="contactNum"></form:input>
												 <td id="contactNumError" class="error" style="display:none;"><font color="red">Please Enter Contact Num</font></td>
					               <td id="contactNumError" class="error" style="display:none">Please Enter Contact Num</td>
											
									</tr>
									
									</table>
									
									
							<div class="col-sm-12 col-md-12 col-lg-12">	
								<p align="center" style="padding-top: 10px;">
					
					<input type="submit" class="btn btn-primary" value="Save">
								<a href="bankEmp" class="btn btn-success" name="back"  onclick="javascript:window.location='#';"/>back</a>
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

