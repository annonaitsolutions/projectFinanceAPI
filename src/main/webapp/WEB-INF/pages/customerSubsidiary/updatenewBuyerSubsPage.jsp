<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script type="text/javascript">
	function validateForm() {
		/* var d = new Date().getTime();
		var uuid = 'xxxxxx'.replace(/[xy]/g, function(c) {
			var r = (d + Math.random() * 16) % 16 | 0;
			d = Math.floor(d / 16);
			return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
		});
		document.newbuyer.transactionId.value = uuid;
		
		var d1 = new Date().getTime();
		var uuid1 = 'xxxxxx'.replace(/[xy]/g, function(c) {
			var r1 = (d1+ Math.random() * 16) % 16 | 0;
			d1 = Math.floor(d1 / 16);
			return (c1 == 'x' ? r1 : (r1& 0x3 | 0x8)).toString(16);
		});
		document.newbuyer.transactionId.value = uuid; */
	}
</script>

<section>
	<div class="container">
		<div class="row">
			<div class="successMsg"
				style="text-align: center; color: green; font-size: 18px;">${success}</div>
			<div class="row-fluid apps">
				<h3 align="center">Buyer  Details</h3>
				<form:form name="newbuyer" action="newbuyerConfirmSubList" commandName="newBuyerForm" onsubmit="return validateForm()">
					<table align="center">


						<tr>
							<td class="heading_text"><b>Customer Name</b></td>
							<td><form:input path="name" id="name"
									value="${model.newBuyerForm.name}"
									readonly="true" />
						</tr>
                         <tr>
							<td class="heading_text"><b>Buyer Name</b></td>
							<td><form:input path="buyerName" id="buyerName"
									value="${model.newBuyerForm.buyerName}" readonly="true" />
						</tr>



						<tr>
							<td class="heading_text"><b>Buyer Company Name</b></td>
							<td><form:input path="companyName" id="companyName"
									value="${model.newBuyerForm.companyName}" readonly="true" />
						</tr>

						<tr>
							<td class="heading_text"><b>Buyer Bank</b></td>
							<td><form:input path="bank" id="bank"
									value="${model.newBuyerForm.bank}" readonly="true" />
						</tr>
						
							<tr>
							<td class="heading_text"><b>Buyer Bank Email</b></td>
							<td><form:input path="bankEmail" id="bankEmail"
									value="${model.newBuyerForm.bankEmail}" readonly="true" />
						</tr>
						<tr>
							<td class="heading_text"><b>Pin Code</b></td>
							<td><form:input path="pinCode" id="pinCode"
									value="${model.newBuyerForm.pinCode}" readonly="true" />
						</tr>

						<tr>
							<td class="heading_text"><b>Country</b></td>
							<td><form:input path="country" id="country"
									value="${model.newBuyerForm.country}" readonly="true" />
						</tr>
						<tr>
							<td class="heading_text"><b>State</b></td>
							<td><form:input path="state" id="state"
									value="${model.newBuyerForm.state}" readonly="true" />
						</tr>
                         <tr>
							<td class="heading_text"><b>City</b></td>
							<td><form:input path="city" id="city"
									value="${model.newBuyerForm.city}" readonly="true" />
						</tr>

						<tr>
							<td class="heading_text"><b>Contact Number</b></td>
							<td><form:input path="contactNum" id="contactNum"
									value="${model.newBuyerForm.contactNum}" readonly="true" />
						</tr>

                      

	                        <tr>
							<td class="heading_text"><b>Alt Contact Number</b></td>
							<td><form:input path="altcontactNum" id="altcontactNum"
									value="${model.newBuyerForm.altcontactNum}" readonly="true" />
						</tr>

						<tr>
							<td class="heading_text"><b>Alt Email</b></td>
							<td><form:input path="altEmail" id="altEmail"
									value="${model.newBuyerForm.altEmail}" readonly="true" />
						</tr>
						
							<tr>
							<td class="heading_text"><b>Email</b></td>
							<td><form:input path="email" id="email"
									value="${model.newBuyerForm.email}" readonly="true" />
						</tr>

						<tr>
							<td><b>Buyer Bank Branch:</b></td>
							<td><form:input path="branch"
									value="${model.newBuyerForm.branch}" readonly="true" /></td>
						</tr>
						
							<tr>
							<td><b>Currency Dealtin:</b></td>
							<td><form:input path="currencydeal"
									value="${model.newBuyerForm.currencydeal}" readonly="true" /></td>
						</tr>

						<tr>
							<td><b>Address:</b></td>
							<td><form:input path="address"
									value="${model.newBuyerForm.address}" readonly="true" /></td>
							<form:hidden path="transactionId" value="" />
						
						</tr>
						
							

					</table>

					<div class="col-sm-11">
						<table align="center">
							<tr>
								<td class="col-sm-8"><input type="submit" size="3"
									value="Continue" class="btn btn-primary"></td>
								<td><button type="button" name="Back"
										onclick="javascript:window.location='/annona/admin/regulationPage';"
										class="btn btn-success">Cancel</button></td>

							</tr>
						</table>
					</div>

				</form:form>
			</div>
		</div>
</section>
