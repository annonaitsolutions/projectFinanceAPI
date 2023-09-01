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
		document.approveCompanySave.transactionId.value = uuid; */
	
	
	/* var d1 = new Date().getTime();
	var uuid1 = 'xxxxxx'.replace(/[xy]/g, function(c1) {
		var r1 = (d1 + Math.random() * 16) % 16 | 0;
		d1 = Math.floor(d / 16);
		return (c1 == 'x' ? r1 : (r1 & 0x3 | 0x8)).toString(16);
	});
	document.approveCompanySave.customerHeadKey.value = uuid1; */
}
</script>	

<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
	</div>
<form:form action="approveCompanySave" name="approveCompanySave" commandName="companyForm" onsubmit="return validateForm();">

			<div class="col-sm-12 col-md-12 col-lg-12">
								<table align="center">
							<tr>
										<td class="col-sm-6"><b><spring:message code="label.companyName"/></b></td>
										<td class="col-sm-6"><form:input path="companyName"  readonly="true"></form:input>
																								
									</tr>
																		
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.companyPrefix"/></b></td>
										<td class="col-sm-6"><form:input path="companyPrefix" readonly="true"  id="companyPrefix"></form:input>
											
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.country"/></b></td>
										<td class="col-sm-6"><form:input path="country" readonly="true"  id="country"></form:input>
											
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.state"/></b></td>
										<td class="col-sm-6"><form:input path="state" readonly="true"  id="state"></form:input></td>
																									
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.city"/></b></td>
										<td class="col-sm-6"><form:input path="city" readonly="true"  id="city"></form:input></td>
									</tr>
							
										<tr>
										<td class="col-sm-6"><b><spring:message code="label.status"/></b><span style="color:red">*</span></td>
										
										<td class="col-sm-6"><form:input path="status" readonly="true" id="status" placeholder="Enter Comment"></form:input></td>															
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.comment"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:textarea path="comment" id="comment" placeholder="Enter Comment" style="height:120px;" readonly="true"></form:textarea>
										
										</td>
									</tr>

										<form:hidden path="transactionId"/>
										<form:hidden path="companyId"/>
									</table>
									<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="approveCompany" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
									</div>
								


</form:form>

</div>
