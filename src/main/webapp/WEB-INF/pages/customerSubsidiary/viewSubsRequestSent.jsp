<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
			<br>
			<h3 align="middle">Distribute Funds</h3>
			<table class="table table-hover jqtable" id="bootstrap-table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Customer Name</th>
						<th>Date</th>
						<th>Master Key</th>
						<th>Amount</th>
                      </tr>
				</thead>
				<tbody>
					<c:if test="${! empty requestList}">
						<c:forEach items="${requestList}" var="funds">
							<tr>
								<td><c:out value="${funds.id}"></c:out></td>
								<td><c:out value="${funds.requestedFrom}"></c:out>
								<fmt:formatDate var='requestDate' value='${funds.requestDate}' pattern="dd/MM/yyyy" />
								<td><c:out value="${requestDate}"></c:out></td>
								<td><c:out value="${funds.masterKey}"></c:out>
								<td><c:out value="${funds.amount}"></c:out></td>
								
							</tr>
						</c:forEach>
					</c:if>
					
				</tbody>
			</table> 
			</div>