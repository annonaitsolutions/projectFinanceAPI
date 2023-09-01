<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>

<script language="javascript">

function Val1()
{

var p,r,n,i;
if(document.interest.quantity.value == " " )
{
alert("PLEASE ENTER Quantity");
document.interest.quantity.alert();
}
else if(document.interest.usedQuantity.value=="")
{
alert("ENTER used quantity");
document.interest.usedQuantity.blur();

}
else if(document.interest.damaged.value=="")
{
alert("ENTER NUMBER OF damaged quantity");
document.interest.damaged.prompt();
}
else
{
p=document.interest.quantity.value;

r=document.interest.usedQuantity.value;

n=document.interest.damaged.value;

i=p-n-r;

document.interest.total.value= i;
}
}
</script>
</head>
<section>	


<form:form action="inventoryPoListForSubShowConfirm" name="interest" commandName="inventoryForm" onsubmit="return Val();">
<h1>Inventory Details</h1>
			<div class="content">
								<table class="theader">
								<div class="col-sm-3"><font color="Red"></font>ID</div>
					<div class="col-sm-7"><form:input path="id" readonly="true" style="width:100%"/></div>
					<div class="col-sm-11"><form:errors path="id" cssStyle="color:red"></form:errors></div>
				
					<div class="col-sm-3">Customer Name</div>
					<div class="col-sm-7"><form:input path="customerName" readonly="true" style="width:100%"/></div>
					<div class="col-sm-11"><form:errors path="customerName" cssStyle="color:red"></form:errors></div>
					
					<div class="col-sm-3">Customer Head Name</div>
					<div class="col-sm-7"><form:input path="customerHeadName" readonly="true" style="width:100%"/></div>
					<div class="col-sm-11"><form:errors path="customerHeadName" cssStyle="color:red"></form:errors></div>
				
				
					<div class="col-sm-3">Supplier Name</div>
					<div class="col-sm-7"><form:input path="supplierName" readonly="true" style="width:100%"/></div>
					<div class="col-sm-11"><form:errors path="supplierName" cssStyle="color:red"></form:errors></div>
				
					<div class="col-sm-3">Master Key</div>
					<div class="col-sm-7"><form:input path="masterKey" readonly="true" style="width:100%"/></div>
					<div class="col-sm-11"><form:errors path="masterKey" cssStyle="color:red"></form:errors></div>
					
					<div class="col-sm-3">Purchase Key</div>
					<div class="col-sm-7"><form:input path="poKey" readonly="true" style="width:100%"/></div>
					<div class="col-sm-11"><form:errors path="poKey" cssStyle="color:red"></form:errors></div>
				
					<div class="col-sm-3">Amount</div>
					<div class="col-sm-7"><form:input path="amount" readonly="true" style="width:100%"/></div>
					<div class="col-sm-11"><form:errors path="amount" cssStyle="color:red"></form:errors></div>
			
				<div class="col-sm-3">Total Quantity</div>
					<div class="col-sm-7"><form:input path="quantity"  style="width:100%"/></div>
					<div class="col-sm-11"><form:errors path="quantity" cssStyle="color:red"></form:errors></div>
					
				<div class="col-sm-3">Used Quantity</div>
					<div class="col-sm-7"><form:input path="usedQuantity" id="usedQuantity" style="width:100%"/></div>
					<td id="usedQuantityError" class="error" style="display:none;"><font color="red">*Please Enter Use Quantity</font></td>
					 <td id="usedQuantityError" class="error" style="display:none">Please Enter Use Quantity</td>
				
				
				<div class="col-sm-3">Damaged Quantity</div>
					<div class="col-sm-7"><form:input path="damaged" id="damaged" style="width:100%"/></div>
			        <td id="damagedError" class="error" style="display:none;"><font color="red">*Please Enter Damaged Quantity</font></td>
					 <td id="damagedError" class="error" style="display:none">Please Enter Damaged Quantity</td>
				
					 <td colspan="2"><input type="button" value="CALCULATE" size="5" onClick="Val1()">
                    <input type="reset" value="clear">

               </tr>
					
					<div class="col-sm-3">Quantity Remaining</div>
					<div class="col-sm-7"><form:input path="total" id="total"  style="width:100%"/></div>
				     <td id="totalError" class="error" style="display:none;"><font color="red">*Please Enter Calaulate Quantity Remaning</font></td>
					 <td id="totalError" class="error" style="display:none">Please Click on Calculate Button</td>
				
					
					
											<p align="center" style="padding-top: 10px;">
					<input type="submit" class="btn btn-primary" value="Confrim">
					
					<a href="bankEmp" class="btn btn-success" name="back"  onclick="javascript:window.location='/annona/appMng/buyerPage';"/>back</a>
				</p>
									</table>
									</div>
								


</form:form>
<script>
		
			
				 
				function Val(){
					
					var usedQuantity  = document.getElementById('usedQuantity');
					var damaged  = document.getElementById('damaged');
					var total  = document.getElementById('total');

					var canSubmit = true; 
					
					if (document.getElementById('usedQuantity').value == ''){
						document.getElementById('usedQuantityError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('usedQuantityError').style.display='none';
					}
					if (document.getElementById('damaged').value == ''){
						document.getElementById('damagedError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('damagedError').style.display='none';
					}
					
					if (document.getElementById('total').value == ''){
						document.getElementById('totalError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('totalError').style.display='none';
					}

					if(canSubmit == false){
						return false;
					}
				}
			
				
		</script>

</section>

