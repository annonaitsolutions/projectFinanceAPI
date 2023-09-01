<%@include file="taglib_includes.jsp" %>
<script>
	function validateForm() {

		var masterKey = document.getElementById('masterKey');

		var canSubmit = true;

		if (document.getElementById('cost').value == '') {
			document.getElementById('costError').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('costError').style.display = 'none';
		}
		
		if (document.getElementById('datepicker').value == '') {
			document.getElementById('date1Error').style.display = 'block';
			canSubmit = false;
		} else {
			document.getElementById('date1Error').style.display = 'none';
		}

		if (canSubmit == false) {
			return false;
		}

	}
</script>
<div class="col-md-9 col-sm-9 col-lg-9 body_fixed">
<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
	<h3><spring:message code="label.eventsTable"/></h3>
</div>
	<form:form name="event" action="confirmSellerDiffBankEvent" method="post" commandName="sellerDiffBankEventForm" onsubmit="return validateForm();">
		
			
		
			<table align="center">
				<tr>
					<td  class="col-sm-5"><b><spring:message code="label.customerName"/>:</b></td>
					<td  class="col-sm-7"><form:input id="customerName" path="customerName" value="${model.invoice.customerName}" readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.buyerName"/>:</b></td>
					<td class="col-sm-7"><form:input id="buyer" path="buyer" value="${model.invoice.buyerName}" readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.buyerBank"/>:</b></td>
					<td class="col-sm-7"><form:input id="buyerBank" path="buyerBank" value="${model.invoice.buyerBank}" readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.masterKey"/>:</b></td>
					<td  class="col-sm-7"><form:input id="masterKey" path="masterKey" value="${model.invoice.masterKey}" readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.goods"/>:</b></td>
					<td class="col-sm-7"><form:input id="goods" path="goods" value="${model.invoice.goods}" readonly="true"></form:input></td>
				</tr>
				
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.cost"/>:</b></td>
					<td class="col-sm-7"><form:input id="cost" path="cost" value=""></form:input>
					<div id="costError" style="display: none; color: red;"><spring:message code="label.validation"/></div>
					
			<div id="costMoreError" style="display: none; color: red;"><spring:message code="label.costValidation"/></div></td>
				</tr>
			</table>
<form:hidden path="invoiceKey" value="${model.invoice.poKey}"/>
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
					<td><form:input  value="1" size="3" path="first"/></td>
					<td id="e1"><form:input id="datepicker" path="date1" class="form-control" placeholder="Select date" style="width: 123px;" readonly="true"/><i class="fa fa-calendar"></i>
					<div id="date1Error" style="display: none; color: red;">
						Date is Mandatory</div>
						<div id="date1Error" style="display: none; color: red;">
						Date is Mandatory</div> 
					</td>
					
					<td><form:hidden path="event1" value="Contract between ${model.invoice.buyerName} and ${model.invoice.customerName}"/>
					<p>Contract between ${model.invoice.buyerName} and ${model.invoice.customerName}</p></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/hands.png" class="imagesize" alt="img"></img></td>
				</tr>
			     <tr>
					<td>2</td>
					<td><form:input size="3"  path="second" onchange="myFunc(document.getElementById('first').value,document.getElementById('second').value,'date2')"/></td> 
					<td id="e2"><form:input path="date2" id="date2" readonly="true"></form:input></td>
					<td><form:hidden path="event2" value="${model.invoice.buyerName} sends a Purchase Order to ${model.invoice.customerName}"/>
					<p>${model.invoice.buyerName} sends a Purchase Order to ${model.invoice.customerName}</p></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/invoice.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>3</td>
					<td><form:input size="3"  path="third" onchange="myFunc(document.getElementById('second').value,document.getElementById('third').value,'date3')"/></td>
					<td id="e3"><form:input path="date3" id="date3" readonly="true"></form:input></td>
					<td><form:select style="width: 670px;" path="event3"><form:option value="${model.invoice.buyerName} opens a Letter of Credit favouring ${model.invoice.customerName}"/>
					<form:option value="${model.invoice.buyerName}  sells the ${model.invoice.goods} on Open Account (Factoring)"></form:option>
					</form:select></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/letterOfCredit.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>4</td>
					<td> <form:input size="3"  path="fourth" onchange="myFunc(document.getElementById('third').value,document.getElementById('fourth').value,'date4')"/> </td>
					<td id="e4"><form:input path="date4" id="date4" readonly="true"></form:input></td>
					<td><form:select style="width: 670px;" path="event4"><form:option value="${model.invoice.customerName} loads the ${model.invoice.goods} in a Truck and sends it to ${model.invoice.buyerName} (Single modal)"/>
					<form:option value="${model.invoice.customerName} loads the ${model.invoice.goods} in a Ship/Rail/Truck and sends it to ${model.invoice.buyerName} (Multi  modal)"></form:option>
					</form:select>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/truckSendGoods.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>5</td>
					<td> <form:input size="3" path="fifth" onchange="myFunc(document.getElementById('fourth').value,document.getElementById('fifth').value,'date5')"/></td>
					<td id="e5"><form:input path="date5" id="date5" readonly="true"></form:input></td>
					<td><form:hidden path="event5" value="${model.invoice.customerName} sends documents to Bank"/>
					<p>${model.invoice.customerName} sends documents to Bank</p></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/sendsdocument.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>6</td>
					<td> <form:input size="3" path="six" onchange="myFunc(document.getElementById('fifth').value,document.getElementById('six').value,'date6')"/></td>
					<td id="e6"><form:input path="date6" id="date6" readonly="true"></form:input></td>
					<td><form:hidden path="event6" value="${model.invoice.customerName} intimates ${model.invoice.buyerName} that they have sent the ${model.invoice.goods} and documents submitted to their bank"/>
					<p>${model.invoice.customerName} intimates ${model.invoice.buyerName} that they have sent the ${model.invoice.goods} and documents submitted to their bank</p></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/phone.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>7</td>
					<td><form:input size="3" path="seven" onchange="myFunc(document.getElementById('six').value,document.getElementById('seven').value,'date7')"/> </td>
					<td id="e7"><form:input path="date7" id="date7" readonly="true"></form:input></td>
					<td><form:hidden path="event7" value="${model.customerBankName} receives documents and forwards it to ${model.invoice.buyerBank} for payment"/>
					<p>${model.customerBankName} Bank receives documents and forwards it to ${model.invoice.buyerBank} for payment</p></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankToBank.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>8</td>
					<td><form:input size="3" path="eight" onchange="myFunc(document.getElementById('seven').value,document.getElementById('eight').value,'date8')"/></td>
					<td id="e8"><form:input path="date8" id="date8" readonly="true"></form:input></td>
					<td>
					<form:hidden path="event8" value="${model.invoice.buyerBank} receives documents intimates ${model.invoice.buyerName} for Acceptance of Documents"/>
					<p>${model.invoice.buyerBank} receives documents intimates ${model.invoice.buyerName} for Acceptance of Documents</p></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankIntimates.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>9</td>
					<td><form:input size="3" path="nine" onchange="myFunc(document.getElementById('eight').value,document.getElementById('nine').value,'date9')"/></td>
					<td id="e9"><form:input path="date9" id="date9" readonly="true"></form:input></td>
					<td><form:hidden path="event9" value="${model.invoice.buyerName} accepts Bill of Exchange and takes the documents to release the ${model.invoice.goods}"/>
					<p>${model.invoice.buyerName} accepts Bill of Exchange and takes the documents to release the ${model.invoice.goods}</p></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/accepted.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>10</td>
					<td> <form:input size="3" path="ten" onchange="myFunc(document.getElementById('nine').value,document.getElementById('ten').value,'date10')"/> </td>
					<td id="e10"><form:input path="date10" id="date10" readonly="true"></form:input></td>
					<td><form:hidden path="event10" value="${model.invoice.buyerBank} sends the Accepted Bill of Exchange and a copy of Documents to ${model.customerBankName} and keeps a copy with themselves"/>
					<p>${model.invoice.buyerBank} sends the Accepted Bill of Exchange and a copy of Documents to ${model.customerBankName} and keeps a copy with themselves</p></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankToBank.png" class="imagesize" alt="img"></img></td>
				</tr>  
				<tr>
					<td>11</td>
					<td> <form:input size="3" path="elven" onchange="myFunc(document.getElementById('ten').value,document.getElementById('elven').value,'date11')"/></td>
					<td id="e11"><form:input path="date11" id="date11" readonly="true"></form:input></td>
					<td><form:hidden path="event11" value="${model.customerBankName} Credits ${model.invoice.customerName} Account less the amount paid to ${model.invoice.buyerName}. Interest and other Charges"/>
					<p>${model.customerBankName} Credits ${model.invoice.customerName} Account less the amount paid to ${model.invoice.buyerName}. Interest and other Charges</p></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankToCustomerMoney.png" class="imagesize" alt="img"></img></td>
				</tr> 
				<tr>
					<td>12</td>
					<td> <form:input size="3" path="twelve" onchange="myFunc(document.getElementById('elven').value,document.getElementById('twelve').value,'date12')"/> </td>
					<td id="e12"><form:input  path="date12" id="date12" readonly="true"></form:input></td>
					<td><form:hidden path="event12" value="${model.invoice.buyerName} Receives ${model.invoice.goods}"/>
					<p>${model.invoice.buyerName} Receives ${model.invoice.goods}</p></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/goodsRecevied.png" class="imagesize" alt="img"></img></td>
				</tr>  
				<tr>
					<td>13</td>
					<td> <form:input size="3" path="thirteen" onchange="myFunc(document.getElementById('twelve').value,document.getElementById('thirteen').value,'date13')"/></td>
					<td id="e13"><form:input path="date13" id="date13" readonly="true"></form:input></td>
					<td><form:hidden path="event13" value="${model.invoice.buyerName} sells the ${model.invoice.goods} and pays their Bank"/>
					<p>${model.invoice.buyerName} sells the ${model.invoice.goods} and pays their Bank</p></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankToCustomerMoney.png" class="imagesize" alt="img"></img></td>
				</tr>  
				<tr>
					<td>14</td>
					<td> <form:input size="3" path="fourteen" onchange="myFunc(document.getElementById('thirteen').value,document.getElementById('fourteen').value,'date14')"/></td>
					<td id="e14"><form:input path="date14" id="date14" readonly="true"></form:input></td>
					<td><form:hidden path="event14" value="${model.invoice.buyerBank} reimburses ${model.customerBankName}"/>
					<p>${model.invoice.buyerBank} reimburses ${model.customerBankName}</p></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankToBankMoney.png" class="imagesize" alt="img"></img></td>
				</tr>  
				<tr>
					<td>15</td>
					<td> <form:input size="3" path="fifteen" onchange="myFunc(document.getElementById('fourteen').value,document.getElementById('fifteen').value,'date15')"/></td>
					<td id="e15"><form:input path="date15" id="date15" readonly="true"></form:input></td>
					<td><form:hidden path="event15" value="All Documents are Retired and Archived"/>
					<p>All Documents are Retired and Archived</p></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/sendsdocument.png" class="imagesize" alt="img"></img></td>
				</tr>  
				
				<tr>
					<td>16</td>
					<td> <form:input size="3" path="sixteen" onchange="myFunc(document.getElementById('fifteen').value,document.getElementById('sixteen').value,'date16')"/></td>
					<td id="e16"><form:input path="date16" id="date16" readonly="true"></form:input></td>
					<td><form:hidden path="event16" value="TRANSACTION ENDS"/>
					<p>TRANSACTION ENDS</p></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/hands.png" class="imagesize" alt="img"></img></td>
				</tr>  
				<form:hidden path="status" value="NA"/>
				<form:hidden path="customerStatus" value="NA"/>
				

		</table>
		<div class="col-sm-12 col-md-12 col-lg-12">&nbsp;</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="sellerNewEvent" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
		</form:form>
		
	</div>

	
	<style>
	div#ui-datepicker-div {
    background-color: #3991CC;
    color: white;
    width: 206px;
	}
	td.ui-datepicker-week-end {
  	  padding: 3px 17px;
	}
	a.ui-state-default {
    color: white;
	}
	.ui-datepicker-header.ui-widget-header.ui-helper-clearfix.ui-corner-all {
        padding: 5px 18px;
	}
	a.ui-datepicker-next.ui-corner-all {
    float: right;
    background-color: #0856A7;
    border-radius: 3px;
    padding: 0px 6px;
    color: white;
	}
	span.ui-icon.ui-icon-circle-triangle-w {
    float: left;
    background-color: #0856A7;
    border-radius: 3px;
    padding: 0px 6px;
    color: white;
	}
	
	i.fa.fa-calendar {
	    position: absolute;
    margin-left: -32px;
    padding-top: 5px;
    font-size: 21px;
    color: #0C7AC7;
    }
    input#date1,input#date2,input#date3,input#date4,input#date5,input#date6,input#date7,input#date8,input#date9,input#date10,input#date11,input#date12,input#date13,input#date14,input#date15,input#date16 {
   	 width: 123px;
	}
	input#first,input#second,input#third,input#fourth,input#fifth,input#six,input#seven,input#eight,input#nine,input#ten,input#elven,input#twelve,input#thirteen,input#fourteen,input#fifteen,input#sixteen {
   	   width: 50px;
	}
</style>
	
	
	<script>

      $(function() {
	  $( "#datepicker" ).datepicker();
	   });

	  $(document).ready(function(){
		    $("#datepicker").change(function(){
		day1=($(this).datepicker('getDate'));
		day1=new Date(day1.getTime());
		if(day1.getDay()==0)
			{day1=new Date(day1.getTime()+(24*60*60*1000));}
		
		
		    document.forms["event"]["date1"].value=day1.getDate() + '/' +(day1.getMonth() + 1) + '/' +day1.getFullYear();
		    });
		});

	function myFunc(predays,days,dateId) {
		
		if(isNaN(days))
		{
		alert("Must input numbers ");
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
		if(date == '')
		{
		alert("You should select first date.");
		}
	else
		{

		from = date.split("/");
		var eventDate = new Date(from[2], from[1] - 1, from[0]);
		
		var n = eventDate.getDate() + parseInt(days);
		eventDate.setDate(n);
        
		
	  	 		
		
		if (eventDate.getDay() == 0) {
			if(confirm("Do you want to consider "+eventDate.getDate() + '/' + (eventDate.getMonth() + 1)   + '/' +  eventDate.getFullYear()+" Sunday as a working day?")==true)
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
		}
		else
		{
		alert("enter more than previous day ");
		}
		}		
	  	
	}
</script>
