<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center"><spring:message code="label.creditAssignment"/></h3>
		</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
			<form:form commandName="masterPlanForm" onsubmit="return val();">
								<table align="center">
								<form:hidden path="id" />
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.buyingCost"/></b></td>
										<td class="col-sm-6"><form:input path="buyingCost" value="${model.masterPlanForm.buyingCost}"  readonly="true"></form:input>
																								
									</tr>
									
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.tenure"/></b></td>
										<td class="col-sm-6"><form:input path="tenure" value="${model.masterPlanForm.tenure}" readonly="true"  id="tenure"></form:input>
											
									</tr>
								<tr>
										<td class="col-sm-6"><b><spring:message code="label.sanctionedBuyingAmount"/></b></td>
										<td class="col-sm-6"><form:input path="buyingCostSanc" value="${model.masterPlanForm.buyingCostSanc}" readonly="true"  id="buyingCostSanc"></form:input>
											
									</tr>
									<!-- --- -->
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.interestCharge"/></b></td>
										<td class="col-sm-6"><form:input path="rateOfInt" value="${model.masterPlanForm.rateOfInt}" readonly="true"  id="rateOfInt"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.totalInterestAmount"/></b></td>
										<td class="col-sm-6"><form:input path="rateOfInt1" value="${model.masterPlanForm.rateOfInt1}" readonly="true"  id="rateOfInt1"></form:input>
											
									</tr>
									<!-- ------ -->
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.flatCharges"/></b></td>
										<td class="col-sm-6"><form:input path="flatCharges" value="${model.masterPlanForm.flatCharges}" readonly="true"  id="flatCharges"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.procFees"/></b></td>
										<td class="col-sm-6"><form:input path="procFee" value="${model.masterPlanForm.procFee}" readonly="true"  id="procFee"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.docFee"/></b></td>
										<td class="col-sm-6"><form:input path="docFee" value="${model.masterPlanForm.docFee}" readonly="true"  id="docFee"></form:input>
											
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.lateFee"/></b></td>
										<td class="col-sm-6"><form:input path="lateFee" value="${model.masterPlanForm.lateFee}" readonly="true"  id="lateFee"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.totalTax"/></b></td>
										<td class="col-sm-6"><form:input path="totalTaxAmt" value="${model.masterPlanForm.totalTaxAmt}" readonly="true"  id="totalTax"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.totalAmount"/></b></td>
										<td class="col-sm-6"><form:input path="funalAmt" value="${model.masterPlanForm.funalAmt}" readonly="true"  id="funalAmt"></form:input>
											
									</tr>
		
							</table>
							</form:form>
		 </div>		
						<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							
							<td><a href="appMasterPlanDetails" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
									
</div>