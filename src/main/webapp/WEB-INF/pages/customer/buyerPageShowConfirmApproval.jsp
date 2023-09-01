<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<section>
<script type="text/javascript">
	function validateForm() {
		/* var d = new Date().getTime();
		var uuid = 'xxxxxx'.replace(/[xy]/g, function(c) {
			var r = (d + Math.random() * 16) % 16 | 0;
			d = Math.floor(d / 16);
			return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
		});
		document.bankbuyerUpdate.transactionId.value = uuid; */
	
	
	/* var d1 = new Date().getTime();
	var uuid1 = 'xxxxxx'.replace(/[xy]/g, function(c1) {
		var r1 = (d1 + Math.random() * 16) % 16 | 0;
		d1 = Math.floor(d / 16);
		return (c1 == 'x' ? r1 : (r1 & 0x3 | 0x8)).toString(16);
	});
	document.bankbuyerUpdate.uniqueKey.value = uuid1; */
	}
</script>

	<div class="container">
		<div class="row">

			<div class="row-fluid apps">
				<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
				<form:form name="bankbuyerUpdate" action="updatebuyerPageShowConfrim" method="post"
					commandName="newBuyerForm" onsubmit="return validateForm();">
					<table align="center">
					
					<tr>
					<td class="heading_text"><b><spring:message code="label.id"/></b><span style="color:red">*</span></td>
					<td>
					<form:input path="id" id="id" 	value="${model.newBuyerForm.id}" readonly="true"/>
					</td>
				</tr>
					 <tr>
							<td class="heading_text"><b><spring:message code="label.customerName"/></b></td>
							<td><form:input path="name" id="name"
									value="${model.newBuyerForm.name}" readonly="true" />
						</tr> 
 
					<form:hidden path="bName" value="${model.newBuyerForm.bName}"/>
						<tr>
							<td><b><spring:message code="label.email"/></b></td>
							<td><form:input path="email"
									value="${model.newBuyerForm.email}" readonly="true" /></td>

						</tr>

						<tr>
							<td><b><spring:message code="label.contactNumber"/></b></td>
							<td><form:input path="contactNum"
									value="${model.newBuyerForm.contactNum}" readonly="true" /></td>
						</tr>
                           

                   	<tr>
							<td><b><spring:message code="label.status"/></b></td>
							<td><form:input path="cStatus"
									value="${model.newBuyerForm.cStatus}" readonly="true" /></td>
								<form:hidden path="transactionId"  value=""/>	
								<form:hidden path="uniqueKey" value="" />	
								
						</tr>
						
	                     <tr>
							<td><b><spring:message code="label.comment"/></b></td>
							<td><form:input path="comment"
									value="${model.newBuyerForm.comment}" readonly="true" /></td>
						</tr>


					</table>

					<div class="col-sm-11">
						<table align="center">
							<tr>
								<td class="col-sm-8"><input type="submit" size="3"
									value="<spring:message code="label.continue"/>" class="btn btn-primary"></td>
								<td><button type="button" name="Back"
										onclick="javascript:window.location='/annona/admin/bankDetails';"
										class="btn btn-success"><spring:message code="label.cancel"/></button></td>

							</tr>
						</table>
					</div>

				</form:form>
			</div>
		</div>
</section>
