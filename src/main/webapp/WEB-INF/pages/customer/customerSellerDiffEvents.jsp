<%@include file="taglib_includes.jsp" %>
 <div class="container">
    <div class="row">
        <div class="col-md-12">
            <h3 align="center" style="color: #2E9AFE;font-size: 19px; font-weight: 400;margin-top: -4px;text-transform: uppercase;"><spring:message code="label.sellerDifferentBankEvents"/></h3>    
            <div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
            <div><font color="red">${success}</font></div>
              <thead>
                <tr>
                    <th><spring:message code="label.id"/></th>
                    <th><spring:message code="label.customerName"/></th>
                   	<th><spring:message code="label.buyerName"/></th>
					<th><spring:message code="label.buyerBank"/></th>
					<th><spring:message code="label.goods"/></th>
					<th><spring:message code="label.masterKey"/></th>
					<th><spring:message code="label.day"/>1</th>
					<th><spring:message code="label.date"/>1</th>
					<th><spring:message code="label.event"/>1</th>
					<th><spring:message code="label.approvalStatus"/></th>
					<th><spring:message code="label.customerStatus"/></th>
					<th><spring:message code="label.action"/></th> 
                </tr>
                </thead>
                <tbody>
                 <c:if test="${! empty sellerDiffBankEvents}">
                 <c:forEach items="${sellerDiffBankEvents}" var="sellerDiffBankEvents"> 
                <tr>
                    <td><c:out value="${sellerDiffBankEvents.id}"></c:out> </td>
                    <td><c:out value="${sellerDiffBankEvents.customerName}"></c:out></td>
                     <td><c:out value="${sellerDiffBankEvents.buyer}"></c:out></td>
                    <td><c:out value="${sellerDiffBankEvents.buyerBank}"></c:out></td>
						 <td><c:out value="${sellerDiffBankEvents.goods}"></c:out></td>
						 <td><c:out value="${sellerDiffBankEvents.masterKey}"></c:out></td>
						 <td><c:out value="${sellerDiffBankEvents.first}"></c:out></td>
					<fmt:formatDate var='date1' value='${sellerDiffBankEvents.date1}' pattern="dd/MM/yyyy" />
                                        
						 <td><c:out value="${date1}"></c:out></td>
						 <td><c:out value="${sellerDiffBankEvents.event1}"></c:out></td>
						 <td><c:out value="${sellerDiffBankEvents.status}"></c:out></td>
						 <td><c:out value="${sellerDiffBankEvents.customerStatus}"></c:out></td>
					
					<td><a href="selectCustomerSellerDiffEvents?id=${sellerDiffBankEvents.id}" class="btn btn-primary"><spring:message code="label.select"/></a></td>
                </tr>
                </c:forEach>
                </c:if>
               
                </tbody>
            </table>
         
        </div>
    </div>
</div>

<script>
    $(document).ready( function () {
        $('#bootstrap-table').bdt();
    });
</script>

</body>