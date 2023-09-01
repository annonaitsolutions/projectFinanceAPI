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
if(document.interest.avail.value == " " )
{
alert("PLEASE ENTER Quantity");
document.interest.avail.alert();
}
else if(document.interest.usedQuantity.value=="")
{
alert("ENTER used quantity");
document.interest.usedQuantity.blur();

}
// else if(document.interest.damaged.value=="")
// {
// alert("ENTER NUMBER OF damaged quantity");
// document.interest.damaged.prompt();
// }
// else if(document.interest.returned.value=="")
// {
// alert("ENTER NUMBER OF returned quantity");
// document.interest.returned.prompt();
// }
else
{
p=document.interest.avail.value;

r=document.interest.usedQuantity.value;

// n=document.interest.damaged.value;

// t=document.interest.returned.value;

i=p-r;

document.interest.total.value= i;
}
}
</script>
</head>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.inventoryDetails"/></h3>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
				 <div  class="sucess"><b><font color="green">${success}</font></b></div>
			</div>
			 <div class="col-sm-12 col-md-12 col-lg-12">


<form:form action="inventoryUpdateShowConfirm" name="interest" commandName="inventoryForm" onsubmit="return Val();">

			<div class="col-sm-12 col-md-12">
				<table align="center">
								<form:hidden path="id"/>
			<%-- 	<tr>
					<td class="col-sm-6"><font color="Red"></font><spring:message code="label.id"/></td>
					<td class="col-sm-6"><form:input path="id" readonly="true"/></td>
					<td class="col-sm-11"><form:errors path="id" cssStyle="color:red"></form:errors></td>
				</tr> --%>
				<tr>
					<td class="col-sm-6"><spring:message code="label.customerName"/></td>
					<td class="col-sm-6"><form:input path="customerName" readonly="true"/></td>
					<td class="col-sm-11"><form:errors path="customerName" cssStyle="color:red"></form:errors></td>
				</tr>
				<tr>	
					<td class="col-sm-6"><spring:message code="label.customerHeadName"/></td>
					<td class="col-sm-6"><form:input path="customerHeadName" readonly="true"/></td>
					<td class="col-sm-11"><form:errors path="customerHeadName" cssStyle="color:red"></form:errors></td>
				</tr>
				<tr>
				
					<td class="col-sm-6"><spring:message code="label.supplierName"/></td>
					<td class="col-sm-6"><form:input path="supplierName" readonly="true"/></td>
					<td class="col-sm-11"><form:errors path="supplierName" cssStyle="color:red"></form:errors></td>
				</tr>
			
				<tr>	
					<td class="col-sm-6"><spring:message code="label.poKey"/></td>
					<td class="col-sm-6"><form:input path="poKey" readonly="true"/></td>
					<td class="col-sm-11"><form:errors path="poKey" cssStyle="color:red"></form:errors></td>
				</tr>
				<tr>
					<td class="col-sm-6"><spring:message code="label.amount"/></td>
					<td class="col-sm-6"><form:input path="amount" readonly="true"/></td>
					<td class="col-sm-11"><form:errors path="amount" cssStyle="color:red"></form:errors></td>
				</tr>
				<tr>
					<td class="col-sm-6"><spring:message code="label.totalQuantity"/></td>
					<td class="col-sm-6"><form:input path="quantity" readonly="true"/></td>
					<td class="col-sm-11"><form:errors path="quantity" cssStyle="color:red"></form:errors></td>
				</tr>
				<tr>	
					<td class="col-sm-6"><spring:message code="label.availableQuantity"/></td>
					<td class="col-sm-6"><form:input path="avail" readonly="true"/></td>
					<td class="col-sm-11"><form:errors path="avail" cssStyle="color:red"></form:errors></td>
				</tr>
				<tr>	
					<td class="col-sm-6"><spring:message code="label.usedQuantity"/></td>
					<td class="col-sm-6"><form:input path="usedQuantity" id="usedQuantity"/></td>
					<td id="usedQuantityError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
					<td id="usedQuantityError" class="error" style="display:none"><spring:message code="label.validation"/></td>
				</tr>
<%--				<tr>--%>
<%--					<td class="col-sm-6"><spring:message code="label.returnedQuantity"/></td>--%>
<%--					<td class="col-sm-6"><form:input path="returned" id="returned"/></td>--%>
<%--					<td id="usedQuantityError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>--%>
<%--					 <td id="usedQuantityError" class="error" style="display:none"><spring:message code="label.validation"/></td>--%>
<%--				</tr>--%>
<%--				<tr>	 --%>
<%--					<td class="col-sm-6"><spring:message code="label.damagedQuantity"/></td>--%>
<%--					<td class="col-sm-6"><form:input path="damaged" id="damaged"/></td>--%>
<%--			        <td id="damagedError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>--%>
<%--					 <td id="damagedError" class="error" style="display:none"><spring:message code="label.validation"/></td>--%>
<%--				</tr>--%>
				<tr>
					 <td colspan="2"><input type="button" value="CALCULATE" size="5" onClick="Val1()" class="btn btn-primary">
                    <input type="reset" value="clear" class="btn btn-primary"></td>
				</tr>
               
				<tr>	
					<td class="col-sm-6"><spring:message code="label.quantityRemaining"/></td>
					<td class="col-sm-6"><form:input path="total" id="total" readonly="true"/></td>
				     <td id="totalError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation2"/></font></td>
					 <td id="totalError" class="error" style="display:none"><spring:message code="label.validation2"/></td>
				</tr>
					<form:hidden path="inventoryType"/>
					
			
									</table>
					<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="InventoryPoList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
									</div>
								


</form:form>
<script>
		
			
				 
				function Val(){
					
					var usedQuantity  = document.getElementById('usedQuantity');
					// var damaged  = document.getElementById('damaged');
					var total  = document.getElementById('total');

					var canSubmit = true; 
					
					if (document.getElementById('usedQuantity').value == ''){
						document.getElementById('usedQuantityError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('usedQuantityError').style.display='none';
					}
					// if (document.getElementById('damaged').value == ''){
					// 	document.getElementById('damagedError').style.display='block';
					// 	canSubmit = false;
					// }
					// else{
					// 	document.getElementById('damagedError').style.display='none';
					// }
					
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

</div>
</div>

