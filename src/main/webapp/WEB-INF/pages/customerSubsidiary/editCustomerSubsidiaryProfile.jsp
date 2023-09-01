<%@include file="taglib_includes.jsp"%>

<section>
	<div class="container">
		<div class="row">
				<div class="p_content">
					<form:form action="confirmEditCustomerSubsidiaryProfile" name="editProfile" autocomplete="off" commandName="endUserForm">
						<div class="Pro_img1">
							<img src="<%=request.getContextPath()%>/resources/images/Leena.jpg" class="img_pro">
							<div id="drop">
								<a href="#">Upload Photo &nbsp;<i class="fa fa-pencil"></i></a>
								<input type="file" name="upl" multiple />
							</div>
						</div>
						<div class="form_page">
						<form:hidden path="id"/>
						<form:hidden path="transactionId"/>
							<table>
							
								
								<tr>
									<td><label for="">User Name :</label></td>
									<td><form:input path="userName" class="form-control" id="userName"
										 autocomplete="off" readonly="true"></form:input></td>
								</tr>
								<tr>
									<td><label for="">Display Name :</label></td>
									<td><form:input path="displayName" class="form-control" id="displayName"
										 autocomplete="off"></form:input></td>
								</tr>
								<tr>
									<td><label for="">Email :</label></td>
									<td><form:input path="email" class="form-control"
										id="email" autocomplete="off" ></form:input>
									</td>
								</tr>
								<tr>
									<td><label for="">Alternative Email :</label></td>
									<td><form:input path="altEmail" class="form-control"
										id="altEmail" placeholder="Email" autocomplete="off"></form:input>
									</td>
								</tr>
								<tr>
									<td><label for="">Contact No:</label></td>
									<td><form:input path="contactNo" class="form-control"
										id="contactNo" placeholder="Contact" autocomplete="off" ></form:input>
									</td>
								</tr>
								<tr>
									<td><label for="">Alternative Contact No:</label></td>
									<td><form:input path="altContactNo" class="form-control"
										id="altContactNo" placeholder="Contact" autocomplete="off"></form:input>
									</td>
								</tr>
								
								<tr>
									<td colspan="2">
									<a href="editCustomerSubsidiaryPWD?id=${model.user.id}">Change Password</a>
										<button class="btn btn-md btn-primary btn-block" type="submit"
											style="width: 100px; float: right;">Update</button>
									</td>
								</tr>
							</table>
						</div>
				</form:form>
				</div>
		</div>
	</div>


</section>