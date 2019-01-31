<%@ page errorPage="error_page.jsp"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set
	var="title"
	value="list_clients"
	scope="page"
/>
<%@ include file="/WEB-INF/jspf/layouts/head.jspf"%>
<body>
	<%@ include file="/WEB-INF/jspf/layouts/header.jspf"%>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h4 style="color: red; text-align: center">${errors}</h4>
			</div>
		</div>
	</div>
	<div class="container">
		<h1 class="text-center">
			<fmt:message key='client_list.title.client_list' />
		</h1>
		<c:choose>
			<c:when test="${fn:length(listClients) == 0}">
				<h4 class="text-center">No clients in system</h4>
			</c:when>
			<c:otherwise>
				<table
					class="table table-bordered"
					id="clients_table"
				>
					<thead>
						<tr>
							<th><fmt:message key='client_list.title.id' /></th>
							<th><fmt:message key='client_list.title.login' /></th>
							<th><fmt:message key='client_list.title.first_name' /></th>
							<th><fmt:message key='client_list.title.last_name' /></th>
							<th><fmt:message key='client_list.title.block_state' /></th>
							<th><fmt:message key='client_list.title.change_block_state' /></th>
						</tr>
					</thead>
					<c:forEach
						items="${listClients}"
						var="client"
					>
						<form
							action="controller"
							method="post"
						>
							<input
								type="hidden"
								name="command"
								value="changeUserState"
							/>
							<tbody>
								<tr>
									<td>${client.id}</td>
									<td>${client.login}</td>
									<td>${client.firstName}</td>
									<td>${client.lastName}</td>
									<td>${client.block}</td>
									<td><select name="block">
											<option value="false"><fmt:message
													key='client_list.select.unblock_client'
												/></option>
											<option value="true"><fmt:message
													key='client_list.select.block_client'
												/></option>
									</select>
									<td><input
										type="hidden"
										name="roleId"
										value="${client.roleId}"
									/> <input
										type="hidden"
										name="id"
										value="${client.id}"
									/> <input
										class="btn btn-danger btn-sm"
										type="submit"
										value="<fmt:message key='client_list.button.submit' />"
									></td>
								</tr>
							</tbody>
						</form>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
		<div class="col-md-offset-5 col-md-4">
			<a
				href="controller?command=viewRegistration"
				type="button"
				class="btn btn-info btn-sm"
			><fmt:message key='client_list.button.registration' /></a>
		</div>
	</div>
	<%@ include file="/WEB-INF/jspf/layouts/footer.jspf"%>
</body>
</html>