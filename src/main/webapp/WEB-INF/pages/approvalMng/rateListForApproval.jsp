<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<section>	
<form:form action="rateListForApprovalConfirm" commandName="purchaseOrderForm" onsubmit="return val();">
<div class="heading"><spring:message code="label.approval"/></div>
			<div class="content">
								<table class="theader">
								<form:hidden path="id"  />
									<form:hidden path="customerHeadEmail"  />
									<form:hidden path="customerBranchEmail"  />
								    	<form:hidden path="transactionId"  />
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.customerName"/></b></td>
										<td class="col-sm-8"><form:input path="customerName" placeholder="Enter name" readonly="true" style="width:85%" ></form:input>
																								
									</tr>
									
									
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.amount"/></b></td>
										<td class="col-sm-8"><form:input path="funalAmt" readonly="true" placeholder="Enter contact number" id="customerPrefix" style="width:85%"></form:input>
											
									</tr>
									
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.poKey"/></b></td>
										<td class="col-sm-8"><form:input path="poKey" readonly="true" placeholder="Enter Company Name" id="companyName" style="width:85%"></form:input>
									</tr>	
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.interest"/></b></td>
										<td class="col-sm-8"><form:input path="rateOfInt" readonly="true" placeholder="Enter Company Name" id="companyName" style="width:85%"></form:input>
									</tr>
							<tr>
											<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.status"/></b><span style="color:red">*</span></td>
											
											<td class="col-sm-8"><form:select path="rStatus" id="status" style="width:86.9%">
											    <form:option value=""><spring:message code="label.selectValue"/></form:option> 
											    <form:option value="Approved"><spring:message code="label.approve"/></form:option>
												<form:option value="Rejected"><spring:message code="label.reject"/></form:option>
												</form:select>
											<td id="statusError" class="error" style="display:none;"><font color="red"><spring:message code="label.pleaseEnterStatus"/></font></td>
										    <td id="statusError" class="error" style="display:none"><spring:message code="label.pleaseEnterStatus"/></td>
											
											</td>	
																									
									</tr>
								
									
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.comment"/></b><span style="color:red">*</span></td>
										<td class="col-sm-8"><form:input path="rComment" id="comment" placeholder="Enter Comment"  style="width:85%"></form:input>
										<td id="commentError" class="error" style="display:none;"><font color="red"><spring:message code="label.commentValidation"/></font></td>
										    <td id="commentError" class="error" style="display:none"><spring:message code="label.commentValidation"/></td>
											
										</td>
									</tr>
											<p align="center" style="padding-top: 10px;">
					<input type="submit" class="btn btn-primary" value="<spring:message code="label.confirm"/>">
					
					<a href="bankEmp" class="btn btn-success" name="back"  onclick="javascript:window.location='#';"/><spring:message code="label.back"/></a>
				</p>
									</table>
									</div>
								


</form:form>
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
</section>

