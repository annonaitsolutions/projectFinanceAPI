
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12">

				<table align="center">


					<tr>
						<td class="col-sm-4"><b><spring:message code="label.transactionId"/></b></td>
						<td class="col-sm-8">${model.masterPlanForm.transactionId}</td>
					</tr>
					<tr>
						<td class="col-sm-4"><b><spring:message code="label.transactionType"/></b></td>
						<td class="col-sm-8"><spring:message code="label.acc/rej"/></td>
					</tr>

					<tr>
						<td class="col-sm-4"><b><spring:message code="label.status"/></b></td>
						<td class="col-sm-8"><font color="green"><spring:message code="label.submitSuccessfully"/></font></td>
					</tr>
					<tr>
						<td class="col-sm-4"><b><spring:message code="label.note"/>:</b></td>
						<td class="col-sm-8"><font color="orange"><spring:message code="label.noteMessage"/></font></td>
					</tr>

					<tr>
						<td class="col-sm-4"><b><spring:message code="label.note"/>:</b></td>
						<td class="col-sm-8"><font color="red"><spring:message code="label.distributeFundsNote"/> <a href="masterPlanFunds" style="color: blue;"><spring:message code="label.clickHere"/></a></font></td>
					</tr>

				</table>
			</div>
		</div>