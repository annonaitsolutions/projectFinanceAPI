<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<section>
	<div class="container">
		<div class="row">
			<div class="successMsg"
				style="text-align: center; color: green; font-size: 18px;">${success}</div>
			<div class="row-fluid apps">
				<h3 align="center">New Supplier Confirm</h3>
				<form:form name="selectSupplierUpdate3" action="selectsupplierUpdateForCustomerSub3" commandName="supplierForm" onsubmit="return validateForm()">
					<table align="center">

						<tr>
							<td class="heading_text"><b>ID</b></td>
							<td><form:input path="id" id="id"
									value="${model.supplierForm.id}" readonly="true" />
						</tr>
						<tr>
							<td class="heading_text"><b>Customer Name:</b></td>
							<td><form:input path="name" id="name"
									value="${model.supplierForm.name}" readonly="true" />
						</tr>

	                     <tr>
							<td class="heading_text"><b>Supplier Name</b></td>
							<td><form:input path="supplierName" id="supplierName"
									value="${model.supplierForm.supplierName}" readonly="true" />
						</tr>
						
							<tr>
							<td class="heading_text"><b>Pin Code</b></td>
							<td><form:input path="pinCode" id="pinCode"
									value="${model.supplierForm.pinCode}" readonly="true" />
						</tr>
						
						
						
							<tr>
							<td class="heading_text"><b>Country</b></td>
							<td><form:input path="country" id="country"
									value="${model.supplierForm.country}" readonly="true" />
						</tr>
						
						
						
							<tr>
							<td class="heading_text"><b>State</b></td>
							<td><form:input path="state" id="state"
									value="${model.supplierForm.state}" readonly="true" />
						</tr>
						
						
							<tr>
							<td class="heading_text"><b>City</b></td>
							<td><form:input path="city" id="city"
									value="${model.supplierForm.city}" readonly="true" />
						</tr>
						
						
							<tr>
							<td class="heading_text"><b>Email</b></td>
							<td><form:input path="email" id="email"
									value="${model.supplierForm.email}" readonly="true" />
						</tr>
						
						
							<tr>
							<td class="heading_text"><b>Supplier Company Name</b></td>
							<td><form:input path="companyName" id="companyName"
									value="${model.supplierForm.companyName}" readonly="true" />
						</tr>
						
						
							
							<tr>
							<td class="heading_text"><b>Supplier Bank</b></td>
							<td><form:input path="bank" id="bank"
									value="${model.supplierForm.bank}" readonly="true" />
						</tr>
						
						
							<tr>
							<td class="heading_text"><b>Supplier Bank Email</b></td>
							<td><form:input path="bankEmail" id="bankEmail"
									value="${model.supplierForm.name}" readonly="true" />
						</tr>
						

						<tr>
							<td><b>Contact Number:</b></td>
							<td><form:input path="contactNum"
									value="${model.supplierForm.contactNum}" readonly="true" /></td>
						</tr>

						<tr>
							<td class="heading_text"><b>Branch</b></td>
							<td><form:input path="branch" id="branch"
									value="${model.supplierForm.branch}" readonly="true" />
						</tr>




						<tr>
							<td><b>Address:</b></td>
							<td><form:input path="address"
									value="${model.supplierForm.address}" readonly="true" /> <form:hidden
									path="transactionId" value="" /></td>


						</tr>
						<tr>
							<td class="heading_text"><b>Currency DealtIn</b></td>
							<td><form:input path="currencydeal" id="currencydeal"
									value="${model.supplierForm.currencydeal}" readonly="true" />
						</tr>
						

					</table>

					<div class="col-sm-11">
						<table align="center">
							<tr>
								<td class="col-sm-8"><input type="submit" size="3"
									value="Continue" class="btn btn-primary"></td>
								<td><button type="button" name="Back"
										onclick="javascript:window.location='/annona/admin/selectRole?id=76';"
										class="btn btn-success">Cancel</button></td>

							</tr>
						</table>
					</div>

				</form:form>
			</div>
		</div>
</section>
