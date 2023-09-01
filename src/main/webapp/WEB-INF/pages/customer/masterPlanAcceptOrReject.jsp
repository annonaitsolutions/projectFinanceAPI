<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3><spring:message code="label.masterPlanAccept"/></h3>
	</div>
<form:form action="masterPlanAcceptOrRejectConfirm" commandName="masterPlanForm" onsubmit="return val();">

			<div class="col-sm-12 col-md-12 col-lg-12">
								<table align="center">
						
									<tr>
										<td class="col-sm-6"><b> <spring:message code="label.name"/></b></td>
										<td class="col-sm-6"><form:input path="customer"  readonly="true"></form:input></td>
																								
									</tr>
											<form:hidden path="transactionId"  />
											<form:hidden path="buyingCostSanc"  />
											<form:hidden path="id"  />	
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.masterKey"/></b></td>
										<td class="col-sm-6"><form:input path="masterKey" readonly="true"  id="customerPrefix"></form:input></td>
											
									</tr>
									
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.accept"/></b><span style="color:red">*</span></td>
											
											<td class="col-sm-6"><form:select path="accept" id="accept" style="width: 207px;">
											    <form:option value=""><spring:message code="label.selectValue"/></form:option> 
											    <form:option value="Yes"><spring:message code="label.yes"/></form:option>
												<form:option value="No"><spring:message code="label.no"/></form:option>
												</form:select></td>
										    <td id="acceptError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation2"/></font></td>
										    <td id="acceptError" class="error" style="display:none"><spring:message code="label.validation2"/></td>
																									
									</tr>
							
									</table>
								<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="masterPlanAccept" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
									</div>
								


</form:form>
</div>
	<script>
		
			
				 
				function val(){
					
					var accept  = document.getElementById('accept');
					

					var canSubmit = true; 
					
					if (document.getElementById('accept').value == ''){
						document.getElementById('acceptError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('acceptError').style.display='none';
					}
				

					if(canSubmit == false){
						return false;
					}
				}
			
				
		</script>


