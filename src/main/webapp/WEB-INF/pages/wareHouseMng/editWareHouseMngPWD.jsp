<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

  <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

				<div class="col-sm-12 col-md-12 col-lg-12">
				<div class="p_content">
					<form:form action="updateEditWareHouseMngPWD" name="editProfile" autocomplete="off" onsubmit="return validateForm()" commandName="endUserForm">
						
						<div class="form_page">
						<form:hidden path="id"/>
						<form:hidden path="transactionId"/>
						<div id="alertPWD" style="display: none; color: red;"><spring:message code="label.bothShouldMatch"/></div>
				<div id="alertPWD" style="display: none; color: red;"><spring:message code="label.bothShouldMatch"/></div>
						
							<table align="center">
							<tr>
							<td class="col-sm-6">
								<label for=""><spring:message code="label.newPassword"/></label></td>
									
									<td class="col-sm-6"><form:password path="newPassword" class="form-control"
										id="newPassword" placeholder="Enter new Password" autocomplete="off" ></form:password>
										<div id="newPasswordError" style="display: none; color: red;"><spring:message code="label.newPasswordMandatory"/></div>
			              	<div id="newPasswordError" style="display: none; color: red;"><spring:message code="label.newPasswordMandatory"/></div>
									</td>
								</tr>
								<tr>
									<td class="col-sm-6"><label for=""><spring:message code="label.confirmPassword"/></label></td>
									<td class="col-sm-6"><form:password path="confirmNewPassword" class="form-control"
										id="confirmNewPassword" placeholder="Enter confirm New Password" autocomplete="off"></form:password>
										<div id="confirmNewPasswordError" style="display: none; color: red;"><spring:message code="label.confirmPasswordMandatory"/></div>
				             <div id="confirmNewPasswordError" style="display: none; color: red;"><spring:message code="label.confirmPasswordMandatory"/></div>
									</td>
								</tr>
								
								<tr>
									<td colspan="2" class="col-sm-6">									
									<td class="col-sm-6"><input type="submit"  value="<spring:message code="label.update"/>" class="btn btn-primary">
									<a href="wareHouseMngPage" class="btn btn-success" ><spring:message code="label.back"/></a></td>
								</tr>
							</table>
						</div>
				</form:form>
				</div>
		</div>
	</div>


<script>
	function validateForm() {

		var newPassword = document.getElementById('newPassword');
		
		var confirmNewPassword = document.getElementById('confirmNewPassword');
	    var password = /^[A-Za-z0-9!@#$%^&*()_]{6,20}$/;
		var canSubmit = true;

	 	if(newPassword.value.match(/^[A-Za-z0-9!@#$%^&*()_]{6,20}$/) &&!(document.getElementById('newPassword').value == ''))
	  	{
	  		
	  		document.getElementById('newPasswordError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('newPasswordError').style.display='block';
	  	     canSubmit = false;
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