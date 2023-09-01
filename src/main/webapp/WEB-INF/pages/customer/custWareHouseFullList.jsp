<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
		
		<span class="counter pull-right"></span>
		<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<caption><spring:message code="label.whList"/></caption>
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.whName"/></th>
						<th><spring:message code="label.personInCharge"/></th>
						<th><spring:message code="label.address"/></th>
						<th><spring:message code="label.status"/></th>

					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty whList}">
						<c:forEach items="${whList}" var="customer">
							<tr>
								<td><c:out value="${customer.id}"></c:out></td>
								<td><c:out value="${customer.customerName}"></c:out>
								<td><c:out value="${customer.wareHouseName}"></c:out>
								<td><c:out value="${customer.personInCharge}"></c:out></td>
								<td><c:out value="${customer.address}"></c:out></td>
								<td><c:out value="${customer.status}"></c:out></td>

							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
		</div>		
	</div>	 