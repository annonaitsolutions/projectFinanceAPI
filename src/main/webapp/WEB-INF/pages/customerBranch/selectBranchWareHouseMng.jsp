<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.updateHead"/></h3>
			</div>
			<div class="col-sm-12 col-md-6 col-lg-6">
			<form:form action="updateBranchWareHouseMngConfirm" commandName="wareHouseMngForm" onsubmit="return val()">
			
								<table class="theader">
								<caption><spring:message code="label.addPersonalDetails"/></caption>
								<tr>
										<td class="col-sm-5"><b><spring:message code="label.id"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="id" readonly="true" value="${model.wareHouseMngForm.id}" placeholder="Enter name"></form:input></td>
										<td id="nameError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										<td id="nameError" class="error" style="display:none"><spring:message code="label.validation"/></td> 
																									
								</tr>
								<tr>
										<td class="col-sm-5"><b><spring:message code="label.managerName"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="mngName" readonly="true" value="${model.wareHouseMngForm.mngName}" placeholder="Enter name"></form:input></td>
										<td id="nameError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										<td id="nameError" class="error" style="display:none"><spring:message code="label.validation"/></td> 
																									
								</tr>
								<tr>
										<td class="col-sm-5"><b><spring:message code="label.gender"/></b></td>
										<td class="col-sm-7"><form:select path="gender" value="${model.wareHouseMngForm.gender}">
											    <form:option value="Male"><spring:message code="label.male"/></form:option>
												<form:option value="Female"><spring:message code="label.female"/></form:option>
												<form:option value="Transgender"><spring:message code="label.transgender"/></form:option>
												</form:select>
										</td>															
								</tr>
									
								<tr>
										<td class="col-sm-5"><b><spring:message code="label.contactNumber"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="contactNum" value="${model.wareHouseMngForm.contactNum}" placeholder="Enter contact number" id="contactno"></form:input></td>
										<td id="contactnoError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></td>
										<td id="contactnoError" class="error" style="display:none"><spring:message code="label.validation"/></td>
								</tr>
								<tr>
										<td class="col-sm-5"><b><spring:message code="label.altContactNo"/></b></td>
										<td class="col-sm-7"><form:input path="altContactNum" value="${model.wareHouseMngForm.altContactNum}"  placeholder="Enter alternative contact number" id="txtAlternateContactNumber"></form:input>
										</td>
								</tr>
								<tr>
										<td class="col-sm-5"><b><spring:message code="label.email"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="email" value="${model.wareHouseMngForm.email}" placeholder="Enter email id" id="email"></form:input></td>
										<td id="emailError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></td>
										<td id="emailError" class="error" style="display:none"><spring:message code="label.validation"/></td>
																									
								</tr>
								<tr>
										<td class="col-sm-5"><b><spring:message code="label.altEmail"/></b></td>
										<td class="col-sm-7"><form:input path="altEmail" value="${model.wareHouseMngForm.altEmail}" placeholder="Enter alternative email id" id="txtAlternateEmail"></form:input>
										</td>
								</tr>
								<tr>
										<td class="col-sm-5"><b><spring:message code="label.customerPrefix"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="customerPrefix" value="${model.wareHouseMngForm.customerPrefix}" placeholder="Enter customer prefix" id="cusmprefix"></form:input></td>
									
										<td id="cusmprefixError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></td>
									    <td id="cusmprefixError" class="error" style="display:none"><spring:message code="label.validation"/></td>
									
								</tr>
									
							
									
						</table>
		  </div>
		   					
		  <div class="col-sm-12 col-md-6 col-lg-6">
			
							<table class="theader broad">
								<caption><spring:message code="label.companyDetails"/></caption>
									<tr class="rowcolor">
										<td class="col-sm-5"><font color="Red">*</font><b><spring:message code="label.companyName"/></b></td>
										<td class="col-sm-7"><form:input path="companyName" value="${model.wareHouseMngForm.companyName}" placeholder="Enter company Name" id="companyName"></form:input></td>
										<td id="companyNameError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></td>
										<td id="companyNameError" class="error" style="display:none"><spring:message code="label.validation"/></td>
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.companyPrefix"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="companyPrefix" value="${model.wareHouseMngForm.companyPrefix}" placeholder="Enter Company Prefix" id="companyPrefix"></form:input></td>
										<td id="companyPrefixError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></td>
										<td id="companyPrefixError" class="error" style="display:none"><spring:message code="label.validation"/></td>
																								
									</tr>
									<tr>	
										<td class="col-sm-5"><b><spring:message code="label.address"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="address" value="${model.wareHouseMngForm.address}" placeholder="Enter address" id="address"></form:input></td>
										<td id="addressError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></td>
										<td id="addressError" class="error" style="display:none"><spring:message code="label.validation"/></td> 
																									
									</tr>	
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.pincode"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="pincode" value="${model.wareHouseMngForm.pincode}" placeholder="Enter pincode" id="pincode"></form:input></td>
										<td id="pincodeError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></td>
										<td id="pincodeError" class="error" style="display:none"><spring:message code="label.validation"/></td>
																											
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.country"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7">
										<form:select path="country" id="country" value="${model.wareHouseMngForm.country}"  placeholder="Select country"></form:select>
										</td>
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.state"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:select path="state" value="${model.wareHouseMngForm.state}"  placeholder="Select state" id ="state"></form:select>
											<script>
												 populateCountries("country", "state");
											</script>
										</td>																
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.city"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="city" value="${model.wareHouseMngForm.city}"  placeholder="Enter city" id="city"></form:input></td>
										<td id="cityError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></td>
										<td id="cityError" class="error" style="display:none"><spring:message code="label.validation"/></td>
									</tr>
								</table>
		</div>
							<div class="col-sm-12 col-md-12 col-lg-12">
								<p align="center" style="padding-top: 10px;">
								<input type="submit" class="btn btn-primary" value="<spring:message code="label.confirm"/>">
					
								<a href="createBranchWareHouseMng" class="btn btn-success"   onclick="javascript:window.location='#';"/><spring:message code="label.back"/></a>
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
				if (document.getElementById('contactno').value == ''){
					document.getElementById('contactnoError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('contactnoError').style.display='none';
				}
				if (document.getElementById('email').value == ''){
					document.getElementById('emailError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('emailError').style.display='none';
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
				
				
			if(canSubmit == false){
					return false;
				}
			}
				
		</script>


</div>