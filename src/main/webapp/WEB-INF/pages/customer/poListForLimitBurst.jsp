<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 align="center"><spring:message code="label.limitBurstList"/></h3>
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
						<th><spring:message code="label.customerHeadName"/></th>
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.supplierName"/></th>
						<th><spring:message code="label.poKey"/></th>
						<th><spring:message code="label.status"/></th>
						<th><spring:message code="label.amount"/></th>
						<th><spring:message code="label.limitBurst"/></th>
						
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty poList}">
						<c:forEach items="${poList}" var="master">
							<tr>
								<td><c:out value="${master.id}"></c:out></td>
								<td><c:out value="${master.customerHeadName}"></c:out>
								<td><c:out value="${master.customerName}"></c:out>
								<td><c:out value="${master.supplierName}"></c:out>
								<td><c:out value="${master.poKey}"></c:out>
								<td><c:out value="${master.status}"></c:out></td>
								<td><c:out value="${master.amount}"></c:out></td>

								<td><a href="poApplyLimit?id=${master.id}"
									class="btn btn-primary"><spring:message code="label.select"/></a></td>
									
								
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			
			</div>
</div>
