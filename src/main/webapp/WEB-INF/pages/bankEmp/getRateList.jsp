<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<section>	
<h2 style="margin-left:500px"><spring:message code="label.buyerFinancialRates"/></h2>
		
	<form:form action="getRateListConfirm" name="interest" method="post" commandName="purchaseOrderForm">
		
			<div class="content">
          <table style="margin-left:90px;margin-top:-5px;border:1px solid #D4E0E4"; width="865px;">
							 <tr style="background-color:#D4E0E4;">
							 <form:hidden path="id"/>
							 <form:hidden path="transactionId"/>
								<td colspan="2">
									<font color="Red"></font> <spring:message code="label.bankName"/>
								<td><form:select path="bankName">
								<form:options items="${purchaseOrderForm.banklist}"
									itemValue="bankName" itemLabel="bankName" />
							</form:select></td></td>																
							</tr>
							
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									<font color="Red"></font><spring:message code="label.poKey"/>
									<form:input path="poKey"  style="margin-left:108px;" readonly="true"/>
								</td>																
							</tr>
							
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									<font color="Red"></font><spring:message code="label.customerName"/>
									<form:input path="customerName"  style="margin-left:95px;" readonly="true"/>
								</td>																
							</tr>
						
								<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									<font color="Red"></font><spring:message code="label.customerHeadEmail"/>
									<form:input path="customerHeadEmail" style="margin-left:143px;" readonly="true"/>
								</td>																
							</tr>
							
								<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									<font color="Red"></font><spring:message code="label.customerBranchEmail"/>
									<form:input path="customerBranchEmail" style="margin-left:143px;" readonly="true"/>
								</td>																
							</tr>
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									<font color="Red"></font><spring:message code="label.masterKey"/>
									<form:input path="masterKey" style="margin-left:143px;" readonly="true"/>
								</td>																
							</tr>
							
								
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									<font color="Red"></font><spring:message code="label.supplierName"/>
									<form:input path="supplierName"  style="margin-left:141px;" readonly="true"/>
								</td>																
							</tr>
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									<font color="Red"></font> <spring:message code="label.supplierEmail"/>
									<form:input path="supplierEmail"  style="margin-left:149px;" readonly="true"/>
								</td>															
							</tr>
							</table>
						
						<br/>
						<table>
						<tr height="10"></tr></table>
						
					<table style="margin-left:90px;margin-top:-5px;border:1px solid #D4E0E4"; width="865px;">
					
					<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									<font color="Red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><spring:message code="label.amount"/>
									  <form:input path="amount" id="txtPincode" style="margin-left:190px;" readonly="true"/>
									 
								</td>																
							</tr>
					
					
					
					    <tr>
					
						<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.typeOfInterest"/>
									<form:radiobutton onclick="document.getElementById('field1').disabled=false;document.getElementById('field2').disabled=true;document.getElementById('field3').disabled=true;document.getElementById('field4').disabled=true;document.getElementById('field5').disabled=true;" path="interestType" value="fixed" style="margin-left:130px;"/>Fixed
									<form:radiobutton onclick="document.getElementById('field1').disabled=true;document.getElementById('field2').disabled=false;document.getElementById('field3').disabled=false;document.getElementById('field4').disabled=false;document.getElementById('field5').disabled=false;" path="interestType" value="variable"/>Variable
								</td>																
							</tr>						
					
						<br>
						
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.rateOfInterest"/>
									<form:input id="field1" path="rateOfInt" value="0" style="margin-left:115px;" disabled="true"/>									
								</td>																
							</tr>						
							<tr>
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.noOfDays"/>
									<form:input path="tenure"  id="txtPincode" style="margin-left:164px;"/>									
								</td>																
							</tr>						
					</table>
						<br/>
						<table style="margin-left:90px;margin-top:-5px;border:1px solid #D4E0E4"; width="865px;">
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.plrRate"/>
									<form:input id="field2" path="plrRate" value="0" style="margin-left:173px;" disabled="true"/>									
								</td>																
							</tr>						
							<tr>
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.basicPoints"/>
									<form:input id="field3" path="basicPoints" value="0" style="margin-left:155px;" disabled="true"/>	
									
								</td><td><input type="button" value="Generate"  onClick="basicpts()"/></td><td><form:input id="field4" path="basicAmt" value="0" size="20" readonly="readonly" disabled="true"/></td>																
							</tr>						
							
								<tr style="background-color:#D4E0E4;">
								<td colspan="2"><input type="button" value="PLR Rate Generate" size="5" onClick="plr()"/></td>
							
						<td><form:input id="field5" path="calPlrRate" size="10" value="0" readonly="readonly" disabled="true"/> 
																	
								</td>																
							</tr>					
						</table>
						<br/>
						<table style="margin-left:90px;margin-top:-5px;border:1px solid #D4E0E4"; width="865px;">
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
								<input type="button" value="<spring:message code="label.calculateInterest"/>" size="5" onClick="simple()"/>
									
								</td>																
							</tr>	
							<tr>

							<form:hidden path="total1" readonly="true"/>
							</tr>
						</table>
						<br/>
						<table style="margin-left:90px;margin-top:-5px;border:1px solid #D4E0E4"; width="865px;">
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.amountInterest"/>
									<form:input path="rateOfInt1" id="txtPincode" value="0" style="margin-left:109px"/>																		
								</td>																
							</tr>													
						</table>
						<br/>
						<img src="img/11/Content1.png" style="margin-top:25px;margin-left:90px;"></img>
						<table style="margin-left:90px;margin-top:-5px;border:1px solid #D4E0E4"; width="865px;">
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.flatCharges"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<form:select path="flatCharges">
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
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.percent"/>
									<form:input path="percentage" id="txtPincode" value="0" style="margin-left:137px;"/>	
									
								</td><td><input type="button" value="Generate" size="5" onClick="percent()"/></td><td><form:input path="percentAmt" value="0" size="20" readonly="readonly"/></td>																
							</tr>														
						</table>
						<br/>
						<table style="margin-left:90px;margin-top:-5px;border:1px solid #D4E0E4"; width="865px;">
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.procFees"/>
									<form:input path="procFee" id="txtPincode" value="0" style="margin-left:129px"/>																		
								</td>																
							</tr>													
							<tr>
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.docFee"/>
									<form:input path="docFee" id="txtPincode" value="0" style="margin-left:68px"/>																		
								</td>																
							</tr>													
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.lateFee"/>
									<form:input path="lateFee" id="txtPincode" value="0" style="margin-left:169px"/>																		
								</td>																
							</tr>	
							</table><br>
							
						<table style="margin-left:90px;margin-top:-5px;border:1px solid #D4E0E4"; width="865px;">
						
						
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><spring:message code="label.taxName"/>
									<form:select path="taxName" style="margin-left:163px;  width:172px;">
									
										<form:option value="Corporate Tax">
									   <spring:message code="label.corTax"/>
										</form:option>
										<form:option value="Custom Duty">
						                 	<spring:message code="label.custDuty"/>
										</form:option>
										<form:option value="Value Added Tax">
									<spring:message code="label.vallAddTax"/>
										</form:option>
										<form:option value="Service Tax">
                                    <spring:message code="label.servTax"/>								
                                     </form:option>
											<form:option value="Banking Cash Transaction Tax">
									<spring:message code="label.bankTax"/>
										</form:option>
											<form:option value="Fringe Benefit Tax">
								<spring:message code="label.frinTax"/>
										</form:option>
											<form:option value="Excise Tax">
								<spring:message code="label.excTax"/>
										
										</form:option>
									</form:select>>
								</td>																
							</tr>
							<tr>
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.taxPerc"/>
									<form:input path="taxPercentage" id="txtPincode" value="0" style="margin-left:107px;"/>	
									
								</td><td><input type="button" value="Generate" size="5" onClick="taxamnt()"></td><td><form:input path="taxAmt" value="0" size="20" readonly="readonly"/></td>																
							</tr>														
						</table>
						
						<table style="margin-left:90px;margin-top:-5px;border:1px solid #D4E0E4"; width="865px;">
						
						
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><spring:message code="label.taxName"/>
									<form:select path="taxName1" style="margin-left:163px;  width:172px;">
									
										<form:option value="Corporate Tax">
									    <spring:message code="label.corTax"/>
										</form:option>
										<form:option value="Custom Duty">
						                 	<spring:message code="label.custDuty"/>
										</form:option>
										<form:option value="Value Added Tax">
									<spring:message code="label.vallAddTax"/>
										</form:option>
										<form:option value="Service Tax">
                                    <spring:message code="label.servTax"/>											
                                     </form:option>
											<form:option value="Banking Cash Transaction Tax">
									<spring:message code="label.bankTax"/>
										</form:option>
											<form:option value="Fringe Benefit Tax">
								<spring:message code="label.frinTax"/>
										</form:option>
											<form:option value="Excise Tax">
								<spring:message code="label.excTax"/>
										
										</form:option>
									</form:select>>
								</td>																
							</tr>
							<tr>
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.taxPerc"/>
									<form:input path="taxPercentage1" id="txtPincode" value="0" style="margin-left:107px;"/>	
									
								</td><td><input type="button" value="Generate" size="5" onClick="taxamnt1()"></td><td><form:input path="taxAmt1" value="0" size="20" readonly="readonly"/></td>																
							</tr>														
						</table>
						<table style="margin-left:90px;margin-top:-5px;border:1px solid #D4E0E4"; width="865px;">
						
						
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><spring:message code="label.taxName"/>
									<form:select path="taxName2" style="margin-left:163px;  width:172px;">
									
										<form:option value="Corporate Tax">
									    <spring:message code="label.corTax"/>
										</form:option>
										<form:option value="Custom Duty">
						                 	<spring:message code="label.custDuty"/>
										</form:option>
										<form:option value="Value Added Tax">
									<spring:message code="label.vallAddTax"/>
										</form:option>
										<form:option value="Service Tax">
                                    <spring:message code="label.servTax"/>											
                                     </form:option>
											<form:option value="Banking Cash Transaction Tax">
									<spring:message code="label.bankTax"/>
										</form:option>
											<form:option value="Fringe Benefit Tax">
								<spring:message code="label.frinTax"/>
										</form:option>
											<form:option value="Excise Tax">
								<spring:message code="label.excTax"/>
										
										</form:option>
									</form:select>>
								</td>																
							</tr>
							<tr>
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.taxPerc"/>
									<form:input path="taxPercentage2" id="txtPincode" value="0" style="margin-left:107px;"/>	
									
								</td><td><input type="button" value="Generate" size="5" onClick="taxamnt2()"></td><td><form:input path="taxAmt2" value="0" size="20" readonly="readonly"/></td>																
							</tr>														
						</table>
																						
						
						
									<table style="margin-left:90px;margin-top:-5px;border:1px solid #D4E0E4"; width="865px;">						 
									<tr style="background-color:#D4E0E4;">
									 <td colspan="2"><input type="button" value="Calculate Total Amount" size="5" onClick="Amount()"/>
									</td>
									</tr> </table>
						<br/>
						<table style="margin-left:90px;margin-top:-5px;border:1px solid #D4E0E4"; width="865px;">
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.amtAllChrg"/>
									<form:input path="funalAmt" id="txtPincode" value="0" style="margin-left:67px"/>																		
								</td>																
							</tr>
																					
						</table>
						
						<br/>
						<table>
						
						<tr height="10"></tr></table>
						
			<div>${success}</div>
			<br><br>
			
				 <table align="center">
			<tr>
				<td><input type="submit" size="3" value="<spring:message code="label.save"/>" class="btn btn-primary"/></td>
				<td><button type="button" name="Back"  onclick="javascript:window.location='/annona/bnkEmp/bankEmp';" class="btn btn-success"><spring:message code="label.back"/></button></td>
			</tr>
			</table>
			</div>
			</form:form>
			
			</section>