<%@include file="taglib_includes.jsp"%>
 <div class="col-sm-9 col-md-9 col-lg-9 body_fixed">

			 <div class="col-sm-12 col-md-12 col-lg-12 header_customer">
					<h3 align="center"><spring:message code="label.updateRegulation"/></h3>
			 </div>
			<form:form name="updateReg" action="updateReg" method="post" commandName="regulationsForm" onsubmit="return val();">

				<form:hidden path="transactionId" value="" />

				<table  align="center">

					<tr>
						<td class="col-sm-6"><b><spring:message code="label.id"/></b></td>
						<td class="col-sm-6"><form:input path="id" id="id" readonly="true" />
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.country"/></b></td>
						<td class="col-sm-6"><form:input path="countryName" id="countryName"
								readonly="true" />
					</tr>

					<tr>
						<td class="col-sm-6"><b><spring:message code="label.regulationStatus"/></b></td>
						<td class="col-sm-6"><form:select path="regulation" id="status" width="205px;!important" >
								 <form:option value=""><spring:message code="label.selectValue"/></form:option> 
								<form:option value="Banned" onclick="radio_check()"><spring:message code="label.banned"/></form:option>
								<form:option value="Not Banned" onclick="radio_check()"><spring:message code="label.notBanned"/></form:option>
								<form:option value="Restricted" onclick="radio_check()"><spring:message code="label.restricted"/></form:option>
						<td id="statusError" class="error" style="display:none;"><font color="red"><spring:message code="label.selectValue"/></font></td>
						<td id="statusError" class="error" style="display:none"><spring:message code="label.selectValue"/></td>
							</form:select>
							</td>
					</tr>
					<tr>
						<td class="col-sm-6"><b><spring:message code="label.notBannedGoods"/></b></td>
						<td class="col-sm-6"><form:textarea path="exportedGoods" value="" id="l1" /></td>

					</tr>

					<tr>
						<td class="col-sm-6"><b><spring:message code="label.bannedGoods"/></b></td>
						<td class="col-sm-6"><form:input path="bannedGoods"  placeholder="Enter BannedGoods" value="" id="bannedGoods" />
                      	<div id="bannedGoodsError" style="display:none;color:red;"><spring:message code="label.validation"/></div></td>
					</tr>

				</table>
				 <div class="col-sm-12 col-md-12 col-lg-12 header_customer">
				<table align="center">
					<tr>
						<td><input type="submit" size="3" value="<spring:message code="label.confirm"/>"
							class="btn btn-primary"></td>
						<td><a href="regulationPage" class="btn btn-success" ><spring:message code="label.back"/></a></td>
					</tr>
				</table>

				</div>
			</form:form>

		</div>
<script>
	$(document).ready(function() {
		$('#bootstrap-table').bdt();
	});

	function radio_check() {
		alert("hi");
		if (document.updateReg.regulation.value == 'Banned') {
			document.getElementById("l1").disabled = true;

		} else {
			document.getElementById("l2").disabled = true;
		}

	}
</script>
<script>
		function val(){
				
			
		 
			    var bannedGoods =document.getElementById('bannedGoods');
				var status  = document.getElementById('status');
				var canSubmit = true;
                if (document.getElementById('bannedGoods').value == '') {
					document.getElementById('bannedGoodsError').style.display = 'block';
					canSubmit = false;
				} else {
					document.getElementById('bannedGoodsError').style.display = 'none';
				}
                if (document.getElementById('status').value == ''){
					document.getElementById('statusError').style.display='block';
					canSubmit = false;
				}
				else{
					document.getElementById('statusError').style.display='none';
				}
				if(canSubmit == false){
					return false;
				}
			}
				
		</script>
