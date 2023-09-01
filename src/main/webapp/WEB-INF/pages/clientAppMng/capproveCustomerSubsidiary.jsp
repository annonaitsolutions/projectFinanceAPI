<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<section>	
<form:form action="capproveCustomerSubsidiaryConfirm" commandName="customerSubsidiaryForm" onsubmit="return val();">
<div class="heading"><spring:message code="label.custDetail"/></div>
			<div class="content">
								<table class="theader">
								<form:hidden path="id"  />
								<form:hidden path="customerHeadKey"  />
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.customerName"/>:</b></td>
										<td class="col-sm-8"><form:input path="name" placeholder="Enter name" readonly="true" style="width:85%" ></form:input>
																								
									</tr>
											<form:hidden path="contactNum"  />
									<form:hidden path="email"  />	
									
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.customerPrefix"/>:</b></td>
										<td class="col-sm-8"><form:input path="customerPrefix" readonly="true" placeholder="Enter contact number" id="customerPrefix" style="width:85%"></form:input>
											
									</tr>
									
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.customerHeadName"/>:</b></td>
										<td class="col-sm-8"><form:input path="customerHeadName" readonly="true" placeholder="Enter Company Name" id="companyName" style="width:85%"></form:input>
																									
									</tr>
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.address"/>:</b></td>
										<td class="col-sm-8"><form:input path="address" readonly="true" placeholder="Enter Company Name" id="companyName" style="width:85%"></form:input>
										
									<tr>
											<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.status"/>:</b><span style="color:red">*</span></td>
											
											<td class="col-sm-8"><form:select path="aStatus" id="status" style="width:86.9%">
											    <form:option value=""><spring:message code="label.selectValue"/></form:option> 
											    <form:option value="Approved"><spring:message code="label.approve"/></form:option>
												<form:option value="Rejected"><spring:message code="label.reject"/></form:option>
												</form:select>
										
											</td>															
									</tr>
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.comment"/>:</b><span style="color:red">*</span></td>
										<td class="col-sm-8"><form:input path="comment" id="comment" placeholder="Enter Comment"  style="width:85%"></form:input>
										<td id="commentError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										    <td id="commentError" class="error" style="display:none"><spring:message code="label.validation"/></td>
											
										</td>
									</tr>
											<p align="center" style="padding-top: 10px;">
					<input type="submit" class="btn btn-primary" value="Confrim">
					
					<a href="bankEmp" class="btn btn-success" name="back"  onclick="javascript:window.location='#';"/>back</a>
				</p>
									</table>
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
</section>

