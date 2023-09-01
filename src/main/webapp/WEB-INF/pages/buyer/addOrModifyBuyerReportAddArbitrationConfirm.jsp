<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

				
<div class="col-sm-6 col-md-9 col-lg-9 body_fixed">
				
				<div class="col-sm-12 col-md-12 col-lg-12">
					
					<div  class="successMsg" style="text-align:center;color:green;font-size: 18px;">${success}</div>
				</div>
				
				<form:form action="addOrModifyBuyerReportAddArbitrationPost" method="post" commandName="disputeReportsForm"  onsubmit="return val();">
				
				<form:hidden path ="transactionId" />
				<form:hidden path ="invoicekey" />
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
						<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="addOrModifyBuyerReportList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
				
				</form:form>
				
	</div>
				
