<%@include file="taglib_includes.jsp"%>
<section>
	<div class="container">
		<div class="row">
		
			<div class="row-fluid apps">


				<table align="center">


					<tr>
						<td class="heading_text"><b><spring:message code="label.transactionId"/>:</b></td>
						<td>${model.supplierForm.transactionId}</td>
					<tr>
						<td><b><spring:message code="label.transactionType"/>:</b></td>
						<td><spring:message code="label.suppDetails"/></td>
					</tr>

					<tr>
						<td><b><spring:message code="label.transactionStatus"/>:</b></td>
						<td><font color="green"><spring:message code="label.updateSuccessfully"/></font></td>
					</tr>

				</table>

				<div class="col-sm-11"></div>


			</div>
		</div>
</section>