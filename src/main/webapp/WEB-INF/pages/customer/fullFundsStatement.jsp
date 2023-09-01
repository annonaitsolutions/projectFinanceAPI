<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.fullStatement"/></h3>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div  class="successMsg"><b><font color="green">${success}</font></b></div>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
			<div class="pull-right">			 
				<a href="#"	style="color:green;" onClick="$('#my-table').tableExport({type:'excel',escape:'false'});"><spring:message code="label.download"/></a>			 
				<a href="#"	style="color:#1B5CBF;" onClick="$('#my-table').tableExport({type:'pdf',pdfFontSize:7,escape:'false'});"><spring:message code="label.print"/></a>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerHeadName"/></th>
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.date"/></th>
						<th><spring:message code="label.amountTransfered"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty statement}">
						<c:forEach items="${statement}" var="drafts">
							<tr>
								<td><c:out value="${drafts.id}"></c:out></td>
								<td><c:out value="${drafts.customerHeadName}"></c:out>
								<td><c:out value="${drafts.customerName}"></c:out>
								<fmt:formatDate var='fundsDate' value='${drafts.fundsDate}' pattern="dd/MM/yyyy" />
								<td><c:out value="${fundsDate}"></c:out></td>
								<td><c:out value="${drafts.amount}"></c:out>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			</div>

</div>
</div>