<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="<%=request.getContextPath()%>/resources/js/highcharts.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/exporting.js"></script>

<!-- To fetch the request url -->
<c:set var="req" value="${requestScope['javax.servlet.forward.request_uri']}"/>      
<c:set var="baseURL" value="${fn:split(req,'/')}" />

<script>

$(function () {
    $('#container').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: 'Loan Details'
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
                        return this.point.name + '</b> = ' + Highcharts.numberFormat(this.y, 2);
                    },
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            name: 'Amount',
            colorByPoint: true,
            data: [{
                name: 'Sanctioned Amount',
                y: parseFloat(${model.plan.funalAmt})
            },{
                name: 'Amount Paid',
                y: parseFloat(${model.plan.amountPaid})
            },{
                name: 'Balance',
                y: parseFloat(${model.plan.balance})
            },{
                name: 'Buying Amount Spent',
                y: parseFloat(${model.plan.wcTotalAmount})
            },{
                name: 'Selling Amount Spent',
                y: parseFloat(${model.plan.calPlrRate})
            }]        
        }]
    });
});

$(function () {
    $('#rateContainer').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: 'Rate Details'
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
                        return this.point.name + '</b> = ' + Highcharts.numberFormat(this.y, 2);
                    },
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            name: 'Rate',
            colorByPoint: true,
            data: [{
                name: 'Rate of Interest',
                y: parseFloat(${model.plan.rateOfInt})
            },{
                name: 'PLR Rate',
                y: parseFloat(${model.plan.plrRate})
            },{
                name: 'Amount Interest',
                y: parseFloat(${model.plan.rateOfInt1})
            },{
                name: 'Flat Charges',
                y: parseFloat(${model.plan.flatCharges})
            },{
                name: 'Percentage',
                y: parseFloat(${model.plan.percentage})
            },{
                name: 'Processing Fee',
                y: parseFloat(${model.plan.procFee})
            },{
                name: 'Document Handling Fee',
                y: parseFloat(${model.plan.docFee})
            },{
                name: 'Late Fee',
                y: parseFloat(${model.plan.lateFee})
            },{
                name: 'Tax Percentage',
                y: parseFloat(${model.plan.taxPercentage})
            }]        
        }]
    });
});

</script>
 <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">
 	<div class="col-sm-12 col-md-12 col-lg-12">
		<div id="container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>
	</div>
	<div class="col-sm-12 col-md-12 col-lg-12">&nbsp;</div>
	<div class="col-sm-12 col-md-12 col-lg-12">
		<div id="rateContainer" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>
	</div>
	<div class="col-sm-12 col-md-12 col-lg-12">&nbsp;</div>
	<div class="col-sm-12 col-md-12 col-lg-12">
         	<c:if test="${baseURL[1] == 'bnkEmp'}"><c:set var="back" value="generalLedgerBank"/></c:if>
					<c:if test="${baseURL[1] == 'appMng'}"><c:set var="back" value="generalLedgerAppMng"/></c:if>					
					<table align="center">					
						<tr>
							<td><a href="${back}" class="btn btn-success"><spring:message code="label.back"/></a></td>
						</tr>
					</table>
		        </div>

</div>