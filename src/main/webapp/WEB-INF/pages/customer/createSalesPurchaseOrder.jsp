<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
   
<body>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

		<div class="col-sm-12 col-md-12 col-lg-12">
		<form:form action="#" method="post" commandName="masterPlanForm">
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3 style="text-align:center"><spring:message code="label.masterPlanSummary"/></h3>
		</div>
		<div class="col-sm-12">
		<form:hidden path="id" id="id"/>
		<form:hidden path="customerPrefix"/>
		<form:hidden path="quantity"/>
		            <div class="col-sm-3"><b><spring:message code="label.masterKey"/></b></div>
					<div class="col-sm-7">
					<form:input path="masterKey" id="masterKey" style="width:85%" readonly="true"/>
					</div>
				     <div class="col-sm-3"><b><spring:message code="label.name"/></b></div>
					<div class="col-sm-7">
					<form:input path="customer" id="customer" style="width:85%" readonly="true"/>
					</div>
		
					<div class="col-sm-3"><b><spring:message code="label.sanctionedAmount"/></b></div>
					<div class="col-sm-7">
					<form:input path="buyingCostSanc" id="bamtsanctioned" style="width:85%" readonly="true"/>
					</div>
				
					<div class="col-sm-3"><b><spring:message code="label.utilizedAmount"/></b></div>
					<div class="col-sm-7">
					<form:input path="utilizedBusnsAmt" id="reqpoamt"  style="width:85%" readonly="true"/>
					</div>
					<div class="col-sm-3"><b><spring:message code="label.email"/></b></div>
					<div class="col-sm-7">
					<form:input path="customerEmail" id="customerEmail"  style="width:85%" readonly="true"/>
					</div>
				
					<div class="col-sm-3"><b><spring:message code="label.balance"/></b></div>
					<div class="col-sm-7">
					<form:input path="balance" id="availablebalance" style="width:85%" readonly="true" />
					</div>
				
		</div>
			<c:if test="${! empty suppliers}">
        <div class="col-md-12 col-sm-12">
        <h3 align="center" style="font-size: 22px; font-weight:400; margin-top: -6px; color: #04ADF1;"><spring:message code="label.approvedSellerDetails"/></h3>
        <div><font color="red">${success}</font></div>
            <div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
			</div>
			<table class="table data jqtable example" id="my-table">
                <thead>
	                <tr>
						<th><spring:message code="label.id"/></th>
						<th> <spring:message code="label.name"/></th>
						<th><spring:message code="label.company"/></th>
						<th><spring:message code="label.product"/></th>
						<th><spring:message code="label.bank"/></th>
						<th><spring:message code="label.contactNumber"/></th>
						<th><spring:message code="label.email"/></th>
						<th><spring:message code="label.status"/></th>
						<th><spring:message code="label.rating"/></th>
						<th><spring:message code="label.limit"/></th>
						<th><spring:message code="label.poList"/></th>
						<th><spring:message code="label.action"/></th>
					</tr>
				</thead>
			<tbody>

		
			<c:forEach items="${suppliers}" var="supplierForm">  		
		    <tr>
				<td><c:out value="${supplierForm.id}"></c:out></td>
				<td><c:out value="${supplierForm.supplierName}"></c:out></td>
				<td><c:out value="${supplierForm.companyName}"></c:out></td>
				<td><c:out value="${supplierForm.material}"></c:out></td>
				<td ><c:out value="${supplierForm.bank}"></c:out> </td>
				<td ><c:out value="${supplierForm.contactNum}"></c:out></td>
				<td ><c:out value="${supplierForm.email}"></c:out> </td>
				<td ><c:out value="${supplierForm.status}"></c:out></td>
				<td ><div class="basic"	data-average="${material.rate}"></div></td>
				<td ><c:out value="${supplier.custLimit}"></c:out></td>
				<td><a href="listofPoOnMasterKey?id=${masterPlanForm.id}" class="btn btn-primary"><spring:message code="label.view"/></a></td> 
				<td><a href="businessPlanForPo?id=${supplierForm.id}&&supplierBuyerId=${supplierForm.supplierBuyerId}"class="btn btn-primary"><spring:message code="label.select"/></a></td>
			</tr>
				 </c:forEach>

				 
			 </tbody>
           </table>
    
			 
		</div>
				 </c:if>
			<c:if test="${! empty buyers}">
			<div class="col-sm-12">
           <h3 align="center" style="font-size: 22px; font-weight: 400;margin-top: -6px; color: #04ADF1;"><spring:message code="label.approvedBuyerDetails"/></h3>
          <div><font color="red">${success}</font></div>
             <div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" class="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
			</div>
			<table class="table data jqtable example my-table">
                <thead>
	                <tr>
						<th><spring:message code="label.id"/></th>
						<th> <spring:message code="label.name"/></th>
						<th><spring:message code="label.company"/></th>
						<th><spring:message code="label.bank"/></th>
						<th><spring:message code="label.contactNumber"/></th>
						<th><spring:message code="label.email"/></th>
						<th><spring:message code="label.status"/></th>
						<th><spring:message code="label.rating"/></th>
						<th><spring:message code="label.limit"/></th>
						<th><spring:message code="label.invoiceList"/></th>
						<th><spring:message code="label.action"/></th>
					</tr>
				</thead>
			<tbody>

		
			<c:forEach items="${buyers}" var="buyer">  		
		    <tr>
				<td><c:out value="${buyer.id}"></c:out></td>
				<td><c:out value="${buyer.buyerName}"></c:out></td>
				<td><c:out value="${buyer.companyName}"></c:out></td>
				<td ><c:out value="${buyer.bank}"></c:out> </td>
				<td ><c:out value="${buyer.contactNum}"></c:out></td>
				<td ><c:out value="${buyer.email}"></c:out> </td>
				<td ><c:out value="${buyer.status}"></c:out></td>
				<td ><div class="basic"	data-average="${buyer.rate}"></div></td>
				<td ><c:out value="${buyer.custLimit}"></c:out></td>
				<td><a href="listofInvoiceOnMasterKey?id=${masterPlanForm.id}" class="btn btn-primary"><spring:message code="label.view"/></a></td> 
				<td><a href="businessPlanForSalesOrder?id=${buyer.id}"class="btn btn-primary"><spring:message code="label.select"/></a></td> 
			</tr>
				 </c:forEach>

				 
			 </tbody>
            </table>
			</div>
			</c:if>
		</form:form>
</div>
<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td><a href="businessPlan" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
</div>
<script>
		$(document).ready(function(){
	    	 $(".basic").jRating({    		
	    		 isDisabled :true
	    	 });
		});
</script>
