<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 body_fixed">
			
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center"><spring:message code="label.invoiceList"/></h3>
		</div>
		
		<div class="col-sm-12 col-md-12 col-lg-12">
			 <div  class="successMsg"><b><font color="green">${success}</font></b></div>
	 	</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
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
						<th><spring:message code="label.action"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty invoiceList}">
						<c:forEach items="${invoiceList}" var="master">
							<tr>
								<td><c:out value="${master.id}"></c:out></td>
								<td><c:out value="${master.customerName}"></c:out>
								<td><c:out value="${master.buyerName}"></c:out>
								<td><c:out value="${master.poKey}"></c:out>
								<td><c:out value="${master.goods}"></c:out>
								<td><c:out value="${master.quantity}"></c:out>
								<td><c:out value="${master.requestMoney}"></c:out>
								<td><a href="#" id='modal-launcher' data-toggle="modal" data-target="#login-modal" 
								       data-id="${master.id}" data-name="${master.buyerName}"
									class="open-rateDialog btn btn-primary"  id="cancel" ><spring:message code="label.close"/></a>
								</td>
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
								<h3 class="modal-title" id="myModalLabel">Rate this Buyer</h3>
							</div>
							<div class="modal-body login-modal">
								<div class="clearfix"></div>
								<div id='social-icons-conatainer'>
									<div class='modal-body-left'>
										<form:form action="updateRateForBuyer" commandName="newBuyerForm" >
										<div class="form-group">
											<form:hidden path="id" id="id" value="" />
											Buyer Name :<form:input path="buyerName" id="buyerName" value="" class="form-control" readonly="true"/>											
										</div>
						
										<div class="form-group">
										<form:hidden path="rate" id="rate" value="" />
											Rating : <div class="basic" data-average="5"></div>
										</div>	
										<input type="submit" class="btn btn-primary" value="Rate">
										<a href="#" onClick="closeInvoice(document.getElementById('id').value)" class="btn">Not Now</a>
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
<script type="text/javascript">
function confirm(){
	  if (confirm("Are you sure you want to Close?")){
	    return true;
	  }else{
	    return false;
	  }
	}
	
$(document).ready(function() {
	
	$(".basic").jRating({
		onClick : function(element, rate) {				
			$('#rate').val(rate);				
		}
	})
});

$(document).on("click", ".open-rateDialog", function () {
     $(".modal-body #id").val( $(this).data('id') );
     $(".modal-body #buyerName").val( $(this).data('name') );
     
});

function validateForm() {
	var canSubmit = true;
	var rate = document.getElementById("rate").value;

	var id = document.getElementById('id').value;
	if(rate == "") {
		document.getElementById("rateError").style.display='block';
		canSubmit = false;
	}else {
		document.getElementById("rateError").style.display='none';
	}

	if(canSubmit == false) {
		return false;
	}else {			
		closeInvoice(id);
	}
}
function closeInvoice(id) {		
	location.href = "closeInvoiceBranch?id="+id;
}
</script>