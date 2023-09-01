<section>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<div class="row">
			<div class="successMsg"
				style="text-align: center; color: green; font-size: 18px;">${success}</div>
			<div class="row-fluid apps">


				<table align="center">


					<tr>
						<td class="heading_text"><b><spring:message code="label.transactionId"/></b></td>
						<td>${model.buyerSameBankEventForm.transactionId}</td>
					<tr>
						<td><b><spring:message code="label.transactionType"/></b></td>
						<td><spring:message code="label.buyerSameBankEventDetails"/></td>
					</tr>

					<tr>
						<td><b><spring:message code="label.status"/></b></td>
						<td><font color="green"><spring:message code="label.saveSuccessfully"/></font></td>
					</tr>

				</table>

				<div class="col-sm-11"></div>


			</div>
		</div>
</section>
