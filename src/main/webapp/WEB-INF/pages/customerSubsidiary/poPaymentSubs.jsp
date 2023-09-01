<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="col-sm-9 col-md-9">	
<div class="col-sm-12 col-md-12">
<div class="heading"><h3 align="center">PO Payment</h3></div>
</div>

 <script>
				function val(){
					
					var userName  = document.getElementById('typeOfTrans');
					

					var canSubmit = true; 
					
					if (document.getElementById('typeOfTrans').value == ''){
						document.getElementById('typeOfTransError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('typeOfTransError').style.display='none';
					}
					

					if(canSubmit == false){
						return false;
					}
				}
			
				
		</script>
<form:form  action="poPaymentSubsConfirm" name="myForm" commandName="purchaseOrderForm" onsubmit="return val();">

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
											<td class="col-sm-2" style="width:23.666667%;"><b>Type Of Payment:</b><span style="color:red">*</span></td>
											
											<td class="col-sm-8"><form:select path="typeOfTrans" id="typeOfTrans" style="width:86.9%">
											    <form:option value="">Select A Value</form:option> 
											    <form:option value="transfer">Funds Transfer</form:option>
												<form:option value="Cheque">Cheaque</form:option>
												<form:option value="DD">Demand Draft</form:option>
												<form:option value="LC">LC</form:option>
												<form:option value="BG">Bank Gaurantee</form:option>
												</form:select>
										<td id="typeOfTransError" class="error" style="display:none;"><font color="red">Please Select A Value</font></td>
					               <td id="typeOfTransError" class="error" style="display:none">Please Select A Value</td>
						
											</td>	
																									
									</tr>
									
									<tr>
										<td class="col-sm-5"><b>Cheque Num:</b></td>
										<td class="col-sm-7"><form:input path="chequenum" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									</table>
									</div>
						
									
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

