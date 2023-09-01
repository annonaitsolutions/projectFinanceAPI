<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
<form:form action="branchWareHouseConfirm" method="post" commandName="wareHouseForm" onsubmit="return val();">
	 <div  class="successMsg"><b><font color="green">${success}</font></b></div>
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 align="center"><spring:message code="label.wareHouse"/></h3>
				</div>
		<div class="col-sm-12 col-md-12 col-lg-12 newcustomer_border">
			<div class="content col-sm-12 col-md-12 col-lg-12">
								<table align="center">								
									<form:hidden path="customerName"/>
									<tr>
										<td class="col-sm-5"><b><spring:message code="label.whName"/></b><span style="color:red">*</span></td>
										<td class="col-sm-7"><form:input path="wareHouseName" id="wareHouseName" placeholder="Enter Name"></form:input>
										<span id="wareHouseNameError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
									    <span id="wareHouseNameError" class="error" style="display:none"><spring:message code="label.validation"/></span></td>
																									
									</tr>
										<tr>
						<td class="col-sm-5"><b><spring:message code="label.personInCharge"/><span style="color:red">&nbsp;*</span>&nbsp;:</b></td>
						<td class="col-sm-7"><form:select id="personInCharge" path="personInCharge"  style="width:205px;">
						<form:option value=""><spring:message code="label.selectValue"/></form:option>
								<form:options items="${wareHouseForm.whList}"
									itemValue="mngName" itemLabel="mngName" />
							</form:select>
							<span id="personInChargeError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></span>
									    <span id="personInChargeError" class="error" style="display:none"><spring:message code="label.validation"/></span></td>
										
							
					</tr>
									<tr>
											<td class="col-sm-5"><b><spring:message code="label.country"/></b><span style="color:red">*</span></td>
											<td class="col-sm-7">
											<form:select path="country" id="country" placeholder="Select country" style="width:205px;"></form:select>
											<span id="countryError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
											<span id="countryError" class="error" style="display:none"><spring:message code="label.validation"/></span></td> 
									</tr>
									<tr>
											<td class="col-sm-5"><b><spring:message code="label.state"/></b><span style="color:red">*</span></td>
											<td class="col-sm-7"><form:select path="state" placeholder="Select state" id ="state" style="width:205px;"></form:select>
											<script>
												 populateCountries("country", "state");
											</script>
											<span id="stateError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
											<span id="stateError" class="error" style="display:none"><spring:message code="label.validation"/></span></td> 																
									</tr>
									
									<tr>	
											<td class="col-sm-5"><b><spring:message code="label.address"/></b><span style="color:red">*</span></td>
											<td class="col-sm-7"><form:input path="address" placeholder="Enter address" id="address"></form:input>
											<span id="addressError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
											<span id="addressError" class="error" style="display:none"><spring:message code="label.validation"/></span></td> 
																									
									</tr>	
									<tr>
											<td class="col-sm-5"><b><spring:message code="label.pincode"/></b><span style="color:red">*</span></td>
											<td class="col-sm-7"><form:input path="pinCode" placeholder="Enter pincode" id="pincode"></form:input>
											<span id="pincodeError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
											<span id="pincodeError" class="error" style="display:none"><spring:message code="label.validation"/></span></td>
																											
									
									<tr>
									<tr>
											<td class="col-sm-5"><b><spring:message code="label.capacity"/></b><span style="color:red">*</span></td>
											<td class="col-sm-7"><form:input path="capacity" placeholder="Enter Capacity" id="capacity"></form:input>
											<span id="capacityError2" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
											<span id="capacityError2" class="error" style="display:none"><spring:message code="label.validation"/></span></td>
																											
									
									<tr>
									<tr>
											<td class="col-sm-5"><b><spring:message code="label.size"/></b><span style="color:red">*</span></td>
											<td class="col-sm-7"><form:input path="size" placeholder="Enter Size" id="size"></form:input>
											<span id="sizeError2" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
											<span id="sizeError2" class="error" style="display:none"><spring:message code="label.validation"/></span></td>
																											
									
									<tr>
									
									
									
						</table>
						
				
				
			</div>
		
		
		<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="userBranch" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
		</div>
		</form:form>
		<div class="col-sm-12 col-md-12 col-lg-12">
		
		<span class="counter pull-right"></span>
				<div class="col-sm-12 col-md-12 col-lg-12">
					<input type="text" id="kwd_search" value="" placeholder="Search Here..."  style="float:right;"/>
				</div>
			<table class="table data jqtable example" id="my-table">
				
				<thead>
					<tr>
						<th><spring:message code="label.id"/></th>
						<th><spring:message code="label.customerName"/></th>
						<th><spring:message code="label.whName"/></th>
						<th><spring:message code="label.personInCharge"/></th>
						<th><spring:message code="label.address"/></th>
						<th><spring:message code="label.status"/></th>
						<th><spring:message code="label.action"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${! empty whList}">
						<c:forEach items="${whList}" var="customer">
							<tr>
								<td><c:out value="${customer.id}"></c:out></td>
								<td><c:out value="${customer.customerName}"></c:out>
								<td><c:out value="${customer.wareHouseName}"></c:out>
								<td><c:out value="${customer.personInCharge}"></c:out></td>
								<td><c:out value="${customer.address}"></c:out></td>
								<td><c:out value="${customer.status}"></c:out></td>



								<td><a href="selectBranchWareHouse?id=${customer.id}"
									class="btn btn-primary"><spring:message code="label.edit" /></a></td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table> 
		</div>		
	</div>	 
		<script>
		 $(document).ready(function() {
			$(':input','#wareHouseForm')
			  .not(':button, :submit, :reset, :hidden')
			  .val('')  
		});
		
			$('.accordion .item .heading').click(function() {
				
				var a = $(this).closest('.item');
				var b = $(a).hasClass('open');
				var c = $(a).closest('.accordion').find('.open');
					
				if(b != true) {
					$(c).find('.content').slideUp(200);
					$(c).removeClass('open');
				}

				$(a).toggleClass('open');
				$(a).find('.content').slideToggle(200);

			}); 
			
	function val(){
				
				var categoryName = document.getElementById('name');
				var number= /(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/;
				var number1= /^-?[0-9]+$/;
				var size = document.getElementById('size');
				var canSubmit = true;


				if (document.getElementById('wareHouseName').value == ''){
					document.getElementById('wareHouseNameError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('wareHouseNameError').style.display='none';
				}
				if (document.getElementById('personInCharge').value == ''){
					document.getElementById('personInChargeError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('personInChargeError').style.display='none';
				}
				 if (document.getElementById('address').value == ''){
					document.getElementById('addressError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('addressError').style.display='none';
				}
				
				 if (document.getElementById('pincode').value == ''){
					document.getElementById('pincodeError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('pincodeError').style.display='none';
				}
				 if (document.getElementById('country').value == ''){
						document.getElementById('countryError').style.display='block';
						canSubmit = false;
					}
					else{
						document.getElementById('countryError').style.display='none';
					}
				 
				 if (document.getElementById('state').value == '-1'
					    || document.getElementById('state').value == '') {
					   document.getElementById('stateError').style.display = 'block';
					   canSubmit = false;
					  } else {
					   document.getElementById('stateError').style.display = 'none';
					  }
				if(capacity.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || capacity.value.match(/^-?[0-9]+$/) &&!(document.getElementById('capacity').value == ''))
				  	{
				  		
				  		document.getElementById('capacityError2').style.display='none';
				  	
				  	}
				  	else{
				  		document.getElementById('capacityError2').style.display='block';
				  	     canSubmit = false;
				  	}
					
					
					if(size.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || size.value.match(/^-?[0-9]+$/) &&!(document.getElementById('size').value == ''))
				  	{
				  		
				  		document.getElementById('sizeError2').style.display='none';
				  	
				  	}
				  	else{
				  		document.getElementById('sizeError2').style.display='block';
				  	     canSubmit = false;
				  	}
					
					if(canSubmit == false){
					return false;
				}
			}
			
			
			$(document).ready(function() {
				  $(".search").keyup(function () {
				    var searchTerm = $(".search").val();
				    var listItem = $('.results tbody').children('tr');
				    var searchSplit = searchTerm.replace(/ /g, "'):containsi('")
				    
				  $.extend($.expr[':'], {'containsi': function(elem, i, match, array){
				        return (elem.textContent || elem.innerText || '').toLowerCase().indexOf((match[3] || "").toLowerCase()) >= 0;
				    }
				  });
				    
				  $(".results tbody tr").not(":containsi('" + searchSplit + "')").each(function(e){
				    $(this).attr('visible','false');
				  });

				  $(".results tbody tr:containsi('" + searchSplit + "')").each(function(e){
				    $(this).attr('visible','true');
				  });

				  var jobCount = $('.results tbody tr[visible="true"]').length;
				    $('.counter').text(jobCount + ' item');

				  if(jobCount == '0') {$('.no-result').show();}
				    else {$('.no-result').hide();}
						  });
				});
		</script>

