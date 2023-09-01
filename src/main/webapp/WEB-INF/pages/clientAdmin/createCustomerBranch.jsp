<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3 align="center"><spring:message code="label.addBranchDetails"/></h3>
	</div>	
	<div class="col-sm-12 col-md-12 col-lg-12">
		<div  class="successMsg"><b><font color="green">${success}</font></b></div>
	</div>
	<form:form action="customerBranchesConfirm" method="post" commandName="customerBranchForm" onsubmit="return val();">
	
	 		
	 		<div class="col-sm-12 col-md-12 col-lg-12 heading_color">
				<h3><spring:message code="label.customerHeadDetails"/></h3>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
								<table>
									<tr class="rowcolor">
										<td class="col-sm-6"><b><spring:message code="label.customerHeadName"/><font color="Red">&nbsp;*</font>&nbsp;:</b></td>
										<td class="col-sm-6"><form:input path="customerHeadName" value="${model.customerBranchForm.customerHeadName}" readonly="true" placeholder="Enter company Name" id="companyName"></form:input></td>
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerHeadPrefix"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
										<td class="col-sm-6"><form:input path="customerPrefix" value="${model.customerBranchForm.customerPrefix}" readonly="true" placeholder="Enter Company Prefix" id="companyPrefix"></form:input></td>
										  													
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerHeadKey"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
										<td class="col-sm-6"><form:input path="customerHeadKey" value="${model.customerBranchForm.customerHeadKey}" readonly="true" placeholder="Enter Company Prefix" id="companyPrefix"></form:input></td>
										  													
									</tr>
									
								</table>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12 heading_color">
					<h3><spring:message code="label.addNewDeatils"/></h3>
			</div>
			<div class="col-sm-12 col-md-6 col-lg-6">
					
					<div class="col-sm-12 col-md-12 col-lg-12">
								<table class="theader">
								<form:hidden path="id"/>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerName"/><font color="Red">&nbsp;*</font>&nbsp;:</b></td>
										<td class="col-sm-6"><form:input path="name" placeholder="Enter Name"></form:input></td>
									</tr>
									<tr>
										<td id="nameError" class="error" style="display:none;float:right;"><font color="red"><spring:message code="label.validation"/>*</font></td>
										<td id="nameError" class="error" style="display:none"><spring:message code="label.validation"/></td> 
									</tr>
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.gender"/><font color="Red">&nbsp;*</font>&nbsp;:</b></td>
											
											<td class="col-sm-6"><form:select path="gender" cssStyle="width:206px;">
											    <form:option value="Male"><spring:message code="label.male"/></form:option>
												<form:option value="Female"><spring:message code="label.female"/></form:option>
												<form:option value="Transgender"><spring:message code="label.transgender"/></form:option>
												</form:select>
											</td>															
									</tr>
									
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.roleType"/><font color="Red">&nbsp;*</font>&nbsp;:</b></td>
											
											<td class="col-sm-6"><form:select path="roleType" id="roleType" cssStyle="width:206px;">
											 <form:option value=""><spring:message code="label.selectValue"/></form:option> 		
											    <form:option value="Branch"><spring:message code="label.branch"/></form:option>
												<form:option value="Subsidiary"><spring:message code="label.subsidiary"/></form:option>
													</form:select>
						             <td id="roleTypeError" class="error" style="display:none;"><font color="red"><spring:message code="label.selectValue"/></font></td>
						            <td id="roleTypeError" class="error" style="display:none"><spring:message code="label.selectValue"/></td>
																									
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.contactNumber"/><font color="Red">&nbsp;*</font>&nbsp;:</b></td>
										<td class="col-sm-6"><form:input path="contactNum" placeholder="Enter Contact Number" id="contactNum"></form:input></td>
								<td id="contactNum1Error" class="error" style="display:none;float:right;"><font color="red"><spring:message code="label.validation"/>*</font></td>
								<td id="contactNum1Error" class="error" style="display:none"><spring:message code="label.validation"/></td>
									</tr>
								
								
									
								
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.altContactNo"/><font color="Red">&nbsp;*</font>&nbsp;:</b></td>
										<td class="col-sm-6"><form:input path="altContactNum" placeholder="Enter Alternative Contact Number" id="altContactNum"></form:input></td>
							     <td id="contactNum2Error" class="error" style="display:none;float:right;"><font color="red"><spring:message code="label.validation"/>*</font></td>
								<td id="contactNum2Error" class="error" style="display:none"><spring:message code="label.validation"/></td>
									
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.email"/><font color="Red">&nbsp;*</font>&nbsp;:</b></td>
										<td class="col-sm-6"><form:input path="email" placeholder="Enter Email" id="email"></form:input></td>
								<td id="emailError" class="error" style="display:none;float:right;"><font color="red"><spring:message code="label.validation"/>*</font></td>
								 <td id="emailError" class="error" style="display:none"><spring:message code="label.validation"/></td>	
									</tr>
								
											
																									
								
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.altEmail"/><font color="Red">&nbsp;*</font>&nbsp;:</b></td>
										<td class="col-sm-6"><form:input path="altEmail" placeholder="Enter Alternative Email" id="altEmail"></form:input>
								<td id="altemailError" class="error" style="display:none;float:right;"><font color="red"><spring:message code="label.validation"/>*</font></td>
								<td id="altemailError" class="error" style="display:none"><spring:message code="label.validation"/></td>
									
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerBranchPrefix"/><font color="Red">&nbsp;*</font>&nbsp;:</b></td>
										<td class="col-sm-6"><form:input path="customerBranchPrefix" placeholder="Enter Customer Prefix" id="cusmprefix"></form:input></td>
									</tr>
									<tr>
									    <td id="cusmprefixError" class="error" style="display:none;float:right;"><font color="red"><spring:message code="label.validation"/>*</font></td>
									    <td id="cusmprefixError" class="error" style="display:none"><spring:message code="label.validation"/></td>
									</tr>
								</table>
					</div>
			</div>
					<div class="col-sm-12 col-md-6 col-lg-6">
						<div class="col-sm-12 col-md-12 col-lg-12">
								<table>	
									<tr>	
										<td class="col-sm-6"><b><spring:message code="label.address"/><font color="Red">&nbsp;*</font>&nbsp;:</b></td>
										<td class="col-sm-6"><form:input path="address" placeholder="Enter Address" id="address"></form:input></td>
									</tr>
									<tr>
										 <td id="addressError" class="error col-sm-6" style="display:none;float:right;"><font color="red"><spring:message code="label.validation"/>*</font></td>
										 <td id="addressError" class="error" style="display:none"><spring:message code="label.validation"/></td> 
									</tr>	
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.pincode"/><font color="Red">&nbsp;*</font>&nbsp;:</b></td>
											<td class="col-sm-6"><form:input path="pincode" placeholder="Enter Pincode" id="pincode"></form:input></td>
									</tr>
									<tr>
											<td id="pincodeError" class="error col-sm-6" style="display:none;float:right;"><font color="red"><spring:message code="label.validation"/>*</font></td>
											<td id="pincodeError" class="error" style="display:none"><spring:message code="label.validation"/></td>
																											
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.country"/><font color="Red">&nbsp;*</font>&nbsp;:</b></td>
											<td class="col-sm-6">
											<form:select path="country" id="country" placeholder="Select Country" cssStyle="width:206px;"></form:select>
											<div id="countryError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
											</td>
									</tr>
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.state"/><font color="Red">&nbsp;*</font>&nbsp;:</b></td>
											<td class="col-sm-6"><form:select path="state" placeholder="Select State" id ="state" cssStyle="width:206px;"></form:select>
											<script>
												 populateCountries("country", "state");
											</script>
									<div id="stateError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
											</td>																
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.city"/><font color="Red">&nbsp;*</font>&nbsp;:</b></td>
										<td class="col-sm-6"><form:input path="city" placeholder="Enter City" id="city"></form:input></td>
									</tr>
									<tr>
										<td id="cityError" class="error col-sm-6" style="display:none;float:right;"><font color="red"><spring:message code="label.validation"/>*</font></td>
										<td id="cityError" class="error" style="display:none"><spring:message code="label.validation"/></td>
																								
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.manager"/><font color="Red">&nbsp;*</font>&nbsp;:</b></td>
										<td class="col-sm-6"><form:input path="manager" placeholder="Enter Manager Name" id="manager"></form:input></td>
									</tr>
									<tr>
											<td id="managerError" class="error col-sm-6" style="display:none;float:right;"><font color="red"><spring:message code="label.validation"/></font></td>
										    <td id="managerError" class="error" style="display:none"><spring:message code="label.validation"/></td>
																									
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.managerEmail"/><font color="Red">&nbsp;*</font>&nbsp;:</b></td>
										<td class="col-sm-6"><form:input path="managerEmail" placeholder="Enter Manager Email" id="managerEmail"></form:input></td>
									</tr>
									<tr>
											<td id="managerEmailError" class="error col-sm-6" style="display:none;float:right;"><font color="red"><spring:message code="label.validation"/></font></td>
										    <td id="managerEmailError" class="error" style="display:none"><spring:message code="label.validation"/></td>
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.accountExpiryDate"/></b><span style="color:red">*</span></td>										
										<td id="e1" class="col-sm-6"><form:input path="accExpiryDate" class="form-control" placeholder="Select Date" style="width: 206px;" readonly="true" id="datepicker"/><i class="fa fa-calendar"></i>
									
										<span id="expiryDateError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
									   </td>
									
									</tr>
									
						</table>
					</div>	
				</div>
					<div class="col-sm-12 col-md-12 col-lg-12">
								<p align="center" style="padding-top: 10px;">
						<input type="submit" class="btn btn-primary" value="<spring:message code="label.confirm"/>">
					
						<a href="bankEmp" class="btn btn-success" name="back"  onclick="javascript:window.location='#';"><spring:message code="label.back"/></a>
						</p>
					</div>
		
					</form:form>
		
		<div class="col-sm-12 col-md-12 col-lg-12">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.customerHeadList"/></h3>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.transactionId"/></th>
						<th><spring:message code="label.customerHeadKey"/></th>
						<th><spring:message code="label.customerHeadName"/></th>
						<th><spring:message code="label.address"/></th>
						<th><spring:message code="label.status"/></th>
						<th><spring:message code="label.action"/></th>
					
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty customerList}">
						<c:forEach items="${customerList}" var="customer">
							<tr>
								<td><c:out value="${customer.id}"></c:out></td>
								<td><c:out value="${customer.name}"></c:out>
								<td><c:out value="${customer.transactionId}"></c:out>
								<td><c:out value="${customer.customerHeadKey}"></c:out></td>
								<td><c:out value="${customer.customerHeadName}"></c:out></td>
								<td><c:out value="${customer.address}"></c:out></td>
								<td><c:out value="${customer.status}"></c:out></td>
								<td></td>


								<td><a href="selectCustomerBranch?id=${customer.id}"
									class="btn btn-primary"><spring:message code="label.edit"/></a></td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
		</div>
</div>						 
		<script>
		
		 $(function() {
			  $( "#datepicker" ).datepicker({format: 'dd/mm/yyyy'});
			   });
		 
		 /* $(document).ready(function() {
			$(':input','#customerBranchForm')
			  .not(':button, :submit, :reset, :hidden')
			  .val('')  
		});  */
		
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
				
				var name = document.getElementById('name');
				var address = document.getElementById('address');
				 var categoryName = document.getElementById('pincode');
				var categoryName = document.getElementById('city');
				var categoryName = document.getElementById('contactno');
				var categoryName = document.getElementById('email');
				var categoryName = document.getElementById('cusmprefix');
				var companyName = document.getElementById('companyName');
				var companyPrefix = document.getElementById('companyPrefix');
				
				var accExpiryDate = document.getElementById('datepicker').value;
		    	var phoneNum            = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/; 
				var reg                 = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
				var nameval             = /^[A-Za-z][A-Za-z0-9]*$/;
				
				var canSubmit = true;


				
				 if(name.value.match(/^[A-Za-z][A-Za-z0-9]*$/) && !(document.getElementById('name').value == '' )) {
			    	 
			    	  document.getElementById('nameError').style.display='none';
			      }
			      else {
			       
			        document.getElementById('nameError').style.display='block';
			        canSubmit = false;
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
					    if (document.getElementById('country').value == '-1') {
						document.getElementById('countryError').style.display = 'block';
						canSubmit = false;
					} else {
						document.getElementById('countryError').style.display = 'none';
					}

					if (document.getElementById('state').value == '-1'
							|| document.getElementById('state').value == '') {
						document.getElementById('stateError').style.display = 'block';
						canSubmit = false;
					} else {
						document.getElementById('stateError').style.display = 'none';
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
				if (accExpiryDate == ''){
					document.getElementById('expiryDateError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('expiryDateError').style.display='none';
				}
				if (document.getElementById('roleType').value == ''){
					document.getElementById('roleTypeError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('roleTypeError').style.display='none';
				}
				
			if(canSubmit == false){
					return false;
				}
			}
				
		</script>
