<%@include file="taglib_includes.jsp"%>
<div class="col-md-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3><spring:message code="label.sellerDifferentBankEventDetails"/></h3>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerName"/></th>


						<th><spring:message code="label.date"/></th>
						<th><spring:message code="label.firstEvent"/></th>
						<th><spring:message code="label.status"/></th>

						<th><spring:message code="label.action"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty sellerDiffBankEventList}">
						<c:forEach items="${sellerDiffBankEventList}" var="sellerdiffBank">
							<tr>
								<td><c:out value="${sellerdiffBank.id}"></c:out></td>
								<td><c:out value="${sellerdiffBank.customerName}"></c:out></td>
								<fmt:formatDate var='date1' value='${sellerdiffBank.date1}' pattern="dd/MM/yyyy" />             
						 <td><c:out value="${date1}"></c:out></td>
								<td><c:out value="${sellerdiffBank.event1}"></c:out></td>
								<td><c:out value="${sellerdiffBank.status}"></c:out></td>

								<td><a
									href="updatesellerBankDiffrentEvent?id=${sellerdiffBank.id}"
									class="btn btn-primary"><spring:message code="label.select"/></a></td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table>

		</div>
	</div>


<script>
	$(document).ready(function() {
		$('#bootstrap-table').bdt();
	});
</script>

</body>