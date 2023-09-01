<%@include file="taglib_includes.jsp"%>
		<div class="col-md-9 col-sm-9 col-lg-9 body_fixed">
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center"><spring:message code="label.transactionDetails"/></h3>
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
						<th><spring:message code="label.transactionId"/></th>
						<th><spring:message code="label.transactionType"/></th>
						<th><spring:message code="label.transactionStatus"/></th>
						
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty transactionPageList}">

						<c:forEach items="${transactionPageList}" var="transaction">
							<tr>
								<td><c:out value="${transaction.id}"></c:out></td>
								<td><c:out value="${transaction.transactionId}"></c:out></td>
								<td><c:out value="${transaction.transactionType}"></c:out></td>
								<td><c:out value="${transaction.transactionStatus}"></c:out></td>
								</tr>
							</c:forEach>
						</c:if>

					</tbody>
            </table>
         
        </div>


<script>
	
</script>
