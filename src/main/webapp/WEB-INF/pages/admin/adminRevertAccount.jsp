<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-sm-9 col-md-9 body_fixed">
	<form:form action="updateRevertAccount" commandName="swapAccountForm">
		<div class="col-sm-12 col-md-12 header_customer">
			<h3 align="center"><spring:message code="revertAccount"/></h3>
		</div>
		<div class="col-sm-12 col-md-12">
		<div class="col-sm-6 col-md-6">
			<h3 align="left"><spring:message code="label.revertFrom"/></h3>
			
			<table align="left">
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.userName"/>:</b></td>
					<td class="col-sm-7"><form:input path="newUser" value="${swapAccountForm.newUser}" readonly="true"/>
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.contactNumber"/>:</b></td>
					<td class="col-sm-7"><form:input path="newContactNo" value="${swapAccountForm.newContactNo}" readonly="true"/>
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.email"/>:</b></td>
					<td class="col-sm-7"><form:input path="newEmail" value="${swapAccountForm.newEmail}" readonly="true"/>
				</tr>
				
			</table>
		</div>
		<div class="col-sm-6 col-md-6">
			<h3 align="left"><spring:message code="label.revertTo"/></h3>
			<table align="left">
			<tr>
					<td class="col-sm-5"><b><spring:message code="label.userName"/>:</b></td>
					<td class="col-sm-7"><form:input path="oldUser" value="${swapAccountForm.oldUser}" readonly="true"></form:input>
				</tr>
				<!-- Current Role from EndUSer is mapped to UserEmail -->
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.email"/>:</b></td>
					<td class="col-sm-7"><form:input path="oldEmail" value="${swapAccountForm.oldEmail}" readonly="true"></form:input>
				</tr>
				
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.contactNumber"/>:</b></td>
					<td class="col-sm-7"><form:input path="oldContactNo" value="${swapAccountForm.oldContactNo}" readonly="true"></form:input>
				</tr>
				
			</table>
			</div>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
			<p align="center" style="padding-top: 10px;">
			
			<form:hidden path="endUserId" value="${swapAccountForm.endUserId}"/>
				<input type="submit" class="btn btn-primary" value="Revert"> 
				<a href="usersForSwapRevert" class="btn btn-success" onclick="javascript:window.location='#';">Back</a>
			</p>
		</div>
	</form:form>
</div>



