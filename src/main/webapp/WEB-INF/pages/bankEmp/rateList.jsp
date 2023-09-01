<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<section>	

	<div class="container">
	 <div  class="sucess"><b><font color="green">${success}</font></b></div>

<br>
			<br>
			<h3 align="middle"><spring:message code="label.purchaseOrderList"/></h3>
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
						<th><spring:message code="label.status"/></th>
						<th><spring:message code="label.purchaseDate"/></th>
						<th><spring:message code="label.amount"/></th>
						<th><spring:message code="label.goods"/></th>
						<th><spring:message code="label.quantity"/></th>
						<th><spring:message code="label.rateStatus"/></th>
						<th><spring:message code="label.requestSent"/></th>
						<th><spring:message code="label.action"/></th>
					
						
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
								<td><c:out value="${master.rStatus}"></c:out></td>
								<td><c:out value="${master.request}"></c:out></td>
								<td><a href="getRateList?id=${master.id}"class="btn btn-primary"><spring:message code="label.select"/></a></td> 
								
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			

</div>

</section>