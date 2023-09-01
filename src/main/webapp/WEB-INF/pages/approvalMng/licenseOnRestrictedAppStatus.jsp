<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
<form:form action="licenseOnRestrictedAppStatusConfirm" method="post" commandName="restrictedLicenseForm" onsubmit="return val();">
	<div class="col-sm-12 col-md-12 col-lg-12 align="center">
		 <div  class="successMsg"><b><font color="green">${success}</font></b></div>
	</div>
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 align="center"><spring:message code="label.licenseDetails"/></h3>
				</div>
		<div class="col-sm-12 col-md-12 col-lg-12 newcustomer_border">
			<div class="content col-sm-12 col-md-12 col-lg-12">
			<form:hidden path="transId"/>
			<form:hidden path="id"/>
								<table align="center">
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.licenseDetails"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="licenseDetails" id="licenseDetails" readonly="true" ></form:input>
																								
									</tr>
								<tr>
										<td class="col-sm-6"><b><spring:message code="label.startDate"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="startDate" readonly="true" id="startDate" placeholder="Enter Date"></form:input>
																									
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.endDate"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="endDate" readonly="true" id="endDate" placeholder="Enter Date"></form:input>
																								
									</tr>
									
									<tr>	
											<td class="col-sm-6"><b><spring:message code="label.goodsName"/></b><span style="color:red"></span></td>
											<td class="col-sm-6"><form:input path="goodsName" readonly="true"  id="goodsName"></form:input>
																								
									</tr>	
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.weight"/></b><span style="color:red"></span></td>
											<td class="col-sm-6"><form:input path="qty" readonly="true" id="qty"></form:input>
											
									<tr>
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.status"/></b><span style="color:red">*</span></td>
											
											<td class="col-sm-6"><form:select path="status" id="status" style="width: 207px;">
											    <form:option value=""><spring:message code="label.selectValue"/></form:option> 
											    <form:option value="Approved"><spring:message code="label.approve"/></form:option>
												<form:option value="Rejected"><spring:message code="label.reject"/></form:option>
												</form:select></td>
										    <td id="statusError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation2"/></font></td>
										    <td id="statusError" class="error" style="display:none"><spring:message code="label.validation2"/></td>
																									
									</tr>
									<tr>	
											<td class="col-sm-6"><b><spring:message code="label.comment"/></b><span style="color:red"></span></td>
											<td class="col-sm-6"><form:input path="comment" placeholder="Enter comment" id="comment"></form:input>
											<span id="commentError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
											<span id="commentError" class="error" style="display:none"><spring:message code="label.validation"/></span></td> 
																									
									</tr>
						</table>
						
				
				
			</div>
		
		
		<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="licenseOnRestrictedApp" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
		</div>
		</form:form>
		</div> 
		<script>
		
		
		
			
			function val(){
				
				var categoryName = document.getElementById('name');
				var number= /(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/;
				var number1= /^-?[0-9]+$/;
				var size = document.getElementById('size');
				var canSubmit = true;


				if (document.getElementById('status').value == ''){
					document.getElementById('statusError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('statusError').style.display='none';
				}
				if (document.getElementById('comment').value == ''){
					document.getElementById('commentError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('commentError').style.display='none';
				}
					
					
					
					if(canSubmit == false){
					return false;
				}
			}
			
			
			
		</script>

