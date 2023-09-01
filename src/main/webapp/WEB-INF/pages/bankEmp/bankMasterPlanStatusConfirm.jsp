<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

 <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

		 	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		 			<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
	 			<div  class="successMsg"><b><font color="green">${success}</font></b></div>
			</div>	
	 <form:form action="bankMasterPlanApproveStatusPost" name="bankMasterPlanApproveStatusPost" method="post" commandName="masterPlanForm" onsubmit="return validateForm();">
	
		
				<div class="col-sm-12 col-md-12 col-lg-12"> 
								<table align="center">
								<form:hidden path="transactionId" value="" />
								<form:hidden path="id" value="" />
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerName"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="customer" readonly="true" placeholder="Enter name"></form:input>
																									
									</tr>
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.masterKey"/></b></td>
											
											<td class="col-sm-6"><form:input path="masterKey" readonly="true" placeholder="Enter masterKey"></form:input>
											</td>															
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.status"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="status"  readonly="true" placeholder="Enter status" id="contactno"></form:input>
										
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.comment"/></b></td>
										<td class="col-sm-6"><form:input path="comment"  readonly="true" placeholder="Enter comment" id="txtAlternateContactNumber"></form:input>
										</td>
									</tr>		
						</table>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="bankMasterPlanPendingDetails" class="btn btn-success"><spring:message code="label.back"/></a></td>
							
					
						</tr>
					</table>
		        </div>
						</form:form>
</div>					