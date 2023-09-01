<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<section>
	<div class="container">
		<div class="row">
				<div class="p_content">
					<form:form action="updateLoginChangePwd" autocomplete="off"
			onsubmit="return validateForm()" commandName="endUserForm" id="pwdForm">
						
						<div class="form_page">
						<form:hidden path="id"/>
						<form:hidden path="transactionId"/>
						
						<h3>Details</h3>
						<div style="text-align:center;">
			<font color="red" >${error}</font>
		</div>
				<div id="pwdUnEqualError" style="text-align:center; display: none; color: red;">New and Confirm Passwords must match</div>
						
							<table align="center">
							
								<tr>

					<td class="col-sm-6"><label for="">Old Password :</label></td>

					<td class="col-sm-6"><form:password path="password" class="form-control"
							id="password" placeholder="Old Password" autocomplete="off"></form:password>
						<div id="passwordError" style="display: none; color: red;">Password is Mandatory</div>						
				</tr>
								
								<tr>
					<td class="col-sm-6"><label for="">New Password :</label></td>
					<td class="col-sm-6"><form:password path="newPassword" class="form-control" id="newPassword"
							placeholder="New Password" autocomplete="off"></form:password>
						<div id="newPwdError" style="display: none; color: red;">Password
							is Mandatory</div>						
				</tr>
				
				<tr>
					<td class="col-sm-6"><label for="">Confirm New Password :</label></td>
					<td class="col-sm-6"><form:password path="confirmNewPassword" class="form-control" id="confirmNewPassword"
							placeholder="Confirm New Password" autocomplete="off"></form:password>
						<div id="cfmPwdError" style="display: none; color: red;">Password
							is Mandatory</div>						
				</tr>
				
				<tr>
					<td colspan="2" class="col-sm-6">
					<form:hidden path="id" value="${endUserForm.id}"/>
						<button class="btn btn-primary" type="submit"
							 ><spring:message code="label.save"/></button></td>
						<td class="col-sm-6">
						<button type="button" name="Back"
							onclick="javascript:window.location='/annona/';"
							class="btn btn-success"><spring:message code="label.cancel"/></button>
					</td>
				</tr>
						
							</table>
						</div>
				</form:form>
				</div>
		</div>
	</div>

</section>

<script>
	function validateForm() {

		var oldPassword = document.getElementById('password').value;
		var newPassword = document.getElementById('newPassword').value;
		var confirmNewPassword = document.getElementById('confirmNewPassword').value;

		var canSubmit = true;

		if (oldPassword == '') {
			document.getElementById('passwordError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('passwordError').style.display = 'none';
		}

		if (newPassword == '') {
			document.getElementById('newPwdError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('newPwdError').style.display = 'none';
		}
		
		if (confirmNewPassword == '') {
			document.getElementById('cfmPwdError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('cfmPwdError').style.display = 'none';
		}
		
		if(newPassword != confirmNewPassword) {
			document.getElementById("pwdForm").reset();
			document.getElementById('pwdUnEqualError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('pwdUnEqualError').style.display = 'none';
		}

		if (canSubmit == false) {
			return false;
		}

	}
	
</script>
<style>
		.p_content {
    margin-top: 100px;
}	h3 {
    text-align: center;
    color: #797979;
    font-family: sans-serif;
}
.form_page {

    margin-bottom: 60px;
}
	</style>
