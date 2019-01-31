<%@ page errorPage="error_page.jsp"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set
	var="title"
	value="list_managers"
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
			<fmt:message key='manager_list_.title.manger_list' />
		</h1>
		<c:choose>
			<c:when test="${fn:length(listManagers) == 0}">
				<h4 class="text-center">No managers in system</h4>
			</c:when>
			<c:otherwise>
				<table
					class="table table-bordered"
					id="managers_table"
				>
					<thead>
						<tr>
							<th><fmt:message key='manager_list_.head.id' /></th>
							<th><fmt:message key='manager_list_.head.login' /></th>
							<th><fmt:message key='manager_list_.head.first_name' /></th>
							<th><fmt:message key='manager_list_.head.last_name' /></th>
							<th><fmt:message key='manager_list_.head.block_state' /></th>
							<th><fmt:message key='manager_list_.head.change_block_state' /></th>
						</tr>
					</thead>
					<c:forEach
						items="${listManagers}"
						var="manager"
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
									<td>${manager.id}</td>
									<td>${manager.login}</td>
									<td>${manager.firstName}</td>
									<td>${manager.lastName}</td>
									<td>${manager.block}</td>
									<td><select name="block">
											<option value="false"><fmt:message
													key='manager_list_.select.unblock_manager'
												/></option>
											<option value="true"><fmt:message
													key='manager_list_.select.block_manager'
												/></option>
									</select>
									<td><input
										type="hidden"
										name="roleId"
										value="${manager.roleId}"
									/> <input
										type="hidden"
										name="id"
										value="${manager.id}"
									/> <input
										class="btn btn-danger btn-sm"
										type="submit"
										value="<fmt:message key='manager_list_.button.submit' />"
									></td>
								</tr>
							</tbody>
						</form>
					</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
		<div class="col-md-offset-5 col-md-4">
			<a
				href="controller?command=viewRegistration"
				type="button"
				class="btn btn-info btn-sm"
			><fmt:message key='manager_list_.button.registration' /></a>
		</div>
	</div>
	<%@ include file="/WEB-INF/jspf/layouts/footer.jspf"%>
</body>
</html>