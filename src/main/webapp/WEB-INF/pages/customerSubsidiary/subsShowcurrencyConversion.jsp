
<script>

function simple()
{
var p,r,n,i;
if(document.interest.principle.value == " " )
{
alert("PLEASE ENTER RATE OF EXCHANGE");
document.interest.principle.alert();
}
else if(document.interest.rate.value=="")
{
alert("ENTER RATE OF MARK UP");
document.interest.rate.blur();

}
else if(document.interest.value.value=="")
{
alert("ENTER NUMBER OF AMONT TO BE CONVERTED");
document.interest.value.prompt();
}
else
{
p=parseInt(document.interest.principle.value);

r=parseInt(document.interest.rate.value);

n=parseInt(document.interest.value.value);

i=n*(p+r);

document.interest.answer.value="" + i;
}
}
</script>
<div class="col-sm-9 col-md-9 col-lg-9 body_fixed">	
	<div class="col-sm-12 col-md-12 col-lg-12 header_customer">
		<h3>Currency Convert</h3>
	</div>
			<form method="post" action="" name="interest">
			
			<table  align="center">
			<tr>
				<td class="col-sm-6">
					<label>FROM :</label>
					<select id="xxfrom" style="width:205px;">
					<option value="USD"> $ - U.S. Dollar</option>
					<option value="GBP">&pound; - British Pound</option>
					<option value="EUR">EUR - Euro</option>
					<option value="CAD">CAD - Canadian Dollar</option>
					<option value="AUD">AUD - Australian Dollar</option>
					<option value="JPY">JPY - Japanese Yen</option>
					<option value="RUP">&#x20B9; - indian Rupees</option>
					</select>
				</td>
				<td class="col-sm-6">
					<label>TO :</label>
					<select id="xxto" style="width:205px;">
					<option value="RUP">&#x20B9; - indian Rupees</option>
					<option value="EUR">EUR - Euro</option>
					<option value="USD"> $ - U.S. Dollar</option>
					<option value="GBP">&pound; - British Pound</option>
					<option value="CAD">CAD - Canadian Dollar</option>
					<option value="AUD">AUD - Australian Dollar</option>
					<option value="JPY">JPY - Japanese Yen</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="col-sm-6">Enter Rate Of Exchange</td>
				<td class="col-sm-6"><input type="text" name="principle" size="7"></td>
			</tr>
			
			<tr>
				<td class="col-sm-6">Enter Mark Up</td>
				<td class="col-sm-6"><input type="text" name="rate" size="7"></td>
			</tr>
			
			<tr>
				<td class="col-sm-6">Enter Amount To be Converted</td>
				<td class="col-sm-6"><input type="text" name="value" size="7"></td>
			</tr>
			
			
			<tr>
				<td colspan="2"  class="col-sm-12"><input type="button" class="btn btn-primary" value="CALCULATE" size="5" onClick="simple()">
				<input type="reset" class="btn btn-success" value="clear"></td>
			
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class="col-sm-6"><b>Convert Amount</B></td>
				<td class="col-sm-6"><input type="text" name="answer" size="7"></td>
			</tr>
			
			</table>
			</form>
</div>
<!-- <html>
<head>
<script type="text/javascript" src="http://currencyconverter.55uk.net/currency_converter_json.js"></script>
<script type="text/javascript">
function getMyRates(jData) {
if (jData == null) {
alert("There was a problem parsing search results.");
return;
}
var myval = jData.ResultSet;
var mydiv = jData.xxMyDiv;
document.getElementById(mydiv).innerHTML = myval;
}

function myPrivateConverterMany() {
var xxv = document.getElementById('xxvalue').value;
var xxf = document.getElementById('xxfrom').value;
var xxt = document.getElementById('xxto').value;
if (xxv > 0) {
getExchangeRatesDiv('xxrates',xxv,xxf,xxt,'true');
}
}


</script>
</head>
<body>
<form method="post" action="" name="interest">
CONVERT: <input id="xxvalue" value="1.00" />
FROM:
<select id="xxfrom">
<option value="GBP">GBP - British Pound</option>
<option value="EUR">EUR - Euro</option>
<option value="USD">USD - U.S. Dollar</option>
<option value="CAD">CAD - Canadian Dollar</option>
<option value="AUD">AUD - Australian Dollar</option>
<option value="JPY">JPY - Japanese Yen</option>
</select>
TO:
<select id="xxto">
<option value="EUR">EUR - Euro</option>
<option value="USD">USD - U.S. Dollar</option>
<option value="GBP">GBP - British Pound</option>
<option value="CAD">CAD - Canadian Dollar</option>
<option value="AUD">AUD - Australian Dollar</option>
<option value="JPY">JPY - Japanese Yen</option>
</select>
<input type="button" value="GO" onclick="myPrivateConverterMany();" />
<div id="xxrates" style=" margin-left: 522px;"></div>


</table>
</form>
</body>
</html> -->