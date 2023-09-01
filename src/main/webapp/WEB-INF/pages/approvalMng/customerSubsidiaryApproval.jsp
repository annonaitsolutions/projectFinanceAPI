<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.approval"/></h3>
			</div>
<form:form action="approveCustomerSubsidiaryConfirm" commandName="customerSubsidiaryForm" onsubmit="return val();">

			<div class="col-sm-12 col-md-12 col-lg-12">
								<table align="center">
								<form:hidden path="id"  />
								<form:hidden path="customerHeadKey"  />
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.name"/></b></td>
										<td class="col-sm-7"><form:input path="name" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
											<form:hidden path="contactNum"  />
									<form:hidden path="email"  />	
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.customerSubsidiaryPrefix"/></b></td>
										<td class="col-sm-7"><form:input path="customerPrefix" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.customerHeadName"/></b></td>
										<td class="col-sm-7"><form:input path="customerHeadName" readonly="true" placeholder="Enter Company Name" id="companyName"></form:input>
																									
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.address"/></b></td>
										<td class="col-sm-7"><form:input path="address" readonly="true" placeholder="Enter Company Name" id="companyName"></form:input>
										
									<tr>
											<td class="col-sm-5"><b><spring:message code="label.status"/></b><span style="color:red">*</span></td>
											
											<td class="col-sm-7"><form:select path="status" id="status">
											    <form:option value=""><spring:message code="label.selectValue"/></form:option> 
											    <form:option value="Approved"><spring:message code="label.approve"/></form:option>
												<form:option value="Rejected"><spring:message code="label.reject"/></form:option>
												</form:select>
										
											</td>															
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.comment"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="comment" id="comment" placeholder="Enter Comment"></form:input>
										<td id="commentError" class="error" style="display:none;"><font color="red"><spring:message code="label.commentValidation"/></font></td>
										<td id="commentError" class="error" style="display:none"><spring:message code="label.commentValidation"/></td>
										
									</tr>
											
									</table>
									<div class="col-sm-12 col-md-12 col-lg-12">
									<p align="center" style="padding-top: 10px;">
										<input type="submit" class="btn btn-primary" value="<spring:message code="label.confirm"/>">
										<a href="bankEmp" class="btn btn-success" name="back"  onclick="javascript:window.location='#';"><spring:message code="label.back"/></a>
									</p>
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
				

					if(canSubmit == false){
						return false;
					}
				}
			
				
		</script>
	</div>
