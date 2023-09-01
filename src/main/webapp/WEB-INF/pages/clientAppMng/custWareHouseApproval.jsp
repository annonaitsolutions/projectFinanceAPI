<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="col-sm-9 col-md-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3><spring:message code="label.wareHouse"/></h3>
	</div>
<form:form action="custWareHouseApprovalConfirm" commandName="wareHouseForm" onsubmit="return val();">
	
			<div class="col-sm-12 col-md-12 col-lg-12">
								<table align="center">
								<form:hidden path="id"  />
								<form:hidden path="transactionId"  />
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerName"/>:</b></td>
										<td class="col-sm-6"><form:input path="customerName"  readonly="true"></form:input></td>
																								
									</tr>
									
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.whName"/>:</b></td>
										<td class="col-sm-6"><form:input path="wareHouseName" readonly="true"  id="customerPrefix"></form:input></td>
											
									</tr>
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.status"/>:</b><span style="color:red">*</span></td>
											
											<td class="col-sm-6"><form:select path="status" id="status">
											    <form:option value=""><spring:message code="label.selectValue"/></form:option> 
											    <form:option value="Approved"><spring:message code="label.approve"/></form:option>
												<form:option value="Rejected"><spring:message code="label.reject"/></form:option></td>
												<td id="statusError" class="error" style="display:none;"><font color="red"><spring:message code="label.plzSelectValue"/></font></td>
										   		 <td id="statusError" class="error" style="display:none"><spring:message code="label.plzSelectValue"/></td>
											
												</form:select>
										
																							
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.comment"/>:</b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:textarea path="comment" id="comment" placeholder="Enter Comment"></form:textarea></td>
										<td id="commentError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										    <td id="commentError" class="error" style="display:none"><spring:message code="label.validation"/></td>
											
									</tr>
									
									</table>
									<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="custWareHouseAppList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
									</div>
								


</form:form>
</div>
	<script>
		
			
				 
				function val(){
					
					var userName  = document.getElementById('comment');
					

					var canSubmit = true; 
					
					if (document.getElementById('comment').value == ''){
						document.getElementById('commentError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('commentError').style.display='none';
					}
					
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


