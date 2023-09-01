<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


 <script>
				function val(){
					
					var userName  = document.getElementById('loanDate');
					var userName  = document.getElementById('amount');
					

					var canSubmit = true; 
					
					if (document.getElementById('loanDate').value == ''){
						document.getElementById('loanDateError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('loanDateError').style.display='none';
					}
					if (document.getElementById('amount').value == ''){
						document.getElementById('amountError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('amountError').style.display='none';
					}
					if (document.getElementById('loanDate1').value == ''){
						document.getElementById('loanDate1Error').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('loanDate1Error').style.display='none';
					}
					if (document.getElementById('amount1').value == ''){
						document.getElementById('amount1Error').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('amount1Error').style.display='none';
					}
					
					
				

					if(canSubmit == false){
						return false;
					}
				}
			
				
		</script>
		<div class="col-sm-9 col-md-9 body_fixed">	
<div class="col-sm-12 col-md-12 header_customer">
<div class="heading"><h3 align="center"><spring:message code="label.repay"/></h3></div>
</div>
<form:form  action="tmasterPlanRePaymentDisplayHalfConfirm" name="myForm" commandName="thalfYearlyForm" onsubmit="return val();">

		<div class="col-sm-12 col-md-12">	
					<div class="col-sm-12 col-md-12">
								<table align="center">
						
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customer"/>:</b></td>
										<td class="col-sm-6"><form:input path="customer" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
										
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.masterKey"/>:</b></td>
										<td class="col-sm-6"><form:input path="masterKey" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
								
																					
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.paymentOption"/>:</b></td>
										<td class="col-sm-6"><form:input path="payOption" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.loanDte"/>:</b></td>
										<td class="col-sm-6"><form:input path="loanDate" placeholder="Enter Loan Date" id="datepicker" style=" width: 183px;"></form:input><i class="fa fa-calendar"></i></td>
										<td id="loanDateError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										    <td id="loanDateError" class="error" style="display:none"><spring:message code="label.validation"/></td>
											
									</tr>
										<tr>
										<td class="col-sm-6"><b><spring:message code="label.amountToPay"/>:</b></td>
										<td class="col-sm-6"><form:input path="amount" placeholder="Enter Amount to Pay" id="amount"></form:input>
											<td id="amountError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										    <td id="amountError" class="error" style="display:none"><spring:message code="label.validation"/></td>
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.interestCharge"/>:</b></td>
										<td class="col-sm-6"><form:input path="intRate" value="0" placeholder="Enter Interest" id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.noOfDay"/>(%):</b></td>
										<td class="col-sm-6"><form:input path="payDate" value="0" placeholder="Enter Interest" id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-md-6"><input type="button" class="btn btn-success"  value="Calculate Interest Amount" onClick="calcAmt()"/></td>
									 <td class="col-md-6"><form:input path="totalAmount" value="0" readonly="true"/></td>
						         </tr>
						         <tr>
										<td class="col-sm-6"><b><spring:message code="label.loanDte"/>:</b></td>
										<td class="col-sm-6"><form:input path="loanDate1" placeholder="Enter Loan Date" id="datepicker1" style=" width: 183px;"></form:input><i class="fa fa-calendar"></i></td>
										<td id="loanDate1Error" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										 <td id="loanDate1Error" class="error" style="display:none"><spring:message code="label.validation"/></td>
											
									</tr>
										<tr>
										<td class="col-sm-6"><b><spring:message code="label.amountToPay"/>:</b></td>
										<td class="col-sm-6"><form:input path="amount1" placeholder="Enter Interest" id="amount1"></form:input>
											<td id="amount1Error" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										    <td id="amount1Error" class="error" style="display:none"><spring:message code="label.validation"/></td>
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.interestCharge"/>:</b></td>
										<td class="col-sm-6"><form:input path="intRate1" value="0" placeholder="Enter Interest" id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.noOfDay"/>(%):</b></td>
										<td class="col-sm-6"><form:input path="payDate1" value="0" placeholder="Enter Interest" id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6">	<input type="button" class="btn btn-success"  value="Calculate Interest Amount" onClick="calcAmt1()"/></td>
									 <td class="col-md-6"><form:input path="totalAmount1" value="0" readonly="true"/></td>
								
									</tr>
									</table>
						</div>
						
									<form:hidden path="id"  />
										<form:hidden path="customerEmail"  />
									<form:hidden path="transactionId" />
							<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="tmasterPlanRePaymentBank" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
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

      $(function() {
		  $( "#datepicker" ).datepicker({format:'dd/mm/yyyy'});
	 	 $( "#datepicker1" ).datepicker({format:'dd/mm/yyyy'});
	   });
	   
	   </script>
