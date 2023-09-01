<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="col-sm-9 col-md-9 body_fixed">	
<div class="col-sm-12 col-md-12 header_customer">
	<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
</div>

<form:form  action="requestMoneyAcceptOrNoPost" name="myForm" commandName="invoiceForm" onsubmit="return val();">

		<div class="col-sm-12 col-md-12">	
					<div class="col-sm-12 col-md-12">
								<table align="center">
						
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerName"/>:</b></td>
										<td class="col-sm-6"><form:input path="customerName" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
										
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.invoiceKey"/>:</b></td>
										<td class="col-sm-6"><form:input path="poKey" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
								
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.buyerName"/>:</b></td>
										<td class="col-sm-6"><form:input path="buyerName" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>														
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.paymentAmount"/>:</b></td>
										<td class="col-sm-6"><form:input path="funalAmt" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.tenure"/>:</b></td>
										<td class="col-sm-6"><form:input path="tenure" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.status"/>:</b></td>
										<td class="col-sm-6"><form:input path="transStatus" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
								
						        
									</table>
						</div>
						
									<form:hidden path="id"  />
									<form:hidden path="transactionId" />
							<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="requestMoneyAccepted" class="btn btn-success"><spring:message code="label.back"/></a></td>
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

