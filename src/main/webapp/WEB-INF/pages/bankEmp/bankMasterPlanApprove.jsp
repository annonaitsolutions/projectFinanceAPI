<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>



 <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

 	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">	
 		<h3 align="center"><spring:message code="label.masterPlanApproval"/></h3>
 	</div>
<form:form action="bankMasterPlanApproveStatusConfirm" commandName="masterPlanForm" onsubmit="return val();">

			<div class="col-sm-12 col-md-12 col-lg-12"> 
								<table align="center">
								<form:hidden path="id"  />
									<tr>
										<td class="col-sm-6"><b> <spring:message code="label.name"/></b></td>
										<td class="col-sm-6"><form:input path="customer" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
											<form:hidden path="transactionId"  />
									<form:hidden path="id"  />	
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.masterKey"/></b></td>
										<td class="col-sm-6"><form:input path="masterKey" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.status"/></b><span style="color:red">*</span></td>
											
											<td class="col-sm-6"><form:select path="status" id="status" style="width: 209px;">
											    <form:option value=""><spring:message code="label.selectValue"/></form:option> 
											    <form:option value="Approved"><spring:message code="label.approve"/></form:option>
												<form:option value="Rejected"><spring:message code="label.reject"/></form:option>
										
									 			</form:select>
										   
								   
								<div id="statusError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation2"/></font></div>
								<div id="statusError" class="error" style="display:none"><spring:message code="label.validation2"/></div>
											
											</td>															
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.comment"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:textarea path="comment" id="comment" placeholder="Enter Comment"></form:textarea>
									
									
										<div id="commentError" class="error" style="display:none;"><font color="red"><spring:message code="label.commentValidation"/></font></div>
										<div id="commentError" class="error" style="display:none;float:right;"><spring:message code="label.commentValidation"/></div></td>
									</tr>
											
									</table>
								
						</div>
								<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="bankMasterPlanPendingDetails" class="btn btn-success"><spring:message code="label.back"/></a></td>
							
					
						</tr>
					</table>
		        </div>
								


</form:form>
	<script>
		
			
				 
				function val(){
					
					var userName  = document.getElementById('comment');
					var status  = document.getElementById('status');

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