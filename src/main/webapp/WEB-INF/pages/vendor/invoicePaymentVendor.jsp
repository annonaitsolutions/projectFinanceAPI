<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
	<h3><spring:message code="label.invoice"/></h3>
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
<form:form  action="invoicePaymentVendorConfirm" name="myForm" commandName="invoiceForm" onsubmit="return val();">

		<div class="col-sm-12 col-md-12">	
					<div class="col-sm-12 col-md-12">
								<table align="center">
						<form:hidden path="id"  />
										<form:hidden path="customerHeadEmail"  />
										<form:hidden path="customerBranchEmail"  />
										<form:hidden path="buyerEmail"  />
									<form:hidden path="transactionId" />
								<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerName"/> :</b></td>
										<td class="col-sm-6"><form:input path="customerName" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
										
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.invoiceKey"/>:</b></td>
										<td class="col-sm-6"><form:input path="poKey" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
								
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.buyerName"/>:</b></td>
										<td class="col-sm-6"><form:input path="buyerName" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>														
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.paymentAmount"/>:</b></td>
										<td class="col-sm-6"><form:input path="funalAmt" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.tenure"/>:</b></td>
										<td class="col-sm-6"><form:input path="tenure" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.typeOfPayment"/>:</b><span style="color:red">*</span></td>
											
											<td class="col-sm-6"><form:select path="typeOfTransaction" id="typeOfTrans">
											    <form:option value=""><spring:message code="label.selectValue"/></form:option> 
											    <form:option value="transfer"><spring:message code="label.fundsTransfer"/></form:option>
												<form:option value="cheaque"><spring:message code="label.cheaque"/></form:option>
												<form:option value="DD"><spring:message code="label.demandDraft"/></form:option>
												
												</form:select></td>
										<td id="typeOfTransError" class="error" style="display:none;"><font color="red"><spring:message code="label.plzSelectValue"/></font></td>
					             	  <td id="typeOfTransError" class="error" style="display:none"><spring:message code="label.plzSelectValue"/></td>
						
										
																									
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.chequeNum"/>:</b></td>
										<td class="col-sm-6"><form:input path="chequeNum" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									</table>
									</div>
						
									
							<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="invoicePaymentVendorList" class="btn btn-success"><spring:message code="label.back"/></a></td>
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

