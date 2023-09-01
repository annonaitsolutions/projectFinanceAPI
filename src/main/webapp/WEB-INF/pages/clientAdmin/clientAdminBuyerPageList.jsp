<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3><spring:message code="label.buyerDetails"/></h3>
	</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
			
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
						<th><spring:message code="label.buyerName"/></th>
						<th><spring:message code="label.buyerCompanyName"/></th>
						
						<th><spring:message code="label.address"/></th>
						<th><spring:message code="label.pincode"/></th>
						<th><spring:message code="label.country"/></th>
						<th><spring:message code="label.state"/></th>

						<th><spring:message code="label.contactNumber"/></th>

						<th><spring:message code="label.email"/></th>

					

						<th><spring:message code="label.status"/></th>
						<th><spring:message code="label.comment"/></th>


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
