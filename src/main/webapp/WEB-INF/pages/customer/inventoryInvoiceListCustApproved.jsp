<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.invoiceInventoryList"/></h3>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div  class="sucess"><b><font color="green">${success}</font></b></div>
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
						<th><spring:message code="label.masterKey"/></th>
						<th><spring:message code="label.poKey"/></th>
						 <th><spring:message code="label.amount"/></th>
						<th> <spring:message code="label.totalQuantity"/></th>
						<th> <spring:message code="label.usedQuantity"/></th>
						<th><spring:message code="label.damagedQuantity"/></th>
						<th><spring:message code="label.quantityRemaining"/></th>
					
						<th><spring:message code="label.amount"/></th>
						<th><spring:message code="label.goods"/></th>
				        <th><spring:message code="label.inventoryType"/></th>
				
						
						
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty inventoryInvoiceList}">
						<c:forEach items="${inventoryInvoiceList}" var="inventoryInvoice">
							<tr>
								<td><c:out value="${inventoryInvoice.id}"></c:out></td>
								<td><c:out value="${inventoryInvoice.customerName}"></c:out>
								<td><c:out value="${inventoryInvoice.buyerName}"></c:out>
								<td><c:out value="${inventoryInvoice.masterKey}"></c:out></td>
								<td><c:out value="${inventoryInvoice.poKey}"></c:out></td>
								<td><c:out value="${inventoryInvoice.amount}"></c:out></td>
								<td><c:out value="${inventoryInvoice.quantity}"></c:out></td>
								<td><c:out value="${inventoryInvoice.usedQuantity}"></c:out></td>
								<td><c:out value="${inventoryInvoice.damaged}"></c:out></td>
								<td><c:out value="${inventoryInvoice.total}"></c:out></td>
							    <td><c:out value="${inventoryInvoice.amount}"></c:out></td>
							    <td><c:out value="${inventoryInvoice.quantity}"></c:out></td>
								<td><c:out value="${inventoryInvoice.inventoryType}"></c:out></td>
					
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			

</div>

</section>