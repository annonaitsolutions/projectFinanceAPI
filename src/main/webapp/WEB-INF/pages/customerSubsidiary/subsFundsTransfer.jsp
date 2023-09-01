<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<section>	

	<div class="container">
	 <div  class="sucess"><b><font color="green">${success}</font></b></div>

<br>
			<br>
			<h3 align="middle">Request For Funds</h3>
			<table class="table table-hover jqtable" id="bootstrap-table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Customer Name</th>
						<th>Master Key</th>
						<th>Distributed Amount</th>
						<th>Utilized Amount</th>
						<th>Balance</th>
						<th>Request For Money</th>
						
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty masterList}">
						<c:forEach items="${masterList}" var="master">
							<tr>
								<td><c:out value="${master.id}"></c:out></td>
								<td><c:out value="${master.customerName}"></c:out>
								<td><c:out value="${master.masterKey}"></c:out>
								<td><c:out value="${master.distributedAmount}"></c:out></td>
								<td><c:out value="${master.utilizedAmount}"></c:out></td>
								<td><c:out value="${master.busBalance}"></c:out></td>
									<td><a href="subsFundsTransfer1?id=${master.id}"
									class="btn btn-primary">Transfer</a></td>
									
								
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			

</div>

</section>