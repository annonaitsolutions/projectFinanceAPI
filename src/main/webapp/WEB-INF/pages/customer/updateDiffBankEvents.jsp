<%@include file="taglib_includes.jsp" %>
 <div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
			 <div class="col-sm-12 col-md-12 col-lg-12 header_customer">
                <h3 align="center"><spring:message code="label.buyerDiffBankDet"/></h3> 
             </div>   
             <div class="col-sm-12 col-md-12 col-lg-12">
             		<font color="red">${success}</font>
             </div>
           <div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
              <thead>
                <tr>
                    <th><spring:message code="label.id"/></th>
                    <th><spring:message code="label.customer"/></th>
                   	<th><spring:message code="label.supplier"/></th>
					<th><spring:message code="label.supplierBank"/></th>
					<th><spring:message code="label.goods"/></th>
					<th><spring:message code="label.masterKey"/></th>
					<th><spring:message code="label.day1"/></th>
					<th><spring:message code="label.date1"/></th>
					<th><spring:message code="label.event1"/></th>
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
						
					<td><a href="selectUpdateDiffBankEvents?id=${buyerDiffBankEvents.id}" class="btn btn-primary"><spring:message code="label.select"/></a></td>
                </tr>
                </c:forEach>
                </c:if>
               
                </tbody>
            </table>
         
        </div>

