<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
<form:form action="tUpdateClientAppMng" method="post" commandName="clientAppMngForm">
		<div class="col-sm-12 col-md-12 col-lg-12">
			 <div  class="successMsg"><b><font color="green">${success}</font></b></div>
		</div>
		
			<div class="col-sm-12 col-md-6 col-lg-6">
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3><spring:message code="label.personalDetails"/></h3>
				</div>
								<table class="theader">
								<tr>
										<td class="col-sm-6"><b><spring:message code="label.companyName"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="companyName"  readonly="true" id="companyPrefix"></form:input>
										   												
									</tr>
								<tr>
										<td class="col-sm-6"><b><spring:message code="label.id"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="id" readonly="true" placeholder="Enter name"></form:input></td>
																									
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerName"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="name" readonly="true" placeholder="Enter name"></form:input></td>
																									
									</tr>
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.gender"/></b></td>
											
											<td class="col-sm-6"><form:input path="gender" readonly="true" placeholder="Enter name"></form:input>
											</td>															
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.contactNumber"/></b><span style="color:red">*</span></td>
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
										<td class="col-sm-6"><b><spring:message code="label.customerPrefix"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="customerPrefix"  readonly="true" placeholder="Enter customer prefix" id="cusmprefix"></form:input>
									
									</tr>
							
										
						</table>
						
				
				
			</div>
		   			 <div class="item">
			
			<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-6"><input type="submit" value="<spring:message code="label.update"/>" class="btn btn-primary"></td>
							<td><a href="tCreateClientAppMng" class="btn btn-success"><spring:message code="label.back"/></a></td>
							
					
						</tr>
					</table>
		        </div>
		</div>
		
		
		</form:form>

</div>
