<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 body_fixed">
<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center"><spring:message code="label.masterPlanList"/></h3>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
	 			<div  class="successMsg"><b><font color="green">${success}</font></b></div>
			</div>
			<div class="form-group pull-right">
   		
			</div>
			<span class="counter pull-right"></span>
						<div class="col-sm-12 col-md-12 col-lg-12">
	<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
</div>
		<table class="table table-hover data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.masterplaneDate"/></th>
						<th><spring:message code="label.buyingCost"/></th>
						<th><spring:message code="label.tenure"/></th>
						<th><spring:message code="label.status"/></th>
						<th><spring:message code="label.action"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty masterPendingList}">
						<c:forEach items="${masterPendingList}" var="master">
							<tr>
								<td><c:out value="${master.id}"></c:out></td>
								<td><c:out value="${master.customer}"></c:out>
								<td><c:out value="${master.masterPlanDate}"></c:out>
								<td><c:out value="${master.buyingCost}"></c:out>
								<td><c:out value="${master.tenure}"></c:out></td>
								<td><c:out value="${master.status}"></c:out></td>



								<td><a href="tcollateral?id=${master.id}"
									class="btn btn-primary"><spring:message code="label.addColl"/></a></td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			

</div>
