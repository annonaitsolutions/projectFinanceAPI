<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
function radio_check()
{
if(document.regulation.regulation[1].checked){
	alert("Fund Transfer To Subsidiary");
document.getElementById("l1").disabled=true;
}else{
document.getElementById("l1").disabled=false;
}
if(document.regulation.regulation[0].checked){
	alert("Fund Transfer To Branch");
	document.getElementById("l2").disabled=true;
}
else{
	document.getElementById("l2").disabled=false;
}
}

$(function () {
    $('#l1').multiselect({
        includeSelectAllOption: true
    });
    $('#btnSelected').click(function () {
        var selected = $("#l1 option:selected");
        var message = "";
        selected.each(function () {
            message += $(this).text() + " " + $(this).val() + "\n";
        });
        alert(message);
    });
});
$(function () {
    $('#l2').multiselect({
        includeSelectAllOption: true
    });
    $('#btnSelected').click(function () {
        var selected = $("#l2 option:selected");
        var message = "";
        selected.each(function () {
            message += $(this).text() + " " + $(this).val() + "\n";
        });
        alert(message);
    });
});




</script>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3 align="center"><spring:message code="label.distributeFunds"/></h3>
	</div>
	<div class="col-sm-12 col-md-12 col-lg-12">
		<div  class="successMsg" style="text-align:center;color:green;font-size: 18px;">${success}</div>
	</div>
    	
	<div class="col-sm-12 col-md-12 col-lg-12">			
			<form:form action="masterPlanFundsDistributeConfirm" name="regulation" commandName="fundsDistributeForm" onsubmit="return validateForm();">
					<table align="center">
					<tr>
									<form:hidden path="id"/>
									<form:hidden path="flag"/>
									<form:hidden path="distributedAmount"/>
								
						  <td class="col-sm-6"><b><spring:message code="label.customerName"/></b></td>
						 <td class="col-sm-6">
							<form:input path="customerHeadName" id="customerHeadName" readonly="true" value="${model.fundsDistributeForm.customerName}"/></td>
				    </tr>
				    <tr>
					  	 <td class="col-sm-6"><b><spring:message code="label.masterKey"/></b></td>
						 <td class="col-sm-6"><form:input path="masterKey" id="masterKey" readonly="true" value="${model.fundsDistributeForm.masterKey}"/></td>
				    </tr>
				    
				    <tr>
					 	 <td class="col-sm-6"><b><spring:message code="label.sanctionedAmount"/></b></td>
						 <td class="col-sm-6"><form:input path="buyingCostSanc" id="buyingCostSanc" readonly="true" value="${model.fundsDistributeForm.buyingCostSanc}"/></td>
				    </tr>
				    <tr>
					   <td class="col-sm-6"><b><spring:message code="label.distAmt"/></b></td>
						 <td class="col-sm-6">
						<form:input path="distributedAmount" id="distributedAmount" readonly="true" value="${model.fundsDistributeForm.distributedAmount}"/>
						</td>
				    </tr>
				    
				    <tr>
					     <td class="col-sm-6"><b><spring:message code="label.customerHeadEmail"/></b></td>
						 <td class="col-sm-6">
					<form:input path="custHeadEmail" id="distributedAmount" readonly="true" value="${model.fundsDistributeForm.custHeadEmail}"/>
					</td>
				  </tr>
				 <tr>
					   <td class="col-sm-6"><b><spring:message code="label.balance"/></b></td>
					 <td class="col-sm-6">
					<form:input path="balance" id="buyingCostSanc" readonly="true" value="${model.fundsDistributeForm.balance}"/>
					</td>
				</tr>
			<%-- 	<tr>
						 <td class="col-sm-6"><b><spring:message code="label.distrib"/>:</b></td>
						 <td class="col-sm-6">
						<input type=radio name=regulation value='Banned'  onClick="radio_check()";><spring:message code="label.br"/> 
                                     <input type=radio name=regulation value='Not Banned' checked="checked" onClick="radio_check()";><spring:message code="label.subs"/><br />
                                   </td>
				</tr>  --%>
			 	<tr>
						 <td class="col-sm-6"><b><spring:message code="label.disFundsBr"/>:</b></td>
						 <td class="col-sm-6">
							<form:select path="customerName" id="l1" enabled="true" style="width:206px;">
                                     <form:options items="${fundsDistributeForm.customerBranchList}" itemValue="name" itemLabel="name"/>
                                     </form:select>
						</td>																
				</tr> 
			<%-- 	<tr>
						 <td class="col-sm-6"><b><spring:message code="label.disFundsSubs"/>:</b></td>
						 <td class="col-sm-6">
							<form:select path="customerName" id="l2" style="width:206px;">
                                     <form:options items="${fundsDistributeForm.customerSubsList}" itemValue="name" itemLabel="name"/>
                                     </form:select></td>	
																				
				</tr>  --%>
				<tr>
					 	  <td class="col-sm-6"><b><spring:message code="label.amount"/></b></td>
						 <td class="col-sm-6">
						<form:input path="amount"  class="form-control" placeholder="Enter the amount" id="amount" />
						  <td id="amountError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
						 <td id="amountError" class="error" style="display:none"><spring:message code="label.validation"/></td>
						
				  </tr>
			</table>
				<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="masterPlanFunds" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
			</form:form>
			
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3><spring:message code="label.distributeFunds"/></h3>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerHeadName"/></th>
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.customerType"/></th>
						<th><spring:message code="label.masterKey"/></th>
						<th><spring:message code="label.availableAmount"/></th>
						<th><spring:message code="label.sanctionedAmount"/></th>
						
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty fundsList}">
						<c:forEach items="${fundsList}" var="funds">
							<tr>
								<td><c:out value="${funds.id}"></c:out></td>
								<td><c:out value="${funds.customerHeadName}"></c:out>
								<td><c:out value="${funds.customerName}"></c:out>
								<td><c:out value="${funds.currentRole}"></c:out>
								<td><c:out value="${funds.masterKey}"></c:out>
								<td><c:out value="${funds.distributedAmount}"></c:out></td>
								<td><c:out value="${funds.buyingCostSanc}"></c:out></td>
								
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
			</div>
			</div>
</div>
<script>
	function validateForm(){
	

		var amount  = document.getElementById('amount');
		var number='^\\d+$';
		var number= /(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/;
		var number1= /^-?[0-9]+$/;
		var canSubmit = true; 
		
		
	 
    	if(amount.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || amount.value.match(/^-?[0-9]+$/) &&!(document.getElementById('amount').value == ''))
	  	{
	  		
	  		document.getElementById('amountError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('amountError').style.display='block';
	  	     canSubmit = false;
	  	} 

		if(canSubmit == false){
			return false;
		}
	}
	

</script>