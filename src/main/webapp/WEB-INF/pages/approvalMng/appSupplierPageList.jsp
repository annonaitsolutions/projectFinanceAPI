<%@include file="taglib_includes.jsp"%>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3><spring:message code="label.supplierDetails"/></h3>
				</div>	
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<font color="red">
			${success}
			</font>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerName"/></th>					
						<th><spring:message code="label.supplierName"/></th>
						<th><spring:message code="label.supplierCompanyName"/></th>
						<th><spring:message code="label.supplierBank"/></th>
						<th><spring:message code="label.pincode"/></th>
				        <th><spring:message code="label.country"/></th>
					    <th><spring:message code="label.contactNumber"/></th>
					    <th><spring:message code="label.email"/></th>
						<th><spring:message code="label.status"/></th>
					

					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty SupplierList}">

						<c:forEach items="${SupplierList}" var="supplier">
							<tr>
								<td><c:out value="${supplier.id}"></c:out></td>

									<td><c:out value="${supplier.name}"></c:out></td>
							
								<td><c:out value="${supplier.supplierName}"></c:out></td>
								<td><c:out value="${supplier.companyName}"></c:out></td>
								<td><c:out value="${supplier.bank}"></c:out></td>
								<td><c:out value="${supplier.pinCode}"></c:out></td>
								<td><c:out value="${supplier.country}"></c:out></td>
								<td><c:out value="${supplier.contactNum}"></c:out></td>
                                 <td><c:out value="${supplier.email}"></c:out></td>
                                <td><c:out value="${supplier.status}"></c:out></td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table>

		</div>


