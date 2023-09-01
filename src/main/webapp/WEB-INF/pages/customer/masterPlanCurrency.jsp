<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

<form:form action="masterPlanCurrency" commandName="masterPlanForm" onsubmit="return val();" accept-charset="character_set">
<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
<h3 align="center"><spring:message code="label.masterPlan"/></h3>
</div>

								<div class="col-sm-2"><b><spring:message code="label.typeOfCurr"/></b><span style="color:red">*</span></div>
										<form:hidden path="customerPrefix"/>	
								<div class="col-sm-10"><form:select path="currencySymbol" id="currencySymbol" style="width:86.9%">
											    <form:option value=""><spring:message code="label.selectValue"/></form:option> 
											    <form:option value="rs"><spring:message code="label.rupee"/></form:option>
												<form:option value="dollar"><spring:message code="label.dollar"/></form:option>
												<form:option value="euro"><spring:message code="label.euro"/></form:option>
												</form:select>
								</div>
								<center>
								<div id="currencyError" class="error col-sm-12" style="display:none;"><font color="red"><spring:message code="label.validation2"/></font></div>
								<div id="currencyError" class="error" style="display:none"><spring:message code="label.validation2"/></div>
								</center>			
							<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.continue"/>" class="btn btn-primary"></td>
							<td><a href="user" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
						
											</form:form>
		</div>									
												<script>
		
			
				 
				function val(){
					debugger;
					var userName  = document.getElementById('currencySymbol');

					var canSubmit = true; 
					
					if (document.getElementById('currencySymbol').value == ''){
						document.getElementById('currencyError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('currencyError').style.display='none';
					}
				

					if(canSubmit == false){
						return false;
					}
				}
			
				
		</script>
		<style>


.bankemp_footer.col-sm-12 {
    margin-top: 125px;
}
</style>					