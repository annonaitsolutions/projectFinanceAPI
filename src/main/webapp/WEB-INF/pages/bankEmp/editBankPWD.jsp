<%@include file="taglib_includes.jsp"%>

 <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
		 <div class="col-sm-12 col-md-12 col-lg-12">
				<div class="p_content">
					<form:form action="updateEditBankPWD" name="editProfile" autocomplete="off" onsubmit="return validateForm()" commandName="endUserForm">
						
						<div class="form_page">
						<form:hidden path="id"/>
						<form:hidden path="transactionId"/>
						<div id="alertPWD" style="display: none; color: red;"><spring:message code="label.passVal"/></div>
				<div id="alertPWD" style="display: none; color: red;"><spring:message code="label.passVal"/></div>
						
							<table>
							
								<tr>
								
									<td>
									
									
									<label for=""><spring:message code="label.newPass"/></label></td>
									
									<td><form:password path="newPassword" class="form-control"
										id="newPassword" placeholder="Enter new Password" autocomplete="off" ></form:password>
										<div id="newPasswordError" style="display: none; color: red;"><spring:message code="label.passVal1"/></div>
				<div id="newPasswordError" style="display: none; color: red;"><spring:message code="label.passVal1"/></div>
									</td>
								</tr>
								<tr>
									<td><label for=""><spring:message code="label.confirmPass"/></label></td>
									<td><form:password path="confirmNewPassword" class="form-control"
										id="confirmNewPassword" placeholder="Enter confirm New Password" autocomplete="off"></form:password>
										<div id="confirmNewPasswordError" style="display: none; color: red;"><spring:message code="label.passVal2"/></div>
				<div id="confirmNewPasswordError" style="display: none; color: red;"><spring:message code="label.passVal2"/></div>
									</td>
								</tr>
								
								<tr>
									<td colspan="2">
										<button class="btn btn-md btn-primary btn-block" type="submit"
											style="width: 100px; float: right;"><spring:message code="label.update"/></button>
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