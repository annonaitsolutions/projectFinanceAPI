<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.updateHead"/></h3>
			</div>
			<div class="col-sm-12 col-md-6 col-lg-6">
			<form:form action="updateCustomerHeadConfirm" commandName="customerHeadForm" onsubmit="return val();">
			
								<table class="theader">
								<caption><spring:message code="label.addPersonalDetails"/></caption>
								<tr class="rowcolor">
										<td class="col-sm-5"><font color="Red">*</font><b><spring:message code="label.companyName"/></b></td>
										<td class="col-sm-7"><form:input path="companyName" value="${model.customerHeadForm.companyName}" placeholder="Enter company Name" readonly="true" id="companyName"></form:input></td>
										<td id="companyNameError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></td>
										<td id="companyNameError" class="error" style="display:none"><spring:message code="label.validation"/></td>
									</tr>
 								 <tr>  
 										<td class="col-sm-5"><b><spring:message code="label.id"/></b><span style="color:red">*</span></td> 
  										<td class="col-sm-7"><form:input path="id" readonly="true" value="${model.customerHeadForm.id}" placeholder="Enter name"></form:input></td> 
 										<td id="nameError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td> 
 										<td id="nameError" class="error" style="display:none"><spring:message code="label.validation"/></td>  
																									
  								</tr>  
								<tr>
										<td class="col-sm-5"><b><spring:message code="label.customerName"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="name" readonly="true" value="${model.customerHeadForm.name}" placeholder="Enter name"></form:input></td>
										<td id="nameError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										<td id="nameError" class="error" style="display:none"><spring:message code="label.validation"/></td> 
																									
								</tr>
								<tr>
										<td class="col-sm-5"><b><spring:message code="label.gender"/></b></td>
										<td class="col-sm-7"><form:select path="gender" value="${model.customerHeadForm.gender}">
											    <form:option value="Male"><spring:message code="label.male"/></form:option>
												<form:option value="Female"><spring:message code="label.female"/></form:option>
												<form:option value="Transgender"><spring:message code="label.transgender"/></form:option>
												</form:select>
										</td>															
								</tr>
									
								<tr>
										<td class="col-sm-5"><b><spring:message code="label.contactNumber"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="contactNum" value="${model.customerHeadForm.contactNum}" placeholder="Enter contact number" id="contactNum"></form:input></td>
										<td id="contactNum1Error" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></td>
										<td id="contactNum1Error" class="error" style="display:none"><spring:message code="label.validation"/></td>
								</tr>
								<tr>
										<td class="col-sm-5"><b><spring:message code="label.altContactNo"/></b></td>
										<td class="col-sm-7"><form:input path="altContactNum" value="${model.customerHeadForm.altContactNum}"  placeholder="Enter alternative contact number" id="altContactNum"></form:input>
							<td id="contactNum2Error" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></td>
										</td>
								</tr>
								<tr>
										<td class="col-sm-5"><b><spring:message code="label.email"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="email" value="${model.customerHeadForm.email}" placeholder="Enter email id" id="email"></form:input></td>
										<td id="emailError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></td>
										<td id="emailError" class="error" style="display:none"><spring:message code="label.validation"/></td>
																									
								</tr>
								<tr>
										<td class="col-sm-5"><b><spring:message code="label.altEmail"/></b></td>
										<td class="col-sm-7"><form:input path="altEmail" value="${model.customerHeadForm.altEmail}" placeholder="Enter alternative email id" id="altEmail"></form:input>
									<td id="altemailError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></td>
										</td>
								</tr>
								<tr>
										<td class="col-sm-5"><b><spring:message code="label.customerPrefix"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="customerPrefix" value="${model.customerHeadForm.customerPrefix}" placeholder="Enter customer prefix" id="cusmprefix"></form:input></td>
									
										<td id="cusmprefixError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></td>
									    <td id="cusmprefixError" class="error" style="display:none"><spring:message code="label.validation"/></td>
									
								</tr>
									
								<tr>
										<td class="col-sm-5"><b><spring:message code="label.manager"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="manager" placeholder="Enter manager name" id="manager"></form:input></td>
										<td id="managerError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										<td id="managerError" class="error" style="display:none"><spring:message code="label.validation"/></td>
																									
								</tr>
								<tr>
										<td class="col-sm-5"><b><spring:message code="label.managerEmail"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="managerEmail" placeholder="Enter manager email" id="managerEmail"></form:input>
										<td id="managerEmailError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										<td id="managerEmailError" class="error" style="display:none"><spring:message code="label.validation"/></td>
								</tr>
									
						</table>
		  </div>
							<div class="col-sm-12 col-md-12 col-lg-12">
								<p align="center" style="padding-top: 10px;">
								<input type="submit" class="btn btn-primary" value="<spring:message code="label.confirm"/>">
					
								<a href="createCustomerHead" class="btn btn-success"><spring:message code="label.back"/></a>
								</p>
							</div>
					
	        	
			</form:form>
		
				
		 
		<script>
		
		
		
			$('.accordion .item .heading').click(function() {
				
				var a = $(this).closest('.item');
				var b = $(a).hasClass('open');
				var c = $(a).closest('.accordion').find('.open');
					
				if(b != true) {
					$(c).find('.content').slideUp(200);
					$(c).removeClass('open');
				}

				$(a).toggleClass('open');
				$(a).find('.content').slideToggle(200);

			});
			
			function val(){
				
				var categoryName = document.getElementById('name');
				var categoryName = document.getElementById('address');
				 var categoryName = document.getElementById('pincode');
				var categoryName = document.getElementById('city');
				var categoryName = document.getElementById('contactno');
				var categoryName = document.getElementById('email');
				var categoryName = document.getElementById('cusmprefix');
				var companyName = document.getElementById('companyName');
				var companyPrefix = document.getElementById('companyPrefix');
		    	var phoneNum            = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/; 
				var reg                 = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
				
				var canSubmit = true;


				if (document.getElementById('name').value == ''){
					document.getElementById('nameError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('nameError').style.display='none';
				}
				if (document.getElementById('address').value == ''){
					document.getElementById('addressError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('addressError').style.display='none';
				}
				
				 if (document.getElementById('pincode').value == ''){
					document.getElementById('pincodeError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('pincodeError').style.display='none';
				}
				if (document.getElementById('city').value == ''){
					document.getElementById('cityError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('cityError').style.display='none';
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
				if (document.getElementById('cusmprefix').value == ''){
					document.getElementById('cusmprefixError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('cusmprefixError').style.display='none';
				}
				
				if (document.getElementById('companyName').value == ''){
					document.getElementById('companyNameError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('companyNameError').style.display='none';
				}
				if (document.getElementById('companyPrefix').value == ''){
					document.getElementById('companyPrefixError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('companyPrefixError').style.display='none';
				}
				
				if (document.getElementById('manager').value == ''){
					document.getElementById('managerError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('managerError').style.display='none';
				}
				if(managerEmail.value.match(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/) &&!(document.getElementById('managerEmail').value == ''))
			  	{
			  		
			  		document.getElementById('managerEmailError').style.display='none';
			  	
			  	}
			  	else{
			  		document.getElementById('managerEmailError').style.display='block';
			  	     canSubmit = false;
			  	}
				
			if(canSubmit == false){
					return false;
				}
			}
				
		</script>


</div>