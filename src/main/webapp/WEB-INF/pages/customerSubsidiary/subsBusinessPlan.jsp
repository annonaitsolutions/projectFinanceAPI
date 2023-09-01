<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
	 
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center">Create Business Plan</h3>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div  class="successMsg"><b><font color="green">${success}</font></b></div>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
			<table class="table table-hover jqtable" id="bootstrap-table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Customer Head Name</th>
						<th>Customer Name</th>
						<th>Master Key</th>
						<th>Utilized Amount</th>
						<th>Distributed Amount</th>
						<th>Balance</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty plan}">
						<c:forEach items="${plan}" var="master">
							<tr>
								<td><c:out value="${master.id}"></c:out></td>
								<td><c:out value="${master.customerHeadName}"></c:out>
								<td><c:out value="${master.customerName}"></c:out>
								<td><c:out value="${master.masterKey}"></c:out>
								<td><c:out value="${master.utilizedAmount}"></c:out></td>
								<td><c:out value="${master.distributedAmount}"></c:out></td>
								<td><c:out value="${master.busBalance}"></c:out></td>
                               <td><a href="createSubsBusinessPlan?id=${master.id}"
									class="btn btn-primary">Create</a></td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			</div>

</div>