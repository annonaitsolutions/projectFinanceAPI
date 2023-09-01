<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="col-sm-9 col-md-9 body_fixed">
	<form:form action="renewAccount" commandName="endUserForm" onsubmit="return val();">
		<div class="col-sm-12 col-md-12 header_customer">
			<h3 align="center"><spring:message code="label.renewAccount"/></h3>
		</div>
		<div class="col-sm-12 col-md-12">

			<table align="center">
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.id"/></b></td>
					<td class="col-sm-7"><form:input path="id" readonly="true"></form:input>
				</tr>
				<tr>
					 <td class="col-sm-6"><b><spring:message code="label.accountExpiry"/></b></td>
					 <td class="col-sm-6">
					 <fmt:formatDate value="${endUserForm.accExpiryDate}" pattern="dd/MM/yyyy" var="formattedDate"/>
					 <form:input path="accExpiryDate"  class="form-control" readonly="true" placeholder="Enter Date" value="${formattedDate}" style="width: 206px;"  id="accExpiryDate"/></td>
					 <td><i class="fa fa-calendar"></i></td>
					 <td id="accExpiryDatetError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										    <td id="accExpiryDateError" class="error" style="display:none"><spring:message code="label.validation"/></td>
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.reason"/></b></td>
					<td class="col-sm-7"><form:textarea path="reason" ></form:textarea>
				</tr>
			</table>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
			<p align="center" style="padding-top: 10px;">
				<input type="submit" class="btn btn-primary" value="<spring:message code="label.update"/>"> 
				<a href="getUsersForBlockUnblockRenew" class="btn btn-success" onclick="javascript:window.location='#';"><spring:message code="label.back"/></a>
			</p>
		</div>
	</form:form>
</div>

<script>

      $(function() {
	  $( "#datepicker" ).datepicker();
	   });
      
      function val() {
          debugger;
		var accExpiryDate = document.getElementById('accExpiryDate');
		
		var canSubmit = true;
		
		if (accExpiryDate == '') {
			document.getElementById('expiryDateError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('expiryDateError').style.display = 'none';
		}

		if (canSubmit == false) {
			return false;
		}
		
      }
	   
</script>