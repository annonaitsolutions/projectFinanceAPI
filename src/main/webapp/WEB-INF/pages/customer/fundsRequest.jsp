<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<section>	

	<div class="container">
	 <div  class="sucess"><b><font color="green">${success}</font></b></div>

<br>
			<br>
			<h3 align="middle"><spring:message code="label.requestForFunds"/></h3>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.masterKey"/></th>
						<th><spring:message code="label.status"/></th>
						<th><spring:message code="label.managerStatus"/></th>
						<th><spring:message code="label.Balance"/></th>
						<th><spring:message code="label.requestMoney"/></th>
						
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty masterList}">
						<c:forEach items="${masterList}" var="master">
							<tr>
								<td><c:out value="${master.id}"></c:out></td>
								<td><c:out value="${master.customer}"></c:out>
								<td><c:out value="${master.masterKey}"></c:out>
								<td><c:out value="${master.status}"></c:out></td>
								<td><c:out value="${master.managerStatus}"></c:out></td>
								<td><c:out value="${master.balance}"></c:out></td>
									<td><a href="askFundsRequest?id=${master.id}"
									class="btn btn-primary"><spring:message code="label.request"/></a></td>
									
								
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			

</div>

</section>