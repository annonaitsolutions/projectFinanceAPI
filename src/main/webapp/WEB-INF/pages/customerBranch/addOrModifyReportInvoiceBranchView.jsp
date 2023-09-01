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
						<td class="col-sm-6"><b><spring:message code="label.buyerName"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="buyerName" readonly="true" class="form-control" placeholder="Enter Email"  id="customerEmail"/>
						</td>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.invoiceKey"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="invoicekey" readonly="true" class="form-control" placeholder="Enter PO Key"  id="purchaseOrderKey"/>
							
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
							<form:input path="goods" readonly="true" class="form-control"  id="supplierName"/>
							
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
							<form:input path="suppSurveyorName" readonly="true" class="form-control"  id="supplierName"/>
							
						</td>
					</tr>
					
						<tr>	
						<td class="col-sm-6"><b><spring:message code="label.surveyorCom"/></b></td>
						<td class="col-sm-6">
							<form:input path="suppSurveyorCom" readonly="true" class="form-control"  id="supplierName"/>
							
						</td>
					</tr>
						<tr>	
						<td class="col-sm-6"><b><spring:message code="label.surveyorAdd"/></b></td>
						<td class="col-sm-6">
							<form:input path="suppSurveyorAdd" readonly="true"  class="form-control"  id="supplierName"/>
							
						</td>
					</tr>
						<tr>	
						<td class="col-sm-6"><b><spring:message code="label.surveyorEmail"/></b></td>
						<td class="col-sm-6">
							<form:input path="suppSurveyorEmail" readonly="true"  class="form-control"  id="surveyorEmail"/>
							
						</td>
					</tr>
						<tr>	
						<td class="col-sm-6"><b><spring:message code="label.surveyorPhone"/></b></td>
						<td class="col-sm-6">
							<form:input path="suppSurveyorPhone" readonly="true" class="form-control"  id="surveyorPhone"/>
						
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
							<form:input path="suppDefectType" readonly="true" class="form-control"  id="defectQty"/>
							
						</td>
					</tr>	
					
						<tr>	
						<td class="col-sm-6"><b><spring:message code="label.defectQty"/></b></td>
						<td class="col-sm-6">
							<form:input path="suppDefectQty" readonly="true" class="form-control"  id="defectQty"/>
							
						</td>
					</tr>
						<tr>	
						<td class="col-sm-6"><b><spring:message code="label.defectCostForGoods"/></b></td>
						<td class="col-sm-6">
							<form:input path="suppDefectCostForGoods" readonly="true" class="form-control"  id="supplierName"/>
							
						</td>
					</tr>
					
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.damageStatus"/></b></td>
						<td class="col-sm-6">
							<form:input path="suppDamageStatus" readonly="true"  class="form-control"  id="repairCost"/>
						
						</td>
					</tr>
				
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.replacement"/></b></td>
						<td class="col-sm-6">
							<form:input path="suppReplacement" readonly="true"  class="form-control"  id="repairCost"/>
						
						</td>
					</tr>
					
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.repairCost"/></b></td>
						<td class="col-sm-6">
							<form:input path="suppRepairCost" readonly="true"  class="form-control"  id="repairCost"/>
						
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.accept"/></b></td>
						<td class="col-sm-6">
							<form:input path="accept" readonly="true"  class="form-control"  id="repairCost"/>
						
						</td>
					</tr>
				</table>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3><spring:message code="label.arbDetails"/></h3>
				</div>
				<table align="center">
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.arbName"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="arbNames" readonly="true" class="form-control" placeholder="Enter Name"  id="arbNames" /></td>
											
					</tr>
					
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.location"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="loc" readonly="true" class="form-control" placeholder="Enter Location"  id="loc"/>
						</td>
						
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.judgement"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="judgement" readonly="true" class="form-control" placeholder="Enter Judgement"  id="judgement"/>
						</td>
								
					</tr>	
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.cost"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="arbCost" readonly="true" class="form-control" placeholder="Enter Cost"  id="arbCost"/>
						</td>
							
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.quantity"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="arbQty" readonly="true" class="form-control" placeholder="Enter Quantity"  id="arbQty"/>
						</td>
							
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.startDate"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="arbStartDate" readonly="true" class="form-control" placeholder="Enter Date"  id="arbStartDate"/>
						</td>
						
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.endDate"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="arbEndDate" readonly="true" class="form-control" placeholder="Enter Date"  id="arbEndDate"/>
						</td>
						
					</tr>
						<tr>
						<td class="col-sm-6"><b><spring:message code="label.documents"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="docSub" readonly="true" class="form-control" placeholder="Enter Documents Name"  id="docSub"/>
						</td>
							
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.moneyPaid"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="moneyPaid" readonly="true" class="form-control" placeholder="Enter Paid Money"  id="moneyPaid"/>
						</td>
							
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.payDate"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="payDate" readonly="true"  class="form-control" placeholder="Enter Paid Date"  id="payDate"/>
						</td>
						 	
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.goodsReplaced"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="GoodsReplaced" readonly="true" class="form-control" placeholder="Enter Goods"  id="GoodsReplaced"/>
						</td>
							
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.replacedDate"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="repacedDate" readonly="true" class="form-control" placeholder="Enter Date"  id="repacedDate"/>
						</td>
							
					</tr>
				</table>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td><a href="addOrModifyReportInvoiceBranchFullList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
				
				</form:form>
				
	</div>
				
							
				