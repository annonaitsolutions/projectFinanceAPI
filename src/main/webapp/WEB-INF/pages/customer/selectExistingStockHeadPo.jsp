<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script>

function Val1()
{

var p,r,n,i;
if(document.interest.quantity.value == " " )
{
alert("PLEASE ENTER Quantity");
document.interest.quantity.alert();
}
else if(document.interest.usedQuantity.value=="")
{
alert("ENTER used quantity");
document.interest.usedQuantity.blur();

}
else if(document.interest.damaged.value=="")
{
alert("ENTER NUMBER OF damaged quantity");
document.interest.damaged.prompt();
}
else
{
p=document.interest.quantity.value;

r=document.interest.usedQuantity.value;

n=document.interest.damaged.value;

i=p-n-r;

document.interest.overAllQuantity.value= i;
}
}
</script>
<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
<form:form action="updateExistingStockHeadPoConfirm" method="post" name="interest" commandName="poStockForm" onsubmit="return val();">
	 <div  class="successMsg"><b><font color="green">${success}</font></b></div>
				<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 align="center"><spring:message code="label.existingStock"/></h3>
				</div>
		<div class="col-sm-12 col-md-12 col-lg-12 newcustomer_border">
			<div class="content col-sm-12 col-md-12 col-lg-12">
								<table align="center">
								
								<form:hidden path="id"/>
									<tr>
										<td class="col-sm-6"><b><spring:message code="label.customer"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="customerName" id="customerName" readonly="true" ></form:input>
																									
									</tr>
							<%-- 	<tr>
										<td class="col-sm-6"><b><spring:message code="label.customerHeadName"/></b><span style="color:red">*</span></td>
										<td class="col-sm-6"><form:input path="customerHeadName" id="customerHeadName" readonly="true" ></form:input>
																									
									</tr> --%>
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.goodsName"/></b><span style="color:red">*</span></td>
											<td class="col-sm-6">
											<form:input path="goodsName" id="goodsName" placeholder="Select country"></form:input>
											<span id="goodsNameError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
											<span id="goodsNameError" class="error" style="display:none"><spring:message code="label.validation"/></span></td> 
									</tr>
									
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.quantity"/></b><span style="color:red">*</span></td>
											<td class="col-sm-6"><form:input path="quantity"  id="quantity"></form:input>
											<span id="quantityError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
											<span id="quantityError" class="error" style="display:none"><spring:message code="label.validation"/></span></td>
																											
									
									<tr>
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.usedQuantity"/></b><span style="color:red">*</span></td>
											<td class="col-sm-6"><form:input path="usedQuantity"  id="usedQuantity"></form:input>
											<span id="usedQuantityError2" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
											<span id="usedQuantityError2" class="error" style="display:none"><spring:message code="label.validation"/></span></td>
																											
									
									<tr>
									<tr>
											<td class="col-sm-6"><b><spring:message code="label.damagedQuantity"/></b><span style="color:red">*</span></td>
											<td class="col-sm-6"><form:input path="damaged"  id="damaged"></form:input>
											<span id="damagedError2" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/>*</font></span>
											<span id="damagedError2" class="error" style="display:none"><spring:message code="label.validation"/></span></td>
																											
									
									<tr>
									
									<tr>
					 <td colspan="2"><input type="button" value="CALCULATE" size="5" onClick="Val1()" class="btn btn-primary">
                    <input type="reset" value="clear" class="btn btn-primary"></td>
				</tr>
            
					<tr>
					<td class="col-sm-6"><b><spring:message code="label.quantityRemaining"/></b></td>
					<td class="col-sm-6"><form:input path="overAllQuantity" id="overAllQuantity" readonly="true"/></td>
				     <td id="totalError" class="error" style="display:none;"><font color="red"><spring:message code="label.validation"/></font></td>
					 <td id="totalError" class="error" style="display:none"><spring:message code="label.validation"/></td>
				</tr>	
									
						</table>
						
				
				
			</div>
		
		
		<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="existingStockHeadPo" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
		</div>
		</form:form>
	 
		<script>
		
		
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
			    var number1= /^-?[0-9]+$/
			    var quantity = document.getElementById('quantity');
			    var usedQuantity = document.getElementById('usedQuantity');
			    var damaged = document.getElementById('damaged');
			    var canSubmit = true;


			    if (document.getElementById('goodsName').value == ''){
			     document.getElementById('goodsNameError').style.display='block';
			     canSubmit = false;
			    }
			    else{
			     document.getElementById('goodsNameError').style.display='none';
			    }
			    
			     if(quantity.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || quantity.value.match(/^-?[0-9]+$/) &&!(document.getElementById('quantity').value == ''))
			       {
			        
			        document.getElementById('quantityError').style.display='none';
			       
			       }
			       else{
			        document.getElementById('quantityError').style.display='block';
			            canSubmit = false;
			       }
			  
			     if(usedQuantity.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || usedQuantity.value.match(/^-?[0-9]+$/) &&!(document.getElementById('usedQuantity').value == ''))
			       {
			        
			        document.getElementById('usedQuantityError2').style.display='none';
			       
			       }
			       else{
			        document.getElementById('usedQuantityError2').style.display='block';
			            canSubmit = false;
			       }
			     
			     if(damaged.value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/)  || damaged.value.match(/^-?[0-9]+$/) &&!(document.getElementById('damaged').value == ''))
			       {
			        
			        document.getElementById('damagedError2').style.display='none';
			       
			       }
			       else{
			        document.getElementById('damagedError2').style.display='block';
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

