<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
	function val() {
		debugger;
		var buyerName = document.getElementById('goodsCategory');
		var companyName = document.getElementById("goods");
		var bank = document.getElementById('goodsDetails');
		var quantity = document.getElementById('quantity');
		var invoiceRemaining = document.getElementById('invoiceRemaining');
		var quantityForInvoice = document.getElementById('quantityForInvoice');
		var weight = document.getElementById('weight');
		var address = document.getElementById("licence");
		var amount = document.getElementById('amount');
		var poKey = document.getElementById('poKey');
		var poDate = document.getElementById('poDate');
		var poInfo = document.getElementById('poInfo');
		var number='^\\d+$';
		var number= /(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/;
		var number1= /^-?[0-9]+$/;
		var serviceTax= document.getElementById('serviceTax');

		var dutyTax= document.getElementById('dutytax');
		var vatTax= document.getElementById('vattax');

		var insuranceAmount  = document.getElementById('insuranceAmount');
		var canSubmit = true;

		if(quantityForInvoice.value>invoiceRemaining.value || quantityForInvoice.value<1){
			document.getElementById('quantityForInvoiceError').style.display='block';
			//This is pointing to different element above the quantity for invoice so that invoice quantity should come in view,
			// our html structure is not correct so this is temporary solution as we don't have enought time,
			// we have some other high priority tasks
			// document.getElementById('heading').scrollIntoView();
			quantityForInvoice.scrollIntoView();
			canSubmit = false;
		}

		// if (document.getElementById('goodsCategory').value == '') {
		// 	document.getElementById('goodsCategoryError').style.display = 'block';
		// 	canSubmit = false;
		// } else {
		// 	document.getElementById('goodsCategoryError').style.display = 'none';
		// }

		// if (document.getElementById('goods').value == '') {
		// 	document.getElementById('goodsError').style.display = 'block';
		// 	canSubmit = false;
		// } else {
		// 	document.getElementById('goodsError').style.display = 'none';
		// }
		if (document.getElementById('goodsDetails').value == '') {
			document.getElementById('goodsDetailsError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('goodsDetailsError').style.display = 'none';
		}

		// if(quantity.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || quantity.value.match(/^-?[0-9]+$/) &&!(document.getElementById('quantity').value == ''))
		// {
		//
		// 	document.getElementById('quantityError').style.display='none';
		//
		// }
		// else{
		// 	document.getElementById('quantityError').style.display='block';
		// 	canSubmit = false;
		// }

		if(amount.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || amount.value.match(/^-?[0-9]+$/) &&!(document.getElementById('amount').value == ''))
		{

			document.getElementById('amountError').style.display='none';

		}
		else{
			document.getElementById('amountError').style.display='block';
			canSubmit = false;
		}

		if(serviceTax.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || serviceTax.value.match(/^-?[0-9]+$/) &&!(document.getElementById('serviceTax').value == ''))
		{

			document.getElementById('serviceTaxError').style.display='none';

		}
		else{
			document.getElementById('serviceTaxError').style.display='block';
			canSubmit = false;
		}

		if(dutyTax.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || serviceTax.value.match(/^-?[0-9]+$/) &&!(document.getElementById('dutytax').value == ''))
		{

			document.getElementById('dutytaxError').style.display='none';

		}
		else{
			document.getElementById('dutytaxError').style.display='block';
			canSubmit = false;
		}

		if(vatTax.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || serviceTax.value.match(/^-?[0-9]+$/) &&!(document.getElementById('vattax').value == ''))
		{

			document.getElementById('vattaxError').style.display='none';

		}
		else{
			document.getElementById('vattaxError').style.display='block';
			canSubmit = false;
		}

		if(amount.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || amount.value.match(/^-?[0-9]+$/) &&!(document.getElementById('amount').value == ''))
		{

			document.getElementById('amountError').style.display='none';

		}
		else{
			document.getElementById('amountError').style.display='block';
			canSubmit = false;
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
		if(insuranceAmount.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || insuranceAmount.value.match(/^-?[0-9]+$/) ||(document.getElementById('insuranceAmount').value == ''))
		{

			document.getElementById('insuranceAmountError').style.display='none';

		}
		else{
			document.getElementById('insuranceAmountError').style.display='block';
			canSubmit = false;
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



	<form:form action="generateInvoiceFromPOConfirm" method="post" name="interest" commandName="invoiceForm" id="newBuyer" onsubmit="return val();">
		<div class="col-sm-6">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 id="heading"><spring:message code="label.buyerDetails"/></h3>
			</div>

			<table align="center">
				<form:hidden path="customerName" value="${model.masterPlanForm.customer}"/>
				<form:hidden path="masterKey" value="${model.masterPlanForm.masterKey}"/>
				<form:hidden path="customerHeadEmail" value="${model.masterPlanForm.customerEmail}"/>
				<form:hidden path="buyerPoId" value="${model.invoiceForm.buyerPoId}"/>


				<tr>
					<td class="col-sm-6"><b><spring:message code="label.buyerName"/></b><span style="color:red">*</span></td>
					<td class="col-sm-6">
						<form:input path="buyerName" value="${model.newBuyerForm.buyerName}" id="name" readonly="true"/>
					</td>
				</tr>
				<form:hidden path="id" value="${model.newBuyerForm.id}" />
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.buyerEmail"/></b><span style="color:red">*</span></td>
					<td class="col-sm-6">
						<form:input path="buyerEmail" value="${model.newBuyerForm.email}" id="name" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.buyerBank"/></b><span style="color:red">*</span></td>
					<td class="col-sm-6">
						<form:input path="buyerBank" value="${model.newBuyerForm.bank}" id="name" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.buyerBankEmail"/></b><span style="color:red">*</span></td>
					<td class="col-sm-6">
						<form:input path="buyerBankEmail" value="${model.newBuyerForm.bankEmail}" id="name" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.buyerCountry"/></b><span style="color:red">*</span></td>
					<td class="col-sm-6">
						<form:input path="buyerCountry" value="${model.newBuyerForm.country}" id="name" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.buyerState"/></b><span style="color:red">*</span></td>
					<td class="col-sm-6">
						<form:input path="buyerState" value="${model.newBuyerForm.state}" id="name" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.buyerCity"/></b><span style="color:red">*</span></td>
					<td class="col-sm-6">
						<form:input path="buyerCity" value="${model.newBuyerForm.city}" id="buyerCity" readonly="true"/>
					</td>
				</tr>
			</table>
		</div>
		<div class="col-sm-6 col-md-6">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3><spring:message code="label.poInfo"/></h3>
			</div>
			<table align="center">

				<tr>
					<td class="col-sm-6"><b><spring:message code="label.poKey"/></b><span
							style="color: red">*</span></td>
					<td class="col-sm-6"><form:input path="poKey" value="${model.purchaseOrderForm.poKey}"  placeholder="Enter PoKey" id="poKey" />
						<div id="poKeyError" style="display: none; color: red;"><spring:message code="label.validation"/></div>

				</tr>

				<tr>
					<td class="col-sm-6"><b><spring:message code="label.poDate"/></b><span
							style="color: red">*</span></td>

							<td class="col-sm-6"><form:input id="datepicker" path="poDate" value='${model.purchaseDate}' placeholder="Enter PoDate"/>

							<%-- <td class="col-sm-6"><form:input id="datepicker" path="poDate" value='${model.purchaseOrderForm.purchaseDate}'  placeholder="Enter PoDate"/> --%>

						<div id="date1Error" style="display: none; color: red;"><spring:message code="label.dateIsMandatory"/></div>
							</td>

				</tr>
				<%-- <tr>
					<td class="col-sm-6"><b><spring:message code="label.poInfo"/></b><span style="color: red">*</span></td>
					<td class="col-sm-6"><form:input path="poInfo" id="poInfo" placeholder="Enter PoInfo"/>
						<div id="poInfoError" style="display: none; color: red;"><spring:message code="label.validation"/></div>

				</tr> --%>
			</table>
		</div>


		<div class="col-sm-12 col-md-12">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3><spring:message code="label.productInfo"/></h3>
			</div>

			<table align="center">

				<tr>
					<td class="col-sm-6"><b><spring:message code="label.goodsCategory"/></b><span
							style="color: red">*</span></td>
						<%-- 	<td><form:input path="goodsCategory" readonly="true" id="goodsCategory" /></td> --%>
					<td class="col-sm-6"><form:input path="goodsCategory" value="${model.purchaseOrderForm.goodsCategory}" id="goodsCategory" readonly="true" placeholder="Enter Goods Category" />
						<div id="goodsError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
						<div id="goodsError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>

				</tr>

				<tr>
					<td class="col-sm-6"><b><spring:message code="label.goods"/></b><span
							style="color: red">*</span></td>
					<td class="col-sm-6"><form:input path="goods" id="goods" value="${model.purchaseOrderForm.goods}" readonly="true" placeholder="Enter Goods"/>
						<div id="goodsError" style="display: none; color: red;"><spring:message code="label.validation"/></div>

				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.details"/></b><span style="color: red">*</span></td>
					<td class="col-sm-6"><form:input path="goodsDetails" id="goodsDetails"  value="${model.purchaseOrderForm.goodsDetails}" placeholder="Enter Goods Details"/>
						<div id="goodsDetailsError" style="display: none; color: red;"><spring:message code="label.validation"/></div>

				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.quantity"/></b><span
							style="color: red">*</span></td>
					<td class="col-sm-6"><form:input readOnly="true" path="quantity" id="quantity"  value="${model.purchaseOrderForm.quantity}"  placeholder="Enter Quantity"/>

				</tr>

				<tr>
					<td class="col-sm-6"><b><spring:message code="label.invoiceRemaining"/></b><span
							style="color: red">*</span></td>
					<td class="col-sm-6"><form:input readOnly="true" path="invoiceRemaining" id="invoiceRemaining"  value="${model.invoiceForm.invoiceRemaining}"  placeholder="Enter Quantity"/>

				</tr>

				<tr>
					<td class="col-sm-6"><b><spring:message code="label.quantityForInvoice"/></b><span
							style="color: red">*</span></td>
					<td class="col-sm-6"><form:input type="number"  min="1" max="${model.invoiceForm.invoiceRemaining}" path="quantityForInvoice" id="quantityForInvoice"  value="${model.invoiceForm.quantityForInvoice}"  placeholder="Enter Quantity"/>
						<div id="quantityForInvoiceError" style="display: none; color: red;">Quantity For Invoice can't be les than 1 or greater then Invoice Remaining</div>

				</tr>

				<tr>
					<td class="col-sm-6"><b><spring:message code="label.whName"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
					<td class="col-sm-6"><form:select id="wareHouseName" path="wareHouseName" style="width:205px;">
						<form:option value=""><spring:message code="label.select"/></form:option>
						<form:options items="${invoiceForm.wareHouseList}"
									  itemValue="wareHouseName" itemLabel="wareHouseName" />
					</form:select>
						<div id="wareHouseNameError" style="display: none; color: red;"><spring:message code="label.validation2"/>
						</div>
					</td>

					</td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.weight"/></b><span
							style="color: red">*</span></td>
					<td class="col-sm-6"><form:input path="weight" id="weight" value="${model.purchaseOrderForm.weight}" placeholder="Enter Weight"/>
						<div id="weightError" style="display: none; color: red;"><spring:message code="label.validation"/></div>

				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.license"/></b><span
							style="color: red">*</span></td>
					<td class="col-sm-6"><form:select path="licence" id="licence" style="width:205px;">
							<form:option value=""><spring:message code="label.selectValue"/></form:option>
							<form:option value="ogl"><spring:message code="label.ogl"/></form:option>
							<form:option value="restricted"><spring:message code="label.restricted"/></form:option>
						</form:select>
						<div id="licenceError" style="display: none; color: red;"><spring:message code="label.validation2"/></div>

				</tr>
<%--				<tr>--%>
<%--					<td class="col-sm-6"><b><spring:message code="label.tenure"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>--%>
<%--					<td class="col-sm-6"><form:input path="tenure" id="tenure"  placeholder="Enter Tenure"/>--%>
<%--						<div id="tenureError" style="display: none; color: red;"><spring:message code="label.validation"/></div>--%>

<%--				</tr>--%>




				<tr>
					<td class="col-sm-6"><b><spring:message code="label.servicetax"/></b><span
							style="color: red">*</span></td>
					<td class="col-sm-6"><form:input path="serviceTax" id="serviceTax"  placeholder="Enter Service Tax Amount"/>
						<div id="serviceTaxError" style="display: none; color: red;"><spring:message code="label.validation"/></div>

				</tr>

				<tr>
					<td class="col-sm-6"><b><spring:message code="label.dutytax"/></b><span
							style="color: red">*</span></td>
					<td class="col-sm-6"><form:input path="dutytax" id="dutytax"  placeholder="Enter Duty Tax Amount"/>
						<div id="dutytaxError" style="display: none; color: red;"><spring:message code="label.validation"/></div>

				</tr>

				<tr>
					<td class="col-sm-6"><b><spring:message code="label.vattax"/></b><span
							style="color: red">*</span></td>
					<td class="col-sm-6"><form:input path="vattax" id="vattax"  placeholder="Enter Vat Tax Amount"/>
						<div id="vattaxError" style="display: none; color: red;"><spring:message code="label.validation"/></div>

				</tr>





				<tr>
					<td class="col-sm-6"><b><spring:message code="label.amount"/></b><span
							style="color: red">*</span></td>
					<td class="col-sm-6"><form:input path="amount" id="amount"  placeholder="Enter Amount"/>
						<div id="amountError" style="display: none; color: red;"><spring:message code="label.validation"/></div>

				<tr>
					<td class="col-sm-6"><input type="button" value="<spring:message code="label.totalcost"/>" class="btn btn-default" style="color:#2E9AFE;font-weight: 600;" onclick="Test();"></td>
					<td class="col-sm-6">
						<form:input path="answer"  class="form-control" placeholder=""  id="totalcost"/>
					</td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.insType"/></b></td>
					<td class="col-sm-6"><form:input path="insuranceType" id="insuranceType" placeholder="Enter Insurance Type"></form:input>

				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.insAmt"/></b></td>
					<td class="col-sm-6"><form:input path="insuranceAmount" id="insuranceAmount" placeholder="Enter Insurance Amount"></form:input>
					<td id="insuranceAmountError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
					<td id="insuranceAmountError" class="error" style="display:none"><spring:message code="label.validation"/></td>


				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.insDetails"/></b></td>
					<td class="col-sm-6"><form:textarea path="insuranceDetails" id="insuranceDetails" placeholder="Enter Insurance Details"></form:textarea>


				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.startDate"/></b></td>
					<td class="col-sm-6">
						<form:input path="startDate"  class="form-control" readonly="true" placeholder="Enter Date" style="width: 176px;" id="startDate"/></td>
					<td><i class="fa fa-calendar"></i></td>
				</tr>

				<tr>
					<td class="col-sm-6"><b><spring:message code="label.endDate"/></b></td>
					<td class="col-sm-6">
						<form:input path="endDate"  class="form-control" readonly="true" placeholder="Enter Date" style="width: 176px;" id="endDate"/></td>
					<td><i class="fa fa-calendar"></i></td>
				</tr>
			</table>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
			<table align="center">
				<tr>
					<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
					<td><a href="generateInvoice" class="btn btn-success"><spring:message code="label.back"/></a></td>
				</tr>
			</table>
		</div>
	</form:form>
</div>
<script>

	$(function() {
	 $( "#datepicker" ).datepicker({format:'dd/mm/yyyy'});  
	});

	$(document).ready(function() {
		$(':input','#masterPlanForm')
				.not(':button, :submit, :reset, :hidden')
				.val('')

		document.getElementById("licence").value='${purchaseOrderForm.licence}';
	});
</script>
<script type="text/javascript">

	function Test()
	{

		var s,d,v,a,t;

		s=parseFloat(document.interest.serviceTax.value);
		d=parseFloat(document.interest.dutytax.value);
		v=parseFloat(document.interest.vattax.value);
		a=parseFloat(document.interest.amount.value);
		t=s+d+v+a;
		document.interest.answer.value= t;
	}

	$(function() {
		$( "#startDate" ).datepicker({format:'dd/mm/yyyy'});
		$( "#endDate" ).datepicker({format:'dd/mm/yyyy'});
		
	});
	

</script>