<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

				
<div class="col-sm-6 col-md-9 col-lg-9 body_fixed">
				
				<div class="col-sm-12 col-md-12 col-lg-12">
					
					<div  class="successMsg" style="text-align:center;color:green;font-size: 18px;">${success}</div>
				</div>
				
				<form:form action="addOrModifyReportPost" method="post" commandName="disputeReportsForm" enctype="multipart/form-data"  name="interest" onsubmit="return val();">
				
				<form:hidden path ="transactionId" />
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3><spring:message code="label.reports"/></h3>
				</div>
				<table align="center">
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.customerName"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="customerName" readonly="true" class="form-control" placeholder=""  id="customer" onclick="same();"/></td>
					</tr>
					
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.supplierName"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="supplierName" readonly="true" class="form-control" placeholder="Enter Email"  id="customerEmail"/>
						</td>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.disputeKey"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="disputekey" readonly="true" class="form-control" placeholder="Enter PO Key"  id="purchaseOrderKey"/>
							
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
							<form:input path="goodsDetails" readonly="true"  class="form-control" placeholder="Enter Goods Info"  id="goodsInfo"/>
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.goods"/></b></td>
						<td class="col-sm-6">
							<form:input path="goods" readonly="true" class="form-control" placeholder="Enter Total Cost Of Goods"  id="totalCost"/>
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.cost"/></b></td>
						<td class="col-sm-6">
							<form:input path="cost" readonly="true" class="form-control" placeholder="Enter Goods Quantity"  id="quantity"/>
						</td>
					</tr>	
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.insDetails"/></b></td>
						<td class="col-sm-6">
							<form:input path="insDetails" readonly="true" class="form-control" placeholder="Enter Goods Quantity"  id="quantity"/>
						</td>
					</tr>
						<tr>	
						<td class="col-sm-6"><b><spring:message code="label.startDate"/></b></td>
						<td class="col-sm-6">
							<form:input path="inStartDate" readonly="true" class="form-control" id="insStartDate"/>
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.endDate"/></b></td>
						<td class="col-sm-6">
							<form:input path="insEndDate" readonly="true" class="form-control" placeholder="Enter Goods Quantity"  id="quantity"/>
						</td>
					</tr>
				</table>
			</div>				
			<div class="col-sm-12 col-md-12 col-lg-12">
						<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
							<h3><spring:message code="label.transInfo"/></h3>
						</div>
				<table align="center">
					
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.transType"/></b></td>
						<td class="col-sm-6">
							<form:input path="transportType" readonly="true" class="form-control"  id="supplierName"/>
							
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.transMode"/></b></td>
						<td class="col-sm-6">
							<form:input path="modeOfTransport" readonly="true" class="form-control"  id="supplierName"/>
							
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.termsAndCon"/></b></td>
						<td class="col-sm-6">
							<form:textarea path="termsCond" readonly="true" class="form-control"   id="supplierEmail"/>
						
						</td>
					</tr>
						<%-- <tr>	
						<td class="col-sm-6"><b><spring:message code="label.inclusion"/></b></td>
						<td class="col-sm-6">
							<form:input path="inclusion" readonly="true" class="form-control"  id="supplierName"/>
							
						</td>
					</tr>
						<tr>	
						<td class="col-sm-6"><b><spring:message code="label.exclusion"/></b></td>
						<td class="col-sm-6">
							<form:input path="exclusion" readonly="true" class="form-control"  id="supplierName"/>
							
						</td>
					</tr> --%>
				</table>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
						<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
							<h3><spring:message code="label.surInfo"/></h3>
						</div>
				<table align="center">
						
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.surveyorName"/></b></td>
						<td class="col-sm-6">
							<form:input path="surveyorName" readonly="true" class="form-control"  id="supplierName"/>
							
						</td>
					</tr>
					
						<tr>	
						<td class="col-sm-6"><b><spring:message code="label.surveyorCom"/></b></td>
						<td class="col-sm-6">
							<form:input path="surveyorCom" readonly="true" class="form-control"  id="supplierName"/>
							
						</td>
					</tr>
						<tr>	
						<td class="col-sm-6"><b><spring:message code="label.surveyorAdd"/></b></td>
						<td class="col-sm-6">
							<form:input path="surveyorAdd" readonly="true"  class="form-control"  id="supplierName"/>
							
						</td>
					</tr>
						<tr>	
						<td class="col-sm-6"><b><spring:message code="label.surveyorEmail"/></b></td>
						<td class="col-sm-6">
							<form:input path="surveyorEmail" readonly="true"  class="form-control"  id="surveyorEmail"/>
							
						</td>
					</tr>
						<tr>	
						<td class="col-sm-6"><b><spring:message code="label.surveyorPhone"/></b></td>
						<td class="col-sm-6">
							<form:input path="surveyorPhone" readonly="true" class="form-control"  id="surveyorPhone"/>
						
						</td>
					</tr>
				</table>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
						<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
							<h3><spring:message code="label.damageInfo"/></h3>
						</div>
				<table align="center">
					
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.defectType"/></b></td>
						<td class="col-sm-6">
							<form:input path="defectType" readonly="true" class="form-control"  id="defectQty"/>
							
						</td>
					</tr>	
					
						<tr>	
						<td class="col-sm-6"><b><spring:message code="label.defectQty"/></b></td>
						<td class="col-sm-6">
							<form:input path="defectQty" readonly="true" class="form-control"  id="defectQty"/>
							
						</td>
					</tr>
						<tr>	
						<td class="col-sm-6"><b><spring:message code="label.defectCostForGoods"/></b></td>
						<td class="col-sm-6">
							<form:input path="defectCostForGoods" readonly="true" class="form-control"  id="supplierName"/>
							
						</td>
					</tr>
					
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.damageStatus"/></b></td>
						<td class="col-sm-6">
							<form:input path="damageStatus" readonly="true"  class="form-control"  id="repairCost"/>
						
						</td>
					</tr>
				
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.replacement"/></b></td>
						<td class="col-sm-6">
							<form:input path="replacement" readonly="true"  class="form-control"  id="repairCost"/>
						
						</td>
					</tr>
					
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.repairCost"/></b></td>
						<td class="col-sm-6">
							<form:input path="repairCost" readonly="true"  class="form-control"  id="repairCost"/>
						
						</td>
					</tr>
				</table>
		</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="disputeHeadUpdateList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
				
				</form:form>
				
	</div>
				
							
				