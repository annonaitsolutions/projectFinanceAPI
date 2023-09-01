<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>

function val() {

	      /*  var d = new Date().getTime();
			var uuid = 'xxxxxx'.replace(/[xy]/g, function(c) {
				var r = (d + Math.random() * 16) % 16 | 0;
				d1 = Math.floor(d / 16);
				return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
			});
			document.dispute.transactionId.value = uuid; */
		
	}
	
</script>				
						
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 style="font-size: 19px; font-weight: 400;"><spring:message code="label.customerInfo"/></h3>
				</div>
				<div class="col-sm-12 col-md-12 col-lg-12">
					<div  class="successMsg col-sm-11" style="text-align:center;color:green;font-size: 18px;">${successFull}</div>
				</div>
				
				<form:form action="disputeHeadUpdatePost" method="post" commandName="disputeForm" name="dispute"  onsubmit="return val();">
				
				<form:hidden path ="transactionId" value="" />
				<form:hidden path ="id" />
						<div class="col-sm-3"><b><spring:message code="label.customerName"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="customerName" readonly="true" class="form-control" placeholder="" style="width:85%" id="customer" onclick="same();"/>
						
						<div id="customerError" style="display:none;color:red;"><font color="red">Name should be Alphanumeric, Example: William George Jr2 (Maximum 30 Characters)</font></div>
					        <div id="customerError" style="display:none;color:red;">Master Key is Mandatory</div>
					        </div>
			
						<div class="col-sm-3"><b><spring:message code="label.customerEmail"/></b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="customerEmail" readonly="true" class="form-control" placeholder="Enter Email" style="width:85%" id="customerEmail"/>
						</div>
						
					
						
						<div class="col-sm-3"><b><spring:message code="label.poKey"/></b><span style="color:red">*</span></div>
						<div class="col-sm-9">
							<form:input path="pokey" readonly="true" class="form-control" placeholder="Enter PO Key" style="width:85%" id="purchaseOrderKey"/>
							
						</div>
				
						
						<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
							<h3 style="font-size: 19px; font-weight: 400;"><spring:message code="label.suppInfo"/></h3>
						</div>
						
						<div class="col-sm-3"><b><spring:message code="label.name"/></b><span style="color:red">*</span></div>
						<div class="col-sm-9">
							<form:input path="supplierName" readonly="true" class="form-control" placeholder="Enter Supplier name" style="width:85%" id="supplierName"/>
							
						</div>
						
						<div class="col-sm-3"><b><spring:message code="label.email"/></b><span style="color:red">*</span></div>
						<div class="col-sm-9">
							<form:input path="supplierEmail" readonly="true"  class="form-control" placeholder="Enter Supplier Email" style="width:85%" id="supplierEmail"/>
						
						</div>
						
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 style="font-size: 19px; font-weight: 400;"><spring:message code="label.disputeInfo"/></h3>
				</div>
						
						
						
						<div class="col-sm-3"><b><spring:message code="label.disputeStatus"/></b><span style="color:red">*</span></div>
						<div class="col-sm-9">
							<form:input path="disputeStatus" readonly="true"  class="form-control" placeholder="Enter Status" style="width:85%" id="supplierEmail"/>
						
						</div>
						<div class="col-sm-3"><b><spring:message code="label.reason"/></b><span style="color:red">*</span></div>
						<div class="col-sm-9">
							<form:input path="comment" readonly="true"  class="form-control" placeholder="Enter Comment" style="width:85%" id="supplierEmail"/>
						
						</div>
					<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.update"/>" class="btn btn-primary"></td>
							<td><a href="disputeHeadUpdateList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>	
				
				</form:form>
				
	</div>
			
		
				