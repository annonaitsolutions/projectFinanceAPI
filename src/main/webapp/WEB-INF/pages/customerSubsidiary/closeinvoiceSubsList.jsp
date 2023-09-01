<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 body_fixed">
			
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center">Invoice List</h3>
		</div>
		
		<div class="col-sm-12 col-md-12 col-lg-12">
			 <div  class="successMsg"><b><font color="green">${success}</font></b></div>
	 	</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
			<table class="table table-hover jqtable" id="bootstrap-table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Customer Name</th>
						<th>Buyer Name</th>
						<th>Invoice Key</th>
						<th>Goods</th>
						<th>Quantity</th>
						<th>Request Money</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty invoiceList}">
						<c:forEach items="${invoiceList}" var="master">
							<tr>
								<td><c:out value="${master.id}"></c:out></td>
								<td><c:out value="${master.customerName}"></c:out>
								<td><c:out value="${master.buyerName}"></c:out>
								<td><c:out value="${master.poKey}"></c:out>
								<td><c:out value="${master.goods}"></c:out>
								<td><c:out value="${master.quantity}"></c:out>
								<td><c:out value="${master.requestMoney}"></c:out>
								<td><a href="closeInvoiceSubs?id=${master.id}"
									class="btn btn-primary" onclick="return confirm();">Close</a></td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
		</div>

</div>
<script type="text/javascript">
function confirm(){
	  if (confirm("Are you sure you want to Close?")){
	    return true;
	  }else{
	    return false;
	  }
	}
</script>