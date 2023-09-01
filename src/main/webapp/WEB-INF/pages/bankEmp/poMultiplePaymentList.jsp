<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


 <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
 	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
 		<h3 align="center"><spring:message code="label.poPaymentList"/></h3>
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
						<th><spring:message code="label.transactionId"/></th>
						<th><spring:message code="label.customerHeadName"/></th>
						<th><spring:message code="label.customerName"/></th>												
						<th><spring:message code="label.status"/></th>						
						<th><spring:message code="label.typeOfTransfer"/></th>
						<th><spring:message code="label.moneyClearance"/></th>
						<th><spring:message code="label.status"/></th>
						<th><spring:message code="label.view"/></th>
						<th><spring:message code="label.clear"/></th>
						<th><spring:message code="label.reject"/></th>

						
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty purchaseList}">
						<c:forEach items="${purchaseList}" var="master">
							<tr>
								<td><c:out value="${master.transactionId}"></c:out></td>
								<td><c:out value="${master.customerHeadName}"></c:out>
								<td><c:out value="${master.customerName}"></c:out>								
								<td><c:out value="${master.status}"></c:out></td>
								<td><c:out value="${master.typeOfTrans}"></c:out></td>
								<td><c:out value="${master.transResult}"></c:out></td>
								<td><c:out value="${master.transStatus}"></c:out></td>
								<td><a href="poMultiplePaymentView?id=${master.transactionId}"
									class="btn btn-primary"><spring:message code="label.view"/></a></td>
									<td><a href="poMultiplePaymentClearFunds?id=${master.transactionId}"
									class="btn btn-primary"><spring:message code="label.clear"/></a></td>
									<td><a href="poMultiplePaymentRejectFunds?id=${master.transactionId}"
									class="btn btn-primary"><spring:message code="label.reject"/></a></td>
								
								
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
		</div>	

</div>
