<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.businessPlan"/></h3>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div  class="successMsg"><b><font color="green">${success}</font></b></div>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerHeadName"/></th>
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.masterKey"/></th>
						<th><spring:message code="label.utilizedAmount"/></th>
						<th><spring:message code="label.distAmt"/></th>
				<%-- 		<th><spring:message code="label.balance"/></th> --%>
						<th><spring:message code="label.action"/></th>
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
						<%-- 		<td><c:out value="${master.busBalance}"></c:out></td> --%>
                               <td><a href="createBranchBusinessPlan?id=${master.id}"
									class="btn btn-primary"><spring:message code="label.create"/></a></td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			</div>

</div>
