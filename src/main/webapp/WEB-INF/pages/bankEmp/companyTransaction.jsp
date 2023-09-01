<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
	<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<table align="center" width="400" height="80" style="margin-top: 50px;">
		<tr>
			<td><b><spring:message code="label.transactionId" /></b></td>
			<td>${model.companyForm.transactionId}</td>
		<tr>
			<td><b><spring:message code="label.transactionType" /></b></td>
			<td>${transaction.transactionType}</td>
		</tr>

		<tr>
			<td><b><spring:message code="label.status" /></b></td>
			<td><font color="green">${transaction.transactionStatus}</font></td>
		</tr>

	</table>

</div>
<style>
.bankemp_footer {
	margin-top: 124px;
}
</style>