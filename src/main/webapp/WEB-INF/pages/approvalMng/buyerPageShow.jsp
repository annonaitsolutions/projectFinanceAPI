<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3><spring:message code="label.approval"/></h3>
	</div>
<form:form action="buyerPageShowConfirm" commandName="newBuyerForm" onsubmit="return val();">

			<div class="col-sm-12 col-md-12 col-lg-12">
				<table align="center">
				<tr>
					<td class="col-sm-6"><font color="Red"></font><spring:message code="label.id"/></td>
					<td class="col-sm-6"><form:input path="id" readonly="true"/></td>
					<td class="col-sm-12"><form:errors path="id" cssStyle="color:red"></form:errors></td>
				</tr>
				<tr>
					<td class="col-sm-6"><spring:message code="label.customerName"/></td>
					<td class="col-sm-6"><form:input path="name" readonly="true" /></td>
					<td class="col-sm-12"><form:errors path="name" cssStyle="color:red"></form:errors></td>
				</tr>
				<tr>
				
					<td class="col-sm-6"><spring:message code="label.buyerName"/></td>
					<td class="col-sm-6"><form:input path="buyerName" readonly="true"/></td>
					<td class="col-sm-12"><form:errors path="buyerName" cssStyle="color:red"></form:errors></td>
				</tr>
				<tr>
					<td class="col-sm-6"><spring:message code="label.buyerCompanyName"/></td>
					<td class="col-sm-6"><form:input path="companyName" readonly="true"/></td>
					<td class="col-sm-12"><form:errors path="companyName" cssStyle="color:red"></form:errors></td>
				</tr>
				<tr>
					<td class="col-sm-6"><spring:message code="label.buyerBank"/></td>
					<td class="col-sm-6"><form:input path="bank" readonly="true"/></td>
					<td class="col-sm-12"><form:errors path="bank" cssStyle="color:red"></form:errors></td>
				</tr>
				<tr>
					<td class="col-sm-6"><spring:message code="label.buyerBankBranch"/></td>
					<td class="col-sm-6"><form:input path="branch" readonly="true"/></td>
					<td class="col-sm-12"><form:errors path="branch" cssStyle="color:red"></form:errors></td>
					</tr>
				<tr>
					<td class="col-sm-6"><spring:message code="label.buyerBankEmail"/></td>
					<td class="col-sm-6"><form:input path="bankEmail" readonly="true"/></td>
					<td class="col-sm-12"><form:errors path="bankEmail" cssStyle="color:red"></form:errors></td>
				</tr>
				<tr>
					<td class="col-sm-6"><spring:message code="label.contactNumber"/></td>
					<td class="col-sm-6"><form:input path="contactNum" readonly="true"/></td>
					<td class="col-sm-12"><form:errors path="contactNum" Style="color:red"></form:errors></td>
				</tr>
					
				<tr>
					<td class="col-sm-6"><spring:message code="label.email"/></td>
					<td class="col-sm-6"><form:input path="email" readonly="true"/></td>
					<td class="col-sm-12"><form:errors path="email" Style="color:red"></form:errors></td>
				</tr>
					
				
                    <tr>
					<td class="col-sm-6"><b><spring:message code="label.status"/></b><span style="color:red">*</span></td>
					<td class="col-sm-6"><form:select path="status" id="status" width="205px;!important">
						 <form:option value=""><spring:message code="label.selectValue"/></form:option> 					 
						<form:option   value="Approved"><spring:message code="label.approve"/></form:option>
						<form:option   value="Rejected"><spring:message code="label.reject"/></form:option>
						</form:select>
						<td id="statusError" class="error" style="display:none;"><font color="red"><spring:message code="label.selectValue"/></font></td>
						<td id="statusError" class="error" style="display:none"><spring:message code="label.selectValue"/></td>
					
					</tr>
									 <tr>
						<td class="col-sm-6"><b><spring:message code="label.comment"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6"><form:textarea path="comment" id="comment" placeholder="Enter Comment" style="height:120px;"></form:textarea></td>
					    <td id="commentError" class="error" style="display:none;"><font color="red"><spring:message code="label.commentValidation"/></font></td>
						<td id="commentError" class="error" style="display:none"><spring:message code="label.commentValidation"/></td>
						</tr>
										
									</table>
										<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="buyerPage" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
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