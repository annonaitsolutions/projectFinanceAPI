<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
			<br>
			<h3 align="middle"><spring:message code="label.distributeFunds"/></h3>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerHeadName"/></th>
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.customerType"/></th>
						<th><spring:message code="label.masterKey"/></th>
						<th><spring:message code="label.availableAmount"/></th>
						<th><spring:message code="label.sanctionedAmount"/></th>
						<th><spring:message code="label.request"/></th>
						
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty fundsList}">
						<c:forEach items="${fundsList}" var="funds">
							<tr>
								<td><c:out value="${funds.id}"></c:out></td>
								<td><c:out value="${funds.customerHeadName}"></c:out>
								<td><c:out value="${funds.customerName}"></c:out>
								<td><c:out value="${funds.currentRole}"></c:out>
								<td><c:out value="${funds.masterKey}"></c:out>
								<td><c:out value="${funds.distributedAmount}"></c:out></td>
								<td><c:out value="${funds.buyingCostSanc}"></c:out></td>
								<td><a href="requestMoney?id=${funds.id}"
									class="btn btn-primary"><spring:message code="label.request"/></a></td>
								
							</tr>
						</c:forEach>
					</c:if>
					
				</tbody>
			</table> 
			</div>