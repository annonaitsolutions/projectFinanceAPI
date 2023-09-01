<%@include file="taglib_includes.jsp" %>
 <div class="col-md-9 col-sm-9 col-lg-9 body_fixed">
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
            <h3 align="center"><spring:message code="label.sellerDifferentBankEvents"/></h3> 
      </div>
            <form:form action="confirmSelectUpdateSellerDiffBankEvents" method="post" commandName="sellerDiffBankEventForm">  
            <table align="center">
				<tr>
					<td  class="col-sm-6"><b><spring:message code="label.customerName"/>:</b></td>
					<td  class="col-sm-6"><form:input id="customerName" path="customerName" readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.buyerName"/>:</b></td>
					<td class="col-sm-6"><form:input id="buyer" path="buyer" readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.buyerBank"/>:</b></td>
					<td class="col-sm-6"><form:input id="buyerBank" path="buyerBank" readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.masterKey"/>:</b></td>
					<td  class="col-sm-6"><form:input id="masterKey" path="masterKey" readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.goods"/>:</b></td>
					<td class="col-sm-6"><form:input id="goods" path="goods" readonly="true"></form:input></td>
				</tr>
				
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.cost"/>:</b></td>
					<td class="col-sm-7"><form:input id="cost" path="cost" readonly="true"></form:input>
					</td>
				</tr>
			</table>
<form:hidden path="id"></form:hidden>
<form:hidden path="invoiceKey"></form:hidden>

		<table class="table-bordered theme" align="center">
			<thead>
				<tr>
						<th><spring:message code="label.stepNumber"/></th>
					<th><spring:message code="label.dayNumber"/></th>
					<th><spring:message code="label.date"/></th>
					<th><spring:message code="label.event"/></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td><form:input  value="1" size="3" path="first" readonly="true"/></td>
					<td id="e1"><form:input path="date1" id="datepicker" readonly="true"></form:input></td>
					
					<td><form:input path="event1" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/hands.png" class="imagesize" alt="img"></img></td>
				</tr>
			      <tr>
					<td>2</td>
					<td><form:input size="3"  path="second" onchange="myFunc(document.getElementById('first').value,document.getElementById('second').value,'date2')"/></td> 
					<td id="e2"><form:input path="date2" id="date2" readonly="true"></form:input></td>
					<td><form:input path="event2" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/invoice.png" class="imagesize" alt="img"></img></td>
				</tr>
			    <tr>
					<td>3</td>
					<td><form:input size="3"  path="third" onchange="myFunc(document.getElementById('second').value,document.getElementById('third').value,'date3')"/></td>
					<td id="e3"><form:input path="date3" id="date3" readonly="true"></form:input></td>
					<td><form:select style="width: 670px;" path="event3"><form:option value="${model.sellerDiffBankEvent.buyer} opens a Letter of Credit favouring ${model.sellerDiffBankEvent.customerName}"/>
					<form:option value="${model.sellerDiffBankEvent.buyer}  sells the ${model.sellerDiffBankEvent.goods} on Open Account (Factoring)"></form:option>
					</form:select>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/letterOfCredit.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>4</td>
					<td> <form:input size="3"  path="fourth" onchange="myFunc(document.getElementById('third').value,document.getElementById('fourth').value,'date4')"/> </td>
					<td id="e4"><form:input path="date4" id="date4" readonly="true"></form:input></td>
					<td><form:select style="width: 670px;" path="event4"><form:option value="${model.sellerDiffBankEvent.customerName} loads the ${model.sellerDiffBankEvent.goods} in a Truck and sends it to ${model.sellerDiffBankEvent.buyer} (Single modal)"/>
					<form:option value="${model.sellerDiffBankEvent.customerName} loads the ${model.sellerDiffBankEvent.goods} in a Ship/Rail/Truck and sends it to ${model.sellerDiffBankEvent.buyer} (Multi  modal)"></form:option>
					</form:select>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/truckSendGoods.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>5</td>
					<td> <form:input size="3" path="fifth" onchange="myFunc(document.getElementById('fourth').value,document.getElementById('fifth').value,'date5')"/></td>
					<td id="e5"><form:input path="date5" id="date5" readonly="true"></form:input></td>
					<td><form:input path="event5" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/sendsdocument.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>6</td>
					<td> <form:input size="3" path="six" onchange="myFunc(document.getElementById('fifth').value,document.getElementById('six').value,'date6')"/></td>
					<td id="e6"><form:input path="date6" id="date6" readonly="true"></form:input></td>
					<td><form:input path="event6" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/phone.png" class="imagesize" alt="img"></img></td>
				</tr>
				 <tr>
					<td>7</td>
					<td><form:input size="3" path="seven" onchange="myFunc(document.getElementById('six').value,document.getElementById('seven').value,'date7')"/> </td>
					<td id="e7"><form:input path="date7" id="date7" readonly="true"></form:input></td>
					<td><form:input path="event7" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankToBank.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>8</td>
					<td><form:input size="3" path="eight" onchange="myFunc(document.getElementById('seven').value,document.getElementById('eight').value,'date8')"/></td>
					<td id="e8"><form:input path="date8" id="date8" readonly="true"></form:input></td>
					<td><form:input path="event8" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankIntimates.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>9</td>
					<td><form:input size="3" path="nine" onchange="myFunc(document.getElementById('eight').value,document.getElementById('nine').value,'date9')"/></td>
					<td id="e9"><form:input path="date9" id="date9" readonly="true"></form:input></td>
					<td><form:input path="event9" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/accepted.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>10</td>
					<td> <form:input size="3" path="ten" onchange="myFunc(document.getElementById('nine').value,document.getElementById('ten').value,'date10')"/> </td>
					<td id="e10"><form:input path="date10" id="date10" readonly="true"></form:input></td>
					<td><form:input path="event10" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankToBank.png" class="imagesize" alt="img"></img></td>
				</tr>  
				<tr>
					<td>11</td>
					<td> <form:input size="3" path="elven" onchange="myFunc(document.getElementById('ten').value,document.getElementById('elven').value,'date11')"/></td>
					<td id="e11"><form:input path="date11" id="date11" readonly="true"></form:input></td>
					<td><form:input path="event11" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankToCustomerMoney.png" class="imagesize" alt="img"></img></td>
				</tr> 
				<tr>
					<td>12</td>
					<td> <form:input size="3" path="twelve" onchange="myFunc(document.getElementById('elven').value,document.getElementById('twelve').value,'date12')"/></td>
					<td id="e12"><form:input path="date12" id="date12" readonly="true"></form:input></td>
					<td><form:input path="event12" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/goodsRecevied.png" class="imagesize" alt="img"></img></td>
				</tr> 
				<tr>
					<td>13</td>
					<td> <form:input size="3" path="thirteen" onchange="myFunc(document.getElementById('twelve').value,document.getElementById('thirteen').value,'date13')"/></td>
					<td id="e13"><form:input path="date13" id="date13" readonly="true"></form:input></td>
					<td><form:input path="event13" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankToCustomerMoney.png" class="imagesize" alt="img"></img></td>
				</tr> 
				<tr>
					<td>14</td>
					<td> <form:input size="3" path="fourteen" onchange="myFunc(document.getElementById('thirteen').value,document.getElementById('fourteen').value,'date14')"/></td>
					<td id="e13"><form:input path="date14" id="date14" readonly="true"></form:input></td>
					<td><form:input path="event14" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankToBankMoney.png" class="imagesize" alt="img"></img></td>
				</tr> 
				
				<tr>
					<td>15</td>
					<td> <form:input size="3" path="fifteen" onchange="myFunc(document.getElementById('fourteen').value,document.getElementById('fifteen').value,'date15')"/></td>
					<td id="e13"><form:input path="date15" id="date15" readonly="true"></form:input></td>
					<td><form:input path="event15" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/sendsdocument.png" class="imagesize" alt="img"></img></td>
				</tr> 
				
				<tr>
					<td>16</td>
					<td> <form:input size="3" path="sixteen" onchange="myFunc(document.getElementById('fifteen').value,document.getElementById('sixteen').value,'date16')"/></td>
					<td id="e13"><form:input path="date16" id="date16" readonly="true"></form:input></td>
					<td><form:input path="event16" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/hands.png" class="imagesize" alt="img"></img></td>
				</tr> 
				

		</table>
		
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
							<h3 align="center"><spring:message code="label.prePlanDocs"/>.</h3>
				</div> 
			<div class="col-sm-12 col-md-12 col-lg-12"> 
			 <table class="host-list" align="center" id="dataTable">
			<tr class="chargescolor">
	                     
	                  <th><spring:message code="label.docsName"/></th>
						<th><spring:message code="label.description"/></th>
					</tr>		
		<c:forEach items="${model.invoiceDocForms}" var="status">
				<tr>
				
					<td><input type="text" name="colourDelayI" value="${status.docName}" readonly="readonly"   /></td>
					<td><input type="text" name="colourAdvanceI" value="${status.description}" readonly="readonly" /></td>
					</tr>
					</c:forEach>
					</table></div><br>
		<div class="col-sm-12 col-md-12">&nbsp;</div>
		
	<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="sellerDiffViewAllEvents" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
		
            
            </form:form>
         
        </div>
    
<style>
  input#date1,input#date2,input#date3,input#date4,input#date5,input#date6,input#date7,input#date8,input#date9,input#date10,input#date11,input#date12,input#date13,input#date14,input#date15,input#date16 {
   		 width: 123px;
	}
	input#first,input#second,input#third,input#fourth,input#fifth,input#six,input#seven,input#eight,input#nine,input#ten,input#elven,input#twelve,input#thirteen,input#fourteen,input#fifteen,input#sixteen {
   	    width: 50px;
	}
	input#datepicker {
    	width: 123px;
}
	input#event1,input#event2,input#event3,input#event4,input#event5,input#event6,input#event7,input#event8,input#event9,input#event10,input#event11,input#event12,input#event13,input#event14,input#event15,input#event16 {
    	width: 600px;
}	
</style>
<script>
    $(document).ready( function () {
        $('#bootstrap-table').bdt();
    });
    
function myFunc(predays,days,dateId) {
	
	if(isNaN(days))
	{
	alert("Must input numbers");
	}
else
	{
	if(days == '')
	{
	document.getElementById(dateId).value = '';
	}
	else if(predays<=days)
{ 
		
		var date = document.getElementById("datepicker").value;
		from = date.split("/");
		var eventDate = new Date(from[2], from[1] - 1, from[0]);
		
		var n = eventDate.getDate() + parseInt(days);
		eventDate.setDate(n);
        
		 
	  	 		
		
		if (eventDate.getDay() == 0) {
			if(confirm(eventDate.getDate() + '/' + (eventDate.getMonth() + 1)   + '/' +  eventDate.getFullYear()+" is sunday \nConsider sunday as a working day.")==true)
    		{
				eventDate.setDate(eventDate.getDate());
    	}
    	else
    		{
    	
    		eventDate.setDate(eventDate.getDate()+parseInt(1));
    	    		
    	 } 
			
		}
		document.getElementById(dateId).value = eventDate.getDate() + '/' + (eventDate.getMonth() + 1)   + '/' +  eventDate.getFullYear();
}	
	
	 

	else
		{
		alert("enter more than previous day ");
		}
	}		
	  	
	}
</script>

</body>