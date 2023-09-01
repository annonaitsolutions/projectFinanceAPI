<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>

function validateForm() {

}
</script>


<div class="col-sm-6 col-md-9 col-lg-9 body_fixed">
					
				<div class="col-sm-12 col-md-12 col-lg-12">
					<div  class="successMsg" style="text-align:center;color:green;font-size: 18px;">${successFull}</div>
				</div>
				
				<form:form action="disputeBranchPost" method="post"  commandName="disputeForm" enctype="multipart/form-data"  name="msplan" onsubmit="return validateForm()">
				
				<form:hidden path="disputeKey"  value=""/>
				<form:hidden path ="wareHousrMng" />
				<div class="col-sm-6 col-md-6 col-lg-6">
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
						<h3><spring:message code="label.customerInfo"/></h3>
					</div>
				<table align="center">
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.customerName"/>:</b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="customerName" readonly="true" class="form-control" placeholder="" id="customer" onclick="same();"/></td>
					</tr>
						
						<div id="customerError" style="display:none;color:red;"><font color="red"><spring:message code="label.validation"/></font></div>
					        <div id="customerError" style="display:none;color:red;"><spring:message code="label.validation"/></div>
					        
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.customerEmail"/>:</b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="customerEmail" readonly="true" class="form-control" placeholder="Enter Email" id="customerEmail"/>
						</td>
					</tr>
					 <div  class="successMsg" style="text-align:center;color:red;font-size: 18px;">${success}</div>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.masterKey"/>:</b><span style="color:red">*</span></td>
						
						<td class="col-sm-6">
						
							<form:input path="masterKey" readonly="true" class="form-control" placeholder="Enter Master Key" id="masterKey"/>
						
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.poKey"/>:</b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="pokey" readonly="true" class="form-control" placeholder="Enter PO Key" id="purchaseOrderKey"/>
							
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
							<form:input path="supplierName" readonly="true" class="form-control" placeholder="Enter Supplier name" id="supplierName"/>
							
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.email"/>:</b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="supplierEmail" readonly="true"  class="form-control" placeholder="Enter Supplier Email" id="supplierEmail"/>
						
						</td>
					</tr>
				</table>	
				</div>
						<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
							<h3><spring:message code="label.goodsInfo"/></h3>
						</div>
						
			<table align="center">
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.goodsCategory"/>:</b><span style="color:red">*</span></td>
							<td class="col-sm-6">
							<form:input path="goods" readonly="true" class="form-control" placeholder="" id="goodsInfo"/>
													</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.goodsInfo"/>:</b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="goodsInfo" readonly="true" class="form-control" placeholder="" id="goodsInfo"/>
													</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.totalCostOfGoods"/>:</b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="totalCost" readonly="true"  class="form-control" placeholder="" id="totalCost"/>
							
						</td>
					</tr>
				<%-- 	<tr>	
						<td class="col-sm-6"><b><spring:message code="label.goodsQuantity"/>:</b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="quantity" readonly="true" class="form-control" placeholder="" id="quantity"/>
							
						</td>
					</tr> --%>
					<%-- <tr>	
							<td class="col-sm-6"><b><spring:message code="label.costOfEachUnit"/>:</b><span style="color:red">*</span></td>
							
							<td class="col-sm-6">
								<form:input path="answer" readonly="true" class="form-control" placeholder="" id="costEachUnit"/>
							</td>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.defectType"/>:</b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="goodsDefect" readonly="true" class="form-control" placeholder="" id="damageSummary"/>
						</td>
					</tr> --%>
					<tr>
						<td align="center"><form:checkbox path="missingGoods"  value="missingGoods" style="margin-top:-1px;"/><b><spring:message code="label.missingGoods"/></b></td>
					</tr>
					
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.quantity"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="missingGoodsQty"  class="form-control" readonly="true" placeholder="Enter Quantity"  id="missingGoodsQty"/>
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.cost"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="missingGoodsCost" readonly="true" class="form-control" placeholder="Enter Cost"  id="missingGoodsCost"/>
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.fir"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="fir"  class="form-control" readonly="true" placeholder="Enter FIR Details"  id="fir"/>
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
							<form:input path="location"  class="form-control" readonly="true" placeholder="Enter Location"  id="location"/>
						</td>
					</tr>
					<tr>
						<td align="center"><form:checkbox path="partiallyDamagedGoods" value="partiallyDamagedGoods" style="margin-top:-1px;"/><b><spring:message code="label.damagedGoods"/></b></td>
					</tr>
					
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.quantity"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="partiallyDamagedQty"  class="form-control" readonly="true" placeholder="Enter Quantity"  id="partiallyDamagedQty"/>
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.cost"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="partiallyDamagedCost" readonly="true" class="form-control" placeholder="Enter Cost"  id="partiallyDamagedCost"/>
						</td>
					</tr>
						<tr>
						<td align="center"><form:checkbox path="fullyDamagedGoods" value="fullyDamagedGoods" style="margin-top:-1px;"/><b><spring:message code="label.fullyDamagedGoods"/></b></td>
					</tr>
					
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.quantity"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="fullyDamagedQty"  class="form-control" readonly="true" placeholder="Enter Quantity"  id="fullyDamagedQty"/>
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.cost"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="fullyDamagedCost"  class="form-control" placeholder="Enter Cost" readonly="true" id="fullyDamagedCost"/>
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.damageSummary"/>:</b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="goodsSummarry" readonly="true" class="form-control" placeholder="" id="damageSummary"/>
						</td>
					</tr>
					<%-- <tr>	
						<td class="col-sm-6"><b><spring:message code="label.noOfDefectiveGoods"/>:</b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="noOfDefect" readonly="true" class="form-control" placeholder="" id="noOfDefect"/>
						</td>
					</tr> --%>
					
						<div class="col-sm-12 col-md-12 col-lg-12">&nbsp;</div>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.noOfDefectiveGoods"/> :</b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="noOfDefect" readonly="true"  class="form-control" placeholder=""  id="defectPercentage"/>
						</td>
					</tr>
					<tr>
						<form:hidden path ="transactionId" />
							<td class="col-sm-6"><b><spring:message code="label.costOfDefectiveGoods"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="answer2" readonly="true"  class="form-control" placeholder=""  id="costDefectiveGoods"/>
						</td>
					</tr>
			</table>
				<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">	
								<tr>
								<td>
								<input type="submit" class="btn btn-primary" value="Save"></td>
								<td>
								<a href="user" class="btn btn-success" name="back"  onclick="javascript:window.location='#';"><spring:message code="label.back"/></a></td>
								</tr>
							
					</table>	
				</div>
				</form:form>
				
				
				
				
	</div>
			
				
				