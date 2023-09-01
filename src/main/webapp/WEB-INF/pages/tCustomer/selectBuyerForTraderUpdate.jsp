<%@include file="taglib_includes.jsp"%>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center"><spring:message code="label.buyerDetails"/></h3>
		</div>
			<form:form name="selectBuyerForTraderUpdate2" action="selectBuyerForTraderUpdate2" method="post" commandName="buyerTradeForm" onsubmit="return val();">

				<form:hidden path="transactionId" value="" />
	             <form:hidden path="cStatus" value=""/>
				<table align="center">

					<tr>
						<td class="col-sm-6"><b><spring:message code="label.id"/></b></td>
						<td class="col-sm-6"><form:input path="id" id="id" readonly="true" />
					</tr>
				
					
						<tr>
						<td class="col-sm-6"><b><spring:message code="label.buyerName"/></b></td>
						<td class="col-sm-6"><form:input path="name" id="name" readonly="true" />
						
						
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.address"/></b></td>
						<td class="col-sm-6"><form:input path="address" id="address"  />
					</tr>
					  <tr>
						<td class="col-sm-6"><b><spring:message code="label.buyerBank"/></b></td>
						<td class="col-sm-6"><form:input path="bank" id="bank" />
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.ifscOrSwift"/></b></td>
						<td class="col-sm-6"><form:input path="ifsc" id="ifsc" />
					</tr>
					
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.contactNumber"/></b></td>
						<td class="col-sm-6"><form:input path="contactNum" id="contactNum" />
				<div id="contactNum1Error" style="display: none; color: red;"><spring:message code="label.validation"/></div>
				<div id="contactNum1Error" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.altContactNo"/></b></td>
						<td class="col-sm-6"><form:input path="altcontactNum" id="altcontactNum"
								 />
					 <div id="altcontactNumError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
					<div id="altcontactNumError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.altEmail"/></b></td>
						<td class="col-sm-6"><form:input path="altEmail" id="altEmail"
								 />
								 <div id="altemailError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="altemailError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
					</tr>
                     <tr>
						<td class="col-sm-6"><b><spring:message code="label.pincode"/></b></td>
						<td class="col-sm-6"><form:input path="pinCode" id="pinCode" />
					</tr>

                      <tr>
						<td class="col-sm-6"><b><spring:message code="label.country"/></b></td>
						<td class="col-sm-6"><form:input path="country" id="country" />
					</tr>
					  <tr>
						<td class="col-sm-6"><b><spring:message code="label.state"/></b></td>
						<td class="col-sm-6"><form:input path="state" id="state" />
					</tr>
					  <tr>
						<td class="col-sm-6"><b><spring:message code="label.city"/></b></td>
						<td class="col-sm-6"><form:input path="city" id="city" />
					</tr>
					  <tr>
						<td class="col-sm-6"><b><spring:message code="label.email"/></b></td>
						<td class="col-sm-6"><form:input path="email" id="email" />
						<div id="emailError1" style="display: none; color: red;"><spring:message code="label.validation"/></div>
					   <div id="emailError1" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
					</tr>
					  <tr>
						<td class="col-sm-6"><b><spring:message code="label.buyerName"/></b></td>
						<td class="col-sm-6"><form:input path="buyerName" id="buyerName" />
					</tr>
					  <tr>
						<td class="col-sm-6"><b><spring:message code="label.buyerComName"/></b></td>
						<td class="col-sm-6"><form:input path="companyName" id="companyName" />
					</tr>
					
					
					
					  <tr>
						<td class="col-sm-6"><b><spring:message code="label.buyerBankEmail"/></b></td>
						<td class="col-sm-6"><form:input path="bankEmail" id="bankEmail" />
						<div id="emailError2" style="display: none; color: red;"><spring:message code="label.validation"/></div>
					   <div id="emailError2" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
					</tr>
					
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.currencyDealt"/>:</b></td>
						<td class="col-sm-6"><form:input path="currencydeal" id="currencydeal"  />
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.buyerBankBranch"/>:</b></td>
						<td class="col-sm-6"><form:input path="branch" value="" id="l1" /></td>

					</tr>

				</table>

				<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="addnewBuyerTrade" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>


			</form:form>

		</div>
	</div>
</div>


</body>

<script>
	function val() {

	debugger;
		var contactNum = document.getElementById('contactNum');
		var altcontactNum = document.getElementById('altcontactNum');
		var email = document.getElementById('email');
		var altEmail = document.getElementById('altEmail');
		var ifsc = document.getElementById("ifsc");
       
    	var phoneNum            = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/; 
		var reg                 = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		var canSubmit = true;

	
		if(bankEmail.value.match(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/) &&!(document.getElementById('bankEmail').value == ''))
	  	{
	  		
	  		document.getElementById('emailError2').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('emailError2').style.display='block';
	  	     canSubmit = false;
	  	}

		if(contactNum.value.match(/^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/) && !(document.getElementById('contactNum').value == '' )) {
	    	 
	    	  document.getElementById('contactNum1Error').style.display='none';
	      }
	      else {
	       
	        document.getElementById('contactNum1Error').style.display='block';
	        canSubmit = false;
	      }

	  	if(email.value.match(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/) &&!(document.getElementById('email').value == ''))
	  	{
	  		
	  		document.getElementById('emailError1').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('emailError1').style.display='block';
	  	     canSubmit = false;
	  	}

	   	 if(altcontactNum.value.match(/^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/) ||(document.getElementById('altcontactNum').value == '' )) {
	    	 
	    	  document.getElementById('altcontactNumError').style.display='none';
	      }
	      else {
	       
	        document.getElementById('altcontactNumError').style.display='block';
	        canSubmit = false;
	      } 
	 
		if(altEmail.value.match(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/) ||(document.getElementById('altEmail').value == ''))
	  	{
	  		
	  		document.getElementById('altemailError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('altemailError').style.display='block';
	  	     canSubmit = false;
	  	}  
	
	

		if (canSubmit == false) {
			return false;
		}
	}
</script>

