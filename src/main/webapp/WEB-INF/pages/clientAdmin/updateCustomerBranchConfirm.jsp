<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
				<div class="col-sm-12 col-md-12 header_customer">
					<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
				</div>
				<form:form action="updateCustomerBranchConfirm"
					commandName="customerBranchForm" onsubmit="return validateForm()">
					
			<div class="col-sm-12 col-md-12 header_customer">
				<h3><spring:message code="label.editDetails"/></h3>
			</div>
			<div class="col-sm-12 col-md-12">
								<table align="center">
								<tr>
										<td class="col-sm-6"><b><spring:message code="label.id"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input  path="id" value="${model.customerBranchForm.id}" readonly="true" placeholder="Enter name" ></form:input>
																								
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerName"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="name" readonly="true" value="${model.customerBranchForm.name}" placeholder="Enter name" ></form:input>
																								
									</tr>
								
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.gender"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="gender" readonly="true" value="${model.customerBranchForm.gender}" placeholder="Enter gender" id="contactNum"></form:input>
											
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.contactNumber"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="contactNum" readonly="true" value="${model.customerBranchForm.contactNum}" placeholder="Enter contact number" id="contactNum"></form:input>
											
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.altContactNo"/></b></td>
										<td class="col-sm-6"><form:input path="altContactNum" readonly="true" value="${model.customerBranchForm.altContactNum}"  placeholder="Enter alternative contact number" id="altContactNum"></form:input>
							
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.email"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="email" readonly="true" value="${model.customerBranchForm.email}" placeholder="Enter email id" id="email"></form:input>
																									
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.altEmail"/></b></td>
										<td class="col-sm-6"><form:input path="altEmail" readonly="true" value="${model.customerBranchForm.altEmail}" placeholder="Enter alternative email id" id="txtAlternateEmail"></form:input>
										</td>
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerPrefix"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input readonly="true" path="customerPrefix" value="${model.customerBranchForm.customerPrefix}" placeholder="Enter customer prefix" id="cusmprefix"></form:input>
									
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.manager"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input readonly="true" path="manager" placeholder="Enter manager name" id="manager"></form:input>
																									
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.managerEmail"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input readonly="true" path="managerEmail" placeholder="Enter manager email" id="managerEmail"></form:input>
																									
									</tr>
									
									
									<tr>	
											<td class="col-sm-6"><b><spring:message code="label.address"/></b><span style="color:red">*</span></td>
											<td class="col-sm-6"><form:input readonly="true" path="address" value="${model.customerBranchForm.address}" placeholder="Enter address" id="address"></form:input>
											 													
									</tr>	
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.pincode"/></b><span style="color:red">*</span></td>
											<td class="col-sm-6"><form:input readonly="true" path="pincode" value="${model.customerBranchForm.pincode}" placeholder="Enter pincode" id="pincode"></form:input>
																										
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.country"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="country" value="${model.customerBranchForm.country}" readonly="true" placeholder="Enter country" id="city"></form:input>
										  													
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.state"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="state" value="${model.customerBranchForm.state}" readonly="true" placeholder="Enter state" id="city"></form:input>
										  													
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.city"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input readonly="true" path="city" value="${model.customerBranchForm.city}"  placeholder="Enter city" id="city"></form:input>
										 													
									</tr>
									
						</table>
						
				
				
			</div>



								 <div class="col-sm-12 col-md-12 col-lg-12">
								<p align="center" style="padding-top: 10px;">

								<input type="submit" class="btn btn-primary" value="<spring:message code="label.save"/>">
								<a href="selectCustomerBranch" class="btn btn-success"><spring:message code="label.back"/></a>
								</p>
								</div>
</form:form>
</div>

