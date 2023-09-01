<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
	function val() {

		var email = document.getElementById("email");
		var buyerName = document.getElementById('goodsCategory');
		var companyName = document.getElementById("goods");
		var bank = document.getElementById('goodsDetails');
		var bankEmail = document.getElementById("quantity");
		var branch = document.getElementById('weight');
		var country = document.getElementById('amount');
		var country = document.getElementById('purchaseDate');
		var country = document.getElementById('tenure');
		var country = document.getElementById('pokey');
		var phoneNum  = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/; 
		var reg     = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		var number='^\\d+$';
		var number= /(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/;
		var number1= /^-?[0-9]+$/;
		var canSubmit = true;

		if (document.getElementById('goodsCategory').value == '') {
			document.getElementById('goodsCategoryError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('goodsCategoryError').style.display = 'none';
		}
	  	if(email.value.match(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/) &&!(document.getElementById('email').value == ''))
	  	{
	  		
	  		document.getElementById('emailError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('emailError').style.display='block';
	  	     canSubmit = false;
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

		
		if(weight.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || weight.value.match(/^-?[0-9]+$/)&&!(document.getElementById('weight').value == ''))
	  	{
	  		
	  		document.getElementById('weightError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('weightError').style.display='block';
	  	     canSubmit = false;
	  	}
		
	if(amount.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || amount.value.match(/^-?[0-9]+$/)&&!(document.getElementById('amount').value == ''))
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
		
		if (document.getElementById('poKey').value == '') {
				document.getElementById('poKeyError').style.display = 'block';
				canSubmit = false;
			} else {
				document.getElementById('poKeyError').style.display = 'none';
			}

		

		if (canSubmit == false) {
			return false;
		}
	}
</script>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3><spring:message code="label.purchaseOrder"/></h3>
	</div>
		 <div class="col-sm-12 col-md-12 col-lg-12">
				 <div  class="sucess"><b><font color="green">${success}</font></b></div>
		 </div>
		  <div class="col-sm-12 col-md-12 col-lg-12">
			 <div class="successMsg" style="text-align: center; color: green; font-size: 18px;">${success}</div>
		  </div>
			<form:form action="buyerPoConfirm" method="post" commandName="salesOrderForm" id="salesOrder" onsubmit="return val();">

				<form:hidden path="id" />

				<div class="col-sm-6 col-md-6 col-lg-6">	
					<table align="center">
				
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.customerName"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="customerHeadName" id="customerHeadName" readonly="true"/></td>
						</tr>
							<tr>
							<td class="col-sm-6"><b><spring:message code="label.buyerName"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="buyerName" id="customerHeadName" readonly="true"/></td>
						</tr>
							<tr>
							<td class="col-sm-6"><b><spring:message code="label.email"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="buyerEmail" id="email" placeholder="Enter Email" />
								<div id="emailError" style="display: none; color: red;"><spring:message code="label.validation"/>
									</div>
								<div id="emailError" style="display: none; color: red;"><spring:message code="label.validation"/>
									</div></td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.goodsCategory"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:select path="goodsCategory" id="goodsCategory" style="width:205px;">
								<form:option value=""><spring:message code="label.selectValue"/></form:option> 
								 <form:option value="Antique">Antique</form:option>
                                 <form:option value="Automobiles">Automobiles</form:option>
                              	 <form:option value="Books">Books</form:option>
                         		 <form:option value="Clothing & Accessories">Clothing & Accessories</form:option>
                            	 <form:option value="Computers & Accessories">Computers & Accessories </form:option>
                            	 <form:option value="Electronics">Electronics</form:option>
                            	 <form:option value="Health Care">Health Care</form:option>
                            	 <form:option value="Jewellery">Jewellery</form:option>
                            	 <form:option value="Musical Instruments">Musical Instruments</form:option>
                            	 <form:option value="Metals">Metals</form:option>
                            	 <form:option value="Machinery">Machinery</form:option>
                            	 <form:option value="Software">Software</form:option>
                            	 <form:option value="Scientific Instruments">Scientific Instruments</form:option>
												</form:select>
												<div id="goodsCategoryError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="goodsCategoryError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.purchaseDate"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input id="datepicker" path="startDate" placeholder="Enter Star Date"  />
								<div id="date1Error" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="date1Error" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.goods"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="goods" id="goods" placeholder="Enter Goods" />
								<div id="goodsError" style="display: none; color: red;"><spring:message code="label.validation"/>
									</div>
								<div id="goodsError" style="display: none; color: red;"><spring:message code="label.validation"/>
									</div></td>
						</tr>
			</table>
			</div>
			<div class="col-sm-6 col-md-6 col-lg-6">
			<table>
			           <%--  <tr>
							<td class="col-sm-6"><b><spring:message code="label.masterKey"/></b><span style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="masterKey" id="masterKey" placeholder="Enter Details"/>
								<div id="masterKeyError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="masterKeyError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr> --%>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.details"/></b><span style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="goodsDetails" id="goodsDetails" placeholder="Enter Details"/>
								<div id="goodsDetailsError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="goodsDetailsError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.quantity"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="quantity" id="quantity" placeholder="Enter Quantity" />
								<div id="quantityError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="quantityError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.weight"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="weight" id="weight" placeholder="Enter Weight" />
								<div id="weightError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="weightError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>
					
					<%-- 	<tr>
							<td class="col-sm-6"><b><spring:message code="label.poKey"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="poKey" id="poKey" placeholder="Enter PoKey" />
								<div id="poKeyError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="poKeyError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr> --%>
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.amount"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="amount" id="amount" placeholder="Enter Amount" />
								<div id="amountError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="amountError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.tenure"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="tenure" id="tenure" placeholder="Enter Tenure" />
								<div id="tenureError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="tenureError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>
						
					

				</table>
					</div>
				<div class="col-sm-12 col-md-12 col-lg-12">&nbsp;</div>
					<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="buyers" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
					</form:form>
					
					
</div>
<script>
	$(function() {
		$("#datepicker").datepicker({
			format : 'dd/mm/yyyy'
		});
	});
</script>