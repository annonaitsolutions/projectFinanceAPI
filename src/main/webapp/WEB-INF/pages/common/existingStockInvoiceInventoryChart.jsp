<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet"	href="<%=request.getContextPath()%>/resources/css/jquery.jOrgChart.css" />
<link rel="stylesheet"	href="<%=request.getContextPath()%>/resources/css/customChart.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/prettify.css" /> 
<script type="text/javascript"	src="<%=request.getContextPath()%>/resources/js/prettify.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/resources/js/jquery-ui.min.js"></script>
<script	type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.jOrgChart.js"></script>

<script>
    jQuery(document).ready(function() {
        $("#org").jOrgChart({
            chartElement : '#chart',
            dragAndDrop  : true
        });
    });
</script>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center"><spring:message code="label.whList"/></h3>
		</div>
	<div class="col-sm-12 col-md-12 col-lg-12">
	<form:form action="existingStockInvoiceInventoryChartById" commandName="wareHouseForm">
		<table class="table table-hover jqtable" id="bootstrap-table">
				<thead>
					<tr>
						<th><spring:message code="label.select"/></th>
						<th><spring:message code="label.whName"/></th>											
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty warehouseList}">
						<c:forEach items="${warehouseList}" var="warehouse" varStatus="status">
							<tr>
								<td><form:checkbox  path="ids" value="${warehouse.id}"/></td>
								<td><c:out value="${warehouse.wareHouseName}"></c:out></td>							
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			<div class="col-sm-12 col-md-12 col-lg-12">
					<table style="margin:0px auto;">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.ghView"/>" class="btn btn-primary"></td>
						</tr>
					</table>
		        </div>
			</form:form> 	
	</div>
	<div class="col-sm-12 col-md-12 col-lg-12">
		<div onload="prettyPrint();">

			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.whChart"/></h3>
			</div>
			<ul  id="org" style="display: none">			
				<li>
			<ul>							
				<c:forEach var="warehouse" items="${model.warehouseChart}">
					<li><spring:message code="label.wareHouse"/><br>
					<table width="400" class="table table-bordered" style="margin-top: 0px;width: 321px;margin-left: -62px;">
						<tr>
							<td><spring:message code="label.name"/></td>
							<td><spring:message code="label.capacity"/></td>
							<td><spring:message code="label.size"/></td>
						</tr>
				
						<tr>
							<td>${warehouse.key.wareHouseName}</td>
							<td>${warehouse.key.capacity}</td>
							<td>${warehouse.key.size}</td>
						</tr>
					</table>
					<ul>
					<c:forEach var="goods" items="${warehouse.value}">
					<img src="<%=request.getContextPath()%>/resources/images/warehouse.png">
							
								<li>
								<table style="margin-top:15px; margin-left: 32px;">
										<tr>
											<td><spring:message code="label.goodsName"/></td>
																						
										</tr>
										<tr>
											<td>${goods.goodsName}</td>											
										</tr>
									</table>
									<ul>
										<li><%-- <spring:message code="label.overall"/> --%><img style="margin-top: -66px;" src="<%=request.getContextPath()%>/resources/images/overall.png"><br>${goods.quantity}
											<ul>
												<li><spring:message code="label.used"/><img src="<%=request.getContextPath()%>/resources/images/used.png"><br>${goods.usedQuantity}</li>
												<li><spring:message code="label.damaged"/><img src="<%=request.getContextPath()%>/resources/images/damaged.png"><br>${goods.damaged}</li>
												<li><spring:message code="label.quantityRemaining"/> <br>${goods.overAllQuantity}</li>
											</ul>
										</li>
									</ul>
								</li>
							
						</c:forEach>
						</ul>
					</li>
				</c:forEach>
			</ul>
			</li>
			</ul>
			<div id="chart" class="orgChart"></div>
		</div>
	</div>
</div>
<script>
	jQuery(document).ready(function() {

		/* Custom jQuery for the example */
		$("#show-list").click(function(e) {
			e.preventDefault();

			$('#list-html').toggle('fast', function() {
				if ($(this).is(':visible')) {
					$('#show-list').text('Hide underlying list.');
					$(".topbar").fadeTo('fast', 0.9);
				} else {
					$('#show-list').text('Show underlying list.');
					$(".topbar").fadeTo('fast', 1);
				}
			});
		});

		$('#list-html').text($('#org').html());

		$("#org").bind("DOMSubtreeModified", function() {
			$('#list-html').text('');

			$('#list-html').text($('#org').html());

			prettyPrint();
		});
	});
</script>
<style>
.jOrgChart .node {
     display: inline-block;
     width: 150px;
   	 height: 67px;
  	 z-index: 19;
  	 margin: 0 45px;
}
.jOrgChart{
	overflow: scroll;
    height: 450px;
     border: 2px solid #828282;
 }
</style>