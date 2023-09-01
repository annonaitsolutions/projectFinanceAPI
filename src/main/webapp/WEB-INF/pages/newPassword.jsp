<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<section>
	<div class="container">
		<div class="row">
				<div class="p_content">
					<form:form action="newForgotPassword" name="editProfile" autocomplete="off" onsubmit="return validateForm()" commandName="endUserForm">
						
						<div class="form_page">
						<form:hidden path="id"/>
						<form:hidden path="transactionId"/>
						
						<h3>Reset Password</h3>
						
						<div id="alertPWD" style="display: none; color: red;">Both should be matched</div>
				<div id="alertPWD" style="display: none; color: red;">Both should be matched</div>
						
							<table align="center">
							
								<tr>
								
									<td class="col-sm-6">
									
									
									<label for="">New password:</label></td>
									
									<td class="col-sm-6"><form:password path="newPassword" class="form-control"
										id="newPassword" placeholder="new Password" autocomplete="off" ></form:password>
										<div id="newPasswordError" style="display: none; color: red;">New Password is Mandatory</div>
				<div id="newPasswordError" style="display: none; color: red;">New Password is Mandatory</div>
									</td>
								</tr>
								<tr>
									<td class="col-sm-6"><label for="">Confirm New password:</label></td>
									<td class="col-sm-6"><form:password path="confirmNewPassword" class="form-control"
										id="confirmNewPassword" placeholder="confirm New Password" autocomplete="off"></form:password>
										<div id="confirmNewPasswordError" style="display: none; color: red;">Confirm New Password is Mandatory</div>
				<div id="confirmNewPasswordError" style="display: none; color: red;">Confirm New Password is Mandatory</div>
									</td>
								</tr>
								
								<tr>
									<td colspan="2"  class="col-sm-6">
										<button class="btn  btn-primary" type="submit" >Update</button>
									</td>
									<td colspan="2"  class="col-sm-6">
										<button class="btn btn-primary" type="cancel">Cancel</button>
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

		var newPassword = document.getElementById('newPassword');
		
		var confirmNewPassword = document.getElementById('confirmNewPassword');

		var canSubmit = true;

		if (document.getElementById('newPassword').value == '') {
			document.getElementById('newPasswordError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('newPasswordError').style.display = 'none';
		}
		
		if (document.getElementById('confirmNewPassword').value == '') {
			document.getElementById('confirmNewPasswordError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('confirmNewPasswordError').style.display = 'none';
		}
		
		if(document.editProfile.newPassword.value == document.editProfile.confirmNewPassword.value )
			{
			document.getElementById('alertPWD').style.display = 'none';			
		} else {
			
			document.getElementById('alertPWD').style.display = 'block';
			canSubmit = false;
		}
	

		if (canSubmit == false) {
			return false;
		}

	}
</script>
<style>
		.p_content {
    margin-top: 122px;
}	h3 {
    text-align: center;
    color: #797979;
    font-family: sans-serif;
}
.form_page {

    margin-bottom: 75px;
}
	</style>