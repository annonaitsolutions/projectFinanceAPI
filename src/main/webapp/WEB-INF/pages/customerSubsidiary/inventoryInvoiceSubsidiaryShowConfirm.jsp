<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section class="container-fluid padding-top15">
	<div class="row-fluid">
		<div class="successMsg"
			style="text-align: center; color: green; font-size: 18px;">${success}</div>
		<div class="row-fluid apps">

			<form:form action="updateinventoryInvoiceSubsidiaryShowConfirm" method="post"
				commandName="inventoryForm" name="inventory"
				onsubmit="return validateForm()">
				<br>
				<h3>Invoice Details</h3>
				<div class="col-sm-6">
					<table align="center">
	

						<tr>
							<td class="heading_text"><b>Customer Name</b><span
								style="color: red">*</span></td>
							<td><form:input path="customerName" id="customerName"
									readonly="true" /></td>
						</tr>
							<tr>
							<td class="heading_text"><b>Customer Head Name</b><span
								style="color: red">*</span></td>
							<td><form:input path="customerHeadName" id="customerName"
									readonly="true" /></td>
						</tr>
						<tr>
							<td class="heading_text"><b>Supplier Name</b><span
								style="color: red">*</span></td>
							<td><form:input path="supplierName" id="supplierName"
									readonly="true" /></td>
						</tr>
						<tr>
							<td class="heading_text"><b>Master Key</b><span
								style="color: red">*</span></td>
							<td><form:input path="masterKey" id="masterKey"
									readonly="true" /></td>
						</tr>
						<tr>
							<td class="heading_text"><b>Purchase Key</b><span
								style="color: red">*</span></td>
							<td><form:input path="poKey" id="poKey" readonly="true" /></td>
						</tr>
						
						<tr>
							<td class="heading_text"><b>Amount</b><span
								style="color: red">*</span></td>
							<td><form:input path="amount" id="amount" readonly="true" />
							</td>
						</tr>
						<tr>
							<td class="heading_text"><b>Total Quantity</b><span
								style="color: red">*</span></td>
							<td><form:input path="quantity" id="quantity"
									readonly="true" /></td>
						</tr>

						<tr>
							<td class="heading_text"><b>Used Quantity</b><span
								style="color: red">*</span></td>
							<td><form:input path="usedQuantity" id="usedQuantity"
									readonly="true" /></td>
						</tr>

						<tr>
							<td class="heading_text"><b>Damaged Quantity</b><span
								style="color: red">*</span></td>
							<td><form:input path="damaged" id="damaged" readonly="true" />
							</td>
						</tr>


						<tr>
							<td class="heading_text"><b>Quantity Remaining</b><span
								style="color: red">*</span></td>
							<td><form:input path="total" id="total" readonly="true" /></td>
						</tr>

					</table>





				</div>


				<tr>
					<td><input type="submit" class="btn btn-primary"
						value="Continue"></td>
					<td><a href="user" class="btn btn-success" name="back"
						onclick="javascript:window.location='#';" />back</a></td>
				</tr>

				</table>
			</form:form>