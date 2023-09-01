<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
	function val() {
		var number='^\\d+$';
		var number= /(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/;
		var number1= /^-?[0-9]+$/;
		var buyerName = document.getElementById('goodsCategory');
		var companyName = document.getElementById("goods");
		var bank = document.getElementById('goodsDetails');
		var quantity = document.getElementById("quantity");
		var branch = document.getElementById('weight');
		var address = document.getElementById("licence");
		var amount = document.getElementById('amount');
		var country = document.getElementById('purchaseDate');
		var country = document.getElementById('tenure');
		var country = document.getElementById('country');
		var state = document.getElementById('state');
		var canSubmit = true;
		
		
		
		  if(quantity.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || quantity.value.match(/^-?[0-9]+$/) &&!(document.getElementById('quantity').value == ''))
		  	{
		  		
		  		document.getElementById('quantityError').style.display='none';
		  	
		  	}
		  	else{
		  		document.getElementById('quantityError').style.display='block';
		  	     canSubmit = false;
		  	}

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
		
		
	
		  if(weight.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || weight.value.match(/^-?[0-9]+$/) &&!(document.getElementById('weight').value == ''))
		  	{
		  		
		  		document.getElementById('weightError').style.display='none';
		  	
		  	}
		  	else{
		  		document.getElementById('weightError').style.display='block';
		  	     canSubmit = false;
		  	}

		if (document.getElementById('licence').value == '') {
			document.getElementById('licenceError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('licenceError').style.display = 'none';
		}
		
		if(amount.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || amount.value.match(/^-?[0-9]+$/) &&!(document.getElementById('amount').value == ''))
		  	{
		  		
		  		document.getElementById('amountError').style.display='none';
		  	
		  	}
		  	else{
		  		document.getElementById('amountError').style.display='block';
		  	     canSubmit = false;
		  	}
		
		if (document.getElementById('datepicker').value == '') {
			document.getElementById('date1Error').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('date1Error').style.display = 'none';
		}
		
		 
		 if(tenure.value.match('^\\d+$') &&!(document.getElementById('tenure').value == ''))
		  	{
		  		
		  		document.getElementById('tenureError').style.display='none';
		  	
		  	}
		  	else{
		  		document.getElementById('tenureError').style.display='block';
		  	     canSubmit = false;
		  	}
		 
		 if (document.getElementById('wareHouseName').value == '') {
				document.getElementById('wareHouseNameError').style.display = 'block';
				canSubmit = false;
			} else {
				document.getElementById('wareHouseNameError').style.display = 'none';
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
		

		if (canSubmit == false) {
			return false;
		}
	}
</script>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

		<div class="col-sm-12 col-md-12 col-lg-12">
			<div class="successMsg" style="text-align: center; color: green; font-size: 18px;">${success}</div>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
		<form:form action="businessPlanForPoConfirm" method="post"
				commandName="purchaseOrderForm" id="newBuyer" onsubmit="return val();">



				
				<div class="col-sm-12 col-lg-6 col-md-6">
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 align="center"><spring:message code="label.supplierDetails"/></h3>
				</div>
					<table align="center">
         <form:hidden path="customerPrefix" value="${model.masterPlanForm.customerPrefix}"/>
         <form:hidden path="customerName" value="${model.masterPlanForm.customer}"/>
         <form:hidden path="masterKey" value="${model.masterPlanForm.masterKey}"/>
         <form:hidden path="utilizedBusnsAmt" value="${model.masterPlanForm.utilizedBusnsAmt}"/>
         <form:hidden path="customerHeadEmail" value="${model.masterPlanForm.customerEmail}"/>
						 <tr>
					<td class="heading_text"><b><spring:message code="label.supplierName"/>&nbsp;<span style="color:red">*</span>&nbsp;:</b></td>
					<td>
					<form:input path="supplierName" value="${model.supplierForm.supplierName}" id="name" readonly="true"/>
					</td>
				</tr>
				<form:hidden path="id" value="${model.supplierForm.id}" />
				 <tr>
					<td class="heading_text"><b><spring:message code="label.supplierEmail"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
					<td>
					<form:input path="supplierEmail" value="${model.supplierForm.email}" id="name" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td class="heading_text"><b><spring:message code="label.supplierBank"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
					<td>
					<form:input path="supplierBank" value="${model.supplierForm.bank}" id="name" readonly="true"/>
					</td>
				</tr>
                  <tr>
					<td class="heading_text"><b><spring:message code="label.supplierBankEmail"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
					<td>
					<form:input path="supplierBankEmail" value="${model.supplierForm.email}" id="name" readonly="true"/>
					</td>
				</tr>
                    <tr>
					<td class="heading_text"><b><spring:message code="label.supplierCountry"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
					<td>
					<form:input path="Suppliercountry" value="${model.supplierForm.country}" id="name" readonly="true"/>
					</td>
				</tr>
				 <tr>
					<td class="heading_text"><b><spring:message code="label.supplierState"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
					<td>
					<form:input path="supplierState" value="${model.supplierForm.state}" id="name" readonly="true"/>
					</td>
				</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.supplierCity"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
					<td>
					<form:input path="supplierCity" value="${model.supplierForm.city}" id="name" readonly="true"/>
					</td>
				</tr>
					</table>
				</div>
				<div class="col-sm-12 col-lg-6 col-md-6">
					<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
						<h3 align="center"><spring:message code="label.productInfo"/></h3>
					</div>
					<table align="center">
					
						<tr>
							<td class="heading_text"><b><spring:message code="label.goodsCategory"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="goodsCategory" readonly="true" id="goodsCategory" />
								</td>
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.purchaseDate"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input id="datepicker" path="purchaseDate" readonly="true" placeholder="Enter Purchase Date" />
								<div id="date1Error" style="display: none; color: red;"><spring:message code="label.dateIsMandatory"/></div>
							
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.goods"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="goods" id="goods" readonly="true" placeholder="Enter Goods" />
								<div id="goodsError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="goodsError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>
						<%--<tr>
						<td class="heading_text"><b><spring:message
									code="label.country" /></b><span style="color: red">*</span></td>
						<td><form:select path="country" id="country"  value=""
								style="width: 206px;" />
							<div id="countryError" style="display: none; color: red;">
								<spring:message code="label.plzSelectValue" />
							</div>
					</tr>--%>

					<%--<tr>
						<td class="heading_text"><b><spring:message
									code="label.state" /></b><span style="color: red">*</span></td>
						<td><form:select path="state" id="state" value=""
								style="width: 206px;" /> <script>
									populateCountries("country", "state");
								</script>
							<div id="stateError" style="display: none; color: red;">
								<spring:message code="label.plzSelectValue" />
							</div>
					</tr>--%>
						<tr>
							<td class="heading_text"><b><spring:message code="label.details"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="goodsDetails" id="goodsDetails" placeholder="Enter Details" readonly="true"/>
								<div id="goodsDetailsError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="goodsDetailsError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.quantity"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="quantity" id="quantity" placeholder="Enter Quantity"/>
								<div id="quantityError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="quantityError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>
						<tr>
						<td class="heading_text"><b><spring:message code="label.whName"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
						<td><form:select id="wareHouseName" path="wareHouseName" style="width:205px;">
						<form:option value="">Select</form:option>
								<form:options items="${purchaseOrderForm.wareHouseList}"
									itemValue="wareHouseName" itemLabel="wareHouseName" />
							</form:select>
							<div id="wareHouseNameError" style="display: none; color: red;"><spring:message code="label.validation2"/>
									</div>
								<div id="wareHouseNameError" style="display: none; color: red;"><spring:message code="label.validation2"/>
									</div></td>
							
							
					</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.weight"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="weight" id="weight" placeholder="Enter Weight" readonly="true"/>
								<div id="weightError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="weightError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.license"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
								<td><form:select path="licence" id="licence" style="width:205px;" >
											    <form:option value=""><spring:message code="label.selectValue"/></form:option> 
											    <form:option value="ogl"><spring:message code="label.ogl"/></form:option>
												<form:option value="restricted"><spring:message code="label.restricted"/></form:option>
												</form:select>
												<div id="licenceError" style="display: none; color: red;"><spring:message code="label.validation2"/></div>
								<div id="licenceError" style="display: none; color: red;"><spring:message code="label.validation2"/></div></td>
						</tr>
						
						<tr>
							<td class="heading_text"><b><spring:message code="label.availableBalance"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="balance" value="${model.masterPlanForm.balance}"  readonly="true" id="amount1" />
								</td>
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.amount"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
							<td><form:input path="amount" id="amount" value="${model.masterPlanForm.buyingCostSanc}" placeholder="Enter Amount"/>
								<div id="amountError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="amountError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>
						
<%--						<tr>--%>
<%--							<td class="heading_text"><b><spring:message code="label.tenure"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>--%>
<%--							<td><form:input path="tenure" id="tenure" placeholder="Enter Tenure"/>--%>
<%--								<div id="tenureError" style="display: none; color: red;"><spring:message code="label.validation"/></div>--%>
<%--								<div id="tenureError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>--%>
<%--						</tr>--%>
						
						
					

				</table>
					</div>
					<div class="col-sm-12 col-md-12 col-lg-12">&nbsp;</div>
					<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="salesPurchaseOrder?productType=PurchaseOrder" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
					</form:form>
					</div>
					</div>
	<script>

      $(function() {
	  $( "#datepicker" ).datepicker({format:'dd/mm/yyyy'});
	   });
	   
      
      $(document).ready(function() {
		  debugger;  		$(':input','#masterPlanForm')
  		  .not(':button, :submit, :reset, :hidden')
  		  .val('')  
  		//  document.getElementById("country").value = "India";
  		// populateStates("country", "state");

		  document.getElementById("licence").value='${masterPlanForm.licence}';

  	});
	   </script>
