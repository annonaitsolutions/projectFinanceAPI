<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="col-sm-9 col-md-9 body_fixed">
			
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3><spring:message code="label.distributeFunds"/></h3>
		</div>
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
						<th><spring:message code="label.amountDistributed"/></th>
						<th><spring:message code="label.sanctionedAmount"/></th>
						
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
								
							</tr>
						</c:forEach>
					</c:if>
					
				</tbody>
			</table> 
			<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td><a href="masterPlanFunds" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
			</div>