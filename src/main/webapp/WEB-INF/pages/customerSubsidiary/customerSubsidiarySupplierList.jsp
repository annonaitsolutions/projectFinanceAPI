<%@include file="taglib_includes.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<h3>supplier Details</h3>
			<div>

				<font color="red">${success } </font>
			</div>
			<table class="table table-hover jqtable table-responisive"
				id="bootstrap-table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Customer</th>
						<th>supplier Name</th>
						<th>supplier Company Name</th>
						
						<th>Address</th>
						<th>Pincode</th>
						<th>Country</th>
						<th>State</th>
						<th>Contact Number</th>
						<th>Email</th>
						<th>Status</th>
						<th>Comment</th>


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

								<td><c:out value="${supplier.pinCode}"></c:out></td>
								<td><c:out value="${supplier.country}"></c:out></td>
								<td><c:out value="${supplier.state}"></c:out></td>

								<td><c:out value="${supplier.contactNum}"></c:out></td>

								<td><c:out value="${supplier.email}"></c:out></td>


								<td><c:out value="${supplier.status}"></c:out></td>
								<td><c:out value="${supplier.comment}"></c:out></td>

									
								</tr>
							</c:forEach>
						</c:if>

					</tbody>
            </table>
         
        </div>
    </div>
</div>

<script>
    $(document).ready( function () {
        $('#bootstrap-table').bdt();
    });
</script>

</body>