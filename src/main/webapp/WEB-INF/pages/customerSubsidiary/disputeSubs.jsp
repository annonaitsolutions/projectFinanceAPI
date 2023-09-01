<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<script>

function same()
{
	var bankname =  document.getElementById('customer');
	
	if (document.getElementById('customer').value == ''){
		document.getElementById('customerError').style.display='block';
		canSubmit = false;
	}
	else{
		document.getElementById('customerError').style.display='none';
	}
	
	
	}
function val(){
	
	var bankname =  document.getElementById('masterKey');


	var canSubmit = true; 
	
	if (document.getElementById('masterKey').value == ''){
		document.getElementById('masterKeyError').style.display='block';
		canSubmit = false;
	}
	else{
		document.getElementById('masterKeyError').style.display='none';
	}
	
	
	if(canSubmit == false){
		return false;
	}
}

</script>


<section>

				
				<script type="text/javascript">
				
				function Test()
				{
                
				var p,r,i;

				p=parseFloat(document.interest.totalCost.value);
                r=parseFloat(document.interest.quantity.value);
                i=p/r;
                document.interest.costEachUnit.value= i;
				} 
				function Test1()
				{
					var p,r,i;
					p=parseFloat(document.interest.noOfDefect.value);
					r=parseFloat(document.interest.quantity.value);
					i=(p/r)*100;
					document.interest.defectPercentage.value= i;
					
					}
					
					function Test2()
					{
						var p,r,i;
						p=parseFloat(document.interest.noOfDefect.value);
						r=parseFloat(document.interest.costEachUnit.value);
						i=p*r;
						document.interest.costDefectiveGoods.value=i;
					}
				
			</script>
				
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 style="font-size: 19px; font-weight: 400; color: #2E9AFE;">Customer Information</h3>
				</div>
				<div class="col-sm-12 col-md-12 col-lg-12">
					<div  class="successMsg col-sm-11" style="text-align:center;color:green;font-size: 18px;">${successFull}</div>
				</div>
				
				<form:form action="disputeSubsConfirm" method="post" commandName="disputeForm" enctype="multipart/form-data"  name="interest" onsubmit="return val();">
				
				<form:hidden path ="transactionId" />
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
							<form:select path="goods"  class="form-control" placeholder="" style="width:85%" id="goodsCategory">
							<form:option value="">Select Goods Category</form:option>
								 <form:option value="automobiles">Automobiles</form:option>
								<form:option value="electronics">Electronics</form:option>
								</form:select>
						</div>
						
						<div class="col-sm-3"><b>Goods Information:</b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="goodsInfo"  class="form-control" placeholder="" style="width:85%" id="goodsInfo"/>
							<div id="goodsInfoError" style="display:none;color:red;">Goods Info is Mandatory</div>
					        <div id="goodsInfoError" style="display:none;color:red;">Goods Info is Mandatory</div>
						</div>
						
						<div class="col-sm-3"><b>Total Cost Of Goods:</b><span style="color:red">*</span></div>
						<div class="col-sm-9">
							<form:input path="totalCost"   class="form-control" placeholder="" style="width:85%" id="totalCost"/>
							<div id="totalCostError" style="display:none;color:red;">Cost is Mandatory</div>
					        <div id="totalCostError" style="display:none;color:red;">Cost is Mandatory</div>
						</div>
						
						<div class="col-sm-3"><b>Goods Quantity:</b><span style="color:red">*</span></div>
						<div class="col-sm-9">
							<form:input path="quantity"  class="form-control" placeholder="" style="width:85%" id="quantity"/>
							<div id="quantityError" style="display:none;color:red;">Quantity is Mandatory</div>
					        <div id="quantityError" style="display:none;color:red;">Quantity is Mandatory</div>
						</div>
						
						<div class="col-sm-3"><input type="button" value="Cost Of Each Unit" class="btn btn-default" style="color:#2E9AFE;font-weight: 600;" onclick="Test();"></div>
						<div class="col-sm-9">
							<form:input path="answer"  class="form-control" placeholder="" style="width:85%" id="costEachUnit"/>
													</div>
						<div class="col-sm-3"><b>Type Of Defect:</b><span style="color:red">*</span></div>
						<div class="col-sm-9">
							<form:select path="goodsDefect"  class="form-control" placeholder="" style="width:85%" id="goodsDefect">
								 <form:option value="Select Defect Type"></form:option>
								 <form:option value="Missing Goods">Missing Goods</form:option>
								<form:option value="Damaged Goods">Damaged Goods</form:option>
								<form:option value="Mismatched Goods">Mismatched Goods</form:option>
							</form:select>
						</div>
						
						<div class="col-sm-3"><b>Damage Summary:</b><span style="color:red"></span></div>
						<div class="col-sm-9">
							<form:input path="damageGoods"  class="form-control" placeholder="" style="width:85%" id="damageSummary"/>
						</div>
						
						<div class="col-sm-3"><b>Number Of Defective units:</b><span style="color:red">*</span></div>
						<div class="col-sm-9">
							<form:input path="noOfDefect"  class="form-control" placeholder="" style="width:85%" id="noOfDefect"/>
						</div>
						<div class="col-sm-12 col-md-12 col-lg-12">&nbsp;</div>
						<div class="col-sm-3"><input type="button" value="Defect Percentage(%)" class="btn btn-default" style="color:#2E9AFE;font-weight: 600;" onclick="Test1();"></div>
						<div class="col-sm-9">
							<form:input path="answer1"  class="form-control" placeholder="" style="width:85%" id="defectPercentage"/>
						</div>
						
						<div class="col-sm-3"><input type="button" value="Cost Of Defective Goods" class="btn btn-default" style="color:#2E9AFE;font-weight: 600;" onclick="Test2();"></div>
						<div class="col-sm-9">
							<form:input path="answer2"  class="form-control" placeholder="" style="width:85%" id="costDefectiveGoods"/>
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
			
				
				