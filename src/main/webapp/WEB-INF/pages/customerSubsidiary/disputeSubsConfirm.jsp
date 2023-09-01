<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>

function validateForm() {
	var d = new Date().getTime();
    var uuid = 'xxxxxx'.replace(/[xy]/g, function(c) {
        var r = (d + Math.random()*16)%16 | 0;
        d = Math.floor(d/16);
        return (c=='x' ? r : (r&0x3|0x8)).toString(16);
    });
    document.msplan.disputeKey.value=uuid;
}
</script>

<section>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 style="font-size: 19px; font-weight: 400; color: #2E9AFE;">Customer Information</h3>
				</div>
				<div class="col-sm-12 col-md-12 col-lg-12">
					<div  class="successMsg col-sm-11" style="text-align:center;color:green;font-size: 18px;">${successFull}</div>
				</div>
				
				<form:form action="disputeSubsPost" method="post"  commandName="disputeForm" enctype="multipart/form-data"  name="msplan" onsubmit="return validateForm()">
				
				<form:hidden path="disputeKey"  value=""/>
				
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
						
						<div class="col-sm-3"><b>Master Key:</b><span style="color:red">*</span></div>
						<div class="col-sm-9">
						 <div  class="successMsg" style="text-align:center;color:red;font-size: 18px;">${success}</div>
							<form:input path="masterKey" readonly="true" class="form-control" placeholder="Enter Master Key" style="width:85%" id="masterKey"/>
							
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
					<h3 style="font-size: 19px; font-weight: 400; color: #2E9AFE;">Goods Information</h3>
				</div>
						
						
						
						<div class="col-sm-3"><b>Goods Category:</b><span style="color:red">*</span></div>
							<div class="col-sm-9">
							<form:input path="goods" readonly="true" class="form-control" placeholder="" style="width:85%" id="goodsInfo"/>
													</div>
						
						<div class="col-sm-3"><b>Goods Information:</b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="goodsInfo" readonly="true" class="form-control" placeholder="" style="width:85%" id="goodsInfo"/>
													</div>
						
						<div class="col-sm-3"><b>Total Cost Of Goods:</b><span style="color:red">*</span></div>
						<div class="col-sm-9">
							<form:input path="totalCost" readonly="true"  class="form-control" placeholder="" style="width:85%" id="totalCost"/>
							
						</div>
						
						<div class="col-sm-3"><b>Goods Quantity:</b><span style="color:red">*</span></div>
						<div class="col-sm-9">
							<form:input path="quantity" readonly="true" class="form-control" placeholder="" style="width:85%" id="quantity"/>
							
						</div>
						
						<div class="col-sm-9">
							<form:input path="answer" readonly="true" class="form-control" placeholder="" style="width:85%" id="costEachUnit"/>
													</div>
						<div class="col-sm-3"><b>Type Of Defect:</b><span style="color:red">*</span></div>
						<div class="col-sm-9">
							<form:input path="goodsDefect" readonly="true" class="form-control" placeholder="" style="width:85%" id="damageSummary"/>
						</div>
						
						<div class="col-sm-3"><b>Damage Summary:</b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="damageGoods" readonly="true" class="form-control" placeholder="" style="width:85%" id="damageSummary"/>
						</div>
						
						<div class="col-sm-3"><b>Number Of Defective units:</b><span style="color:red">*</span></div>
						<div class="col-sm-9">
							<form:input path="noOfDefect" readonly="true" class="form-control" placeholder="" style="width:85%" id="noOfDefect"/>
						</div>
						<div class="col-sm-12 col-md-12 col-lg-12">&nbsp;</div>
						
						<div class="col-sm-9">
							<form:input path="answer1" readonly="true"  class="form-control" placeholder="" style="width:85%" id="defectPercentage"/>
						</div>
						<form:hidden path ="transactionId" />
						<div class="col-sm-9">
							<form:input path="answer2" readonly="true"  class="form-control" placeholder="" style="width:85%" id="costDefectiveGoods"/>
						</div>
				<div class="col-sm-12 col-md-12 col-lg-12">
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
			
				
				