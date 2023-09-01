<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.companyList"/></h3>
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
						<th><spring:message code="label.companyName"/></th>
						<th><spring:message code="label.companyPrefix"/></th>
						<th><spring:message code="label.country"/></th>
						<th><spring:message code="label.state"/></th>
						<th><spring:message code="label.status"/></th>
						
						<th><spring:message code="label.action"/></th>
						
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty companyList}">
						<c:forEach items="${companyList}" var="company">
							<tr>
								<td><c:out value="${company.id}"></c:out></td>
								<td><c:out value="${company.companyName}"></c:out></td>	
								<td><c:out value="${company.companyPrefix}"></c:out>
								<td><c:out value="${company.country}"></c:out></td>
								<td><c:out value="${company.state}"></c:out></td>
								<td><c:out value="${company.status}"></c:out></td>
								
								<td><a href="approveCompany?id=${company.id}"
									class="btn btn-primary"><spring:message code="label.select"/></a></td>

							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			
</div>
</div>
