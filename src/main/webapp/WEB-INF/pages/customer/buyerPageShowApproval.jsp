<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<section>	


<form:form action="buyerPageShowConfirmApproval" commandName="newBuyerForm" onsubmit="return val();">
<h1><spring:message code="label.buyerApp"/></h1>
			<div class="content">
								<table class="theader">
								<div class="col-sm-3"><font color="Red"></font><spring:message code="label.id"/></div>
					<div class="col-sm-7"><form:input path="id" readonly="true" style="width:100%"/></div>
					<div class="col-sm-11"><form:errors path="id" cssStyle="color:red"></form:errors></div>
				
					<div class="col-sm-3"><spring:message code="label.customerName"/></div>
					<div class="col-sm-7"><form:input path="name" readonly="true" style="width:100%"/></div>
					<div class="col-sm-11"><form:errors path="name" cssStyle="color:red"></form:errors></div>
				
				
					<div class="col-sm-3"><spring:message code="label.buyerName"/></div>
					<div class="col-sm-7"><form:input path="buyerName" readonly="true" style="width:100%"/></div>
					<div class="col-sm-11"><form:errors path="buyerName" cssStyle="color:red"></form:errors></div>
				
					<div class="col-sm-3"><spring:message code="label.buyerCompanyName"/></div>
					<div class="col-sm-7"><form:input path="companyName" readonly="true" style="width:100%"/></div>
					<div class="col-sm-11"><form:errors path="companyName" cssStyle="color:red"></form:errors></div>
				
					<div class="col-sm-3"><spring:message code="label.buyerBank"/></div>
					<div class="col-sm-7"><form:input path="bank" readonly="true" style="width:100%"/></div>
					<div class="col-sm-11"><form:errors path="bank" cssStyle="color:red"></form:errors></div>
				
					<div class="col-sm-3"><spring:message code="label.buyerBankBranch"/></div>
					<div class="col-sm-7"><form:input path="branch" readonly="true" style="width:100%"/></div>
					<div class="col-sm-11"><form:errors path="branch" cssStyle="color:red"></form:errors></div>
			
					<div class="col-sm-3"><spring:message code="label.buyerBankEmail"/></div>
					<div class="col-sm-7"><form:input path="bankEmail" readonly="true" style="width:100%"/></div>
					<div class="col-sm-11"><form:errors path="bankEmail" cssStyle="color:red"></form:errors></div>
				
					<div class="col-sm-3"><spring:message code="label.contactNumber"/></div>
					<div class="col-sm-7"><form:input path="contactNum" readonly="true" style="width:100%"/></div>
					<div class="col-sm-11"><form:errors path="contactNum" Style="color:red"></form:errors></div>
			
					
				
					<div class="col-sm-3"><spring:message code="label.email"/></div>
					<div class="col-sm-7"><form:input path="email" readonly="true" style="width:100%"/></div>
					<div class="col-sm-11"><form:errors path="email" Style="color:red"></form:errors></div>
				
					
				
                    <tr>
					<td><spring:message code="label.status"/></td>
					<td><form:select path="cStatus">
							<form:option style="color:green" value="<spring:message code="label.approved"/>"
								label="Approved" />
							<form:option style="color:red" value="<spring:message code="label.rejected"/>" label="Rejected" />
						</form:select></td>
				</tr>
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.comment"/></b><span style="color:red">*</span></td>
										<td class="col-sm-8"><form:input path="comment" id="comment" placeholder="Enter Comment"  style="width:85%"></form:input>
										<td id="commentError" class="error" style="display:none;"><font color="red"><spring:message code="label.commentValidation"/></font></td>
										    <td id="commentError" class="error" style="display:none"><spring:message code="label.commentValidation"/></td>
											
										</td>
									</tr>
											<p align="center" style="padding-top: 10px;">
					<input type="submit" class="btn btn-primary" value="<spring:message code="label.confirm"/>">
					
					<a href="bankEmp" class="btn btn-success" name="back"  onclick="javascript:window.location='/annona/appMng/buyerPage';"/><spring:message code="label.back"/></a>
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

