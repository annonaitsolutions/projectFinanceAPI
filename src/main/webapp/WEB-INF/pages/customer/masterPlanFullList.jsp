<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 body_fixed">
			
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center"><spring:message code="label.masterPlanList"/></h3>
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
				        <th><spring:message code="label.company"/></th>
						<th><spring:message code="label.masterKey"/></th>
					      <th><spring:message code="label.masterplaneDate"/></th>
					      <th><spring:message code="label.product"/></th>
						<th><spring:message code="label.status"/></th>
						<th><spring:message code="label.action"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty masterList}">
						<c:forEach items="${masterList}" var="master">
							<tr>
								<td><c:out value="${master.id}"></c:out></td>
						        <td><c:out value="${company}"></c:out></td>
								<td><c:out value="${master.masterKey}"></c:out>
								<fmt:formatDate var='mpDate' value='${master.masterPlanDate}' pattern="dd/MM/yyyy" />
								<td><c:out value="${mpDate}"></c:out>
								<td><c:out value="${master.product}"></c:out>
								<td><c:out value="${master.status}"></c:out>
								<td><a href="masterPlanFullDetails?id=${master.id}"
									class="btn btn-primary"><spring:message code="label.fullDetails"/></a></td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
		</div>

</div>
