<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>



<script>

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
<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center"><spring:message code="label.invoiceDetails"/></h3>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div  class="successMsg" style="text-align:center;color:green;font-size: 18px;">${success}</div>
			</div>	


<form:form action="inventoryInvoiceBranchShowConfirm" name="interest" commandName="inventoryForm" onsubmit="return Val();">

			<div class="col-sm-12 col-md-12">
				<table align="center">
				<%-- <tr>					
					<td class="col-sm-6"><font color="Red"></font><spring:message code="label.id"/></td>
					<td class="col-sm-6"><form:input path="id" readonly="true"/></td>
					<td class="col-sm-12"><form:errors path="id" cssStyle="color:red"></form:errors></td>
				</tr> --%>
				<tr>
					<td class="col-sm-6"><spring:message code="label.customerName"/></td>
					<td class="col-sm-6"><form:input path="customerName" readonly="true"/></td>
					<td class="col-sm-12"><form:errors path="customerName" cssStyle="color:red"></form:errors></td>
				</tr>
				<tr>	
					<td class="col-sm-6"><spring:message code="label.customerHeadName"/></td>
					<td class="col-sm-6"><form:input path="customerHeadName" readonly="true"/></td>
					<td class="col-sm-12"><form:errors path="customerHeadName" cssStyle="color:red"></form:errors></td>
				</tr>
				<tr>
					<td class="col-sm-6"><spring:message code="label.buyerName"/></td>
					<td class="col-sm-6"><form:input path="buyerName" readonly="true"/></td>
					<td class="col-sm-12"><form:errors path="buyerName" cssStyle="color:red"></form:errors></td>
				</tr>
				<tr>
					<td class="col-sm-6"><spring:message code="label.masterKey"/></td>
					<td class="col-sm-6"><form:input path="masterKey" readonly="true"/></td>
					<td class="col-sm-12"><form:errors path="masterKey" cssStyle="color:red"></form:errors></td>
				</tr>
				<tr>	
					<td class="col-sm-6"><spring:message code="label.poKey"/></td>
					<td class="col-sm-6"><form:input path="poKey" readonly="true"/></td>
					<td class="col-sm-12"><form:errors path="poKey" cssStyle="color:red"></form:errors></td>
				</tr>
				<tr>	
					<td class="col-sm-6"><spring:message code="label.goods"/></td>
					<td class="col-sm-6"><form:input path="goods" readonly="true"/></td>
				</tr>
				<tr>
					<td class="col-sm-6"><spring:message code="label.amount"/></td>
					<td class="col-sm-6"><form:input path="amount" readonly="true"/></td>
					<td class="col-sm-12"><form:errors path="amount" cssStyle="color:red"></form:errors></td>
				</tr>
				<tr>
						<td class="col-sm-6"><b><spring:message code="label.wareHouse"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
						<td class="col-sm-6"><form:select id="wareHouseName" path="wareHouseName" style="width:205px;">
						<form:option value=""><spring:message code="label.selectValue"/></form:option>
								<form:options items="${inventoryForm.wareHouseList}"
									itemValue="wareHouseName" itemLabel="wareHouseName" />
							</form:select>
							<div id="wareHouseNameError" style="display: none; color: red;"><spring:message code="label.validation"/>
									</div>
								<div id="wareHouseNameError" style="display: none; color: red;"><spring:message code="label.validation"/>
									</div></td>
							
					</tr>
				<tr>
					<td class="col-sm-6"><spring:message code="label.totalQuantity"/></td>
					<td class="col-sm-6"><form:input path="quantity"/></td>
					<td class="col-sm-12"><form:errors path="quantity" cssStyle="color:red"></form:errors></td>
				</tr>
				<tr>	
					<td class="col-sm-6"><spring:message code="label.usedQuantity"/></td>
					<td class="col-sm-6"><form:input path="usedQuantity" id="usedQuantity"/></td>
					<td id="usedQuantityError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
					 <td id="usedQuantityError" class="error" style="display:none"><spring:message code="label.validation"/></td>
				</tr>
				<tr>
					<td class="col-sm-6"><spring:message code="label.damagedQuantity"/></td>
					<td class="col-sm-6"><form:input path="damaged" id="damaged"/></td>
			        <td id="damagedError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
					<td id="damagedError" class="error" style="display:none"><spring:message code="label.validation"/></td>
				</tr>
				<tr>
					 <td colspan="2"><input type="button" value="CALCULATE" size="5" onClick="Val1()" class="btn btn-primary">
                    <input type="reset" value="clear" class="btn btn-primary"></td>
				</tr>
            
					<tr>
					<td class="col-sm-6"><spring:message code="label.quantityRemaining"/></td>
					<td class="col-sm-6"><form:input path="total" id="total" readonly="true"/></td>
				     <td id="totalError" class="error" style="display:none;"><font color="red"><spring:message code="label.masterKey"/><spring:message code="label.validation"/></font></td>
					 <td id="totalError" class="error" style="display:none"><spring:message code="label.masterKey"/><spring:message code="label.validation"/></td>
				</tr>	
					
					
									</table>
				<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="inventoryInvoiceBranchList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
									</div>
								


</form:form>
</div>
<script>
		function Val(){
					
				var usedQuantity  = document.getElementById('usedQuantity');
				var damaged  = document.getElementById('damaged');
				var returned  = document.getElementById('returned');
				var number='^\\d+$';
				var number= /(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/;
				var number1= /^-?[0-9]+$/;
				var canSubmit = true; 
				
				
				
				if(usedQuantity.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || usedQuantity.value.match(/^-?[0-9]+$/) &&!(document.getElementById('usedQuantity').value == ''))
			  	{
			  		
			  		document.getElementById('usedQuantityError').style.display='none';
			  	
			  	}
			  	else{
			  		document.getElementById('usedQuantityError').style.display='block';
			  	     canSubmit = false;
			  	}
				
				
				if(damaged.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || damaged.value.match(/^-?[0-9]+$/) &&!(document.getElementById('damaged').value == ''))
			  	{
			  		
			  		document.getElementById('damagedError').style.display='none';
			  	
			  	}
			  	else{
			  		document.getElementById('damagedError').style.display='block';
			  	     canSubmit = false;
			  	}
				
				
				if(total.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || total.value.match(/^-?[0-9]+$/) &&!(document.getElementById('total').value == ''))
				  	{
				  		
				  		document.getElementById('totalError').style.display='none';
				  	
				  	}
				  	else{
				  		document.getElementById('totalError').style.display='block';
				  	     canSubmit = false;
				  	}
					
				

				if(canSubmit == false){
					return false;
				}
			}
</script>



