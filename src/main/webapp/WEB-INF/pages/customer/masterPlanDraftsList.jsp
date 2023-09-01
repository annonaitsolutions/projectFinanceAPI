<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.draftList"/></h3>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			 	<div  class="successMsg"><b><font color="green">${success}</font></b></div>
			</div>
			
			
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.companyName"/></th>
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.product"/></th>
						<th><spring:message code="label.buyingCost"/></th>
						<th><spring:message code="label.date"/></th>
						<th><spring:message code="label.action"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty draftsList}">
						<c:forEach items="${draftsList}" var="drafts">
							<tr>
								<td><c:out value="${drafts.id}"></c:out></td>
								<td><c:out value="${company}"></c:out></td>
								<td><c:out value="${drafts.customer}"></c:out>
								<td><c:out value="${drafts.product}"></c:out></td>
								<td><c:out value="${drafts.buyingCost}"></c:out>
								<fmt:formatDate var='draftsDate' value='${drafts.draftsDate}' pattern="dd/MM/yyyy" />
								<td><c:out value="${draftsDate}"></c:out></td>
								<td><a href="draftsMasterPlan1?id=${drafts.id}"
									class="btn btn-primary"><spring:message code="label.edit"/></a></td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			

</div>
</div>