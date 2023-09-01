<%@include file="taglib_includes.jsp"%>
<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.buyerDocsDetails"/></h3>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
				<font color="red">${success } </font>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.poKey"/></th>
						<th><spring:message code="label.docsType"/></th>
						<th><spring:message code="label.reasonForUpload"/></th>
						<th><spring:message code="label.fileName"/></th>
						<th><spring:message code="label.uploadDate"/></th>
			
						</tr>
				</thead>
				<tbody>
					<c:if test="${! empty docList}">

						<c:forEach items="${docList}" var="upload">
							<tr>
								<td><c:out value="${upload.id}"></c:out></td>
								<td><c:out value="${upload.customerName}"></c:out></td>
								<td><c:out value="${upload.poKey}"></c:out></td>
								<td><c:out value="${upload.document}"></c:out></td>
								<td><c:out value="${upload.reason}"></c:out></td>
								<td><c:out value="${upload.fileName}"></c:out></td>
								<td><c:out value="${upload.uploadDate}"></c:out></td>
                 

								</tr>
							</c:forEach>
						</c:if>

					</tbody>
            </table>
         
        </div>

<script>
    $(document).ready( function () {
        $('#bootstrap-table').bdt();
    });
</script>