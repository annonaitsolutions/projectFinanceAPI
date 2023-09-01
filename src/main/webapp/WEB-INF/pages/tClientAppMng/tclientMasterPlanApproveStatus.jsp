<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


 <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

			 <div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			 	<h3 align="center"><spring:message code="label.masterPlanApproval"/></h3>
			 </div>
		<form:form action="tclientMasterPlanApproveStatusConfirm" commandName="tMasterPlanForm" onsubmit="return val();">

			<div class="content">
								<table align="center">
								<form:hidden path="id"  />
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customer"/>:</b></td>
										<td class="col-sm-6"><form:input path="customer"  readonly="true"></form:input>
																								
									</tr>
										<form:hidden path="transactionId"  />
										<form:hidden path="id"  />	
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.masterKey"/>:</b></td>
										<td class="col-sm-6"><form:input path="masterKey" readonly="true"  id="customerPrefix"></form:input>
											
									</tr>
									
								
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.status"/></b><span style="color:red">*</span></td>
											
											<td class="col-sm-6"><form:select path="aStatus" id="status" style="width:205px;">
											    <form:option value=""><spring:message code="label.selectValue"/></form:option> 
											    <form:option value="Approved"><spring:message code="label.approve"/></form:option>
												<form:option value="Rejected"><spring:message code="label.reject"/></form:option>
												</form:select>
										   <td id="statusError" class="error" style="display:none;"><font color="red"><spring:message code="label.selectValue"/></font></td>
											<td id="statusError" class="error" style="display:none"><spring:message code="label.selectValue"/></td>
											
																									
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.comment"/>:</b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:textarea path="aComment" id="comment" placeholder="Enter Comment"></form:textarea>
										
									</tr>
									<tr>
										<td id="commentError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										<td id="commentError" class="error" style="display:none"><spring:message code="label.validation"/></td>
									</tr> 
									</table>
									<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="tclientMasterPlanPendingDetails" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
									</div>
								


</form:form>
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
		</div>
