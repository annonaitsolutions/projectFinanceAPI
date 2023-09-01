<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
 			<div class="col-sm-12 col-md-12">
				<div class="successMsg" style="text-align: center; color: green; font-size: 18px;">${success}</div>
			</div>
			<div class="col-sm-12 col-md-12">
				<table align="center" width="400">
						<tr>
							<td class="heading_text"><b><spring:message code="label.transactionId"/>:</b></td>
							<td>${model.buyerDiffBankEventForm.transactionId}</td>
						<tr>
							<td><b><spring:message code="label.transactionType"/>:</b></td>
							<td><spring:message code="label.buyerEventsDetail"/></td>
						</tr>
	
								<tr>
						<td><b><spring:message code="label.transactionStatus"/>:</b></td>
						<td><font color="green"><spring:message code="label.submitSuccessfully"/>.</font></td>
					</tr>
	
				</table>
			</div>
</div>
