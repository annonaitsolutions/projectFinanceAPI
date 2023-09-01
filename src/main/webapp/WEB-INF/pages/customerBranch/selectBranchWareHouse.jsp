<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
<form:form action="updateBranchWareHouseConfirm" method="post" commandName="wareHouseForm" onsubmit="return val();">
	 <div  class="successMsg"><b><font color="green">${success}</font></b></div>
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 align="center"><spring:message code="label.wareHouse"/></h3>
				</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
			
								<table align="center">
							
								<form:hidden path="id"/>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerName"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="customerName" id="customerName" readonly="true" placeholder="Enter Name"></form:input>
																								
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.whName"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="wareHouseName" id="wareHouseName" placeholder="Enter Name"></form:input>
										<span id="wareHouseNameError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
									    <span id="wareHouseNameError" class="error" style="display:none"><spring:message code="label.validation"/></span></td>
																									
									</tr>
								<tr>
										<td class="col-sm-6"><b><spring:message code="label.personInCharge"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="personInCharge" id="personInCharge" placeholder="Enter Name"></form:input>
										<span id="personInChargeError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
									    <span id="personInChargeError" class="error" style="display:none"><spring:message code="label.validation"/></span></td>
																									
									</tr>
								
									<tr>	
											<td class="col-sm-6"><b><spring:message code="label.address"/></b><span style="color:red">*</span></td>
											<td class="col-sm-6"><form:input path="address" placeholder="Enter address" id="address"></form:input>
											<span id="addressError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
											<span id="addressError" class="error" style="display:none"><spring:message code="label.validation"/></span></td> 
																									
									</tr>	
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.pincode"/></b><span style="color:red">*</span></td>
											<td class="col-sm-6"><form:input path="pinCode" placeholder="Enter pincode" id="pincode"></form:input>
											<span id="pincodeError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
											<span id="pincodeError" class="error" style="display:none"><spring:message code="label.validation"/></span></td>
																											
									
									<tr>
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.capacity"/></b><span style="color:red">*</span></td>
											<td class="col-sm-6"><form:input path="capacity" placeholder="Enter pincode" id="capacity"></form:input>
											<span id="capacityError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
											<span id="capacityError" class="error" style="display:none"><spring:message code="label.validation"/></span></td>
																											
									
									<tr>
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.size"/></b><span style="color:red">*</span></td>
											<td class="col-sm-6"><form:input path="size" placeholder="Enter pincode" id="size"></form:input>
											<span id="sizeError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
											<span id="sizeError" class="error" style="display:none"><spring:message code="label.validation"/></span></td>
																											
									
									<tr>
									
									
									
						</table>
						
				
				
			</div>
		
		
		<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="createBranchWareHouse" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
		
		</form:form>
		
		</div>		
	 
		<script>
	
			
			function val(){
				
				var categoryName = document.getElementById('name');
				
				
				var canSubmit = true;


				if (document.getElementById('customerName').value == ''){
					document.getElementById('customerNameError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('customerNameError').style.display='none';
				}
				if (document.getElementById('wareHouseName').value == ''){
					document.getElementById('wareHouseNameError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('wareHouseNameError').style.display='none';
				}
				if (document.getElementById('personInCharge').value == ''){
					document.getElementById('personInChargeError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('personInChargeError').style.display='none';
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
				 if (document.getElementById('country').value == ''){
						document.getElementById('countryError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('countryError').style.display='none';
					}
				 
				 if (document.getElementById('state').value == '-1'
					    || document.getElementById('state').value == '') {
					   document.getElementById('stateError').style.display = 'block';
					   canSubmit = false;
					  } else {
					   document.getElementById('stateError').style.display = 'none';
					  }
				 if (document.getElementById('capacity').value == ''){
						document.getElementById('capacityError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('capacityError').style.display='none';
					}
				 if (document.getElementById('size').value == ''){
						document.getElementById('sizeError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('sizeError').style.display='none';
					}
			
				
				
			if(canSubmit == false){
					return false;
				}
			}
			
		
		</script>

