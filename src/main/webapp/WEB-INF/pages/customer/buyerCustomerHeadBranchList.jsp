<%@include file="taglib_includes.jsp" %>
 <div class="container">
    <div class="row">
        <div class="col-md-12">
            <h3><spring:message code="label.buyerCustomerHeadDetails"/></h3>  
            <div>
            
           <font color="red">${success } </font>
            </div>  
            <div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
              <thead>
						<tr>
							<th><spring:message code="label.id"/></th>
							<th><spring:message code="label.customerName"/></th>
							<th><spring:message code="label.buyerName"/></th>
							<th><spring:message code="label.buyerCompanyName"/></th>
							<th><spring:message code="label.currencyDealtIn"/></th>
							<th><spring:message code="label.address"/></th>
							<th><spring:message code="label.pincode"/></th>
							<th><spring:message code="label.country"/></th>
							<th><spring:message code="label.state"/></th>
							<th><spring:message code="label.contactNumber"/></th>
						    <th><spring:message code="label.email"/></th>
					         <th><spring:message code="label.status"/></th>
					         <th><spring:message code="label.comment"/></th>
							<th><spring:message code="label.action"/></th>
						

						</tr>
					</thead>
					<tbody>
						<c:if test="${! empty newbuyerList}">

							<c:forEach items="${newbuyerList}" var="buyer">
								<tr>
									<td><c:out value="${buyer.id}"></c:out></td>
									<td><c:out value="${buyer.name}"></c:out></td>
								    <td><c:out value="${buyer.buyerName}"></c:out></td>
									<td><c:out value="${buyer.companyName}"></c:out></td>
							        <td><c:out value="${buyer.currencydeal}"></c:out></td>
									<td><c:out value="${buyer.address}"></c:out></td>
								
									<td><c:out value="${buyer.pinCode}"></c:out></td>
									<td><c:out value="${buyer.country}"></c:out></td>
									<td><c:out value="${buyer.state}"></c:out></td>
                                    <td><c:out value="${buyer.contactNum}"></c:out></td>
						            <td><c:out value="${buyer.email}"></c:out></td>
									
						            <td><c:out value="${buyer.cStatus}"></c:out></td>
						            <td><c:out value="${buyer.comment}"></c:out></td>
						            <td>&nbsp;<a href="buyerPageShowApproval?id=${buyer.id}"class="btn btn-primary"><spring:message code="label.select"/></a></td>
								
								
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