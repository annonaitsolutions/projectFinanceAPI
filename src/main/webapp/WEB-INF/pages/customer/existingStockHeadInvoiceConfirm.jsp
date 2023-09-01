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
		document.regulation.transactionId.value = uuid; */
	}
</script>
<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
<form:form action="existingStockHeadInvoiceSave" method="post" name="regulation" commandName="invoiceStockForm" onsubmit="return validateForm();">
	 <div  class="successMsg"><b><font color="green">${success}</font></b></div>
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
				</div>
		<div class="col-sm-12 col-md-12 col-lg-12 newcustomer_border">
		<div class="content col-sm-12 col-md-12 col-lg-12">
								<table align="center">
								<caption><spring:message code="label.details"/></caption>
								
										<form:hidden path="transactionId" value="" />
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customer"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="customerName" id="customerName" readonly="true" placeholder="Enter Name"></form:input>
																									
									</tr>
							<%-- 	<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerHeadName"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="customerHeadName" id="customerHeadName" readonly="true" placeholder="Enter Name"></form:input>
																									
									</tr> --%>
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.goods"/></b><span style="color:red">*</span></td>
											<td class="col-sm-6">
											<form:input path="goodsName" id="goodsName" readonly="true"></form:input>
											</tr>
									
									<tr>
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.whName"/></b><span style="color:red">*</span></td>
											<td class="col-sm-6">
											<form:input path="wareHouseName" id="wareHouseName" readonly="true"></form:input>
											</tr>
									
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.quantity"/></b><span style="color:red">*</span></td>
											<td class="col-sm-6"><form:input readonly="true" path="quantity" placeholder="Enter pincode" id="quantity"></form:input>
										
									<tr>
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.usedQuantity"/></b><span style="color:red">*</span></td>
											<td class="col-sm-6"><form:input path="usedQuantity" readonly="true" placeholder="Enter pincode" id="usedQuantity"></form:input>
																										
									
									<tr>
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.damagedQuantity"/></b><span style="color:red">*</span></td>
											<td class="col-sm-6"><form:input path="damaged" readonly="true" placeholder="Enter pincode" id="damaged"></form:input>
																											
									
									<tr>
									
					
            
					<tr>
					<td class="col-sm-6"><b><spring:message code="label.quantityRemaining"/></b></td>
					<td class="col-sm-6"><form:input path="overAllQuantity"  id="overAllQuantity" readonly="true"/></td>
				    </tr>	
									
						</table>
						
				
				
			</div>
		
		
		<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="existingStockHeadInvoice" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
		</div>
		</form:form>
	 
		