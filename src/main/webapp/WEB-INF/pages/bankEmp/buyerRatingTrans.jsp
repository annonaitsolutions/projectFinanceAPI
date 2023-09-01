<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
			<div class="successMsg"
				style="text-align: center; color: green; font-size: 18px;">${success}</div>
		
				<table align="center" width="400">


					<tr>
						<td class="col-sm-6"><b><spring:message code="label.transactionId"/></b></td>
						<td class="col-sm-6">${model.newBuyerForm.transactionId}</td>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.transactionType"/></b></td>
						<td class="col-sm-6"><spring:message code="label.rating"/></td>
					</tr>

					<tr>
						<td class="col-sm-6"><b><spring:message code="label.status"/></b></td>
						<td class="col-sm-6"><font color="green"><spring:message code="label.updateSuccessfully"/></font></td>
					</tr>

				</table>

		</div>

