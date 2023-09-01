<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

	<script type="text/javascript">
	function validateForm() {
		var d = new Date().getTime();
		var uuid = 'xxxxxx'.replace(/[xy]/g, function(c) {
			var r = (d + Math.random() * 16) % 16 | 0;
			d = Math.floor(d / 16);
			return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
		});
		document.customerHeadSave.transactionId.value = uuid;
	}
</script>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
<form:form action="tClientAppMngSave" name="customerHeadSave" method="post" commandName="clientAppMngForm" onsubmit="return validateForm();">
<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
			</div>
	<div class="col-sm-12 col-md-12 col-lg-12">
	 <div  class="successMsg"><b><font color="green">${success}</font></b></div>
	 <form:hidden path="transactionId" value="" />
	</div>			
			<div class="col-sm-12 col-md-12 col-lg-12">
			<div class="col-sm-6 col-md-6 col-lg-6">
								<table class="theader">
								<caption><spring:message code="label.personalDetails"/></caption>
								    <tr>
										<td class="col-sm-5"><b><spring:message code="label.companyName"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="companyName"  readonly="true" id="companyPrefix"></form:input>
										   												
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.customerName"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="name" readonly="true" placeholder="Enter name"></form:input>
																									
									</tr>
									<tr>
											<td class="col-sm-5"><b><spring:message code="label.gender"/></b></td>
											
											<td class="col-sm-7"><form:input path="gender" readonly="true" placeholder="Enter name"></form:input>
											</td>															
									</tr>
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.contactNumber"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="contactNum"  readonly="true" placeholder="Enter contact number" id="contactno"></form:input>
										
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.altcontactNumber"/></b></td>
										<td class="col-sm-7"><form:input path="altContactNum"  readonly="true" placeholder="Enter alternative contact number" id="txtAlternateContactNumber" ></form:input>
										</td>
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.email"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="email"  readonly="true" placeholder="Enter email id" id="email" ></form:input>
																					
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.altEmail"/></b></td>
										<td class="col-sm-7"><form:input path="altEmail"  readonly="true" placeholder="Enter alternative email id" id="txtAlternateEmail"></form:input>
										</td>
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.customerPrefix"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="customerPrefix"  readonly="true" placeholder="Enter customer prefix" id="cusmprefix"></form:input>
									
									</tr>
									<tr>
									<fmt:formatDate value="${clientAppMngForm.accExpiryDate}" var="formatDate" pattern="dd/MM/yyyy"/>
										<td class="col-sm-5"><b>Acc Expiry Date</b><span style="color:red">*</span></td>										
										<td id="e1" class="col-sm-6"><form:input path="accExpiryDate" class="form-control" value="${formatDate}" style="width: 206px;" readonly="true" id="datepicker"/>
									   </td>
									
									</tr>
										
						</table>
						
			</div>	
				
			
								<div class="col-sm-12 col-md-12">
							<p align="center" style="padding-top: 10px;">
							<input type="submit" class="btn btn-primary" value="<spring:message code="label.save"/>">
					
							<a href="tCreateClientAppMng" class="btn btn-success" ><spring:message code="label.back"/></a>
							</p>
							</div>
		</div>
		</form:form>

</div>