<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
		<div class="col-sm-12 col-md-12 col-lg-12">
				<h3><spring:message code="label.existingStock"/></h3>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
			 <div  class="sucess"><b><font color="green">${success}</font></b></div>
		</div>

		
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.customerHeadName"/></th>
						<th><spring:message code="label.goodsName"/></th>
						<th><spring:message code="label.quantityRemaining"/></th>
						<th><spring:message code="label.transactionId"/></th>
					</tr>
				</thead>
					<tbody>
					<c:if test="${! empty poList}">
						<c:forEach items="${poList}" var="customer">
							<tr>
								<td><c:out value="${customer.id}"></c:out></td>
								<td><c:out value="${customer.customerName}"></c:out>
								<td><c:out value="${customer.customerHeadName}"></c:out>
								<td><c:out value="${customer.goodsName}"></c:out></td>
								<td><c:out value="${customer.overAllQuantity}"></c:out></td>
                                 <td><c:out value="${customer.transactionId}"></c:out></td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
					<div class="col-sm-12 col-md-12 col-lg-12">
     <table align="center">
      <tr>
      
       <td><a href="bankEmp" class="btn btn-success"><spring:message code="label.back"/></a></td>
      </tr>
     </table>
          </div>
			</table> 
			

</div>
