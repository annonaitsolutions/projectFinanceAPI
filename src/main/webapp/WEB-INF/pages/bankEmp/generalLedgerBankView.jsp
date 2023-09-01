<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center"><spring:message code="label.masterPlanDetails"/></h3>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
			 <div  class="successMsg"><b><font color="green">${success}</font></b></div>
		</div>
		<div class="col-sm-12 col-md-6 col-lg-6">
			<div class="col-sm-12 col-md-12 col-lg-12">
				<h3 style="font-size:19px; font-weight:400; color:#2E9AFE;"><spring:message code="label.masterPlanMainDetails"/></h3>
			</div>
				<table width="400">
						<tr>
							<td class="heading_text"><b><spring:message code="label.masterKey"/>:</b></td>
							<td>${model.masterPlanForm.masterKey}</td>
						</tr>
						
						<tr>
							<td class="heading_text"><b><spring:message code="label.customer"/>:</b></td>
							<td>${model.masterPlanForm.customer}</td>
						</tr>
						
						<tr>
							<td class="heading_text"><b><spring:message code="label.tenure"/>:</b></td>
							<td>${model.masterPlanForm.tenure}</td>
						</tr>
						
						<tr>
							<td class="heading_text"><b><spring:message code="label.bankEmployeeStatus"/>:</b></td>
							<td>${model.masterPlanForm.status}</td>
						</tr>
					
						<tr>
							<td class="heading_text"><b><spring:message code="label.managerStatus"/>:</b></td>
							<td>${model.masterPlanForm.managerStatus}</td>
						</tr>
				</table>
				</div>
				<div class="col-sm-12 col-md-6 col-lg-6">
				<div class="col-sm-12 col-md-12 col-lg-12">
						<h3 style="font-size:19px; font-weight:400; color:#2E9AFE;"><spring:message code="label.productInfo"/></h3>
					</div>
					<table width="400">
						<tr>
						<td class="heading_text"><b><spring:message code="label.category"/>:</b></td>
						<td>${model.masterPlanForm.category}</td>
						</tr>
					<tr>
					<td class="heading_text"><b><spring:message code="label.product"/>:</b></td>
						<td>${model.masterPlanForm.product}</td>
						</tr>
					
						<tr>
					<td class="heading_text"><b><spring:message code="label.license"/>:</b></td>
						<td>${model.masterPlanForm.licence}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.weight"/>:</b></td>
						<td>${model.masterPlanForm.weight}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.quantity"/>:</b></td>
						<td>${model.masterPlanForm.quantity}</td>
						</tr>
				</table>
				</div>
				
					<div class="col-sm-12 col-md-6 col-lg-6">
				<div class="col-sm-12 col-md-12 col-lg-12">
						<h3 style="font-size:19px; font-weight:400; color:#2E9AFE;"><spring:message code="label.ratesDetails"/></h3>
					</div>
					<table width="400">
						<tr>
						<td class="heading_text"><b><spring:message code="label.rateOfInterest"/>:</b></td>
						<td>${model.masterPlanForm.rateOfInt}</td>
						</tr>
					<tr>
					<td class="heading_text"><b><spring:message code="label.plrRate"/>:</b></td>
						<td>${model.masterPlanForm.plrRate}</td>
						</tr>
					
						<tr>
					<td class="heading_text"><b><spring:message code="label.amountInterest"/>:</b></td>
						<td>${model.masterPlanForm.rateOfInt1}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.flatCharges"/>:</b></td>
						<td>${model.masterPlanForm.flatCharges}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.percent"/>:</b></td>
						<td>${model.masterPlanForm.percentage}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.procFees"/>:</b></td>
						<td>${model.masterPlanForm.procFee}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.docFee"/>:</b></td>
						<td>${model.masterPlanForm.docFee}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.lateFee"/>:</b></td>
						<td>${model.masterPlanForm.lateFee}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.taxPerc"/>:</b></td>
						<td>${model.masterPlanForm.taxPercentage}</td>
						</tr>
						
						
				</table>
				</div>
				<div class="col-sm-12 col-md-6 col-lg-6">
				<div class="col-sm-12 col-md-12 col-lg-12">
						<h3 style="font-size:19px; font-weight:400; color:#2E9AFE;"><spring:message code="label.overAllTransDetails"/></h3>
					</div>
					<table width="400">
						<tr>
						<td class="heading_text"><b><spring:message code="label.sanctionedAmount"/>:</b></td>
						<td>${model.masterPlanForm.funalAmt}</td>
						</tr>
					<tr>
					<td class="heading_text"><b><spring:message code="label.amountPaid"/>:</b></td>
						<td>${model.masterPlanForm.amountPaid}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.balance"/>:</b></td>
						<td>${model.masterPlanForm.balance}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.amtBuyin"/>:</b></td>
						<td>${model.masterPlanForm.wcTotalAmount}</td>
						</tr>
						<tr>
					<td class="heading_text"><b><spring:message code="label.amtSelling"/>:</b></td>
						<td>${model.masterPlanForm.calPlrRate}</td>
						</tr>
				</table>
				</div>
				<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>							
							<td><a href="generalLedgerBank" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
				</div>
				