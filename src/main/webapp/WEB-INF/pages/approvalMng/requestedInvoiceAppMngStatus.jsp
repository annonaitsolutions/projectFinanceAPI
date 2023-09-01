<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


 <script>
				function val(){
					
					var userName  = document.getElementById('transStatus');
					

					var canSubmit = true; 
					
					if (document.getElementById('transStatus').value == ''){
						document.getElementById('transStatusError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('transStatusError').style.display='none';
					}
					if (document.getElementById('transComment').value == ''){
						document.getElementById('transCommentError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('transCommentError').style.display='none';
					}
					
				

					if(canSubmit == false){
						return false;
					}
				}
			
				
		</script>
<div class="col-sm-9 col-md-9 body_fixed">	
<div class="col-sm-12 col-md-12 header_customer">
<div class="heading"><h3 align="center"><spring:message code="label.setRateApproval"/></h3></div>
</div>
<form:form  action="requestedInvoiceAppMngStatusConfirm" name="myForm" commandName="invoiceForm" onsubmit="return val();">

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
										<td class="col-sm-6"><b><spring:message code="label.paymentAmount"/></b></td>
										<td class="col-sm-6"><form:input path="funalAmt" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
									<tr>
										<td class="col-sm-6"><b> <spring:message code="label.tenure"/></b></td>
										<td class="col-sm-6"><form:input path="tenure" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
									<tr>
									<td class="col-sm-6"><b><spring:message code="label.status"/></b><span style="color:red">*</span></td>
									
									<td class="col-sm-6"><form:select path="transStatus" id="transStatus" style="width:205px;">
									    <form:option value=""><spring:message code="label.selectValue"/></form:option> 
									    <form:option value="Approved"><spring:message code="label.approve"/></form:option>
										<form:option value="Rejected"><spring:message code="label.reject"/></form:option>
										</form:select>
									<td id="transStatusError" class="error" style="display:none;"><font color="red"><spring:message code="label.pleaseSelectAValue"/></font></td>
								    <td id="transStatusError" class="error" style="display:none"><spring:message code="label.pleaseSelectAValue"/></td>
									
										
																									
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.comment"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="transComment" id="transComment" placeholder="Enter Comment"></form:input></td>
										<td id="transCommentError" class="error" style="display:none;"><font color="red"><spring:message code="label.commentValidation"/></font></td>
										    <td id="transCommentError" class="error" style="display:none"><spring:message code="label.commentValidation"/></td>
										
									</tr>
						        
									</table>
						</div>
						
									<form:hidden path="id"  />
									<form:hidden path="transactionId" />
							<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="requestedInvoiceAppMng" class="btn btn-success"><spring:message code="label.back"/></a></td>
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

