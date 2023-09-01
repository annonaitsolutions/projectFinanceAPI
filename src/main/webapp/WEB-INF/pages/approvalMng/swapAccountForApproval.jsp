<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<div class="col-sm-9 col-md-9 body_fixed">

	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3 align="center"><spring:message code="label.swapRevertAccountApproval"/></h3>
	</div>

	<div class="col-sm-12 col-md-12 col-lg-12">
		<div class="successMsg">
			<b><font color="green">${success}</font></b>
		</div>
	</div>
	<div class="col-sm-12 col-md-12 col-lg-12">
		<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
			<thead>
				<tr>
					<th><spring:message code="label.id"/></th>
					<th><spring:message code="label.existingUser"/></th>
					<th><spring:message code="label.existingEmail"/></th>
					<th><spring:message code="label.newUser"/></th>
					<th><spring:message code="label.newEmail"/></th>
					<th><spring:message code="label.action"/></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${! empty swapAccountList}">
					<c:forEach items="${swapAccountList}" var="user">
						<tr>
							<td><c:out value="${user.id}"></c:out></td>
							<td><c:out value="${user.oldUser}"></c:out></td>
							<td><c:out value="${user.oldEmail}"></c:out></td>
							<td><c:out value="${user.newUser}"></c:out></td>
							<td><c:out value="${user.newEmail}"></c:out></td>
							<td><a href="approveRejectSwapping?id=${user.id}"
								   class="btn btn-primary"><spring:message code="label.approveReject"/></a></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
</div>
