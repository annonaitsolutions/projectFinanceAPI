<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-md-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3><spring:message code="label.sellerSameBankEventDetails"/></h3>
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
					<c:if test="${! empty SellerSameBankEventList}">
						<c:forEach items="${SellerSameBankEventList}" var="sellersameBank">
							<tr>
								<td><c:out value="${sellersameBank.id}"></c:out></td>
								<td><c:out value="${sellersameBank.customerName}"></c:out></td>
								<td><c:out value="${sellersameBank.date1}"></c:out></td>
								<td><c:out value="${sellersameBank.event1}"></c:out></td>
								<td><c:out value="${sellersameBank.status}"></c:out></td>

								<td><a
									href="updateSellerSameBankEvent?id=${sellersameBank.id}"class="btn btn-primary"><spring:message code="label.select"/></a></td>
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