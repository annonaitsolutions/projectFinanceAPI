<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="col-sm-9 col-md-9 body_fixed">


               
<form:form action="limitBurstRatesApprConfirm" commandName="limitBurstForm" onsubmit="return val();">
			 <div class="col-sm-12 col-md-12 col-lg-12 header_customer">	
				<h3><spring:message code="label.approval"/></h3>
			</div>
			<div class="content">
								<table align="center">
								<form:hidden path="id"  />
								<form:hidden path="transactionId"  />
								<form:hidden path="customerEmail"  />
								<form:hidden path="customerHeadName"  />
								<form:hidden path="masterKey"  />
									
									
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.requestedAmount"/></b></td>
										<td class="col-sm-6"><form:input path="reqAmt" readonly="true"  id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.totalAmount"/></b></td>
										<td class="col-sm-6"><form:input path="finalAmt" readonly="true"  id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.tenure"/></b></td>
										<td class="col-sm-6"><form:input path="tenure" readonly="true"  id="customerPrefix"></form:input></td>
											
									</tr>
									
								
											<td class="col-sm-6"><b><spring:message code="label.status"/></b><span style="color:red">*</span></td>
											
											<td class="col-sm-6"><form:select path="aStatus" id="status" style="width:205px;">
											    <form:option value=""><spring:message code="label.selectValue"/></form:option> 
											    <form:option value="Approved"><spring:message code="label.approve"/></form:option>
												<form:option value="Rejected"><spring:message code="label.reject"/></form:option>
												</form:select></td>
											<td id="statusError" class="error" style="display:none;"><font color="red"><spring:message code="label.pleaseEnterStatus"/></font></td>
										    <td id="statusError" class="error" style="display:none"><spring:message code="label.pleaseEnterStatus"/></td>
																								
									</tr>
								
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.comment"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:textarea path="comment" id="comment" placeholder="Enter Comment" ></form:textarea>
										<td id="commentError" class="error" style="display:none;"><font color="red"><spring:message code="label.commentValidation"/></font></td>
										    <td id="commentError" class="error" style="display:none"><spring:message code="label.commentValidation"/></td>
									
									</tr>
							
									</table>
									
									
											 <div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="limitBurstRatesApprList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
	</div>
								


</form:form>
</div>
	<script>
		
			
				 
				function val(){
					
					var userName  = document.getElementById('comment');
					var userName  = document.getElementById('status');
					

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


