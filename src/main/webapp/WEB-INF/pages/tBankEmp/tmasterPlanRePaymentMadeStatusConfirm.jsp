<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="col-sm-9 col-md-9 body_fixed">	
<div class="col-sm-12 col-md-12 header_customer">
<div class="heading"><h3 align="center"><spring:message code="label.repay"/></h3></div>
</div>

<form:form  action="tmasterPlanRePaymentMadeStatusPost" name="myForm" commandName="trepaymentForm" onsubmit="return val();">

		<div class="col-sm-12 col-md-12">	
					<div class="col-sm-12 col-md-12">
								<table align="center">
						
									<tr>
										<td class="col-sm-6"><b> <spring:message code="label.customer"/>:</b></td>
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
										<td class="col-sm-6"><b><spring:message code="label.amountPaid"/>:</b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="amountPaid" readonly="true" id="comment" placeholder="Enter Comment"></form:input>
										
										</td>
									</tr>
									</table>
									</div>
						
									<form:hidden path="id"  />
										<form:hidden path="customerEmail"  />
									<form:hidden path="transactionId" />
							<div class="col-sm-12 col-md-12 col-lg-12">	
								<p align="center" style="padding-top: 10px;">
					
					<input type="submit" class="btn btn-primary" value="Save">
								<a href="bankEmp" class="btn btn-success" name="back"  onclick="javascript:window.location='#';"/>back</a>
								</p>
							</div>
									
	</div>

</form:form>
	</div>
<style>
.link p a{
color: tomato;
}
</style>

