<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<script type="text/javascript">
function validateForm()
{

}
</script>

	<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

			 <div class="col-sm-12 col-md-12 col-lg-12 header_customer">

					<h3 align="center"><spring:message code="label.regBanned"/></h3>
				</div>
			<form:form name="regulation" action="bannedRegulationDetails" commandName="regulationsForm" onsubmit="return validateForm()" >
					<table align="center">
							<tr>
					  <td class="col-sm-6"><b><spring:message code="label.country"/></b></td>
					<td class="col-sm-6">
					<form:input path="countryName" id="countryName" value="${model.regulationsForm.countryName}" readonly="true"/>
					
				    </tr>
					
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.regulationStatus"/>:</b></td>
										<td class="col-sm-6">
										<form:input path="regulation" value="${model.regulationsForm.regulation}" readonly="true"/>
										</td>
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.exportableGoods"/>:</b></td>
										<td class="col-sm-6"> 
										<form:textarea path="exportedGoods" value="${model.regulationsForm.exportedGoods}" readonly="true"/>
											
										</td>	
										<form:hidden path="transactionId"  value=""/>															
									</tr>	
									
					</table>
					
					<div class="col-sm-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit"  value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="regulationPage" class="btn btn-success"><spring:message code="label.back"/></a></td>							
					
						</tr>
					</table>
		        </div>
				
			</form:form>			
	
</div>