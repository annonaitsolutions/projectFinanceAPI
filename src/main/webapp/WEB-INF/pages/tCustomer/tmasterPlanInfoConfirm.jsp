
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">

	function validateForm() {

		var suppliers = document.getElementsByName("supplierName");
		var materials = document.getElementsByName("material");
		var countries = document.getElementsByName("country");
		var quantities = document.getElementsByName("quantityStr");
		var costs = document.getElementsByName("costStr");

		var buyers = document.getElementsByName("buyerName");
		var finalProducts = document.getElementsByName("finalPro");
		var qtyStrs = document.getElementsByName("qtyStr");
		var amtStrs = document.getElementsByName("amtStr");

		var reasons = document.getElementsByName("wcReason");
		var wcAmts = document.getElementsByName("wcAmt");

		var canSubmit = true;

		var table = document.getElementById("dataTable");
		var totalCost = 0.0;

		var buyingCost = document.getElementById('buyingCost').value;
		for (var i = 1; i < table.rows.length; i++) {

			totalCost = +totalCost + +table.rows[i].cells[4].children[1].value;
		}

		for (var i = 0; i < suppliers.length; i++) {

			if (suppliers[i].value == '') {
				document.getElementById('supplierNameError').style.display = 'block';
				canSubmit = false;
			} else {
				document.getElementById('supplierNameError').style.display = 'none';
			}

			if (materials[i].value == '') {
				document.getElementById('materialsError').style.display = 'block';
				canSubmit = false;
			} else {
				document.getElementById('materialsError').style.display = 'none';
			}

			if (countries[i].value == -1) {
				document.getElementById('countryError').style.display = 'block';
				canSubmit = false;
			} else {
				document.getElementById('countryError').style.display = 'none';
			}

			if (quantities[i].value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/)
					|| quantities[i].value.match(/^-?[0-9]+$/)
					&& !(quantities[i].value == '')) {

				document.getElementById('quantityStrError').style.display = 'none';

			} else {
				document.getElementById('quantityStrError').style.display = 'block';
				canSubmit = false;
			}

			if (costs[i].value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/)
					|| costs[i].value.match(/^-?[0-9]+$/)
					&& !(costs[i].value == '')) {

				document.getElementById('costStrError').style.display = 'none';

			} else {
				document.getElementById('costStrError').style.display = 'block';
				canSubmit = false;
			}

		}

		for (var i = 0; i < buyers.length; i++) {

			if (qtyStrs[i].value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/)
					|| qtyStrs[i].value.match(/^-?[0-9]+$/)
					|| (qtyStrs[i].value == '')) {

				document.getElementById('qtyStrError').style.display = 'none';

			} else {
				document.getElementById('qtyStrError').style.display = 'block';
				canSubmit = false;
			}

			if (amtStrs[i].value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/)
					|| amtStrs[i].value.match(/^-?[0-9]+$/)
					|| (amtStrs[i].value == '')) {

				document.getElementById('amtStrError').style.display = 'none';

			} else {
				document.getElementById('amtStrError').style.display = 'block';
				canSubmit = false;
			}

		}
		for (var i = 0; i < reasons.length; i++) {

			if (wcAmts[i].value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/)
					|| wcAmts[i].value.match(/^-?[0-9]+$/)
					|| (wcAmts[i].value == '')) {

				document.getElementById('wcAmtError').style.display = 'none';

			} else {
				document.getElementById('wcAmtError').style.display = 'block';
				canSubmit = false;
			}

		}

		if ((parseInt(totalCost) > parseInt(buyingCost))
				|| (parseInt(totalCost) < parseInt(buyingCost))) {
			document.getElementById('costMismatchError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('costMismatchError').style.display = 'none';
		}

		if (canSubmit == false) {
			return false;
		}
	}

	function addRow1(tableID1) {

		var table = document.getElementById(tableID1);

		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount);

		var colCount = table.rows[1].cells.length;

		for (var i = 0; i < colCount; i++) {
			var newcell = row.insertCell(i);

			newcell.innerHTML = table.rows[1].cells[i].innerHTML;
			switch (newcell.childNodes[1].type) {
			case "text":
				newcell.childNodes[1].value = "";
				break;
			case "select-one":
				newcell.childNodes[1].selectedIndex = 0;
				break;
			}
		}
	}

	function deleteRow1(tableID1) {
		try {
			var table = document.getElementById(tableID1);
			var rowCount = table.rows.length;

			if (rowCount > 2) {
				table.deleteRow(rowCount - 1);
			} else {
				alert("Cannot delete all the rows");
			}

		} catch (e) {
			alert(e);
		}
	}

	function addRow(tableID) {

		var table = document.getElementById(tableID);

		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount);

		var colCount = table.rows[1].cells.length;

		for (var i = 0; i < colCount; i++) {
			var newcell = row.insertCell(i);

			newcell.innerHTML = table.rows[1].cells[i].innerHTML;
			switch (newcell.childNodes[1].type) {
			case "text":
				newcell.childNodes[1].value = "";
				break;
			case "select-one":
				newcell.childNodes[1].selectedIndex = 0;
				break;
			}
		}
	}

	function deleteRow(tableID) {
		try {
			var table = document.getElementById(tableID);
			var rowCount = table.rows.length;

			if (rowCount > 2) {
				table.deleteRow(rowCount - 1);
			} else {
				alert("Cannot delete all the rows");
			}

		} catch (e) {
			alert(e);
		}
	}

	function addRow2(tableID2) {

		var table = document.getElementById(tableID2);

		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount);

		var colCount = table.rows[1].cells.length;

		for (var i = 0; i < colCount; i++) {
			var newcell = row.insertCell(i);

			newcell.innerHTML = table.rows[1].cells[i].innerHTML;
			switch (newcell.childNodes[1].type) {
			case "text":
				newcell.childNodes[1].value = "";
				break;
			case "select-one":
				newcell.childNodes[1].selectedIndex = 0;
				break;
			}
		}
	}

	function deleteRow2(tableID2) {
		try {
			var table = document.getElementById(tableID2);
			var rowCount = table.rows.length;

			if (rowCount > 2) {
				table.deleteRow(rowCount - 1);
			} else {
				alert("Cannot delete all the rows");
			}

		} catch (e) {
			alert(e);
		}
	}

	var y = document.msplan.customerPrefix.value;
	var d = new Date().getTime();
	var uuid = y + '-MP-xxxxxx'.replace(/[xy]/g, function(c) {
		var r = (d + Math.random() * 16) % 16 | 0;
		d = Math.floor(d / 16);
		return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
	});
	document.msplan.masterkey.value = uuid;

	var d1 = new Date().getTime();
	var uuid1 = 'xxxxxx'.replace(/[xy]/g, function(c1) {
		var r1 = (d1 + Math.random() * 16) % 16 | 0;
		d1 = Math.floor(d / 16);
		return (c1 == 'x' ? r1 : (r1 & 0x3 | 0x8)).toString(16);
	});
	document.msplan.transactionId.value = uuid1;
</script>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
	<form:form action="tmasterPlanInfoPost" name="msplan"
		onsubmit="return validateForm()" method="post"
		commandName="tMasterPlanForm">
		<div class="col-sm-6 col-md-6">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center">
					<spring:message code="label.productInfo" />
				</h3>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div id="costMismatchError" class="error"
					style="text-align: center; display: none; color: red;">
					<spring:message code="label.breakDownVal" />
				</div>
				<div style="text-align: center; display: none; color: red;">${successFull}</div>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
				<table>

					<tr>
						<td class="heading_text"><b><spring:message
									code="label.goodsCategory" /></b><span style="color: red">*</span></td>
						<td><form:input path="category" readonly="true" id="category" />
						</td>
					</tr>
					<tr>
						<td class="heading_text"><b><spring:message
									code="label.product" /></b><span style="color: red">*</span></td>
						<td><form:input path="product" readonly="true" id="product" />
						</td>
					</tr>
					<form:hidden path="id" />
					<tr>
						<td class="heading_text"><b><spring:message
									code="label.description" /></b><span style="color: red">*</span></td>
						<td><form:input path="description" readonly="true"
								id="description" /></td>
					</tr>
					<tr>
						<td class="heading_text"><b><spring:message
									code="label.license" /></b><span style="color: red">*</span></td>
						<td><form:input path="licence" readonly="true" id="licence" />
						</td>
					</tr>
					<tr>
						<td class="heading_text"><b><spring:message
									code="label.weight" /></b><span style="color: red">*</span></td>
						<td><form:input path="weight" readonly="true" id="weight" /></td>
					</tr>
					<tr>
						<td class="heading_text"><b><spring:message
									code="label.quantity" /></b><span style="color: red">*</span></td>
						<td><form:input path="quantity" readonly="true" id="quantity" />
						</td>
					</tr>

				</table>
			</div>
		</div>
		<div class="col-sm-6 col-md-6">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center">
					<spring:message code="label.costInfo" />
				</h3>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
				<table>
					<tr>
						<td class="heading_text"><b><spring:message
									code="label.buyingCost" /></b><span style="color: red">*</span></td>
						<td><form:input path="buyingCost" readonly="true"
								id="buyingCost" /></td>
								<c:choose>
							<c:when test="${tMasterPlanForm.currencySymbol == 'dollar'}">
							<td><span class="rupee"><img src="<%=request.getContextPath()%>/resources/images/dollar.png"></span></td>														
							</c:when>
							<c:when test="${tMasterPlanForm.currencySymbol == 'euro'}">
							<td><span class="rupee"><img src="<%=request.getContextPath()%>/resources/images/euro.png"></span></td>														
							</c:when>
							<c:otherwise>
							<td><span class="rupee"><img src="<%=request.getContextPath()%>/resources/images/rupee.png"></span></td>							
							</c:otherwise>
							</c:choose>
					</tr>

					<tr>
						<td class="heading_text"><b><spring:message
									code="label.tenure" /></b><span style="color: red">*</span></td>
						<td><form:input path="tenure" readonly="true" id="tenure" />
						</td>
					</tr>


					<form:hidden path="masterKey" id="masterKey" value="" />
					<form:hidden path="transactionId" id="transactionId" value="" />
					<form:hidden path="customer" id="customer" />
					<form:hidden path="currencySymbol" id="currencySymbol" />

				</table>
			</div>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center">
				<spring:message code="label.brekDownCharge" />
			</h3>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
			<table class="host-list" id="dataTable">

				<tr class="chargescolor">

					<th><spring:message code="label.supplierName" /></th>
					<th><spring:message code="label.materials" /></th>
					<th><spring:message code="label.country" /></th>
					<th><spring:message code="label.quantity" /></th>
					<th><spring:message code="label.cost" /></th>
				</tr>
				<tr>
					<td>
						<div id="supplierNameError" class="error"
							style="display: none; color: red;">
							<spring:message code="label.multipleRowVal" />
						</div> <input type="text" name="supplierName" id="supplierName">

					</td>

					<td>
						<div id="materialsError" class="error"
							style="display: none; color: red;">
							<spring:message code="label.multipleRowVal" />
						</div> <input type="text" name="material" id="material">
					</td>

					<td>
						<div id="countryError" class="error"
							style="display: none; color: red;">
							<spring:message code="label.multipleRowVal" />
						</div> <select id="country" name="country"></select> <script>
							populateCountries("country");
						</script>
					</td>

					<td>
						<div id="quantityStrError" class="error"
							style="display: none; color: red;">
							<spring:message code="label.multipleRowVal" />
						</div> <input type="text" name="quantityStr" id="quantityStr"
						style="width: 111px;">
					</td>
					<td>
						<div id="costStrError" class="error"
							style="display: none; color: red;">
							<spring:message code="label.multipleRowVal" />
						</div> <input type="text" name="costStr" id="costStr"
						style="width: 111px;">
					</td>
				</tr>
			</table>
		</div>
<div class="col-sm-12 col-md-12 col-lg-12">
			<a class="btn btn-default"  onclick="addRow('dataTable')" 
			 style="color:#2E9AFE;font-weight: 600;"><spring:message code="label.addNewSupp"/></a>
			<a  class="btn btn-default"  onclick="deleteRow('dataTable')"
			 style="color:#2E9AFE;font-weight: 600;"><spring:message code="label.removSupp"/></a>
		
		</div>	

		<div class="col-sm-12 col-md-12 col-lg-12">
			<table align="center">
				<tr>
					<td class="col-sm-8"><input type="submit"
						value="<spring:message code="label.save"/>"
						class="btn btn-primary"></td>
					<td><a href="masterPlan" class="btn btn-success"><spring:message
								code="label.back" /></a></td>
				</tr>
			</table>
		</div>
	</form:form>
	<form:form action="tdraftsMasterPlan" method="post"
		commandName="tMasterPlanForm">


		<form:hidden path="category" id="customer" />
		<form:hidden path="product" id="product" />
		<form:hidden path="description" id="description" />
		<form:hidden path="licence" id="licence" />
		<form:hidden path="weight" id="weight" />
		<form:hidden path="quantity" id="quantity" />
		<form:hidden path="buyingCost" id="buyingCost" />
		<form:hidden path="tenure" id="tenure" />
		<form:hidden path="workingCapital" id="workingCapital" />
		<form:hidden path="WcTenure" id="WcTenure" />
		<form:hidden path="sellingCost" id="sellingCost" />
		<form:hidden path="sellingTenure" id="sellingTenure" />
		<form:hidden path="customer" id="customer" />
		<form:hidden path="currencySymbol" id="currencySymbol" />
		<div class="col-sm-12 col-md-12 col-lg-12" style="margin-top: 28px;">
			<table align="center">
				<tr>
					<td><input type="submit" class="btn btn-primary"
						value="<spring:message code="label.saveToDrafts"/>"
						style="background: lightcoral;"></td>
				</tr>
			</table>
		</div>

	</form:form>

</div>
<style>
	.rupee img{
		    height: 20px;
    width: 20px;
    margin-top: -8px;
	}
</style>
