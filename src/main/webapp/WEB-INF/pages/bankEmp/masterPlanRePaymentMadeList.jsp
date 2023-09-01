<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


 <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

 		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
 				<h3 align="center"><spring:message code="label.repay"/></h3>
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
						<th><spring:message code="label.status"/></th>
						<th><spring:message code="label.paymentType"/></th>
						<th><spring:message code="label.sanctionedAmount"/></th>
						<th><spring:message code="label.interestAmount"/></th>
						<th><spring:message code="label.fullAmount"/></th>
						<th><spring:message code="label.moneyStatus"/></th>
						<th><spring:message code="label.fullAmount"/></th>
						<th><spring:message code="label.quarterlyAmount"/></th>
						<th><spring:message code="label.halfYearlyAmount"/></th>
						<th><spring:message code="label.action"/></th>

						
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty repay}">
						<c:forEach items="${repay}" var="master">
							<tr>
								<td><c:out value="${master.id}"></c:out></td>
								<td><c:out value="${master.customer}"></c:out>
								<td><c:out value="${master.masterKey}"></c:out>
								<td><c:out value="${master.status}"></c:out></td>
								<td><c:out value="${master.payOption}"></c:out>
								<td><c:out value="${master.buyingCostSanc}"></c:out></td>
								<td><c:out value="${master.rateOfInt1}"></c:out></td>
								<td><c:out value="${master.funalAmt}"></c:out></td>
								<td><c:out value="${master.moneyStatus}"></c:out></td>
								<td><a href="masterPlanRePaymentBankFullList?id=${master.id}"
									class="btn btn-primary"><spring:message code="label.view"/></a></td>
									<td><a href="masterPlanRePaymentBankQuarterly?id=${master.id}"
									class="btn btn-primary"><spring:message code="label.view"/></a></td>
									<td><a href="masterPlanRePaymentBankHalfYearly?id=${master.id}"
									class="btn btn-primary"><spring:message code="label.view"/></a></td>
									<td><a href="masterPlanRePaymentMadeStatus?id=${master.id}"
									class="btn btn-primary"><spring:message code="label.select"/></a></td>
								
								
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			
		</div>
</div>
