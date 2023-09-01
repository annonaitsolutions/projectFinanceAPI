<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

 <script>
 $(function() {
	  	$( "#datepicker" ).datepicker({format: 'dd/mm/yyyy'});
	  	$( "#datepicker1" ).datepicker({format: 'dd/mm/yyyy'});
	  });

   var today = new Date();
   today.setHours(0,0,0,0);
				function val(){
					
					
					var userName  = document.getElementById('goods');
					var date1  = $('#datepicker').datepicker("getDate");
					var date2  = $('#datepicker1').datepicker("getDate");	
					
					var canSubmit = true; 
					
					if (document.getElementById('goods').value == ''){
						document.getElementById('goodsError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('goodsError').style.display='none';
					}
					if (document.getElementById('datepicker').value == '') {
						document.getElementById('date1Error').style.display = 'block';
						canSubmit = false;
					} else {
						document.getElementById('date1Error').style.display = 'none';
					}
					if (document.getElementById('datepicker1').value == '') {
						document.getElementById('dateError').style.display = 'block';
						canSubmit = false;
					} else {
						document.getElementById('dateError').style.display = 'none';
					}
					
					if(date1> date2) {
						document.getElementById('date3Error').style.display = 'block';
						canSubmit = false;
					} else {
						document.getElementById('date3Error').style.display = 'none';
					}
					
					if(date2 > today) {						
						document.getElementById('date2Error').style.display = 'block';
						canSubmit = false;
					} else {
						document.getElementById('date2Error').style.display = 'none';
					}

					if(canSubmit == false){
						return false;
					}
				}
			
				
		</script>
<div class="col-sm-9 col-md-9 body_fixed">	
	<div class="col-sm-12 col-md-12 header_customer">
	<div class="heading"><h3><spring:message code="label.reports"/></h3></div>
	</div>
<form:form  action="reportsBranchBuyingPost" name="myForm" commandName="invoiceForm" onsubmit="return val();">

		<div class="col-sm-12 col-md-12">	
					<div class="col-sm-12 col-md-12">
						<table align="center">
							<tr>
											<td class="col-sm-6"><b><spring:message code="label.goodsCategory"/>:</b><span style="color:red">*</span></td>
											
											<td class="col-sm-6"><form:select path="goodsCategory" id="goods">
											    <form:option value=""><spring:message code="label.selectValue"/></form:option> 
								  <form:option value="Antique">Antique</form:option>
                                 <form:option value="Automobiles">Automobiles</form:option>
                              	 <form:option value="Books">Books</form:option>
                         		 <form:option value="Clothing & Accessories">Clothing & Accessories</form:option>
                            	 <form:option value="Computers & Accessories">Computers & Accessories </form:option>
                            	 <form:option value="Electronics">Electronics</form:option>
                            	 <form:option value="Health Care">Health Care</form:option>
                            	 <form:option value="Jewellery">Jewellery</form:option>
                            	 <form:option value="Musical Instruments">Musical Instruments</form:option>
                            	 <form:option value="Metals">Metals</form:option>
                            	 <form:option value="Machinery">Machinery</form:option>
                            	 <form:option value="Software">Software</form:option>
                            	 <form:option value="Scientific Instruments">Scientific Instruments</form:option>
									</form:select>
								<td id="goodsError" class="error" style="display:none;"><font color="red"><spring:message code="label.plzSelectValue"/></font></td>
								<td id="goodsError" class="error" style="display:none"><spring:message code="label.plzSelectValue"/></td>
							</tr>
						 <tr>
							<td id="e1" class="col-sm-6">
              					         					 
						     <label><b><spring:message code="label.from"/></b></label>
                    		<form:input id="datepicker" path="fromDate" class="form-control" placeholder="Select date" style="width: 206px;" readonly="true"/><i class="fa fa-calendar"></i>
							<div id="date1Error" style="display: none; color: red;"><spring:message code="label.validation"/></div>
										<div id="date1Error" style="display: none; color: red;"><spring:message code="label.validation"/></div>
										<div id="date2Error" style="display: none; color: red;">To Date cannot be greater than today's date</div>
										<div id="date3Error" style="display: none; color: red;"><spring:message code="label.dateConfirmation"/></div>
								</td>
				
					<td id="e1" class="col-sm-6">
              					         					 
					  <label><b><spring:message code="label.to"/></b></label>      
                    <form:input id="datepicker1" path="toDate" class="form-control" placeholder="Select date" style="width: 206px;" readonly="true"/><i class="fa fa-calendar"></i>
					<div id="dateError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
										<div id="dateError" style="display: none; color: red;"><spring:message code="label.validation"/></div> 
								</td>
							</tr>		    
					</table>
				</div>
						
				<form:hidden path="id"  />
										
				<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.view"/>" class="btn btn-primary"></td>
							
						</tr>
					</table>
		        </div>
									
	</div>

</form:form>
</div>
<style>
.link p a{
color: tomato;
}
</style>

