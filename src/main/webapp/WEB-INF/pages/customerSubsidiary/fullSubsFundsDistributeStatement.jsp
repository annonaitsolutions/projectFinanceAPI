<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<section>	

	<div class="container">
	 <div  class="successMsg"><b><font color="green">${success}</font></b></div>

<br>
			<br>
			<h3 align="middle">Full Statement</h3>
			<table class="table table-hover jqtable" id="bootstrap-table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Customer Head Name</th>
						<th>Customer Name</th>
						<th>Date</th>
						<th>Amount Transfered</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty statement}">
						<c:forEach items="${statement}" var="drafts">
							<tr>
								<td><c:out value="${drafts.id}"></c:out></td>
								<td><c:out value="${drafts.customerHeadName}"></c:out>
								<td><c:out value="${drafts.customerName}"></c:out>
								<fmt:formatDate var='fundsDate' value='${drafts.fundsDate}' pattern="dd/MM/yyyy" />
								<td><c:out value="${fundsDate}"></c:out></td>
								<td><c:out value="${drafts.amount}"></c:out>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			

</div>

</section>