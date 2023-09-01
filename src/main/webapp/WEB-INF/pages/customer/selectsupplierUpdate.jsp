<%@include file="taglib_includes.jsp"%>
<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
		<div class="col-sm-12 col-md-12 col-lg-12">
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center"><spring:message code="label.suppDetails"/></h3>
		</div>
	    <form:form name="selectSupplierUpdate2" action="selectSupplierUpdate2" method="post" commandName="supplierForm" onsubmit="return val();">
             <form:hidden path="transactionId" value="" />

				<table width="400" align="center">

					<tr>
						<td class="heading_text"><b><spring:message code="label.id"/> :</b></td>
						<td><form:input path="id" id="id" readonly="true" />
					</tr>
					<tr>
						<td class="heading_text"><b><spring:message code="label.customerName"/>:</b></td>
						<td><form:input path="name" id="name" readonly="true" />
					</tr>
						<tr>
						<td class="heading_text"><b><spring:message code="label.country"/> :</b></td>
						<td><form:input path="country" id="country" readonly="true" />
					</tr>
					<tr>
						<td class="heading_text"><b><spring:message code="label.state"/> :</b></td>
						<td><form:input path="state" id="state" readonly="true" />
					</tr>
					<tr>
						<td class="heading_text"><b><spring:message code="label.supplierName"/>:</b></td>
						<td><form:input path="supplierName" id="supplierName"  />
						<div id="supplierNameError" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
					</tr>
					<tr>
						<td class="heading_text"><b><spring:message code="label.supplierCompanyName"/>:</b></td>
						<td><form:input path="companyName" id="companyName"  />
							<div id="companyNameError" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
				
					</tr>
					<tr>
						<td class="heading_text"><b><spring:message code="label.supplierBank"/> :</b></td>
						<td><form:input path="bank" id="bank"  />
						<div id="bankError" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
					</tr>
						<tr>
						<td class="heading_text"><b><spring:message
									code="label.ifscOrSwift" /></b><span style="color: red">*</span>
						<td><form:input path="ifsc" id="ifsc"
								placeholder="Enter Ifsc or Swift Code" />
							<div id="ifscError" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
					</tr>
					<tr>
						<td class="heading_text"><b><spring:message code="label.supplierBankEmail"/> :</b></td>
						<td><form:input path="bankEmail" id="bankEmail" />
				
				    <div id="bankEmailError" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
					</tr>
					<tr>
						<td class="heading_text"><b><spring:message code="label.bankBranch"/>:</b></td>
						<td><form:input path="branch" id="branch"  />
							<div id="branchError" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
					</tr>
					<tr>
						<td class="heading_text"><b><spring:message code="label.pincode"/> :</b></td>
						<td><form:input path="pinCode" id="pinCode"  />
							<div id="pinCodeError" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
					</tr>
				
				
					<tr>
						<td class="heading_text"><b><spring:message code="label.city"/> :</b></td>
						<td><form:input path="city" id="city"  />
						<div id="cityError" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
					</tr>
					
					<tr>
						<td class="heading_text"><b><spring:message code="label.contactNumber"/>:</b></td>
						<td><form:input path="contactNum" id="contactNum" />
			
				<div id="contactNum1Error" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
					</tr>

	                 <tr>
						<td class="heading_text"><b><spring:message code="label.altContactNo"/>:</b></td>
						<td><form:input path="altContactNum" id="altContactNum" />
				
				     	<div id="contactNum2Error" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
					</tr>
                       <tr>
						<td class="heading_text"><b><spring:message code="label.email"/> :</b></td>
						<td><form:input path="email" id="email" />
				  
					<div id="emailError" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
						
					</tr>
					   <tr>
						<td class="heading_text"><b><spring:message code="label.altEmail"/>:</b></td>
						<td><form:input path="altEmail" id="altEmail" />
				
							<div id="altemailError" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
					</tr>

					<tr>
						<td class="heading_text"><b><spring:message code="label.address"/> :</b></td>
						<td><form:input path="address" id="address" />
						<div id="addressError" style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div>
					</tr>
					<tr>
						<td class="heading_text"><b><spring:message
									code="label.currencyDealt" /></b><span style="color: red">*</span></td>
						<td><form:select path="currencydeal" id="currencydeal"
								style="width: 206px;">
								<form:option value="">
									<spring:message code="label.selectValue" />
								</form:option>
								<form:option value="Rupee">
									<spring:message code="label.rupee" />
								</form:option>
								<form:option value="Dollar">
									<spring:message code="label.dollar" />
								</form:option>
							</form:select>
								<div id="currencydealError" class="error"
								style="display: none; color: red;">
								<spring:message code="label.validation" />
							</div></td>
					</tr>
				
					

					</table>
			<div class="col-sm-12 col-md-12 col-lg-12">
								<p align="center" style="padding-top: 10px;">
								<input type="submit" class="btn btn-primary" value="<spring:message code="label.confirm"/>">
					
								<a href="newSupplierPage" class="btn btn-success"><spring:message code="label.back"/></a>
								</p>
		</div>

			</form:form>

		</div>
	</div>
	<script>
		function val(){
				
			
		    
				var altContactNum  = document.getElementById('altContactNum');
				var contactNum  = document.getElementById('contactNum');
				var email  = document.getElementById('email');
				var altEmail  = document.getElementById('altEmail');
				var supplierName = document.getElementById('supplierName');
				var companyName = document.getElementById("companyName");
				var bankEmail = document.getElementById('bankEmail');
				var branch = document.getElementById('branch');
				var ifsc = document.getElementById('ifsc');
				var address = document.getElementById('address');
				var pinCode = document.getElementById('pinCode');
				var city = document.getElementById('city');
				var currencydeal = document.getElementById('currencydeal');
				var bank = document.getElementById('bank');
				var phoneNum            = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/; 
				var reg                 = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
				var nameval = /^[A-Za-z][A-Za-z0-9]*$/;
				var canSubmit = true;
				

				if (supplierName.value.match(/^[A-Za-z][A-Za-z0-9]*$/)
						&& !(document.getElementById('supplierName').value == '')) {

					document.getElementById('supplierNameError').style.display = 'none';
				} else {

					document.getElementById('supplierNameError').style.display = 'block';
					canSubmit = false;
				}
		 
				if (document.getElementById('companyName').value == '') {
					document.getElementById('companyNameError').style.display = 'block';
					canSubmit = false;
				} else {
					document.getElementById('companyNameError').style.display = 'none';
				}
		
				if (document.getElementById('bank').value == '') {
					document.getElementById('bankError').style.display = 'block';
					canSubmit = false;
				} else {
					document.getElementById('bankError').style.display = 'none';
				}
				
				
				if (document.getElementById('ifsc').value == '') {
					document.getElementById('ifscError').style.display = 'block';
					canSubmit = false;
				} else {
					document.getElementById('ifscError').style.display = 'none';
				}
			
				if (document.getElementById('address').value == '') {
					document.getElementById('addressError').style.display = 'block';
					canSubmit = false;
				} else {
					document.getElementById('addressError').style.display = 'none';
				}
		
				if (document.getElementById('pinCode').value == '') {
					document.getElementById('pinCodeError').style.display = 'block';
					canSubmit = false;
				} else {
					document.getElementById('pinCodeError').style.display = 'none';
				}
			
				if (document.getElementById('city').value == '') {
					document.getElementById('cityError').style.display = 'block';
					canSubmit = false;
				} else {
					document.getElementById('cityError').style.display = 'none';
				}
				if (document.getElementById('branch').value == '') {
					document.getElementById('branchError').style.display = 'block';
					canSubmit = false;
				} else {
					document.getElementById('branchError').style.display = 'none';
				}
				
				if (document.getElementById('currencydeal').value == '') {
					document.getElementById('currencydealError').style.display = 'block';
					canSubmit = false;
				} else {
					document.getElementById('currencydealError').style.display = 'none';
				}
	
			
				
				 if(contactNum.value.match(/^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/)&&!(document.getElementById('contactNum').value == '' )) {
			    	 
			    	  document.getElementById('contactNum1Error').style.display='none';
			      }
			      else {
			       
			        document.getElementById('contactNum1Error').style.display='block';
			        canSubmit = false;
			      }
				 if (document.getElementById('ifsc').value == '') {
						document.getElementById('ifscError').style.display = 'block';
						canSubmit = false;
					} else {
						document.getElementById('ifscError').style.display = 'none';
					}
				if(email.value.match(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/) &&!(document.getElementById('email').value == ''))
				  	{
				  		
				  		document.getElementById('emailError').style.display='none';
				  	
				  	}
				  	else{
				  		document.getElementById('emailError').style.display='block';
				  	     canSubmit = false;
				  	}
				
				
					 if(altContactNum.value.match(/^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/) ||(document.getElementById('altContactNum').value == '' )) {
				    	 
				    	  document.getElementById('contactNum2Error').style.display='none';
				      }
				      else {
				       
				        document.getElementById('contactNum2Error').style.display='block';
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
				 
				 if(bankEmail.value.match(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/) &&!(document.getElementById('bankEmail').value == ''))
				  	{
				  		
				  		document.getElementById('bankEmailError').style.display='none';
				  	
				  	}
				  	else{
				  		document.getElementById('bankEmailError').style.display='block';
				  	     canSubmit = false;
				  	} 
				
				if(canSubmit == false){
					return false;
				}
			}
				
		</script>
