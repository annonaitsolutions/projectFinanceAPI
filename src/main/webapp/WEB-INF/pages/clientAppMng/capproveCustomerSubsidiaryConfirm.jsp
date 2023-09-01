<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<section>
	<script type="text/javascript">
	function validateForm() {
		/* var d = new Date().getTime();
		var uuid = 'xxxxxx'.replace(/[xy]/g, function(c) {
			var r = (d + Math.random() * 16) % 16 | 0;
			d = Math.floor(d / 16);
			return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
		});
		document.approveCustomerSubsidiarySave.transactionId.value = uuid;
	
	
	var d1 = new Date().getTime();
	var uuid1 = 'xxxxxx'.replace(/[xy]/g, function(c1) {
		var r1 = (d1 + Math.random() * 16) % 16 | 0;
		d1 = Math.floor(d / 16);
		return (c1 == 'x' ? r1 : (r1 & 0x3 | 0x8)).toString(16);
	});
	document.approveCustomerSubsidiarySave.customerSubsidiaryKey.value = uuid1; */
}
</script>	
<form:form action="capproveCustomerSubsidiarySave" name="approveCustomerSubsidiarySave" commandName="customerSubsidiaryForm" onsubmit="return validateForm();">
<div class="heading"><spring:message code="label.custDetail"/></div>
			<div class="content">
								<table class="theader">
								<form:hidden path="id"  />
								<form:hidden path="customerHeadKey"  />
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.customerName"/>:</b></td>
										<td class="col-sm-8"><form:input path="name" placeholder="Enter name" readonly="true" style="width:85%" ></form:input>
																								
									</tr>
											<form:hidden path="contactNum"  />
									<form:hidden path="email"  />	
									
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.customerPrefix"/>:</b></td>
										<td class="col-sm-8"><form:input path="customerPrefix" readonly="true" placeholder="Enter contact number" id="customerPrefix" style="width:85%"></form:input>
											
									</tr>
									
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.customerHeadName"/>:</b></td>
										<td class="col-sm-8"><form:input path="customerHeadName" readonly="true" placeholder="Enter Company Name" id="companyName" style="width:85%"></form:input>
																									
									</tr>
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.address"/>:</b></td>
										<td class="col-sm-8"><form:input path="address" readonly="true" placeholder="Enter Company Name" id="companyName" style="width:85%"></form:input>
										
									<tr>
											<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.status"/>:</b><span style="color:red">*</span></td>
											
											<td class="col-sm-8"><form:input path="aStatus" readonly="true" placeholder="Enter Status" id="companyName" style="width:85%"></form:input>
											
										
											</td>															
									</tr>
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.comment"/>:</b><span style="color:red">*</span></td>
										<td class="col-sm-8"><form:input path="comment" readonly="true" id="comment" placeholder="Enter Comment"  style="width:85%"></form:input>
										
										</td>
									</tr>
									<form:hidden path="transactionId" value="" />
									<form:hidden path="customerSubsidiaryKey" value="" />
											<p align="center" style="padding-top: 10px;">
					<input type="submit" class="btn btn-primary" value="Save">
					
					<a href="bankEmp" class="btn btn-success" name="back"  onclick="javascript:window.location='#';"/>back</a>
				</p>
									</table>
									</div>
								


</form:form>

</section>

