<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- To fetch the request url -->
<c:set var="req" value="${requestScope['javax.servlet.forward.request_uri']}"/>      
<c:set var="baseURL" value="${fn:split(req,'/')}" />

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	 	
	
	<div class="col-sm-12 col-md-12 col-lg-12">
		 <div  class="sucess"><b><font color="green">${success}</font></b></div>
	</div>
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3><spring:message code="label.poPaymentList"/></h3>
	</div>
	<div class="col-sm-7 col-md-7 col-lg-7">
	
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
				<form:form name="role" action="poMultiplePaymentReview" commandName="purchaseOrderForm" method="POST">
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.supplierName"/></th>
						<th><spring:message code="label.amount"/></th>
						<th><spring:message code="label.goods"/></th>
					<%-- 	<th><spring:message code="label.action"/></th> --%>

						
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty purchaseList}">
						<c:forEach items="${purchaseList}" var="master" varStatus="po">
							<tr>
								<td><form:checkbox path="purchaseList[${po.index}].id" value="${master.id}" id="id"/>
								<td><c:out value="${master.customerName}"></c:out>
								<td><c:out value="${master.supplierName}"></c:out>
								<td><c:out value="${master.amount}"></c:out></td>
								<td><c:out value="${master.goods}"></c:out></td>
								<%-- <td><a href="poPaymentHead?id=${master.id}"
									class="btn btn-primary"><spring:message code="label.select"/></a></td> --%>
								
								
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			<form:hidden path="status" id="status"/>
			<form:hidden path="transactionId" value="" />
			<input class="btn btn-primary" type="submit"  value="Review" onclick="val()" id="review" />
			<div id="pay" >
			<input class="btn btn-primary" type="submit"  value="Make Payment"  />			
			<span class="total-amount"><spring:message code="label.totalAmount"/>&nbsp;&nbsp;: ${model.totalAmt}</span>
			<a href="" class="btn btn-success" onClick="cancel()"><spring:message code="label.back"/></a>
			</div>
			</form:form>
	</div>
	 <div class="col-sm-3 col-md-3 col-lg-3">
				
		<video id="video1" width="400" height="300" controls autoplay loop>
			<source src="<%=request.getContextPath()%>/resources/videos/multiplePO.mp4" type="video/mp4">
		</video>
	</div>  
</div>
<script>
$( document ).ready(function() {
	var status = document.getElementById("status").value;	
	if(status == "checked") {		
		$('#review').hide();
		$('#pay').show();		
	}else {
		$('#pay').hide();
		$('#review').show();
	}
});
function val() {
	$("#status").val("checked");
}
$('#pay').click(function(e){
	/* var d = new Date().getTime();
	var uuid = 'xxxxxx'.replace(/[xy]/g, function(c) {
		var r = (d + Math.random() * 16) % 16 | 0;
		d = Math.floor(d / 16);
		return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
	});
	document.role.transactionId.value = uuid; */
	if('${baseURL[1]}' == 'userBranch'){
		$(this).parent('form').attr('action', '${pageContext.request.contextPath}/userBranch/poMultiplePaymentUpdate');
	}else {
    	$(this).parent('form').attr('action', '${pageContext.request.contextPath}/users/poMultiplePaymentUpdate');
	}
});

$('#review').click(function(e){
	if('${baseURL[1]}' == 'userBranch'){
		$(this).parent('form').attr('action', '${pageContext.request.contextPath}/userBranch/poMultiplePaymentReview');
	}else {
    	$(this).parent('form').attr('action', '${pageContext.request.contextPath}/users/poMultiplePaymentReview');
	}
});
function cancel() {
	$("#status").val("");
	if('${baseURL[1]}' == 'userBranch'){
		$(this).parent('form').attr('action', '${pageContext.request.contextPath}/userBranch/poMultiplePayment');
	}else {
    	$(this).parent('form').attr('action', '${pageContext.request.contextPath}/users/poMultiplePayment');
	}
};
</script>
<style>
	span.total-amount {
    float: right;
   
    font-weight: bold;
    padding: 4px;
    border-radius: 5px;
}
</style>