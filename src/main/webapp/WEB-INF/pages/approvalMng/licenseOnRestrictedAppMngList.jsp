<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

			 <div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.listOfLicenseUpdate"/></h3>
			 <div class="col-sm-12 col-md-12 col-lg-12">
				<font color="red">${Success } </font>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
				<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerName"/></th>
				         <th><spring:message code="label.licenseDetails"/></th>
						<th><spring:message code="label.startDate"/></th>
						<th><spring:message code="label.endDate"/></th>
						<th><spring:message code="label.goodsName"/></th>
						<th><spring:message code="label.status"/></th>
						<th><spring:message code="label.quantity"/></th>
					
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty license}">
						<c:forEach items="${license}" var="user">
							<tr>
								<td><c:out value="${user.id}"></c:out></td>
								<td><c:out value="${user.customer}"></c:out></td>
						        <td><c:out value="${user.licenseDetails}"></c:out></td>
								<fmt:formatDate var='startDate' value='${user.startDate}' pattern="dd/MM/yyyy" />
								<td><c:out value="${startDate}"></c:out></td>
									<fmt:formatDate var='endDate' value='${user.endDate}' pattern="dd/MM/yyyy" />
								<td><c:out value="${endDate}"></c:out></td>
								<td><c:out value="${user.goodsName}"></c:out></td>
								<td><c:out value="${user.status}"></c:out></td>
							    <td><c:out value="${user.qty}"></c:out></td>
							   
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table>

		</div>
	</div>

