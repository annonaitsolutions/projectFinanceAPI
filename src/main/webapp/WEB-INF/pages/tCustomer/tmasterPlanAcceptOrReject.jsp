<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3><spring:message code="label.masterPlan"/></h3>
	</div>
<form:form action="tmasterPlanAcceptOrRejectConfirm" commandName="tMasterPlanForm" onsubmit="return val();">

			<div class="col-sm-12 col-md-12 col-lg-12">
								<table align="center">
						
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerName"/>:</b></td>
										<td class="col-sm-6"><form:input path="customer"  readonly="true"></form:input></td>
																								
									</tr>
											<form:hidden path="transactionId"  />
											<form:hidden path="buyingCostSanc"  />
											<form:hidden path="id"  />	
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.masterKey"/>:</b></td>
										<td class="col-sm-6"><form:input path="masterKey" readonly="true"  id="customerPrefix"></form:input></td>
											
									</tr>
									
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.acc/rej"/>:</b><span style="color:red">*</span></td>
											
											<td class="col-sm-6"><form:select path="accept" id="status" style="width: 207px;">
											    <form:option value=""><spring:message code="label.selectValue"/></form:option> 
											    <form:option value="Yes"><spring:message code="label.yes"/></form:option>
												<form:option value="No"><spring:message code="label.no"/></form:option>
												</form:select></td>
										    <td id="statusError" class="error" style="display:none;"><font color="red"><spring:message code="label.plzSelectValue"/></font></td>
										    <td id="statusError" class="error" style="display:none"><spring:message code="label.plzSelectValue"/></td>
																									
									</tr>
							
									</table>
								<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="tmasterPlanAccept" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
									</div>
								


</form:form>
</div>
	<script>
		function val(){
					var status  = document.getElementById('status');
					var canSubmit = true; 
					
					if (document.getElementById('status').value == ''){
						document.getElementById('statusError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('statusError').style.display='none';
					}
					
					if(canSubmit == false){
						return false;
					}
				}
</script>


