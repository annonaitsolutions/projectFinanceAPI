<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		 <h3 align="middle"><spring:message code="label.purchaseOrder"/></h3>
	</div>
	<div class="col-sm-12 col-md-12 col-lg-12">
		 <div  class="sucess"><b><font color="green">${success}</font></b></div>
	</div>
		<div class="col-sm-12 col-md-12 col-lg-12">	
				<div class="col-sm-12 col-md-12 col-lg-12">
	<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
</div>
		<table class="table table-hover data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerName"/></th>
				
						<th><spring:message code="label.poKey"/></th>
						<th><spring:message code="label.status"/></th>
						<th><spring:message code="label.uploadDate"/></th>
					<%-- 	<th><spring:message code="label.supplierName"/></th> --%>
					<%-- 	<th><spring:message code="label.amount"/></th> --%>
					<%-- <th><spring:message code="label.goods"/></th>
					<th><spring:message code="label.quantity"/></th> --%>
						<th><spring:message code="label.fileName"/></th>
						<th><spring:message code="label.document"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty poList}">
						<c:forEach items="${poList}" var="master">
							<tr>
								<td><c:out value="${master.id}"></c:out></td>
								<td><c:out value="${master.customerName}"></c:out>
						<%-- 		<td><c:out value="${master.supplierName}"></c:out> --%>
								<td><c:out value="${master.poKey}"></c:out></td>
								<td><c:out value="${master.status}"></c:out></td>
								<fmt:formatDate var='uploadDate' value='${master.uploadDate}' pattern="dd/MM/yyyy" />
								<td><c:out value="${uploadDate}"></c:out></td>
							<%-- 	<td><c:out value="${master.amount}"></c:out></td>
								<td><c:out value="${master.goods}"></c:out></td>
								<td><c:out value="${master.quantity}"></c:out></td> --%>
						        <td><c:choose>
								<c:when test="${! empty master.fileNames}">
									<c:forEach items="${master.fileNames}" var="file">
										<c:out value="${file}"></c:out><br>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<spring:message code='label.filesNotFound'/>
								</c:otherwise>
								</c:choose></td>
							     <td><c:out value="${master.document}"></c:out></td>
								
								
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			
</div>
</div>
