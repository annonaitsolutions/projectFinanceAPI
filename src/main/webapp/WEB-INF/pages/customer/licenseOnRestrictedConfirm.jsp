<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script>

function validateForm() {
	var d = new Date().getTime();
    var uuid = 'xxxxxx'.replace(/[xy]/g, function(c) {
        var r = (d + Math.random()*16)%16 | 0;
        d = Math.floor(d/16);
        return (c=='x' ? r : (r&0x3|0x8)).toString(16);
    });
    document.msplan.transId.value=uuid;
}
</script>
<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
<form:form action="licenseOnRestrictedPost" name="msplan" method="post" commandName="restrictedLicenseForm" onsubmit="return validateForm()">
	<div class="col-sm-12 col-md-12 col-lg-12 align="center">
		 <div  class="successMsg"><b><font color="green">${success}</font></b></div>
	</div>
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 align="center"><spring:message code="label.licenseDetails"/></h3>
				</div>
		<div class="col-sm-12 col-md-12 col-lg-12 newcustomer_border">
			<div class="content col-sm-12 col-md-12 col-lg-12">
								<table align="center">
									<form:hidden path="transId"  value=""/>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.licenseDetails"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="licenseDetails" id="licenseDetails" readonly="true"></form:input>
																									
									</tr>
								<tr>
										<td class="col-sm-6"><b><spring:message code="label.startDate"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="startDate" id="startDate" readonly="true"></form:input><i class="fa fa-calendar"></i>
									</tr>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.endDate"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="endDate" id="endDate" readonly="true"></form:input><i class="fa fa-calendar"></i>
									</tr>
									
									<tr>	
											<td class="col-sm-6"><b><spring:message code="label.goodsName"/></b><span style="color:red"></span></td>
											<td class="col-sm-6"><form:input path="goodsName" readonly="true" id="goodsName"></form:input>
									</tr>	
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.weight"/></b><span style="color:red"></span></td>
											<td class="col-sm-6"><form:input path="qty" readonly="true" id="qty"></form:input>
																											
									
									<tr>
						</table>
						
				
				
			</div>
		
		
		<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="licenseOnRestricted" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
		</div>
		</form:form>
		</div> 
		

