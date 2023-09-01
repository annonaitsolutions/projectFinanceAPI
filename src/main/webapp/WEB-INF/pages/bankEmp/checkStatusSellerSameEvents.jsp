<%@include file="taglib_includes.jsp" %>
   <div class="col-md-9 col-sm-9 col-lg-9 body_fixed">
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
            <h3><spring:message code="label.sellerSameBankDetails"/></h3>    
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
					
					<th><spring:message code="label.action"/></th> 
                </tr>
                </thead>
                <tbody>
                 <c:if test="${! empty sellerSameBankEvents}">
                 <c:forEach items="${sellerSameBankEvents}" var="sellerSameBankEvents"> 
                <tr>
                    <td><c:out value="${sellerSameBankEvents.id}"></c:out> </td>
                    <td><c:out value="${sellerSameBankEvents.customerName}"></c:out></td>
                     <td><c:out value="${sellerSameBankEvents.buyer}"></c:out></td>
                    <td><c:out value="${sellerSameBankEvents.buyerBank}"></c:out></td>
						 <td><c:out value="${sellerSameBankEvents.goods}"></c:out></td>
						 <td><c:out value="${sellerSameBankEvents.masterKey}"></c:out></td>
						 <td><c:out value="${sellerSameBankEvents.first}"></c:out></td>
						 <fmt:formatDate var='date1' value='${sellerSameBankEvents.date1}' pattern="dd/MM/yyyy" />
                           
						 <td><c:out value="${date1}"></c:out></td>
						 <td><c:out value="${sellerSameBankEvents.event1}"></c:out></td>
						 <td><c:out value="${sellerSameBankEvents.status}"></c:out></td>
						
					
					<td><a href="selectSellerSameEventsForCheckStatus?id=${sellerSameBankEvents.id}" class="btn btn-primary"><spring:message code="label.select"/></a></td>
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

</body>