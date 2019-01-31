<%@ page errorPage="error_page.jsp"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set
	var="title"
	value="settings"
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
	<div id="main-container">
		<%-- CONTENT --%>
		<div class="col-md-offset-4 col-md-5">
			<form
				action="controller"
				method="post"
			>
				<input
					type="hidden"
					name="command"
					value="changeLocale"
				/>
				<fmt:message key='settings.title.menu_language' />
				<select name="locale">
					<c:forEach
						items="${applicationScope.locales}"
						var="locale"
					>
						<c:set
							var="selected"
							value="${locale.key == currentLocale ? 'selected' : '' }"
						/>
						<option
							value="${locale.key}"
							${selected}
						>${locale.value}</option>
					</c:forEach>
				</select> <input
					type="submit"
					value="<fmt:message key='settings.submit.save_language'/>"
				>
			</form>
		</div>
		<c:if test="${not empty user}">
			<div class="col-md-offset-4 col-md-5">
				<h1 class="text-center">
					<fmt:message key='settings.title.data_update' />
				</h1>
				<form
					id="settings_form"
					action="controller"
					method="post"
				>
					<input
						type="hidden"
						name="command"
						value="updateSettings"
					/>
					<div class="form-group">
						<label for="firstName"><fmt:message
								key='settings.label.first_name'
							/></label> <input
							name="firstName"
							type="text"
							class="form-control"
							id="firstName"
							placeholder="<fmt:message key='settings.placeholder.first_name' />"
						>
					</div>
					<div class="form-group">
						<label for="lastName"><fmt:message
								key='settings.label.last_name'
							/></label> <input
							name="lastName"
							type="text"
							class="form-control"
							id="lastName"
							placeholder="<fmt:message key='settings.placeholder.last_name' />"
						>
					</div>
					<div class="form-group">
						<label for="password"><fmt:message
								key='settings.label.new_password'
							/></label> <input
							name="password"
							type="password"
							class="form-control"
							id="password"
							placeholder="<fmt:message key='settings.placeholder.new_password' />"
						>
					</div>
					<div class="col-md-offset-4 col-md-4">
						<button
							type="submit"
							class="btn btn-success btn-block btn-xs"
						>
							<h4>
								<fmt:message key='settings.button.update' />
							</h4>
						</button>
					</div>
				</form>
			</div>
		</c:if>
	</div>
	<%@ include file="/WEB-INF/jspf/layouts/footer.jspf"%>
</body>
</html>