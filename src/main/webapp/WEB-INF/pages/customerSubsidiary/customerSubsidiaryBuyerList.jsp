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
						<th>Buyer Name</th>
						<th>Company Name</th>
						
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
					<c:if test="${! empty buyerList}">

						<c:forEach items="${buyerList}" var="buyer">
							<tr>
								<td><c:out value="${buyer.id}"></c:out></td>
								<td><c:out value="${buyer.name}"></c:out></td>
								<td><c:out value="${buyer.buyerName}"></c:out></td>
								<td><c:out value="${buyer.companyName}"></c:out></td>

								<td><c:out value="${buyer.address}"></c:out></td>

								<td><c:out value="${buyer.pinCode}"></c:out></td>
								<td><c:out value="${buyer.country}"></c:out></td>
								<td><c:out value="${buyer.state}"></c:out></td>

								<td><c:out value="${buyer.contactNum}"></c:out></td>

								<td><c:out value="${buyer.email}"></c:out></td>


								<td><c:out value="${buyer.status}"></c:out></td>
								<td><c:out value="${buyer.comment}"></c:out></td>

									
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