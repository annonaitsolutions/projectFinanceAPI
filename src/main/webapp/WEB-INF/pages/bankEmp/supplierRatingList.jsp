<%@include file="taglib_includes.jsp"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align=center><spring:message code="label.supplierRatings"/></h3>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
			<font color="red">${success}</font>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
			
			<span class="counter pull-right"></span>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerName"/></th>
					    <th><spring:message code="label.supplierName"/></th>
						<th><spring:message code="label.supplierCompanyName"/></th>
						<th><spring:message code="label.supplierBank"/></th>
						<th><spring:message code="label.pincode"/></th>
				        <th><spring:message code="label.country"/></th>
					    <th><spring:message code="label.limit"/></th>
					    <th><spring:message code="label.rating"/></th>
                        <th><spring:message code="label.status"/></th>
						<th><spring:message code="label.comment"/></th>
						<th><spring:message code="label.action"/></th>
					

					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty supplierList}">

						<c:forEach items="${supplierList}" var="supplier">
							<tr>
								<td><c:out value="${supplier.id}"></c:out></td>

									<td><c:out value="${supplier.name}"></c:out></td>
						
								<td><c:out value="${supplier.supplierName}"></c:out></td>
								<td><c:out value="${supplier.companyName}"></c:out></td>
								<td><c:out value="${supplier.bank}"></c:out></td>
								<td><c:out value="${supplier.pinCode}"></c:out></td>
								<td><c:out value="${supplier.country}"></c:out></td>
								<td><c:out value="${supplier.custLimit}"></c:out></td>
                                 <td><c:out value="${supplier.rating}"></c:out></td>
                                <td><c:out value="${supplier.status}"></c:out></td>
								<td><c:out value="${supplier.comment}"></c:out></td>
								<td><a href="supplierRating?id=${supplier.id}"
									class="btn btn-primary"><spring:message code="label.rating"/></a></td>
							


								
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table>

		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$('#bootstrap-table').bdt();
	});
</script>

</body>