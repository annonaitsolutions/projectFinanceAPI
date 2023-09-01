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

	<div class="col-sm-6 col-md-6 col-lg-6">
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center"><spring:message code="label.whList"/></h3>
		</div>
	<form:form action="generateWarehouseChartById" commandName="wareHouseForm">
			<table class="table table-hover jqtable" id="bootstrap-table" width="300">
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
					<table align="center" width="400">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.ghView"/>" class="btn btn-primary"></td>
						</tr>
					</table>
		        </div>
			</form:form>	
	</div>
	<div class="col-sm-3 col-md-3 col-lg-3">
				
		<video id="video1" width="400" height="300" controls>
			<source src="<%=request.getContextPath()%>/resources/videos/buying.mp4" type="video/mp4">
		</video>
	</div>  
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<div onload="prettyPrint();">
			
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
							
								<li><img src="${goods.imageName}">
									<table width="400" class="table table-bordered" style="margin-top: 0px;width: 321px;margin-left: -62px;">
										<tr>
											<td><spring:message code="label.type"/></td>
											<td><spring:message code="label.amount"/></td>
											<td><spring:message code="label.supplierName"/></td>
										</tr>
										<tr>
											<td>${goods.inventoryType}</td>
											<td>${goods.amount}</td>
											<td>${goods.supplierName}</td>
										</tr>
									</table>
									<ul>
										<li><img style="margin-top: -49px;" src="<%=request.getContextPath()%>/resources/images/overall.png"><br>${goods.quantity}
											<ul>
												<li><spring:message code="label.used"/><img src="<%=request.getContextPath()%>/resources/images/used.png"><br>${goods.usedQuantity}</li>
												<li><spring:message code="label.damaged"/><img src="<%=request.getContextPath()%>/resources/images/damaged.png"><br>${goods.damaged}</li>
												<li><spring:message code="label.returned"/><img src="<%=request.getContextPath()%>/resources/images/returned.png"><br>${goods.returned}</li>
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
 
.jOrgChart{
	overflow: scroll;
    height: 450px;
     border: 2px solid #828282;
 }
.jOrgChart .node{
	display:inline-block;
	width:150px;
	height: 67px;
	z-index:10;
	margin:0px 59px;
}
.node.ui-draggable.ui-droppable {
    margin-top: 4px;
    margin-bottom: 16px;
}
</style>