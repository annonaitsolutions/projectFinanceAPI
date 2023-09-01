<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

				
<div class="col-sm-6 col-md-9 col-lg-9 body_fixed">
				
				<div class="col-sm-12 col-md-12 col-lg-12">
					
					<div  class="successMsg" style="text-align:center;color:green;font-size: 18px;">${success}</div>
				</div>
				
				<form:form action="addOrModifyReportAddArbitrationConfirm" method="post" commandName="disputeReportsForm"  onsubmit="return val();">
				
				<form:hidden path ="transactionId" />
				<form:hidden path ="disputekey" />
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3><spring:message code="label.arbDetails"/></h3>
				</div>
				<table align="center">
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.arbName"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="arbNames"  class="form-control" placeholder="Enter Name"  id="arbNames" /></td>
							
											
					</tr>
					<tr>
						<td class="col-sm-6"></td>
						 <td id="arbNamesError" class="error col-sm-6" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
							<td id="arbNamesError" class="error" style="display:none"><spring:message code="label.validation"/></td>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.location"/></b><span style="color:red"></span></td>
						<td class="col-sm-6">
							<form:input path="loc" class="form-control" placeholder="Enter Location"  id="loc"/>
						</td>
							
					</tr>
					<tr>
						<td class="col-sm-6"></td>
						  <td id="locError" class="error col-sm-6" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
							<td id="locError" class="error" style="display:none"><spring:message code="label.validation"/></td>
							
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.judgement"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="judgement" class="form-control" placeholder="Enter Judgement"  id="judgement"/>
						</td>
					</tr>
					<tr>
						<td class="col-sm-6"></td>
						 <td id="judgementError" class="error col-sm-6" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
							<td id="judgementError" class="error " style="display:none"><spring:message code="label.validation"/></td>
							
					</tr>	
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.cost"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="arbCost" class="form-control" placeholder="Enter Cost"  id="arbCost"/>
						</td>
							
					</tr>
					<tr>
						<td class="col-sm-6"></td>
						 <td id="arbCostError" class="error col-sm-6" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
							<td id="arbCostError" class="error" style="display:none"><spring:message code="label.validation"/></td>
							
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.quantity"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="arbQty"  class="form-control" placeholder="Enter Quantity"  id="arbQty"/>
						</td>
							
					</tr>
					<tr>
						<td class="col-sm-6"></td>
						  <td id="arbQtyError" class="error col-sm-6" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
							<td id="arbQtyError" class="error" style="display:none"><spring:message code="label.validation"/></td>
							
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.startDate"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="arbStartDate"  class="form-control" readonly="true" placeholder="Enter Date" style="width: 176px;" id="arbStartDate"/></td>
							<td><i class="fa fa-calendar"></i></td>
						
					</tr>
					<tr>
						<td class="col-sm-6"></td>
						 <td id="arbStartDateError" class="error col-sm-6" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
							<td id="arbStartDateError" class="error" style="display:none"><spring:message code="label.validation"/></td>
							
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.endDate"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="arbEndDate"  class="form-control" readonly="true" placeholder="Enter Date" style="width: 176px;" id="arbEndDate"/></td><td><i class="fa fa-calendar"></i></td>
							
					</tr>
					<tr>
						<td class="col-sm-6"></td>
						<td id="arbEndDateError" class="error col-sm-6" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
							<td id="arbEndDateError" class="error" style="display:none"><spring:message code="label.validation"/></td>
							
					</tr>
						<tr>
						<td class="col-sm-6"><b><spring:message code="label.documents"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="docSub"  class="form-control" placeholder="Enter Documents Name"  id="docSub"/>
						</td>
							
					</tr>
					<tr>
						<td class="col-sm-6"></td>
						 <td id="docSubError" class="error col-sm-6" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
							<td id="docSubError" class="error" style="display:none"><spring:message code="label.validation"/></td>
							
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.moneyPaid"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="moneyPaid"  class="form-control" placeholder="Enter Paid Money"  id="moneyPaid"/>
						</td>
						 	
					</tr>
					<tr>
						<td class="col-sm-6"></td>
						<td id="moneyPaidError" class="error col-sm-6" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
							<td id="moneyPaidError" class="error" style="display:none"><spring:message code="label.validation"/></td>
							
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.payDate"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="payDate"  class="form-control" readonly="true" placeholder="Enter Paid Date" style="width: 176px;"  id="payDate"/></td><td><i class="fa fa-calendar"></i></td>
								
					</tr>
					<tr>
						<td class="col-sm-6"></td>
					 <td id="payDateError" class="error col-sm-6" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
							<td id="payDateError" class="error" style="display:none"><spring:message code="label.validation"/></td>
							
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.goodsReplaced"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="GoodsReplaced"  class="form-control" placeholder="Enter Goods"  id="GoodsReplaced"/>
						</td>
							
					</tr>
					<tr>
						<td class="col-sm-6"></td>
					  <td id="GoodsReplacedError" class="error col-sm-6" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
							<td id="GoodsReplacedError" class="error" style="display:none"><spring:message code="label.validation"/></td>
							
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.replacedDate"/></b><span style="color:red">*</span></td>
						<td class="col-sm-6">
							<form:input path="repacedDate"  class="form-control" readonly="true" placeholder="Enter Date" style="width: 176px;" id="repacedDate"/></td><td><i class="fa fa-calendar"></i></td>
						
						 	
					</tr>
					<tr>
						<td class="col-sm-6"></td>
					<td id="repacedDateError" class="error col-sm-6" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
							<td id="repacedDateError" class="error" style="display:none"><spring:message code="label.validation"/></td>
							
					</tr>
				</table>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
						<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="disputeHeadUpdateList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
				
				</form:form>
				
	</div>
				
<script>
		
			
				 
				function val(){
					var number='^\\d+$';
					var number= /(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/;
					var number1= /^-?[0-9]+$/;
					

					var canSubmit = true; 
					
					if (document.getElementById('arbNames').value == ''){
						document.getElementById('arbNamesError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('arbNamesError').style.display='none';
					}
					if (document.getElementById('loc').value == ''){
						document.getElementById('locError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('locError').style.display='none';
					}
					if (document.getElementById('judgement').value == ''){
						document.getElementById('judgementError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('judgementError').style.display='none';
					}
					if(arbCost.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || arbCost.value.match(/^-?[0-9]+$/) && !(document.getElementById('arbCost').value == ''))
				  	{
				  		document.getElementById('arbCostError').style.display='none';
				  	}
				  	else{
				  		document.getElementById('arbCostError').style.display='block';
				  	     canSubmit = false;
				  	}
					if(arbQty.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || arbQty.value.match(/^-?[0-9]+$/) && !(document.getElementById('arbQty').value == ''))
				  	{
				  		document.getElementById('arbQtyError').style.display='none';
				  	}
				  	else{
				  		document.getElementById('arbQtyError').style.display='block';
				  	     canSubmit = false;
				  	}
					if (document.getElementById('arbStartDate').value == ''){
						document.getElementById('arbStartDateError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('arbStartDateError').style.display='none';
					}
					if (document.getElementById('arbEndDate').value == ''){
						document.getElementById('arbEndDateError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('arbEndDateError').style.display='none';
					}
					if (document.getElementById('docSub').value == ''){
						document.getElementById('docSubError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('docSubError').style.display='none';
					}
					if(moneyPaid.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || moneyPaid.value.match(/^-?[0-9]+$/) && !(document.getElementById('moneyPaid').value == ''))
				  	{
				  		document.getElementById('moneyPaidError').style.display='none';
				  	}
				  	else{
				  		document.getElementById('moneyPaidError').style.display='block';
				  	     canSubmit = false;
				  	}
					if (document.getElementById('payDate').value == ''){
						document.getElementById('payDateError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('payDateError').style.display='none';
					}
					if (document.getElementById('GoodsReplaced').value == ''){
						document.getElementById('GoodsReplacedError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('GoodsReplacedError').style.display='none';
					}
					if (document.getElementById('repacedDate').value == ''){
						document.getElementById('repacedDateError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('repacedDateError').style.display='none';
					}
					if(canSubmit == false){
						return false;
					}
				}
			
				
				   $(function() {
						  $( "#arbStartDate" ).datepicker({format:'dd/mm/yyyy'});
						  $( "#arbEndDate" ).datepicker({format:'dd/mm/yyyy'});
						  $( "#payDate" ).datepicker({format:'dd/mm/yyyy'});
						  $( "#repacedDate" ).datepicker({format:'dd/mm/yyyy'});
						   });
				
		</script>							
				