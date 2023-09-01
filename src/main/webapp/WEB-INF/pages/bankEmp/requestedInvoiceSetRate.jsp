<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


 <script>
				function val(){
					
					var userName  = document.getElementById('percentage');
					var number= /(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/;
					

					var canSubmit = true; 
					
				if(percentage.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || percentage.value.match(/^-?[0-9]+$/) &&!(document.getElementById('percentage').value == ''))
				  	{
				  		
				  		document.getElementById('percentageError').style.display='none';
				  	
				  	}
				  	else{
				  		document.getElementById('percentageError').style.display='block';
				  	     canSubmit = false;
				  	}

					if(canSubmit == false){
						return false;
					}
				}
			
				
		</script>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3><spring:message code="label.setRate"/></h3>
		</div>
<form:form  action="requestedInvoiceSetRateConfirm" name="myForm" commandName="invoiceForm" onsubmit="return val();">

		<div class="col-sm-12 col-md-12">	
					<div class="col-sm-12 col-md-12">
								<table align="center">
						
									<tr>
										<td class="col-sm-6"><b> <spring:message code="label.customerName"/></b></td>
										<td class="col-sm-6"><form:input path="customerName" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
										
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.invoiceKey"/></b></td>
										<td class="col-sm-6"><form:input path="poKey" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
								
									<tr>
										<td class="col-sm-6"><b> <spring:message code="label.buyerName"/></b></td>
										<td class="col-sm-6"><form:input path="buyerName" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>														
									<tr>
										<td class="col-sm-6"><b> <spring:message code="label.amount"/></b></td>
										<td class="col-sm-6"><form:input path="amount" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.interestCharge"/></b></td>
										<td class="col-sm-6"><form:input path="percentage" value="0" placeholder="Enter Interest" id="percentage"></form:input>
										<td id="percentageError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										    <td id="percentageError" class="error" style="display:none"><spring:message code="label.validation"/></td>
												
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.tenure"/></b></td>
										<td class="col-sm-6"><form:input path="tenure" readonly="true"  id="customerPrefix"></form:input></td>
											
									</tr>
									<tr>
										<td class="col-sm-6"><input type="button" class="btn btn-success"  value="Calculate Interest Amount" onClick="calcInvoice()"/></td>
										 <td class="col-sm-6"><form:input path="funalAmt" value="0" readonly="true"/></td>
						         	</tr>
									</table>
						</div>
						
									<form:hidden path="id"  />
									<form:hidden path="transactionId" />
								</table>
				<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" size="3" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="requestedInvoiceBank" class="btn btn-success"><spring:message code="label.back"/></a></td>
							
					
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

