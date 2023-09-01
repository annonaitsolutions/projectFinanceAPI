<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<script>
		
			
				 
				function val(){
					
					var userName  = document.getElementById('amount');
					

					var canSubmit = true; 
					
					if (document.getElementById('amount').value == ''){
						document.getElementById('amountError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('amountError').style.display='none';
					}
				

					if(canSubmit == false){
						return false;
					}
				}
			
				
		</script>

<section>
<form:form action="subsFundsTransfer1Confirm"  commandName="fundsDistributeForm" onsubmit="return val();">
<div class="heading">Request</div>
			<div class="content">
				<div class="container">
	 <div  class="sucess"><b><font color="green">${success}</font></b></div>
								<table class="theader">
								<form:hidden path="id"  />
								<form:hidden path="custHeadEmail"  />
								<form:hidden path="custHeadMngEmail"  />
								<form:hidden path="email"  />
								<form:hidden path="managerEmail"  />
								<form:hidden path="balance"  />
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b>Customer Name:</b></td>
										<td class="col-sm-8"><form:input path="customerName" placeholder="Enter name" readonly="true" style="width:85%" ></form:input>
																								
									</tr>
									
									
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b>Customer Head Name:</b></td>
										<td class="col-sm-8"><form:input path="customerHeadName" readonly="true" placeholder="Enter contact number" id="customerPrefix" style="width:85%"></form:input>
											
									</tr>
									
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b>Master Key:</b></td>
										<td class="col-sm-8"><form:input path="masterKey" readonly="true" placeholder="Enter Company Name" id="companyName" style="width:85%"></form:input>
																									
									</tr>
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b>Distributed Amount:</b></td>
										<td class="col-sm-8"><form:input path="distributedAmount" readonly="true" placeholder="Enter Company Name" id="companyName" style="width:85%"></form:input>
																									
									</tr>
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b>Utilized Amount:</b></td>
										<td class="col-sm-8"><form:input path="utilizedAmount" readonly="true" placeholder="Enter Company Name" id="companyName" style="width:85%"></form:input>
																									
									</tr>
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b>Available Amount:</b></td>
										<td class="col-sm-8"><form:input path="busBalance" readonly="true" placeholder="Enter Amount" id="companyName" style="width:85%"></form:input>
									
									</tr>
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b>Amount:</b>
										<td id="amountError" class="error" style="display:none;"><font color="red">*Please Enter  Amount</font></td>
										    <td id="amountError" class="error" style="display:none">Please Enter Amount</td>
											</td>
										<td class="col-sm-8"><form:input path="amount"  placeholder="Enter Amount" id="amount" style="width:85%"></form:input>
									
									</tr>
									
											<p align="center" style="padding-top: 10px;">
					<input type="submit" class="btn btn-primary" value="Confirm">
					
					<a href="bankEmp" class="btn btn-success" name="back"  onclick="javascript:window.location='#';"/>back</a>
				</p>
									</table>
									</div>
								


</form:form>

</section>

