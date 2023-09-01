<section>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<div class="container">
		<div class="row">
			
			<div class="row-fluid apps">


				<table align="center">


					<tr>
						<td class="heading_text"><b><spring:message code="label.transactionId"/></b></td>
						<td>${model.purchaseOrderForm.transactionId}</td>
					<tr>
						<td><b><spring:message code="label.transactionType"/></b></td>
						<td><spring:message code="label.purchaseOrderApproval"/></td>
					</tr>

					<tr>
						<td><b><spring:message code="label.status"/></b></td>
						<td><font color="green"><spring:message code="label.updateSuccessfully"/></font></td>
					</tr>

				</table>

				<div class="col-sm-11"></div>


			</div>
		</div>
</section>
