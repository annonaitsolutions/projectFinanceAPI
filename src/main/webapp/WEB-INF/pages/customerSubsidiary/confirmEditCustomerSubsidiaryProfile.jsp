<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<section>
	<div class="container">
		<div class="row">

			<div class="row-fluid apps">
				<h3 align="center">Profile Details</h3>
				<form:form name="customerDetails" action="updateCustomerSubsidiaryDetails" commandName="endUserForm"
					onsubmit="">
					<table align="center">
						<form:hidden path="id" />
						

						<tr>
							<td class="heading_text"><b>User Name :</b></td>
							<td><form:input path="userName" id="userName" value=""
									readonly="true" />
						</tr>

						<tr>
							<td class="heading_text"><b>Display Name :</b></td>
							<td><form:input path="displayName" id="displayName" value=""
									readonly="true" />
						</tr>


						<tr>
							<td><b>Contact No :</b></td>
							<td><form:input path="contactNo" value="" readonly="true" /></td>

						</tr>

						<tr>
							<td><b>Alternative Contact No :</b></td>
							<td><form:input path="altContactNo" value="" readonly="true" /></td>

						</tr>

						<tr>
							<td><b>Email :</b></td>
							<td><form:input path="email" value="" readonly="true" /></td>
						</tr>
						<tr>
							<td><b>Alternative Email :</b></td>
							<td><form:input path="altEmail" value="" readonly="true" /></td>
						</tr>

						


						<form:hidden path="transactionId" value="" />
					</table>

					<div class="col-sm-11">
						<table align="center">
							<tr>
								<td class="col-sm-8"><input type="submit" size="3"
									value="Continue" class="btn btn-primary"></td>
								<td><button type="button" name="Back"
										onclick="javascript:window.location='/annona/userSubsidiary/userSubsidiary';"
										class="btn btn-success">Cancel</button></td>

							</tr>
						</table>
					</div>

				</form:form>
			</div>
		</div>
</section>
