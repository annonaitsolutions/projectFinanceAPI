<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<section>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 style="font-size: 19px; font-weight: 400; color: #2E9AFE;">Customer Information</h3>
				</div>
				<div class="col-sm-12 col-md-12 col-lg-12">
					<div  class="successMsg col-sm-11" style="text-align:center;color:green;font-size: 18px;">${successFull}</div>
				</div>
				
				<form:form action="disputeSubsUpdateConfirm" method="post" commandName="disputeForm"   onsubmit="return val();">
				
				<form:hidden path ="transactionId" />
				<form:hidden path ="id" />
						<div class="col-sm-3"><b>Customer:</b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="customerName" readonly="true" class="form-control" placeholder="" style="width:85%" id="customer" onclick="same();"/>
						
						<div id="customerError" style="display:none;color:red;"><font color="red">Name should be Alphanumeric, Example: William George Jr2 (Maximum 30 Characters)</font></div>
					        <div id="customerError" style="display:none;color:red;">Master Key is Mandatory</div>
					        </div>
			
						<div class="col-sm-3"><b>Customer Email:</b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="customerEmail" readonly="true" class="form-control" placeholder="Enter Email" style="width:85%" id="customerEmail"/>
						</div>
						
						<div class="col-sm-3"><b>Dispute Key:</b><span style="color:red">*</span></div>
						<div class="col-sm-9">
						 <div  class="successMsg" style="text-align:center;color:red;font-size: 18px;">${success}</div>
							<form:input path="disputeKey" readonly="true" class="form-control" placeholder="Enter Master Key" style="width:85%" id="masterKey"/>
							
						</div>
						
						<div class="col-sm-3"><b>Purchase Order Key:</b><span style="color:red">*</span></div>
						<div class="col-sm-9">
							<form:input path="pokey" readonly="true" class="form-control" placeholder="Enter PO Key" style="width:85%" id="purchaseOrderKey"/>
							
						</div>
				
						
						<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
							<h3 style="font-size: 19px; font-weight: 400; color:#2E9AFE;">Supplier Information</h3>
						</div>
						
						<div class="col-sm-3"><b>Name:</b><span style="color:red">*</span></div>
						<div class="col-sm-9">
							<form:input path="supplierName" readonly="true" class="form-control" placeholder="Enter Supplier name" style="width:85%" id="supplierName"/>
							
						</div>
						
						<div class="col-sm-3"><b>Email:</b><span style="color:red">*</span></div>
						<div class="col-sm-9">
							<form:input path="supplierEmail" readonly="true"  class="form-control" placeholder="Enter Supplier Email" style="width:85%" id="supplierEmail"/>
						
						</div>
						
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 style="font-size: 19px; font-weight: 400; color: #2E9AFE;">Dispute Info</h3>
				</div>
						
						
						
						<div class="col-sm-3"><b>Dispute Status:</b><span style="color:red">*</span></div>
						<div class="col-sm-9">
							<form:select path="disputeStatus"  class="form-control" placeholder="" style="width:85%" id="goodsCategory">
							<form:option value="">Select Goods Category</form:option>
								 <form:option value="Settled">Settled</form:option>
								<form:option value="Revoked">Revoked</form:option>
								<form:option value="Pending">Pending</form:option>
								</form:select>
						</div>
						<div class="col-sm-3"><b>Reason:</b><span style="color:red">*</span></div>
						<div class="col-sm-9">
							<form:input path="comment"   class="form-control" placeholder="Enter Comment" style="width:85%" id="supplierEmail"/>
						
						</div>
					<table align="center">	
								<tr>
								<td>
								<input type="submit" class="btn btn-primary" value="Save"></td>
								<td>
								<a href="user" class="btn btn-success" name="back"  onclick="javascript:window.location='#';">Back</a></td>
								</tr>
							
					</table>	
				</div>
				</form:form>
				
				
				
				
	</div>
			
				
				