<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/canvasjs.min.js"></script>
<!DOCTYPE HTML>
<html>

<head>  
 
  <script type="text/javascript">
  window.onload = function () {
	  var json=${model.json};
	  
    var chart = new CanvasJS.Chart("chartContainer",
    {
      title:{
        text: "Graph"    
      },
      animationEnabled: true,
      axisY: {
        title: "Date Exposure"    
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
        legendText: " ",
        dataPoints: json
      }   
      ]
    });

    chart.render();
  }
  </script>
  <script type="text/javascript" src="/assets/script/canvasjs.min.js"></script>
</head>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
		<div class="col-sm-12 col-md-12 col-lg-12">
		<div class="successMsg"
			style="text-align: center; color: green; font-size: 18px;">${success}</div>
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		
		</div>
		<div class="col-sm-12 col-md-12 col-lg-12 newcustomer_border">
 
  <body>
  <div id="chartContainer" style="height: 300px; width: 100%;">
    </div>
  </body>
  </div>
</html>
