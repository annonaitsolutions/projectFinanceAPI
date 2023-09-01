<%@include file="taglib_includes.jsp" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-md-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.buyerSameBankDetails"/></h3>  
            </div> 
            <div class="col-sm-12 col-md-12 col-lg-12"> 
             <div><font color="red">${success}</font></div>
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
                   	<th><spring:message code="label.supplierName"/></th>
					<th><spring:message code="label.supplierBank"/></th>
					<th><spring:message code="label.goods"/></th>
					<th><spring:message code="label.masterKey"/></th>
					<th><spring:message code="label.day"/>1</th>
					<th><spring:message code="label.date"/>1</th>
					<th><spring:message code="label.event"/>1</th>
					<th><spring:message code="label.approvalStatus"/></th>
					
					<th><spring:message code="label.action"/></th> 
                </tr>
                </thead>
                <tbody>
                 <c:if test="${! empty buyerDiffBankEvents}">
                 <c:forEach items="${buyerDiffBankEvents}" var="buyerDiffBankEvents"> 
                <tr>
                    <td><c:out value="${buyerDiffBankEvents.id}"></c:out> </td>
                    <td><c:out value="${buyerDiffBankEvents.customerName}"></c:out></td>
                     <td><c:out value="${buyerDiffBankEvents.supplier}"></c:out></td>
                    <td><c:out value="${buyerDiffBankEvents.supplierBank}"></c:out></td>
						 <td><c:out value="${buyerDiffBankEvents.goods}"></c:out></td>
						 <td><c:out value="${buyerDiffBankEvents.masterKey}"></c:out></td>
						 <td><c:out value="${buyerDiffBankEvents.first}"></c:out></td>
						 <fmt:formatDate var='date1' value='${buyerDiffBankEvents.date1}' pattern="dd/MM/yyyy" />             
						 <td><c:out value="${date1}"></c:out></td>
						 <td><c:out value="${buyerDiffBankEvents.event1}"></c:out></td>
						 <td><c:out value="${buyerDiffBankEvents.status}"></c:out></td>
						 
					
					<td><a href="selectDiffEventsForCheckStatus?id=${buyerDiffBankEvents.id}" class="btn btn-primary"><spring:message code="label.select"/></a></td>
                </tr>
                </c:forEach>
                </c:if>
               
                </tbody>
            </table>
         
        </div>
    </div>

<script>
    $(document).ready( function () {
        $('#bootstrap-table').bdt();
    });
</script>
