<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="col-sm-9 col-md-9 body_fixed">	
<div class="col-sm-12 col-md-12">

				<table align="center" width="400">


					<tr>
						<td class="col-sm-6"><b><spring:message code="label.transactionId"/>:</b></td>
						<td class="col-sm-6">${model.invoiceForm.transactionId}</td>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.transactionType"/>:</b></td>
						<td class="col-sm-6"><spring:message code="label.acc/rej"/></td>
					</tr>

					<tr>
						<td class="col-sm-6"><b><spring:message code="label.transactionStatus"/>:</b></td>
						<td class="col-sm-6"><font color="green"><spring:message code="label.submitSuccessfully"/>.</font></td>
					</tr>
				

				</table>



			</div>
		</div>

