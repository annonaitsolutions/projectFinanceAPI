	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>

function validateForm() {
	
	var d = new Date().getTime();
    var uuid = 'xxxxxx'.replace(/[xy]/g, function(c) {
        var r = (d + Math.random()*16)%16 | 0;
        d = Math.floor(d/16);
        return (c=='x' ? r : (r&0x3|0x8)).toString(16);
    });
    document.msplan.masterkey.value=uuid;
   
    var d1 = new Date().getTime();
	var uuid1 = 'xxxxxx'.replace(/[xy]/g, function(c1) {
		var r1 = (d1 + Math.random() * 16) % 16 | 0;
		d1 = Math.floor(d / 16);
		return (c1 == 'x' ? r1 : (r1 & 0x3 | 0x8)).toString(16);
	});
	document.msplan.transactionId.value = uuid1;

 
    
    
    var fproduct =document.getElementsByName('category');
	var quantity =document.getElementsByName('product');
 	var tenure =document.getElementsByName('description');
 	var bcost =document.getElementsByName('licence');
	var btenure =document.getElementsByName('weight'); 
    var supplierName =document.getElementsByName('quantity');
	var country =document.getElementsByName('buyingCost');
 	var materials =document.getElementsByName('tenure');
 	var canSubmit = true;
 	


     if (document.getElementById('category').value == ''){
 		document.getElementById('categoryError').style.display='block';
 		canSubmit = false;
 	}
 	else{
 		document.getElementById('categoryError').style.display='none';
 	}

      if (document.getElementById('product').value == ''){
  		document.getElementById('productError').style.display='block';
  		canSubmit = false;
  	}
  	else{
  		document.getElementById('productError').style.display='none';
  	}

       if (document.getElementById('description').value == ''){
   		document.getElementById('descriptionError').style.display='block';
   		canSubmit = false;
   	}
   	else{
   		document.getElementById('descriptionError').style.display='none';
   	}

        if (document.getElementById('licence').value == ''){
    		document.getElementById('licenceError').style.display='block';
    		canSubmit = false;
    	}
    	else{
    		document.getElementById('licenceError').style.display='none';
    	}

         if (document.getElementById('weight').value == ''){
     		document.getElementById('weightError').style.display='block';
     		canSubmit = false;
     	}
     	else{
     		document.getElementById('weightError').style.display='none';
     	} 
         
 	     if (document.getElementById('quantity').value == ''){
 	       		document.getElementById('quantityError').style.display='block';
 	       		canSubmit = false;
 	       	}
 	       	else{
 	       		document.getElementById('quantityError').style.display='none';
 	       	}
 	           
 	     if (document.getElementById('buyingCost').value == ''){
 	       		document.getElementById('buyingCostError').style.display='block';
 	       		canSubmit = false;
 	       	}
 	       	else{
 	       		document.getElementById('buyingCostError').style.display='none';
 	       	}
 	     if (document.getElementById('tenure').value == ''){
	       		document.getElementById('tenureError').style.display='block';
	       		canSubmit = false;
	       	}
	       	else{
	       		document.getElementById('tenureError').style.display='none';
	       	}

 if(canSubmit == false){
 	return false;
 }

 }
 
 
        
   </script>
   <form:form action="tdraftsMasterPlanInfoConfirm" name="msplan" method="post" onsubmit="return validateForm()" commandName="tMasterPlanForm">
	
	<table align="center">
	<h3 style="font-size:19px; font-weight:400; color:#2E9AFE;"><spring:message code="label.productInfo"/></h3>
					<div class="col-sm-6">
						<tr>
						<td class="heading_text"><b><spring:message code="label.category"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-8"><form:select path="category" id="category" style="width:86.9%">
											    <form:option value=""><spring:message code="label.selectValue"/></form:option> 
											    <form:option value="autoMobile"><spring:message code="label.automobile"/></form:option>
												<form:option value="clothes"><spring:message code="label.clothes"/></form:option>
												</form:select>
								<div id="categoryError" style="display: none; color: red;"><spring:message code="label.validation"/>
									</div>
								<div id="categoryError" style="display: none; color: red;"><spring:message code="label.validation"/>
									</div></td>
						</tr>
					<tr>
							<td class="heading_text"><b><spring:message code="label.product"/></b><span style="color: red">*</span></td>
							<td><form:input path="product" id="product" />
								<div id="productError" style="display: none; color: red;"><spring:message code="label.validation"/>
									</div>
								<div id="productError" style="display: none; color: red;"><spring:message code="label.validation"/>
									</div></td>
						</tr>
                               
                               <form:hidden path="id"/>
						<tr>
							<td class="heading_text"><b><spring:message code="label.description"/></b><span style="color: red">*</span></td>
							<td><form:input path="description" id="description" />
								<div id="descriptionError" style="display: none; color: red;"><spring:message code="label.validation"/>
									</div>
								<div id="descriptionError" style="display: none; color: red;"><spring:message code="label.validation"/>
									</div></td>
						</tr>
							<tr>
							<td class="heading_text"><b><spring:message code="label.license"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-8"><form:select path="licence" id="licence" style="width:86.9%">
											    <form:option value=""><spring:message code="label.selectValue"/></form:option> 
											    <form:option value="ogl"><spring:message code="label.ogl"/></form:option>
												<form:option value="restricted"><spring:message code="label.restricted"/></form:option>
												</form:select>
								<div id="licenceError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="licenceError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.weight"/></b><span
								style="color: red">*</span></td>
							<td><form:input path="weight" id="weight" />
								<div id="weightError" style="display: none; color: red;"><spring:message code="label.validation"/>
									</div>
								<div id="weightError" style="display: none; color: red;"><spring:message code="label.validation"/>
									</div></td>
						</tr>
						<tr>
							<td class="heading_text"><b><spring:message code="label.quantity"/></b><span
								style="color: red">*</span></td>
							<td><form:input path="quantity" id="quantity" />
								<div id="quantityError" style="display: none; color: red;"><spring:message code="label.validation"/>
									</div>
								<div id="quantityError" style="display: none; color: red;"><spring:message code="label.validation"/>
									</div></td>
						</tr>

				</table>
		<table align="center">
		<h3 style="font-size:19px; font-weight:400; color:#2E9AFE;"><spring:message code="label.costInfo"/></h3>
					<div class="col-sm-6">
						
							<tr>
							<td class="heading_text"><b><spring:message code="label.buyingCost"/></b><span style="color: red">*</span></td>
							<td><form:input path="buyingCost" id="buyingCost" />
								<div id="buyingCostError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="buyingCostError" style="display: none; color: red;"><spring:message code="label.validation"/> </div></td>
						</tr>

						<tr>
							<td class="heading_text"><b><spring:message code="label.tenure"/></b><span style="color: red">*</span></td>
							<td><form:input path="tenure" id="tenure" />
								<div id="tenureError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								<div id="tenureError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>
						</tr>
							
						
						<form:hidden path ="masterKey" value="masterKey" id="masterkey"/>
						<form:hidden path ="transactionId" value="transactionId" id="transactionId"/>
						<form:hidden path ="customer"  id="customer"/>
						<form:hidden path ="currencySymbol" id="currencySymbol"/>

				</table>
	
		
							<tr>
								<td><input type="submit" class="btn btn-primary" value="Continue"></td>
								<td><a href="user" class="btn btn-success" name="back"  onclick="javascript:window.location='#';"/>back</a></td>
							</tr>
					</table>
					</form:form>	