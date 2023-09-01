<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">

	 <div  class="sucess"><b><font color="green">${success}</font></b></div>

<br>
			<br>
			<h3 align="center"><spring:message code="label.masterplanApprovalList"/></h3>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.masterKey"/></th>
						<th><spring:message code="label.bankEmployeeStatus"/></th>
						<th><spring:message code="label.managerStatus"/></th>
						<th><spring:message code="label.fullDetails"/></th>
						<th><spring:message code="label.riskAssignment"/></th>
						<th><spring:message code="label.creditAssignment"/></th>
						<th><spring:message code="label.workingCapitalAssignment"/></th>
						<th><spring:message code="label.approve/reject"/></th>
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
								<td><a href="appMasterPlanFullDetails?id=${master.id}"
									class="btn btn-primary"><spring:message code="label.view"/></a></td>
									<td><a href="appRiskAssignment"
									class="btn btn-primary"><spring:message code="label.view"/></a></td>
									<td><a href="appMasterPlanCreditAssign?id=${master.id}"
									class="btn btn-primary"><spring:message code="label.view"/></a></td>
									<td><a href="appMasterPlanWcAssign?id=${master.id}"
									class="btn btn-primary"><spring:message code="label.view"/></a></td>
									<td><a href="appMasterPlanApproveStatus?id=${master.id}"
									class="btn btn-primary"><spring:message code="label.edit"/></a></td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			

</div>

</div>