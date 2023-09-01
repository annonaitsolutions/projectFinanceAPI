<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>



<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
			</div>
<form:form action="limitBurstRatesAssignPost" method="post" name="myForm" commandName="limitBurstForm" onsubmit="return val();">

			<div class="col-sm-12 col-md-12 col-lg-12">
								<table align="center">
									
									<form:hidden path="id"  />
									<form:hidden path="transactionId"  />
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.amount"/></b></td>
										<td class="col-sm-7"><form:input path="reqAmt" id="reqAmt" placeholder="Enter cost" readonly="true"></form:input>
																								
									</tr>
									
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.tenure"/></b></td>
										<td class="col-sm-7"><form:input path="tenure" readonly="true" placeholder="Enter tenure"></form:input>
											
									</tr>
									
										
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.interestCharge"/></b></td>
										<td class="col-sm-7"><form:input path="intRate" placeholder="Enter Interest" id="intRate" readonly="true"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.totalCharge"/></b></td>
										<td class="col-sm-7"><form:input path="totalRate" placeholder="Enter Interest" id="intRate" readonly="true"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.finalAmount"/></b></td>
										<td class="col-sm-7"><form:input path="finalAmt" placeholder="Enter Interest" id="intRate" readonly="true"></form:input>
											
									</tr>
							
									</table>
									<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" size="3" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="setLimitBurstRatesList" class="btn btn-success"><spring:message code="label.back"/></a></td>
							
					
						</tr>
					</table>
		        </div>
									</div>
								


</form:form>

		
    

</div>

