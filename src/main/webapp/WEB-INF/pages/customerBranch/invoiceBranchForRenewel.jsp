<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
		<div class="col-md-12 col-sm-12 col-lg-12 header_customer">
		<h3 align="center"><spring:message code="label.invoice"/></h3>
		</div>
<form:form action="invoiceBranchForRenewelConfirm" commandName="invoiceForm" onsubmit="return val();">

			
								<table align="center">
								<form:hidden path="id"  />
									<form:hidden path="customerHeadEmail"  />
									<form:hidden path="customerBranchEmail"  />
								    <form:hidden path="buyerEmail"  />
								     <form:hidden path="transactionId"  />
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.customerName"/>:</b></td>
										<td class="col-sm-7"><form:input path="customerName"  readonly="true"></form:input>
																								
									</tr>
									
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.customerHeadName"/>:</b></td>
										<td class="col-sm-7"><form:input path="customerHeadName" readonly="true"  id="customerPrefix"></form:input>
											
									</tr>
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.buyerName"/>:</b></td>
										<td class="col-sm-7"><form:input path="buyerName" readonly="true"  id="companyName"></form:input>
																									
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.masterKey"/>:</b></td>
										<td class="col-sm-7"><form:input path="masterKey" readonly="true"  id="companyName"></form:input>
									</tr>	
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.goods"/>:</b></td>
										<td class="col-sm-7"><form:input path="goods" readonly="true"  id="companyName"></form:input>
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.quantity"/>:</b></td>
										<td class="col-sm-7"><form:input path="quantity" readonly="true"  id="companyName"></form:input>
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.amount"/>:</b></td>
										<td class="col-sm-7"><form:input path="amount" readonly="true"  id="companyName"></form:input>
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.insType"/>:</b></td>
										<td class="col-sm-7"><form:input path="insuranceType" id="insuranceType" placeholder="Enter Insurance Type"></form:input>
										
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.insAmt"/>:</b></td>
										<td class="col-sm-7"><form:input path="insuranceAmount" id="insuranceAmount" placeholder="Enter Insurance Amount"></form:input>
										<td id="insuranceAmountError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										    <td id="insuranceAmountError" class="error" style="display:none"><spring:message code="label.validation"/></td>
											
										
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.insDetails"/>:</b></td>
										<td class="col-sm-7"><form:textarea path="insuranceDetails" id="insuranceDetails" placeholder="Enter Insurance Details"></form:textarea>
										
										
									</tr>
									<tr>
						             <td class="col-sm-5"><b><spring:message code="label.startDate"/></b><span style="color:red">*</span></td>
						                 <td class="col-sm-7">
							                <form:input path="startDate"  class="form-control" readonly="true" placeholder="Enter Date" style="width: 176px;" id="status"/></td>
							                  <td><i class="fa fa-calendar"></i></td>
							                   <td id="statusError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										    <td id="statusError" class="error" style="display:none"><spring:message code="label.validation"/></td>
											
						        </tr>
						        
						        	<tr>
						                <td class="col-sm-5"><b><spring:message code="label.endDate"/></b><span style="color:red">*</span></td>
						                     <td class="col-sm-7">
							                   <form:input path="endDate"  class="form-control" readonly="true" placeholder="Enter Date" style="width: 176px;" id="comment"/></td>
							                 <td><i class="fa fa-calendar"></i></td>
							                 <td id="commentError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										    <td id="commentError" class="error" style="display:none"><spring:message code="label.validation"/></td>
											
						        </tr>
								
					
						
									</table>
					<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="invoiceBranchListForRenewel" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
									
								


</form:form>
</div>
	<script>
		
			
				 
				function val(){
					
					var userName  = document.getElementById('comment');
					var insuranceAmount  = document.getElementById('insuranceAmount');
					var number='^\\d+$';
					var number= /(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/;
					var number1= /^-?[0-9]+$/;

					var canSubmit = true; 
					
					if (document.getElementById('comment').value == ''){
						document.getElementById('commentError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('commentError').style.display='none';
					}
					if(insuranceAmount.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || insuranceAmount.value.match(/^-?[0-9]+$/) && !(document.getElementById('insuranceAmount').value == ''))
				  	{
				  		
				  		document.getElementById('insuranceAmountError').style.display='none';
				  	
				  	}
				  	else{
				  		document.getElementById('insuranceAmountError').style.display='block';
				  	     canSubmit = false;
				  	}
				
					
					if (document.getElementById('status').value == ''){
						document.getElementById('statusError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('statusError').style.display='none';
					}

					if(canSubmit == false){
						return false;
					}
				}
			
				$(function() {
					  $( "#status" ).datepicker({format:'dd/mm/yyyy'});
					  $( "#comment" ).datepicker({format:'dd/mm/yyyy'});
					   });
		</script>

