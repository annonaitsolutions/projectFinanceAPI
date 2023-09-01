<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<section>	

	<div class="container">
	 <div  class="sucess"><b><font color="green">${success}</font></b></div>

<br>
			<br>
			<h3 align="middle">Purchase Order List</h3>
			<table class="table table-hover jqtable" id="bootstrap-table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Customer Name</th>
						<th>Supplier Name</th>
						<th>Master Key</th>
						<th>Status</th>
						<th>Purchase Date</th>
						<th>Amount</th>
						<th>Goods</th>
						<th>Quantity</th>
						
						
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty poList}">
						<c:forEach items="${poList}" var="master">
							<tr>
								<td><c:out value="${master.id}"></c:out></td>
								<td><c:out value="${master.customerName}"></c:out>
								<td><c:out value="${master.supplierName}"></c:out>
								<td><c:out value="${master.masterKey}"></c:out></td>
								<td><c:out value="${master.status}"></c:out></td>
								<fmt:formatDate var='purchaseDate' value='${master.purchaseDate}' pattern="dd/MM/yyyy" />
								<td><c:out value="${purchaseDate}"></c:out></td>
								<td><c:out value="${master.amount}"></c:out></td>
								<td><c:out value="${master.goods}"></c:out></td>
								<td><c:out value="${master.quantity}"></c:out></td>
								
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			

</div>

</section>