<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="col-sm-9 col-md-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3><spring:message code="label.confirmationScreen"/></h3>
	</div>
<form:form action="custWareHouseApprovalPost" commandName="wareHouseForm" >

			<div class="col-sm-12 col-md-12 col-lg-12">
								<table align="center">
								<form:hidden path="id"  />
								<form:hidden path="transactionId"  />
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerName"/>:</b></td>
										<td class="col-sm-6"><form:input path="customerName" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
									
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.whName"/>:</b></td>
										<td class="col-sm-6"><form:input path="wareHouseName" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
											<tr>
										<td class="col-sm-6"><b><spring:message code="label.status"/>:</b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="status" id="comment" placeholder="Enter Comment" readonly="true"></form:input>
										
										</td>
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.comment"/>:</b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="comment" id="comment" placeholder="Enter Comment" readonly="true"></form:input>
										
										</td>
									</tr>
										
									</table>
									<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="custWareHouseAppList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
									</div>
								


</form:form>

</div>

