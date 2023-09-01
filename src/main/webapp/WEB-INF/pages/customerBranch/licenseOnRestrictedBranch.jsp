<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
<form:form action="licenseOnRestrictedBranchConfirm" method="post" commandName="restrictedLicenseForm" onsubmit="return val();">
	<div class="col-sm-12 col-md-12 col-lg-12 align="center">
		 <div  class="successMsg"><b><font color="green">${success}</font></b></div>
	</div>
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 align="center"><spring:message code="label.licenseDetails"/></h3>
				</div>
		<div class="col-sm-12 col-md-12 col-lg-12 newcustomer_border">
			<div class="content col-sm-12 col-md-12 col-lg-12">
								<table align="center">
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.licenseDetails"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="licenseDetails" id="licenseDetails" placeholder="Enter License Details"></form:input>
										<span id="licenseDetailsError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></span>
									    <span id="licenseDetailsError" class="error" style="display:none"><spring:message code="label.validation"/></span></td>
																									
									</tr>
								<tr>
										<td class="col-sm-6"><b><spring:message code="label.startDate"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="startDate" readonly="true" id="startDate" placeholder="Enter Date"></form:input><i class="fa fa-calendar"></i>
										<span id="startDateError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></span>
									    <span id="startDateError" class="error" style="display:none"><spring:message code="label.validation"/></span></td>
																									
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.endDate"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="endDate" readonly="true" id="endDate" placeholder="Enter Date"></form:input><i class="fa fa-calendar"></i>
										<span id="endDateError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></span>
									    <span id="endDateError" class="error" style="display:none"><spring:message code="label.validation"/></span></td>
																									
									</tr>
									
									<tr>	
											<td class="col-sm-6"><b><spring:message code="label.goodsName"/></b><span style="color:red"></span></td>
											<td class="col-sm-6"><form:input path="goodsName" placeholder="Enter Goods Name" id="goodsName"></form:input>
											<span id="goodsNameError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
											<span id="goodsNameError" class="error" style="display:none"><spring:message code="label.validation"/></span></td> 
																									
									</tr>	
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.weight"/></b><span style="color:red"></span></td>
											<td class="col-sm-6"><form:input path="qty" placeholder="Enter Weight" id="qty"></form:input>
											<span id="qtyError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
											<span id="qtyError" class="error" style="display:none"><spring:message code="label.validation"/></span></td>
																											
									
									<tr>
						</table>
						
				
				
			</div>
		
		
		<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="userBranch" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
		</div>
		</form:form>
		</div> 
		<script>
		 $(document).ready(function() {
			$(':input','#restrictedLicenseForm')
			  .not(':button, :submit, :reset, :hidden')
			  .val('')  
		});
		
		
			
			function val(){
				
				var categoryName = document.getElementById('name');
				var number= /(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/;
				var number1= /^-?[0-9]+$/;
				var size = document.getElementById('size');
				var canSubmit = true;


				if (document.getElementById('licenseDetails').value == ''){
					document.getElementById('licenseDetailsError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('licenseDetailsError').style.display='none';
				}
				if (document.getElementById('startDate').value == ''){
					document.getElementById('startDateError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('startDateError').style.display='none';
				}
				 if (document.getElementById('endDate').value == ''){
					document.getElementById('endDateError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('endDateError').style.display='none';
				}
				
				 if (document.getElementById('goodsName').value == ''){
					document.getElementById('goodsNameError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('goodsNameError').style.display='none';
				}
				
				if(qty.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || qty.value.match(/^-?[0-9]+$/) &&!(document.getElementById('qty').value == ''))
				  	{
				  		
				  		document.getElementById('qtyError').style.display='none';
				  	
				  	}
				  	else{
				  		document.getElementById('qtyError').style.display='block';
				  	     canSubmit = false;
				  	}
					
					
					
					if(canSubmit == false){
					return false;
				}
			}
			
			
			 $(function() {
				  $( "#startDate" ).datepicker({format:'dd/mm/yyyy'});
				  $( "#endDate" ).datepicker({format:'dd/mm/yyyy'});
				   });
			
		</script>

