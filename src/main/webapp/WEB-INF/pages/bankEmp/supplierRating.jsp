<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@include file="taglib_includes.jsp" %>

<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
<form:form action="supplierRatingConfirm" method="post" commandName="supplierForm" onsubmit="return val();">
	 <div  class="successMsg"><b><font color="green">${success}</font></b></div>
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 align="center"><spring:message code="label.rating"/></h3>
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
										<td class="col-sm-6"><form:input path="rating" placeholder="Enter Rating" id="rating"></form:input>
											<div id="ratingError" style="display: none; color: red;"><spring:message code="label.ratingValidation"/></div>
								      <div id="ratingError" style="display: none; color: red;"><spring:message code="label.ratingValidation"/></div></td>
										
									</tr>
									
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.limit"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="limit" placeholder="Enter Limit" id="limit"></form:input>
										<div id="limitError" style="display: none; color: red;"><spring:message code="label.limitValidation"/></div>
								      <div id="limitError" style="display: none; color: red;"><spring:message code="label.limitValidation"/></div></td>
										
									</tr>
									
						</table>

								
				</div>
		</div>
		
		<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" size="3" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
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

<script>
$(function() {
	  $( "#datepicker" ).datepicker();
	   });

	function val() {

		var limit = document.getElementById('limit');

		var number= /(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/;
		var number1= /^-?[0-9]+$/;
		var canSubmit = true;

		if(limit.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || limit.value.match(/^-?[0-9]+$/) &&!(document.getElementById('limit').value == ''))
	  	{
	  		
	  		document.getElementById('limitError').style.display='none';
	  	
	  	}
	  	else{
	  		document.getElementById('limitError').style.display='block';
	  	     canSubmit = false;
	  	}
		
	  
		if (document.getElementById('rating').value == '') {
			document.getElementById('ratingError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('ratingError').style.display = 'none';
		}
		
     if (canSubmit == false) {
			return false;
		}
		
		$(document).ready(function() {
			$(':input','#newBuyerForm')
			  .not(':button, :submit, :reset, :hidden')
			  .val('')  
		});
	}
</script>