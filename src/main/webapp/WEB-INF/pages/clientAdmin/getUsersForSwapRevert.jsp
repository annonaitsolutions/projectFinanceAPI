<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<div class="col-sm-9 col-md-9 body_fixed">

	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3 align="center"><spring:message code="label.swapRevertAccount"/></h3>
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
					<th><spring:message code="label.userName"/></th>
					<th><spring:message code="label.role"/></th>
					<th><spring:message code="label.expiryDate"/></th>
					<th><spring:message code="label.action"/></th>
					<th><spring:message code="label.action"/></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${! empty userList}">
					<c:forEach items="${userList}" var="user">

						<tr>
							<td><c:out value="${user.id}"></c:out></td>
							<td><c:out value="${user.userName}"></c:out></td>
							<td><c:out value="${user.currentRole}"></c:out></td>
							<fmt:formatDate value="${user.accExpiryDate}"
								pattern="MM/dd/yyyy" var="formattedDate" />
							<td><c:out value="${formattedDate}"></c:out></td>
							<%-- <c:choose>
							<c:when test="${! empty  swapAccountList}">
								<c:forEach items="${swapAccountList}" var="swapUser">
									<td><c:choose>
											<c:when   test="${user.id == swapUser.endUserId && 
												      (user.status == 'Pending' || empty swapUser.newUser)}">
												<a href="#" class="btn btn-primary" disabled="true">Swap</a>
											</c:when>
											<c:otherwise>
												<a href="swapAccount?id=${user.id}" class="btn btn-primary">Swap</a>
											</c:otherwise>
										</c:choose></td>
									<td><c:choose>
											<c:when   test="${user.id == swapUser.endUserId && 
												      (user.status == 'Pending' || !empty swapUser.newUser)}">
												<a href="#" class="btn btn-primary" disabled="true">Revert</a>
											</c:when>
											<c:otherwise>
												<a href="revertSwapping?id=${user.id}" class="btn btn-primary">Revert</a>
											</c:otherwise>
										</c:choose></td>
								</c:forEach>
							</c:when>
								<c:otherwise>
									<td><a href="swapAccount?id=${user.id}" class="btn btn-primary">Swap</a></td>
									<td><a href="revertSwapping?id=${user.id}" class="btn btn-primary">Revert</a></td>
								</c:otherwise>
							</c:choose> --%>
							<td><a href="swapAccount?id=${user.id}" class="btn btn-primary"><spring:message code="label.swap"/></a></td>
							<td><a href="revertAccount?id=${user.id}" class="btn btn-primary"><spring:message code="label.revert"/></a></td>
						</tr>
					</c:forEach>

				</c:if>
			</tbody>
		</table>
	</div>
</div>
