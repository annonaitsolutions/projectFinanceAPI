<%@include file="taglib_includes.jsp" %>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section>
 <div class="container">
    <div class="row">	
		<h3 style="font-weight:400;font-size:22px;text-align:center;color:#2E9AFE;"><spring:message code="label.sellerFinancialRates"/></h3>
		
		<form:form action="getRateInvoiceListConfirm" name="interest" method="post" commandName="invoiceForm">
		
			
         		<div class="content">
          <table style="margin-left:90px;margin-top:-5px;border:1px solid #D4E0E4"; width="865px;">
							 <tr style="background-color:#D4E0E4;">
							 <form:hidden path="id"/>
							 <form:hidden path="transactionId"/>
								<td colspan="2">
									<font color="Red"></font><spring:message code="label.bankName"/>
								<td><form:select path="bankName">
								<form:options items="${invoiceForm.banklist}"
									itemValue="bankName" itemLabel="bankName" />
							</form:select></td></td>																
							</tr>
							
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									<font color="Red"></font> <spring:message code="label.poKey"/>
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
									<font color="Red"></font> <spring:message code="label.buyerName"/>
									<form:input path="buyerName"  style="margin-left:141px;" readonly="true"/>
								</td>																
							</tr>
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									<font color="Red"></font> <spring:message code="label.buyerEmail"/>
									<form:input path="buyerEmail"  style="margin-left:149px;" readonly="true"/>
								</td>															
							</tr>
							</table>
			
			 <div class="col-sm-11 col-lg-11 spacer-5"></div>
						
			 <div class="col-sm-11 col-lg-11">
					
						 <div class="col-sm-3 col-lg-3"> <spring:message code="label.amount"/></div>
						 <div class="col-sm-7 col-lg-7"><form:input path="amount"  id="txtPincode" style="width:85%;" readonly="true"/>
									 
						 </div>																
						
					
						 <div class="col-sm-3 col-lg-3"> <spring:message code="label.typeOfInterest"/></div>
						 <div class="col-sm-7 col-lg-7">	
									<form:radiobutton onclick="document.getElementById('field1').disabled=false;document.getElementById('field2').disabled=true;document.getElementById('field3').disabled=true;document.getElementById('field4').disabled=true;document.getElementById('field5').disabled=true;" path="interestType" style="margin-bottom: 5px;" value="fixed"/>Fixed
						<form:radiobutton onclick="document.getElementById('field1').disabled=true;document.getElementById('field2').disabled=false;document.getElementById('field3').disabled=false;document.getElementById('field4').disabled=false;document.getElementById('field5').disabled=false;" path="interestType" style="margin-bottom: 5px;" value="variable"/>Variable
						</div>																
						<div class="col-sm-11" style="height:15px;"></div>	
							
						<div class="col-sm-3 col-lg-3"><spring:message code="label.rateOfInterest"/></div>
							 <div class="col-sm-7 col-lg-7"><form:input id="field1" path="rateOfInt" value="0" style="width:85%;" disabled="true"/>									
								</div>																
						
						<div class="col-sm-3 col-lg-3"><spring:message code="label.noOfDays"/></div>
						 <div class="col-sm-7 col-lg-7"><form:input path="tenure" id="txtPincode" value="0" style="width:85%;"/>									
						</div>																
											
		
						
						<div class="col-sm-3 col-lg-3"><spring:message code="label.plrRate"/></div>
						<div class="col-sm-7 col-lg-7"><form:input id="field2" path="plrRate" value="0" style="width:85%;" disabled="true"/></div>																
							
						<div class="col-sm-3 col-lg-3"><spring:message code="label.basicPoints"/></div>
						<div class="col-sm-7 col-lg-7"><form:input id="field3" path="basicPoints" value="0" style="width:32.5%;" disabled="true"/>	
									
							<input type="button" value="Generate" class="btn btn-success"  onClick="basicpts()" style="margin-bottom: 10px;"/>&nbsp;
							<form:input id="field4" path="basicAmt" value="0" size="20" readonly="readonly" disabled="true" style="width:35%;"/></div>													
						<div class="col-sm-3 col-lg-3"></div>	
						<div class="col-sm-7 col-lg-7" style="left: 162px;"><input type="button" class="btn btn-success" value="PLR Rate Generate" style="margin-bottom:10px;" onClick="plr()"/>
								
								<form:input id="field5" path="calPlrRate" size="20" value="0" readonly="readonly" disabled="true" style="width:36%;"/> 
						</div>																
						
						<div class="col-sm-11 col-lg-11">
						<input type="button" value="<spring:message code="label.calculateInterest"/>" size="5" onClick="simple()" class="btn btn-success"/>
						</div>
									
						<div class="col-sm-11 col-lg-11">

							<form:hidden path="total1" size="20"  readonly="readonly"/>
						</div>
					
						<div class="col-sm-3 col-lg-3"><spring:message code="label.amountInterest"/></div>
						<div class="col-sm-7 col-lg-7">
									<form:input path="rateOfInt1" id="txtPincode" value="0" style="width:85%;"/>																		
						</div>
						
						<!-- <img src="img/11/Content1.png" style="margin-top:25px;margin-left:90px;"></img> -->
						<div class="col-sm-3 col-lg-3"><spring:message code="label.flatCharges"/></div>
						<div class="col-sm-7 col-lg-7">
									<form:select path="flatCharges"  style="width:87%;">
									<form:option value="0">0</form:option>
									<form:option value="100">100</form:option>
									<form:option value="200">200</form:option>
									<form:option value="300">300</form:option>
									<form:option value="400">400</form:option>
									<form:option value="500">500</form:option>
									<form:option value="600">600</form:option>
									<form:option value="300">300</form:option>
									</form:select>
						  </div>
						<div class="col-sm-3 col-lg-3"><spring:message code="label.percent"/></div>
						<div class="col-sm-7 col-lg-7"><form:input path="percentage" id="txtPincode" value="0" style=""/>	
								
							<input type="button" value="Generate" size="5" onClick="percent()" style="margin-bottom: 10px;" class="btn btn-success"/>
							<form:input path="percentAmt" value="0" size="20" readonly="readonly" style="width:36%;"/>	
																						
						</div>
						
						<div class="col-sm-3 col-lg-3"><spring:message code="label.procFees"/></div>
						<div class="col-sm-7 col-lg-7">
									<form:input path="procFee" id="txtPincode" value="0" style="width:85%;"/>																		
						</div>													
						<div class="col-sm-3 col-lg-3"><spring:message code="label.docFee"/></div>
						<div class="col-sm-7 col-lg-7"><form:input path="docFee" id="txtPincode" value="0" style="width:85%;"/></div>																	
													
						<div class="col-sm-3 col-lg-3"><spring:message code="label.lateFee"/></div>
						<div class="col-sm-7 col-lg-7"><form:input path="lateFee" id="txtPincode" value="0" style="width:85%;"/></div>																	
						
							
						<div class="col-sm-3 col-lg-3"><spring:message code="label.taxName"/></div>
						<div class="col-sm-7 col-lg-7"><form:select path="taxName" style="width:87%;">
									
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
									</form:select>
							</div>
							<div class="col-sm-3 col-lg-3"><spring:message code="label.taxPerc"/></div>
							<div class="col-sm-7 col-lg-7"><form:input path="taxPercentage" id="txtPincode" value="0"/>	
									
								<input type="button" value="Generate"   class="btn btn-success" onClick="taxamnt()"  style="margin-bottom: 10px;">
								<form:input path="taxAmt" value="0" size="20" readonly="readonly" style="width:36%;"/>															
							</div>
							
							
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
																						
						
						
							<div class="col-sm-11 col-lg-11">
									<input type="button" value="Calculate Total Amount" class="btn btn-success" onClick="Amount()"/>
							</div>
						<div class="col-sm-3 col-lg-3"><spring:message code="label.amtAllChrg"/></div>
						<div class="col-sm-7 col-lg-7"><form:input path="funalAmt" id="txtPincode" value="0" style="width:85%;"/>	</div>																	
							
			<div class="col-sm-11"></div>		
			<div class="col-sm-11">${success}</div>
			<br><br>
		<div class="col-sm-11">
			<table align="center">
				<tr>
					<td><input type="submit" size="3" value="<spring:message code="label.save"/>" class="btn btn-primary"/></td>
					<td><button type="button" name="Back"  onclick="javascript:window.location='/annona/bnkEmp/bankEmp';" class="btn btn-success"><spring:message code="label.back"/></button></td>
				</tr>
			</table>
		</div>
		
	</div>
			</form:form>
</div>
</div>		
</section>