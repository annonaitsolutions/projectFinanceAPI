<%@include file="taglib_includes.jsp" %>
<script>
	function validateForm() {

		var poAmount = parseFloat(document.event.poAmount.value);
		
		var canSubmit = true;

		
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
<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			 	<h3 style="text-align:center"><spring:message code="label.eventsTablePoSameBank"/></h3>
			 </div>
	<form:form name="event" action="confirmBuyerSameBankEvent" method="post" commandName="buyerSameBankEventForm" onsubmit="return validateForm();">
			<div class="col-sm-12 col-md-12">
			<table align="center">
				<tr>
					<td  class="col-sm-5"><b><spring:message code="label.customerName"/></b></td>
					<td  class="col-sm-7"><form:input id="customerName" path="customerName" value="${model.purchaseOrder.customerName}" readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.supplierName"/></b></td>
					<td class="col-sm-7"><form:input id="supplier" path="supplier" value="${model.purchaseOrder.supplierName}" readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.supplierBank"/></b></td>
					<td class="col-sm-7"><form:input id="supplierBank" path="supplierBank" value="${model.purchaseOrder.supplierBank}" readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.masterKey"/></b></td>
					<td  class="col-sm-7"><form:input id="masterKey" path="masterKey" value="${model.purchaseOrder.masterKey}" readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.goods"/></b></td>
					<td class="col-sm-7"><form:input id="goods" path="goods" value="${model.purchaseOrder.goods}" readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.poAmount"/></b></td>
					<td class="col-sm-7"><form:input id="poAmount" path="poAmount" value="${model.purchaseOrder.amount}" readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.sanctionedAmt"/></b></td>
					<td class="col-sm-7"><form:input id="sanctionedAmount" path="sanctionedAmount" value="${model.masterPlan.buyingCostSanc}" readonly="true"></form:input>
					
			</td>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.utilizedAmt"/></b></td>
					<td class="col-sm-7"><form:input id="utilizedAmount" path="utilizedAmount" value="${model.masterPlan.utilizedBusnsAmt}" readonly="true"></form:input>
					
			</td>
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.availableAmt"/></b></td>
					<td class="col-sm-7"><form:input id="availableCost" path="availableCost" value="" readonly="true"></form:input>
					
			</td>
				</tr>
			</table>
			
				 
			</div>
		
		<form:hidden path="poKey" value="${model.purchaseOrder.poKey}"/>
		<form:hidden path="status" value="NA"/>
		<div class="col-sm-12 col-md-12 col-lg-12">
		<table class="table-bordered theme">
			 <colgroup>
       			 <col class="first" span="1"/>
       			 <col class="second" span="2"/>
    		</colgroup>
			<thead>
				<tr>
					<th><spring:message code="label.stepNo"/></th>
					<th><spring:message code="label.dayNo"/></th>
					<th><spring:message code="label.date"/></th>
					<th><spring:message code="label.event"/></th>
					<th></th>    
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td><form:input  value="1" size="3" path="first" style="width: 60px;" /></td>
					<td id="e1">
              			<form:input id="datepicker" path="date1" class="form-control" placeholder="Select date" style="width: 111px;" readonly="true"/><i class="fa fa-calendar"></i>
					<div id="date1Error" style="display: none; color: red;"><spring:message code="label.validation3"/></div>
						<div id="date1Error" style="display: none; color: red;"><spring:message code="label.validation3"/></div> 
					</td>
					
					<td><form:hidden path="event1" value="Contract between ${model.purchaseOrder.supplierName} and ${model.purchaseOrder.customerName}"/>
					<p>Contract between ${model.purchaseOrder.supplierName} and ${model.purchaseOrder.customerName}</p></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/hands.png" class="imagesize" alt="img"></img></td>
				</tr>
			    <tr>
					<td>2</td>
					<td><form:input size="3"  style="width: 60px;" path="second" id="second" onchange="myFunc(document.getElementById('first').value,document.getElementById('second').value,'date2')"/></td> 
					<td id="e2"><form:input path="date2" readonly="true" id="date2" style="width: 111px;"></form:input></td>
					<td><form:hidden path="event2" style="width: 575px;" value="${model.purchaseOrder.customerName} sends a Purchase Order to ${model.purchaseOrder.supplierName}"/>
					<p>${model.purchaseOrder.customerName} sends a Purchase Order to ${model.purchaseOrder.supplierName}</p></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/sending.png" class="imagesize" alt="img"></img></td>
				</tr>
			   <tr>
				 <td>3</td>
					<td><form:input size="3"  style="width: 60px;" path="third" id="third" onchange="myFunc(document.getElementById('second').value,document.getElementById('third').value,'date3')"/></td>
					<td id="e3"><form:input path="date3" readonly="true" id="date3" style="width: 111px;"></form:input></td>
					<td><form:select style="width: 575px;" path="event3"><form:option value="${model.purchaseOrder.customerName} opens a Letter of Credit favouring ${model.purchaseOrder.supplierName}"/>
					<form:option value="${model.purchaseOrder.customerName}  sells the ${model.purchaseOrder.goods} on Open Account (Factoring)"></form:option>
					</form:select></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/letterOfCredit.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>4</td>
					<td> <form:input size="3" style="width: 60px;"  path="fourth" onchange="myFunc(document.getElementById('third').value,document.getElementById('fourth').value,'date4')"/> </td>
					<td id="e4"><form:input path="date4" id="date4" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:select style="width: 575px;" path="event4"><form:option value="${model.purchaseOrder.supplierName} loads the ${model.purchaseOrder.goods} in a Truck and sends it to ${model.purchaseOrder.customerName} (Single modal)"/>
					<form:option value="${model.purchaseOrder.supplierName} loads the ${model.purchaseOrder.goods} in a Ship/Rail/Truck and sends it to ${model.purchaseOrder.customerName} (Multi  modal)"></form:option>
					</form:select>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/truckSendGoods.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>5</td>
					<td> <form:input size="3" style="width: 60px;" path="fifth" onchange="myFunc(document.getElementById('fourth').value,document.getElementById('fifth').value,'date5')"/></td>
					<td id="e5"><form:input path="date5" id="date5" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:hidden path="event5" value="${model.purchaseOrder.supplierName} sends documents to Bank"/>
					<p>${model.purchaseOrder.supplierName} sends documents to Bank</p></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/sendsdocument.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>6</td>
					<td> <form:input size="3" style="width: 60px;" path="six" onchange="myFunc(document.getElementById('fifth').value,document.getElementById('six').value,'date6')"/></td>
					<td id="e6"><form:input path="date6" id="date6" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:hidden path="event6" value="${model.purchaseOrder.supplierName} intimates ${model.purchaseOrder.customerName} that they have sent the ${model.purchaseOrder.goods} and documents submitted to their bank"/>
					<p>${model.purchaseOrder.supplierName} intimates ${model.purchaseOrder.customerName} that they have sent the ${model.purchaseOrder.goods} and documents submitted to their bank</p></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/phone.png" class="imagesize" alt="img"></img></td>
				</tr>
				 <tr>
					<td>7</td>
					<td><form:input size="3" style="width: 60px;" path="seven" onchange="myFunc(document.getElementById('six').value,document.getElementById('seven').value,'date7')"/> </td>
					<td id="e7"><form:input path="date7" id="date7" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:hidden path="event7" value="Bank receives documents intimates ${model.purchaseOrder.customerName} for Acceptance of Documents"/>
					<p>Bank receives documents intimates ${model.purchaseOrder.customerName} for Acceptance of Documents</p></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankIntimates.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>8</td>
					<td><form:input size="3"  style="width: 60px;" path="eight" onchange="myFunc(document.getElementById('seven').value,document.getElementById('eight').value,'date8')"/></td>
					<td id="e8"><form:input path="date8" id="date8" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:hidden path="event8" value="${model.purchaseOrder.customerName} accepts Bill of Exchange and takes the documents to release the ${model.purchaseOrder.goods}"/>
					<p>${model.purchaseOrder.customerName} accepts Bill of Exchange and takes the documents to release the ${model.purchaseOrder.goods}</p></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/accepted.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>9</td>
					<td><form:input size="3" style="width: 60px;" path="nine" onchange="myFunc(document.getElementById('eight').value,document.getElementById('nine').value,'date9')"/></td>
					<td id="e9"><form:input path="date9" id="date9" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:hidden path="event9" value="Bank Credits ${model.purchaseOrder.supplierName} Account"/>
					<p>Bank Credits ${model.purchaseOrder.supplierName} Account</p></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankToCustomerMoney.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>10</td>
					<td> <form:input size="3" style="width: 60px;" path="ten" onchange="myFunc(document.getElementById('nine').value,document.getElementById('ten').value,'date10')"/> </td>
					<td id="e10"><form:input path="date10" id="date10" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:hidden path="event10" value="${model.purchaseOrder.customerName} Receives ${model.purchaseOrder.goods} and starts to process it to finished products"/>
					<p>${model.purchaseOrder.customerName} Receives ${model.purchaseOrder.goods} and starts to process it to finished products</p></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/goodsRecevied.png" class="imagesize" alt="img"></img></td>
				</tr>  
				<tr>
					<td>11</td>
					<td> <form:input size="3" style="width: 60px;" path="elven" onchange="myFunc(document.getElementById('ten').value,document.getElementById('elven').value,'date11')"/></td>
					<td id="e10"><form:input path="date11" id="date11" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:hidden path="event11" value="TRANSACTION ENDS"/>
					<p>TRANSACTION ENDS</p></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/hands.png" class="imagesize" alt="img"></img></td>
				</tr>
 				

		</table>
	</div>
		<div class="col-sm-12 col-md-12 col-lg-12">&nbsp;</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
			<div >${success}</div>
		</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.confirm"/>" class="btn btn-primary"></td>
							<td><a href="newEvent" class="btn btn-success"><spring:message code="label.back"/></a></td>
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