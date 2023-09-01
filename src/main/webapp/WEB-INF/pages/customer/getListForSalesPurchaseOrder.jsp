<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 body_fixed">			
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center"><spring:message code="label.listForBusinessPlan"/></h3>

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
						<th><spring:message code="label.status"/></th>
						<th><spring:message code="label.managerStatus"/></th>
						<th><spring:message code="label.amountSanctioned"/></th>
						<th><spring:message code="label.amountDistributed"/></th>
						<th><spring:message code="label.balance"/></th>
						<th><spring:message code="label.distributionDetails"/></th>
						<c:if test="${!model.salesOrder}">
							<th><spring:message code="label.createPurchaseOrder"/></th>
						</c:if>
						<c:if test="${model.salesOrder}">
							<th><spring:message code="label.createSalesOrder"/></th>
						</c:if>


					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty plan}">
						<c:forEach items="${plan}" var="master">
							<tr>
								<td><c:out value="${master.id}"></c:out></td>
								<td><c:out value="${master.customer}"></c:out>
								<td><c:out value="${master.masterKey}"></c:out>
								<td><c:out value="${master.status}"></c:out></td>
								<td><c:out value="${master.managerStatus}"></c:out></td>
								<td><c:out value="${master.buyingCostSanc}"></c:out></td>
								<td><c:out value="${master.distributedAmt}"></c:out></td>
								<td><c:out value="${master.balance}"></c:out></td>
								<td><a href="distributionFullDetails?id=${master.id}"
									class="btn btn-primary"><spring:message code="label.view"/></a></td>
									<td><a href="createSalesPurchaseOrder?id=${master.id}&&productType=${model.productType}"
									class="btn btn-primary"><spring:message code="label.create"/></a></td>
							
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			

</div>
