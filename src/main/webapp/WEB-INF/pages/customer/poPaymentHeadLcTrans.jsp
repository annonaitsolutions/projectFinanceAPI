<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12">
				<table align="center" width="400">


					<tr>
						<td class="heading_text"><b><spring:message code="label.transactionId"/>:</b></td>
						<td>${model.letterOfCreditForm.transactionId}</td>
					<tr>
						<td><b><spring:message code="label.transactionType"/>:</b></td>
						<td><spring:message code="label.poPayment"/></td>
					</tr>

				<tr>
						<td><b><spring:message code="label.transactionStatus"/>:</b></td>
						<td><font color="green"><spring:message code="label.submitSuccessfully"/>.</font></td>
					</tr>
				

				</table>

			</div>
			<%-- <div class="col-sm-12 col-md-12 col-lg-12" align="center">

		<video id="paymentVideo" width="400" height="300" controls loop>
			<source	src="<%=request.getContextPath()%>/resources/videos/payment_lcbg.mp4" type="video/mp4" id="payment">
		</video>
	</div> --%>
		</div>