<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3><spring:message code="label.inventoryList"/></h3>
	</div>
	<div class="col-sm-12 col-md-12 col-lg-12">
		 <div  class="sucess"><b><font color="green">${success}</font></b></div>
	</div>
				<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.supplierName"/></th>
					    <th><spring:message code="label.masterKey"/></th>
						<th><spring:message code="label.poKey"/></th>
						 <th><spring:message code="label.amount"/></th>
						<th><spring:message code="label.totalQuantity"/></th>
						<th><spring:message code="label.usedQuantity"/></th>
						<th><spring:message code="label.damagedQuantity"/></th>
						<th><spring:message code="label.quantityRemaining"/></th>
					
						<th><spring:message code="label.goods"/></th>
				
				
						
						
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty inventoryList}">
						<c:forEach items="${inventoryList}" var="inventory">
							<tr>
								<td><c:out value="${inventory.id}"></c:out></td>
								<td><c:out value="${inventory.customerName}"></c:out>
								<td><c:out value="${inventory.supplierName}"></c:out>
								<td><c:out value="${inventory.masterKey}"></c:out></td>
								<td><c:out value="${inventory.poKey}"></c:out></td>
								<td><c:out value="${inventory.amount}"></c:out></td>
								<td><c:out value="${inventory.quantity}"></c:out></td>
								<td><c:out value="${inventory.usedQuantity}"></c:out></td>
								<td><c:out value="${inventory.damaged}"></c:out></td>
								<td><c:out value="${inventory.total}"></c:out></td>
							
								<td><c:out value="${inventory.quantity}"></c:out></td>
					
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			

</div>