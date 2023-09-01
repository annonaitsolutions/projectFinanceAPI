<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@include file="taglib_includes.jsp" %>

<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
<form:form action="supplierRatingPost" method="post" name="rating" commandName="supplierForm" onsubmit="return validateForm();">
	 <div  class="successMsg"><b><font color="green">${success}</font></b></div>
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 align="center"><spring:message code="label.confirmationScreen"/></h3>
				</div>
		<div class="col-sm-12 col-md-12 col-lg-12 newcustomer_border">
			<div class="content col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
										<td class="col-sm-6"><b><spring:message code="label.supplierName"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="supplierName" readonly="true" placeholder="Enter Name"></form:input>
																							
									</tr>
								<form:hidden path="id"/>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.rating"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="rating" readonly="true" placeholder="Enter Rating" id="contactno"></form:input>
										
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.limit"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="limit" readonly="true" placeholder="Enter Limit" id="Limit"></form:input>
										 	<form:hidden path="transactionId" value="" />
									</tr>
									
						</table>

								
				</div>
		</div>
		
		<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" size="3" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="supplierRatingList" class="btn btn-success"><spring:message code="label.back"/></a></td>
							
					
						</tr>
					</table>
		        </div>
		        </form:form>
		</div>
		
	 
		<script>
		$(document).ready(function() {
			$(':input','#clientAdminForm')
			  .not(':button, :submit, :reset, :hidden')
			  .val('')  
		});
		
			
		</script>


	<script type="text/javascript">
	function validateForm() {

	}
</script>