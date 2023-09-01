<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-sm-9 col-md-9 body_fixed">
	<form:form action="updateSwapAccount" commandName="swapAccountForm">
		<div class="col-sm-12 col-md-12 header_customer">
			<h3 align="center"><spring:message code="label.approval"/></h3>
		</div>
		<div class="col-sm-12 col-md-12">

			<table align="center">
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.id"/></b></td>
					<td class="col-sm-7"><form:input path="id" readonly="true" value="${swapAccountForm.id}"></form:input>
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.existingUser"/></b></td>
					<td class="col-sm-6">
						<form:input path="oldUser" class="form-control" 
						            readonly="true" value="${swapAccountForm.oldUser}" />
					</td>
				</tr>	
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.existingEmail"/></b></td>
					<td class="col-sm-6">
						<form:input path="oldEmail" class="form-control" 
						            readonly="true" value="${swapAccountForm.oldEmail}" />
					</td>
				</tr>	
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.newUser"/></b></td>
					<td class="col-sm-6">
						<form:input path="newUser" class="form-control" 
						            readonly="true" value="${swapAccountForm.newUser}" />
					</td>
				</tr>	
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.newEmail"/></b></td>
					<td class="col-sm-6">
						<form:input path="newEmail" class="form-control" 
						            readonly="true" value="${swapAccountForm.newEmail}" />
					</td>
				</tr>	
				
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.approveReject"/></b></td>
					<td class="col-sm-6">
						<form:radiobutton path="status" value="Approved"/><spring:message code="label.approve"/>
						<form:radiobutton path="status" value="Rejected"/><spring:message code="label.reject"/>
					</td>
				</tr>
			</table>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
			<p align="center" style="padding-top: 10px;">
				<input type="submit" class="btn btn-primary" value="<spring:message code="label.update"/>"> 
				
				<a href="swapAccountForApproval" class="btn btn-success"><spring:message code="label.back"/></a>
			</p>
		</div>
	</form:form>
</div>