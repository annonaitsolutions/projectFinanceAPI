<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

				
<div class="col-sm-6 col-md-9 col-lg-9 body_fixed">
				
				<div class="col-sm-12 col-md-12 col-lg-12">
					
					<div  class="successMsg" style="text-align:center;color:green;font-size: 18px;">${success}</div>
				</div>
				
				<form:form action="disputeMngConfirm" method="post" commandName="disputeForm" enctype="multipart/form-data"  name="interest" onsubmit="return val();">
				
				<form:hidden path ="transactionId" />
				<form:hidden path ="id" />
			<div class="col-sm-6 col-md-6 col-lg-6">
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3><spring:message code="label.customerInfo"/></h3>
				</div>
				<table align="center">
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.customerName"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="customerName" readonly="true" class="form-control" placeholder=""  id="customer" onclick="same();"/></td>
					</tr>
					
					
						 
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.masterKey"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="masterKey" readonly="true" class="form-control" placeholder="Enter Master Key"  id="masterKey"/>
							
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
						<td class="col-sm-6"><b><spring:message code="label.name"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="supplierName" readonly="true" class="form-control" placeholder="Enter Supplier name"  id="supplierName"/>
							
						</td>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.poKey"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="pokey" readonly="true" class="form-control" placeholder="Enter PO Key"  id="purchaseOrderKey"/>
							
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
						<td class="col-sm-6"><b><spring:message code="label.goodsInfo"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="goods"  class="form-control" readonly="true" id="goodsInfo"/>
						</td>
					</tr>
				
					<tr>
						<td align="center"><h5 class="sub-heading"><spring:message code="label.missingGoods"/></h5></td>
					</tr>
					
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.quantity"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="missingGoodsQty" readonly="true" class="form-control" placeholder="Enter Quantity"  id="missingGoodsQty"/>
						</td>
					</tr>
				
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.cost"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="missingGoodsCost" readonly="true" class="form-control" placeholder="Enter Cost"  id="fullyDamagedQty"/>
						</td>
					</tr>
					
					<tr>
						<td align="center"><h5 class="sub-heading"><spring:message code="label.damagedGoods"/></h5></td>
					</tr>
					
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.quantity"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="partiallyDamagedQty" readonly="true" class="form-control" placeholder="Enter Quantity"  id="partiallyDamagedQty"/>
					</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.cost"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="partiallyDamagedCost" readonly="true" class="form-control" placeholder="Enter Cost"  id="partiallyDamagedCost"/>
						</td>
					</tr>
						<tr>
						<td align="center"><h5 class="sub-heading"><spring:message code="label.fullyDamagedGoods"/></h5></td>
					</tr>
					
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.quantity"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="fullyDamagedQty" readonly="true" class="form-control" placeholder="Enter Quantity"  id="fullyDamagedQty"/>
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.cost"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="fullyDamagedCost" readonly="true" class="form-control" placeholder="Enter Cost"  id="fullyDamagedQty"/>
						</td>
					</tr>
					
				<tr>	
						<td class="col-sm-6"><b><spring:message code="label.noOfDefectiveGoods"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="noOfDefect" readonly="true" class="form-control"   id="partiallyDamagedCost"/>
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.costOfDefectiveGoods"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="answer2" readonly="true" class="form-control"   id="partiallyDamagedCost"/>
						</td>
					</tr>	
					 	<tr>	
						<td class="col-sm-6"><b><spring:message code="label.status"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:select path="status"  class="form-control" placeholder=""  id="status" style="width:205px;">
							<form:option value=""><spring:message code="label.selectValue"/></form:option>
								    <form:option value="Approved"><spring:message code="label.approve"/></form:option>
                                 <form:option value="Rejected"><spring:message code="label.reject"/></form:option>
                            
								</form:select>
							<div id="statusError" style="display:none;color:red;"><spring:message code="label.plzSelectValue"/></div>
					        <div id="statusError" style="display:none;color:red;"><spring:message code="label.plzSelectValue"/></div>
						</td>
					</tr> 
					
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.comment"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:textarea path="comment" placeholder="Enter Comment"  class="form-control"   id="comment"/>
								<div id="commentError" style="display:none;color:red;"><spring:message code="label.validation"/></div>
					        <div id="commentError" style="display:none;color:red;"><spring:message code="label.validation"/></div>
						
						</td>
					</tr>
				</table>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="disputeMngList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
				
				</form:form>
				
				
				
				
	</div>
			
	<script>
	function val(){
				
					var status  = document.getElementById('status');
				

					var canSubmit = true; 
							
			  	if (document.getElementById('status').value == '') {
					document.getElementById('statusError').style.display = 'block';
					canSubmit = false;
				} else {
					document.getElementById('statusError').style.display = 'none';
				}
				
			  	if (document.getElementById('comment').value == '') {
					document.getElementById('commentError').style.display = 'block';
					canSubmit = false;
				} else {
					document.getElementById('commentError').style.display = 'none';
				}
				
			if(canSubmit == false){
				return false;
				}
					
					
				}

		</script>				
							
				