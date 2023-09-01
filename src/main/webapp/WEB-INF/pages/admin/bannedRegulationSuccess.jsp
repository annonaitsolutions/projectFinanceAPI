<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
			<div class="successMsg"
				style="text-align: center; color: green; font-size: 18px;">${success}</div>
			<div class="row-fluid apps">


				<table align="center" width="400">


					<tr>
						<td class="heading_text"><b><spring:message code="label.transactionId"/>:</b></td>
						<td>${model.regulationsForm.transactionId}</td>
					<tr>
						<td><b><spring:message code="label.transactionType"/>:</b></td>
						<td><spring:message code="label.regulation"/></td>
					</tr>

					<tr>
										<td><b><spring:message code="label.status"/></b></td>
										<td>
										<font color="green"><spring:message code="label.updateSuccessfully"/></font>
											
										</td>																
									</tr>

				</table>

			</div>
		</div>

