<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
	 <div  class="successMsg"><b><font color="green">${success}</font></b></div>

			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center">Customer Head List</h3>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
		   <span class="counter pull-right"></span>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Customer Name</th>
						<th>Customer Prefix</th>
						<th>Company Name</th>
						<th>Address</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty customerList}">
						<c:forEach items="${customerList}" var="customer">
							<tr>
								<td><c:out value="${customer.id}"></c:out></td>
								<td><c:out value="${customer.name}"></c:out>
								<td><c:out value="${customer.customerPrefix}"></c:out>
								<td><c:out value="${customer.companyName}"></c:out></td>
								<td><c:out value="${customer.address}"></c:out></td>
								<td><c:out value="${customer.status}"></c:out></td>

							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			</div>

</div>