<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<section>	

	<div class="container">
	 <div  class="sucess"><b><font color="green">${success}</font></b></div>

<br>
			<br>
			<h3 align="middle">PO Payment List</h3>
			<table class="table table-hover jqtable" id="bootstrap-table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Customer Head Name</th>
						<th>Customer Name</th>
						<th>Supplier Name</th>
						<th>Master Key</th>
						<th>PO Key</th>
						<th>Status</th>
						<th>Amount</th>
						<th>Type Of Transacaction</th>
						<th>Money Clearence</th>
						<th>Status</th>
						<th>LC/BG</th>
						

						
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty purchaseList}">
						<c:forEach items="${purchaseList}" var="master">
							<tr>
								<td><c:out value="${master.id}"></c:out></td>
								<td><c:out value="${master.customerHeadName}"></c:out>
								<td><c:out value="${master.customerName}"></c:out>
								<td><c:out value="${master.supplierName}"></c:out>
								<td><c:out value="${master.masterKey}"></c:out>
								<td><c:out value="${master.poKey}"></c:out>
								<td><c:out value="${master.status}"></c:out></td>
								<td><c:out value="${master.amount}"></c:out></td>
								<td><c:out value="${master.typeOfTrans}"></c:out></td>
								<td><c:out value="${master.transResult}"></c:out></td>
								<td><c:out value="${master.transStatus}"></c:out></td>
								<td><a href="poPaymentSubsClear?id=${master.id}"
									class="btn btn-primary">View</a></td>
						
									
								
								
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			

</div>

</section>