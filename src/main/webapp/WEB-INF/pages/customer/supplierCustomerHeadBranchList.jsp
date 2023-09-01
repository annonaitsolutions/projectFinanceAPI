<%@include file="taglib_includes.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<h3>Supplier Details</h3>
			<div>

				<font color="red">${success } </font>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customer"/></th>
						<th><spring:message code="label.supplierName"/></th>
						<th><spring:message code="label.supplierCompanyName"/></th>
						<th><spring:message code="label.currencyDealt"/></th>
						<th><spring:message code="label.Address"/></th>
						<th><spring:message code="label.Pincode"/></th>
						<th><spring:message code="label.Country"/></th>
						<th><spring:message code="label.State"/></th>
						<th><spring:message code="label.contactNumber"/></th>
						<th><spring:message code="label.email"/></th>
						<th><spring:message code="label.cStatus"/></th>
						<th><spring:message code="label.comment"/></th>
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
								<td><c:out value="${supplier.currencydeal}"></c:out></td>
								<td><c:out value="${supplier.address}"></c:out></td>
								<td><c:out value="${supplier.pinCode}"></c:out></td>
								<td><c:out value="${supplier.country}"></c:out></td>
								<td><c:out value="${supplier.state}"></c:out></td>
								<td><c:out value="${supplier.contactNum}"></c:out></td>
								<td><c:out value="${supplier.email}"></c:out></td>
								<td><c:out value="${supplier.cStatus}"></c:out></td>
								<td><c:out value="${supplier.comment}"></c:out></td>
                                 <td>&nbsp;<a href="supplierPageShowConfirmApproval?id=${supplier.id}"
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

<script>
	$(document).ready(function() {
		$('#bootstrap-table').bdt();
	});
</script>

</body>