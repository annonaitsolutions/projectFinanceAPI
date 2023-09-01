<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 body_fixed">
			
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center"><spring:message code="label.reports"/></h3>
		</div>
		
		<div class="col-sm-12 col-md-12 col-lg-12">
			 <div  class="successMsg"><b><font color="green">${success}</font></b></div>
	 	</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.buyerName"/></th>
						<th><spring:message code="label.invoiceKey"/></th>
						<th><spring:message code="label.accept"/></th>
						<th><spring:message code="label.view"/></th>
						<th><spring:message code="label.compare"/></th>
						
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty disputeList}">
						<c:forEach items="${disputeList}" var="master">
							<tr>
								<td><c:out value="${master.id}"></c:out></td>
								<td><c:out value="${master.customerName}"></c:out>
								<td><c:out value="${master.buyerName}"></c:out>
								<td><c:out value="${master.invoicekey}"></c:out>
								<td><c:out value="${master.accept}"></c:out>
								<td><a href="addOrModifyReportInvoiceBankView?id=${master.id}" class="btn btn-primary" onclick="return confirm();"><spring:message code="label.view"/></a></td>
							<td><a href="addOrModifyReportInvoiceBankCompare?id=${master.id}" class="btn btn-primary" onclick="return confirm();"><spring:message code="label.compare"/></a></td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
		</div>

</div>
<script type="text/javascript">
function confirm(){
	alert(hii);
	  if (confirm("Are you sure you want to Close?")){
		  alert("hii1")
	    return true;
	  }else{
	    return false;
	  }
	  alert("hii3")
	}
</script>