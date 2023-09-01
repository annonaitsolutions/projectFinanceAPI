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
		document.sellerbankUpdate.transactionId.value = uuid;
	
	
	var d1 = new Date().getTime();
	var uuid1 = 'xxxxxx'.replace(/[xy]/g, function(c1) {
		var r1 = (d1 + Math.random() * 16) % 16 | 0;
		d1 = Math.floor(d / 16);
		return (c1 == 'x' ? r1 : (r1 & 0x3 | 0x8)).toString(16);
	});
	document.sellerbankUpdate.uniquekey.value = uuid1; */
	}
</script>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3><spring:message code="label.confirmationScreen"/></h3>
				</div>
				<form:form name="sellerbankUpdate" action="updatesupplierPageShowConfrim" method="post"
					commandName="supplierForm" onsubmit="return validateForm();">
					<table align="center">
					
					<tr>
					<td class="col-sm-6"><b><spring:message code="label.id"/></b></td>
					<td class="col-sm-6">
					<form:input path="id" id="id" 	value="${model.supplierForm.id}" readonly="true"/>
					</td>
				</tr>
						 <tr>
							<td class="col-sm-6"><b><spring:message code="label.customerName"/></b></td>
							<td class="col-sm-6"><form:input path="name" id="name"
									value="${model.supplierForm.name}" readonly="true" />
						</tr> 
                         <tr>
							<td class="col-sm-6"><b><spring:message code="label.supplierName"/></b></td>
							<td class="col-sm-6"><form:input path="supplierName" id="supplierName"
									value="${model.supplierForm.supplierName}" readonly="true" />
						</tr> 
					
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.email"/></b></td>
							<td class="col-sm-6"><form:input path="email"
									value="${model.supplierForm.email}" readonly="true" /></td>

						</tr>

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.contactNumber"/></b></td>
							<td class="col-sm-6"><form:input path="contactNum"
									value="${model.supplierForm.contactNum}" readonly="true" /></td>
						</tr>


                   	<tr>
							<td class="col-sm-6"><b><spring:message code="label.status"/></b></td>
							<td class="col-sm-6"><form:input path="status"
									value="${model.supplierForm.status}" readonly="true" /></td>
								
						</tr>
	                     <tr>
							<td class="col-sm-6"><b><spring:message code="label.comment"/></b></td>
							<td class="col-sm-6"><form:textarea path="comment" id="comment" placeholder="Enter Comment" style="height:120px;" readonly="true"></form:textarea></td>
									<form:hidden path="transactionId"  value=""/>	
								   <form:hidden path="uniquekey"  value=""/>	
						</tr>


					</table>
<form:hidden path="accExpiryDate" />
					<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="supplierPage" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
					</div>
				</form:form>
		        </div>
			
