<%@include file="taglib_includes.jsp"%>

<section>
	<div class="container">
		<div class="row">
				<div class="p_content">
					<form:form action="updateEditCustomerSubsidiaryPWD" name="editProfile" autocomplete="off" onsubmit="return validateForm()" commandName="endUserForm">
						
						<div class="form_page">
						<form:hidden path="id"/>
						<form:hidden path="transactionId"/>
						<div id="alertPWD" style="display: none; color: red;">Both should be matched</div>
				<div id="alertPWD" style="display: none; color: red;">Both should be matched</div>
						
							<table>
							
								<tr>
								
									<td>
									
									
									<label for="">New password:</label></td>
									
									<td><form:password path="newPassword" class="form-control"
										id="newPassword" placeholder="new Password" autocomplete="off" ></form:password>
										<div id="newPasswordError" style="display: none; color: red;">New Password is Mandatory</div>
				<div id="newPasswordError" style="display: none; color: red;">New Password is Mandatory</div>
									</td>
								</tr>
								<tr>
									<td><label for="">Confirm New password:</label></td>
									<td><form:password path="confirmNewPassword" class="form-control"
										id="confirmNewPassword" placeholder="confirm New Password" autocomplete="off"></form:password>
										<div id="confirmNewPasswordError" style="display: none; color: red;">Confirm New Password is Mandatory</div>
				<div id="confirmNewPasswordError" style="display: none; color: red;">Confirm New Password is Mandatory</div>
									</td>
								</tr>
								
								<tr>
									<td colspan="2">
										<button class="btn btn-md btn-primary btn-block" type="submit"
											style="width: 100px; float: right;">Update</button>
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