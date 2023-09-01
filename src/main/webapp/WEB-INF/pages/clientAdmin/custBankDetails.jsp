<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
	function validateForm() {

		var bankName = document.getElementById('bankName');
		var contactNo           =  document.getElementById('contactNo');
		var email               =  document.getElementById('email');
		var phoneNum            = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/; 
		var reg                 = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		
	  var canSubmit = true; 

	 if(contactNo.value.match(/^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/) && !(document.getElementById('contactNo').value == '' )) {
	    	 
	    	  document.getElementById('contactNo1Error').style.display='none';
	      }
	      else {
	       
	        document.getElementById('contactNo1Error').style.display='block';
	        canSubmit = false;
	      }

	      
	  	if(email.value.match(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/) &&!(document.getElementById('email').value == ''))
	  	{
	  		
	  		document.getElementById('emailError1').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('emailError1').style.display='block';
	  	     canSubmit = false;
	  	}

		

		if (document.getElementById('bankName').value == '') {
			document.getElementById('bankNameError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('bankNameError').style.display = 'none';
		}

		if (canSubmit == false) {
			return false;
		}

	}
</script>
 <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

		<div class="error">${errorMsg}</div>

		<br>
		<form:form action="confirmCustBankDetails" method="post"
			commandName="customerBankDetailsForm" onsubmit="return validateForm()">
			  <div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 align="center"><spring:message code="label.addNewBankDetails"/></h3>
			  </div>
			  <table align="center">
			  <tr>
				  <td class="col-sm-6"><spring:message code="label.bankName"/><span
					style="color: red">*</span></td>
				  <td class="col-sm-6">
					<form:input path="bankName" class="form-control" id="bankName"
						placeholder="Enter Name" />
					<div id="bankNameError" style="display: none; color: red;"><spring:message code="label.bankNameValidation"/></div>
					<div id="bankNameError" style="display: none; color: red;"><spring:message code="label.bankNameValidation"/></div>
				  </td>
			</tr>
			<tr>
				<td class="col-sm-6"><spring:message code="label.location"/><span
					style="color: red"></span></td>
				<td class="col-sm-6">
					<form:input path="location" class="form-control"
						placeholder="Enter Location"  id="location" />
				</td>
			</tr>
			<tr>
				<td class="col-sm-6"><spring:message code="label.branch"/><span
					style="color: red"></span></td>
				<td class="col-sm-6">
					<form:input path="branch" class="form-control"
						placeholder="Enter Branch"  id="branch" />
				</td>
			</tr>
			<tr>
				<td class="col-sm-6"><spring:message code="label.branchCode"/><span
					style="color: red"></span></td>
				<td class="col-sm-6">
					<form:input path="branchCode" class="form-control"
						placeholder="Enter Branch Code"  id="branchCode" />
				</td>
			</tr>
			<tr>
				<td class="col-sm-6"><spring:message code="label.contactNumber"/><span style="color: red">*</span></td>
				<td class="col-sm-6">
					<form:input path="contactNo" class="form-control" placeholder="Enter Contact Number" id="contactNo"/>
					 <div id="contactNo1Error" style="display:none;color:red;"><spring:message code="label.contactNumberValidation"/></div>
					 <div id="contactNo1Error" style="display:none;color:red;"><spring:message code="label.contactNumberValidation"/></div>
				</td>
			</tr>
			<tr>
				<td class="col-sm-6"><spring:message code="label.email"/><span style="color: red">*</span></td>
	
				<td class="col-sm-6">
					<form:input path="email" class="form-control" placeholder="Enter Email" id="email"/>
					  <div id="emailError1" style="display:none;color:red;"><spring:message code="label.emailValidation"/></div>
					  <div id="emailError1" style="display:none;color:red;"><spring:message code="label.emailValidation"/></div>
				</td>
			</tr>
		</table>
		<div class="col-sm-12 col-md-12">&nbsp;</div>
			<table align="center">
				<tr>
					<td class="col-sm-8"><input type="submit" size="3"
						value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
					<td><a href="custAdminPage" class="btn btn-success"><spring:message code="label.back"/></a></td>

				</tr>
			</table>

		</form:form>

		<br>
		<br>
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3><spring:message code="label.bankDetailsTable"/></h3>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
		<table class="table data jqtable example" id="my-table">
			<thead>
				<tr>
					<th><spring:message code="label.id"/></th>
					<th><spring:message code="label.transactionId"/></th>
					<th><spring:message code="label.bankName"/></th>
					<th><spring:message code="label.location"/></th>
					<th><spring:message code="label.branch"/></th>
					<th><spring:message code="label.contactNumber"/></th>
					<th><spring:message code="label.email"/></th>
					<th><spring:message code="label.action"/></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${! empty customerBankDetails}">
					<c:forEach items="${customerBankDetails}" var="customerBankDetails">
						<tr>
							<td><c:out value="${customerBankDetails.id}"></c:out></td>
							<td><c:out value="${customerBankDetails.transactionId}"></c:out></td>
							<td><c:out value="${customerBankDetails.bankName}"></c:out></td>
							<td><c:out value="${customerBankDetails.location}"></c:out></td>
							<td><c:out value="${customerBankDetails.branch}"></c:out></td>
							<td><c:out value="${customerBankDetails.contactNo}"></c:out></td>
							<td><c:out value="${customerBankDetails.email}"></c:out></td>

							<td><a href="selectCustBankDetails?id=${customerBankDetails.id}"
								class="btn btn-primary"><spring:message code="label.edit"/></a></td>
						</tr>
					</c:forEach>
				</c:if>

			</tbody>
		</table>

	</div>