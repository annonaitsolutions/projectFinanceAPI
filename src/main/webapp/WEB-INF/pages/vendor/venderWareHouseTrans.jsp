<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12">
	


				<table align="center" width="400">


					<tr>
						<td class="heading_text"><b><spring:message code="label.customerHeadName"/></b></td>
						<td>${purchaseOrderForm.customerHeadName}</td>
					<tr>
					<td class="heading_text"><b><spring:message code="label.supplierName"/></b></td>
						<td>${purchaseOrderForm.supplierName}</td>
					</tr>
					<tr>
					<td class="heading_text"><b><spring:message code="label.amount"/></b></td>
						<td>${purchaseOrderForm.amount}</td>
					</tr>
					
					<tr>
					<td class="heading_text"><b><spring:message code="label.masterKey"/></b></td>
						<td>${purchaseOrderForm.masterKey}</td>
					</tr>
			
					<tr>
					<td class="heading_text"><b><spring:message code="label.goods"/></b></td>
						<td>${purchaseOrderForm.goods}</td>
					</tr>
					
					<tr>
					<td class="heading_text"><b><spring:message code="label.goodsQuantity"/></b></td>
						<td>${purchaseOrderForm.quantity}</td>
					</tr>
					
					<tr>
						<td><b><spring:message code="label.status"/></b></td>
						<td><font color="green"><spring:message code="label.sentFSupplier"/></font></td>
					</tr>
					

				</table>

			</div>
		</div>
