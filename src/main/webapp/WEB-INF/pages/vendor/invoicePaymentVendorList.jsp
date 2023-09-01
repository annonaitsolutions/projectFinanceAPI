<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3><spring:message code="label.invoice"/></h3>
	</div>
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
	 	<div  class="sucess"><b><font color="green">${success}</font></b></div>
	</div>
   <form:form action="#" name="msplan" method="post" commandName="invoiceForm">
	<div class="col-sm-12 col-md-12 col-lg-12">
	<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
</div>
		<table class="table table-hover data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.buyerName"/></th>
						<th><spring:message code="label.masterKey"/></th>
						<th><spring:message code="label.invoiceKey"/></th>
						<th><spring:message code="label.status"/></th>
						<th><spring:message code="label.amount"/></th>
						<th><spring:message code="label.goods"/></th>
						<th><spring:message code="label.quantity"/></th>
						<th><spring:message code="label.action"/></th>
						
						
						
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty invoiceList}">
						<c:forEach items="${invoiceList}" var="master">
							<tr>
								<td><c:out value="${master.id}"></c:out></td>
								<td><c:out value="${master.customerName}"></c:out>
								<td><c:out value="${master.buyerName}"></c:out>
								<td><c:out value="${master.masterKey}"></c:out></td>
								<td><c:out value="${master.poKey}"></c:out></td>
								<td><c:out value="${master.poKey}"></c:out></td>
								<td><c:out value="${master.amount}"></c:out></td>
								<td><c:out value="${master.goods}"></c:out></td>
								<td><c:out value="${master.quantity}"></c:out></td>
								<td><a href="invoicePaymentVendor?id=${master.id}"
									class="btn btn-primary"><spring:message code="label.makePayment"/></a></td>
						
						
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			


</form:form>
</div>