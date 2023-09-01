<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<section>
	<div class="container">
		<div class="row">
			
			<div class="row-fluid apps">


				<table align="center">


					<tr>
						<td class="heading_text"><b><spring:message code="label.transactionId"/>:</b></td>
						<td>${model.tquarterlyForm.transactionId}</td>
					<tr>
						<td><b><spring:message code="label.transactionType"/>:</b></td>
						<td><spring:message code="label.repayRatesSet"/></td>
					</tr>

					<tr>
						<td><b><spring:message code="label.transactionStatus"/>:</b></td>
						<td><font color="green"><spring:message code="label.submitSuccessfully"/>.</font></td>
					</tr>
				

				</table>

				<div class="col-sm-11"></div>


			</div>
		</div>
</section>
