<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

	 	
	
	<div class="col-sm-12 col-md-12 col-lg-12">
		 <div  class="sucess"><b><font color="green">${success}</font></b></div>
	</div>
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3><spring:message code="label.poPaymentList"/></h3>
	</div>
	<!-- <div class="col-sm-8 col-md-7 col-lg-7"> -->
	
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.supplierName"/></th>
						 <th><spring:message code="label.masterKey"/></th>
						<th><spring:message code="label.poKey"/></th>
						<th><spring:message code="label.poDate"/></th>
						 <th><spring:message code="label.amount"/></th>
						<th><spring:message code="label.goods"/></th>
						<th><spring:message code="label.action"/></th>
                        <th><spring:message code="label.action"/></th>
						
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty purchaseList}">
						<c:forEach items="${purchaseList}" var="master">
							<tr>
								<td><c:out value="${master.customerName}"></c:out>
								<td><c:out value="${master.supplierName}"></c:out>
								<td><c:out value="${master.masterKey}"></c:out>
								<td><c:out value="${master.poKey}"></c:out>
								<fmt:formatDate var='purchaseDate' value='${master.purchaseDate}' pattern="dd/MM/yyyy" />
								<td><c:out value="${purchaseDate}"></c:out></td>
								 <td><c:out value="${master.amount}"></c:out></td>
								<td><c:out value="${master.goods}"></c:out></td>
								<td><a href="poPaymentHead?id=${master.id}"
									class="btn btn-primary"><spring:message code="label.select"/></a></td>
								<td><a href="poPaymentHeadAdvance?id=${master.id}"
									class="btn btn-primary"><spring:message code="label.advancePay"/></a></td>
								
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			
	<!-- </div> -->
	
	<%-- <div class="col-sm-3 col-md-3 col-lg-3">
				
		<video id="video1" width="400" height="300" controls>
			<source src="<%=request.getContextPath()%>/resources/videos/payments.mp4" type="video/mp4">
		</video>
	</div>   --%>
</div>
