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
						document.getElementById('amount2Error').style.display='none';
					}
					if (document.getElementById('loanDate2').value == ''){
						document.getElementById('loanDate1Error').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('loanDate2Error').style.display='none';
					}
					if (document.getElementById('amount2').value == ''){
						document.getElementById('amount2Error').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('amount2Error').style.display='none';
					}
					if (document.getElementById('loanDate3').value == ''){
						document.getElementById('loanDate3Error').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('loanDate3Error').style.display='none';
					}
					if (document.getElementById('amount3').value == ''){
						document.getElementById('amount3Error').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('amount3Error').style.display='none';
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
<form:form  action="masterPlanRePaymentDisplayBankConfirm" name="myForm" commandName="quarterlyForm" onsubmit="return val();">

		<div class="col-sm-12 col-md-12">	
					<div class="col-sm-12 col-md-12">
								<table align="center">
						
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerName"/></b></td>
										<td class="col-sm-6"><form:input path="customer" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
										
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.masterKey"/></b></td>
										<td class="col-sm-6"><form:input path="masterKey" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
								
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.amountType"/></b></td>
										<td class="col-sm-6"><form:input path="amtType" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>														
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.paymentOption"/></b></td>
										<td class="col-sm-6"><form:input path="payOption" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
<%--									<tr>--%>
<%--										<td class="col-sm-6"><b><spring:message code="label.loanDate"/></b></td>--%>
<%--										<td class="col-sm-6"><form:input path="loanDate" placeholder="Enter Date" id="datepicker"></form:input><i class="fa fa-calendar"></i>--%>
<%--										<td id="loanDateError" class="error" style="display:none;float:right;"><font color="red"><spring:message code="label.validation3"/></font></td>--%>
<%--										    <td id="loanDateError" class="error" style="display:none"><spring:message code="label.validation3"/></td>--%>
<%--											--%>
<%--									</tr>--%>
<%--										<tr>--%>
<%--										<td class="col-sm-6"><b><spring:message code="label.amountToPay"/></b></td>--%>
<%--										<td class="col-sm-6"><form:input path="amount" placeholder="Enter Amount to Pay" id="amount"></form:input>--%>
<%--											<td id="amountError" class="error" style="display:none;float:right;"><font color="red"><spring:message code="label.validation"/></font></td>--%>
<%--										    <td id="amountError" class="error" style="display:none"><spring:message code="label.validation"/></td>--%>
<%--									</tr>--%>

									<tr>
											<%--										<td class="col-md-6"><input type="button" class="btn btn-success"  value="Calculate Interest Amount" onClick="calcAmt()"/></td>--%>
											<%--										 <td class="col-md-6"><form:input path="totalAmount" value="0" readonly="true" /></td>--%>

										<td class="col-sm-6"><b><spring:message code="label.amountToPay"/></b></td>
										<td class="col-md-6"><form:input path="amount"  readonly="true" /></td>
									</tr>
									
<%--									<tr>--%>
<%--										<td class="col-sm-6"><b><spring:message code="label.interestCharge"/></b></td>--%>
<%--										<td class="col-sm-6"><form:input path="intRate" value="0" placeholder="Enter Interest" id="customerPrefix"></form:input>--%>
<%--											--%>
<%--									</tr>--%>
<%--									<tr>--%>
<%--										<td class="col-sm-6"><b><spring:message code="label.noOfDays"/></b></td>--%>
<%--										<td class="col-sm-6"><form:input path="payDate" placeholder="Enter No of Days" id="customerPrefix"></form:input>--%>
<%--											--%>
<%--									</tr>--%>
									<tr>
<%--										<td class="col-md-6"><input type="button" class="btn btn-success"  value="Calculate Interest Amount" onClick="calcAmt()"/></td>--%>
<%--										 <td class="col-md-6"><form:input path="totalAmount" value="0" readonly="true" /></td>--%>

										<td class="col-sm-6"><b><spring:message code="label.totalAmount"/></b></td>
										<td class="col-md-6"><form:input path="totalAmount"  readonly="true" /></td>
						       		  </tr>

									<tr>

										<td class="col-sm-6"><b><spring:message code="label.loanDates"/></b></td>
										<td class="col-sm-6"><form:input path="loanDates" placeholder="Enter No of Days" id="customerPrefix"></form:input>
											<ul>
												<c:forEach var="listValue" items="${loanDates}">
													<li>${listValue}</li>
												</c:forEach>
											</ul>

										</td>
									</tr>

<%--						        	 <tr>--%>
<%--										<td class="col-sm-6"><b><spring:message code="label.loanDate"/></b></td>--%>
<%--										<td class="col-sm-6"><form:input path="loanDate1" placeholder="Enter Date" id="datepicker2"></form:input><i class="fa fa-calendar"></i>--%>
<%--										<td id="loanDate1Error" class="error" style="display:none;float:right;"><font color="red"><spring:message code="label.validation3"/></font></td>--%>
<%--										<td id="loanDate1Error" class="error" style="display:none"><spring:message code="label.validation3"/></td>--%>
<%--									</tr>--%>
<%--									<tr>--%>
<%--										<td class="col-sm-6"><b><spring:message code="label.amountToPay"/></b></td>--%>
<%--										<td class="col-sm-6"><form:input path="amount1" placeholder="Enter Amount to Pay" id="amount1"></form:input>--%>
<%--											<td id="amount1Error" class="error" style="display:none;float:right;"><font color="red"><spring:message code="label.validation"/></font></td>--%>
<%--										    <td id="amount1Error" class="error" style="display:none"><spring:message code="label.validation"/></td>--%>
<%--									</tr>--%>
<%--									--%>
<%--									<tr>--%>
<%--										<td class="col-sm-6"><b><spring:message code="label.interestCharge"/></b></td>--%>
<%--										<td class="col-sm-6"><form:input path="intRate1" value="0" placeholder="Enter Interest" id="customerPrefix"></form:input></td>--%>
<%--											--%>
<%--									</tr>--%>
<%--									<tr>--%>
<%--										<td class="col-sm-6"><b><spring:message code="label.noOfDays"/></b></td>--%>
<%--										<td class="col-sm-6"><form:input path="payDate1" placeholder="Enter No of Days" id="customerPrefix"></form:input></td>--%>
<%--											--%>
<%--									</tr>--%>
<%--									<tr>--%>
<%--										<td class="col-md-6"><input type="button" class="btn btn-success"  value="Calculate Interest Amount" onClick="calcAmt1()"/></td>--%>
<%--										 <td class="col-md-6"><form:input path="totalAmount1" value="0" readonly="true"/></td>--%>
<%--						         </tr>--%>
<%--						         <tr>--%>
<%--										<td class="col-sm-6"><b><spring:message code="label.loanDate"/></b></td>--%>
<%--										<td class="col-sm-6"><form:input path="loanDate2" placeholder="Enter Date" id="datepicker3"></form:input><i class="fa fa-calendar"></i></td>--%>
<%--										<td id="loanDate2Error" class="error" style="display:none;float:right;"><font color="red"><spring:message code="label.validation"/></font></td>--%>
<%--										    <td id="loanDate2Error" class="error" style="display:none"><spring:message code="label.validation"/></td>--%>
<%--											--%>
<%--									</tr>--%>
<%--										<tr>--%>
<%--										<td class="col-sm-6"><b><spring:message code="label.amountToPay"/></b></td>--%>
<%--										<td class="col-sm-6"><form:input path="amount2" placeholder="Enter Amount to Pay" id="amount2"></form:input></td>--%>
<%--											<td id="amount2Error" class="error" style="display:none;float:right;"><font color="red"><spring:message code="label.validation"/></font></td>--%>
<%--										    <td id="amount2Error" class="error" style="display:none"><spring:message code="label.validation"/></td>--%>
<%--									</tr>--%>
<%--									--%>
<%--									<tr>--%>
<%--										<td class="col-sm-6"><b><spring:message code="label.interestCharge"/></b></td>--%>
<%--										<td class="col-sm-6"><form:input path="intRate2" value="0" placeholder="Enter Interest" id="customerPrefix"></form:input></td>--%>
<%--											--%>
<%--									</tr>--%>
<%--									<tr>--%>
<%--										<td class="col-sm-6"><b><spring:message code="label.noOfDays"/></b></td>--%>
<%--										<td class="col-sm-6"><form:input path="payDat2" placeholder="Enter No of Days" id="customerPrefix"></form:input></td>--%>
<%--											--%>
<%--									</tr>--%>
<%--									<tr>--%>
<%--									 <td class="col-md-6"> <input type="button" class="btn btn-success"  value="Calculate Interest Amount" onClick="calcAmt2()"/></td>--%>
<%--									 <td class="col-md-6"><form:input path="totalAmount2" value="0" readonly="true" /></td>--%>
<%--						         </tr>--%>
<%--						         <tr>--%>
<%--										<td class="col-sm-6"><b><spring:message code="label.loanDate"/></b></td>--%>
<%--										<td class="col-sm-6"><form:input path="loanDate3" placeholder="Enter Date" id="datepicker4"></form:input><i class="fa fa-calendar"></i></td>--%>
<%--										<td id="loanDate3Error" class="error" style="display:none;float:right;"><font color="red"><spring:message code="label.validation3"/></font></td>--%>
<%--										    <td id="loanDate3Error" class="error" style="display:none"><spring:message code="label.validation3"/></td>--%>
<%--											--%>
<%--									</tr>--%>
<%--									<tr>--%>
<%--										<td class="col-sm-6"><b><spring:message code="label.amountToPay"/></b></td>--%>
<%--										<td class="col-sm-6"><form:input path="amount3" placeholder="Enter Amount to Pay" id="amount3"></form:input></td>--%>
<%--											<td id="amount3Error" class="error" style="display:none;float:right;"><font color="red"><spring:message code="label.validation"/></font></td>--%>
<%--										    <td id="amount3Error" class="error" style="display:none"><spring:message code="label.validation"/></td>--%>
<%--									</tr>--%>
<%--									--%>
<%--									<tr>--%>
<%--										<td class="col-sm-6"><b><spring:message code="label.interestCharge"/></b></td>--%>
<%--										<td class="col-sm-6"><form:input path="intRate3" value="0" placeholder="Enter Interest" id="customerPrefix"></form:input></td>--%>
<%--											--%>
<%--									</tr>--%>
<%--									<tr>--%>
<%--										<td class="col-sm-6"><b><spring:message code="label.noOfDays"/></b></td>--%>
<%--										<td class="col-sm-6"><form:input path="payDate3" placeholder="Enter No of Days" id="customerPrefix"></form:input></td>--%>
<%--											--%>
<%--									</tr>--%>
<%--									<tr>--%>
<%--										<td class="col-md-6"><input type="button" class="btn btn-success"  value="Calculate Interest Amount" onClick="calcAmt3()"/></td>--%>
<%--										 <td class="col-md-6"><form:input path="totalAmount3" value="0" readonly="true"/></td>--%>
<%--						        	 </tr>--%>
<%--						       --%>
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