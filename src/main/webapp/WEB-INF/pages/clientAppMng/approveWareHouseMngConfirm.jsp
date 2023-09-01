<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<section>


<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
	</div>
<form:form action="approveWareHouseMngSave" name="approveCustomerHeadSave" commandName="wareHouseMngForm" onsubmit="return validateForm();">

			<div class="col-sm-12 col-md-12 col-lg-12">
								<table align="center">
								<form:hidden path="id"/>
								
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.customerName"/></b></td>
										<td class="col-sm-7"><form:input path="customerName" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.managerName"/></b></td>
										<td class="col-sm-7"><form:input path="mngName" placeholder="Enter name" readonly="true"></form:input>
																								
									</tr>
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.customerPrefix"/></b></td>
										<td class="col-sm-7"><form:input path="customerPrefix" readonly="true" placeholder="Enter contact number" id="customerPrefix"></form:input>
											
									</tr>
									
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.companyName"/></b></td>
										<td class="col-sm-7"><form:input path="companyName" readonly="true" placeholder="Enter Company Name" id="companyName"></form:input>
																									
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.address"/></b></td>
										<td class="col-sm-7"><form:input path="address" readonly="true" placeholder="Enter Company Name" id="companyName"></form:input>
										
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.status"/></b><span style="color:red">*</span></td>
										
										<td class="col-sm-7"><form:input path="status" readonly="true" id="status" placeholder="Enter Comment"></form:input></td>															
									</tr>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.comment"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:textarea path="comment" id="comment" placeholder="Enter Comment" style="height:120px;" readonly="true"></form:textarea>
										
										</td>
									</tr>
									<form:hidden path="transactionId"/>
										
									</table>
									<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="wareHouseMngApprovalList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
									</div>
								


</form:form>

</div>
