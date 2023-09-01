<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<h3 align="center"><spring:message code="label.masterPlanList"/></h3>
		</div>
	<div class="col-sm-12 col-md-12 col-lg-12">
		<table class="table table-hover jqtable" id="bootstrap-table">
				<thead>
					<tr>
						<th><spring:message code="label.masterKey"/></th>	
						<th><spring:message code="label.category"/></th>					
						<th><spring:message code="label.action"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty masterPlanList}">
						<c:forEach items="${masterPlanList}" var="master">
							<tr>
								<td><c:out value="${master.masterKey}"></c:out></td>
								<td><c:out value="${master.category}"></c:out>
								
								<td><a href="generateMasterPlanChart?id=${master.id}"><spring:message code="label.ghView"/></a></td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 	
	</div>
	
	<div class="col-sm-12 col-md-12 col-lg-12">
		<div onload="prettyPrint();">
		
			<div class="col-sm-12 col-md-12 col-lg-12">
						<h3 align="center"><spring:message code="label.mpChart"/></h3>
			</div>
		
			<ul id="org" style="display: none">
			<c:forEach var="plan" items="${model.planChart}">
		    		<li><br>
		    		<table class="table table-bordered" align="center" width="400">
					<tr>
					<td><spring:message code="label.masterKey"/></td>
					<td><spring:message code="label.customer"/></td>
					<td><spring:message code="label.product"/></td>
					</tr>
				
					<tr>
					<td>${plan.key.masterKey}</td>
					<td>${plan.key.customer}</td>
					<td>${plan.key.product}</td>
					</tr>
					</table>
		    		<br>
			    		<ul>
			    		<c:if test="${! empty plan.value.poList}">
			    			<c:forEach var="po" items="${plan.value.poList}">
			    				<li><table class="table table-bordered" align="center" width="800">
										<tr>
											<td><spring:message code="label.poKey" /></td>
											<td>${po.key.poKey}</td></tr>
											<tr>
											<td><spring:message code="label.supplier" /></td>
											<td>${po.key.supplierName}</td>
											</tr>											
											<tr>											
											<td><spring:message code="label.quantity" /></td>
											<td>${po.key.quantity}</td>
											</tr>
									</table>
									<ul>
									
									<c:if test="${! empty po.value.limitBurstList}">
										<c:forEach var="limit" items="${po.value.limitBurstList}">
										<li><table class="table table-bordered" align="center" width="400">
										<tr>
											<td><spring:message code="label.limitdate"/></td>
											<td>${limit.limitDate}</td></tr>
											<tr>
											<td><spring:message code="label.reqAmt"/></td>
											<td>${limit.reqAmt}</td>	
											</tr>
										<tr>
											<td><spring:message code="label.finalAmount"/></td>									
											<td>${limit.finalAmt}</td>
										</tr>
										</table>
											</li>
											
										</c:forEach>
										</c:if>
										<c:if test="${not empty po.value.poUploadList}">										
										<c:forEach var="upload" items="${po.value.poUploadList}">
										<li><table class="table table-bordered" align="center" width="400">
											<tr>
												<td><spring:message code="label.uploadDate"/></td>
												<td>${upload.uploadDate}</td></tr>
												<tr>
												<td><spring:message code="label.reason"/></td>
												<td>${upload.reason}</td>												
											</tr>
											<tr>
												<td><spring:message code="label.status"/></td>												
												<td>${upload.status}</td>
											</tr>
										</table>
											</li>
										</c:forEach>
										</c:if>
									</ul>							
								</li>
								
			    			</c:forEach>
			    			</c:if>
			    			<c:if test="${! empty plan.value.invoiceList}">
			    			<c:forEach var="invoice" items="${plan.value.invoiceList}">
			    				<li><table class="table table-bordered" align="center" width="400">
										<tr>
											<td><spring:message code="label.invoiceKey" /></td>
											<td>${invoice.poKey}</td></tr>
											<tr>
											<td><spring:message code="label.buyerName" /></td>
											<td>${invoice.buyerName}</td>
											</tr>											
											<tr>											
											<td><spring:message code="label.amount" /></td>
											<td>${invoice.amount}</td>
											</tr>
									</table>															
								</li>
			    			</c:forEach>
			    			</c:if>
			    		</ul>
		    		</li>
			</c:forEach>
		
			</ul>
		
			<div id="chart" class="orgChart"></div>
		</div>
</div>
</div>
<script>
        jQuery(document).ready(function() {
            
            /* Custom jQuery for the example */
            $("#show-list").click(function(e){
                e.preventDefault();
                
                $('#list-html').toggle('fast', function(){
                    if($(this).is(':visible')){
                        $('#show-list').text('Hide underlying list.');
                        $(".topbar").fadeTo('fast',0.9);
                    }else{
                        $('#show-list').text('Show underlying list.');
                        $(".topbar").fadeTo('fast',1);                  
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
     width: 394px;
   	 height: 67px;
  	 z-index: 19;
  	 margin: 0 159px;
}
.jOrgChart{
	    overflow: scroll;
        border: 2px solid #828282;
        height: 364px;
}
</style>