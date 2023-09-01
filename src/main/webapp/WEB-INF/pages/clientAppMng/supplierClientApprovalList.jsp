<%@include file="taglib_includes.jsp"%>

 <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

 		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3><spring:message code="label.suppDetails"/></h3>
		<div>
			<div class="col-sm-12 col-md-12 col-lg-12">
				<font color="red">${success } </font>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
			
			<span class="counter pull-right"></span>
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
						<th><spring:message code="label.supplierName"/></th>
						<th><spring:message code="label.companyName"/></th>
						<th><spring:message code="label.address"/></th>
						<th><spring:message code="label.country"/></th>
						<th><spring:message code="label.state"/></th>
						<th><spring:message code="label.contactNumber"/></th>
						<th><spring:message code="label.email"/></th>
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
								<td><c:out value="${supplier.address}"></c:out></td>
								<td><c:out value="${supplier.country}"></c:out></td>
								<td><c:out value="${supplier.state}"></c:out></td>
								<td><c:out value="${supplier.contactNum}"></c:out></td>
								<td><c:out value="${supplier.email}"></c:out></td>
								<td><c:out value="${supplier.cStatus}"></c:out></td>
								<td><c:out value="${supplier.comment}"></c:out></td>
                                 <td>&nbsp;<a href="supplierClientPageShowConfirmApproval?id=${supplier.id}"
									class="btn btn-primary"><spring:message code="label.select"/></a>
								</td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table>

		</div>
	</div>
</div>
</div>
<script>
	$(document).ready(function() {
		$(".basic").jRating({
			isDisabled : true
		});
	});
</script>