<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="col-sm-9 col-md-9 body_fixed">
	<form:form action="saveSwapAccount" commandName="swapAccountForm" onsubmit="return val();">
		<div class="col-sm-12 col-md-12 header_customer">
			<h3 align="center"><spring:message code="label.swapAccount"/></h3>
		</div>
		<div class="col-sm-12 col-md-12">
		<div class="col-sm-6 col-md-6 header_customer">
			<h3 align="center"><spring:message code="label.swapFrom"/></h3>
			
			<table align="center">
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
		<div class="col-sm-6 col-md-6 header_customer">
			<h3><spring:message code="label.swapTo"/></h3>
			<table align="center">
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.userName"/></b></td>
					<td class="col-sm-7"><form:input path="newUser" placeholder="Enter User Name" />
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.contactNumber"/></b></td>
					<td class="col-sm-7"><form:input path="newContactNo" placeholder="Enter Contact Number" id="contactNum"/>
					 <div id="contactNum1Error" style="display:none;color:red;"><spring:message code="label.validation"/></div>
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.email"/></b></td>
					<td class="col-sm-7"><form:input path="newEmail" placeholder="Enter Email" id="email" />
			     <div id="emailError" style="display:none;color:red;"><spring:message code="label.validation"/></div>
				</tr>
			</table>
			</div>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
			<p align="center" style="padding-top: 10px;">
			<form:hidden path="endUserId" value="${swapAccountForm.endUserId}"/>
				<input type="submit" class="btn btn-primary" value="<spring:message code="label.swap"/>"> 
				<a href="usersForSwapRevert" class="btn btn-success"><spring:message code="label.back"/></a>
			</p>
		</div>
	</form:form>
</div>

<script>
	function val() {

	
		
		var phoneNum            = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/; 
		var reg                 = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

		var canSubmit = true;

	
		
	
		 if(contactNum.value.match(/^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/) && !(document.getElementById('contactNum').value == '' )) {
	    	 
	    	  document.getElementById('contactNum1Error').style.display='none';
	      }
	      else {
	       
	        document.getElementById('contactNum1Error').style.display='block';
	        canSubmit = false;
	      }

			if(email.value.match(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/) &&!(document.getElementById('email').value == ''))
	  	{
	  		
	  		document.getElementById('emailError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('emailError').style.display='block';
	  	     canSubmit = false;
	  	} 

  if (canSubmit == false) {
			return false;
		}
	}
</script>


