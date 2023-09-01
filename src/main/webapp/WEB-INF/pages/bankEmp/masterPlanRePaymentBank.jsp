<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<script>

	$(document).ready( function () {
		var paymentType= ${master.amtType};
		if(paymentType === 'Working Capital'){

		}
	});
</script>

 <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

 		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
 			<h3 align="center"><spring:message code="label.repayment"/></h3>
 		</div>
 		<div class="col-sm-12 col-md-12 col-lg-12">
			 <div  class="sucess"><b><font color="green">${success}</font></b></div>
		</div>

		<div class="col-sm-12 col-md-12 col-lg-12">	
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.masterKey"/></th>
						<th><spring:message code="label.sanctionedAmount"/></th>
						<th><spring:message code="label.interestAmount"/></th>
						<th><spring:message code="label.fullAmount"/></th>
<%--						<th><spring:message code="label.managerStatus"/></th>--%>
<%--						<th><spring:message code="label.moneyStatus"/></th>--%>
						<th><spring:message code="label.amountType"/></th>
						<th><spring:message code="label.action"/></th>
						<th><spring:message code="label.reject"/></th>

						
					</tr>
				</thead>
				<tbody>
				<c:if test="${! empty repayList}">
					<c:forEach items="${repayList}" var="master">

						<td><c:out value="${master.id}"></c:out></td>
						<td><c:out value="${master.customer}"></c:out>
						<td><c:out value="${master.masterKey}"></c:out>

						<c:if test="${ master.amtType=='Buying Cost'}">
							<td><c:out value="${master.buyingCostSanc}"></c:out></td>
							<td><c:out value="${master.rateOfInt1}"></c:out></td>
							<td><c:out value="${master.funalAmt}"></c:out></td>
						</c:if>

						<c:if test="${ master.amtType=='Working Capital'}">
							<td><c:out value="${master.wcSancAmount}"></c:out></td>
							<td><c:out value="${master.wcTotalInterest}"></c:out></td>
							<c:set var="total" value="${master.wcSancAmount + master.wcTotalInterest}" />
							<td><c:out  value="${total}"></c:out></td>
						</c:if>

						<td><c:out value="${master.amtType}"></c:out></td>
						<%--								<td><c:out value="${master.status}"></c:out></td>--%>
						<%--								<td><c:out value="${master.moneyStatus}"></c:out></td>--%>
						<td><a href="masterPlanRePaymentBankSet?id=${master.id}"
							   class="btn btn-primary"><spring:message code="label.select"/></a></td>
						<td><a href="masterPlanRePaymentReject?id=${master.id}"
							   class="btn btn-primary"><spring:message code="label.reject"/></a></td>


						</tr>
					</c:forEach>
				</c:if>

				</tbody>
			</table> 
			

</div>
</div>