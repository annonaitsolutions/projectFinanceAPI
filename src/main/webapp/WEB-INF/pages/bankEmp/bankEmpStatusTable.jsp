<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

  <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
     				<h3><spring:message code="label.statusTable"/></h3>
 				</div>
           <form:form action="" commandName="masterPlanForm">
           <h4 align="center" style="color:#0A81FF;"><spring:message code="label.masterPlanDetails"/></h4>
            <table style="margin:0px auto;">            
              <tbody>
              			<tr>
              				<td align="center" class="tableh"><spring:message code="label.proposal"/></td>
              			</tr>
                     				
					 <tr>
						<fmt:formatDate var='masterPlanDate' value='${model.master.masterPlanDate}' pattern="dd/MM/yyyy" />
						<td class="col-sm-6"><form:input path="masterPlanDate" value="${masterPlanDate}" readonly="true"/></td>
						<td class="col-sm-6"><textarea rows="5" cols="30" path=comment ><spring:message code="label.mpProposedBy"/> ${model.master.customer} .<spring:message code="label.mpUniqueIs"/>: ${model.master.masterKey},<spring:message code="label.goodsQty"/>:${model.master.quantity},<spring:message code="label.amtRequested"/>:${model.master.buyingCost}  
						
						</textarea>
					</td>
					<tr>
              				<td align="center" class="tableh"><spring:message code="label.bankApp"/></td>
              			</tr>
					</tr>
						<tr>
						<fmt:formatDate var='bankDate' value='${model.master.bankDate}' pattern="dd/MM/yyyy" />
						<td class="col-sm-6"><form:input path="bankDate" value="${bankDate}"  readonly="true"/></td>
						<td class="col-sm-6"><textarea rows="5" cols="30" path=comment ><spring:message code="label.mpIdIs"/>:${model.master.masterKey} .<spring:message code="label.appByAppMng"/>  
						
						</textarea>
						</td>
						</tr>
						<tr>
              				<td align="center" class="tableh"><spring:message code="label.creditAssignment"/></td>
              			</tr>
					 <tr>
						<fmt:formatDate var='creditDate' value='${model.master.creditDate}' pattern="dd/MM/yyyy" />
						<td class="col-sm-6"><form:input path="creditDate" value="${creditDate}"  readonly="true"/></td>
						<td class="col-sm-6"><textarea rows="5" cols="30" path=comment ><spring:message code="label.forMpIdIs"/>:${model.master.masterKey}<spring:message code="label.creditSancIs"/>  : ${model.master.buyingCostSanc}
						</textarea>
					</tr> 
					<tr>
              				<td align="center" class="tableh"><spring:message code="label.managerApp"/></td>
              			</tr>
					<tr>
						<fmt:formatDate var='appDate' value='${model.master.appDate}' pattern="dd/MM/yyyy" />
						<td class="col-sm-6"><form:input path="appDate" value="${appDate}"  readonly="true"/></td>
						<td class="col-sm-6"><textarea rows="5" cols="30" path=comment ><spring:message code="label.limitIs"/>:${model.master.buyingCostSanc},:${model.masterPlanForm.info},<spring:message code="label.forMpIdIs"/> ${model.master.masterKey}
						</textarea>
						</td>
					</tr>
                   </table>
                 
						  
			  <h4 align="center" style="color:#0A81FF;"><spring:message code="label.mpRatesDetails"/></h4> 
              <table align="center"> 

                 <tr>
						<%-- <fmt:formatDate var='appDate' value='${model.master.appDate}' pattern="dd/MM/yyyy" />
						<td class="col-sm-6"><form:input path="masterPlanDate" value="${masterPlanDate}"  readonly="true"/></td> --%>
						<td class="col-sm-6"><textarea rows="5" cols="65" path=comment ><spring:message code="label.bankRatesForMp"/>${model.master.masterKey} <spring:message code="label.amtByBank"/>: ${model.master.buyingCostSanc}(100% of invoice value).,<spring:message code="label.payByCustToBank"/>::${model.master.funalAmt}
                                               <spring:message code="label.bdCharges"/>:
<spring:message code="label.typeOfInt"/>               : ${model.master.interestType}
<%-- <spring:message code="label.rateOfInterest"/>          :Rs ${model.master.rateOfInt} --%>
<%-- <spring:message code="label.noOfDay"/>                 : ${model.master.noOfDays} --%>
<spring:message code="label.plrRate"/>                 :&#8377 ${model.master.plrRate}
<spring:message code="label.flatCharges"/>             :&#8377 ${model.master.flatCharges}
<spring:message code="label.percent"/>                 : ${model.master.percentage}
<spring:message code="label.procFees"/>                :&#8377 ${model.master.procFee}  
<spring:message code="label.docFee"/>                  :&#8377 ${model.master.docFee}  
<spring:message code="label.lateFee"/>                 :&#8377 ${model.master.lateFee} 
                       ${model.master.taxName}         :   ${model.master.taxPercentage}
<spring:message code="label.totalAmtAllChrg"/>                  :&#8377 ${model.master.funalAmt}
						</textarea></td>
					</tr> 
					</table>			   
              <h4 align="center" style="color:#0A81FF;"><spring:message code="label.poDetails"/></h4> 
              <div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable " id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.supplierName"/></th>
						<th><spring:message code="label.poKey"/></th>
						<th><spring:message code="label.status"/></th>
						<th><spring:message code="label.amount"/></th>
						<th><spring:message code="label.purchaseDate"/></th>
						<th><spring:message code="label.limitAmt"/></th>
						<th><spring:message code="label.limitDate"/></th>
						<th><spring:message code="label.limitStatus"/></th>
					

						
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty poList}">
						<c:forEach items="${poList}" var="master">
							<tr>
								<td><c:out value="${master.customerName}"></c:out>
								<td><c:out value="${master.supplierName}"></c:out>
								<td><c:out value="${master.poKey}"></c:out>
								<td><c:out value="${master.status}"></c:out></td>
								<td><c:out value="${master.amount}"></c:out></td>
								<fmt:formatDate var='purchaseDate' value='${master.purchaseDate}' pattern="dd/MM/yyyy" />
								<td><c:out value="${purchaseDate}"></c:out></td>
								<td><c:out value="${master.limitAmt}"></c:out></td>
								<fmt:formatDate var='limitDate' value='${master.limitDate}' pattern="dd/MM/yyyy" />
								<td><c:out value="${limitDate}"></c:out></td>
								<td><c:out value="${master.limitStatus}"></c:out></td>
                        </tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
   
					
			 <h4 align="center" style="color:#0A81FF;"><spring:message code="label.invoiceDetails"/></h4> 
   			 <div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.buyerName"/></th>
						<th><spring:message code="label.invoiceKey"/></th>
						<th><spring:message code="label.date"/></th>
						<th><spring:message code="label.amount"/></th>
						<th><spring:message code="label.status"/></th>
			
						</tr>
				</thead>
				<tbody>
					<c:if test="${! empty invoice}">

						<c:forEach items="${invoice}" var="invoice">
							<tr>
								<td><c:out value="${invoice.id}"></c:out></td>
								<td><c:out value="${invoice.customerName}"></c:out></td>
								<td><c:out value="${invoice.buyerName}"></c:out></td>
								<td><c:out value="${invoice.poKey}"></c:out></td>
								<fmt:formatDate var='poDate' value='${invoice.poDate}' pattern="dd/MM/yyyy" />
								<td><c:out value="${poDate}"></c:out></td>
								<td><c:out value="${invoice.amount}"></c:out></td>
								<td><c:out value="${invoice.status}"></c:out></td>
								</tr>
							</c:forEach>
						</c:if>

					</tbody>
            </table>
						
            </form:form>
            
            <div class="col-sm-12 col-md-12 col-lg-12">
				<table align="center">
					<tr>
						<td><a href="masterPlanStatusDetails" class="btn btn-success">
						<spring:message	code="label.back" /></a></td>
					</tr>
				</table>
			</div>
</div>
<style>
.tableh{
	    color: steelblue;
    font-size: 17px;
    letter-spacing: 2px;
  }
</style>
			