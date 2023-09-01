<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
<form:form action="updateExistingStockInvoice" method="post" name="interest" commandName="invoiceStockForm" onsubmit="return val();">
	 <div  class="successMsg"><b><font color="green">${success}</font></b></div>
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 align="center"><spring:message code="label.existingStock"/></h3>
				</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
			
								<table align="center">
								
								<form:hidden path="id"/>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customer"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="customerName" id="customerName" readonly="true" placeholder="Enter Name"></form:input>
																									
									</tr>
								<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerHeadName"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="customerHeadName" id="customerHeadName" readonly="true" placeholder="Enter Name"></form:input>
																									
									</tr>
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.goodsName"/></b><span style="color:red">*</span></td>
											<td class="col-sm-6">
											<form:input path="goodsName" id="goodsName" readonly="true"></form:input>
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
					<td class="col-sm-6"><spring:message code="label.quantityRemaining"/></td>
					<td class="col-sm-6"><form:input path="overAllQuantity"  id="overAllQuantity" readonly="true"/></td>
				    </tr>	
									
						</table>
						
				
				
			</div>
		
		
		<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.update"/>" class="btn btn-primary"></td>
							<td><a href="existingStockInvoice" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
		
		</form:form>
	 
		</div>