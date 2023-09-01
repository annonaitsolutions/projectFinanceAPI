<%@include file="taglib_includes.jsp"%>

<div class="col-md-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.buyerDiffBankDet"/></h3>
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

						<th><spring:message code="label.date"/></th>
						<th><spring:message code="label.firstEvent"/></th>
						<th><spring:message code="label.status"/></th>

						<th><spring:message code="label.action"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty buyerDiffBankEventList}">
						<c:forEach items="${buyerDiffBankEventList}" var="eventdiff">
							<tr>
								<td><c:out value="${eventdiff.id}"></c:out></td>
								<td><c:out value="${eventdiff.customerName}"></c:out></td>
								 <fmt:formatDate var='date1' value='${eventdiff.date1}' pattern="dd/MM/yyyy" />             
						 <td><c:out value="${date1}"></c:out></td>
								<td><c:out value="${eventdiff.event1}"></c:out></td>
								<td><c:out value="${eventdiff.status}"></c:out></td>

								<td><a
									href="updatediffrentBankEvent?id=${eventdiff.id}"
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
