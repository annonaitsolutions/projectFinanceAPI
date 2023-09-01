<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
   <script>
    $(document).ready( function () {
        $('#bootstrap-table').bdt();
       
    });
  </script>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
	 <div class="col-sm-12 col-md-12 col-lg-12 header_customer">
	 	<h3 style="text-align:center">Plan Summary</h3>
	 </div>
<form:form action="#" method="post" commandName="fundsDistributeForm">
			
			

	
		<div class="col-sm-12 col-md-12 col-lg-12">
		<form:hidden path="id" id="id"/>
		            <div class="col-sm-3 heading_text"><b>MasterKey:</b></div>
					<div class="col-sm-7">
					<form:input path="masterKey" id="masterKey" style="width:85%" readonly="true"/>
					</div>
					 <div class="col-sm-3 heading_text"><b>Customer Head Name:</b></div>
					<div class="col-sm-7">
					<form:input path="customerHeadName" id="customerHeadName" style="width:85%" readonly="true"/>
					</div>
				     <div class="col-sm-3 heading_text"><b>Customer sName:</b></div>
					<div class="col-sm-7">
					<form:input path="customerName" id="customerName" style="width:85%" readonly="true"/>
					</div>
		
					<div class="col-sm-3 heading_text"><b>Dustributed Amount:</b></div>
					<div class="col-sm-7">
					<form:input path="distributedAmount" id="distributedAmount" style="width:85%" readonly="true"/>
					</div>
				
					<div class="col-sm-3  heading_text"><b>Utilized Amount</b></div>
					<div class="col-sm-7">
					<form:input path="utilizedAmount" id="utilizedAmount"  style="width:85%" readonly="true"/>
					</div>
					<div class="col-sm-3  heading_text"><b>Customer Head Email</b></div>
					<div class="col-sm-7">
					<form:input path="custHeadEmail" id="utilizedAmount"  style="width:85%" readonly="true"/>
					</div>
					<div class="col-sm-3  heading_text"><b>Customer Branch Email</b></div>
					<div class="col-sm-7">
					<form:input path="email" id="utilizedAmount"  style="width:85%" readonly="true"/>
					</div>
				
					<div class="col-sm-3 heading_text"><b>Balance:</b></div>
					<div class="col-sm-7">
					<form:input path="busBalance" id="busBalance" style="width:85%" readonly="true" />
					</div>
				
			</div>
			
        <div class="col-md-12 col-sm-12 col-lg-12">
         <div class="col-sm-12 col-md-12 col-lg-12 header_customer">
         	 	<h3 align="center">Approved Seller Details</h3>
          </div>
          <div class="col-sm-12 col-md-12 col-lg-12">
          		<div><font color="red">${success}</font></div>
          </div>
            <table class="table" id="bootstrap-table">
                <thead>
	                <tr>
						<th>ID</th>
						<th> Name</th>
						<th>Company</th>
						<th>Bank</th>
						<th>Contact No</th>
						<th>Email</th>
						<th>Status</th>
						<th>PO List</th>
						<th>Action</th>
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
				<td><a href="listSubsPoOnMasterKeyAndName?id=${fundsDistributeForm.id}" class="btn btn-primary">View</a></td> 
				<td><a href="subsBusinessPlanForPo?id=${supplier.id}"class="btn btn-primary">Select</a></td> 
			</tr>
				 </c:forEach>
				</c:if>	
				 
			 </tbody>
           </table>
    
			 
		</div>
		<div class="col-sm-12">
           <h3 align="center" style="font-size: 22px; font-weight: 400;margin-top: -6px; color: #04ADF1;">Approved Buyer Details</h3>
          <div><font color="red">${success}</font></div>
            <table class="table" id="bootstrap-table">
                <thead>
	                <tr>
						<th>ID</th>
						<th> Name</th>
						<th>Company</th>
						<th>Bank</th>
						<th>Contact No</th>
						<th>Email</th>
						<th>Status</th>
						<th>Invoice List</th>
						<th>Action</th>
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
				<td><a href="listSubsInvoiceOnMasterKeyAndName?id=${fundsDistributeForm.id}" class="btn btn-primary">View</a></td> 
				<td><a href="subsBusinessPlanForInvoice?id=${buyer.id}"class="btn btn-primary">Select</a></td> 
			</tr>
				 </c:forEach>
				</c:if>	
				 
			 </tbody>
            </table>
			</div>		
	</form:form>
</div>