<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">


function validateForm() {    


	var country =document.getElementsByName('material');
 	var materials =document.getElementsByName('country');
 	var quantityStr =document.getElementsByName('quantityStr');
	var costStr =document.getElementsByName('costStr'); 
 	var canSubmit = true;      
         
            if (document.getElementById('material').value == ''){
 	       		document.getElementById('materialsError').style.display='block';
 	       		canSubmit = false;
 	       	}
 	       	else{
 	       		document.getElementById('materialsError').style.display='none';
 	       	}
 	           
 	    if (document.getElementById('country').value == ''){
 	       		document.getElementById('countryError').style.display='block';
 	       		canSubmit = false;
 	       	}
 	       	else{
 	       		document.getElementById('countryError').style.display='none';
 	       	}
 	      if (document.getElementById('quantityStr').value == ''){
	       		document.getElementById('quantityStrError').style.display='block';
	       		canSubmit = false;
	       	}
	       	else{
	       		document.getElementById('quantityStrError').style.display='none';
	       	}

 	  
 	 if (document.getElementById('costStr').value == ''){
 		document.getElementById('costStrError').style.display='block';
 		canSubmit = false;
 	}
 	else{
 		document.getElementById('costStrError').style.display='none';
 	}
 	   
 	   
 	
 if(canSubmit == false){
 	return false;
 }

 }
 



function addRow(tableID) {
	 
    var table = document.getElementById(tableID);
   
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);

    var colCount = table.rows[1].cells.length;

    for(var i=0; i<colCount; i++) {
        var newcell = row.insertCell(i);                
        
        newcell.innerHTML = table.rows[1].cells[i].innerHTML;                
        switch(newcell.childNodes[1].type) {
            case "text":
                    newcell.childNodes[1].value = "";
                    break;                   
            case "select-one":
                    newcell.childNodes[1].selectedIndex = 0;
                    break;
        }
    }
}


function deleteRow(tableID)
{
try
     {
    var table = document.getElementById(tableID);
    var rowCount = table.rows.length;
   
                if (rowCount > 2) {                        
                	 table.deleteRow(rowCount-1);
                }else {
                	alert("Cannot delete all the rows");	
                }
            
        } catch(e)
            {
            alert(e);
    }          
}
</script>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	
	<div class="col-sm-12 col-md-12 col-lg-12">
		<div  class="successMsg" style="text-align:center;color:green;font-size: 18px;">${successFull}</div>
	</div>
   <form:form action="tdraftsMasterPlanInfoPost" name="msplan" onsubmit="return validateForm()" method="post" commandName="tMasterPlanForm">
	
	
 
		<div class="col-sm-6">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3><spring:message code="label.productInfo"/></h3>
			</div>
				<table>
						<tr>
						<td class="col-sm-6"><b><spring:message code="label.category"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="category" readonly="true"  id="category" />
								</td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.product"/></b><span style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="product" readonly="true"  id="product" />
								</td>
						</tr>

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.description"/></b><span style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="description" readonly="true" id="description" />
								</td>
						</tr>
							<tr>
							<td class="col-sm-6"><b><spring:message code="label.license"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="licence" readonly="true" id="licence" />
								</td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.weight"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="weight" readonly="true" id="weight" />
							</td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.quantity"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="quantity" readonly="true" id="quantity" />
								</td>
						</tr>

				</table>
		</div>
		<div class="col-sm-6">
				<h3 style="font-size:19px; font-weight:400; color:#2E9AFE;"><spring:message code="label.costInfo"/></h3>
		<table>
	
				
						  <form:hidden path="id"/>
							<tr>
							<td class="col-sm-6"><b><spring:message code="label.buyingCost"/></b><span style="color: red">*</span></td>
							<td><form:input path="buyingCost" readonly="true" id="buyingCost" /></td>
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
							<td class="col-sm-6"><b><spring:message code="label.tenure"/></b><span style="color: red">*</span></td>
							<td><form:input path="tenure" readonly="true" id="tenure" />
								</td>
						</tr>
							
						
						<form:hidden path ="masterKey"  id="masterKey"/>
						<form:hidden path ="transactionId"  id="transactionId"/>
						<form:hidden path ="customer" id="customer"/>
						<form:hidden path ="currencySymbol"  id="currencySymbol"/>

				</table>
	</div>
	
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
							<h3><spring:message code="label.brekDownCharge"/></h3>
			</div>  
			 <table class="host-list" id="dataTable">

					<tr class="chargescolor">
	
						<th><spring:message code="label.materials"/></th>
						<th><spring:message code="label.country"/></th>
						<th><spring:message code="label.quantity"/></th>
						<th><spring:message code="label.cost"/></th>
					</tr>		
					<tr>
					
						<td>
							<input type="text" name="material" id="material">	
							<div id="materialsError" class="error" style="display:none;color:red;"><spring:message code="label.validation"/></div> 
						</td>
						<td>
							<select id="country" name ="country"style="width:175px;"></select>
							 <div id="countryError" class="error"  style="display:none;color:red;"><spring:message code="label.validation"/></div>	 
						 <script language="javascript">
                                                                 populateCountries("country");
                                                              </script>
                                                              <%-- <form:hidden path ="state"  id="currencySymbol"/> --%>
						</td>
						
						<td>
							<input type="text" name="QuantityStr" id="quantityStr">	
							 <div id="quantityStrError" class="error"  style="display:none;color:red;"><spring:message code="label.validation"/></div> 
						</td>	
						<td>
							<input type="text" name="CostStr" id="costStr">	
						 	<div id="costStrError" class="error"  style="display:none;color:red;"><spring:message code="label.validation"/></div>  
						</td>  
					</tr>			
			</table>
			
			  	<div style="color:#FF4000" class="error_box"></div>
				<div id="countryError" class="error" style="display:none"><spring:message code="label.validation"/></div>
				<div id="materialsError" class="error" style="display:none"><spring:message code="label.validation"/></div>
				<div id="quantityStrError" class="error" style="display:none"><spring:message code="label.validation"/></div>
				<div id="costStrError" class="error" style="display:none"><spring:message code="label.validation"/></div>  
		
			<a class="btn btn-default"  onclick="addRow('dataTable')" value="Add New Supplier"
			 style="color:#2E9AFE;font-weight: 600;"><spring:message code="label.addNewSupp"/></a>
			<a  class="btn btn-default"  onclick="deleteRow('dataTable')" value="Remove Supplier"
			 style="color:#2E9AFE;font-weight: 600;"><spring:message code="label.removSupp"/></a>
		<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="tmasterPlanDraftsList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
			
		<p>&nbsp;</p>
					</form:form>	
					
					<form:form action="tdraftsMasterPlan" method="post" commandName="tMasterPlanForm">
					   
					
					<form:hidden path ="category"  id="customer"/>
						<form:hidden path ="product" id="product"/>
						<form:hidden path ="description"  id="description"/>
						<form:hidden path ="licence" id="licence"/>
						<form:hidden path ="weight"  id="weight"/>
						<form:hidden path ="quantity" id="quantity"/>
					     <form:hidden path ="buyingCost"  id="buyingCost"/>
						<form:hidden path ="tenure" id="tenure"/>
						<form:hidden path ="workingCapital"  id="workingCapital"/>
						<form:hidden path ="WcTenure" id="WcTenure"/>
						<form:hidden path ="sellingCost"  id="sellingCost"/>
						<form:hidden path ="sellingTenure" id="sellingTenure"/>
						
						<form:hidden path ="customer"  id="customer"/>
						<form:hidden path ="currencySymbol" id="currencySymbol"/>
					<table align="center">	
							<tr>
								<td><input type="submit" class="btn btn-primary" value="<spring:message code="label.saveToDrafts"/>" style="background: lightcoral;"></td>
							</tr>
						</table>
					
					
					</form:form>
	</div>	
	<style>
	.rupee img{
		    height: 20px;
    width: 20px;
    margin-top: -8px;
	}
</style>		