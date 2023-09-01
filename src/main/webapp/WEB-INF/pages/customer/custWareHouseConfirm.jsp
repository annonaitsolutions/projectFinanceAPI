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
<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
<form:form action="custWareHouseSave" method="post" name="customerHeadSave" commandName="wareHouseForm" onsubmit="return validateForm();">
	 <div  class="successMsg"><b><font color="green">${success}</font></b></div>
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
				</div>
		<div class="col-sm-12 col-md-12 col-lg-12 newcustomer_border">
			<div class="content col-sm-12 col-md-12 col-lg-12">
								<table align="center">
								<caption><spring:message code="label.wareHouseDetails"/></caption>
									<form:hidden path="transactionId" value="" />
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerName"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="customerName" readonly="true" id="customerName" placeholder="Enter Name"></form:input>
																									
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.whName"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="wareHouseName" readonly="true" id="wareHouseName" placeholder="Enter Name"></form:input>
																							
									</tr>
								<tr>
										<td class="col-sm-6"><b><spring:message code="label.personInCharge"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="personInCharge" readonly="true" id="personInCharge" placeholder="Enter Name"></form:input>
																									
									</tr>
										<tr>
										<td class="col-sm-6"><b><spring:message code="label.country"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="country" readonly="true" id="personInCharge" placeholder="Enter Name"></form:input>
																								
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.state"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="state" id="state" readonly="true" placeholder="Enter Name"></form:input>
																								
									</tr>
									
									
									<tr>	
											<td class="col-sm-6"><b><spring:message code="label.address"/></b><span style="color:red">*</span></td>
											<td class="col-sm-6"><form:input path="address" readonly="true" placeholder="Enter address" id="address"></form:input>
																								
									</tr>	
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.pincode"/></b><span style="color:red">*</span></td>
											<td class="col-sm-6"><form:input path="pinCode" readonly="true" placeholder="Enter pincode" id="pincode"></form:input>
											
									<tr>
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.capacity"/></b><span style="color:red">*</span></td>
											<td class="col-sm-6"><form:input path="capacity" readonly="true" placeholder="Enter pincode" id="capacity"></form:input>
											
									<tr>
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.size"/></b><span style="color:red">*</span></td>
											<td class="col-sm-6"><form:input path="size" readonly="true" placeholder="Enter pincode" id="size"></form:input>
											
									<tr>
									
									
									
						</table>
			</div>
		
		
		<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="createCustWareHouse" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
		</div>
		</form:form>
		
</div>
