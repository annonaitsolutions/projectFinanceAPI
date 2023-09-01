<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3><spring:message code="label.buyerApp"/></h3>
	</div>
<form:form action="buyerPageShowConfirmApproval" commandName="buyerTradeForm" onsubmit="return val();">

			<table align="center">
			<form:hidden path="id" value="${model.buyerTradeForm.id}" />
					<tr>

					<td class="col-sm-6"><spring:message code="label.customerName"/></td>
					<td class="col-sm-6"><form:input path="name" readonly="true" style="width:100%"/></td>
					<td class="col-sm-6"><form:errors path="name" cssStyle="color:red"></form:errors></td>
				</tr>
				<tr>
					<td class="col-sm-6"><spring:message code="label.buyerName"/></td>
					<td class="col-sm-6"><form:input path="buyerName" readonly="true" style="width:100%"/></td>
					<td class="col-sm-6"><form:errors path="buyerName" cssStyle="color:red"></form:errors></td>
				</tr>
				<tr>
					<td class="col-sm-6"><spring:message code="label.buyerComName"/></td>
					<td class="col-sm-6"><form:input path="companyName" readonly="true" style="width:100%"/></td>
					<td class="col-sm-6"><form:errors path="companyName" cssStyle="color:red"></form:errors></td>
				</tr>
				<tr>
					<td class="col-sm-6"><spring:message code="label.buyerBank"/></td>
					<td class="col-sm-6"><form:input path="bank" readonly="true" style="width:100%"/></td>
					<td class="col-sm-6"><form:errors path="bank" cssStyle="color:red"></form:errors></td>
				</tr>
				<tr>
					<td class="col-sm-6"><spring:message code="label.buyerBankBranch"/></td>
					<td class="col-sm-6"><form:input path="branch" readonly="true" style="width:100%"/></td>
					<td class="col-sm-6"><form:errors path="branch" cssStyle="color:red"></form:errors></td>
			</tr>
			<tr>
					<td class="col-sm-6"><spring:message code="label.buyerBankEmail"/></td>
					<td class="col-sm-6"><form:input path="bankEmail" readonly="true" style="width:100%"/></td>
					<td class="col-sm-6"><form:errors path="bankEmail" cssStyle="color:red"></form:errors></td>
				</tr>
				<tr>
					<td class="col-sm-6"><spring:message code="label.contactNumber"/></td>
					<td class="col-sm-6"><form:input path="contactNum" readonly="true" style="width:100%"/></td>
					<td class="col-sm-6"><form:errors path="contactNum" Style="color:red"></form:errors></td>
			</tr>
			<tr>
					
				
					<td class="col-sm-6"><spring:message code="label.email"/></td>
					<td class="col-sm-6"><form:input path="email" readonly="true" style="width:100%"/></td>
					<td class="col-sm-6"><form:errors path="email" Style="color:red"></form:errors></td>
				</tr>

					
				
                    <tr>

					<td class="col-sm-6"><spring:message code="label.status"/></td>
					<td class="col-sm-6"><form:select path="cStatus" id="status" style="width:100%">
					 <form:option value=""><spring:message code="label.selectValue"/></form:option> 
							<form:option style="color:green" value="Approved" label="Approved" />
							<form:option style="color:red" value="Rejected" label="Rejected" />
								
						</form:select></td>
						<td id="statusError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
						<td id="statusError" class="error" style="display:none"><spring:message code="label.validation"/></td>
					
				</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.comment"/>:</b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="comment" id="comment" placeholder="Enter Comment"  style="width:85%"></form:input>
										<td id="commentError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										    <td id="commentError" class="error" style="display:none"><spring:message code="label.validation"/></td>
											
										</tr>
					
									</table>
					<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="buyerCustomerTradeList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
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

