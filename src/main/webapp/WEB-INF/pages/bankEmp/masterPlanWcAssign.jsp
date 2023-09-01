<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>



 <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

 		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
 			<h3 align="center"><spring:message code="label.workingCapitalAssignment"/></h3>
 		</div>
<form:form action="masterPlanWcAssignConfirm" method="post" name="myForm" commandName="masterPlanForm" onsubmit="return val();">

			<div class="col-sm-12 col-md-12 col-lg-12">
								<table align="center">
									
									<form:hidden path="id"  />
									<form:hidden path="transactionId"  />
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.workingCapitalAmount"/></b></td>
										<td class="col-sm-6"><form:input path="workingCapital" placeholder="Enter cost" readonly="true"></form:input>
																								
									</tr>
									
									<tr>
										<td id="sancAmtError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										<td class="col-sm-6"><b><spring:message code="label.sanctionedWcAmount"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="wcSancAmount"  id="sancAmt" placeholder="Enter Amount"></form:input>

<%--										    <td id="sancAmtError" class="error" style="display:none"><spring:message code="label.validation"/></td>--%>
										
									</tr>

									<tr>
										<td id="wcError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
										<td class="col-sm-6"><b><div><spring:message code="label.noOfDays" /></b></td>
										<td class="col-sm-6"><form:input path="WcTenure" readonly="false" placeholder="Enter tenure" value="365"></form:input>

									</tr>

									<tr>
										<td id="wcSancInterestError" class="error" style="display:none;color: red"><spring:message code="label.validation"/></td>
										<td class="col-sm-6"><b><spring:message code="label.rateOfInt"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="wcSancInterest" value="0" placeholder="Enter Interest" id="wcSancInterest"></form:input>


									</tr>
									<tr>
									  <td class="col-sm-6">
										<input type="button" class="btn btn-success"  value="Calculate Interest Amount" onClick="amt()"/></td>
						        	 <td class="col-md-6"><form:input path="wcTotalInterest" value="0" readonly="true"/> </td>
						         </tr>
						         <tr>
						      	  	   <td class="col-sm-6">
										<input type="button" class="btn btn-success" value="Calculate Total Amount" onClick="totalamt()" style="width: 190px;"/></td>
										<td class="col-md-6"><form:input path="wcTotalAmount" value="0"  readonly="true"></form:input></td>

								</tr>

									</table>
					</div>
									<div class="col-sm-12 col-md-12 col-lg-12">&nbsp;</div>
						<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="bankMasterPlanDetails" class="btn btn-success"><spring:message code="label.back"/></a></td>


						</tr>
					</table>

									</div>




</form:form>
	<script>



				function val(){




					var canSubmit = true;




					if(sancAmt.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || sancAmt.value.match(/^-?[0-9]+$/) &&!(document.getElementById('sancAmt').value == ''))
				  	{

				  		document.getElementById('sancAmtError').style.display='none';

				  	}
				  	else{
				  		document.getElementById('sancAmtError').style.display='block';
				  	     canSubmit = false;
				  	}

					if(wcSancInterest.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || wcSancInterest.value.match(/^-?[0-9]+$/) &&!(document.getElementById('wcSancInterest').value == ''))
				  	{

				  		document.getElementById('wcSancInterestError').style.display='none';

				  	}
				  	else{
				  		document.getElementById('wcSancInterestError').style.display='block';
				  	     canSubmit = false;
				  	}


					if(canSubmit == false){
						return false;
					}
				}


			    $(document).ready( function () {
			        $('#bootstrap-table').bdt();
			    });

		</script>


</div>