<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<section>	

	<div class="container">
	 <div  class="sucess"><b><font color="green">${success}</font></b></div>

<br>
			<br>
			<h3 align="middle">Inventory List For Customer Subsidiary</h3>
			<table class="table table-hover jqtable" id="bootstrap-table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Customer Name</th>
						<th>Buyer Name</th>
						<th>Master Key</th>
						<th>PO Key</th>
						 <th>Amount</th>
						<th> Total Quantity</th>
						<th> Used Quantity</th>
						<th>Damaged Quantity</th>
						<th>Quantity Remaining</th>
					
						<th>Amount</th>
						<th>Goods</th>
				
				
						
						
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty inventoryList}">
						<c:forEach items="${inventoryList}" var="inventory">
							<tr>
								<td><c:out value="${inventory.id}"></c:out></td>
								<td><c:out value="${inventory.customerName}"></c:out>
								<td><c:out value="${inventory.buyerName}"></c:out>
								<td><c:out value="${inventory.masterKey}"></c:out></td>
								<td><c:out value="${inventory.poKey}"></c:out></td>
								<td><c:out value="${inventory.amount}"></c:out></td>
								<td><c:out value="${inventory.quantity}"></c:out></td>
								<td><c:out value="${inventory.usedQuantity}"></c:out></td>
								<td><c:out value="${inventory.damaged}"></c:out></td>
								<td><c:out value="${inventory.total}"></c:out></td>
							
						        <td><c:out value="${inventory.amount}"></c:out></td>
							
								<td><c:out value="${inventory.quantity}"></c:out></td>
					
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			

</div>

</section>