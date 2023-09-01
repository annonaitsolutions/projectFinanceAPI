<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<section>	

	<div class="container">
	 <div  class="sucess"><b><font color="green">${success}</font></b></div>

<br>
			<br>
			<h3 align="middle"><spring:message code="label.halfYearlyPayment"/></h3>
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
						<th><spring:message code="label.amountType"/></th>
						<th><spring:message code="label.installmentOne"/></th>
						<th><spring:message code="label.payDate"/></th>
						<th><spring:message code="label.installmentTwo"/></th>
						<th><spring:message code="label.payDate"/></th>
						
						
					

						
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty full}">
						<c:forEach items="${full}" var="master">
							<tr>
								<td><c:out value="${master.id}"></c:out></td>
								<td><c:out value="${master.customer}"></c:out>
								<td><c:out value="${master.payOption}"></c:out>
								<td><c:out value="${master.status}"></c:out></td>
								<td><c:out value="${master.amtType}"></c:out></td>
								<td><c:out value="${master.amount}"></c:out></td>
								<td><c:out value="${master.payDate}"></c:out></td>
								<td><c:out value="${master.amount1}"></c:out></td>
								<td><c:out value="${master.payDate1}"></c:out></td>
								
								
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			

</div>

</section>