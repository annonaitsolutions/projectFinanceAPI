<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


 <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

	 <div class="col-sm-12 col-md-12 col-lg-12 header_customer">
	 		<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
	 </div>
<form:form action="tmasterPlanCreditAssignPost" name="interest"  commandName="tMasterPlanForm" onsubmit="return val();">

			<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-3"><b><spring:message code="label.buyingCost"/>:</b></td>
							<td class="col-sm-9"><form:input path="buyingCost" placeholder="Enter cost" readonly="true"></form:input>
						</tr>
						<form:hidden path="id" />
						<form:hidden path="transactionId" />
						<tr>
							<td class="col-sm-3"><b><spring:message code="label.sanctionedBuyingAmount"/>:</b><span style="color:red">*</span></td>
							<td class="col-sm-9"><form:input path="buyingCostSanc" readonly="true" id="sancAmt" placeholder="Enter Amount"></form:input>
							
								
						</tr>
						 <tr>
							<td class="col-sm-3"><b><spring:message code="label.typeOfInt"/>(%) :</b></td>
							<td class="col-sm-9">
								<form:radiobutton readonly="true" onclick="document.getElementById('field1').disabled=false;document.getElementById('field2').disabled=true;document.getElementById('field3').disabled=true;document.getElementById('field4').disabled=true;document.getElementById('field5').disabled=true;" path="interestType" value="fixed" />Fixed
								<form:radiobutton readonly="true" onclick="document.getElementById('field1').disabled=true;document.getElementById('field2').disabled=false;document.getElementById('field3').disabled=false;document.getElementById('field4').disabled=false;document.getElementById('field5').disabled=false;" path="interestType" value="variable"/>Variable
							</td>																
						</tr>						
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td class="col-sm-3"><b><spring:message code="label.rateOfInt"/>(%) :</b></td>
							<td class="col-sm-9">
								<form:input id="field1" readonly="true" path="rateOfInt"  disabled="true"/>									
							</td>																
						</tr>						
						<tr>
							<td class="col-sm-3"><b><spring:message code="label.noOfDay"/>:</b></td>
							<td class="col-sm-9">
								<form:input path="tenure" readonly="true" id="txtPincode"/>									
							</td>																
						</tr>						
						<tr>
								<td class="col-sm-3"><b><spring:message code="label.plrRate"/>:</b></td>
								<td class="col-sm-9">
									<form:input id="field2" path="plrRate" readonly="true"  disabled="true"/>									
								</td>																
							</tr>						
							<tr>
								<td class="col-sm-3"><b><spring:message code="label.basicPoints"/>:</b></td>
								<td class="col-sm-5">
									<form:input id="field3" path="basicPoints" readonly="true"  disabled="true"/>	
								</td>
								<td class="col-sm-4"><form:input id="field4" path="basicAmt"  readonly="true" disabled="true"/></td>																
							</tr>						
							<tr>
								<td class="col-sm-3">
								<input type="button" value="PLR Rate Generate" size="5" onClick="plr()"/></td>
							
								<td class="col-sm-9"><form:input id="field5" path="calPlrRate" size="10" readonly="readonly" disabled="true"/> 
																	
								</td>																
							</tr>					
					
							<tr>
								<form:hidden path="total1" readonly="true"/>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td class="col-sm-3"><b><spring:message code="label.amtInt"/>(%) :</b></td>
								<td class="col-sm-9">
									<form:input path="rateOfInt1" id="txtPincode" readonly="true" />																		
								</td>																
							</tr>	
							
								<tr>
								<td class="col-sm-3"><b><spring:message code="label.flatCharges"/>:</b></td>
								<td class="col-sm-9">
									<form:input path="flatCharges" id="txtPincode" readonly="true" />																		
								</td>																
							</tr>												
																				
							<tr>
								<td class="col-sm-3"><b><spring:message code="label.percent"/>(%) :</b></td>
								<td class="col-sm-5">
									<form:input path="percentage" id="txtPincode" readonly="true"/>	
									
								</td>
								<td class="col-sm-4"><form:input path="percentAmt" size="20" readonly="true"/></td>																
							</tr>														
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td class="col-sm-3"><b><spring:message code="label.procFees"/> :</b></td>
								<td class="col-sm-9">
									<form:input path="procFee" id="txtPincode" readonly="true"/>																		
								</td>																
							</tr>													
							<tr>
								<td class="col-sm-3"><b><spring:message code="label.docFee"/>:</b></td>
								<td class="col-sm-9">
									<form:input path="docFee" id="txtPincode" readonly="true"/>																		
								</td>																
							</tr>													
							<tr>
								<td class="col-sm-3"><b><spring:message code="label.lateFee"/>:</b></td>
								<td class="col-sm-9">
									<form:input path="lateFee" id="txtPincode" readonly="true"/>																		
								</td>																
							</tr>	
							
							<tr>
								<td class="col-sm-3"><b><spring:message code="label.taxName"/>:</b></td>
								<td class="col-sm-9">
									<form:input path="taxName" id="txtPincode" readonly="true"/>																		
								</td>																
							</tr>
							
						
							<tr>
								<td class="col-sm-3"><b><spring:message code="label.taxPerc"/>:</b></td>
								<td class="col-sm-5">
									<form:input path="taxPercentage" id="txtPincode" readonly="true"/>	
								</td>
								<td class="col-sm-4"><form:input path="taxAmt"  size="20" readonly="true"/></td>																
							</tr>
							<tr>
								<td class="col-sm-3"><b><spring:message code="label.taxName"/>:</b></td>
								<td class="col-sm-9">
									<form:input path="taxName1" id="txtPincode" readonly="true"/>																		
								</td>																
							</tr>														
						
							<tr>
								<td class="col-sm-3"><b><spring:message code="label.taxPerc"/>:</b></td>
								<td class="col-sm-5">
									<form:input path="taxPercentage1" id="txtPincode" readonly="true" />	
								</td>
								<td class="col-sm-4"><form:input path="taxAmt1" readonly="true" size="20" /></td>																
							</tr>														
						<tr>
								<td class="col-sm-3"><b><spring:message code="label.taxName"/>:</b></td>
								<td class="col-sm-9">
									<form:input path="taxName2" id="txtPincode" readonly="true"/>																		
								</td>																
							</tr>
							
							<tr>
								<td class="col-sm-3"><b><spring:message code="label.taxPerc"/>:</b></td>
								<td class="col-sm-5"><form:input path="taxPercentage2" id="txtPincode" readonly="true"/></td>
								<td class="col-sm-4"><form:input path="taxAmt2"  size="20" readonly="true"/></td>																
							</tr>														
										 
							 
						
							<tr>
								<td class="col-sm-3"><b><spring:message code="label.amtAllChrg"/>:</b></td>
								<td class="col-sm-9">
									<form:input path="funalAmt" id="txtPincode" readonly="true"/>																		
								</td>																
							</tr>
						</table>	
						<div class="col-sm-12 col-md-12 col-lg-12">&nbsp;</div>
						<div class="col-sm-12 col-md-12 col-lg-12">
				<table align="center">
					<tr>
						<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>"
							class="btn btn-primary"></td>
						<td><a href="tbankMasterPlanDetails" class="btn btn-success"><spring:message	code="label.back" /></a></td>
					</tr>
				</table>
			</div>
									</div>
								


</form:form>
	<script>
		
			
				 
				function val(){
					
					var userName  = document.getElementById('sancAmt');
					

					var canSubmit = true; 
					
					if (document.getElementById('sancAmt').value == ''){
						document.getElementById('sancAmtError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('sancAmtError').style.display='none';
					}
				

					if(canSubmit == false){
						return false;
					}
				}
			
				
		</script>
</div>
