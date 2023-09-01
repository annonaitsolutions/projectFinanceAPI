<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 body_fixed">			
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center"><spring:message code="label.listOfSalesOrder"/></h3>
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
						<th><spring:message code="label.masterKey"/></th>
						<th><spring:message code="label.goodsCategory"/></th>
						<th><spring:message code="label.goods"/></th>
						<th><spring:message code="label.quantity"/></th>
						<th><spring:message code="label.amount"/></th>
						<th><spring:message code="label.tenure"/></th>
						<th><spring:message code="label.createPurchaseOrder"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty salesOrder}">
						<c:forEach items="${salesOrder}" var="salesOrder">
							<tr>
								<td><c:out value="${salesOrder.id}"></c:out></td>
								<td><c:out value="${salesOrder.customerName}"></c:out>
								<td><c:out value="${salesOrder.masterKey}"></c:out>
								<td><c:out value="${salesOrder.goodsCategory}"></c:out></td>
								<td><c:out value="${salesOrder.goods}"></c:out></td>
								<td><c:out value="${salesOrder.quantity}"></c:out></td>
								<td><c:out value="${salesOrder.amount}"></c:out></td>
								<td><c:out value="${salesOrder.tenure}"></c:out></td>
									<td><a href="creatPurchaseOrder?id=${salesOrder.id}"
									class="btn btn-primary"><spring:message code="label.create"/></a></td>
							
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			

</div>
