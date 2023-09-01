<div class="col-sm-9 col-md-9 body_fixed">
			<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
			
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<table align="center">


				<tr>
						<td class="heading_text"><b><spring:message code="label.transactionId"/></b></td>
						<td>${model.purchaseOrderForm.transactionId}</td>
					</tr>
					<tr>
						<td><b><spring:message code="label.transactionType"/></b></td>
						<td><spring:message code="label.uploadDocs"/></td>
					</tr>

					<tr>
						<td><b><spring:message code="label.status"/></b></td>
						<td><font color="green"><spring:message code="label.updateSuccessfully"/></font></td>
					</tr>
					<tr>
					 <h3 >   <b><spring:message code="label.note"/></b> <font color="green"> <spring:message code="label.filesUploadedSuccesssfully"/>!</font> </h3> 
                   </tr>
				</table>

 </div>
 </div>