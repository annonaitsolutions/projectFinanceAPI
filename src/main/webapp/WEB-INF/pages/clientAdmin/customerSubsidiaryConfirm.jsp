<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

	<script type="text/javascript">
	function validateForm() {
		/* var d = new Date().getTime();
		var uuid = 'xxxxxx'.replace(/[xy]/g, function(c) {
			var r = (d + Math.random() * 16) % 16 | 0;
			d = Math.floor(d / 16);
			return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
		});
		document.customerSubsidiarySave.transactionId.value = uuid; */
	}
</script>
<section>	
<form:form action="customerSubsidiarySave" name="customerSubsidiarySave" method="post" commandName="customerSubsidiaryForm" onsubmit="return validateForm();">
	<div class="container">
	 <div  class="successMsg"><b><font color="green">${success}</font></b></div>
		<div class="row">
		
		 <div class="item">
			<div class="heading"><spring:message code="label.confirmationScreen"/></div>
			<div class="content">
						
							<table class="theader">
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.customerHeadName"/></b><span style="color:red">*</span></div>
										<td class="col-sm-8"><form:input path="customerHeadName"  readonly="true" id="companyPrefix" style="width:85%"></form:input>
										   												
									</tr>
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.customerHeadPrefix"/></b><span style="color:red">*</span></div>
										<td class="col-sm-8"><form:input path="customerPrefix"  readonly="true" placeholder="Enter Company Prefix" id="companyPrefix" style="width:85%"></form:input>
										   												
									</tr>
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.customerHeadKey"/></b><span style="color:red">*</span></div>
										<td class="col-sm-8"><form:input path="customerHeadKey"  readonly="true" placeholder="Enter Company Prefix" id="companyPrefix" style="width:85%"></form:input>
										   												
									</tr>
									
									<form:hidden path="transactionId" value="" />
								</table>
				
		<div class="item">
			<div class="heading"><spring:message code="label.subsidiaryDetails"/></div>
			<div class="content">
								<table class="theader">
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.name"/></b><span style="color:red">*</span></td>
										<td class="col-sm-8"><form:input path="name" readonly="true" placeholder="Enter name" style="width:85%" ></form:input>
																									
									</tr>
									<tr>
											<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.gender"/></b></td>
											
											<td class="col-sm-8"><form:input path="gender" readonly="true" placeholder="Enter name" style="width:85%" ></form:input>
											</td>															
									</tr>
									
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.contactNumber"/></b><span style="color:red">*</span></td>
										<td class="col-sm-8"><form:input path="contactNum"  readonly="true" placeholder="Enter contact number" id="contactno" style="width:85%"></form:input>
										
									</tr>
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.altContactNo"/></b></td>
										<td class="col-sm-8"><form:input path="altContactNum"  readonly="true" placeholder="Enter alternative contact number" id="txtAlternateContactNumber" style="width:85%"></form:input>
										</td>
									</tr>
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.email"/></b><span style="color:red">*</span></td>
										<td class="col-sm-8"><form:input path="email"  readonly="true" placeholder="Enter email id" id="email" style="width:85%"></form:input>
																					
									</tr>
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.altEmail"/></b></td>
										<td class="col-sm-8"><form:input path="altEmail"  readonly="true" placeholder="Enter alternative email id" id="txtAlternateEmail" style="width:85%"></form:input>
										</td>
									</tr>
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.customerSubsidiaryPrefix"/></b><span style="color:red">*</span></td>
										<td class="col-sm-8"><form:input path="companyHeadPrefix"  readonly="true" placeholder="Enter customer prefix" id="cusmprefix" style="width:85%"></form:input>
									
									</tr>
									
									<tr>	
											<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.address"/></b><span style="color:red">*</span></td>
											<td class="col-sm-8"><form:input path="address"  readonly="true" placeholder="Enter address" id="address" style="width:85%"></form:input>
																								
									</tr>	
									<tr>
											<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.pincode"/></b><span style="color:red">*</span></td>
											<td class="col-sm-8"><form:input path="pincode"  readonly="true" placeholder="Enter pincode" id="pincode" style="width:85%"></form:input>
																										
									<tr>
											<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.country"/></b><span style="color:red">*</span></td>
											<td class="col-sm-8">
											<td class="col-sm-8"><form:input path="country"  readonly="true" placeholder="Enter country" id="country" style="width:85%"></form:input>
											</td>
									</tr>
									<tr>
											<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.state"/></b><span style="color:red">*</span></td>
											<td class="col-sm-8"><form:input path="state"  readonly="true" placeholder="Enter state" id="state" style="width:85%"></form:input>
											
										
											</td>																
									</tr>
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.city"/></b><span style="color:red">*</span></div>
										<td class="col-sm-8"><form:input path="city"  readonly="true" placeholder="Enter city" id="city" style="width:85%"></form:input>
										   													
									</tr>
									
									
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.manager"/></b><span style="color:red">*</span></td>
										<td class="col-sm-8"><form:input path="manager" readonly="true" placeholder="Enter manager name" id="manager" style="width:85%"></form:input>
																								
									</tr>
									<tr>
										<td class="col-sm-2" style="width:23.666667%;"><b><spring:message code="label.managerEmail"/></b><span style="color:red">*</span></td>
										<td class="col-sm-8"><form:input path="managerEmail" readonly="true" placeholder="Enter manager email" id="managerEmail" style="width:85%"></form:input>
																							
									</tr>
						</table>
						
				
				
			</div>
		  </div>
		   					
			</div>
		  </div>
		 
							<p align="center" style="padding-top: 10px;">
					<input type="submit" class="btn btn-primary" value="<spring:message code="label.confirm"/>">
					
					<a href="bankEmp" class="btn btn-success" name="back"  onclick="javascript:window.location='#';"/><spring:message code="label.back"/></a>
				</p>
		</div>
		</div>
		</div> 
		</form:form>

</section>

