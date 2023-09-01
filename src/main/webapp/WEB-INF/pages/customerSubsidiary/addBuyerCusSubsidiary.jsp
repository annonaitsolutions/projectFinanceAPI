<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script>
	function val() {

		var buyerName = document.getElementById('buyerName');
		var companyName = document.getElementById("companyName");
		var bank = document.getElementById('bank');
		var bankEmail = document.getElementById("bankEmail");
		var branch = document.getElementById('branch');
		var address = document.getElementById("address");
		var country = document.getElementById('country');
		var state = document.getElementById("state");
		var pinCode = document.getElementById('pinCode');
		var city = document.getElementById('city');
		var contactNum = document.getElementById("contactNum");
		var altcontactNum = document.getElementById('altcontactNum');
		var email = document.getElementById("email");
        var currencydeal=document.getElementById("currencydeal"); 
		var canSubmit = true;

		if (document.getElementById('buyerName').value == '') {
			document.getElementById('buyerNameError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('buyerNameError').style.display = 'none';
		}

		if (document.getElementById('companyName').value == '') {
			document.getElementById('companyNameError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('companyNameError').style.display = 'none';
		}
		if (document.getElementById('bank').value == '') {
			document.getElementById('bankError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('bankError').style.display = 'none';
		}

		if (document.getElementById('bankEmail').value == '') {
			document.getElementById('bankEmailError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('bankEmailError').style.display = 'none';
		}
		if (document.getElementById('branch').value == '') {
			document.getElementById('branchError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('branchError').style.display = 'none';
		}

		if (document.getElementById('address').value == '') {
			document.getElementById('addressError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('addressError').style.display = 'none';
		}
		if (document.getElementById('country').value == '-1') {
			document.getElementById('countryError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('countryError').style.display = 'none';
		}

		if (document.getElementById('state').value == '-1'
				|| document.getElementById('state').value == '') {
			document.getElementById('stateError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('stateError').style.display = 'none';
		}

		if (document.getElementById('pinCode').value == '') {
			document.getElementById('pinCodeError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('pinCodeError').style.display = 'none';
		}

		if (document.getElementById('city').value == '') {
			document.getElementById('cityError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('cityError').style.display = 'none';
		}

		if (document.getElementById('contactNum').value == ''
				|| !contactNumber.value.match(/^[-]?\d*\.?\d*$/)) {
			document.getElementById('contactNumError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('contactNumError').style.display = 'none';
		}

		if (document.getElementById('altcontactNum').value == '') {
			document.getElementById('altcontactNumError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('altcontactNumError').style.display = 'none';
		}
		if (document.getElementById('email').value == '') {
			document.getElementById('emailError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('emailError').style.display = 'none';
		}
		
		if (document.getElementById('currencydeal').value == '') {
			document.getElementById('currencydealError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('currencydealError').style.display = 'none';
		} 

		if (canSubmit == false) {
			return false;
		}
	}
</script>


<section class="container-fluid padding-top15">
	<div class="row-fluid">
		<div class="successMsg"
			style="text-align: center; color: green; font-size: 18px;">${success}</div>
		<div class="row-fluid apps">



			<form:form action="updatenewBuyerSubsPage" method="post"
				commandName="newBuyerForm" id="newBuyer" onsubmit="return val();">



				<br>
				<h3>Create New Buyer</h3>
				<div class="col-sm-6">
					<table align="center">


						 <tr>
					<td class="heading_text"><b><spring:message code="label.customerName"/> </b><span style="color:red">*</span></td>
					<td>
					<form:input path="name" value="${model.newBuyerForm.name}" id="name" readonly="true"/>
					</td>
				</tr>
			
						<tr>
							<td class="heading_text"><b>Buyer Name</b><span
								style="color: red">*</span></td>
							<td><form:input path="buyerName" id="buyerName" />
								<div id="buyerNameError" style="display: none; color: red;">Buyer
									Name is Mandatory</div>
								<div id="buyerNameError" style="display: none; color: red;">Buyer
									Name is Mandatory</div></td>
						</tr>
						<tr>
							<td class="heading_text"><b>Buyer Company Name</b><span
								style="color: red">*</span></td>
							<td><form:input path="companyName" id="companyName" />
								<div id="companyNameError" style="display: none; color: red;">Buyer
									Company Name is Mandatory</div>
								<div id="companyNameError" style="display: none; color: red;">Buyer
									Company Name is Mandatory</div></td>
						</tr>

						<tr>
							<td class="heading_text"><b>Buyer Bank</b><span
								style="color: red">*</span></td>
							<td><form:input path="bank" id="bank" />
								<div id="bankError" style="display: none; color: red;">Buyer
									Bank is Mandatory</div>
								<div id="bankError" style="display: none; color: red;">Buyer
									Bank is Mandatory</div></td>
						</tr>

						<tr>
							<td class="heading_text"><b>Buyer Bank Email</b><span
								style="color: red">*</span></td>
							<td><form:input path="bankEmail" id="bankEmail" />
								<div id="bankEmailError" style="display: none; color: red;">Buyer
									Bank Email is Mandatory</div>
								<div id="bankEmailError" style="display: none; color: red;">Buyer
									Bank Email is Mandatory</div></td>
						</tr>
						<tr>
							<td class="heading_text"><b>Buyer Bank Branch</b><span
								style="color: red">*</span></td>
							<td><form:input path="branch" id="branch" />
								<div id="branchError" style="display: none; color: red;">Buyer
									Bank Branch is Mandatory</div>
								<div id="branchError" style="display: none; color: red;">Buyer
									Bank Branch is Mandatory</div></td>
						</tr>
						<tr>
							<td class="heading_text"><b>Address</b><span
								style="color: red">*</span></td>
							<td><form:input path="address" id="address" />
								<div id="addressError" style="display: none; color: red;">Address
									is Mandatory</div>
								<div id="addressError" style="display: none; color: red;">Address
									is Mandatory</div></td>
						</tr>

					</table>
				</div>
				<table align="center">
					<div class="col-sm-6">
						<tr>
							<td class="heading_text"><b>Pin Code</b><span
								style="color: red">*</span></td>
							<td><form:input path="pinCode" id="pinCode" />
								<div id="pinCodeError" style="display: none; color: red;">PinCode
									is Mandatory</div>
								<div id="pinCodeError" style="display: none; color: red;">PinCode
									is Mandatory</div></td>
						</tr>
						<tr>
							<td class="heading_text"><b>Country</b><span
								style="color: red">*</span></td>
							<td><form:select path="country" id="country" value="" />
								<div id="countryError" style="display: none; color: red;">Country
									is Mandatory</div>
								<div id="countryError" style="display: none; color: red;">Country
									is Mandatory</div></td>
						</tr>

						<tr>
							<td class="heading_text"><b>State</b><span
								style="color: red">*</span></td>
							<td><form:select path="state" id="state" value="" /> <script
									language="javascript">
								populateCountries("country", "state");
							</script>
								<div id="stateError" style="display: none; color: red;">State
									is Mandatory</div>
								<div id="stateError" style="display: none; color: red;">State
									is Mandatory</div></td>
						</tr>
						<tr>
							<td class="heading_text"><b>City</b><span style="color: red">*</span></td>
							<td><form:input path="city" id="city" />
								<div id="cityError" style="display: none; color: red;">City
									is Mandatory</div>
								<div id="cityError" style="display: none; color: red;">City
									is Mandatory</div></td>
						</tr>
						<tr>
							<td class="heading_text"><b>Contact Number</b><span
								style="color: red">*</span></td>
							<td><form:input path="contactNum" id="contactNum" />
								<div id="contactNumError" style="display: none; color: red;">Contact
									Number is Mandatory</div>
								<div id="contactNumError" style="display: none; color: red;">Contact
									Number is Mandatory</div></td>
						</tr>
						<tr>
							<td class="heading_text"><b>Alternate Contact Number</b><span
								style="color: red">*</span></td>
							<td><form:input path="altcontactNum" id="altcontactNum" />
								<div id="altcontactNumError" style="display: none; color: red;">Alt
									Contact Number is Mandatory</div>
								<div id="altcontactNumError" style="display: none; color: red;">Alt
									Contact Number is Mandatory</div></td>
						</tr>
						<tr>
							<td class="heading_text"><b>Email</b><span
								style="color: red">*</span></td>
							<td><form:input path="email" id="email" />
								<div id="emailError" style="display: none; color: red;">Email
									is Mandatory</div>
								<div id="emailError" style="display: none; color: red;">Email
									is Mandatory</div></td>
						</tr>
						<tr>
							<td class="heading_text"><b>Alternate Email</b></td>
							<td><form:input path="altEmail" id="altEmail" /></td>
						</tr>
						<tr>
							<td class="heading_text"><b>Currency DealtIn</b><span
								style="color: red">*</span></td>
							<td><form:input path="currencydeal" id="currencydeal" />
								<div id="currencydealError" style="display: none; color: red;">Currency DealtIn is Mandatory</div>
								<div id="currencydealError" style="display: none; color: red;">Currency DealtIn
									is Mandatory</div></td>
						</tr>
						
						</div>
					

				</table>

				<p align="center" style="padding-top: 10px;">
					<input type="submit" class="btn btn-primary" value="Continue">
					<a href="user" class="btn btn-success" name="back"
						onclick="javascript:window.location='#';" />back</a>

				</p>
			</form:form>



			<br>
			<br>
			<h3 align="middle">Buyer List</h3>
			<table class="table table-hover jqtable" id="bootstrap-table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Buyer Name</th>
						<th>Transaction ID</th>
						<th>Contact Number</th>
						<th>Address</th>
						<th>Branch</th>
						<th>Status</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty newbuyeList}">
						<c:forEach items="${newbuyeList}" var="buyer">
							<tr>
								<td><c:out value="${buyer.id}"></c:out></td>
								<td><c:out value="${buyer.buyerName}"></c:out>
								<td><c:out value="${buyer.transactionId}"></c:out>
								<td><c:out value="${buyer.contactNum}"></c:out></td>
								<td><c:out value="${buyer.address}"></c:out></td>
								<td><c:out value="${buyer.branch}"></c:out></td>
                                <td><c:out value="${buyer.status}"></c:out></td>
                                <td><a href="selectBuyerForCustomerSubUpdate?id=${buyer.id}"class="btn btn-primary">Edit</a></td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table>


		</div>
	</div>
</section>

