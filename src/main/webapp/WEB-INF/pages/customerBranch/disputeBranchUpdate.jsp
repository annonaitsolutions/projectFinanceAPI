<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
				<div class="col-sm-12 col-md-12 col-lg-12">
					<div  class="successMsg col-sm-11" style="text-align:center;color:green;font-size: 18px;">${successFull}</div>
				</div>
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 style="font-size: 19px; font-weight: 400;"><spring:message code="label.customerInfo"/></h3>
				</div>
				
				
				<form:form action="disputeBranchUpdateConfirm" method="post" commandName="disputeForm"   onsubmit="return val();">
				
				<form:hidden path ="transactionId" />
				<form:hidden path ="id" />
						<div class="col-sm-3"><b><spring:message code="label.customerName"/>:</b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="customerName" readonly="true" class="form-control" placeholder="" style="width:85%" id="customer" onclick="same();"/>
						
						<div id="customerError" style="display:none;color:red;"><font color="red"><spring:message code="label.validation"/></font></div>
					        <div id="customerError" style="display:none;color:red;"><spring:message code="label.validation"/></div>
					        </div>
			
						<div class="col-sm-3"><b><spring:message code="label.customerEmail"/>:</b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="customerEmail" readonly="true" class="form-control" placeholder="Enter Email" style="width:85%" id="customerEmail"/>
						</div>
						
					
						
						<div class="col-sm-3"><b><spring:message code="label.poKey"/>:</b><span style="color:red">*</span></div>
						<div class="col-sm-9">
							<form:input path="pokey" readonly="true" class="form-control" placeholder="Enter PO Key" style="width:85%" id="purchaseOrderKey"/>
							
						</div>
				
						
						<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
							<h3 style="font-size: 19px; font-weight: 400;"><spring:message code="label.suppInfo"/></h3>
						</div>
						
						<div class="col-sm-3"><b><spring:message code="label.supplierName"/>:</b><span style="color:red">*</span></div>
						<div class="col-sm-9">
							<form:input path="supplierName" readonly="true" class="form-control" placeholder="Enter Supplier name" style="width:85%" id="supplierName"/>
							
						</div>
						
						<div class="col-sm-3"><b><spring:message code="label.email"/>:</b><span style="color:red">*</span></div>
						<div class="col-sm-9">
							<form:input path="supplierEmail" readonly="true"  class="form-control" placeholder="Enter Supplier Email" style="width:85%" id="supplierEmail"/>
						
						</div>
						
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 style="font-size: 19px; font-weight: 400;"><spring:message code="label.disputeInfo"/></h3>
				</div>
						
						
						
						<div class="col-sm-3"><b><spring:message code="label.disputeStatus"/>:</b><span style="color:red">*</span></div>
						<div class="col-sm-9">
							<form:select path="disputeStatus"  class="form-control" placeholder="" style="width:85%" id="disputeStatus">
							<form:option value=""><spring:message code="label.selectValue"/></form:option>
								 <form:option value="Settled"><spring:message code="label.settled"/></form:option>
								<form:option value="Revoked"><spring:message code="label.revoked"/></form:option>
								<form:option value="Pending"><spring:message code="label.pending"/></form:option>
								</form:select>
								     <div id="disputeStatusError" class="error col-sm-12" style="display:none;"><font color="red"><spring:message code="label.plzSelectValue"/></font></div>
							<div id="disputeStatusError" class="error" style="display:none"><spring:message code="label.plzSelectValue"/></div>
						</div>
						<div class="col-sm-3"><b><spring:message code="label.reason"/>:</b><span style="color:red">*</span></div>
						<div class="col-sm-9">
							<form:input path="comment"   class="form-control" placeholder="Enter Comment" style="width:85%" id="comment"/>
						   <div id="commentError" class="error col-sm-12" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></div>
							<div id="commentError" class="error" style="display:none"><spring:message code="label.validation"/></div>
						</div>
					<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="disputeBranchUpdateList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
					</form:form>
				</div>
<script>

function val() {

		var disputeStatus = document.getElementById('disputeStatus');
		var comment = document.getElementById('comment');
	
		var canSubmit = true;

		if (document.getElementById('disputeStatus').value == '') {
			document.getElementById('disputeStatusError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('disputeStatusError').style.display = 'none';
		}

		if (document.getElementById('comment').value == '') {
			document.getElementById('commentError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('commentError').style.display = 'none';
		}
		
		if (canSubmit == false) {
			return false;
		}
	}
	
</script>							
				