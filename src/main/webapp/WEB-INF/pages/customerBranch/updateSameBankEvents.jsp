<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="taglib_includes.jsp" %>
<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12  header_customer">
             <h3 align="center" ><spring:message code="label.buyerSameBankDetails"/></h3>    
             </div>
              <div class="col-sm-12 col-md-12 col-lg-12"><font color="red">${success}</font></div>
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
					<th><spring:message code="label.day1"/></th>
					<th><spring:message code="label.date1"/></th>
					<th><spring:message code="label.event1"/></th>
					<th><spring:message code="label.status"/></th>
					<th><spring:message code="label.action"/></th> 
                </tr>
                </thead>
                <tbody>
                 <c:if test="${! empty buyerSameBankEvents}">
                 <c:forEach items="${buyerSameBankEvents}" var="buyerSameBankEvents"> 
                <tr>
                    <td><c:out value="${buyerSameBankEvents.id}"></c:out> </td>
                    <td><c:out value="${buyerSameBankEvents.customerName}"></c:out></td>
                     <td><c:out value="${buyerSameBankEvents.supplier}"></c:out></td>
                    <td><c:out value="${buyerSameBankEvents.supplierBank}"></c:out></td>
						 <td><c:out value="${buyerSameBankEvents.goods}"></c:out></td>
						 <td><c:out value="${buyerSameBankEvents.masterKey}"></c:out></td>
						 <td><c:out value="${buyerSameBankEvents.first}"></c:out></td>
						 <fmt:formatDate var='date1' value='${buyerSameBankEvents.date1}' pattern="dd/MM/yyyy" />
						 <td><c:out value="${date1}"></c:out></td>
						 <td><c:out value="${buyerSameBankEvents.event1}"></c:out></td>
						 <td><c:out value="${buyerSameBankEvents.status}"></c:out></td>
										
					<td><a href="selectUpdateSameBankEvents?id=${buyerSameBankEvents.id}" class="btn btn-primary"><spring:message code="label.select"/></a></td>
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
