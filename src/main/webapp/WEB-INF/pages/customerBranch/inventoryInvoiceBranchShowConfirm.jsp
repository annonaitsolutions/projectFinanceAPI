<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3><spring:message code="label.confirmScreen"/></h3>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div  class="successMsg" style="text-align:center;color:green;font-size: 18px;">${success}</div>
			</div>	

			<form:form action="updateinventoryInvoiceBranchShowConfirm" method="post"
				commandName="inventoryForm" name="inventory"
				onsubmit="return validateForm()">
				
				
				<div class="col-sm-12 col-md-12">
					<table align="center">
	

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.customerName"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="customerName" id="customerName"
									readonly="true" /></td>
						</tr>
							<tr>
							<td class="col-sm-6"><b><spring:message code="label.customerHeadName"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="customerHeadName" id="customerName"
									readonly="true" /></td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.buyerName"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="buyerName" id="buyerName"
									readonly="true" /></td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.masterKey"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="masterKey" id="masterKey"
									readonly="true" /></td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.poKey"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="poKey" id="poKey" readonly="true" /></td>
						</tr>
						<tr>	
					<td class="col-sm-6"><spring:message code="label.goods"/></td>
					<td class="col-sm-6"><form:input path="goods" readonly="true"/></td>
				</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.amount"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="amount" id="amount" readonly="true" />
							</td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.whName"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="wareHouseName" id="returned"
									readonly="true" /></td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.totalQuantity"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="quantity" id="quantity"
									readonly="true" /></td>
						</tr>

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.usedQuantity"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="usedQuantity" id="usedQuantity"
									readonly="true" /></td>
						</tr>

						<tr>
							<td class="col-sm-6"><b><spring:message code="label.damagedQuantity"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="damaged" id="damaged" readonly="true" />
							</td>
						</tr>


						<tr>
							<td class="col-sm-6"><b><spring:message code="label.quantityRemaining"/></b><span
								style="color: red">*</span></td>
							<td class="col-sm-6"><form:input path="total" id="total" readonly="true" /></td>
						</tr>

					</table>

				</div>

				<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="inventoryInvoiceBranchList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
				
				
			</form:form>
			</div>