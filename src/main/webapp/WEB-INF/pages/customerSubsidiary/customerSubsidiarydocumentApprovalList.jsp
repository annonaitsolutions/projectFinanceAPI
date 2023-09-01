<%@include file="taglib_includes.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<h3>Customer Subsidiary Document Details</h3>
			<div>

				<font color="red">${success } </font>
			</div>
			<table class="table table-hover jqtable table-responisive"
				id="bootstrap-table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Customer Name</th>
						<th>Type Of Document</th>
						<th>Reason For Upload</th>
						<th>File Name</th>
						<th>Uploaded Date</th>
						<th>Status</th>
						<th>Comment</th>
						</tr>
				</thead>
				<tbody>
					<c:if test="${! empty uploadDocumentList}">

						<c:forEach items="${uploadDocumentList}" var="upload">
							<tr>
								<td><c:out value="${upload.id}"></c:out></td>
								<td><c:out value="${upload.customerName}"></c:out></td>
								<td><c:out value="${upload.document}"></c:out></td>
								<td><c:out value="${upload.reason}"></c:out></td>
								<td><c:out value="${upload.fileName}"></c:out></td>
								<td><c:out value="${upload.uploadDate}"></c:out></td>
                                <td><c:out value="${upload.status}"></c:out></td>
								<td><c:out value="${upload.comment}"></c:out></td>

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