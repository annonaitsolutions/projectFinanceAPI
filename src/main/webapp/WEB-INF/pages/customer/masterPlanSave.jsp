
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>--%>
<%--<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>--%>
<%--<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- To fetch the request url -->
<c:set var="req" value="${requestScope['javax.servlet.forward.request_uri']}" />
<c:set var="baseURL" value="${fn:split(req,'/')}" />

<script type="text/javascript">
	$(document).ready(function() {
		document.getElementById("country").value = "India";

	});
	function checkRegulations(country) {
		debugger;
		var category = $("#category").val();
		var url = "checkRegulations?country=" + country + "&category="
				+ category;
		var xmlhttp;

		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();
		} else {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				if (xmlhttp.responseText != "") {
					alert(xmlhttp.responseText);
					if (xmlhttp.responseText.indexOf('Banned') != -1) {
						var table = document.getElementById('dataTable');
						var rowCount = table.rows.length;
						if (rowCount > 2) {
							table.deleteRow(rowCount - 1);
						} else {
							document.getElementById("supplierName").value = "";
							document.getElementById("material").value = "";
							document.getElementById("country").value = -1;
							document.getElementById("quantityStr").value = "";
							document.getElementById("costStr").value = "";
						}
					}
				}
			}
		}

		xmlhttp.open("POST", url, true);
		xmlhttp.send();
	}

	function perUnitCost(id) {
		debugger;
		console.log("AAAA");

		//this is for fetching number from string
		let matches = id.match(/(\d+)/);
        var number = matches[0];
		var a = document.getElementById("amtStr"+number).value;
		var q = document.getElementById("qtyStr"+number).value;

		var amt = a/q;
		document.getElementById("costperUnit"+number).value = amt.toFixed(2);
	}



	function validateForm() {
		debugger;
		var suppliers = document.getElementsByName("supplierName");
		var currencySymbol = document.getElementById("currencySymbol");
		var materials = document.getElementsByName("material");
		var countries = document.getElementsByName("country");
		var quantities = document.getElementsByName("quantityStr");
		var costs = document.getElementsByName("costStr");

		var buyers = document.getElementsByName("buyerName");
		var finalProducts = document.getElementsByName("finalPro");
		var qtyStrs = document.getElementsByName("qtyStr");
		var amtStrs = document.getElementsByName("amtStr");
		var costperUnit = document.getElementsByName("costperUnitStr");
		/* var costperUnitStr = document.getElementsByName("costperUnitStr"); */

		var reasons = document.getElementsByName("wcReason");
		var wcAmts = document.getElementById("wcAmt");
     	/* var wcAmount = document.getElementById('wcAmt').value;  */

		var canSubmit = true;

		var table = document.getElementById("dataTable");
		var totalCost = 0.0;

		var buyingCost = document.getElementById('buyingCost').value;
		var workingCapital = document.getElementById('workingCapital').value;
		var sellingCost = document.getElementById('sellingCost').value;
		for (var i = 1; i < table.rows.length; i++) {

			totalCost = +totalCost + +table.rows[i].cells[4].children[1].value;
		}
debugger ; 
		var totalAmtStrs=0;
		for (var i = 0; i < amtStrs.length; i++) {
			totalAmtStrs=totalAmtStrs + parseInt(amtStrs[i].value);			
		}
		

		var tableSelling = document.getElementById("dataTable1");
		var quantity = 0.0;

		// var buyingCost = document.getElementById('buyingCost').value;
		
		var actualQuantity =document.getElementById('quantity').value;
		if(sellingCost!=null && sellingCost!="" && sellingCost!=' '){
			for (var i = 1; i < tableSelling.rows.length; i++) {

				quantity = +quantity + +tableSelling.rows[i].cells[2].children[1].value;
			}
			if(quantity>actualQuantity){

				document.getElementById('quantityNotMatchingErrorDiv').style.display = 'block';
				canSubmit = false;
			}
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

			if (buyers[i].value == '') {
				document.getElementById('buyerNameError').style.display = 'block';
				canSubmit = false;
			} else {
				document.getElementById('buyerNameError').style.display = 'none';
			}

			if (finalProducts[i].value == '') {
				document.getElementById('finalProError').style.display = 'block';
				canSubmit = false;
			} else {
				document.getElementById('finalProError').style.display = 'none';
			}

			if (qtyStrs[i].value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/)
					|| qtyStrs[i].value.match(/^-?[0-9]+$/)
					&& !(qtyStrs[i].value == '')) {

				document.getElementById('qtyStrError').style.display = 'none';

			} else {
				document.getElementById('qtyStrError').style.display = 'block';
				canSubmit = false;
			}

			if (amtStrs[i].value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/)
					|| amtStrs[i].value.match(/^-?[0-9]+$/)
					&& !(amtStrs[i].value == '')) {

				document.getElementById('amtStrError').style.display = 'none';

			} else {
				document.getElementById('amtStrError').style.display = 'block';
				canSubmit = false;
			}

			if (costperUnit[i].value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/)
					|| costperUnit[i].value.match(/^-?[0-9]+$/)
					&& !(costperUnit[i].value == '')) {

				document.getElementById('costperUnitError').style.display = 'none';

			} else {
				document.getElementById('costperUnitError').style.display = 'block';
				canSubmit = false;
			}

		}
		if(workingCapital!=null && workingCapital!="" && workingCapital!=' '){

			if (wcAmts.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/)
					|| wcAmts.value.match(/^-?[0-9]+$/)
					|| (wcAmts.value == '')) {

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
		
		
		if(sellingCost!=null && sellingCost!="" && sellingCost!=' '){
			if (parseInt(sellingCost) == totalAmtStrs) {
				document.getElementById('costMismatchError').style.display = 'none';
			} else {
				document.getElementById('costMismatchError').style.display = 'block';
				canSubmit = false;
			     }
			}
		
		
		if(workingCapital!=null && workingCapital!="" && workingCapital!=' '){
		if (parseInt(workingCapital) == parseInt(wcAmts.value)) {
			document.getElementById('wcAmtError').style.display = 'none';
		} else {
			document.getElementById('wcAmtError').style.display = 'block';
			canSubmit = false;
		     }
		}

		if (canSubmit == false) {
			return false;
		}
	}

	function addRow1(tableID1) {
		debugger;
		var table = document.getElementById(tableID1);

		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount);

		var colCount = table.rows[1].cells.length;

		for (var i = 0; i < colCount; i++) {
			var newcell = row.insertCell(i);

			newcell.innerHTML = table.rows[1].cells[i].innerHTML;
			if(newcell.childNodes[3].name=='qtyStr'){
				newcell.childNodes[3].id='qtyStr'+rowCount;
			}
			if(newcell.childNodes[3].name=='amtStr'){
				newcell.childNodes[3].id='amtStr'+rowCount;
			}
			if(newcell.childNodes[3].name=='costperUnitStr'){
				newcell.childNodes[3].id='costperUnit'+rowCount;
			}
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
		var selects = document.getElementsByName('country');

		for (var i = (selects.length-1); i < selects.length; i++) {
			var select = selects[i];
			select.value = 'India';
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

	// var y = document.msplan.customerPrefix.value;
	// var d = new Date().getTime();
	// var uuid = y + '-MP-xxxxxx'.replace(/[xy]/g, function(c) {
	// 	var r = (d + Math.random() * 16) % 16 | 0;
	// 	d = Math.floor(d / 16);
	// 	return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
	// });
	// document.msplan.masterkey.value = uuid;
	//
	// var d1 = new Date().getTime();
	// var uuid1 = 'xxxxxx'.replace(/[xy]/g, function(c1) {
	// 	var r1 = (d1 + Math.random() * 16) % 16 | 0;
	// 	d1 = Math.floor(d / 16);
	// 	return (c1 == 'x' ? r1 : (r1 & 0x3 | 0x8)).toString(16);
	// });
	// document.msplan.transactionId.value = uuid1;

	function saveToDrafts() {
		// alert("saved to drafts");
		document.msplan.action = "masterPlanInfoDraft";
		document.getElementById("msplan").submit();
	}
	// return today;
	// }
</script>
<script>
	function getMaterials(option) {
		debugger;
		// alert("oinside getMaterial");
		var matsDB = ${materials};
		// var materials;
		var supplier = option.value;
		//alert(option.value);
		// var today = null;
		var token = $("input[name='_csrf']").val();
		var header = "X-CSRF-TOKEN";
		if (supplier!='') {
			// const mats=new Array("Glass", "PLastic", "Wires", "Screen", "Sensors");
			var mats=matsDB[option.value];
			var materials = document.getElementById("materials");
			for( mat of mats) {
				var option = document.createElement("option");
				option.text = mat;
				option.value = mat; // Assign a value to the new option if needed
				materials.appendChild(option);
			}
		} else {
			var datalist = document.getElementById("materials");
			datalist.innerHTML = "";
		}
	}
</script>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
	<form:form action="masterPlanInfoPost" name="msplan"
			   onsubmit="return validateForm()" method="post"
			   commandName="masterPlanForm" id="msplan">
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
					<form:hidden path="customerPrefix" />

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
			<div id="WorkingCapitalMissmatchingError" class="error"
							  style="text-align: center; display: none; color: red;">
							
						</div>
				<table>
					<tr>
						<td class="heading_text"><b><spring:message
								code="label.buyingCost" /></b><span style="color: red">*</span></td>
						<td><form:input path="buyingCost" readonly="true"
										id="buyingCost" /></td>
						<c:choose>
							<c:when test="${masterPlanForm.currencySymbol == 'dollar'}">
								<td><span class="rupee"><img
										src="<%=request.getContextPath()%>/resources/images/dollar.png"></span></td>
							</c:when>
							<c:when test="${masterPlanForm.currencySymbol == 'euro'}">
								<td><span class="rupee"><img
										src="<%=request.getContextPath()%>/resources/images/euro.png"></span></td>
							</c:when>
							<c:otherwise>
								<td><span class="rupee"><img
										src="<%=request.getContextPath()%>/resources/images/rupee.png"></span></td>
							</c:otherwise>
						</c:choose>
						<form:hidden path="currencySymbol" />

					</tr>

						<%--					<tr>--%>
						<%--						<td class="heading_text"><b><spring:message--%>
						<%--									code="label.tenure" /></b><span style="color: red">*</span></td>--%>
						<%--						<td><form:input path="tenure" readonly="true" id="tenure" />--%>
						<%--						</td>--%>
						<%--					</tr>--%>

					<tr>
						<td class="heading_text"><b><spring:message
								code="label.workingCapital" /></b></td>
						<td><form:input path="workingCapital" readonly="true"
										id="workingCapital" /></td>

						<c:choose>
							<c:when test="${masterPlanForm.currencySymbol == 'dollar'}">
								<td><span class="rupee"><img
										src="<%=request.getContextPath()%>/resources/images/dollar.png"></span></td>
							</c:when>
							<c:when test="${masterPlanForm.currencySymbol == 'euro'}">
								<td><span class="rupee"><img
										src="<%=request.getContextPath()%>/resources/images/euro.png"></span></td>
							</c:when>
							<c:otherwise>
								<td><span class="rupee"><img
										src="<%=request.getContextPath()%>/resources/images/rupee.png"></span></td>
							</c:otherwise>
						</c:choose>

					</tr>
						<%--					<tr>--%>
						<%--						<td class="heading_text"><b><spring:message--%>
						<%--									code="label.tenure" /></b></td>--%>
						<%--						<td><form:input path="WcTenure" readonly="true" id="WcTenure" /></td>--%>
						<%--					</tr>--%>
					<tr>
						<td class="heading_text"><b><spring:message
								code="label.sellingCost" /></b></td>
						<td><form:input path="sellingCost" readonly="true"
										id="sellingCost" /></td>

						<c:choose>
							<c:when test="${masterPlanForm.currencySymbol == 'dollar'}">
								<td><span class="rupee"><img
										src="<%=request.getContextPath()%>/resources/images/dollar.png"></span></td>
							</c:when>
							<c:when test="${masterPlanForm.currencySymbol == 'euro'}">
								<td><span class="rupee"><img
										src="<%=request.getContextPath()%>/resources/images/euro.png"></span></td>
							</c:when>
							<c:otherwise>
								<td><span class="rupee"><img
										src="<%=request.getContextPath()%>/resources/images/rupee.png"></span></td>
							</c:otherwise>
						</c:choose>


					</tr>
						<%--					<tr>--%>
						<%--						<td class="heading_text"><b><spring:message--%>
						<%--									code="label.tenure" /></b></td>--%>
						<%--						<td><form:input path="sellingTenure" readonly="true"--%>
						<%--								id="sellingTenure" /></td>--%>
						<%--					</tr>--%>
					<form:hidden path="masterKey" id="masterKey" value="" />
					<form:hidden path="transactionId" id="transactionId" value="" />
					<form:hidden path="customer" id="customer" />
					<%-- <form:hidden path="currencySymbol" id="currencySymbol" /> --%>

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
						</div>
						<form:select path="supplierName" id="supplierName"
									 style="width:205px;" class="csrfLink" onChange="getMaterials(this)">
							<form:option value="">
								<spring:message code="label.selectValue" />
							</form:option>
							<c:forEach var="item" items="${supplierList}">
								<form:option value="${item.supplierName}">${item.supplierName}</form:option>
							</c:forEach>
						</form:select>


					</td>

					<td>
						<div id="materialsError" class="error"
							 style="display: none; color: red;">
							<spring:message code="label.multipleRowVal" />
						</div> <input type="text" list="materials" name="material" id="material">
							<%--						<input type="text" name="material" id="material">--%>
							<%--						<label>Choose a browser from this list:--%>
							<%--							<input list="browsers" name="myBrowser" /></label>--%>
						<datalist id="materials">
								<%--							<option value="Glass">--%>
								<%--							<option value="Iron">--%>
								<%--							<option value="Plastic">--%>
								<%--							<option value="Opera">--%>
								<%--							<option value="Safari">--%>
								<%--							<option value="Microsoft Edge">--%>
						</datalist>

					</td>

					<td>
						<div id="countryError" class="error"
							 style="display: none; color: red;">
							<spring:message code="label.multipleRowVal" />
						</div> <select id="country" name="country"
									   onchange="checkRegulations(this.value)"></select> <script>
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
			<a class="btn btn-default" onclick="addRow('dataTable')"
			   style="color: #2E9AFE; font-weight: 600;"><spring:message
					code="label.addNewSupp" /></a> <a class="btn btn-default"
													  onclick="deleteRow('dataTable')"
													  style="color: #2E9AFE; font-weight: 600;"><spring:message
				code="label.removSupp" /></a>

		</div>
		<c:if test="${! empty masterPlanForm.sellingCost}">
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center">
				<spring:message code="label.breakdownOfChargesForBuying" />
			</h3>
			<div id="quantityNotMatchingErrorDiv" class="error"
				 style="display: none; color: red;">
				<spring:message code="label.quantityNotMatching" />
			</div>
			
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
			<table class="host-list" id="dataTable1">

				<tr class="chargescolor">

					<th><spring:message code="label.buyerName" /></th>
					<th><spring:message code="label.finalProduct" /></th>
					<th><spring:message code="label.quantity" /></th>
					<th><spring:message code="label.amount" /></th>
					<th><spring:message code="label.costperunit" /></th>

				</tr>
				<tr>
					<td>
						<div id="buyerNameError" class="error"
							 style="display: none; color: red;">
							<spring:message code="label.multipleRowVal" />
						</div> <form:select path="buyerName" id="buyerName" style="width:205px;">
						<form:option value="">
							<spring:message code="label.selectValue" />
						</form:option>
						<c:forEach var="item" items="${buyerList}">
							<form:option value="${item.buyerName}">${item.buyerName}</form:option>
						</c:forEach>
					</form:select>


					</td>

					<td>
						<div id="finalProError" class="error"
							 style="display: none; color: red;">
							<spring:message code="label.multipleRowVal" />
						</div> <input type="text" value="${masterPlanForm.finalPro}"
									  name="finalPro" id="finalPro">
					</td>

					<td>
						<div id="qtyStrError" class="error"
							 style="display: none; color: red;">
							<spring:message code="label.multipleRowVal" />
						</div> <input type="text" name="qtyStr" id="qtyStr1" onchange="perUnitCost(this.id)"
									  style="width: 111px;">
					</td>
					<td>
						<div id="amtStrError" class="error"
							 style="display: none; color: red;">
							<spring:message code="label.multipleRowVal" />
						</div> <input type="text" name="amtStr" id="amtStr1" onchange="perUnitCost(this.id)"
									  style="width: 111px;">
					</td>
					<td>
						<div id="costperUnitError" class="error"
							 style="display: none; color: red;">
							<spring:message code="label.multipleRowVal" />
						</div> <input type="text"  name="costperUnitStr" id="costperUnit1" value="0"
									  style="width: 111px;">
					</td>
				</tr>
			</table>
		</div>


		<div class="col-sm-12 col-md-12 col-lg-12">
			<a class="btn btn-default" onclick="addRow1('dataTable1')"
			   style="color: #2E9AFE; font-weight: 600;"><spring:message
					code="label.addNew" /></a> <a class="btn btn-default"
												  onclick="deleteRow1('dataTable1')"
												  style="color: #2E9AFE; font-weight: 600;"><spring:message
				code="label.remove" /></a>

		</div>
	</c:if>
		<c:if test="${! empty masterPlanForm.workingCapital}">
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center">
				<spring:message code="label.wcBeakdown" />
			</h3>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
			<table class="host-list" id="dataTable2">

				<tr class="chargescolor">

					<th><spring:message code="label.reason" /></th>
					<th><spring:message code="label.amount" /></th>

				</tr>
				<tr>
					<td>
						<div id="wcReasonError" class="error"
							 style="display: none; color: red;">
							<spring:message code="label.validation" />
						</div> <input type="text" name="wcReason" id="wcReason">
					</td>

					<td>
						<div id="wcAmtError" class="error"
							 style="display: none; color: red;">
							<spring:message code="label.validation4" />
						</div> <input type="text" name="wcAmt" id="wcAmt">
					</td>

				</tr>
			</table>
		</div>

		<div class="col-sm-12 col-md-12 col-lg-12">
			<a class="btn btn-default" onclick="addRow2('dataTable2')"
			   style="color: #2E9AFE; font-weight: 600;"><spring:message
					code="label.addNew" /></a> <a class="btn btn-default"
												  onclick="deleteRow2('dataTable2')"
												  style="color: #2E9AFE; font-weight: 600;"><spring:message
				code="label.remove" /></a>

		</div>
		</c:if>
		<div class="col-sm-12 col-md-12 col-lg-12">
			<table align="center">
				<tr>
					<td class="col-sm-8"><input type="submit"
												value="<spring:message code="label.save"/>"
												class="btn btn-primary"></td>
					<td><a href="masterPlan1" class="btn btn-success"><spring:message
							code="label.back" /></a></td>
				</tr>
			</table>
		</div>
	</form:form>
	<%-- <form:form action="draftsMasterPlan" method="post"
		commandName="masterPlanForm">


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
		<form:hidden path="currencySymbol" id="currencySymbol" /> --%>
	<div hidden class="col-sm-12 col-md-12 col-lg-12" style="margin-top: 28px;">
		<table align="center">
			<tr>
				<td><input type="button" class="btn btn-primary"
						   onClick="saveToDrafts()"
						   value="<spring:message code="label.saveToDrafts"/>"
						   style="background: lightcoral;"></td>
			</tr>
		</table>
	</div>

	<%-- 	</form:form> --%>

</div>

<style>
	.rupee img {
		height: 20px;
		width: 20px;
		margin-top: -8px;
	}
</style>