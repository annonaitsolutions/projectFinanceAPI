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
		var country = document.getElementById('purchaseDate');
		var country = document.getElementById('tenure');
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
		if (document.getElementById('Amount').value == '') {
			document.getElementById('amountError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('amountError').style.display = 'none';
		}
		if (document.getElementById('purchaseDate').value == '') {
			document.getElementById('purchaseDateError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('purchaseDateError').style.display = 'none';
		}
		 if (document.getElementById('tenure').value == '') {
			document.getElementById('tenureError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('tenureError').style.display = 'none';
		} 

		

		if (canSubmit == false) {
			return false;
		}
	}
</script>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
	 
		<div class="successMsg" style="text-align: center; color: green; font-size: 18px;">${success}</div>
		
<form:form action="subsBusinessPlanForPoConfirm" method="post"
				commandName="purchaseOrderForm" id="newBuyer" onsubmit="return val();">

			
		 <div class="col-sm-12 col-md-6 col-lg-6">
		 <div class="col-sm-12 col-md-12 col-lg-12 header_customer">
	 	 	<h3 align="center">SupplierDetails</h3>
		 </div>
					<table align="center">

         <form:hidden path="customerName" value="${model.fundsDistributeForm.customerName}"/>
         <form:hidden path="customerHeadName" value="${model.fundsDistributeForm.customerHeadName}"/>
         <form:hidden path="masterKey" value="${model.fundsDistributeForm.masterKey}"/>
         <form:hidden path="utilizedBusnsAmt" value="${model.fundsDistributeForm.utilizedAmount}"/>
         <form:hidden path="customerHeadEmail" value="${model.fundsDistributeForm.custHeadEmail}"/>
         <form:hidden path="customerBranchEmail" value="${model.fundsDistributeForm.email}"/>
						 <tr>
					<td><b>Supplier Name<span style="color: red">&nbsp;*</span>&nbsp;:</b></td>
					<td>
					<form:input path="supplierName" value="${model.supplierForm.supplierName}" id="name" readonly="true"/>
					</td>
				</tr>
				<form:hidden path="id" value="${model.supplierForm.id}" />
				 <tr>
					<td><b>Supplier Email<span style="color: red">&nbsp;*</span>&nbsp;:</b></td>
					<td>
					<form:input path="supplierEmail" value="${model.supplierForm.email}" id="name" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td><b>Supplier Bank<span style="color: red">&nbsp;*</span>&nbsp;:</b></td>
					<td>
					<form:input path="supplierBank" value="${model.supplierForm.bank}" id="name" readonly="true"/>
					</td>
				</tr>
                  <tr>
					<td><b>Supplier Bank Email<span style="color: red">&nbsp;*</span>&nbsp;:</b></td>
					<td>
					<form:input path="supplierBankEmail" value="${model.supplierForm.bankEmail}" id="name" readonly="true"/>
					</td>
				</tr>
                    <tr>
					<td><b>Supplier Country<span style="color: red">&nbsp;*</span>&nbsp;:</b></td>
					<td>
					<form:input path="Suppliercountry" value="${model.supplierForm.country}" id="name" readonly="true"/>
					</td>
				</tr>
				 <tr>
					<td><b>Supplier State<span style="color: red">&nbsp;*</span>&nbsp;:</b></td>
					<td>
					<form:input path="supplierState" value="${model.supplierForm.state}" id="name" readonly="true"/>
					</td>
				</tr>
						<tr>
					<td><b>Supplier City<span style="color: red">&nbsp;*</span>&nbsp;:</b></td>
					<td>
					<form:input path="supplierCity" value="${model.supplierForm.city}" id="name" readonly="true"/>
					</td>
				</tr>
					</table>
			</div>
			<div class="col-sm-12 col-md-6 col-lg-6">
					<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
						<h3 align="center">Product Info</h3>
					</div>
					
					
					<table align="center">
						<tr>
							<td><b>Goods Category<span style="color: red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:select path="goodsCategory" id="goodsCategory">
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
							<td><b>Purchase Date<span style="color: red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="purchaseDate" id="purchaseDate" />
								<div id="purchaseDateError" style="display: none; color: red;">Date
									is Mandatory</div>
								<div id="purchaseDateError" style="display: none; color: red;">Date
									is Mandatory</div></td>
						</tr>
						<tr>
							<td><b>Goods<span style="color: red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="goods" id="goods" />
								<div id="goodsError" style="display: none; color: red;">Goods
									is Mandatory</div>
								<div id="goodsError" style="display: none; color: red;">Goods
									is Mandatory</div></td>
						</tr>
						<tr>
							<td><b>Details<span style="color: red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="goodsDetails" id="goodsDetails" />
								<div id="goodsDetailsError" style="display: none; color: red;">Details
									is Mandatory</div>
								<div id="goodsDetailsError" style="display: none; color: red;">Details
									is Mandatory</div></td>
						</tr>
						<tr>
							<td><b>Quantity<span style="color: red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="quantity" id="quantity" />
								<div id="quantityError" style="display: none; color: red;">Quantity
									Number is Mandatory</div>
								<div id="quantityError" style="display: none; color: red;">Quantity
									Number is Mandatory</div></td>
						</tr>
						<tr>
							<td><b>Weight<span style="color: red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="weight" id="weight" />
								<div id="weightError" style="display: none; color: red;">Weight is Mandatory</div>
								<div id="weightError" style="display: none; color: red;">Weight is Mandatory</div></td>
						</tr>
						<tr>
							<td><b>Licence<span style="color: red">&nbsp;*</span>&nbsp;:</b></td>
								<td><form:select path="licence" id="licence">
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
							<td class="heading_text"><b>Available Balance<span style="color: red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="balance" value="${model.fundsDistributeForm.busBalance}"  readonly="true" id="amount" />
								</td>
						</tr>
						<tr>
							<td class="heading_text"><b>Amount<span style="color: red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="amount" id="Amount" />
								<div id="amountError" style="display: none; color: red;">Amount is Mandatory</div>
								<div id="amountError" style="display: none; color: red;">Amount
									is Mandatory</div></td>
						</tr>
						
						<tr>
							<td class="heading_text"><b>Tenure<span style="color: red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="tenure" id="tenure" />
								<div id="tenureError" style="display: none; color: red;">Tenure is Mandatory</div>
								<div id="tenureError" style="display: none; color: red;">Tenure
									is Mandatory</div></td>
						</tr>
						
						
				
				</table>
					</div>
					<div class="col-sm-12 col-md-12 col-lg-12">	
					
					<table align="center">
		
							<tr>
								<td><input type="submit" class="btn btn-primary" value="Continue"></td>
								<td><a href="user" class="btn btn-success" name="back"  onclick="javascript:window.location='#';">back</a></td>
							</tr>
							
					</table>
					</div>
					</form:form>
</div>