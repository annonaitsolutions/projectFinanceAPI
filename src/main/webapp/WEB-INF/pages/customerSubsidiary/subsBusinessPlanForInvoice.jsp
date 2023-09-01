<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
	function val() {

		var buyerName = document.getElementById('goodsCategory');
		var companyName = document.getElementById("goods");
		var bank = document.getElementById('goodsDetails');
		var bankEmail = document.getElementById("quantity");
		var branch = document.getElementById('weight');
		var address = document.getElementById("licence");
		var country = document.getElementById('amount');
		var country = document.getElementById('poKey');
		var country = document.getElementById('poDate');
		var country = document.getElementById('poInfo');
		var canSubmit = true;

		if (document.getElementById('goodsCategory').value == '') {
			document.getElementById('goodsCategoryError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('goodsCategoryError').style.display = 'none';
		}

		if (document.getElementById('goods').value == '') {
			document.getElementById('goodsError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('goodsError').style.display = 'none';
		}
		if (document.getElementById('goodsDetails').value == '') {
			document.getElementById('goodsDetailsError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('goodsDetailsError').style.display = 'none';
		}

		if (document.getElementById('quantity').value == '') {
			document.getElementById('quantityError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('quantityError').style.display = 'none';
		}
		if (document.getElementById('weight').value == '') {
			document.getElementById('weightError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('weightError').style.display = 'none';
		}

		if (document.getElementById('licence').value == '') {
			document.getElementById('licenceError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('licenceError').style.display = 'none';
		}
		if (document.getElementById('amount').value == '') {
			document.getElementById('amountError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('amountError').style.display = 'none';
		}
		if (document.getElementById('poKey').value == '') {
			document.getElementById('poKeyError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('poKeyError').style.display = 'none';
		}
		if (document.getElementById('poInfo').value == '') {
			document.getElementById('poInfoError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('poInfoError').style.display = 'none';
		}
		if (document.getElementById('poDate').value == '') {
			document.getElementById('poDateError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('poDateError').style.display = 'none';
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



			<form:form action="subsBusinessPlanForInvoiceConfirm" method="post"
				commandName="invoiceForm" id="newBuyer" onsubmit="return val();">



				<br>
				<h3>Buyer Details</h3>
				<div class="col-sm-6">
					<table align="center">
           <form:hidden path="customerHeadName" value="${model.fundsDistributeForm.customerHeadName}"/>
         <form:hidden path="customerName" value="${model.fundsDistributeForm.customerName}"/>
         <form:hidden path="masterKey" value="${model.fundsDistributeForm.masterKey}"/>
         <form:hidden path="customerHeadEmail" value="${model.fundsDistributeForm.custHeadEmail}"/>
         <form:hidden path="customerBranchEmail" value="${model.fundsDistributeForm.email}"/>

						 <tr>
					<td class="heading_text"><b>Buyer Name</b><span style="color:red">*</span></td>
					<td>
					<form:input path="buyerName" value="${model.newBuyerForm.buyerName}" id="name" readonly="true"/>
					</td>
				</tr>
				<form:hidden path="id" value="${model.newBuyerForm.id}" />
				 <tr>
					<td class="heading_text"><b>Buyer Email</b><span style="color:red">*</span></td>
					<td>
					<form:input path="buyerEmail" value="${model.newBuyerForm.email}" id="name" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td class="heading_text"><b>Buyer Bank</b><span style="color:red">*</span></td>
					<td>
					<form:input path="buyerBank" value="${model.newBuyerForm.bank}" id="name" readonly="true"/>
					</td>
				</tr>
                  <tr>
					<td class="heading_text"><b>Buyer Bank Email</b><span style="color:red">*</span></td>
					<td>
					<form:input path="buyerBankEmail" value="${model.newBuyerForm.bankEmail}" id="name" readonly="true"/>
					</td>
				</tr>
                    <tr>
					<td class="heading_text"><b>Buyer Country</b><span style="color:red">*</span></td>
					<td>
					<form:input path="buyerCountry" value="${model.newBuyerForm.country}" id="name" readonly="true"/>
					</td>
				</tr>
				 <tr>
					<td class="heading_text"><b>Buyer State</b><span style="color:red">*</span></td>
					<td>
					<form:input path="buyerState" value="${model.newBuyerForm.state}" id="name" readonly="true"/>
					</td>
				</tr>
						<tr>
					<td class="heading_text"><b>Buyer City</b><span style="color:red">*</span></td>
					<td>
					<form:input path="buyerCity" value="${model.newBuyerForm.city}" id="name" readonly="true"/>
					</td>
				</tr>
					</table>
					
					<h3>Existing Po Info</h3>
					
					<table align="center">
					<div class="col-sm-6">
						<tr>
							<td class="heading_text"><b>Po Key</b><span
								style="color: red">*</span></td>
							<td><form:input path="poKey" id="poKey" />
								<div id="poKeyError" style="display: none; color: red;">Po Key
									is Mandatory</div>
								<div id="poKeyError" style="display: none; color: red;">Po Key
									is Mandatory</div></td>
						</tr>
						
						<tr>
							<td class="heading_text"><b>Po Date</b><span
								style="color: red">*</span></td>
							<td><form:input path="poDate" id="poDate" />
								<div id="poDateError" style="display: none; color: red;">Po Date
									is Mandatory</div>
								<div id="poDateError" style="display: none; color: red;">Po Date
									is Mandatory</div></td>
						</tr>
						<tr>
							<td class="heading_text"><b>Po Info</b><span style="color: red">*</span></td>
							<td><form:input path="poInfo" id="poInfo" />
								<div id="poInfoError" style="display: none; color: red;">Info
									is Mandatory</div>
								<div id="poInfoError" style="display: none; color: red;">Info
									is Mandatory</div></td>
						</tr>
						</div>
						</table>
						</div>
					
					
					
					
					
					<h3>Product Info</h3>
					
					<table align="center">
					<div class="col-sm-6">
						<tr>
							<td class="heading_text"><b>Goods Category</b><span
								style="color: red">*</span></td>
							<td><form:select path="goodsCategory" id="goodsCategory" style="width:86.9%">
											    <form:option value="">Select A Value</form:option> 
											    <form:option value="automobile">Automobile</form:option>
												<form:option value="machinery">Machinery</form:option>
												</form:select>
												<div id="goodsCategoryError" style="display: none; color: red;">Goods Category
									is Mandatory</div>
								<div id="goodsCategoryError" style="display: none; color: red;">Goods Category
									is Mandatory</div></td>
						</tr>
						
						<tr>
							<td class="heading_text"><b>Goods</b><span
								style="color: red">*</span></td>
							<td><form:input path="goods" id="goods" />
								<div id="goodsError" style="display: none; color: red;">Goods
									is Mandatory</div>
								<div id="goodsError" style="display: none; color: red;">Goods
									is Mandatory</div></td>
						</tr>
						<tr>
							<td class="heading_text"><b>Details</b><span style="color: red">*</span></td>
							<td><form:input path="goodsDetails" id="goodsDetails" />
								<div id="goodsDetailsError" style="display: none; color: red;">Details
									is Mandatory</div>
								<div id="goodsDetailsError" style="display: none; color: red;">Details
									is Mandatory</div></td>
						</tr>
						<tr>
							<td class="heading_text"><b>Quantity</b><span
								style="color: red">*</span></td>
							<td><form:input path="quantity" id="quantity" />
								<div id="quantityError" style="display: none; color: red;">Quantity
									Number is Mandatory</div>
								<div id="quantityError" style="display: none; color: red;">Quantity
									Number is Mandatory</div></td>
						</tr>
						<tr>
							<td class="heading_text"><b>Weight</b><span
								style="color: red">*</span></td>
							<td><form:input path="weight" id="weight" />
								<div id="weightError" style="display: none; color: red;">Weight is Mandatory</div>
								<div id="weightError" style="display: none; color: red;">Weight is Mandatory</div></td>
						</tr>
						<tr>
							<td class="heading_text"><b>Licence</b><span
								style="color: red">*</span></td>
								<td><form:select path="licence" id="licence" style="width:86.9%">
											    <form:option value="">Select A Value</form:option> 
											    <form:option value="ogl">OGL</form:option>
												<form:option value="restricted">Restricted</form:option>
												</form:select>
												<div id="licenceError" style="display: none; color: red;">Licence
									is Mandatory</div>
								<div id="licenceError" style="display: none; color: red;">Licence
									is Mandatory</div></td>
						</tr>
					
						<tr>
							<td class="heading_text"><b>Amount</b><span
								style="color: red">*</span></td>
							<td><form:input path="amount" id="amount" />
								<div id="amountError" style="display: none; color: red;">Amount is Mandatory</div>
								<div id="amountError" style="display: none; color: red;">Amount
									is Mandatory</div></td>
						</tr>
						
						</div>
					

				</table>
					</div>
					<table>
		
							<tr>
								<td><input type="submit" class="btn btn-primary" value="Continue"></td>
								<td><a href="user" class="btn btn-success" name="back"  onclick="javascript:window.location='#';"/>back</a></td>
							</tr>
							
					</table>
					</form:form>