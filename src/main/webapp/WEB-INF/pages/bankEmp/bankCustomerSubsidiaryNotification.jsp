<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<section>	

	<div class="container">
	 <div  class="successMsg"><b><font color="green">${success}</font></b></div>

<br>
			<br>
			<h3 align="middle"><spring:message code="label.customerSubsidiaryList"/></h3>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.name"/></th>
						<th><spring:message code="label.customerSubsidiaryPrefix"/></th>
						<th><spring:message code="label.customerHeadKey"/></th>
						<th><spring:message code="label.customerHeadName"/></th>
						<th><spring:message code="label.address"/></th>
						<th><spring:message code="label.status"/></th>
						<th><spring:message code="label.notificationStatus"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty customerList}">
						<c:forEach items="${customerList}" var="customer">
							<tr>
								<td><c:out value="${customer.id}"></c:out></td>
								<td><c:out value="${customer.name}"></c:out>
								<td><c:out value="${customer.customerPrefix}"></c:out>
								<td><c:out value="${customer.customerHeadKey}"></c:out></td>
								<td><c:out value="${customer.customerHeadName}"></c:out></td>
								<td><c:out value="${customer.address}"></c:out></td>
								<td><c:out value="${customer.status}"></c:out></td>
	                            <td><c:out value="${customer.notificationStatus}"></c:out></td>
	                               <td>&nbsp;<a href="selectbankCustomerSubsidiaryNotification?id=${customer.id}"class="btn btn-primary"><spring:message code="label.select"/></a>
								</td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			

</div>

</section>