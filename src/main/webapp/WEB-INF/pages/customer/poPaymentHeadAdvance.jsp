<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


 <script>
 $( document ).ready(function() {
	 debugger;
		$("#chequeNum").hide();
		
	});
				function val(){
					debugger;
					
					var userName  = document.getElementById('typeOfTrans');
					

					var canSubmit = true; 
					
					if (document.getElementById('typeOfTrans').value == 'transfer'){
						document.getElementById('typeOfTransError').style.display='block';
						document.getElementById('chequenum').style.display='none';
						canSubmit = true;
					}
					if (document.getElementById('typeOfTrans').value == 'Cash'){
						document.getElementById('typeOfTransError').style.display='block';
						document.getElementById('chequenum').style.display='none';
						canSubmit = true;
					}
					
					if (document.getElementById('typeOfTrans').value == ''){
						document.getElementById('typeOfTransError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('typeOfTransError').style.display='none';
					}
					if (document.getElementById('payNow').value == ''){
						document.getElementById('payNowError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('payNowError').style.display='none';
					}
					
					if ($('#chequenum').css('display') != 'none') {
					    if($('#chequenum').val() == '') {					    	
					    	document.getElementById('chequenumError').style.display='block';
					    	canSubmit = false;
					    }else {
					    	document.getElementById('chequenumError').style.display='none';
					    }
					    if($('#signAuth').val() == '') {					    	
					    	document.getElementById('signAuthError').style.display='block';
					    	canSubmit = false;
					    }else {
					    	document.getElementById('signAuthError').style.display='none';
					    }
					    if($('#amtLimit').val() == '') {					    	
					    	document.getElementById('amtLimitError').style.display='block';
					    	canSubmit = false;
					    }else {
					    	document.getElementById('amtLimitError').style.display='none';
					    }
					    /* if(amtLimit.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || amtLimit.value.match(/^-?[0-9]+$/) && !('#amtLimit').value == '')
					  	{
					  		document.getElementById('amtLimitError').style.display='none';
					  	}
					  	else{
					  		document.getElementById('amtLimitError').style.display='block';
					  	     canSubmit = false;
					  	} */
					   
					    
					}

					if(canSubmit == false){
						return false;
					}
				}
			
				
		</script>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3><spring:message code="label.poPayment"/></h3>
	</div>
<form:form  action="poPaymentHeadAdvanceConfirm" name="myForm" commandName="purchaseOrderForm" onsubmit="return val();">

		<div class="col-sm-12 col-md-12">	
		<%-- <div class="col-sm-3 col-md-3 col-lg-3">
				
		<video id="video1" width="300" height="300" controls autoplay loop>
			<source src="<%=request.getContextPath()%>/resources/videos/advPayment.mp4" type="video/mp4">
		</video>
	</div>  --%> 
					<div class="col-sm-9 col-md-9">
								<table align="center">
						<form:hidden path="id"  />
										<form:hidden path="customerHeadEmail"  />
										<form:hidden path="customerBranchEmail"  />
										<form:hidden path="supplierEmail"  />
									<form:hidden path="transactionId" />
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerName"/>:</b></td>
										<td class="col-sm-6"><form:input path="customerName"  readonly="true"></form:input>
																								
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerHeadName"/>:</b></td>
										<td class="col-sm-6"><form:input path="customerHeadName" readonly="true"></form:input>
																								
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.supplierName"/>:</b></td>
										<td class="col-sm-6"><form:input path="supplierName"  readonly="true"></form:input>
																								
									</tr>
										
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.poKey"/>:</b></td>
										<td class="col-sm-6"><form:input path="poKey" readonly="true" id="customerPrefix"></form:input>
											
									</tr>
								
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.amount"/>:</b></td>
										<td class="col-sm-6"><form:input path="amount" readonly="true"  id="customerPrefix"></form:input>
											
									</tr>														
								
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.advPaid"/>:</b></td>
										<td class="col-sm-6"><form:input path="payAdvance" readonly="true"  id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.balance"/>:</b></td>
										<td class="col-sm-6"><form:input path="payBalance" readonly="true"  id="customerPrefix"></form:input>
											
									</tr>
										<tr>
										<td class="col-sm-6"><b><spring:message code="label.payNow"/>:</b></td>
										<td class="col-sm-6"><form:input path="payNow"  id="payNow"></form:input>
									<td id="payNowError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
					               <td id="payNowError" class="error" style="display:none"><spring:message code="label.validation"/></td>
						
									</tr>
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.typeOfPayment"/>:</b><span style="color:red">*</span></td>
											
											<td class="col-sm-6"><form:select path="typeOfTrans" id="typeOfTrans" style="width:205px;">
											    <form:option value=""><spring:message code="label.selectValue"/></form:option> 
											    <form:option value="transfer"><spring:message code="label.fundsTransfer"/></form:option>
												<form:option value="Cheque"><spring:message code="label.cheaque"/></form:option>
												<form:option value="Cash"><spring:message code="label.cash"/></form:option>
												<form:option value="DD"><spring:message code="label.demandDraft"/></form:option>
											 </form:select>
										<td id="typeOfTransError" class="error" style="display:none;"><font color="red"><spring:message code="label.plzSelectValue"/>e</font></td>
					               <td id="typeOfTransError" class="error" style="display:none"><spring:message code="label.plzSelectValue"/></td>
						
																									
									</tr>
									</table>
					<table  align="center" id="chequeNum">
					<tr>
						<td class="col-sm-6" ><b><spring:message
									code="label.chequeNum" />:</b></td>
						<td class="col-sm-6"><form:input path="chequenum"
								id="chequenum"></form:input></td>
								<td id="chequenumError" class="error" style="display:none"><font color="red"><spring:message code="label.validation"/></font></td>
					</tr>
					<tr>
						<td class="col-sm-6" ><b><spring:message
									code="label.signAuth" />:</b></td>
						<td class="col-sm-6"><form:input path="signAuth"
								id="signAuth"></form:input></td>
								<td id="signAuthError" class="error" style="display:none"><font color="red"><spring:message code="label.validation"/></font></td>
					</tr>
					<tr>
						<td class="col-sm-6" ><b><spring:message
									code="label.amtLimit" />:</b></td>
						<td class="col-sm-6"><form:input path="amtLimit"
								id="amtLimit"></form:input></td>
								<td id="amtLimitError" class="error" style="display:none"><font color="red"><spring:message code="label.validation"/></font></td>
					</tr>
					</table>
									 
									</div>
						
									
							<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="poPaymentHeadList" class="btn btn-success"><spring:message code="label.back"/></a></td>
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
$('select').on('change', function() {
	debugger;
	  if(this.value == 'Cheque' || this.value == 'DD') {		  
			$("#chequeNum").show();
	  }else {		  
			$("#chequeNum").hide();
			document.getElementById('chequenumError').style.display='none';
			document.getElementById('signAuthError').style.display='none';
			document.getElementById('amtLimitError').style.display='none';
	  }
	});
</script>
