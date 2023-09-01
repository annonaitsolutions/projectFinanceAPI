<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<section>	
<h2 style="margin-left:500px"><spring:message code="label.confirmationScreen"/></h2>
		
	<form:form action="getRateListPost" name="interest" method="post" commandName="purchaseOrderForm">
		
			<div class="content">
          <table style="margin-left:90px;margin-top:-5px;border:1px solid #D4E0E4"; width="865px;">
							 <tr style="background-color:#D4E0E4;">
							 <form:hidden path="id"/>
							 <form:hidden path="transactionId"/>
								<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									<font color="Red"></font>  <spring:message code="label.bankName"/>
									<form:input path="bankName"  style="margin-left:108px;" readonly="true"/>
								</td>																
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
									<font color="Red"></font> <spring:message code="label.supplierName"/>
									<form:input path="supplierName"  style="margin-left:141px;" readonly="true"/>
								</td>																
							</tr>
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									<font color="Red"></font><spring:message code="label.supplierEmail"/>
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
									<font color="Red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font> <spring:message code="label.amount"/>
									  <form:input path="amount" id="txtPincode" style="margin-left:190px;" readonly="true"/>
									 
								</td>																
							</tr>
					
					
					
					 						
					
						<br>
						<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.typeOfInterest"/>
									<form:input id="field1" readonly="true" path="interestType"  style="margin-left:115px;" disabled="true"/>									
								</td>																
							</tr>
						
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.rateOfInterest"/>
									<form:input id="field1" readonly="true" path="rateOfInt" style="margin-left:115px;" disabled="true"/>									
								</td>																
							</tr>						
							<tr>
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.noOfDays"/>
									<form:input path="tenure" readonly="true" id="txtPincode" style="margin-left:164px;"/>									
								</td>																
							</tr>						
					</table>
						<br/>
						<table style="margin-left:90px;margin-top:-5px;border:1px solid #D4E0E4"; width="865px;">
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.plrRate"/>
									<form:input id="field2" path="plrRate" readonly="true"  style="margin-left:173px;" disabled="true"/>									
								</td>																
							</tr>						
							<tr>
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.basicPoints"/>
									<form:input id="field3" readonly="true"  path="basicPoints" value="0" style="margin-left:155px;" disabled="true"/>	
									
								</td><td><form:input id="field4" path="basicAmt"  size="20" readonly="readonly" disabled="true"/></td>																
							</tr>						
							<tr style="background-color:#D4E0E4;">
							
							<form:hidden  path="calPlrRate" size="20"  readonly="readonly" disabled="true"/>
								
								<td><form:input id="field5" path="calPlrRate" size="20"  readonly="readonly" disabled="true"/> 
																	
								</td>																
							</tr>						
						</table>
						<br/>
						<table style="margin-left:90px;margin-top:-5px;border:1px solid #D4E0E4"; width="865px;">
								
							<tr>

							<form:hidden path="total1" readonly="readonly"/>
							</tr>
						</table>
						<br/>
						<table style="margin-left:90px;margin-top:-5px;border:1px solid #D4E0E4"; width="865px;">
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.amountInterest"/>
									<form:input path="rateOfInt1" readonly="true"  id="txtPincode" style="margin-left:109px"/>																		
								</td>																
							</tr>													
						</table>
						<br/>
						<img src="img/11/Content1.png" style="margin-top:25px;margin-left:90px;"></img>
						<table style="margin-left:90px;margin-top:-5px;border:1px solid #D4E0E4"; width="865px;">
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.flatCharges"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<form:input path="flatCharges" readonly="true" />
	
						            </td>														
							</tr>													
							<tr>
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.percent"/>
									<form:input path="percentage" id="txtPincode" value="0" style="margin-left:137px;"/>	
									<td><form:input path="percentAmt"  size="20" readonly="readonly"/></td>																
							</tr>														
						</table>
						<br/>
						<table style="margin-left:90px;margin-top:-5px;border:1px solid #D4E0E4"; width="865px;">
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.procFees"/>
									<form:input path="procFee" id="txtPincode"  style="margin-left:129px"/>																		
								</td>																
							</tr>													
							<tr>
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.docFee"/>
									<form:input path="docFee" id="txtPincode"  style="margin-left:68px"/>																		
								</td>																
							</tr>													
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.lateFee"/>
									<form:input path="lateFee" id="txtPincode"  style="margin-left:169px"/>																		
								</td>																
							</tr>	
							</table><br>
							
						<table style="margin-left:90px;margin-top:-5px;border:1px solid #D4E0E4"; width="865px;">
						
						
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><spring:message code="label.taxName"/>
									<form:input path="taxName" style="margin-left:163px;  width:172px;" readonly="true" />
									
								</td>																
							</tr>
							<tr>
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.taxPerc"/>
									<form:input path="taxPercentage" id="txtPincode" value="0" style="margin-left:107px;"/>	
								<td><form:input path="taxAmt"  size="20" readonly="readonly"/></td>																
							</tr>														
						</table>
						
						<table style="margin-left:90px;margin-top:-5px;border:1px solid #D4E0E4"; width="865px;">
						
						
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><spring:message code="label.taxName"/>
									<form:input path="taxName1" style="margin-left:163px;  width:172px;" readonly="true" />
									
								</td>																
							</tr>
							<tr>
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.taxPerc"/>
							<td><form:input path="taxAmt1"  size="20" readonly="readonly"/></td>																
							</tr>														
						</table>
						<table style="margin-left:90px;margin-top:-5px;border:1px solid #D4E0E4"; width="865px;">
						
						
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><spring:message code="label.taxName"/>
									<form:input path="taxName2" readonly="true"  style="margin-left:163px;  width:172px;"/>
									
								</td>																
							</tr>
							<tr>
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.taxPerc"/>
							<td><form:input path="taxAmt2"  size="20" readonly="readonly"/></td>																
							</tr>															
						</table>
																						
						<br/>
						<table style="margin-left:90px;margin-top:-5px;border:1px solid #D4E0E4"; width="865px;">
							<tr style="background-color:#D4E0E4;">
								<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.amtAllChrg"/>
									<form:input path="funalAmt" id="txtPincode" style="margin-left:67px" readonly="true" />																		
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