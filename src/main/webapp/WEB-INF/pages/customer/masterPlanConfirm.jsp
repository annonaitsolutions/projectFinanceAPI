<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
   <form:form action="masterPlanInfoConfirm" name="msplan" method="post" onsubmit="return validateForm();" commandName="masterPlanForm" accept-charset="character_set">
   
	<div class="col-sm-6 col-md-6">
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<div  class="successFull" style="text-align:center;color:green;font-size: 18px;">${successFull}</div>
	<h3 align="center"><spring:message code="label.productInfo"/></h3>

	</div>
	<table align="center">
	
					
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.category"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:select path="category" id="category" style="width:205px;">
											    <form:option value=""><spring:message code="label.selectValue"/></form:option> 
											      <form:option value="Antique"><spring:message code="label.antique"/></form:option>
                                 <form:option value="Automobiles"><spring:message code="label.automobiles"/></form:option>
                              	 <form:option value="Books"><spring:message code="label.books"/></form:option>
                         		 <form:option value="Clothing & Accessories"><spring:message code="label.clothingAndAccessories"/></form:option>
                            	 <form:option value="Computers & Accessories"><spring:message code="label.computersAndAccessories"/></form:option>
                            	 <form:option value="Electronics"><spring:message code="label.electronics"/></form:option>
                            	 <form:option value="Health Care"><spring:message code="label.healthCare"/></form:option>
                            	 <form:option value="Jewellery"><spring:message code="label.jewellery"/></form:option>
                            	 <form:option value="Musical Instruments"><spring:message code="label.musicalInstruments"/></form:option>
                            	 <form:option value="Metals"><spring:message code="label.metals"/></form:option>
                            	 <form:option value="Machinery"><spring:message code="label.machinery"/></form:option>
                            	 <form:option value="Software"><spring:message code="label.software"/></form:option>
                            	 <form:option value="Scientific Instruments"><spring:message code="label.scientificInstruments"/></form:option>
												</form:select>
								<div id="categoryError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
						
							</td></tr>
							
						
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.product"/></b><span style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="product" id="product" placeholder="Enter Product"/>
								
							<div id="productError" style="display: none; color: red;"><spring:message code="label.validation"/></div>	
							</td>
						</tr>
                       
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.description"/></b><span style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="description" id="description" placeholder="Enter Description"/>
								<div id="descriptionError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								
						</tr>
							<tr>
							<td class="col-sm-6"><b><spring:message code="label.license"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:select path="licence" id="licence" style="width:205px;">
											    <form:option value=""><spring:message code="label.selectValue"/></form:option> 
											    <form:option value="ogl"><spring:message code="label.ogl"/></form:option>
												<form:option value="restricted"><spring:message code="label.restricted"/></form:option>
												</form:select>
								<div id="licenceError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
							
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.weight"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="weight" class="form-control" placeholder="Enter the weight" id="weight" />
								<div id="weightError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></div>
							</td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.quantity"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="quantity" id="quantity" placeholder="Enter Quantity"/>
								<div id="quantityError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
							
						</tr>

				</table>
			</div>
		<div class="col-sm-6 col-md-6">
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center"><spring:message code="label.costInfo"/></h3>
		</div>
		<table style="margin:0px auto;">
						
						<tr>
							<td><b><spring:message code="label.buyingCost"/></b><span style="color: red">*</span></td>
							<td><form:input path="buyingCost" id="buyingCost" placeholder="Enter Buying Cost" />
								<div id="buyingCostError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
								
							</td>
							
							<c:choose>
							<c:when test="${masterPlanForm.currencySymbol == 'dollar'}">
							<td><span class="rupee"><img src="<%=request.getContextPath()%>/resources/images/dollar.png"></span></td>														
							</c:when>
							<c:when test="${masterPlanForm.currencySymbol == 'euro'}">
							<td><span class="rupee"><img src="<%=request.getContextPath()%>/resources/images/euro.png"></span></td>														
							</c:when>
							<c:otherwise>
							<td><span class="rupee"><img src="<%=request.getContextPath()%>/resources/images/rupee.png"></span></td>							
							</c:otherwise>
							</c:choose> 
							<form:hidden path="currencySymbol" />
						</tr>

<%--						<tr>--%>
<%--							<td><b><spring:message code="label.tenure"/></b><span style="color: red">*</span></td>--%>
<%--							<td><form:input path="tenure" id="tenure" placeholder="Enter Tenure"/>--%>
<%--								<div id="tenureError" style="display: none; color: red;"><spring:message code="label.validation"/></div>--%>
<%--							--%>
<%--							</td>--%>
<%--						</tr>--%>
							
						<tr>
							<td><b><spring:message code="label.workingCapital"/></b></td>
							<td><form:input path="workingCapital" id="workingCapital" placeholder="Enter Working Capital" />
								 <div id="workingCapitalError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
							</td>
						
							<c:choose>
							<c:when test="${masterPlanForm.currencySymbol == 'dollar'}">
							<td><span class="rupee"><img src="<%=request.getContextPath()%>/resources/images/dollar.png"></span></td>														
							</c:when>
							<c:when test="${masterPlanForm.currencySymbol == 'euro'}">
							<td><span class="rupee"><img src="<%=request.getContextPath()%>/resources/images/euro.png"></span></td>														
							</c:when>
							<c:otherwise>
							<td><span class="rupee"><img src="<%=request.getContextPath()%>/resources/images/rupee.png"></span></td>							
							</c:otherwise>
							</c:choose>
							
						   
						   
						</tr>
<%--						<tr>--%>
<%--							<td><b><spring:message code="label.tenure"/></b></td>--%>
<%--							<td><form:input path="WcTenure" id="WcTenure" placeholder="Enter Working Tenure" />--%>
<%--								<div id="WcTenureError" style="display: none; color: red;"><spring:message code="label.validation"/></div>--%>
<%--							</td>--%>
<%--							 --%>
<%--						</tr>--%>
						<tr>
							<td><b><spring:message code="label.sellingCost"/></b></td>
							<td><form:input path="sellingCost" id="sellingCost" placeholder="Enter Selling Cost"/>
								 <div id="sellingCostError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
							</td>
							 
							 <c:choose>
							<c:when test="${masterPlanForm.currencySymbol == 'dollar'}">
							<td><span class="rupee"><img src="<%=request.getContextPath()%>/resources/images/dollar.png"></span></td>														
							</c:when>
							<c:when test="${masterPlanForm.currencySymbol == 'euro'}">
							<td><span class="rupee"><img src="<%=request.getContextPath()%>/resources/images/euro.png"></span></td>														
							</c:when>
							<c:otherwise>
							<td><span class="rupee"><img src="<%=request.getContextPath()%>/resources/images/rupee.png"></span></td>							
							</c:otherwise>
							</c:choose>
							
						
							  
				
						</tr>
<%--						<tr>--%>
<%--							<td><b><spring:message code="label.tenure"/></b></td>--%>
<%--							<td><form:input path="sellingTenure" id="sellingTenure" placeholder="Enter Selling Tenure"/>--%>
<%--						 <div id="sellingTenureError" style="display: none; color: red;"><spring:message code="label.validation"/></div></td>--%>
<%--						</tr>--%>
						<form:hidden path ="masterKey"  id="masterkey" value=""/>
						<form:hidden path ="transactionId"  id="transactionId"  value=""/>
						<form:hidden path ="customer"  id="customer"/>
			<%-- 			<form:hidden path ="currencySymbol" id="currencySymbol"/> --%>
						<form:hidden path="customerPrefix" id="customerPrefix"/>

				</table>
		</div>
				<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="user" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
					</form:form>	
	</div>				
<style>
	.bankemp_footer.col-sm-12 {
    margin-top: 82px;
}
</style>

<script>

function validateForm() {
	debugger;
    var category =document.getElementsByName('category');
	var number= /(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/;
	var number1= /^-?[0-9]+$/
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
      
      if(weight.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || weight.value.match(/^-?[0-9]+$/) &&!(document.getElementById('weight').value == ''))
	  	{
	  		
	  		document.getElementById('weightError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('weightError').style.display='block';
	  	     canSubmit = false;
	  	}
        
      if(quantity.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || quantity.value.match(/^-?[0-9]+$/) &&!(document.getElementById('quantity').value == ''))
	  	{
	  		
	  		document.getElementById('quantityError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('quantityError').style.display='block';
	  	     canSubmit = false;
	  	}
      
      if(buyingCost.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || buyingCost.value.match(/^-?[0-9]+$/) &&!(document.getElementById('buyingCost').value == ''))
	  	{
	  		
	  		document.getElementById('buyingCostError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('buyingCostError').style.display='block';
	  	     canSubmit = false;
	  	}
     /*  if(tenure.value.match(/^-?[0-9]+$/) &&!(document.getElementById('tenure').value == ''))
	  	{
	  		
	  		document.getElementById('tenureError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('tenureError').style.display='block';
	  	     canSubmit = false;
	  	} */
      
      if(workingCapital.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || workingCapital.value.match(/^-?[0-9]+$/) ||(document.getElementById('workingCapital').value == ''))
	  	{
	  		
	  		document.getElementById('workingCapitalError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('workingCapitalError').style.display='block';
	  	     canSubmit = false;
	  	}
      
      
       if(sellingCost.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || sellingCost.value.match(/^-?[0-9]+$/) ||(document.getElementById('sellingCost').value == ''))
	  	{
	  		
	  		document.getElementById('sellingCostError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('sellingCostError').style.display='block';
	  	     canSubmit = false;
	  	}
      
      
      /*  if(WcTenure.value.match(/^-?[0-9]+$/) ||(document.getElementById('WcTenure').value == ''))
	  	{
	  		
	  		document.getElementById('WcTenureError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('WcTenureError').style.display='block';
	  	     canSubmit = false;
	  	} */
      /*  if(sellingTenure.value.match(/^-?[0-9]+$/) ||(document.getElementById('sellingTenure').value == ''))
	  	{
	  		
	  		document.getElementById('sellingTenureError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('sellingTenureError').style.display='block';
	  	     canSubmit = false;
	  	} */
     
      
   if(canSubmit == false){
 	return false;
 }

  /*  var y=document.msplan.customerPrefix.value;
  	var d = new Date().getTime();
   var uuid = y+'-MP-xxxxxx'.replace(/[xy]/g, function(c) {
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
	document.msplan.transactionId.value = uuid1; */

 }
</script>

<style>
	.rupee img{
		    height: 20px;
    width: 20px;
    margin-top: -8px;
	}
</style>