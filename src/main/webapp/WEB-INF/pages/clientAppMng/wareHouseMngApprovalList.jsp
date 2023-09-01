<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12">
	 			<div  class="successMsg"><b><font color="green">${success}</font></b></div>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.warreHouseMngDetails"/></h3>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
		
			<span class="counter pull-right"></span>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
				<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.managerName"/></th>
						<th><spring:message code="label.transactionId"/></th>
						<th><spring:message code="label.companyName"/></th>
						<th><spring:message code="label.address"/></th>
						<th><spring:message code="label.status"/></th>
						<th><spring:message code="label.action"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty customerList}">
						<c:forEach items="${customerList}" var="customer">
							<tr>
								<td><c:out value="${customer.id}"></c:out></td>
								<td><c:out value="${customer.mngName}"></c:out>
								<td><c:out value="${customer.transactionId}"></c:out>
								<td><c:out value="${customer.companyName}"></c:out></td>
								<td><c:out value="${customer.address}"></c:out></td>
								<td><c:out value="${customer.status}"></c:out></td>
								<td><a href="approveWareHouseMng?id=${customer.id}"
									class="btn btn-primary"><spring:message code="label.select"/></a></td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			

</div>
</div>
