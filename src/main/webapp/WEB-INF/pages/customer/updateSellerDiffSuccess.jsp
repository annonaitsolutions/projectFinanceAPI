 <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
			<div class="successMsg"
				style="text-align: center; color: green; font-size: 18px;">${success}</div>

				<table align="center">


					<tr>
						<td class="heading_text"><b><spring:message code="label.transactionId"/>:</b></td>
						<td>${model.sellerDiffBankEventForm.transactionId}</td>
					<tr>
						<td><b><spring:message code="label.transactionType"/>:</b></td>
						<td><spring:message code="label.sellerDifferentBankEventDetails"/></td>
					</tr>

					<tr>
						<td><b><spring:message code="label.status"/>:</b></td>
						<td><font color="green"><spring:message code="label.updateSuccessfully"/></font></td>
					</tr>

				</table>

			


			</div>
	
