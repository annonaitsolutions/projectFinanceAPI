<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<section>
	<div class="container">
		<div class="row">
			<div class="row-fluid apps">
				<h3 align="center">Buyer Details</h3>
				<form:form name="selectBuyerForCustomerSubUpdate3" action="selectBuyerForCustomerSubUpdate3"
					commandName="newBuyerForm" onsubmit="return validateForm()">
					<table align="center">

						<tr>
							<td><b>ID:</b></td>
							<td><form:input path="id" id="id"
									value="${model.newBuyerForm.id}" readonly="true" />
									</td>
						</tr>

						<tr>
							<td><b>Contact Number:</b></td>
							<td><form:input path="contactNum"
									value="${model.newBuyerForm.contactNum}" readonly="true" /></td>
						</tr>
							<tr>
							<td><b>Customer Name:</b></td>
							<td><form:input path="name"
									value="${model.newBuyerForm.name}" readonly="true" /></td>
						</tr>
						
						<tr>
							<td><b>Pin Code:</b></td>
							<td><form:input path="pinCode"
									value="${model.newBuyerForm.pinCode}" readonly="true" /></td>
						</tr>
						
						<tr>
							<td><b>Country:</b></td>
							<td><form:input path="country"
									value="${model.newBuyerForm.country}" readonly="true" /></td>
						</tr>
						
						<tr>
							<td><b>State:</b></td>
							<td><form:input path="state"
									value="${model.newBuyerForm.state}" readonly="true" /></td>
						</tr>
						
						
						<tr>
							<td><b>City:</b></td>
							<td><form:input path="city"
									value="${model.newBuyerForm.city}" readonly="true" /></td>
						</tr>
						
						
						<tr>
							<td><b>Email:</b></td>
							<td><form:input path="email"
									value="${model.newBuyerForm.email}" readonly="true" /></td>
						</tr>
						
						
						
						<tr>
							<td><b>Buyer Name:</b></td>
							<td><form:input path="buyerName"
									value="${model.newBuyerForm.buyerName}" readonly="true" /></td>
						</tr>
						
						
						<tr>
							<td><b>Buyer Company Name:</b></td>
							<td><form:input path="companyName"
									value="${model.newBuyerForm.companyName}" readonly="true" /></td>
						</tr>
						
						
						<tr>
							<td><b>Buyer Bank:</b></td>
							<td><form:input path="bank"
									value="${model.newBuyerForm.bank}" readonly="true" /></td>
						</tr>
						
						
						
						<tr>
							<td><b>Buyer Bank Email:</b></td>
							<td><form:input path="bankEmail"
									value="${model.newBuyerForm.bankEmail}" readonly="true" /></td>
						</tr>
						
						
						<tr>
							<td><b>Currency Dealtin:</b></td>
							<td><form:input path="currencydeal"
									value="${model.newBuyerForm.currencydeal}" readonly="true" /></td>
						</tr>
						


						<tr>
							<td><b>Address:</b></td>
							<td><form:input path="address"
									value="${model.newBuyerForm.address}" readonly="true" /> <form:hidden
									path="transactionId" value="" /></td>


						</tr>
						<tr>
							<td class="heading_text"><b>Branch</b></td>
							<td><form:input path="branch" id="branch"
									value="${model.newBuyerForm.branch}" readonly="true" />
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