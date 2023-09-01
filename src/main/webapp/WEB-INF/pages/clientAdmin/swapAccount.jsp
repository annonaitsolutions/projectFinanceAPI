<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="col-sm-9 col-md-9 body_fixed">
	<form:form action="saveSwapAccount" commandName="swapAccountForm">
		<div class="col-sm-12 col-md-12 header_customer">
			<h3 align="center"><spring:message code="label.swapAccount"/></h3>
		</div>
		<div class="col-sm-12 col-md-12">
		<div class="col-sm-6 col-md-6">
			<h3 align="left"><spring:message code="label.swapFrom"/></h3>
			
			<table align="left">
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.userName"/></b></td>
					<td class="col-sm-7"><form:input path="oldUser" value="${swapAccountForm.oldUser}" readonly="true"></form:input>
				</tr>
				<!-- Current Role from EndUSer is mapped to UserEmail -->
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.role"/></b></td>
					<td class="col-sm-7"><form:input path="oldEmail" value="${swapAccountForm.oldEmail}" readonly="true"></form:input>
				</tr>
				
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.contactNumber"/></b></td>
					<td class="col-sm-7"><form:input path="oldContactNo" value="${swapAccountForm.oldContactNo}" readonly="true"></form:input>
				</tr>
				
			</table>
		</div>
		<div class="col-sm-6 col-md-6">
			<h3 align="left"><spring:message code="label.swapTo"/></h3>
			<table align="left">
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.userName"/></b></td>
					<td class="col-sm-7"><form:input path="newUser" />
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.contactNumber"/></b></td>
					<td class="col-sm-7"><form:input path="newContactNo" />
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.email"/></b></td>
					<td class="col-sm-7"><form:input path="newEmail" />
				</tr>
			</table>
			</div>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
			<p align="center" style="padding-top: 10px;">
			<form:hidden path="endUserId" value="${swapAccountForm.endUserId}"/>
				<input type="submit" class="btn btn-primary" value="<spring:message code="label.swap"/>"> 
				<a href="getUsersForSwapRevert" class="btn btn-success" onclick="javascript:window.location='#';"><spring:message code="label.back"/></a>
			</p>
		</div>
	</form:form>
</div>



