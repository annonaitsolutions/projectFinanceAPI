<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<section>
			<div class="row-fluid apps">
				<h3 align="center"><spring:message code="label.updateCustomerSubsidiary"/></h3>
				<form:form action="updateCustomerSubsidiaryConfirm"
					commandName="customerSubsidiaryForm" onsubmit="return validateForm()">
					<table align="center">

		
			<div class="heading"><spring:message code="label.editDetails"/></div>
			<div class="content">
								<table class="theader">
								<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.id"/></b><span style="color:red">*</span></td>
										<td class="col-sm-8"><form:input path="id" value="${model.customerSubsidiaryForm.id}" readonly="true" placeholder="Enter name" style="width:85%" ></form:input>
										<td id="nameError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
											<td id="nameError" class="error" style="display:none"><spring:message code="label.validation"/></td> 
																									
									</tr>
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b> <spring:message code="label.name"/></b><span style="color:red">*</span></td>
										<td class="col-sm-8"><form:input readonly="true" path="name" value="${model.customerSubsidiaryForm.name}" placeholder="Enter name" style="width:85%" ></form:input>
										<td id="nameError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
											<td id="nameError" class="error" style="display:none"><spring:message code="label.validation"/></td> 
																									
									</tr>
									<tr>
											<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.gender"/></b></td>
											
											<td class="col-sm-8"><form:select path="gender" value="${model.customerSubsidiaryForm.gender}" style="width:86.9%">
											    <form:option value="Male"><spring:message code="label.male"/></form:option>
												<form:option value="Female"><spring:message code="label.female"/></form:option>
												<form:option value="Transgender"><spring:message code="label.transgender"/></form:option>
												</form:select>
										
											</td>															
									</tr>
									
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.contactNumber"/></b><span style="color:red">*</span></td>
										<td class="col-sm-8"><form:input path="contactNum" value="${model.customerSubsidiaryForm.contactNum}" placeholder="Enter contact number" id="contactno" style="width:85%"></form:input>
											<td id="contactnoError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										  <td id="contactnoError" class="error" style="display:none"><spring:message code="label.validation"/></td>
									
									</tr>
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.altContactNo"/></b></td>
										<td class="col-sm-8"><form:input path="altContactNum" value="${model.customerSubsidiaryForm.altContactNum}"  placeholder="Enter alternative contact number" id="txtAlternateContactNumber" style="width:85%"></form:input>
										</td>
									</tr>
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.email"/></b><span style="color:red">*</span></td>
										<td class="col-sm-8"><form:input path="email" value="${model.customerSubsidiaryForm.email}" placeholder="Enter email id" id="email" style="width:85%"></form:input>
											<td id="emailError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										    <td id="emailError" class="error" style="display:none"><spring:message code="label.validation"/></td>
																									
									</tr>
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.altEmail"/></b></td>
										<td class="col-sm-8"><form:input path="altEmail" value="${model.customerSubsidiaryForm.altEmail}" placeholder="Enter alternative email id" id="txtAlternateEmail" style="width:85%"></form:input>
										</td>
									</tr>
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.customerPrefix"/></b><span style="color:red">*</span></td>
										<td class="col-sm-8"><form:input path="customerPrefix" value="${model.customerSubsidiaryForm.customerPrefix}" placeholder="Enter customer prefix" id="cusmprefix" style="width:85%"></form:input>
									
										<td id="cusmprefixError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
									    <td id="cusmprefixError" class="error" style="display:none"><spring:message code="label.validation"/></td>
									
									</tr>
									
									
									<tr>	
											<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.address"/></b><span style="color:red">*</span></td>
											<td class="col-sm-8"><form:input path="address" value="${model.customerSubsidiaryForm.address}" placeholder="Enter address" id="address" style="width:85%"></form:input>
											 <td id="addressError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
											<td id="addressError" class="error" style="display:none"><spring:message code="label.validation"/></td> 
																									
									</tr>	
									<tr>
											<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.pincode"/></b><span style="color:red">*</span></td>
											<td class="col-sm-8"><form:input path="pincode" value="${model.customerSubsidiaryForm.pincode}" placeholder="Enter pincode" id="pincode" style="width:85%"></form:input>
												<td id="pincodeError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
												<td id="pincodeError" class="error" style="display:none"><spring:message code="label.validation"/></td>
																											
									<tr>
											<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.country"/></b><span style="color:red">*</span></td>
											<td class="col-sm-8">
											<form:select path="country" id="country" value="${model.customerSubsidiaryForm.country}"  placeholder="Select country" style="width:86.9%"></form:select>
											</td>
									</tr>
									<tr>
											<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.state"/></b><span style="color:red">*</span></td>
											<td class="col-sm-8"><form:select path="state" value="${model.customerSubsidiaryForm.state}"  placeholder="Select state" id ="state" style="width:87%"></form:select>
											<script language="javascript">
												 populateCountries("country", "state");
											</script>
										
											</td>																
									</tr>
									
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.manager"/></b><span style="color:red">*</span></td>
										<td class="col-sm-8"><form:input path="manager" placeholder="Enter manager name" id="manager" style="width:85%"></form:input>
											<td id="managerError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										    <td id="managerError" class="error" style="display:none"><spring:message code="label.validation"/></td>
																									
									</tr>
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.managerEmail"/></b><span style="color:red">*</span></td>
										<td class="col-sm-8"><form:input path="managerEmail" placeholder="Enter manager email" id="managerEmail" style="width:85%"></form:input>
											<td id="managerEmailError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										    <td id="managerEmailError" class="error" style="display:none"><spring:message code="label.validation"/></td>
																									
									</tr>
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.city"/></b><span style="color:red">*</span></div>
										<td class="col-sm-8"><form:input path="city" value="${model.customerSubsidiaryForm.city}"  placeholder="Enter city" id="city" style="width:85%"></form:input>
										   <td id="cityError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										  <td id="cityError" class="error" style="display:none"><spring:message code="label.validation"/></td>
																								
									</tr>
									
						</table>
						
				
				
			</div>
		  </div>
		   					
			</div>
		  </div>
								<p align="center" style="padding-top: 10px;">
					<input type="submit" class="btn btn-primary" value="<spring:message code="label.confirm"/>">
					
					<a href="bankEmp" class="btn btn-success" name="back"  onclick="javascript:window.location='#';"/><spring:message code="label.back"/></a>
				</p>
					</div>
		</div>
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
				if (document.getElementById('manager').value == ''){
					document.getElementById('managerError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('managerError').style.display='none';
				}
				if (document.getElementById('managerEmail').value == ''){
					document.getElementById('managerEmailError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('managerEmailError').style.display='none';
				}
				
			if(canSubmit == false){
					return false;
				}
			}
				
		</script>


</section>
