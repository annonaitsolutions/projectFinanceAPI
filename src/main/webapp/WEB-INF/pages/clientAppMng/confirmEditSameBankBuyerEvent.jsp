<%@include file="taglib_includes.jsp" %>
<script>
  function validateForm() {
	
  /*  var name=document.myForm.customerName.value;
    var d = new Date().getTime();
	var uuid = name+'-xxxxxx'.replace(/[xy]/g, function(c) {
		var r = (d + Math.random() * 16) % 16 | 0;
		d = Math.floor(d / 16);
		return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
	});
	document.myForm.uniqueId.value = uuid;
	
	var d1 = new Date().getTime();
	var uuid1 = 'xxxxxx'.replace(/[xy]/g, function(c) {
	    var r1 = (d1 + Math.random()*16)%16 | 0;
	    d1 = Math.floor(d1/16);
	    return (c=='x' ? r1 : (r&0x3|0x8)).toString(16);
	});
	document.myForm.transactionId.value=uuid1; */
 
	}
  </script>
 <div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
			 <div class="col-sm-12 col-md-12 col-lg-12 header_customer">
     	  		  <h3 style="text-align:center;"><spring:message code="label.buyerSameBankDetails"/></h3> 
       		 </div>
            <form:form action="updatesameBankEvent" name="myForm" onsubmit="return validateForm();" commandName="buyersameBankForm">
             <div class="col-sm-12 col-md-12 col-lg-12"> 
              <table align="center">
				<tr>
					<td  class="col-sm-5"><b><spring:message code="label.customerName"/>:</b></td>
					<td  class="col-sm-7"><form:input id="customerName" path="customerName"  readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.supplierName"/>:</b></td>
					<td class="col-sm-7"><form:input id="supplier" path="supplier" readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.supplierBank"/>:</b></td>
					<td class="col-sm-7"><form:input id="supplierBank" path="supplierBank" readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.masterKey"/>:</b></td>
					<td  class="col-sm-7"><form:input id="masterKey" path="masterKey" readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.goods"/>:</b></td>
					<td class="col-sm-7"><form:input id="goods" path="goods" readonly="true"></form:input></td>
				</tr>
				
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.sanctionedAmt"/></b></td>
					<td class="col-sm-7"><form:input id="sanctionedAmount" path="sanctionedAmount" readonly="true"></form:input>
					
			</td>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.utilizedAmt"/></b></td>
					<td class="col-sm-7"><form:input id="utilizedAmount" path="utilizedAmount" readonly="true"></form:input>
					
			</td>
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.availableAmt"/></b></td>
					<td class="col-sm-7"><form:input id="availableCost" path="availableCost" readonly="true"></form:input>
					
			</td>
				</tr>
			</table>
           	<form:hidden path="id"></form:hidden>
			<form:hidden path="transactionId"></form:hidden>
				 <form:hidden path="uniqueId"></form:hidden>
          </div>
          <div class="col-sm-12 col-md-12 col-lg-12">
			<table class="table-bordered theme">
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
					<td align="center">1</td>
					<td><form:input  value="1" style="width: 60px;" size="3" path="first" readonly="true"/></td>
					<td id="e1"><form:input path="date1" readonly="true" style="width: 111px;"></form:input></td>
					
					<td><form:input path="event1" style="width:600px;" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/hands.png" class="imagesize" alt="img"></img></td>
				</tr>
			      <tr>
					<td align="center">2</td>
					<td><form:input size="3" style="width: 60px;"  path="second" onchange="valid()" readonly="true"/></td> 
					<td id="e2"><form:input path="date2" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:input path="event2" readonly="true" style="width:600px;"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/sending.png" class="imagesize" alt="img"></img></td>
				</tr>
			   <tr>
					<td align="center">3</td>
					<td><form:input size="3" style="width: 60px;"   path="third" onchange="valid1()" readonly="true"/></td>
					<td id="e3"><form:input path="date3" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:input path="event3" readonly="true" style="width:600px;"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/letterOfCredit.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td align="center">4</td>
					<td> <form:input size="3" style="width: 60px;"  path="fourth" onchange="valid2()" readonly="true"/> </td>
					<td id="e4"><form:input path="date4" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:input path="event4" readonly="true" style="width:600px;"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/truckSendGoods.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td align="center">5</td>
					<td> <form:input size="3" style="width: 60px;" path="fifth" onchange="valid3()" readonly="true"/></td>
					<td id="e5"><form:input path="date5" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:input path="event5" readonly="true" style="width:600px;"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/sendsdocument.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td align="center">6</td>
					<td> <form:input size="3" style="width: 60px;" path="six" onchange="valid4()" readonly="true"/></td>
					<td id="e6"><form:input path="date6" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:input path="event6" readonly="true" style="width:600px;"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/phone.png" class="imagesize" alt="img"></img></td>
				</tr>
				 <tr>
					<td align="center">7</td>
					<td><form:input size="3" style="width: 60px;" path="seven" onchange="valid5()" readonly="true"/> </td>
					<td id="e7"><form:input path="date7" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:input path="event7" readonly="true" style="width:600px;"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankIntimates.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td align="center">8</td>
					<td><form:input size="3" style="width: 60px;" path="eight" onchange="valid6()" readonly="true"/></td>
					<td id="e8"><form:input path="date8" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:input path="event8" readonly="true" style="width:600px;"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/accepted.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td align="center">9</td>
					<td><form:input size="3" style="width: 60px;"  path="nine" onchange="valid7()" readonly="true"/></td>
					<td id="e9"><form:input path="date9" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:input path="event9" readonly="true" style="width:600px;"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankToCustomerMoney.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td align="center">10</td>
					<td> <form:input size="3" style="width: 60px;"  path="ten" onchange="valid8()" readonly="true"/> </td>
					<td id="e10"><form:input path="date10" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:input path="event10" readonly="true" style="width:600px;"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/goodsRecevied.png " class="imagesize" alt="img"></img></td>
				</tr>  
				<tr>
					<td align="center">11</td>
					<td> <form:input size="3" style="width: 60px;" path="elven" onchange="valid9()" readonly="true"/></td>
					<td id="e10"><form:input path="date11" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:input path="event11" readonly="true" style="width:600px;"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/hands.png" class="imagesize" alt="img"></img></td>
				</tr> 
				</tbody>
		</table>
		</div>
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
		<div class="col-sm-12 col-md-12">&nbsp;</div>
			<div class="col-sm-12 col-md-12">
							<table align="center" width="400">
					<tr>
						<td><spring:message code="label.status"/> :</td>
						<td>
							<form:input path="status" readonly="true"/>
						</td>
					</tr>
					<tr>
						<td><spring:message code="label.comment"/> :</td>
						<td><form:textarea path="comment" rows="5" cols="20" readonly="true"/></td>
					</tr>
					<tr>	
						<td>
						<form:errors path="comment" cssStyle="color:red"></form:errors>  </td>	
					</tr>
				</table>
			</div>
		<div class="col-sm-11">${success}</div>
		<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.update"/>" class="btn btn-primary"></td>
							<td><a href="sameBankEventPage" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
            
        </form:form>
         
        </div>
<script>
    $(document).ready( function () {
        $('#bootstrap-table').bdt();
    });
</script>