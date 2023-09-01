<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<script type="text/javascript">
function radio_check()
{
if(document.regulation.regulation[1].checked){
	alert("Please Select Goods To Restrict");
document.getElementById("l1").disabled=true;
}else{
document.getElementById("l1").disabled=false;
}
if(document.regulation.regulation[0].checked){
	alert("Please Select Goods To Ban");
	document.getElementById("l2").disabled=true;
}
else{
	document.getElementById("l2").disabled=false;
}
}

$(function () {
    $('#l1').multiselect({
        includeSelectAllOption: true
    });
    $('#btnSelected').click(function () {
        var selected = $("#l1 option:selected");
        var message = "";
        selected.each(function () {
            message += $(this).text() + " " + $(this).val() + "\n";
        });
        alert(message);
    });
});
$(function () {
    $('#l2').multiselect({
        includeSelectAllOption: true
    });
    $('#btnSelected').click(function () {
        var selected = $("#l2 option:selected");
        var message = "";
        selected.each(function () {
            message += $(this).text() + " " + $(this).val() + "\n";
        });
        alert(message);
    });
});


function validateForm() {
	
	var countryName = document.getElementById('countryName');
	
	var canSubmit = true;


	if (document.getElementById('countryName').value == '-1'){
		document.getElementById('countryNameError').style.display='block';
		canSubmit = false;
	}
	else{
		document.getElementById('countryNameError').style.display='none';
	}
	
	if(canSubmit == false){
		return false;
	}
	
	
}

</script>

 <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
				<div class="col-sm-12 col-md-12 col-lg-12">
				<div  class="successMsg" style="text-align:center;color:green;font-size: 18px;">${success}</div>
    		 </div>
			 <div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			 	 <h3 align="center"><spring:message code="label.regulation"/></h3>
			 </div>
			 
					
			<form:form action="regulationUpdatePage" name="regulation" commandName="regulationsForm" onsubmit="return validateForm()">
					<table align="center">
					<tr>
				  <td class="col-sm-6"><b><spring:message code="label.country"/></b></td>
						<td class="col-sm-6"><form:select path="countryName" id="countryName" value=""/>
						<span id="countryNameError" style="display:none;color:red;"><spring:message code="label.countryValidation"/></span>
						<span id="countryNameError" style="display:none;color:red;"><spring:message code="label.countryValidation"/></span>
				    </td>
				    </tr>
					<tr>
					<%--  <td class="heading_text"><b>State</b><span style="color:red">*</span></td> 
					<td>
					<form:select path="state" id="state" value=""/> --%>
							<script>
		                   populateCountries("countryName", "state");
		                  </script> 
		                 <td>
						<span id="stateError" style="display:none;color:red;"><spring:message code="label.plzSelectValue"/></span>
						<span id="stateError" style="display:none;color:red;"><spring:message code="label.plzSelectValue"/></span>
						</td>
					</tr>	
							
							
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.regulationStatus"/></b></td>
							<td class="col-sm-6"><input type=radio name=regulation value='Banned'  onClick="radio_check()";><spring:message code="label.banned"/>
                                      <input type=radio name=regulation value='Restricted' checked="checked" onClick="radio_check()";><spring:message code="label.restricted"/><br />
                                    </td>
						</tr>
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.exportableGoods"/></b></td>
							<td class="col-sm-6">
								 <form:select path="exportedGoods" id="l1" multiple="multiple" disabled="true" >
								 <form:option value="Antique"><spring:message code="label.antique"/></form:option>
                                 <form:option value="Automobiles"><spring:message code="label.automobiles"/></form:option>
                              	 <form:option value="Books"><spring:message code="label.books"/></form:option>
                         		 <form:option value="Clothing & Accessories"><spring:message code="label.clothingAndAccessories"/></form:option>
                            	 <form:option value="Computers & Accessories"><spring:message code="label.computersAndAccessories"/></form:option>
                            	 <form:option value="Electronics"><spring:message code="label.electronics"/></form:option>
                            	 <form:option value="Health Care"><spring:message code="label.healthCare"/></form:option>
                            	 <form:option value="Jewellery"><spring:message code="label.jewellery"/></form:option>
                            	 <form:option value="Musical Instruments"><spring:message code="label.musicalInstruments"/></form:option>
                            	 <form:option value="Metals"><spring:message code="label.metals"/></form:option>
                            	 <form:option value="Machinery"><spring:message code="label.machinery"/></form:option>
                            	 <form:option value="Software"><spring:message code="label.software"/></form:option>
                            	 <form:option value="Scientific Instruments"><spring:message code="label.scientificInstruments"/></form:option>
                            	 </form:select>
							</td>																
						</tr>	
						<tr>
							<td class="col-sm-6"><b><spring:message code="label.noExportableGoods"/></b></td>
						    <td class="col-sm-6">
									<form:select path="bannedGoods" multiple="true" id="l2">
								 <form:option value="Antique"><spring:message code="label.antique"/></form:option>
                                 <form:option value="Automobiles"><spring:message code="label.automobiles"/></form:option>
                              	 <form:option value="Books"><spring:message code="label.books"/></form:option>
                         		 <form:option value="Clothing & Accessories"><spring:message code="label.clothingAndAccessories"/></form:option>
                            	 <form:option value="Computers & Accessories"><spring:message code="label.computersAndAccessories"/></form:option>
                            	 <form:option value="Electronics"><spring:message code="label.electronics"/></form:option>
                            	 <form:option value="Health Care"><spring:message code="label.healthCare"/></form:option>
                            	 <form:option value="Jewellery"><spring:message code="label.jewellery"/></form:option>
                            	 <form:option value="Musical Instruments"><spring:message code="label.musicalInstruments"/></form:option>
                            	 <form:option value="Metals"><spring:message code="label.metals"/></form:option>
                            	 <form:option value="Machinery"><spring:message code="label.machinery"/></form:option>
                            	 <form:option value="Software"><spring:message code="label.software"/></form:option>
                            	 <form:option value="Scientific Instruments"><spring:message code="label.scientificInstruments"/></form:option>
									 </form:select>
	                                <form:hidden path="transactionId" value=""/>	
							</td>
																						
						</tr> 
					</table>
				<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="adminPage" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
			</form:form>	
			
			 <div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			<h3 align="center"><spring:message code="label.regulationTable"/></h3>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
		
			</div>
           <table class="table data jqtable example" id="my-table">
                    <thead>
                <tr>
                    <th><spring:message code="label.id"/></th>
                    <th><spring:message code="label.transactionId"/></th>
                    <th><spring:message code="label.country"/></th>
                   	<th><spring:message code="label.regulationStatus"/></th>
					<th><spring:message code="label.restricted"/></th>
					<th><spring:message code="label.noExportableGoods"/></th>
					<th><spring:message code="label.action"/></th> 
                </tr>
                </thead>
                <tbody>
                 <c:if test="${! empty regulations}">
                 <c:forEach items="${regulations}" var="regulations"> 
                <tr>
                    <td><c:out value="${regulations.id}"></c:out> </td>
                    <td><c:out value="${regulations.transactionId}"></c:out> </td>
                    <td><c:out value="${regulations.countryName}"></c:out></td>
                     <td><c:out value="${regulations.regulation}"></c:out></td>
                     <td><c:out value="${regulations.bannedGoods}"></c:out></td>
				     <td><c:out value="${regulations.exportedGoods}"></c:out></td>
						 
					
					<td><a href="selectRegulation?id=${regulations.id}" class="btn btn-primary"><spring:message code="label.edit"/></a></td>
                </tr>
                </c:forEach>
                </c:if>
               
                </tbody>
            </table>
			
					
		</div>