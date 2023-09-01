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
		document.customerBranchSave.transactionId.value = uuid; */
	}
</script>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">	
		<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
	</div>
<form:form action="customerBranchesSave" name="customerBranchSave" method="post" commandName="customerBranchForm" onsubmit="return validateForm();">
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">	
		 <div  class="successMsg"><b><font color="green">${success}</font></b></div>
	 </div>
	 				<div class="col-sm-12 col-md-12 col-lg-12 heading_color">
						<h3><spring:message code="label.customerHeadDetails"/></h3>
					</div>
					<div class="col-sm-12 col-md-12 col-lg-12">
								<table>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerHeadName"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="customerHeadName"  readonly="true" id="companyPrefix"></form:input></td>
										   												
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerHeadPrefix"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="customerPrefix"  readonly="true" placeholder="Enter Company Prefix" id="companyPrefix"></form:input></td>
										   												
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerHeadKey"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="customerHeadKey"  readonly="true" placeholder="Enter Company Prefix" id="companyPrefix"></form:input></td>
									</tr>
									
									<form:hidden path="transactionId" value="" />
								</table>
				</div>
		
			<div class="col-sm-12 col-md-12 col-lg-12 heading_color">
					<h3><spring:message code="label.addBranchDetails"/></h3>
			</div>
			<div class="col-sm-12 col-md-6 col-lg-6">
			<div class="col-sm-12 col-md-12 col-lg-12">
								<table class="theader">
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.name"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="name" readonly="true" placeholder="Enter name"></form:input></td>
																									
									</tr>
								
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.gender"/></b></td>
										<td class="col-sm-6"><form:input path="gender" readonly="true" placeholder="Enter name"></form:input></td>															
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.roleType"/></b></td>
										<td class="col-sm-6"><form:input path="roleType" readonly="true" placeholder="Enter name"></form:input></td>															
									</tr>
									
									<tr>
										<td class="col-sm-6"><b>C<spring:message code="label.contactNumber"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="contactNum"  readonly="true" placeholder="Enter contact number" id="contactno"></form:input></td>
										
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.altContactNo"/></b></td>
										<td class="col-sm-6"><form:input path="altContactNum"  readonly="true" placeholder="Enter alternative contact number" id="txtAlternateContactNumber"></form:input>
										</td>
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.email"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="email"  readonly="true" placeholder="Enter email id" id="email"></form:input></td>
																					
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.altEmail"/></b></td>
										<td class="col-sm-6"><form:input path="altEmail"  readonly="true" placeholder="Enter alternative email id" id="txtAlternateEmail"></form:input>
										</td>
									</tr>
								
						
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerBranchPrefix"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="customerBranchPrefix"  readonly="true" placeholder="Enter customer prefix" id="cusmprefix"></form:input></td>
									
									</tr>
							</table>
						</div>
					</div>
						<div class="col-sm-12 col-md-6 col-lg-6">
						<div class="col-sm-12 col-md-12 col-lg-12">
								<table>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.manager"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="manager" readonly="true" placeholder="Enter manager name" id="manager"></form:input></td>
																									
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.managerEmail"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="managerEmail" readonly="true" placeholder="Enter manager email" id="managerEmail"></form:input></td>
																								
									</tr>
									
									<tr>	
											<td class="col-sm-6"><b><spring:message code="label.address"/></b><span style="color:red">*</span></td>
											<td class="col-sm-6"><form:input path="address"  readonly="true" placeholder="Enter address" id="address"></form:input></td>
																								
									</tr>	
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.pincode"/></b><span style="color:red">*</span></td>
											<td class="col-sm-6"><form:input path="pincode"  readonly="true" placeholder="Enter pincode" id="pincode"></form:input></td>
																										
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.country"/></b><span style="color:red">*</span></td>
											<td class="col-sm-6"><form:input path="country"  readonly="true" placeholder="Enter country" id="country"></form:input>
											</td>
									</tr>
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.state"/></b><span style="color:red">*</span></td>
											<td class="col-sm-6"><form:input path="state"  readonly="true" placeholder="Enter state" id="state"></form:input></td>
																										
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.city"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="city"  readonly="true" placeholder="Enter city" id="city"></form:input></td>
									</tr>
									
									<tr>

									<fmt:formatDate value="${customerBranchForm.accExpiryDate}" var="formatDate" pattern="dd/MM/yyyy"/>
										<td class="col-sm-6"><b>Acc Expiry Date</b><span style="color:red">*</span></td>										
										<td id="e1" class="col-sm-6"><form:input path="accExpiryDate" class="form-control" value="${formatDate}" style="width: 206px;" readonly="true" id="datepicker"/>
									   </td>
									
									</tr>
										
						</table>
						
				</div>
				</div>
			
			 <div class="col-sm-12 col-md-12 col-lg-12">

								<p align="center" style="padding-top: 10px;">
								<input type="submit" class="btn btn-primary" value="<spring:message code="label.save"/>">

								<a href="createCustomerBranch" class="btn btn-success"><spring:message code="label.back"/></a>
								</p>
		</div>

		
		</form:form>

</div>


