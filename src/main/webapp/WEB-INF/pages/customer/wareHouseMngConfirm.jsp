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
		document.customerHeadSave.transactionId.value = uuid; */
	}
</script>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
<form:form action="wareHouseMngSave" name="customerHeadSave" method="post" commandName="wareHouseMngForm" onsubmit="return validateForm();">
<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
			</div>
	<div class="col-sm-12 col-md-12 col-lg-12">
	 <div  class="successMsg"><b><font color="green">${success}</font></b></div>
	</div>			
			<div class="col-sm-12 col-md-12 col-lg-12">
			<div class="col-sm-6 col-md-6 col-lg-6">
								<table class="theader">
								<caption><spring:message code="label.personalDetails"/></caption>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.managerName"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="mngName" readonly="true" placeholder="Enter name"></form:input>
																									
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
										<td class="col-sm-5"><b><spring:message code="label.altContactNo"/></b></td>
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
									<fmt:formatDate value="${wareHouseMngForm.accExpiryDate}" var="formatDate" pattern="dd/mm/yyyy"/>
										<td class="col-sm-5"><b><spring:message code="label.accountExpiryDate"/></b><span style="color:red">*</span></td>										
										<td id="e1" class="col-sm-6"><form:input path="accExpiryDate" class="form-control" value="${formatDate}" style="width: 206px;" readonly="true" id="datepicker"/>
									   </td>
									
									</tr>
									
										
						</table>
						
			</div>	
				
			<div class="col-sm-6 col-md-6 col-lg-6">
						
							<table class="theader broad">
									<caption><spring:message code="label.companyDetails"/></caption>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.companyName"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="companyName"  readonly="true" id="companyPrefix"></form:input>
										 												
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.companyPrefix"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="companyPrefix"  readonly="true" placeholder="Enter Company Prefix" id="companyPrefix"></form:input>
										   												
									</tr>
									<tr>	
											<td class="col-sm-5"><b><spring:message code="label.address"/></b><span style="color:red">*</span></td>
											<td class="col-sm-7"><form:input path="address"  readonly="true" placeholder="Enter address" id="address"></form:input>
																								
									</tr>	
									<tr>
											<td class="col-sm-5"><b><spring:message code="label.pincode"/></b><span style="color:red">*</span></td>
											<td class="col-sm-7"><form:input path="pincode"  readonly="true" placeholder="Enter pincode" id="pincode"></form:input>
																										
									<tr>
											<td class="col-sm-5"><b><spring:message code="label.country"/></b><span style="color:red">*</span></td>
											
											<td class="col-sm-7"><form:input path="country"  readonly="true" placeholder="Enter country" id="country"></form:input>
											</td>
									</tr>
									<tr>
											<td class="col-sm-5"><b><spring:message code="label.state"/></b><span style="color:red">*</span></td>
											<td class="col-sm-7"><form:input path="state"  readonly="true" placeholder="Enter state" id="state"></form:input>
										
											</td>																
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.city"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="city"  readonly="true" placeholder="Enter city" id="city"></form:input>
										   													
									</tr>
									<form:hidden path="transactionId" value="" />
								</table>
							</div>
							<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="createWareHouseMng" class="btn btn-success"><spring:message code="label.back"/></a></td>
							
					
						</tr>
					</table>
		        </div>
								
		</div>
		</form:form>

</div>