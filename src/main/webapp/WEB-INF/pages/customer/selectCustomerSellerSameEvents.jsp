<%@include file="taglib_includes.jsp" %>
 <div class="container">
    <div class="row">
        <div class="col-md-12">
            <h3 align="center"><spring:message code="label.sellerSameBankDetails"/></h3> 
            <form:form action="confirmSelectCustomerSellerSameBankEvents" method="post" commandName="sellerSameBankEventForm">  
            <label><spring:message code="label.cost"/>:</label><form:input path="cost" readonly="true"/> 
<form:hidden path="customerName"></form:hidden>
<form:hidden path="buyer"></form:hidden>
<form:hidden path="buyerBank"></form:hidden>
<form:hidden path="masterKey"></form:hidden>
<form:hidden path="goods"></form:hidden>
<form:hidden path="id"></form:hidden>
<form:hidden path="status"></form:hidden>
<form:hidden path="customerCol"></form:hidden>
<form:hidden path="comment"></form:hidden>
<form:hidden path="bankReq"></form:hidden>
<form:hidden path="transactionId"></form:hidden>
		<table class="table-bordered" align="center">
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
					<td id="e1"><form:input id="datepicker" path="date1" readonly="true"/>
					
					</td>
				
					<td><form:input path="event1" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/Contract.jpg" class="imagesize" alt="img"></img></td>
				</tr>
			     <tr>
					<td>2</td>
					<td><form:input size="3"  path="second" readonly="true"/></td> 
					<td id="e2"><form:input path="date2" id="date2" readonly="true"></form:input></td>
					<td><form:input path="event2" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>3</td>
					<td><form:input size="3"  path="third" readonly="true"/></td>
					<td id="e3"><form:input path="date3" id="date3" readonly="true"></form:input></td>
					<td><form:input path="event3" readonly="true"/></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>4</td>
					<td> <form:input size="3"  path="fourth" readonly="true"/> </td>
					<td id="e4"><form:input path="date4" id="date4" readonly="true"></form:input></td>
					<td><form:input path="event4" readonly="true"/></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>5</td>
					<td> <form:input size="3" path="fifth" readonly="true"/></td>
					<td id="e5"><form:input path="date5" id="date5" readonly="true"></form:input></td>
					<td><form:input path="event5" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>6</td>
					<td> <form:input size="3" path="six" readonly="true"/></td>
					<td id="e6"><form:input path="date6" id="date6" readonly="true"></form:input></td>
					<td><form:input path="event6" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>7</td>
					<td><form:input size="3" path="seven" readonly="true"/> </td>
					<td id="e7"><form:input path="date7" id="date7" readonly="true"></form:input></td>
					<td><form:input path="event7" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>8</td>
					<td><form:input size="3" path="eight" readonly="true"/></td>
					<td id="e8"><form:input path="date8" id="date8" readonly="true"></form:input></td>
					<td><form:input path="event8" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/2.jpg" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>9</td>
					<td><form:input size="3" path="nine" readonly="true"/></td>
					<td id="e9"><form:input path="date9" id="date9" readonly="true"></form:input></td>
					<td><form:input path="event9" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>10</td>
					<td> <form:input size="3" path="ten" readonly="true"/> </td>
					<td id="e10"><form:input path="date10" id="date10" readonly="true"></form:input></td>
					<td><form:input path="event10" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr>  
				<tr>
					<td>11</td>
					<td> <form:input size="3" path="elven" readonly="true"/></td>
					<td id="e10"><form:input path="date11" id="date11" readonly="true"></form:input></td>
					<td><form:input path="event11" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr> 
				<tr>
					<td>12</td>
					<td> <form:input size="3" path="twelve" readonly="true"/> </td>
					<td id="e10"><form:input  path="date12" id="date12" readonly="true"></form:input></td>
					<td><form:input path="event12" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr>  
				<tr>
					<td>13</td>
					<td> <form:input size="3" path="thirteen" readonly="true"/></td>
					<td id="e10"><form:input path="date13" id="date13" readonly="true"></form:input></td>
					<td><form:input path="event13" readonly="true"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/dummy.png" class="imagesize" alt="img"></img></td>
				</tr>  
				
		</table>
		
		<table class="table-bordered" align="center">
					 
					<tr>
						<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">*</font><spring:message code="label.t&c"/>
						<select name="customerStatus" style="margin-left:101px; width:171px;">
						<option style="color:green" value="Yes"><spring:message code="label.yes"/></option>
						<option style="color:red" value="No"><spring:message code="label.no"/></option>
						</select>
						</td>
						<td></td>
					</tr> 
					
					<tr>
						<td colspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="label.comment"/>
						<textarea name="customerComment" rows="5" cols="20" style="margin-left:219px;   width: 169px;color:red"></textarea>
						  </td>
						  
						
					</tr></table>
		
		<table align="center">
			<tr>
				<td><input type="submit" size="3" value="Update" class="btn btn-primary"></td>
				<td><button type="button" name="Back"  onclick="history.back()" class="btn btn-success">Back</button></td>
			</tr>
		</table>
		
            
            </form:form>
         
        </div>
    </div>
</div>


</body>