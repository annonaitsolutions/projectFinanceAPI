<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

				
<div class="col-sm-6 col-md-9 col-lg-9 body_fixed">
				
				<div class="col-sm-12 col-md-12 col-lg-12">
					
					<div  class="successMsg" style="text-align:center;color:green;font-size: 18px;">${success}</div>
				</div>
				
				<form:form action="poInsApprovalPost" method="post" commandName="disputeReportsForm" enctype="multipart/form-data"  name="interest" onsubmit="return val();">
				
				<form:hidden path ="transactionId" />
				<form:hidden path ="id" />
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3><spring:message code="label.reports"/></h3>
				</div>
				<table align="center">
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.customerName"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="customerName" readonly="true" class="form-control" placeholder=""  id="customerName" onclick="same();"/>
					</tr>
					
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.supplierName"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="supplierName" readonly="true" class="form-control" placeholder="Enter Email"  id="supplierName"/>
						</td>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.disputeKey"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="disputekey" readonly="true" class="form-control" placeholder="Enter PO Key"  id="disputekey"/>
						</td>
					</tr>	
						<tr>	
						<td class="col-sm-6"><b><spring:message code="label.transMode"/></b></td>
						<td class="col-sm-6">
							<form:input path="modeOfTransport" readonly="true" class="form-control"  id="supplierName"/>
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.transType"/></b></td>
						<td class="col-sm-6">
							<form:input path="transportType" readonly="true" class="form-control"  id="supplierName"/>
							
						</td>
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.status"/></b></td>
						<td class="col-sm-6">
							<form:input path="status" readonly="true" class="form-control"  id="status"/>
						</td>
						
					</tr>
					<tr>	
						<td class="col-sm-6"><b><spring:message code="label.comment"/></b></td>
						<td class="col-sm-6">
							<form:input path="comment" readonly="true" class="form-control"  id="comment"/>
						</td>
						
					</tr>			
				</table>
			</div>		
			
			<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="poInsApprovalList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
				
				</form:form>
				
				
				
				
	</div>
	
			
							
				