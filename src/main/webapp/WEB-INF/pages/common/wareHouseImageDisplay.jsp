<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
	
		<div class="col-sm-12 col-md-12 col-lg-12" id="tableDiv">
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center"><spring:message code="label.whList"/></h3>
		</div>
		
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
				<form:form action="wareHouseImageDisplayById" commandName="wareHouseForm">
				<table class="table data jqtable example" id="my-table">
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
								<td><form:checkbox  class="check" path="id" value="${warehouse.id}"/></td>
								<td><c:out value="${warehouse.wareHouseName}"></c:out></td>							
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.view"/>" class="btn btn-primary" ></td>
						</tr>
					</table>
		        </div>
		        </form:form>
		</div>
	<div class="warehouse" id="imageDiv" style="display:none;">
		<img src="<%=request.getContextPath()%>/resources/images/warehouse1.png">
		<div class="warehouse_name">
			<h3>${wareHouseForm.wareHouseName}</h3>
		</div>
		<div class="warehouse_data">
			<table width="200">
				<tr>
					<td><b><spring:message code="label.address"/></b></td>
					<td><b>${wareHouseForm.address}</b></td>
				</tr>
				<tr>
					<td><b><spring:message code="label.state"/></b></td>
					<td><b>${wareHouseForm.state}</b></td>
				</tr>
				<tr>
					<td><b><spring:message code="label.country"/></b></td>
					<td><b>${wareHouseForm.country}</b></td>
				</tr>
				<tr>
					<td><b><spring:message code="label.size"/>(mt<sup>2</sup>)</b></td>
					<td><b>${wareHouseForm.size}</b></td>
				</tr>
				<tr>
					<td><b><spring:message code="label.capacity"/></b></td>
					<td><b>${wareHouseForm.capacity}</b></td>
				</tr>
			</table>
		</div>
		
		<c:if test="${! empty inventoryList}">
						<div class="warehouse_table">
			<table width="600" style="text-align:center;">
				
					<c:forEach items="${inventoryList}" var="inventory"
						varStatus="status">
						<tr>
							<td>
								<div class="warehouse_profile"><img src="${inventory.imageName}" class="warehouse_img"></div>
							</td>

						</tr>
						<tr>
							<td>
								<div>
									<a href="#" id='modal-launcher' data-toggle="modal"
										data-target="#login-modal" class="open-dialog btn btn-primary" 
										data-id="${inventory.id}" 
										id="cancel"><spring:message code="label.upload" /></a>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div>
									<p><b>${inventory.goods}</b></p>
									<p><b>Used: </b>${inventory.usedQuantity}</p>
									<p><b>Returned: </b>${inventory.returned}</p>
									<p><b>Remaining: </b>${inventory.quantity}</p>
								</div>
							</td>
						</tr>
					</c:forEach>
				
			</table>
			</div>
			</c:if>
			
		
	</div>
	<div class="col-sm-12 col-md-12 col-lg-12" style="text-align:center;display:none;" id="back">
		<a href="wareHouseImageDisplay" class="btn btn-success" ><spring:message code="label.back"/></a>						
	</div>
</div>

 <section>
		<div class="container">
			<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header login_modal_header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h3 class="modal-title" id="myModalLabel"><spring:message code="label.uploadImage"/></h3>
							</div>
							<div class="modal-body login-modal">
								<div class="clearfix"></div>
								<div id='social-icons-conatainer'>
									<div class='modal-body-left'>
								<form:form action="updateImageForGoods"	commandName="inventoryForm" enctype="multipart/form-data">
									<div class="form-group">
										<form:hidden path="id" value="" id="id" />
										<form:hidden path="flag" value="0" />
										<form:hidden path="warehouseId" value="${wareHouseForm.id}" />
										<form:input path="file" type="file"	onchange="checkfile(this);" id="imageFile" />
									</div>									
									<input type="submit" class="btn btn-primary" value="Upload">
									<button class="btn" data-dismiss="modal" aria-hidden="true">
										<spring:message code="label.close" />
									</button>
								</form:form>
							</div>
								</div>																												
								<div class="clearfix"></div>
							</div>
      	
						</div>
					</div>
			</div>
        </div>
	</section> 
	
	<script>
	$(document).ready(function() {
		var status = '${wareHouseForm.status}';		
		if(status == '') {
			$("#tableDiv").show();
		    $("#imageDiv").hide();
		    $("#back").hide();
		}else {
			$("#tableDiv").hide();
		    $("#imageDiv").show();
		    $("#back").show();
		}
	});
	
	$('.check').on('change', function() {
	    $('.check').not(this).prop('checked', false);  
	});
	
	$(document).on("click", ".open-dialog", function () {
	     $(".modal-body #id").val( $(this).data('id') ); 
	});
	</script>
