 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="<%=request.getContextPath()%>/resources/js/highcharts.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/exporting.js"></script>
<script type="text/javascript">

function validateForm() {		
	
	var bankName = document.getElementsByName("bankName");
	var status = document.getElementsByName("status");
	var bankBranch = document.getElementsByName("bankBranch");
	var location = document.getElementsByName("location");
	var limit = document.getElementsByName("limit");
	
	 
	var canSubmit = true;
	
	var table=document.getElementById("dataTable");
	var totalCost = 0.0;

    
   

	for (var i = 0; i < bankName.length; i++) {
		
				if (bankName[i].value == '') {
					document.getElementById('bankNameError').style.display = 'block';
					canSubmit = false; 
				} else {
					document.getElementById('bankNameError').style.display = 'none';
				}
			 
				if(status[i].value == '') {
					document.getElementById('statusError').style.display = 'block';
					canSubmit = false;					
				}else {
					document.getElementById('statusError').style.display = 'none';
				}
			 
				if(bankBranch[i].value == '') {
					document.getElementById('bankBranchError').style.display = 'block';
					canSubmit = false;					
				}else {
					document.getElementById('bankBranchError').style.display = 'none';
				}
				
				if(location[i].value == '') {
					document.getElementById('locationError').style.display = 'block';
					canSubmit = false;					
				}else {
					document.getElementById('locationError').style.display = 'none';
				}
				
				if(limit[i].value.match(/(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/) || limit[i].value.match(/^-?[0-9]+$/) &&!(limit[i].value == ''))
			  	{
			  		
			  		document.getElementById('limitError').style.display='none';
			  	
			  	}
			  	else{
			  		document.getElementById('limitError').style.display='block';
			  	     canSubmit = false;
			  	}
			
				
				
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
 <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
   <form:form action="masterPlanConsBankPost" name="msplan" onsubmit="return validateForm()" method="post" commandName="consortiumForm">
	<div class="col-sm-6 col-md-6">
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
	<h3 align="center"><spring:message code="label.ConBankDetails"/></h3>
	</div>
			<div class="col-sm-12 col-md-12 col-lg-12"> 
			 <table class="host-list" id="dataTable">
			 <form:hidden path="masterKey"/>
              <form:hidden path ="transactionId"/>
					<tr class="chargescolor">
	                     
	                     <th><spring:message code="label.status"/></th>
						<th><spring:message code="label.bankName"/></th>
						<th><spring:message code="label.branch"/></th>
						<th><spring:message code="label.location"/></th>
						<th><spring:message code="label.limit"/></th>
					</tr>		
					<tr>
									<td>
							<div id="statusError" class="error"  style="display:none;color:red;"><spring:message code="label.multipleRowVal"/></div>														
							<select id="status" name ="status">
							           <option><spring:message code="label.leadBank"/></option>
                            <option><spring:message code="label.otherBank"/></option>	</select>					                                                           
						</td>
					<td>
							<div id="bankNameError" class="error" style="display:none;color:red;margin-top: -22px;"><spring:message code="label.multipleRowVal"/></div>
							<input type="text" name="bankName" id="bankName">	
							 
						</td>
					
						<td>
							<div id="bankBranchError" class="error" style="display:none;color:red;margin-top: -22px;"><spring:message code="label.multipleRowVal"/></div>
							<input type="text" name="bankBranch" id="bankBranch">							 
						</td>
						
		
						
						<td>
							 <div id="locationError" class="error"  style="display:none;color:red;margin-top: -22px;"><spring:message code="label.multipleRowVal"/></div>
							<input type="text" name="location" id="location">								 
						</td>	
						<td>
							<div id="limitError" class="error"  style="display:none;color:red;"><spring:message code="label.multipleRowVal"/></div>
							<input type="text" name="limit" id="limit" style="width: 111px;">						 	  
						</td>  
					</tr>			
			</table>
	</div>
		</div>	  	
		<div class="col-sm-12 col-md-12 col-lg-12" style="margin-left: 15px;">
			<a class="btn btn-default"  onclick="addRow('dataTable')" 
			 style="color:#2E9AFE;font-weight: 600;"><spring:message code="label.addNewSupp"/></a>
			<a  class="btn btn-default"  onclick="deleteRow('dataTable')"
			 style="color:#2E9AFE;font-weight: 600;"><spring:message code="label.removSupp"/></a>
		
		</div>	
			
		<div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td class="col-sm-8"><input type="submit" value="<spring:message code="label.save"/>" class="btn btn-primary"></td>
							<td><a href="bankMasterPlanDetails" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
		</form:form>			
		<div class="col-sm-12 col-md-12 col-lg-12">
			<div id="container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>
		</div>
				
		</div>	
		
<style>
	.rupee img{
		    height: 20px;
    width: 20px;
    margin-top: -8px;
	}
</style>
<script>
$(function () {
		var json = ${model.data}
		console.log(json)
	    $('#container').highcharts({
	        chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false,
	            type: 'pie'
	        },
	        title: {
	            text: 'Consortium Of Bank Details'
	        },
	        tooltip: {
	            pointFormat: '{series.name}: <b>{point.y}</b>'
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                dataLabels: {
	                    enabled: true,
	                    formatter: function() {
	                        return 'Bank:' + this.point.name + '<br>' + 'Limit:'+Highcharts.numberFormat(this.y, 2);
	                    },
	                    style: {
	                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
	                    }
	                }
	            }
	        },
	        series: [{
	            name: 'Limit',
	            colorByPoint: true,
	            data: json
	        }]
	    });
	});
</script>