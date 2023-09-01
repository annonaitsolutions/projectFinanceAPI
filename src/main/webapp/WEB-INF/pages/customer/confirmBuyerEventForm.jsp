<%@include file="taglib_includes.jsp" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
function validateForm()
{
var d = new Date().getTime();
/* var uuid = 'xxxxxx'.replace(/[xy]/g, function(c) {
    var r = (d + Math.random()*16)%16 | 0;
    d = Math.floor(d/16);
    return (c=='x' ? r : (r&0x3|0x8)).toString(16);
});
document.buyerSame.transactionId.value=uuid; */

var canSubmit = true;

if (document.getElementById('description').value == ''){
		document.getElementById('descriptionError').style.display='block';
		canSubmit = false;
	}
	else{
		document.getElementById('descriptionError').style.display='none';
	}
	
if (document.getElementById('docName').value == ''){
		document.getElementById('docNameError').style.display='block';
		canSubmit = false;
	}
	else{
		document.getElementById('docNameError').style.display='none';
	}
	
if (canSubmit == false) {
	return false;
}
}

function addRow(tableID) {
	 
    var table = document.getElementById(tableID);
   
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);

    var colCount = table.rows[1].cells.length;

    for(var i=0; i<colCount; i++) {
        var newcell = row.insertCell(i);                
        
        newcell.innerHTML = table.rows[1].cells[i].innerHTML;                
        switch(newcell.childNodes[1].type) {
            case "text":
                    newcell.childNodes[1].value = "";
                    break;                   
            case "select-one":
                    newcell.childNodes[1].selectedIndex = 0;
                    break;
        }
    }
}


function deleteRow(tableID)
{
try
     {
    var table = document.getElementById(tableID);
    var rowCount = table.rows.length;
   
                if (rowCount > 2) {                        
                	 table.deleteRow(rowCount-1);
                }else {
                	alert("Cannot delete all the rows");	
                }
            
        } catch(e)
            {
            alert(e);
    }          
}



</script>
<div class="col-sm-9 col-sm-9 col-lg-9 body_fixed">
			<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
          		 <h3 style="text-align:center"><spring:message code="label.confirmationScreen"/></h3> 
           </div>
            <form:form name="buyerSame" action="insertBSameEvents" method="post" commandName="buyerSameBankEventForm" onsubmit="return validateForm()"> 
          <table align="center">
				<tr>
					<td  class="col-sm-5"><b><spring:message code="label.customerName"/></b></td>
					<td  class="col-sm-7"><form:input id="customerName" path="customerName"  readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.supplierName"/></b></td>
					<td class="col-sm-7"><form:input id="supplier" path="supplier" readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.supplierBank"/></b></td>
					<td class="col-sm-7"><form:input id="supplierBank" path="supplierBank" readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.masterKey"/></b></td>
					<td  class="col-sm-7"><form:input id="masterKey" path="masterKey" readonly="true"></form:input></td>
				</tr>
				<tr>
					<td class="col-sm-5"><b><spring:message code="label.goods"/></b></td>
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
<form:hidden path="poKey"></form:hidden>
<form:hidden path="transactionId"></form:hidden>

		<table class="table-bordered theme">
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
					<td><form:input  value="1" size="3" style="width: 60px;" path="first" readonly="true"/></td>
					<td id="e1"><form:input path="date1" id="datepicker" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:input path="event1" style="width: 575px;" readonly="true"/></td>
					<td><img src="<%=request.getContextPath()%>/resources/images/hands.png" class="imagesize" alt="img"></img></td>
				</tr>
			      <tr>
					<td>2</td>
					<td><form:input size="3" style="width: 60px;"  path="second" readonly="true"/></td> 
					<td id="e2"><form:input path="date2" id="date2" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:input path="event2" readonly="true" style="width: 575px;"/>
					</td>
				<td><img src="<%=request.getContextPath()%>/resources/images/sending.png" class="imagesize" alt="img"></img></td> 
				</tr>
			   <tr>
					<td>3</td>
					<td><form:input size="3" style="width: 60px;" path="third" readonly="true"/></td>
					<td id="e3"><form:input path="date3" id="date3" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:input path="event3" readonly="true" style="width: 575px;"/>
					</td>
			<td><img src="<%=request.getContextPath()%>/resources/images/letterOfCredit.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>4</td>
					<td> <form:input size="3" style="width: 60px;" path="fourth" readonly="true"/> </td>
					<td id="e4"><form:input path="date4" id="date4" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:input path="event4" readonly="true" style="width: 575px;"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/truckSendGoods.png" class="imagesize" alt="img"></img></td>
				</tr>
				<tr>
					<td>5</td>
					<td> <form:input size="3" style="width: 60px;" path="fifth" readonly="true"/></td>
					<td id="e5"><form:input path="date5" id="date5" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:input path="event5" readonly="true" style="width: 575px;"/>
					</td>
				<td><img src="<%=request.getContextPath()%>/resources/images/sendsdocument.png" class="imagesize" alt="img"></img></td> 
				</tr>
				<tr>
					<td>6</td>
					<td> <form:input size="3" style="width: 60px;" path="six" readonly="true"/></td>
					<td id="e6"><form:input path="date6" id="date6" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:input path="event6" readonly="true" style="width: 575px;"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/phone.png" class="imagesize" alt="img"></img></td> 
				</tr>
				 <tr>
					<td>7</td>
					<td><form:input size="3" style="width: 60px;" path="seven" readonly="true"/> </td>
					<td id="e7"><form:input path="date7" id="date7" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:input path="event7" readonly="true" style="width: 575px;"/>
					</td>
				<td><img src="<%=request.getContextPath()%>/resources/images/bankIntimates.png" class="imagesize" alt="img"></img></td> 
				</tr>
				<tr>
					<td>8</td>
					<td><form:input size="3" style="width: 60px;" path="eight" readonly="true"/></td>
					<td id="e8"><form:input path="date8" id="date8" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:input path="event8" readonly="true" style="width: 575px;"/>
					</td>
			<td><img src="<%=request.getContextPath()%>/resources/images/accepted.png" class="imagesize" alt="img"></img></td> 
				</tr>
				<tr>
					<td>9</td>
					<td><form:input size="3"  style="width: 60px;" path="nine" readonly="true"/></td>
					<td id="e9"><form:input path="date9" id="date9" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:input path="event9" readonly="true" style="width: 575px;"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/bankToCustomerMoney.png" class="imagesize" alt="img"></img></td> 
				</tr>
				<tr>
					<td>10</td>
					<td> <form:input size="3" style="width: 60px;" path="ten" readonly="true"/> </td>
					<td id="e10"><form:input path="date10" id="date10" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:input path="event10" readonly="true" style="width: 575px;"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/goodsRecevied.png" class="imagesize" alt="img"></img></td> 
				</tr>  
				<tr>
					<td>11</td>
					<td> <form:input size="3" style="width: 60px;" path="elven" readonly="true"/></td>
					<td id="e10"><form:input path="date11" id="date11" readonly="true" style="width: 111px;"></form:input></td>
					<td><form:input path="event11" readonly="true" style="width: 575px;"/>
					</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/hands.png" class="imagesize" alt="img"></img></td> 
				</tr> 
				
		</table>
		
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
							<h3 align="center"><spring:message code="label.preplanDocsToBeSubmitted"/></h3>
				</div> 
			<div class="col-sm-12 col-md-12 col-lg-12"> 
			 <table class="host-list" align="center" id="dataTable">

					<tr class="chargescolor">
	                     
	                     <th><spring:message code="label.docsName"/></th>
						<th><spring:message code="label.description"/></th>
					</tr>		
					<tr>
					<td>
							<form:input path="docName" id="docName"></form:input>	
							<div id="docNameError" style="display:none;color:red;"><spring:message code="label.validation"/></div> 
						</td>
					
						<td>
							<form:input path="description" id="description"></form:input>
							<div id="descriptionError" style="display:none;color:red;"><spring:message code="label.validation"/></div> 
						</td>
						
						
			</table>
	</div>
	<div class="col-sm-12 col-md-12 col-lg-12" align="center">
			<a class="btn btn-default"  onclick="addRow('dataTable')" value="Add Document"
			 style="color:#2E9AFE;font-weight: 600;"><spring:message code="label.addDocs"/></a>
			<a  class="btn btn-default"  onclick="deleteRow('dataTable')" value="Remove Document"
			 style="color:#2E9AFE;font-weight: 600;"><spring:message code="label.removeDocs"/></a>
		</div>
		<div class="col-sm-12 col-md-12"> &nbsp;</div>
			<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="newEvent" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
            
            </form:form>
         
        </div>
