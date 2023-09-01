<%@include file="taglib_includes.jsp" %>
 <div class="col-md-9 col-sm-9 col-lg-9 body_fixed">
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3 align="center"><spring:message code="label.sellerDifferentBankDeailsStatus"/></h3> 
	</div>
        <div class="col-md-12">
            
            <form:form action="" commandName="sellerDiffBankEventForm">  
             <div class="col-sm-6 col-md-6 col-lg-6">
           <table>
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
					<td class="col-sm-6"><b><spring:message code="label.cost"/>:</b></td>
					<td class="col-sm-6"><form:input id="cost" path="cost" readonly="true"></form:input>
					</td>
				</tr>
			</table>
			</div>
			<div class="col-sm-3 col-md-3 col-lg-3">

		<video id="eventsVideo" width="400" height="300" autoplay>
			<source	src="<%=request.getContextPath()%>/resources/videos/event1.mp4"
				type="video/mp4" id="events">
		</video>
	</div>
		<table class="table-bordered theme" align="center">
			<thead>
				<tr>
					<th><spring:message code="label.stepNumber"/></th>
					<th><spring:message code="label.dayNumber"/></th>
					<th><spring:message code="label.date"/></th>
					<th><spring:message code="label.tenureindays"/></th>
					<th><spring:message code="label.event"/></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td><form:input  value="1" size="3" path="first" readonly="true"/></td>
					<td id="e1"><form:input path="date1" readonly="true" style="width: 105px;"></form:input></td>
					<td><form:input  value="1" style="width: 60px;" size="3" path="" readonly="true"/></td>
					<td><form:input path="event1" cssStyle="color:${model.colour1};width: 587px;" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/hands.png" class="imagesize" alt="img"></img></td>
				</tr>
			      <tr>
					<td>2</td>
					<td><form:input size="3"  path="second" onchange="valid()" readonly="true"/></td> 
					<td id="e2"><form:input path="date2" readonly="true" style="width: 105px;"></form:input></td>
					<td><form:input style="width: 60px;" size="3" path="" value="${model.tenure2}" readonly="true"/></td>
					<td><form:input path="event2" cssStyle="color:${model.colour2};width: 587px;" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/invoice.png" class="imagesize" alt="img"></img></td>
				</tr>
			   <tr>
					<td>3</td>
					<td><form:input size="3"  path="third" onchange="valid1()" readonly="true"/></td>
					<td id="e3"><form:input path="date3" readonly="true" style="width: 105px;"></form:input></td>
					<td><form:input style="width: 60px;" size="3" path="" value="${model.tenure3}" readonly="true"/></td>
					<td><form:input path="event3" cssStyle="color:${model.colour3};width: 587px;" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/letterOfCredit.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>4</td>
					<td> <form:input size="3"  path="fourth" onchange="valid2()" readonly="true"/> </td>
					<td id="e4"><form:input path="date4" readonly="true" style="width: 105px;"></form:input></td>
					<td><form:input style="width: 60px;" size="3" path="" value="${model.tenure4}" readonly="true"/></td>
					<td><form:input path="event4" cssStyle="color:${model.colour4};width: 587px;" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/truckSendGoods.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>5</td>
					<td> <form:input size="3" path="fifth" onchange="valid3()" readonly="true"/></td>
					<td id="e5"><form:input path="date5" readonly="true" style="width: 105px;"></form:input></td>
					<td><form:input style="width: 60px;" size="3" path="" value="${model.tenure5}" readonly="true"/></td>
					<td><form:input path="event5" cssStyle="color:${model.colour5};width: 587px;" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/sendsdocument.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>6</td>
					<td> <form:input size="3" path="six" onchange="valid4()" readonly="true"/></td>
					<td id="e6"><form:input path="date6" readonly="true" style="width: 105px;"></form:input></td>
					<td><form:input style="width: 60px;" size="3" path="" value="${model.tenure6}" readonly="true"/></td>
					<td><%-- <form:input path="event6" cssStyle="color:${model.colour6};width: 587px;" readonly="true"/> --%>
					<form:textarea path="event6" cssStyle="color:${model.colour6};width: 587px;" value="" readonly="true"></form:textarea>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/phone.png" class="imagesize" alt="img"></img></td>
				</tr>
				 <tr>
					<td>7</td>
					<td><form:input size="3" path="seven" onchange="valid5()" readonly="true"/> </td>
					<td id="e7"><form:input path="date7" readonly="true" style="width: 105px;"></form:input></td>
					<td><form:input style="width: 60px;" size="3" path="" value="${model.tenure7}" readonly="true"/></td>
					<td><form:input path="event7" cssStyle="color:${model.colour7};width: 587px;" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankToBank.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>8</td>
					<td><form:input size="3" path="eight" onchange="valid6()" readonly="true"/></td>
					<td id="e8"><form:input path="date8" readonly="true" style="width: 105px;"></form:input></td>
					<td><form:input style="width: 60px;" size="3" path="" value="${model.tenure8}" readonly="true"/></td>
					<td><%-- <form:input path="event8" cssStyle="color:${model.colour8};width: 587px;" readonly="true"/> --%>
					<form:textarea path="event8" cssStyle="color:${model.colour8};width: 587px;" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankIntimates.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>9</td>
					<td><form:input size="3" path="nine" onchange="valid7()" readonly="true"/></td>
					<td id="e9"><form:input path="date9" readonly="true" style="width: 105px;"></form:input></td>
					<td><form:input style="width: 60px;" size="3" path="" value="${model.tenure9}" readonly="true"/></td>
					<td><form:input path="event9" cssStyle="color:${model.colour9};width: 587px;" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/accepted.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>10</td>
					<td> <form:input size="3" path="ten" onchange="valid8()" readonly="true"/> </td>
					<td id="e10"><form:input path="date10" readonly="true" style="width: 105px;"></form:input></td>
					<td><form:input style="width: 60px;" size="3" path="" value="${model.tenure10}" readonly="true"/></td>
					<td><%-- <form:input path="event10" cssStyle="color:${model.colour10};width: 587px;" readonly="true"/> --%>
					<form:textarea path="event10" cssStyle="color:${model.colour10};width: 587px;" value="" readonly="true"></form:textarea>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankToBank.png" class="imagesize" alt="img"></img></td>
				</tr>  
				<tr>
					<td>11</td>
					<td> <form:input size="3" path="elven" onchange="valid9()" readonly="true"/></td>
					<td id="e11"><form:input path="date11" readonly="true" style="width: 105px;"></form:input></td>
					<td><form:input style="width: 60px;" size="3" path="" value="${model.tenure11}" readonly="true"/></td>
					<td><%-- <form:input path="event11" cssStyle="color:${model.colour11};width: 587px;" readonly="true"/> --%>
					<form:textarea path="event11" cssStyle="color:${model.colour11};width: 587px;" value="" readonly="true"></form:textarea>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankToCustomerMoney.png" class="imagesize" alt="img"></img></td>
				</tr> 
				<tr>
					<td>12</td>
					<td> <form:input size="3" path="twelve" onchange="valid10()" readonly="true"/></td>
					<td id="e12"><form:input path="date12" readonly="true" style="width: 105px;"></form:input></td>
					<td><form:input style="width: 60px;" size="3" path="" value="${model.tenure12}" readonly="true"/></td>
					<td><form:input path="event12" cssStyle="color:${model.colour12};width: 587px;" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/goodsRecevied.png" class="imagesize" alt="img"></img></td>
				</tr> 
				<tr>
					<td>13</td>
					<td> <form:input size="3" path="thirteen" onchange="valid11()" readonly="true"/></td>
					<td id="e11"><form:input path="date13" readonly="true" style="width: 105px;"></form:input></td>
					<td><form:input style="width: 60px;" size="3" path="" value="${model.tenure13}" readonly="true"/></td>
					<td><form:input path="event13" cssStyle="color:${model.colour13};width: 587px;" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankToCustomerMoney.png" class="imagesize" alt="img"></img></td>
				</tr> 
				<tr>
					<td>14</td>
					<td> <form:input size="3" path="fourteen" onchange="valid12()" readonly="true"/></td>
					<td id="e13"><form:input path="date14" readonly="true" style="width: 105px;"></form:input></td>
					<td><form:input style="width: 60px;" size="3" path="" value="${model.tenure14}" readonly="true"/></td>
					<td><form:input path="event14" cssStyle="color:${model.colour14};width: 587px;" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankToBankMoney.png" class="imagesize" alt="img"></img></td>
				</tr> 
				
				<tr>
					<td>15</td>
					<td> <form:input size="3" path="fifteen" onchange="valid13()" readonly="true"/></td>
					<td id="e13"><form:input path="date15" readonly="true" style="width: 105px;"></form:input></td>
					<td><form:input style="width: 60px;" size="3" path="" value="${model.tenure15}" readonly="true"/></td>
					<td><form:input path="event15" cssStyle="color:${model.colour15};width: 587px;" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/sendsdocument.png" class="imagesize" alt="img"></img></td>
				</tr> 
				
				<tr>
					<td>16</td>
					<td> <form:input size="3" path="sixteen" onchange="valid14()" readonly="true"/></td>
					<td id="e13"><form:input path="date16" readonly="true" style="width: 105px;"></form:input></td>
					<td><form:input style="width: 60px;" size="3" path="" value="${model.tenure16}" readonly="true"/></td>
					<td><form:input path="event16" cssStyle="color:${model.colour16};width: 587px;" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/hands.png" class="imagesize" alt="img"></img></td>
				</tr> 
				

		</table>
		<div >${success}</div>
		
		
            
            </form:form>
         
        </div>
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
					
					<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
							<h3 align="center"><spring:message code="label.statisticalFormulas"/></h3>
				</div> 
			<div class="col-sm-12 col-md-12 col-lg-12"> 
			 <table class="host-list" align="center">
			<tr class="chargescolor">
	                     <th><spring:message code="label.SlNo"/>.</th>
	                     <th><spring:message code="label.average"/></th>
						<th><spring:message code="label.calculation"/></th>
					</tr>		
		
				<tr>
				    <td>1</td>
					<td><a href="#" data-toggle="tooltip" title="<spring:message code="label.meanInfo"/>"><spring:message code="label.mean"/></a> (&mu;)</td>
					<td><input type="text" name="" value="${model.mean}"  readonly="readonly" /></td>
					</tr>
					<tr>
				    <td>2</td>
					<td><a href="#" data-toggle="tooltip" title="<spring:message code="label.rangeInfo"/>"><spring:message code="label.range"/></a> (MR)</td>
					<td><input type="text" name="" value="${model.range}" readonly="readonly" /></td>
					</tr>
					<tr>
					<td>3</td>
					<td><a href="#" data-toggle="tooltip" title="<spring:message code="label.varianceInfo"/>"><spring:message code="label.variance"/></a> (&sigma;<sup>2</sup>)</td>
					<td><input type="text" name="" value="${model.variance}" readonly="readonly" /></td>
					</tr>
					
					<tr>
					<td>4</td>
					<td><a href="#" data-toggle="tooltip" title="<spring:message code="label.medianInfo"/>"><spring:message code="label.median"/></a> (x&#772;)</td>
					<td><input type="text" name="" value="${model.median}" readonly="readonly" /></td>
					</tr>
					<tr>
					<td>5</td>
					<td><a href="#" data-toggle="tooltip" title="<spring:message code="label.standardDeviationInfo"/>"><spring:message code="label.standardDeviation"/></a>(&radic;&sigma;<sup>2</sup>)</td>
					<td><input type="text" name="" value="${model.standardDeviation}" readonly="readonly" /></td>
					</tr>
					<tr>
					 <td>6</td>
					<td><a href="#" data-toggle="tooltip" title="<spring:message code="label.modeInfo"/>"><spring:message code="label.mode"/></a> (Mo)</td>
					<td><input type="text" name="" value="${model.mode}" readonly="readonly" /></td>
					</tr> 
					</table></div>
            <div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td><a href="sellerDiffViewAllEvents3" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
    </div>
<script type='text/javascript'>
window.onload = events();
function events() {

   var array=[];
   var videos=document.getElementById('eventsVideo');
   var eventVideos = document.getElementById('events');
   var eventCount = 1;
   
   for(var count=2;count<17;count++) {	   
	   var eventValue = 'event'+count;
	   if(document.getElementById(eventValue).value != '')
	   		array[count]= count;
   }
  
   
   videos.addEventListener('ended',myHandler,false);

   function myHandler(e) {	
      if(!e) {
         e = window.event; 
      }
      eventCount++;    
      //alert(eventCount);
      	if(eventCount == 3){      		
      		$(eventVideos).attr('src', "<%=request.getContextPath()%>/resources/videos/event5.mp4");
      	}else if(eventCount == 7 ){
      		$(eventVideos).attr('src', "<%=request.getContextPath()%>/resources/videos/event112.mp4");
      	}else if(eventCount == 9) {      		
      		$(eventVideos).attr('src', "<%=request.getContextPath()%>/resources/videos/event114.mp4");     	   
      	}else if(eventCount == 8){
      		$(eventVideos).attr('src', "<%=request.getContextPath()%>/resources/videos/event113.mp4");      		
      	} else if(eventCount == 14) {
      		$(eventVideos).attr('src', "<%=request.getContextPath()%>/resources/videos/event110.mp4");
      	} else if(eventCount == 10) {
      		$(eventVideos).attr('src', "<%=request.getContextPath()%>/resources/videos/event111.mp4");
      	}
      	else  if(eventCount > 10 && eventCount < 15){      		
      		$(eventVideos).attr('src', "<%=request.getContextPath()%>/resources/videos/ievent"+array[eventCount-2]+".mp4");      	
      	}else if(eventCount == 15){
      		$(eventVideos).attr('src', "<%=request.getContextPath()%>/resources/videos/ievent12.mp4");
      	}else if(eventCount == 16){
      		$(eventVideos).attr('src', "<%=request.getContextPath()%>/resources/videos/event11.mp4");
      	}      	
      	else{
      		$(eventVideos).attr('src', "<%=request.getContextPath()%>/resources/videos/ievent"+array[eventCount]+".mp4");      
      	}
    
      	 videos.load();
     	 videos.play();
      
   }
}

</script>

<style>
	input#first,input#second,input#third,input#fourth,input#fifth,input#six,input#seven,input#eight,input#nine,input#ten,input#elven,input#twelve,input#thirteen,input#fourteen,input#fifteen,input#sixteen {
    width: 52px;
}
</style>
<script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
});
</script>
<style>
	a{
		color:black;
	}
</style>
</body>