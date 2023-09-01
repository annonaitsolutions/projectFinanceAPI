<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
		document.banksupplierUpdate.transactionId.value = uuid;
	
	
	var d1 = new Date().getTime();
	var uuid1 = 'xxxxxx'.replace(/[xy]/g, function(c1) {
		var r1 = (d1 + Math.random() * 16) % 16 | 0;
		d1 = Math.floor(d / 16);
		return (c1 == 'x' ? r1 : (r1 & 0x3 | 0x8)).toString(16);
	});
	document.banksupplierUpdate.uniqueKey.value = uuid1; */
	}
</script>

	<div class="container">
		<div class="row">

			<div class="row-fluid apps">
				<h3 align="center"><spring:message code="label.supplierApprovalDetails"/></h3>
				<form:form name="banksupplierUpdate" action="updatesupplierPageShowConfrim" method="post"
					commandName="supplierForm" onsubmit="return validateForm();">
					<table align="center">
					
					<tr>
					<td class="heading_text"><b><spring:message code="label.id"/></b><span style="color:red">*</span></td>
					<td>
					<form:input path="id" id="id" 	value="${model.supplierForm.id}" readonly="true"/>
					</td>
				</tr>
					 <tr>
							<td class="heading_text"><b><spring:message code="label.customer"/> :</b></td>
							<td><form:input path="name" id="name"
									value="${model.supplierForm.name}" readonly="true" />
						</tr> 
 
					<form:hidden path="bName" value="${model.supplierForm.bName}"/>
						<tr>
							<td><b><spring:message code="label.email"/> :</b></td>
							<td><form:input path="email"
									value="${model.supplierForm.email}" readonly="true" /></td>

						</tr>

						<tr>
							<td><b><spring:message code="label.contactNumber"/>:</b></td>
							<td><form:input path="contactNum"
									value="${model.supplierForm.contactNum}" readonly="true" /></td>
						</tr>
                        <tr>
							<td><b><spring:message code="label.supplierCompanyName"/>:</b></td>
							<td><form:input path="companyName"
									value="${model.supplierForm.companyName}" readonly="true" /></td>
						</tr>  
						
						<tr>
							<td><b><spring:message code="label.supplierName"/>:</b></td>
							<td><form:input path="supplierName"
									value="${model.supplierForm.supplierName}" readonly="true" /></td>
						</tr>    

                   	<tr>
							<td><b><spring:message code="label.status"/>:</b></td>
							<td><form:input path="cStatus"
									value="${model.supplierForm.cStatus}" readonly="true" /></td>
								<form:hidden path="transactionId"  value=""/>	
								<form:hidden path="uniqueKey" value="" />	
								
						</tr>
						
	                     <tr>
							<td><b><spring:message code="label.comment"/>:</b></td>
							<td><form:input path="comment"
									value="${model.supplierForm.comment}" readonly="true" /></td>
						</tr>


					</table>

					<div class="col-sm-11">
						<table align="center">
							<tr>
								<td class="col-sm-8"><input type="submit" size="3"
									value="Continue" class="btn btn-primary"></td>
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
