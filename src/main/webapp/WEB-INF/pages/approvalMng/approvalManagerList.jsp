<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<h3><spring:message code="label.approvalManagerDetails"/></h3>
			<div>
				<font color="red">${Success } </font>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.userName"/></th>
				         <th><spring:message code="label.contactNumber"/></th>
						<th><spring:message code="label.currentRole"/></th>
						<th><spring:message code="label.email"/></th>
						<th><spring:message code="label.notificationStatus"/></th>
					
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty userList}">
						<c:forEach items="${userList}" var="user">
							<tr>
								<td><c:out value="${user.id}"></c:out></td>
								<td><c:out value="${user.userName}"></c:out></td>
						        <td><c:out value="${user.contactNo}"></c:out></td>
								<td><c:out value="${user.currentRole}"></c:out></td>
								<td><c:out value="${user.email}"></c:out></td>
							    <td><c:out value="${user.notificationStatus}"></c:out></td>
                   
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table>

		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$('#bootstrap-table').bdt();
	});
</script>

</body>