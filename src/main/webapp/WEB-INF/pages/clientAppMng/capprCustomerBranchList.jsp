<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

 <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

 		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
 					<h3 align="center"><spring:message code="label.custDetail"/></h3>
 		</div>
	 	<div  class="successMsg"><b><font color="green">${success}</font></b></div>

		<span class="counter pull-right"></span>
		<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.customerPrefix"/></th>
						<th><spring:message code="label.customerHeadName"/></th>
						<th><spring:message code="label.address"/></th>
						<th><spring:message code="label.status"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty customerList}">
						<c:forEach items="${customerList}" var="customer">
							<tr>
								<td><c:out value="${customer.id}"></c:out></td>
								<td><c:out value="${customer.name}"></c:out>
								<td><c:out value="${customer.customerPrefix}"></c:out>
								<td><c:out value="${customer.customerHeadName}"></c:out></td>
								<td><c:out value="${customer.address}"></c:out></td>
								<td><c:out value="${customer.status}"></c:out></td>

							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			

</div>
