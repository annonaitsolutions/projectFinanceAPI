<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="col-sm-9 col-md-9 body_fixed">
<form:form action="approveClientAdminConfirm" commandName="clientAdminForm" onsubmit="return val();">
<div class="col-sm-12 col-md-12 header_customer"><h3 align="center"><spring:message code="label.customerHeadApproval"/></h3></div>
						<div class="col-sm-12 col-md-12">
						
								<form:hidden path="id"/>
								<form:hidden path="accExpiryDate"/>
								<table align="center">
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerName"/></b></td>
										<td class="col-sm-6"><form:input path="name"  readonly="true"></form:input>
																								
									</tr>
									
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerPrefix"/></b></td>
										<td class="col-sm-6"><form:input path="customerPrefix" readonly="true"  id="customerPrefix"></form:input>
											
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.companyName"/></b></td>
										<td class="col-sm-6"><form:input path="companyName" readonly="true"  id="companyName"></form:input></td>
																									
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.address"/>Address:</b></td>
										<td class="col-sm-6"><form:input path="address" readonly="true"  id="companyName"></form:input></td>
									</tr>
							
										<tr>
												<td class="col-sm-6"><b><spring:message code="label.status"/></b><span style="color:red">*</span></td>
												<td class="col-sm-6"><form:select path="status" id="status" style="width:205px;">
												    <form:option value=""><spring:message code="label.selectValue"/></form:option> 
												    <form:option value="Approved"><spring:message code="label.approve"/></form:option>
													<form:option value="Rejected"><spring:message code="label.reject"/></form:option>
													</form:select>
											<td id="statusError" class="error" style="display:none;"><font color="red"><spring:message code="label.selectValue"/></font></td>
											<td id="statusError" class="error" style="display:none"><spring:message code="label.selectValue"/></td>
												
										</tr>
										<tr>
										<td>
										<form:hidden path="contactNum"/></td>
										<td><form:hidden path="email"/></td>
										</tr>
										<tr>
											<td class="col-sm-6"><b><spring:message code="label.comment"/></b><span style="color:red">*</span></td>
											<td class="col-sm-6"><form:textarea path="comment" id="comment" placeholder="Enter Comment" style="height:120px;"></form:textarea></td>
											<td id="commentError" class="error" style="display:none;"><font color="red"><spring:message code="label.commentValidation"/></font></td>
											<td id="commentError" class="error" style="display:none"><spring:message code="label.commentValidation"/></td>
										</tr>
					    	</table>
					</div>
					<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="custAdminApprovalList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>		
								


</form:form>
</div>
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
