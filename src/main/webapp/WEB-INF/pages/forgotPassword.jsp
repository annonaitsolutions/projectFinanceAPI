<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script>
	function validateForm() {

		var newPassword = document.getElementById('userName');

		var confirmNewPassword = document.getElementById('confirmNewPassword');

		var canSubmit = true;

		if (document.getElementById('userName').value == '') {
			document.getElementById('userNameError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('userNameError').style.display = 'none';
		}

		if (document.getElementById('email').value == '') {
			document.getElementById('emailError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('emailError').style.display = 'none';
		}

		if (canSubmit == false) {
			return false;
		}

	}
</script>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
 
<body class="homepage">
	
	<section id="bodychain">
		<div class="container">
			<div class="row">
				<div class="col-sm-12 col-md-12 col-lg-12">
				
				<div class="col-md-12 col-lg-12 col-sm-12 body_fixed">
	<div class="col-md-12 col-lg-12 col-sm-12 header_customer">
		<h3>Details</h3>
	</div>
	<div class="loginForm1">

		

		<div>
			<font color="red">${success}</font>
		</div>

		<form:form action="checkForgotPassword" autocomplete="off"
			onsubmit="return validateForm()" commandName="endUserForm">
			<table align="center">

				<tr>

					<td class="col-sm-6"><label for="">User Name:</label></td>

					<td class="col-sm-6"><form:input path="userName" class="form-control"
							id="userName" placeholder="user Name" autocomplete="off"></form:input>
						<div id="userNameError" style="display: none; color: red;">User
							Name is Mandatory</div>
						<div id="userNameError" style="display: none; color: red;">User
							Name is Mandatory</div></td>
				</tr>
				<tr>
					<td class="col-sm-6"><label for="">Email:</label></td>
					<td class="col-sm-6"><form:input path="email" class="form-control" id="email"
							placeholder="Enter email" autocomplete="off"></form:input>
						<div id="emailError" style="display: none; color: red;">Email
							is Mandatory</div>
						<div id="emailError" style="display: none; color: red;">Email
							is Mandatory</div></td>
				</tr>
				<tr>
					<td colspan="2" class="col-sm-6">
						<button class="btn btn-primary" type="submit"
							 ><spring:message code="label.save"/></button></td>
						<td class="col-sm-6">
						<button type="button" name="Back"
							onclick="javascript:window.location='/annona/';"
							class="btn btn-success"><spring:message code="label.cancel"/></button>
					</td>
				</tr>
			</table>
		</form:form>

	</div>

</div>
				</div>
			</div>
		</div>
	</section>
	<style>
		section#bodychain {
    margin-top: 122px;
}	h3 {
    text-align: center;
    color: #797979;
    font-family: sans-serif;
}
.loginForm1 {
    margin-bottom: 35px;
}
	</style>