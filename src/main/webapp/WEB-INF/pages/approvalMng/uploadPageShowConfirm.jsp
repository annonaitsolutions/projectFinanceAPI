<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function validateForm() {
		/* var d = new Date().getTime();
		var uuid = 'xxxxxx'.replace(/[xy]/g, function(c) {
			var r = (d + Math.random() * 16) % 16 | 0;
			d = Math.floor(d / 16);
			return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
		});
		document.uploadDocument.transactionId.value = uuid; */
	}
</script>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3><spring:message code="label.buyerUploadDocsDetails"/></h3>
			</div>
				<form:form name="uploadDocument" action="uploadPageShowConfrim" method="post"
					commandName="uploadedFileForm" onsubmit="return validateForm();">
					<table align="center">
					
					<tr>
					<td class="col-sm-6"><b><spring:message code="label.id"/></b></td>
					<td class="col-sm-6">
					<form:input path="id" id="id" 	value="${model.uploadedFileForm.id}" readonly="true"/>
					</td>
				</tr>
					 <tr>
							<td class="col-sm-6"><b><spring:message code="label.customerName"/></b></td>
							<td class="col-sm-6"><form:input path="customerName" id="customerName"
									value="${model.uploadedFileForm.customerName}" readonly="true" />
						</tr> 
 
					
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.docsType"/></b></td>
							<td class="col-sm-6"><form:input path="document"
									value="${model.uploadedFileForm.document}" readonly="true" /></td>

						</tr>

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.reasonForUpload"/></b></td>
							<td class="col-sm-6"><form:input path="reason"
									value="${model.uploadedFileForm.reason}" readonly="true" /></td>
						</tr>
                         

                   	<tr>
							<td class="col-sm-6"><b><spring:message code="label.status"/></b></td>
							<td class="col-sm-6"><form:input path="status"
									value="${model.uploadedFileForm.status}" readonly="true" /></td>
								<form:hidden path="transactionId"  value=""/>	
								
						</tr>
						
	                     <tr>
							<td class="col-sm-6"><b><spring:message code="label.comment"/></b></td>
							<td class="col-sm-6"><form:input path="comment"
									value="${model.uploadedFileForm.comment}" readonly="true" /></td>
						</tr>


					</table>

					<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="uploadDocument" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>

				</form:form>
			</div>