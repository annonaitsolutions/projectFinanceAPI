<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


 <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

	 <div class="col-sm-12 col-md-12 col-lg-12 header_customer">
	 		<h3 align="center"><spring:message code="label.creditAssignment"/></h3>
	 </div>
<form:form action="tmasterPlanCreditAssignConfirm" name="interest"  commandName="tMasterPlanForm" onsubmit="return val();">

			<div class="col-sm-12 col-md-12 col-lg-12">
					<table>
						<tr>
							<td class="col-sm-3"><b><spring:message code="label.buyingCost"/>:</b></td>
							<td class="col-sm-9"><form:input path="buyingCost" placeholder="Enter cost" readonly="true"></form:input>
						</tr>
						<form:hidden path="id" />
						<form:hidden path="transactionId" />
						<tr>
							<td class="col-sm-3"><b><spring:message code="label.sanctionedBuyingAmount"/>:</b><span style="color:red">*</span></td>
							<td class="col-sm-9"><form:input path="buyingCostSanc" id="sancAmt" placeholder="Enter Amount"></form:input>
							<td id="sancAmtError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
							<td id="sancAmtError" class="error" style="display:none"><spring:message code="label.validation"/></td>
								
						</tr>
						 <tr>
							<td class="col-sm-3"><b><spring:message code="label.typeOfInt"/>(%) :</b></td>
							<td class="col-sm-9">
								<form:radiobutton onclick="document.getElementById('field1').disabled=false;document.getElementById('field2').disabled=true;document.getElementById('field3').disabled=true;document.getElementById('field4').disabled=true;document.getElementById('field5').disabled=true;" path="interestType" value="fixed" /><spring:message code="label.fixed"/>
								<form:radiobutton onclick="document.getElementById('field1').disabled=true;document.getElementById('field2').disabled=false;document.getElementById('field3').disabled=false;document.getElementById('field4').disabled=false;document.getElementById('field5').disabled=false;" path="interestType" value="variable"/><spring:message code="label.variable"/>
							</td>																
						</tr>						
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td class="col-sm-3"><b><spring:message code="label.rateOfInt"/>(%) :</b></td>
							<td class="col-sm-9">
								<form:input id="field1" path="rateOfInt" value="0"  disabled="true"/>									
							</td>																
						</tr>						
						<tr>
							<td class="col-sm-3"><b><spring:message code="label.noOfDay"/>:</b></td>
							<td class="col-sm-9">
								<form:input path="tenure" placeholder="Enter No Of Days" id="txtPincode"/>									
							</td>																
						</tr>						
						<tr>
								<td class="col-sm-3"><b><spring:message code="label.plrRate"/>:</b></td>
								<td class="col-sm-9">
									<form:input id="field2" path="plrRate" value="0"  disabled="true"/>									
								</td>																
							</tr>						
							<tr>
								<td class="col-sm-3"><b><spring:message code="label.basicPoints"/>:</b></td>
								<td class="col-sm-3">
									<form:input id="field3" path="basicPoints" value="0"  disabled="true"/>	
								</td>
								<td class="col-sm-3"><input type="button" value="Generate" style="margin-left: 64px;" onClick="basicpts()"/></td>
								<td class="col-sm-3"><form:input id="field4" path="basicAmt" value="0"  readonly="readonly" disabled="true"/></td>																
							</tr>						
							<tr>
								<td class="col-sm-3">
								<input type="button" value="PLR Rate Generate" size="5" onClick="plr()"/></td>
							
								<td class="col-sm-9"><form:input id="field5" path="calPlrRate" size="10" value="0" readonly="readonly" disabled="true"/> 
																	
								</td>																
							</tr>					
				
							<tr>
								<td class="col-sm-12">
									<input type="button" value="CALCULATE INTEREST" size="5" onClick="simple()"/>
								</td>																
							</tr>	
							<tr>
								<form:hidden path="total1" readonly="true"/>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td class="col-sm-3"><b><spring:message code="label.amtInt"/>(%) :</b></td>
								<td class="col-sm-9">
									<form:input path="rateOfInt1" id="txtPincode" placeholder="Enter Amount Interest" value="0" />																		
								</td>																
							</tr>													
							<tr>
								<td class="col-sm-3"><b><spring:message code="label.flatCharges"/>:</b></td>
								<td class="col-sm-9">
									<form:select path="flatCharges"  style="width:205px;">
									<form:option value="0">0</form:option>
									<form:option value="100">100</form:option>
									<form:option value="200">200</form:option>
									<form:option value="300">300</form:option>
									<form:option value="400">400</form:option>
									<form:option value="500">500</form:option>
									<form:option value="600">600</form:option>
									<form:option value="700">700</form:option>
									</form:select>
						            </td>														
							</tr>													
							<tr>
								<td class="col-sm-3"><b><spring:message code="label.percent"/>(%) :</b></td>
								<td class="col-sm-3">
									<form:input path="percentage" id="txtPincode" placeholder="Enter Percentage" value="0"/>	
									
								</td>
								<td class="col-sm-3"><input type="button" value="Generate" style="margin-left: 64px;" onClick="percent()"/></td>
								<td class="col-sm-3"><form:input path="percentAmt" placeholder="Enter a Value" value="0" size="20" readonly="readonly"/></td>																
							</tr>														
							<tr><td>&nbsp;</td></tr>
							<tr>
								<td class="col-sm-3"><b><spring:message code="label.procFees"/>:</b></td>
								<td class="col-sm-9">
									<form:input path="procFee" id="txtPincode" placeholder="Enter Processing Fees" value="0"/>																		
								</td>																
							</tr>													
							<tr>
								<td class="col-sm-3"><b><spring:message code="label.docFee"/>:</b></td>
								<td class="col-sm-9">
									<form:input path="docFee" id="txtPincode" placeholder="Enter Document Handling Processing Fees" value="0"/>																		
								</td>																
							</tr>													
							<tr>
								<td class="col-sm-3"><b><spring:message code="label.lateFee"/>:</b></td>
								<td class="col-sm-9">
									<form:input path="lateFee" id="txtPincode" placeholder="Enter Late Fees" value="0"/>																		
								</td>																
							</tr>	
							
						
							<tr>
								<td class="col-sm-3"><b><spring:message code="label.taxName"/>:</b></td>
								<td class="col-sm-9">
									<form:select path="taxName" style="width:205px;">
									
										<form:option value="Corporate Tax"><spring:message code="label.corTax"/>
									  	 
										</form:option>
										<form:option value="Custom Duty"><spring:message code="label.custDuty"/>
						                 	
										</form:option>
										<form:option value="Value Added Tax"><spring:message code="label.valAddTax"/>
											
										</form:option>
										<form:option value="Service Tax"><spring:message code="label.servTax"/>
                                  			  										
                                     	</form:option>
										<form:option value="Banking Cash Transaction Tax"><spring:message code="label.bankTax"/>
										
										</form:option>
											<form:option value="Fringe Benefit Tax"><spring:message code="label.frinTax"/>
										
										</form:option>
											<form:option value="Excise Tax"><spring:message code="label.excTax"/>
										
										</form:option>
									</form:select>
								</td>																
							</tr>
							<tr>
								<td class="col-sm-3"><b><spring:message code="label.taxPerc"/>:</b></td>
								<td class="col-sm-3">
									<form:input path="taxPercentage" id="txtPincode" placeholder="Enter Tax Percentage" value="0"/>	
								</td>
								<td class="col-sm-3"><input type="button" value="Generate" style="margin-left: 64px;" onClick="taxamnt()"></td>
								<td class="col-sm-3"><form:input path="taxAmt" value="0" placeholder="Enter a Value" size="20" readonly="readonly"/></td>																
							</tr>														
						
							<tr>
								<td class="col-sm-3"><b><spring:message code="label.taxName"/>:</b></td>
								<td class="col-sm-9">
									<form:select path="taxName1" style="width:205px;">
									<form:option value="Corporate Tax"><spring:message code="label.corTax"/>
									  	 
										</form:option>
										<form:option value="Custom Duty"><spring:message code="label.custDuty"/>
						                 	
										</form:option>
										<form:option value="Value Added Tax"><spring:message code="label.valAddTax"/>
											
										</form:option>
										<form:option value="Service Tax"><spring:message code="label.servTax"/>
                                  			  										
                                     	</form:option>
										<form:option value="Banking Cash Transaction Tax"><spring:message code="label.bankTax"/>
										
										</form:option>
											<form:option value="Fringe Benefit Tax"><spring:message code="label.frinTax"/>
										
										</form:option>
											<form:option value="Excise Tax"><spring:message code="label.excTax"/>
										
										</form:option>
									</form:select>
								</td>																
							</tr>
							<tr>
								<td class="col-sm-3"><b><spring:message code="label.taxPerc"/>:</b></td>
								<td class="col-sm-3">
									<form:input path="taxPercentage1" id="txtPincode"  placeholder="Enter Tax Percentage" value="0" />	
									
								</td>
								<td class="col-sm-3"><input type="button" value="Generate" style="margin-left: 64px;" onClick="taxamnt1()"></td>
								<td class="col-sm-3"><form:input path="taxAmt1" value="0" placeholder="Enter a Value" size="20"   readonly="readonly"/></td>																
							</tr>														
						
							<tr>
								<td class="col-sm-3"><b><spring:message code="label.taxName"/>:</b></td>
								<td class="col-sm-9">
									<form:select path="taxName2" style="width:205px;">
									
										<form:option value="Corporate Tax"><spring:message code="label.corTax"/>
									  	 
										</form:option>
										<form:option value="Custom Duty"><spring:message code="label.custDuty"/>
						                 	
										</form:option>
										<form:option value="Value Added Tax"><spring:message code="label.valAddTax"/>
											
										</form:option>
										<form:option value="Service Tax"><spring:message code="label.servTax"/>
                                  			  										
                                     	</form:option>
										<form:option value="Banking Cash Transaction Tax"><spring:message code="label.bankTax"/>
										
										</form:option>
											<form:option value="Fringe Benefit Tax"><spring:message code="label.frinTax"/>
										
										</form:option>
											<form:option value="Excise Tax"><spring:message code="label.excTax"/>
										
										</form:option>
									</form:select>
								</td>																
							</tr>
							<tr>
								<td class="col-sm-3"><b><spring:message code="label.taxPerc"/>:</b></td>
								<td class="col-sm-3"><form:input path="taxPercentage2" id="txtPincode"  placeholder="Enter Tax Percentage" value="0"/></td>
								<td class="col-sm-3"><input type="button" value="Generate" style="margin-left: 64px;" onClick="taxamnt2()"></td>
								<td class="col-sm-3"><form:input path="taxAmt2" value="0" placeholder="Enter a Value" size="20" readonly="readonly"/></td>																
							</tr>														
										 
							<tr>
							 	<td class="col-sm-12"><input type="button" value="Calculate Total Amount" size="5" onClick="Amount()"/>
								</td>
							</tr> 
						
							<tr>
								<td class="col-sm-3"><b><spring:message code="label.amtAllChrg"/>:</b></td>
								<td class="col-sm-9">
									<form:input path="funalAmt" id="txtPincode" placeholder="Enter Amount With All Charges" value="0"/>																		
								</td>																
							</tr>
						</table>	
						<div class="col-sm-12 col-md-12 col-lg-12">&nbsp;</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
				<table align="center">
					<tr>
						<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>"
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
					var number= /(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/;
					var number1= /^-?[0-9]+$/;

					var canSubmit = true; 
					if(sancAmt.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || sancAmt.value.match(/^-?[0-9]+$/) &&!(document.getElementById('sancAmt').value == ''))
				  	{
				  		
				  		document.getElementById('sancAmtError').style.display='none';
				  	
				  	}
				  	else{
				  		document.getElementById('sancAmtError').style.display='block';
				  	     canSubmit = false;
				  	}
			    	
				

					if(canSubmit == false){
						return false;
					}
				}
			
				
		</script>
</div>
