<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 style="text-align:center"><spring:message code="label.masterPlanSummary"/></h3>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
			<form:form action="#" method="post" commandName="fundsDistributeForm">
		
		<form:hidden path="id" id="id"/>
		<table align="center">
		<tr>
		            <td class="col-sm-6"><b><spring:message code="label.masterKey"/>:</b></td>
					<td class="col-sm-6">
					<form:input path="masterKey" id="masterKey" readonly="true"/>
					</td>
					</tr>
					<tr>
					 <td class="col-sm-6"><b><spring:message code="label.customerHeadName"/>:</b></td>
					<td class="col-sm-6">
					<form:input path="customerHeadName" id="customerHeadName" readonly="true"/>
					</td>
					</tr>
					<tr>
				     <td class="col-sm-6"><b><spring:message code="label.customerName"/>:</b></td>
					<td class="col-sm-6">
					<form:input path="customerName" id="customerName" readonly="true"/>
					</td>
			</tr>
			<tr>
					<td class="col-sm-6"><b><spring:message code="label.distAmt"/>:</b></td>
					<td class="col-sm-6">
					<form:input path="distributedAmount" id="distributedAmount" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.utilizedAmount"/>:</b></td>
					<td class="col-sm-6">
					<form:input path="utilizedAmount" id="utilizedAmount"  readonly="true"/>
					</td>
					</tr>
					<tr>
					<td class="col-sm-6"><b><spring:message code="label.customerHeadEmail"/>:</b></td>
					<td class="col-sm-6">
					<form:input path="custHeadEmail" id="utilizedAmount" readonly="true"/>
					</td>
					</tr>
					<tr>
					<td class="col-sm-6"><b><spring:message code="label.customerBranchEmail"/>:</b></td>
					<td class="col-sm-6">
					<form:input path="email" id="utilizedAmount"   readonly="true"/>
					</td>
					</tr>
				<%-- 	<tr>
				
					<td class="col-sm-6"><b><spring:message code="label.balance"/> :</b></td>
					<td class="col-sm-6">
					<form:input path="busBalance" id="busBalance" style="width:85%" readonly="true" />
					</td>
					</tr> --%>
					
				</table>
			</div>
		
        <div class="col-sm-12 col-md-12 col-lg-12">
        <div class="col-sm-12 col-md-12 col-lg-12 header_customer">
           <h3 align="center"><spring:message code="label.suppDetails"/></h3>
           </div>
          <div><font color="red">${success}</font></div>
           	<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
                <thead>
	                <tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.supplierName"/></th>
						<th><spring:message code="label.companyName"/></th>
						<th><spring:message code="label.supplierBank"/></th>
						<th><spring:message code="label.contactNumber"/></th>
						<th><spring:message code="label.email"/></th>
						<th><spring:message code="label.status"/></th>
						<th><spring:message code="label.rating"/></th>
						<th><spring:message code="label.poList"/></th>
						<th><spring:message code="label.action"/></th>
					</tr>
				</thead>
			<tbody>
				 <c:if test="${! empty suppliers}">
		
			<c:forEach items="${suppliers}" var="supplier">  		
		    <tr>
				<td><c:out value="${supplier.id}"></c:out></td>
				<td><c:out value="${supplier.supplierName}"></c:out></td>
				<td><c:out value="${supplier.companyName}"></c:out></td>
				<td ><c:out value="${supplier.bank}"></c:out> </td>
				<td ><c:out value="${supplier.contactNum}"></c:out></td>
				<td ><c:out value="${supplier.email}"></c:out> </td>
				<td ><c:out value="${supplier.status}"></c:out></td>
				<td><div class="basic"	data-average="${supplier.rate}"></div></td>
				<td><a href="listofPoOnMasterKeyAndName?id=${fundsDistributeForm.id}" class="btn btn-primary"><spring:message code="label.view"/></a></td> 
				<td><a href="branchBusinessPlanForPo?id=${supplier.id}"class="btn btn-primary"><spring:message code="label.select"/></a></td> 
			</tr>
				 </c:forEach>
				</c:if>	
				 
			 </tbody>
           </table>
    
			 
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
        <div class="col-sm-12 col-md-12 col-lg-12 header_customer">
           <h3 align="center"><spring:message code="label.buyerDetails"/></h3>
           </div>
          <div><font color="red">${success}</font></div>
          <div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" class="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
           <table class="table data jqtable example my-table">
                <thead>
	                <tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.buyerName"/></th>
						<th><spring:message code="label.companyName"/></th>
						<th><spring:message code="label.buyerBank"/></th>
						<th><spring:message code="label.contactNumber"/></th>
						<th><spring:message code="label.email"/></th>
						<th><spring:message code="label.status"/></th>
						<th><spring:message code="label.rating"/></th>
						<th><spring:message code="label.invoiceList"/></th>
						<th><spring:message code="label.action"/></th>
					</tr>
				</thead>
			<tbody>
				 <c:if test="${! empty buyers}">
		
			<c:forEach items="${buyers}" var="buyer">  		
		    <tr>
				<td><c:out value="${buyer.id}"></c:out></td>
				<td><c:out value="${buyer.buyerName}"></c:out></td>
				<td><c:out value="${buyer.companyName}"></c:out></td>
				<td ><c:out value="${buyer.bank}"></c:out> </td>
				<td ><c:out value="${buyer.contactNum}"></c:out></td>
				<td ><c:out value="${buyer.email}"></c:out> </td>
				<td ><c:out value="${buyer.status}"></c:out></td>
				<td><div class="basic"	data-average="${buyer.rate}"></div></td>
				<td><a href="listofInvoiceOnMasterKeyAndName?id=${fundsDistributeForm.id}" class="btn btn-primary"><spring:message code="label.view"/></a></td> 
				<td><a href="branchBusinessPlanForInvoice?id=${buyer.id}"class="btn btn-primary"><spring:message code="label.select"/></a></td> 
			</tr>
				 </c:forEach>
				</c:if>	
				 
			 </tbody>
            </table>
			</div>		
	</form:form>
</div>
<script>
		$(document).ready(function(){
	    	 $(".basic").jRating({    		
	    		 isDisabled :true
	    	 });
		});
</script>