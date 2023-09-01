<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12">
			<div class="col-sm-12 col-md-12 col-lg-12">
			<label><spring:message code="label.note"/> :</label>	 <p>   <font color="green">  <b><spring:message code="label.fileName"/> :</b> "<strong> ${message}</strong>"<spring:message code="label.upSucces"/> !</font> </p> 
			</div>	
				
				<table align="center" width="400">


				<tr>
						<td class="heading_text"><b><spring:message code="label.transactionId"/>:</b></td>
						<td>${model.tuploadedFileForm.transactionId}</td>
					</tr>
					<tr>
						<td><b><spring:message code="label.transactionType"/>:</b></td>
						<td><spring:message code="label.docUpload"/></td>
					</tr>

					<tr>
						<td><b><spring:message code="label.transactionStatus"/>:</b></td>
						<td><font color="green"><spring:message code="label.updateSuccessfully"/>.</font></td>
					</tr>
					<tr>
				</table>

			
			</div>
		</div>