<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 body_fixed">	
<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3><spring:message code="label.masterPlanAccept"/></h3>
</div>

 <script>
				function val(){
					
					
					var userName  = document.getElementById('comment');
					var number='^\\d+$';

					var canSubmit = true; 
					
					
					
					if(comment.value.match('^\\d+$') &&!(document.getElementById('comment').value == ''))
				  	{
				  		
				  		document.getElementById('commentError').style.display='none';
				  	
				  	}
				  	else{
				  		document.getElementById('commentError').style.display='block';
				  	     canSubmit = false;
				  	}
					
					if(canSubmit == false){
						return false;
					}
				}
			
				
		</script>
<form:form  action="masterPlanRePaymentMadeStatusConfirm" name="myForm" commandName="repaymentForm" onsubmit="return val();">

		<div class="col-sm-12 col-md-12">	
					<div class="col-sm-12 col-md-12">
								<table align="center">
						
									<tr>
										<td class="col-sm-6"><b> <spring:message code="label.customerName"/></b></td>
										<td class="col-sm-6"><form:input path="customer" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
										
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.masterKey"/></b></td>
										<td class="col-sm-6"><form:input path="masterKey" readonly="true"  id="customerPrefix"></form:input>
											
									</tr>
								
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.amountType"/></b></td>
										<td class="col-sm-6"><form:input path="amtType" readonly="true" id="customerPrefix"></form:input>
											
									</tr>														
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.paymentOption"/></b></td>
										<td class="col-sm-6"><form:input path="payOption" readonly="true"  id="customerPrefix"></form:input>
											
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.amountPaid"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="amountPaid" id="comment" placeholder="Enter Amount Paid"></form:input>
										<td id="commentError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										 <td id="commentError" class="error" style="display:none"><spring:message code="label.validation"/></td>
										
									</tr>
									</table>
									</div>
						
									<form:hidden path="id"  />
										<form:hidden path="customerEmail"  />
									<form:hidden path="transactionId" />
			<div class="col-sm-12 col-md-12 col-lg-12" align="center">				
				<input type="submit" class="btn btn-primary" value="<spring:message code="label.confirm"/>">
				 <a	href="masterPlanRePaymentMadeList" class="btn btn-success"><spring:message code="label.back" /></a>
			</div>

		</div>

</form:form>
	</div>
<style>
.link p a{
color: tomato;
}
</style>

