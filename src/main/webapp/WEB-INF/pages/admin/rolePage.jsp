<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
	$(document).ready(
			function() {
				$(':input', '#catgoryForm').not(
						':button, :submit, :reset, :hidden').val('')
						.removeAttr('checked').removeAttr('selected');
			});

	$(function() {
		
		debugger;
		 /*  $("#datepicker").datepicker();  */
		 $( "#datepicker" ).datepicker({format:'dd/mm/yyyy'});
		 
		/* $("#datepicker").datepicker({
			format : 'dd/mm/yyyy'
		}); */
	});

	function val() {

		var currentRole = document.getElementById('currentRole').value;
		var contactNo = document.getElementById('contactNo').value;
		var email = document.getElementById('email').value;
		var userName = document.getElementById('userName').value;
		var displayName = document.getElementById('displayName').value;
		var password = document.getElementById("password").value;
		var accExpiryDate = document.getElementById('datepicker').value;
		var phoneNum = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
		var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

		var canSubmit = true;

		if (currentRole == '') {
			document.getElementById('currentRoleError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('currentRoleError').style.display = 'none';
		}

		if (userName == '') {
			document.getElementById('userNameError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('userNameError').style.display = 'none';
		}

		if (displayName == '') {
			document.getElementById('displayNameError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('displayNameError').style.display = 'none';
		}

		if (password == '') {
			document.getElementById('passwordError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('passwordError').style.display = 'none';
		}

		if (accExpiryDate == '') {
			document.getElementById('expiryDateError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('expiryDateError').style.display = 'none';
		}

		if (contactNo
				.match(/^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/)
				&& !(contactNo == '')) {

			document.getElementById('contactNo1Error').style.display = 'none';
		} else {

			document.getElementById('contactNo1Error').style.display = 'block';
			canSubmit = false;
		}

		if (altEmail.value
				.match(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/)
				|| (document.getElementById('altEmail').value == '')) {

			document.getElementById('altemailError').style.display = 'none';

		} else {
			document.getElementById('altemailError').style.display = 'block';
			canSubmit = false;
		}

		if (altContactNo.value
				.match(/^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/)
				|| (document.getElementById('altContactNo').value == '')) {

			document.getElementById('contactNum2Error').style.display = 'none';
		} else {

			document.getElementById('contactNum2Error').style.display = 'block';
			canSubmit = false;
		}
		if (email
				.match(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/)
				&& !(email == '')) {

			document.getElementById('emailError1').style.display = 'none';

		} else {
			document.getElementById('emailError1').style.display = 'block';
			canSubmit = false;
		}

		if (canSubmit == false) {
			return false;
		}
	}
	function roleChange(){
		

		var currentRole = document.getElementById('currentRole').value;
		if (currentRole == 'ROLE_APPROVALMNG') {
			document.getElementById('approvalLimitDiv').style.display = 'block';
		}else{
			document.getElementById('approvalLimit').value =null;
			document.getElementById('approvalLimitDiv').style.display = 'none';
		}
	}
</script>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
	<div class="errorMsg">${error}</div>
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3 align="center">
			<spring:message code="label.newUser" />
		</h3>
	</div>


	<form:form action="updateRole" method="post" commandName="endUserForm"
		onsubmit="return val();">
		<table align="center">
			<tr>
				<td class="col-sm-5"><b><spring:message
						code="label.name" /></b><span style="color: red">*</span></td>
				<td class="col-sm-7"><form:input path="displayName"
												 class="form-control" placeholder="Enter displayName" id="displayName" />
					<div id="displayNameError" style="display: none; color: red;">
						<spring:message code="label.displayNameMandatory" />
					</div></td>
			</tr>

			<tr>
				<td class="col-sm-5"><b><spring:message
							code="label.selectRole" /></b><span style="color: red">*</span></td>
				<td class="col-sm-7"><form:select path="currentRole"
						id="currentRole" onChange="roleChange()">
						<form:option value="">
							<spring:message code="label.selectValue" />
						</form:option>
						<form:option value="ROLE_BANKEMP">
							<spring:message code="label.bankEmployee" />
						</form:option>
						<form:option value="ROLE_APPROVALMNG">
							<spring:message code="label.approvalManager" />
						</form:option>
					</form:select>
					<div id="currentRoleError" style="display: none; color: red;">
						<spring:message code="label.selectValue" />
					</div></td>
			</tr>
					
			<tr id="approvalLimitDiv">
			
				<td class="col-sm-5"><b><spring:message
							code="label.approvalManagerLimit" /></b></td>			
				<td class="col-sm-7"><form:input path="approvallimit"
						class="form-control" value ="${endUserForm.currentRole}" placeholder="Enter the Limit"
						id="approvalLimit" type = "number"/></td>
			</tr>
			<tr>
				<td class="col-sm-5"><b><spring:message
							code="label.contactNumber" /></b><span style="color: red">*</span></td>
				<td class="col-sm-7"><form:input path="contactNo"
						class="form-control" placeholder="Enter Contact Number"
						id="contactNo" />
					<div id="contactNo1Error" style="display: none; color: red;">
						<spring:message code="label.contactNumberValidation" />
					</div></td>
			</tr>
			<tr>
				<td class="col-sm-5"><b><spring:message
							code="label.altcontactNumber" /></b><span style="color: red"></span></td>
				<td class="col-sm-7"><form:input path="altContactNo"
						class="form-control" placeholder="Enter Alternate Contact Number"
						id="altContactNo" />
					<div id="contactNum2Error" style="display: none; color: red;">Alternate
						Contact Number is not correct Format</div></td>
			</tr>
			<tr>
				<td class="col-sm-5"><b><spring:message code="label.email" /></b><span
					style="color: red">*</span></td>
				<td class="col-sm-7"><form:input path="email"
						class="form-control" placeholder="Enter Email" id="email" />
					<div id="emailError1" style="display: none; color: red;">
						<spring:message code="label.emailValidation" />
					</div></td>
			</tr>
			<tr>
				<td class="col-sm-5"><b><spring:message
							code="label.altEmail" /></b><span style="color: red"></span></td>
				<td class="col-sm-7"><form:input path="altEmail"
						class="form-control" placeholder="Enter Altenate Email"
						id="altEmail" />
					<div id="altemailError" style="display: none; color: red;">Alternate
						Email Format is not correct</div></td>
			</tr>
			<tr>
				<td class="col-sm-5"><b><spring:message
							code="label.userExpiry" /></b><span style="color: red">*</span></td>
				<td id="e1" class="col-sm-6">
				<%-- <form:input path="accExpiryDate" class="form-control" placeholder="Select date" style="width: 206px;" readonly="true" id="datepicker" />  --%>
				 <form:input path="accExpiryDateStr" class="form-control" placeholder="Select date" 
							style="width: 206px;" readonly="true" id="datepicker" />
				<i class="fa fa-calendar"></i> 
				<span id="expiryDateError" class="error" style="display: none;"> <font color="red">
							<spring:message code="label.validation" />*
					</font>
				</span></td>

			</tr>

<%--			<tr>--%>
<%--				<td class="col-sm-5"><b><spring:message--%>
<%--						code="label.displayName" /></b><span style="color: red">*</span></td>--%>
<%--				<td class="col-sm-7"><form:input path="displayName"--%>
<%--												 class="form-control" placeholder="Enter displayName" id="displayName" />--%>
<%--					<div id="displayNameError" style="display: none; color: red;">--%>
<%--						<spring:message code="label.displayNameMandatory" />--%>
<%--					</div></td>--%>
<%--			</tr>--%>

			<tr>
				<td class="col-sm-5"><b><spring:message
							code="label.userName" /></b><span style="color: red">*</span></td>
				<td class="col-sm-7"><form:input path="userName"
						class="form-control" placeholder="Enter UserName" id="userName" />
					<div id="userNameError" style="display: none; color: red;">
						<spring:message code="label.userNameValidation" />
					</div></td>
			</tr>


			<tr>
				<td class="col-sm-5"><b><spring:message
							code="label.password" /></b><span style="color: red">*</span></td>
				<td class="col-sm-7"><form:input path="password"
						class="form-control" placeholder="Enter Password" id="password" />
					<div id="passwordError" style="display: none; color: red;">
						<spring:message code="label.passwordValidation" />
					</div></td>
					<form:hidden path="accExpiryDateStr" value="${model.endUserForm.accExpiryDateStr}" />
			</tr>
		</table>

		<div class="col-sm-12 col-md-12 col-lg-12">
			<table align="center">
				<tr>
					<td><input type="submit" class="btn btn-primary"
						value="<spring:message code="label.confirm"/>"></td>
					<td><a href="adminPage" class="btn btn-success"><spring:message
								code="label.back" /></a></td>
				</tr>

			</table>
		</div>
	</form:form>
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3 align="center">
			<spring:message code="label.editDetails" />
		</h3>
	</div>

	<div class="col-sm-12 col-md-12 col-lg-12">
		<div class="col-sm-12 col-md-12 col-lg-12">
			<input type="text" id="kwd_search" value=""
				placeholder="Search Here..." style="float: right;" />
		</div>
		<table class="table with-pager data jqtable example" id="my-table">
			<thead>
				<tr>
					<th><spring:message code="label.id" /></th>

					<th><spring:message code="label.currentRole" /></th>
					<th><spring:message code="label.roleId" /></th>
					<th><spring:message code="label.contactNumber" /></th>
					<th><spring:message code="label.status" /></th>
					<th><spring:message code="label.email" /></th>
					<th><spring:message code="label.userName" /></th>
					<th><spring:message code="label.action" /></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${! empty endUsers}">
					<c:forEach items="${endUsers}" var="roles">
						<tr>
							<td><c:out value="${roles.id}"></c:out></td>
							<td><c:out value="${roles.currentRole}"></c:out></td>
							<td><c:out value="${roles.role}"></c:out></td>
							<td><c:out value="${roles.contactNo}"></c:out></td>
							<td><c:out value="${roles.status}"></c:out></td>
							<td><c:out value="${roles.email}"></c:out></td>
							<td><c:out value="${roles.userName}"></c:out></td>


							<td><a href="selectRole?id=${roles.id}"
								class="btn btn-primary"><spring:message code="label.edit" /></a></td>
						</tr>
					</c:forEach>
				</c:if>

			</tbody>
		</table>

	</div>
</div>
<script>
	$(document).ready(
			function() {
				$(':input', '#endUserForm').not(
						':button, :submit, :reset, :hidden').val('')
			});
</script>
