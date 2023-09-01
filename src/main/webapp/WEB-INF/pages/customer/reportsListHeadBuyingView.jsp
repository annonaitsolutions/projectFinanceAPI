<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="col-sm-9 col-md-9 body_fixed">
			
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center"><spring:message code="label.reports"/></h3>
		</div>
		
		<div class="col-sm-12 col-md-12 col-lg-12">
			 <div  class="successMsg"><b><font color="green">${success}</font></b></div>
	 	</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
		<div class="pull-right download-color">			 
			<a href="#"	onClick="$('#my-table').tableExport({type:'excel',escape:'false'});"><spring:message code="label.download"/></a>			 
			<a href="#"	onClick="$('#my-table').tableExport({type:'pdf',pdfFontSize:7,escape:'false'});"><spring:message code="label.print"/></a>
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
						<th><spring:message code="label.invoiceKey"/></th>
						<th><spring:message code="label.goods"/></th>
						<th><spring:message code="label.quantity"/></th>
						<th><spring:message code="label.requestMoney"/></th>
						
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty invoiceForm.invoiceList}">
						<c:forEach items="${invoiceForm.invoiceList}" var="master">
							<tr>
								<td><c:out value="${master.id}"></c:out></td>
								<td><c:out value="${master.customerName}"></c:out>
								<td><c:out value="${master.buyerName}"></c:out>
								<td><c:out value="${master.poKey}"></c:out>
								<td><c:out value="${master.goods}"></c:out>
								<td><c:out value="${master.quantity}"></c:out>
								<td><c:out value="${master.requestMoney}"></c:out>
								
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
		</div>
		<h3><spring:message code="label.statisticalValue"/></h3>
	<div class="col-sm-12 col-md-12 col-lg-12">
	        <a href="#" data-toggle="tooltip" title="<spring:message code="label.rangeInfo"/>"><spring:message code="label.range"/></a>(MR): ${model.range} <br>
			 <a href="#" data-toggle="tooltip" title="<spring:message code="label.meanInfo"/>"><spring:message code="label.mean"/>	</a>(&mu;)  : ${model.mean} <br>
			 <a href="#" data-toggle="tooltip" title="<spring:message code="label.modeInfo"/>"><spring:message code="label.mode"/></a> (Mo)	 : ${model.mode} <br>
			 <a href="#" data-toggle="tooltip" title="<spring:message code="label.medianInfo"/>"><spring:message code="label.median"/></a> (x&#772;)	 : ${model.median} <br>
			 <a href="#" data-toggle="tooltip" title="<spring:message code="label.varianceInfo"/>"><spring:message code="label.variance"/></a> (&sigma;<sup>2</sup>)	 : ${model.variance} <br>
			 <a href="#" data-toggle="tooltip" title="<spring:message code="label.standardDeviationInfo"/>"><spring:message code="label.standardDeviation"/></a> (&radic;&sigma;<sup>2</sup>) : ${model.standardDeviation}<br>
	</div>
<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td><a href="reportsListHeadBying" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
</div>
<script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
});
</script>
<style>
	a{
		color:black;
	}
</style>