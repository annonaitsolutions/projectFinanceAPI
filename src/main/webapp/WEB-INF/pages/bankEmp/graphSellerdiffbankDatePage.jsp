<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
 
  <script type="text/javascript">
  window.onload = function () {
	  var json=${model.json};
	  var chart = new CanvasJS.Chart("chartContainer",
			    {
			        title: {
			            text: "Cost Exposure For Bank"               
			        },
			        axisY:{      
			        	  title: "Cost"			        	  
			        },			       
			      legend: {
			          verticalAlign: "bottom",
			          horizontalAlign: "center"
			        },
			        theme: "theme2",
			      data: [
			      { 
			    	  type: "column",  
			          showInLegend: true, 
			          legendMarkerColor: "grey",
			          legendText: "",
			          xValueType:"dateTime",	       
			      	  dataPoints: json
			    }
			    
			    ]
			});

     	chart.render();
     }
     </script>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
		<div class="col-sm-12 col-md-12 col-lg-12">
		<div class="successMsg"
			style="text-align: center; color: green; font-size: 18px;">${success}</div>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
			
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12 newcustomer_border">
  <div id="chartContainer" style="height: 300px; width: 100%;">
    </div>
    
     <div class="col-sm-12 col-md-12 col-lg-12">
					<table align="center">
						<tr>
							<td><a href="masterKeyApprovedSellerList" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>
</div>
</div>