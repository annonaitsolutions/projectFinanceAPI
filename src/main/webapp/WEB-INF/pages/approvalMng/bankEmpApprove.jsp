<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.approval"/></h3>
		</div>
				<form:form name="bankempupdate" action="approveBankEmpConfrim" method="post"
					commandName="endUserForm" onsubmit="return val();">
					<table align="center">
					
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.id"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
						<form:input path="id" id="id" 	value="${model.endUserForm.id}" readonly="true"/>
						</td>
				</tr>
						 <tr>
							<td class="col-sm-6"><b><spring:message code="label.userName"/></b></td>
							<td class="col-sm-6"><form:input path="userName" id="userName"
									value="${model.endUserForm.userName}" readonly="true" />
						</tr> 

						<%-- <tr>
							<td><b>Display Name :</b></td>
							<td><form:input path="displayName"
									value="${model.endUserForm.displayName}" readonly="true" /></td>
						</tr> --%>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.email"/></b></td>
							<td class="col-sm-6"><form:input path="email"
									value="${model.endUserForm.email}" readonly="true" /></td>

						</tr>

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.contactNumber"/></b></td>
							<td class="col-sm-6"><form:input path="contactNo"
									value="${model.endUserForm.contactNo}" readonly="true" /></td>
							<form:hidden path="transactionId" value="" />
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
							<td><a href="bankEmpApprov" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>

				</form:form>
			</div>
		
<style>
	select#select {
    width: 205px;
}
</style>
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
