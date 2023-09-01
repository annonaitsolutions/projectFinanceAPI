<%@include file="taglib_includes.jsp" %>
<script>
  function validateForm() {
	
   /* var name=document.myForm.customerName.value;
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
<div class="col-md-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<h3><spring:message code="label.eventsTable"/></h3>
			</div>
				<form:form name="myForm" action="updatediffrentBankEvent" method="post" commandName="buyerdiffBankForm" onsubmit="return validateForm();">	
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
				<table class="table-bordered theme">
					
					<form:hidden path="id"/>
					<form:hidden path="uniqueId"/>
					<form:hidden path="transactionId"/>
				<thead>
			
				<tr>
					<th><spring:message code="label.stepNumber"/></th>
					<th><spring:message code="label.dayNumber"/></th>
					<th><spring:message code="label.date"/></th>
					<th><spring:message code="label.event"/></th>
					<th></th>
				</tr>
			</thead>	
				 	<tr>
						<td>1</td>
						<td><form:input path="first" size="3" readonly="true" style="width: 60px;"/>
						</td>
						<td><form:input type="text" path="date1" name="date1" readonly="true" style="width: 111px;"/>
						</td>
						<td><form:input size="40" style="width:600px;" path="event1" readonly="true" />
						</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/hands.png" class="imagesize" alt="img"></img></td>
						
					</tr> 
					<tr>
						<td>2</td>
						<td><form:input path="second" name="second" size="3" onchange="valid()" readonly="true" style="width: 60px;"/>
						 </td>
						<td><form:input path="date2" name="date2" readonly="true" style="width: 111px;"/>
						</td>
						<td><form:input size="40" style="width:600px;" path="event2" readonly="true"  />
						</td>
				<td><img src="<%=request.getContextPath()%>/resources/images/sending.png" class="imagesize" alt="img"></img></td>
						
					</tr>
					<tr >
						<td>3</td>
						<td><form:input path="third" size="3" onchange="valid1()" readonly="true" style="width: 60px;"/>
						</td>
						<td><form:input path="date3" readonly="true" style="width: 111px;"/>
						</td>
						<td><form:input size="40" style="width:600px;" path="event3" readonly="true"/>
						</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/letterOfCredit.png" class="imagesize" alt="img"></img></td>
						
					</tr>
					<tr>
						<td>4</td>
						<td><form:input path="fourth" size="3" onchange="valid2()" readonly="true" style="width: 60px;"/>
						</td>
						<td><form:input path="date4" readonly="true" style="width: 111px;"/>
						</td>
						<td><form:input size="40" style="width:600px;" path="event4" readonly="true"/>
						</td>
						<td><img src="<%=request.getContextPath()%>/resources/images/truckSendGoods.png" class="imagesize" alt="img"></img></td>
						
					</tr>
					<tr >
						<td>5</td>
						<td><form:input path="fifth" size="3" onchange="valid3()" readonly="true" style="width: 60px;"/>
						</td>
						<td><form:input path="date5" readonly="true" style="width: 111px;"/>
						</td>
						<td><form:input size="40" style="width:600px;" path="event5" readonly="true"/>
						</td>
			<td><img src="<%=request.getContextPath()%>/resources/images/sendsdocument.png" class="imagesize" alt="img"></img></td>
						
					</tr>
					 <tr>
						<td>6</td>
						<td><form:input path="six" size="3" onchange="valid4()" readonly="true" style="width: 60px;"/>
						 </td>
						<td><form:input path="date6" readonly="true" style="width: 111px;"/>
						</td>
						<td><form:input size="40" style="width:600px;" path="event6" readonly="true"/>
						</td>
				<td><img src="<%=request.getContextPath()%>/resources/images/phone.png" class="imagesize" alt="img"></img></td></tr>
				<tr >
						<td>7</td>
						<td><form:input path="seven" size="3" onchange="valid5()" readonly="true" style="width: 60px;"/>
						</td>
						<td><form:input path="date7" readonly="true" style="width: 111px;"/>
						</td>
						<td><form:input size="40" style="width:600px;" path="event7" readonly="true" />
						</td>
			<td><img src="<%=request.getContextPath()%>/resources/images/bankToBank.png" class="imagesize" alt="img"></img></td>
						
					</tr>
				 <tr>
						<td>8</td>
						<td><form:input path="eight" size="3" readonly="true" style="width: 60px;"/>
						</td>
						<td><form:input path="date8" readonly="true" style="width: 111px;"/>
						</td>
						<td><form:input size="40" style="width:600px;" path="event8" readonly="true"/>
						</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankIntimates.png" class="imagesize" alt="img"></img></td>
						
					</tr>
					<tr >
						<td>9</td>
						<td><form:input path="nine" size="3" readonly="true" style="width: 60px;"/>
						</td>
						<td><form:input path="date9" readonly="true" style="width: 111px;"/>
						</td>
						<td><form:input size="40" style="width:600px;" path="event9" readonly="true" />
						</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/accepted.png" class="imagesize" alt="img"></img></td>
						
					</tr>
					<tr>
						<td>10</td>
						<td><form:input path="ten" size="3"  readonly="true" style="width: 60px;"/>
						</td>
						<td><form:input path="date10" readonly="true" style="width: 111px;"/>
						 </td>
						<td><form:input size="40" style="width:600px;" path="event10" readonly="true" />
						</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankToBankMoney.png" class="imagesize" alt="img"></img></td>
						
					</tr> 
					<tr>
						<td>11</td>
						<td><form:input path="elven" size="3"  readonly="true" style="width: 60px;"/>
						 </td>
						<td><form:input path="date11" readonly="true" style="width: 111px;"/>
						</td>
						<td><form:input size="40" style="width:600px;" path="event11" readonly="true" />
						</td>
						<td><img src="<%=request.getContextPath()%>/resources/images/bankToCustomerMoney.png" class="imagesize" alt="img"></img></td>
						
					</tr> 
					
					<tr>
						<td>12</td>
						<td><form:input path="twelve" size="3"  readonly="true" style="width: 60px;"/>
						</td>
						<td><form:input path="date12" readonly="true" style="width: 111px;"/>
						</td>
						<td><form:input size="40" style="width:600px;" path="event12" readonly="true" />
						</td>
						<td><img src="<%=request.getContextPath()%>/resources/images/goodsRecevied.png" class="imagesize" alt="img"></img></td>
						
					</tr> 
					<tr>
						<td>13</td>
						<td><form:input path="thirteen" size="3"  readonly="true" style="width: 60px;"/>
						</td>
						<td><form:input path="date13" readonly="true" style="width: 111px;"/>
						</td>
						<td><form:input size="40" style="width:600px;" path="event13" readonly="true" />
						</td>
						<td><img src="<%=request.getContextPath()%>/resources/images/hands.png" class="imagesize" alt="img"></img></td>
						
					</tr>  
					
					
					</table>
					
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
					<div class="col-sm-12 col-md-12 col-lg-12">&nbsp;</div>
					<div class="col-sm-12 col-md-12 col-lg-12">
						<table align="center">
						 
						<tr >
							<td><spring:message code="label.status"/> :</td>
							<td><form:input path="status" readonly="true"/>
							</td>
						</tr> 
						<tr>
							<td><spring:message code="label.comment"/> :</td>
							<td><form:input path="comment" readonly="true"/></td>
						</tr>
						</table>
					</div>
					<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.update"/>" class="btn btn-primary"></td>
							<td><a href="buyerDiffrentBankPage" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
		</form:form>
</div>
<style>
input#event1,input#event2,input#event3,input#event4,input#event5,input#event6,input#event7,input#event8,input#event9,input#event10,input#event11,input#event12,input#event13 {
    width: 400px;
}
td {
    text-align: center;
}
</style>

	