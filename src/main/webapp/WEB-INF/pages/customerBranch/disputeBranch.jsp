<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

				<script type="text/javascript">
				
				function Test3()
				{
                
				var p,r,i,t,s;

				p=parseFloat(document.interest.totalCost.value);
                r=parseFloat(document.interest.weight.value);
                t= parseFloat(document.interest.fullyDamagedQty.value);
                i=p/r;
                s=t*i;
                document.interest.fullyDamagedCost.value= s;
				}  
			 	
			 	function Test4()
				{
                
				var p,r,i,t,s;

				p=parseFloat(document.interest.totalCost.value);
                r=parseFloat(document.interest.weight.value);
                t= parseFloat(document.interest.missingGoodsQty.value);
                i=p/r;
                s=t*i;
                document.interest.missingGoodsCost.value= s;
				}
				function Test1()
				{
					var p,r,t,i;
					p=parseFloat(document.interest.missingGoodsQty.value);
					r=parseFloat(document.interest.fullyDamagedQty.value);
					t=parseFloat(document.interest.partiallyDamagedQty.value);
					/* i=(p/r)*100; */
					i= p + r + t;
					document.interest.noOfDefect.value= i;
					
					}
					
					function Test2()
					{
						var p,r,t,i;
						p=parseFloat(document.interest.missingGoodsCost.value);
						r=parseFloat(document.interest.fullyDamagedCost.value);
						t=parseFloat(document.interest.partiallyDamagedCost.value);
						/* i=p*r; */
						i= p + r + t;
						document.interest.answer2.value=i;
					}
				
			</script>
				
<div class="col-sm-6 col-md-9 col-lg-9 body_fixed">
				
				<div class="col-sm-12 col-md-12 col-lg-12">
					<div  class="successMsg col-sm-11" style="text-align:center;color:green;font-size: 18px;">${successFull}</div>
				</div>
				
				<form:form action="disputeBranchConfirm" method="post" commandName="disputeForm" enctype="multipart/form-data"  name="interest" onsubmit="return val();">
				
				<form:hidden path ="transactionId" />
				<form:hidden path ="wareHousrMng" />
			<div class="col-sm-6 col-md-6 col-lg-6">
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3><spring:message code="label.customerInfo"/></h3>
				</div>
				<table align="center">
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.customerName"/>:</b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="customerName" readonly="true" class="form-control" placeholder=""  id="customer" onclick="same();"/></td>
					</tr>
					<tr>	
						<td id="customerError" style="display:none;color:red;"><font color="red"><spring:message code="label.validation"/></font></td>
					        <td id="customerError" style="display:none;color:red;"><spring:message code="label.validation"/></div>
					        </td>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.customerEmail"/>:</b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="customerEmail" readonly="true" class="form-control" placeholder="Enter Email"  id="customerEmail"/>
						</td>
					</tr>
					
						 <div  class="successMsg" style="text-align:center;color:red;font-size: 18px;">${success}</div>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.masterKey"/>:</b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="masterKey" readonly="true" class="form-control" placeholder="Enter Master Key"  id="masterKey"/>
							
						</td>
					</tr>	
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.poKey"/>:</b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="pokey" readonly="true" class="form-control" placeholder="Enter PO Key"  id="purchaseOrderKey"/>
							
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.goodsQuantity"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="weight" readonly="true" class="form-control"  id="weight"/>
						
						</td>
					</tr>
				</table>
			</div>		
						
			<div class="col-sm-6 col-md-6 col-lg-6">
						<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
							<h3><spring:message code="label.suppInfo"/></h3>
						</div>
				<table align="center">	
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.supplierName"/>:</b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="supplierName" readonly="true" class="form-control" placeholder="Enter Supplier name"  id="supplierName"/>
							
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.email"/>:</b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="supplierEmail" readonly="true"  class="form-control" placeholder="Enter Supplier Email"  id="supplierEmail"/>
						
						</td>
					</tr>
				</table>
		</div>		
				
			<div class="col-sm-12 col-md-12 col-lg-12">	
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3><spring:message code="label.goodsInfo"/></h3>
				</div>	
				<table align="center">				
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.goodsCategory"/>:</b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:select path="goods"  class="form-control" placeholder=""  id="goods" style="width:205px;">
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
							<div id="goodsError" style="display:none;color:red;"><spring:message code="label.plzSelectValue"/></div>
					        <div id="goodsError" style="display:none;color:red;"><spring:message code="label.plzSelectValue"/></div>
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.goodsInfo"/>:</b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="goodsInfo"  class="form-control" placeholder="Enter goodsInfo"  id="goodsInfo"/>
							<div id="goodsInfoError" style="display:none;color:red;"><spring:message code="label.validation"/></div>
					        <div id="goodsInfoError" style="display:none;color:red;"><spring:message code="label.validation"/></div>
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.totalCostOfGoods"/>:</b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="totalCost"   class="form-control" placeholder="Enter total Cost Of Goods"  id="totalCost"/>
							<div id="totalCostError" style="display:none;color:red;"><spring:message code="label.validation"/></div>
					        <div id="totalCostError" style="display:none;color:red;"><spring:message code="label.validation"/></div>
						</td>
					</tr>
				<%-- 	<tr>	
						<td class="col-sm-6"><b><spring:message code="label.goodsQuantity"/>:</b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="quantity"  class="form-control" placeholder="Enter goods Quantity"  id="quantity"/>
							<div id="quantityError" style="display:none;color:red;"><spring:message code="label.validation"/></div>
					        <div id="quantityError" style="display:none;color:red;"><spring:message code="label.validation"/></div>
						</td>
					</tr>	
					<tr>
						<td class="col-sm-6"><input type="button" value="Cost Of Each Unit" class="btn btn-default" style="color:#2E9AFE;font-weight: 600;" onclick="Test();"></td>
						<td class="col-sm-6">
							<form:input path="answer"  class="form-control" placeholder=""  id="costEachUnit"/>
													</td>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.defectType"/>:</b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:select path="goodsDefect"  class="form-control" placeholder=""  id="goodsDefect" style="width:205px;">
								 <form:option value=""><spring:message code="label.selectValue"/></form:option>
								 <form:option value="Missing Goods"><spring:message code="label.missingGoods"/></form:option>
								<form:option value="Partially Damaged Goods"><spring:message code="label.damagedGoods"/></form:option>
								<form:option value="Fully Damaged Goods"><spring:message code="label.fullyDamagedGoods"/></form:option>
								<form:option value="Mismatched Goods"><spring:message code="label.mismatchGoods"/></form:option>
							</form:select>
							<div id="goodsDefectError" style="display:none;color:red;"><spring:message code="label.id"/><spring:message code="label.plzSelectValue"/></div>
					        <div id="goodsDefectError" style="display:none;color:red;"><spring:message code="label.id"/><spring:message code="label.plzSelectValue"/></div>
						</td>
					</tr> --%>
					<tr>
						<td align="center"><form:checkbox path="missingGoods"  value="Missing Goods"  style="margin-top:-1px;"/><b><spring:message code="label.missingGoods"/></b></td>
					</tr>
					
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.quantity"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="missingGoodsQty" value="0" class="form-control" placeholder="Enter Quantity"  id="missingGoodsQty"/>
							<div id="missingGoodsQtyError" style="display:none;color:red;"><spring:message code="label.validation"/></div>
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><input type="button" value="<spring:message code="label.cost"/>" class="btn btn-default" style="color:#2E9AFE;font-weight: 600;" onclick="Test4();">
						<td class="col-sm-6">
							<form:input path="missingGoodsCost" value="0" class="form-control" placeholder="Enter Cost"  id="missingGoodsCost"/>
							<div id="missingGoodsCostError" style="display:none;color:red;"><spring:message code="label.validation"/></div>
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.fir"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="fir"  class="form-control" placeholder="Enter FIR Details"  id="fir"/>
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.firDate"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="firDate"  class="form-control" readonly="true" placeholder="Enter Date"  id="datepicker"/><i class="fa fa-calendar"></i>
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.location"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="location"  class="form-control" placeholder="Enter Location"  id="location"/>
						</td>
					</tr>
					<tr>
						<td align="center"><form:checkbox path="partiallyDamagedGoods"  value="Partially Damaged"  style="margin-top:-1px;"/><b><spring:message code="label.damagedGoods"/></b></td>
					</tr>
					
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.quantity"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="partiallyDamagedQty" value="0" class="form-control" placeholder="Enter Quantity"  id="partiallyDamagedQty"/>
							<div id="partiallyDamagedQtyError" style="display:none;color:red;"><spring:message code="label.validation"/></div>
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.cost"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="partiallyDamagedCost" value="0"  class="form-control" placeholder="Enter Cost"  id="partiallyDamagedCost"/>
							<div id="partiallyDamagedCostError" style="display:none;color:red;"><spring:message code="label.validation"/></div>
						</td>
					</tr>
						<tr>
						<td align="center"><form:checkbox path="fullyDamagedGoods"  value="Fully Damaged"  style="margin-top:-1px;"/><b><spring:message code="label.fullyDamagedGoods"/></b></td>
					</tr>
					
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.quantity"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="fullyDamagedQty" value="0" class="form-control" placeholder="Enter Quantity"  id="fullyDamagedQty"/>
							<div id="fullyDamagedQtyError" style="display:none;color:red;"><spring:message code="label.validation"/></div>
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><input type="button" value="<spring:message code="label.cost"/>" class="btn btn-default" style="color:#2E9AFE;font-weight: 600;" onclick="Test3();">
						<td class="col-sm-6">
							<form:input path="fullyDamagedCost" value="0" class="form-control" placeholder="Enter Cost"  id="fullyDamagedCost"/>
							<div id="fullyDamagedCostError" style="display:none;color:red;"><spring:message code="label.validation"/></div>
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.damageSummary"/>:</b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:textarea path="goodsSummarry"  class="form-control" placeholder="Enter damage Summary"  id="damageSummary"/>
							<div id="damageSummaryError" style="display:none;color:red;"><spring:message code="label.validation"/></div>
						</td>
					</tr>
				<%-- 	<tr>	
						<td class="col-sm-6"><b><spring:message code="label.noOfDefectiveGoods"/>:</b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="noOfDefect"  class="form-control" placeholder="Enter no Of Defective Goods"  id="noOfDefect"/>
							<div id="noOfDefectError" style="display:none;color:red;"><spring:message code="label.validation"/></div>
					        <div id="noOfDefectError" style="display:none;color:red;"><spring:message code="label.validation"/></div>
						</td>
					</tr> --%>
					
					<tr>
						<td class="col-sm-6"><input type="button" value="<spring:message code="label.noOfDefectiveGoods"/>" class="btn btn-default" style="color:#2E9AFE;font-weight: 600;" onclick="Test1();"></td>
						<td class="col-sm-6">
							<form:input path="noOfDefect"  class="form-control" placeholder="Enter defect Percantage"  id="defectPercentage"/>
						<div id="defectPercentageError" style="display:none;color:red;"><spring:message code="label.validation"/></div>
						</td>
					</tr>	
					<tr>
						<td class="col-sm-6"><input type="button" value="<spring:message code="label.costOfDefectiveGoods"/>" class="btn btn-default" style="color:#2E9AFE;font-weight: 600;" onclick="Test2();"></td>
						<td class="col-sm-6">
							<form:input path="answer2"  class="form-control" placeholder="Enter cost Of DefectiveGoods"  id="costDefectiveGoods"/>
							<div id="costDefectiveGoodsError" style="display:none;color:red;"><spring:message code="label.validation"/></div>
						</td>
					</tr>
				</table>
			</div>
				<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="disputeBranchList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
				</form:form>
				
				
				
				
	</div>
			
	<script>
	function val(){
		
		var goods  = document.getElementById('goods');
		var number='^\\d+$';
		var number= /(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/;
		var number1= /^-?[0-9]+$/;

		var canSubmit = true; 
		
		
		if (document.getElementById('goods').value == '') {
			document.getElementById('goodsError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('goodsError').style.display = 'none';
		}
		if(missingGoodsQty.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || missingGoodsQty.value.match(/^-?[0-9]+$/) &&!(document.getElementById('missingGoodsQty').value == ''))
	  	{
	  		
	  		document.getElementById('missingGoodsQtyError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('missingGoodsQtyError').style.display='block';
	  	     canSubmit = false;
	  	}
		if(missingGoodsCost.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || missingGoodsCost.value.match(/^-?[0-9]+$/) &&!(document.getElementById('missingGoodsCost').value == ''))
	  	{
	  		
	  		document.getElementById('missingGoodsCostError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('missingGoodsCostError').style.display='block';
	  	     canSubmit = false;
	  	}
		if(partiallyDamagedQty.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || partiallyDamagedQty.value.match(/^-?[0-9]+$/) &&!(document.getElementById('partiallyDamagedQty').value == ''))
	  	{
	  		
	  		document.getElementById('partiallyDamagedQtyError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('partiallyDamagedQtyError').style.display='block';
	  	     canSubmit = false;
	  	}
		if(partiallyDamagedCost.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || partiallyDamagedCost.value.match(/^-?[0-9]+$/) &&!(document.getElementById('partiallyDamagedCost').value == ''))
	  	{
	  		
	  		document.getElementById('partiallyDamagedCostError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('partiallyDamagedCostError').style.display='block';
	  	     canSubmit = false;
	  	}
		if(fullyDamagedQty.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || fullyDamagedQty.value.match(/^-?[0-9]+$/) &&!(document.getElementById('fullyDamagedQty').value == ''))
	  	{
	  		
	  		document.getElementById('fullyDamagedQtyError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('fullyDamagedQtyError').style.display='block';
	  	     canSubmit = false;
	  	}
		if(fullyDamagedCost.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || fullyDamagedCost.value.match(/^-?[0-9]+$/) &&!(document.getElementById('fullyDamagedCost').value == ''))
	  	{
	  		
	  		document.getElementById('fullyDamagedCostError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('fullyDamagedCostError').style.display='block';
	  	     canSubmit = false;
	  	}
		if(defectPercentage.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || defectPercentage.value.match(/^-?[0-9]+$/) &&!(document.getElementById('defectPercentage').value == ''))
	  	{
	  		
	  		document.getElementById('defectPercentageError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('defectPercentageError').style.display='block';
	  	     canSubmit = false;
	  	}
		if(costDefectiveGoods.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || costDefectiveGoods.value.match(/^-?[0-9]+$/) &&!(document.getElementById('costDefectiveGoods').value == ''))
	  	{
	  		
	  		document.getElementById('costDefectiveGoodsError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('costDefectiveGoodsError').style.display='block';
	  	     canSubmit = false;
	  	}
	/* if(quantity.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || quantity.value.match(/^-?[0-9]+$/) &&!(document.getElementById('quantity').value == ''))
	  	{
	  		
	  		document.getElementById('quantityError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('quantityError').style.display='block';
	  	     canSubmit = false;
	  	}	 */	
		
		
		/* if (document.getElementById('goodsDefect').value == '') {
			document.getElementById('goodsDefectError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('goodsDefectError').style.display = 'none';
		} */
		
	
/* 	if(noOfDefect.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || noOfDefect.value.match(/^-?[0-9]+$/) &&!(document.getElementById('noOfDefect').value == ''))
  	{
  		
  		document.getElementById('noOfDefectError').style.display='none';
  	
  	}
  	else{
  		document.getElementById('noOfDefectError').style.display='block';
  	     canSubmit = false;
  	} */		
  	if (document.getElementById('damageSummary').value == '') {
		document.getElementById('damageSummaryError').style.display = 'block';
		canSubmit = false;
	} else {
		document.getElementById('damageSummaryError').style.display = 'none';
	}
	
if(canSubmit == false){
	return false;
	}
		
		
	}

	

$(function() {
$( "#datepicker" ).datepicker({format:'dd/mm/yyyy'});
});
			
				
		</script>				
							
				