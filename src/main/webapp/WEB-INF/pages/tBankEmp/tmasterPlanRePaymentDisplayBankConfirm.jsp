<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 body_fixed">	
<div class="col-sm-12 col-md-12 header_customer">
<h3 align="center"><spring:message code="label.repay"/></h3>
</div>
<form:form name="myForm" action="tmasterPlanRePaymentSave" commandName="tquarterlyForm" onsubmit="return val();">

		<div class="col-sm-12 col-md-12">	
					<div class="col-sm-12 col-md-6">
								<table align="center">
						
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customer"/>:</b></td>
										<td class="col-sm-6"><form:input path="customer" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
										
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.masterKey"/>:</b></td>
										<td class="col-sm-6"><form:input path="masterKey" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
								
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.amountType"/>:</b></td>
										<td class="col-sm-6"><form:input path="amtType" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>														
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.paymentOption"/>:</b></td>
										<td class="col-sm-6"><form:input path="payOption" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.amountPaid"/>:</b></td>
										<td class="col-sm-6"><form:input path="amountPaid" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.loanDte"/>:</b></td>
										<td class="col-sm-6"><form:input path="loanDate" readonly="true" placeholder="Enter Date" id="customerPrefix"></form:input>
											
									</tr>
										<tr>
										<td class="col-sm-6"><b><spring:message code="label.amountToPay"/>:</b></td>
										<td class="col-sm-6"><form:input path="amount" readonly="true" placeholder="Enter Interest" id="customerPrefix"></form:input>
											
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.interestCharge"/>:</b></td>
										<td class="col-sm-6"><form:input path="intRate" readonly="true" placeholder="Enter Interest" id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.noOfDay"/>(%):</b></td>
										<td class="col-sm-6"><form:input path="payDate" readonly="true" placeholder="Enter Interest" id="customerPrefix"></form:input>
											
									</tr>
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.interestAmount"/>:</b></td>
											 <td class="col-md-6"><form:input path="totalAmount"  readonly="true"/></td>
						         
						       		</tr>
						         <tr>
										<td class="col-sm-6"><b><spring:message code="label.loanDte"/>:</b></td>
										<td class="col-sm-6"><form:input path="loanDate1" readonly="true" placeholder="Enter Date" id="customerPrefix"></form:input>
											
									</tr>
						         
										<tr>
										<td class="col-sm-6"><b><spring:message code="label.amountToPay"/>:</b></td>
										<td class="col-sm-6"><form:input path="amount1" readonly="true" placeholder="Enter Interest" id="customerPrefix"></form:input>
											
									</tr>
							
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.interestCharge"/>:</b></td>
										<td class="col-sm-6"><form:input path="intRate1" readonly="true" placeholder="Enter Interest" id="customerPrefix"></form:input>
											
									</tr>
						</table>
						</div>
						<div class="col-sm-12 col-md-6 col-lg-6">
							<table>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.noOfDay"/>(%):</b></td>
										<td class="col-sm-6"><form:input path="payDate1" readonly="true" placeholder="Enter Interest" id="customerPrefix"></form:input>
											
									</tr>
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.interestAmount"/>:</b></td>
											 <td class="col-md-6"><form:input path="totalAmount1"  readonly="true"/></td>
						         
						       		</tr>
						         
						         <tr>
										<td class="col-sm-6"><b><spring:message code="label.loanDte"/>:</b></td>
										<td class="col-sm-6"><form:input path="loanDate2" readonly="true" placeholder="Enter Date" id="customerPrefix"></form:input>
											
									</tr>
						         
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.amountToPay"/>:</b></td>
										<td class="col-sm-6"><form:input path="amount2" readonly="true" placeholder="Enter Interest" id="customerPrefix"></form:input>
											
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.interestCharge"/>:</b></td>
										<td class="col-sm-6"><form:input path="intRate2" readonly="true" placeholder="Enter Interest" id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.noOfDay"/>(%):</b></td>
										<td class="col-sm-6"><form:input path="payDat2" readonly="true" placeholder="Enter Interest" id="customerPrefix"></form:input>
											
									</tr>
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.interestAmount"/>:</b></td>
										 <td class="col-md-6"><form:input path="totalAmount2"  readonly="true"/></td>
						         
						        	</tr>
						         <tr>
										<td class="col-sm-6"><b><spring:message code="label.loanDte"/>:</b></td>
										<td class="col-sm-6"><form:input path="loanDate3" readonly="true" placeholder="Enter Date" id="customerPrefix"></form:input>
											
									</tr>
						         
						         
										<tr>
										<td class="col-sm-6"><b><spring:message code="label.amountToPay"/>:</b></td>
										<td class="col-sm-6"><form:input path="amount3" readonly="true" placeholder="Enter Interest" id="customerPrefix"></form:input>
											
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.interestCharge"/>:</b></td>
										<td class="col-sm-6"><form:input path="intRate3" readonly="true" placeholder="Enter Interest" id="customerPrefix"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.noOfDay"/>(%):</b></td>
										<td class="col-sm-6"><form:input path="payDate3" readonly="true" placeholder="Enter Interest" id="customerPrefix"></form:input>
											
									</tr>
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.interestAmount"/>:</b></td>
											 <td class="col-md-6"><form:input path="totalAmount3"  readonly="true"/></td>
						         	</tr>
						       
									</table>
						</div>
						
									<form:hidden path="id"  />
										<form:hidden path="customerEmail"  />
									<form:hidden path="transactionId" />
							<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-6"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="tmasterPlanRePaymentBank" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
									
	</div>

</form:form>
	</div>
<style>
.link p a{
color: tomato;
}
</style>

