<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12">
				<table align="center" width="600">


					<tr>

						<td class="heading_text"><b><spring:message code="label.transactionId"/>:</b></td>


						<td>${model.fundsDistributeForm.transactionId}</td>
					</tr>
					<tr>
						<td><b><spring:message code="label.transactionType"/>:</b></td>
						<td><spring:message code="label.fundsDistribute"/></td>
					</tr>

					<tr>
						<td><b><spring:message code="label.status"/>:</b></td>
						<td><font color="green"><spring:message code="label.distributeSuccessfully"/>.</font></td>
					</tr>
					<tr>
						<td><b><spring:message code="label.note"/>:</b></td>

						<td><font color="orange"><spring:message code="label.goToDistributeFund"/>  <a href="masterPlanFunds" style="color:tomato;"><spring:message code="label.clickHere"/> </a></font></td>

					
					</tr>

				</table>

			</div>
		</div>
