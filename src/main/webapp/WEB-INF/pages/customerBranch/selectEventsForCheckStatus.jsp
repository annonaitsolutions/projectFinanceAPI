<%@include file="taglib_includes.jsp" %>
<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12  header_customer">
          	  <h3 style="text-align:center"><spring:message code="label.buyerSameBankDetailsStatus"/></h3> 
            </div>
            <form:form action="" commandName="buyerSameBankEventForm">
            <div class="col-sm-6 col-md-6 col-lg-6">
            <table align="center">
				<tr>
					<td  class="col-sm-6"><b><spring:message code="label.customerName"/>:</b></td>
					<td  class="col-sm-6"><form:input id="customerName" path="customerName"  readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.supplierName"/>:</b></td>
					<td class="col-sm-6"><form:input id="supplier" path="supplier" readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.supplierBank"/>:</b></td>
					<td class="col-sm-6"><form:input id="supplierBank" path="supplierBank" readonly="true"></form:input></td>
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
					<td class="col-sm-6"><b><spring:message code="label.sanctionedAmt"/></b></td>
					<td class="col-sm-6"><form:input id="sanctionedAmount" path="sanctionedAmount" readonly="true"></form:input>
					
			</td>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.utilizedAmt"/></b></td>
					<td class="col-sm-6"><form:input id="utilizedAmount" path="utilizedAmount" readonly="true"></form:input>
					
			</td>
				</tr>
				<tr>
					<td class="col-sm-6"><b><spring:message code="label.availableAmt"/></b></td>
					<td class="col-sm-6"><form:input id="availableCost" path="availableCost" readonly="true"></form:input>
					
			</td>
			</table>
			</div>
			<div class="col-sm-3 col-md-3 col-lg-3">

		<video id="eventsVideo" width="400" height="300" autoplay>
			<source	src="<%=request.getContextPath()%>/resources/videos/event1.mp4"
				type="video/mp4" id="events">
		</video>
	</div>
		<table class="table-bordered theme">
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
					<td><form:input  value="1" style="width: 60px;" size="3" path="first" readonly="true"/></td>
					<td id="e1"><form:input path="date1" readonly="true" style="width: 97px;"></form:input></td>
					<td><form:input  value="1" style="width: 60px;" size="3" path="" readonly="true"/></td>
					<td><form:input path="event1" cssStyle="color:${model.colour1};width: 590px;" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/hands.png" class="imagesize" alt="img"></img></td>
				</tr>
			      <tr>
					<td>2</td>
					<td><form:input size="3" style="width: 60px;"  path="second" onchange="valid()" readonly="true"/></td> 
					<td id="e2"><form:input path="date2" readonly="true" style="width: 97px;"></form:input></td>
					<td><form:input style="width: 60px;" size="3" path="" value="${model.tenure2}" readonly="true"/></td>
					<td><form:input path="event2" cssStyle="color:${model.colour2};width: 590px;" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/sending.png" class="imagesize" alt="img"></img></td>
				</tr>
			   <tr>
					<td>3</td>
					<td><form:input size="3" style="width: 60px;" path="third" onchange="valid1()" readonly="true"/></td>
					<td id="e3"><form:input path="date3" readonly="true" style="width: 97px;"></form:input></td>
					<td><form:input style="width: 60px;" size="3" path="" value="${model.tenure3}" readonly="true"/></td>
					<td><form:input path="event3" cssStyle="color:${model.colour3};width: 590px;" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/letterOfCredit.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>4</td>
					<td> <form:input size="3" style="width: 60px;" path="fourth" onchange="valid2()" readonly="true"/> </td>
					<td id="e4"><form:input path="date4" readonly="true" style="width: 97px;"></form:input></td>
					<td><form:input style="width: 60px;" size="3" path="" value="${model.tenure4}" readonly="true"/></td>
					<td><form:input path="event4" cssStyle="color:${model.colour4};width: 590px;" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/truckSendGoods.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>5</td>
					<td> <form:input size="3" style="width: 60px;" path="fifth" onchange="valid3()" readonly="true"/></td>
					<td id="e5"><form:input path="date5" readonly="true" style="width: 97px;"></form:input></td>
					<td><form:input style="width: 60px;" size="3" path="" value="${model.tenure5}" readonly="true"/></td>
					<td><form:input path="event5" cssStyle="color:${model.colour5};width: 590px;" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/sendsdocument.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>6</td>
					<td> <form:input size="3" style="width: 60px;" path="six" onchange="valid4()" readonly="true"/></td>
					<td id="e6"><form:input path="date6" readonly="true" style="width: 97px;"></form:input></td>
					<td><form:input style="width: 60px;" size="3" path="" value="${model.tenure6}" readonly="true"/></td>
					<td><%-- <form:input path="event6" cssStyle="color:${model.colour6};width: 590px;" readonly="true"/> --%>
					<form:textarea path="event6" cssStyle="color:${model.colour6};width: 590px;" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/phone.png" class="imagesize" alt="img"></img></td>
				</tr>
				 <tr>
					<td>7</td>
					<td><form:input size="3" style="width: 60px;" path="seven" onchange="valid5()" readonly="true"/> </td>
					<td id="e7"><form:input path="date7" readonly="true" style="width: 97px;"></form:input></td>
					<td><form:input style="width: 60px;" size="3" path="" value="${model.tenure7}" readonly="true"/></td>
					<td><form:input path="event7" cssStyle="color:${model.colour7};width: 590px;" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankIntimates.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>8</td>
					<td><form:input size="3" style="width: 60px;" path="eight" onchange="valid6()" readonly="true"/></td>
					<td id="e8"><form:input path="date8" readonly="true" style="width: 97px;"></form:input></td>
					<td><form:input style="width: 60px;" size="3" path="" value="${model.tenure8}" readonly="true"/></td>
					<td><form:input path="event8" cssStyle="color:${model.colour8};width: 590px;" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/accepted.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>9</td>
					<td><form:input size="3" style="width: 60px;" path="nine" onchange="valid7()" readonly="true"/></td>
					<td id="e9"><form:input path="date9" readonly="true" style="width: 97px;"></form:input></td>
					<td><form:input style="width: 60px;" size="3" path="" value="${model.tenure9}" readonly="true"/></td>
					<td><form:input path="event9" cssStyle="color:${model.colour9};width: 590px;" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankToCustomerMoney.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>10</td>
					<td> <form:input size="3" style="width: 60px;" path="ten" onchange="valid8()" readonly="true"/> </td>
					<td id="e10"><form:input path="date10" readonly="true" style="width: 97px;"></form:input></td>
					<td><form:input style="width: 60px;" size="3" path="" value="${model.tenure10}" readonly="true"/></td>
					<td><form:input path="event10" cssStyle="color:${model.colour10};width: 590px;" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/goodsRecevied.png" class="imagesize" alt="img"></img></td>
				</tr>  
				<tr>
					<td>11</td>
					<td> <form:input size="3" style="width: 60px;" path="elven" onchange="valid9()" readonly="true"/></td>
					<td id="e10"><form:input path="date11" readonly="true" style="width: 97px;"></form:input></td>
					<td><form:input style="width: 60px;" size="3" path="" value="${model.tenure11}" readonly="true"/></td>
					<td><form:input path="event11" cssStyle="color:${model.colour11};width: 590px;" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/hands.png" class="imagesize" alt="img"></img></td>
				</tr> 
				
		</table>
		     </form:form>
		
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
							<h3 align="center"><spring:message code="label.prePlanDocs"/>.</h3>
				</div> 
			<div class="col-sm-12 col-md-12 col-lg-12"> 
			 <table class="host-list" align="center">
			<tr class="chargescolor">
	                     
	                     <th><spring:message code="label.docsName"/></th>
						<th><spring:message code="label.description"/></th>
					</tr>		
		<c:forEach items="${model.purchaseDocForms}" var="status">
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
							<td><a href="viewAllEvents3" class="btn btn-success"><spring:message code="label.back"/></a></td>
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
   
   for(var count=2;count<12;count++) {
	   
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
      $(eventVideos).attr('src', "<%=request.getContextPath()%>/resources/videos/event"+array[eventCount]+".mp4");
      videos.load();
      videos.play();      
   }
}

</script>
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