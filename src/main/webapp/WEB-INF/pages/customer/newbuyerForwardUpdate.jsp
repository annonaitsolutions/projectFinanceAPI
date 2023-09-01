<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<section>	
	<div class="container">
		<div class="row">
		<div  class="successMsg" style="text-align:center;color:green;font-size: 18px;">${success}</div>
    	<div class="row-fluid apps">
					<h3 align="center"><spring:message code="label.buyerDetails"/></h3>
			<form:form name="" action="" commandName="newBuyerForm" onsubmit="return validateForm()" >
					<table align="center">
					
					<tr>
					  <td class="heading_text"><b><spring:message code="label.id"/></b></td>
					<td>
					<form:input path="id" id="id" value="${model.newBuyerForm.id}" readonly="true"/>
					
				    </tr>
					
					
				    
								<tr>
						<td class="heading_text"><b><spring:message code="label.contactNumber"/></b></td>
						<td><form:input path="contactNum" id="contactNum"
								 />
					</tr>



					<tr>
						<td class="heading_text"><b><spring:message code="label.address"/></b></td>
						<td><form:input path="address" id="address"  />
					</tr>
					<tr>
						<td><b><spring:message code="label.branch"/>:</b></td>
						<td><form:input path="branch" value="" id="l1" /></td>
	      <form:hidden path="transactionId"  value=""/>	
					</tr>
										
									
					</table>
					
					<div class="col-sm-11">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" size="3" value="Continue" class="btn btn-primary"></td>
							<td><button type="button" name="Back"  onclick="javascript:window.location='/annona/admin/selectRole?id=76';" class="btn btn-success">Cancel</button></td>
					
						</tr>
					</table>
		        </div>
				
			</form:form>			
		</div>
	</div>	  
</section>
