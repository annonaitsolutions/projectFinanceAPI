<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">

<div class="col-sm-12 col-md-12 col-lg-12">

				<table align="center" width="400">


					<tr>
						<td class="heading_text"><b><spring:message code="label.transactionId"/></b></td>
						<td>${model.wareHouseMngForm.transactionId}</td>
					<tr>
						<td><b><spring:message code="label.transactionType"/></b></td>
						<td><spring:message code="label.warreHouseMngApp"/></td>
					</tr>

					<tr>
						<td><b><spring:message code="label.status"/></b></td>
						<td><font color="green"><spring:message code="label.saveSuccessfully"/></font></td>
					</tr>

				</table>
</div>
</div>	
