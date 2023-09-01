<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="col-sm-9 col-md-9 body_fixed">
	 <table align="center" width="800" height="80" style="margin-top: 50px;">


					<tr>
						<td><b><spring:message code="label.transactionId"/>:</b></td>
						<td>${model.poUploadForm.transactionId}</td>
					<tr>
					<tr>
						<td><b><spring:message code="label.transactionType"/>:</b></td>
						<td><spring:message code="label.uploadDocs"/></td>
					</tr>

					<tr>
						<td><b><spring:message code="label.transactionStatus"/>:</b></td>
						<td><font color="green"><spring:message code="label.updateSuccessfully"/>.</font></td>
					</tr>
					<tr>
					<h3 ><spring:message code="label.note"/>:    <font color="green"><spring:message code="label.fileName"/> : "<strong> ${message}</strong>"<spring:message code="label.filesUploadedSuccesssfully"/> !</font> </h3> 
                   </tr>
				</table>
 </div>
