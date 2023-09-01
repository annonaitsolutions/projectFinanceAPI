<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.inventoryList"/></h3>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
				 <div  class="sucess"><b><font color="green">${success}</font></b></div>
			</div>
			 <div class="col-sm-12 col-md-12 col-lg-12">
			
				<span class="counter pull-right"></span>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.supplierName"/></th>
						<th><spring:message code="label.masterKey"/></th>
						<th><spring:message code="label.poKey"/></th>
						<th><spring:message code="label.goods"/></th>
						<th><spring:message code="label.amount"/></th>
						<th><spring:message code="label.quantityRemaining"/></th>
						<th><spring:message code="label.image"/></th>
						<th><spring:message code="label.action"/></th>		
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty inventoryList}">
						<c:forEach items="${inventoryList}" var="inventory">
							<tr>
								<td><c:out value="${inventory.id}"></c:out></td>
								<td><c:out value="${inventory.customerName}"></c:out>
								<td><c:out value="${inventory.supplierName}"></c:out>
								<td><c:out value="${inventory.masterKey}"></c:out></td>
								<td><c:out value="${inventory.poKey}"></c:out></td>
								<td><c:out value="${inventory.goods}"></c:out></td>
								<td><c:out value="${inventory.amount}"></c:out></td>								
								<td><c:out value="${inventory.total}"></c:out></td>	
								<td><img src="${inventory.imageName}" class="imageSize"></td>
								<td><a href="#" id='modal-launcher' data-toggle="modal" data-target="#login-modal" 
								       data-id="${inventory.id}" 	
									class="open-dialog btn btn-primary"  id="cancel" ><spring:message code="label.uploadImage"/></a></td>
					
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
</div>

</div>

	<section>
		<div class="container">
			<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header login_modal_header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h3 class="modal-title" id="myModalLabel">Upload image</h3>
							</div>
							<div class="modal-body login-modal">
								<div class="clearfix"></div>
								<div id='social-icons-conatainer'>
									<div class='modal-body-left'>
										<form:form action="updateImageForGoods" commandName="inventoryForm" enctype="multipart/form-data">
										<div class="form-group">
										<form:hidden path="id" value="" id="id"/>
											
											<form:input path="file"  type="file" onchange="checkfile(this);" id="imageFile" />											
										</div>
										
										<input type="submit" class="btn btn-primary" value="Upload">
										<button class="btn" data-dismiss="modal" aria-hidden="true"><spring:message code="label.close"/></button>
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
	function checkfile(sender) {
		var validExts = new Array(".bmp", ".dib",".JPG", ".jpg", ".jpeg", ".jpe", ".jfif", ".gif", ".tif", ".tiff", ".png");
		 var fileExt = sender.value;
		 fileExt = fileExt.substring(fileExt.lastIndexOf('.'));
			if (validExts.indexOf(fileExt) < 0) {
		alert("Invalid file selected, valid files are of " +
		validExts.toString() + " types.");
		var fld = document.getElementById("imageFile");
		
		fld.form.reset();
		fld.focus();
		}
		}
	
	$(document).on("click", ".open-dialog", function () {
	     $(".modal-body #id").val( $(this).data('id') );
	    
	     
	});
	</script>
	<style>
		.imageSize
		   { height: 60px;
		    width: 60px;
		    max-width: 61px;
		    min-height: 61px;
		   }
	</style>