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
					
					if (document.getElementById('interestCharge').value == ''){
						document.getElementById('interestChargeError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('interestChargeError').style.display='none';
					}
					if (document.getElementById('noOfDays').value == ''){
						document.getElementById('noOfDaysError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('noOfDaysError').style.display='none';
					}
					
					if(canSubmit == false){
						return false;
					}
				}
			
				
		</script>
	<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3><spring:message code="label.repayment"/></h3>
	</div>
<form:form  action="masterPlanRePaymentDisplayFullConfirm" name="myForm" commandName="fullAmountForm" onsubmit="return val();">

		<div class="col-sm-12 col-md-12">	
					<div class="col-sm-12 col-md-12">
								<table align="center">
						
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerName"/></b></td>
										<td class="col-sm-6"><form:input path="customer"  readonly="true"></form:input>
																								
									</tr>
										
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.masterKey"/></b></td>
										<td class="col-sm-6"><form:input path="masterKey" readonly="true"  id="customerPrefix"></form:input></td>
											
									</tr>
								
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.amountType"/></b></td>
										<td class="col-sm-6"><form:input path="amtType" readonly="true"  id="customerPrefix"></form:input></td>
											
									</tr>														
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.paymentOption"/></b></td>
										<td class="col-sm-6"><form:input path="payOption" readonly="true"  id="customerPrefix"></form:input></td>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.loanDate"/></b></td>
										<td class="col-sm-6"><form:input path="loanDate" readonly="true" placeholder="Enter Loan Date " id="datepicker"></form:input></td>
										<td id="loanDateError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation3"/></font></td>
										    <td id="loanDateError" class="error" style="display:none"><spring:message code="label.validation3"/></td>
											
<%--									</tr>--%>
<%--										<tr>--%>
<%--										<td class="col-sm-6"><b><spring:message code="label.amountToPay"/></b></td>--%>
<%--										<td class="col-sm-6"><form:input path="amount" placeholder="Enter Amount to Pay" id="amount"></form:input></td>--%>
<%--											<td id="amountError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>--%>
<%--										    <td id="amountError" class="error" style="display:none"><spring:message code="label.validation"/></td>--%>
<%--									</tr>--%>
									
<%--									<tr>--%>
<%--										<td class="col-sm-6"><b><spring:message code="label.interestCharge"/></b></td>--%>
<%--										<td class="col-sm-6"><form:input path="intRate" value="0"  id="interestCharge"></form:input></td>--%>
<%--										<td id="interestChargeError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>--%>
<%--										<td id="interestChargeError" class="error" style="display:none"><spring:message code="label.validation"/></td>	--%>
<%--									</tr>--%>

<%--									<tr>--%>
<%--										<td class="col-sm-6"><b><spring:message code="label.tenureindays"/></b></td>--%>
<%--										<td class="col-sm-6"><form:input path="payDate" value="0"  id="noOfDays"></form:input></td>--%>
<%--										<td id="noOfDaysError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>--%>
<%--										 <td id="noOfDaysError" class="error" style="display:none"><spring:message code="label.validation"/></td>	--%>
<%--									</tr>--%>
<%--									<tr>--%>
<%--										<td class="col-sm-6"><input type="button" class="btn btn-success"  value="Calculate Interest Amount" onClick="calcAmt()"/></td>--%>
<%--										 <td class="col-md-6"><form:input path="totalAmount" value="0" readonly="true"/></td>--%>
<%--						        	 </tr>--%>
									<tr>
											<%--										<td class="col-md-6"><input type="button" class="btn btn-success"  value="Calculate Interest Amount" onClick="calcAmt()"/></td>--%>
											<%--										 <td class="col-md-6"><form:input path="totalAmount" value="0" readonly="true" /></td>--%>

										<td class="col-sm-6"><b><spring:message code="label.totalAmount"/></b></td>
										<td class="col-md-6"><form:input path="totalAmount"  readonly="true" /></td>
									</tr>
									</table>
						</div>
						
									<form:hidden path="id"  />
										<form:hidden path="customerEmail"  />
									<form:hidden path="transactionId" />
							<div class="col-sm-12 col-md-12 col-lg-12">
								<p align="center" style="padding-top: 10px;">
								<input type="submit" class="btn btn-primary" value="<spring:message code="label.confirm"/>">
					
								<a href="bankEmp" class="btn btn-success"><spring:message code="label.back"/></a>
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

      $(function() {
    	  $( "#datepicker" ).datepicker({format:'dd/mm/yyyy'});
    	  $( "#datepicker1" ).datepicker({format:'dd/mm/yyyy'});
    	  $( "#datepicker2" ).datepicker({format:'dd/mm/yyyy'});
    	  $( "#datepicker3" ).datepicker({format:'dd/mm/yyyy'});
    	  $( "#datepicker4" ).datepicker({format:'dd/mm/yyyy'});
  });
	   
	   </script>
