<%@include file="taglib_includes.jsp"%>
<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3><spring:message code="label.wareHouse"/></h3>
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
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.personInChr"/></th>
						<th><spring:message code="label.whName"/></th>
						<th><spring:message code="label.address"/></th>
						<th><spring:message code="label.pincode"/></th>
						<th><spring:message code="label.country"/></th>
						<th><spring:message code="label.state"/></th>
						<th><spring:message code="label.action"/></th>



					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty whList}">

						<c:forEach items="${whList}" var="buyer">
							<tr>
								<td><c:out value="${buyer.id}"></c:out></td>
								<td><c:out value="${buyer.customerName}"></c:out></td>
								<td><c:out value="${buyer.personInCharge}"></c:out></td>
								<td><c:out value="${buyer.wareHouseName}"></c:out></td>
								<td><c:out value="${buyer.address}"></c:out></td>
								<td><c:out value="${buyer.pinCode}"></c:out></td>
								<td><c:out value="${buyer.country}"></c:out></td>
								<td><c:out value="${buyer.state}"></c:out></td>
								<td><a href="custWareHouseApproval?id=${buyer.id}"
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
