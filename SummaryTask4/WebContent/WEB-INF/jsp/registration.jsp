<%@ page errorPage="error_page.jsp"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set
	var="title"
	value="registration"
	scope="page"
/>
<%@ include file="/WEB-INF/jspf/layouts/head.jspf"%>
<body>
	<%@ include file="/WEB-INF/jspf/layouts/header.jspf"%>
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<h5 style="color: red; text-align: center">${errors}</h5>
			</div>
		</div>
		<div class="col-lg-offset-3 col-lg-6">
			<h3 class="text-center">
				<fmt:message key='registration.title.registration' />
			</h3>
			<form
				id="registration_form"
				action="controller"
				method="post"
			>
				<input
					type="hidden"
					name="command"
					value="registration"
				/>
				<div class="form-group">
					<label for="login"><fmt:message
							key='registration.label.login'
						/></label>
					<c:choose>
						<c:when test="${not empty requestScope.login}">
							<input
								name="login"
								value="${requestScope.login}"
								type="text"
								class="form-control"
								id="login"
							>
						</c:when>
						<c:otherwise>
							<input
								name="login"
								type="text"
								class="form-control"
								id="login"
								placeholder="<fmt:message key='registration.placeholder.login'/>"
							>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="form-group">
					<label for="firstName"><fmt:message
							key='registration.label.first_name'
						/></label>
					<c:choose>
						<c:when test="${not empty requestScope.firstName}">
							<input
								name="firstName"
								value="${requestScope.firstName}"
								type="text"
								class="form-control"
								id="firstName"
							>
						</c:when>
						<c:otherwise>
							<input
								name="firstName"
								type="text"
								class="form-control"
								id="firstName"
								placeholder="<fmt:message key='registration.placeholder.first_name'/>"
							>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="form-group">
					<label for="lastName"><fmt:message
							key='registration.label.last_name'
						/></label>
					<c:choose>
						<c:when test="${not empty requestScope.lastName}">
							<input
								name="lastName"
								value="${requestScope.lastName}"
								type="text"
								class="form-control"
								id="lastName"
							>
						</c:when>
						<c:otherwise>
							<input
								name="lastName"
								type="text"
								class="form-control"
								id="lastName"
								placeholder="<fmt:message key='registration.placeholder.last_name'/>"
							>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="form-group">
					<label for="password"><fmt:message
							key='registration.label.password'
						/></label> <input
						name="password"
						type="password"
						class="form-control"
						id="password"
						placeholder="<fmt:message key='registration.placeholder.password'/>"
					>
				</div>
				<div class="form-group">
					<label for="repeatPassword"><fmt:message
							key='registration.label.repeat_password'
						/></label> <input
						name="repeatPassword"
						type="password"
						class="form-control"
						id="repeatPassword"
						placeholder="<fmt:message key='registration.placeholder.repeat_password' />"
					>
				</div>
				<c:if test="${userRole.name == 'admin'}">
					<div class="checkbox">
						<label class="radio-inline"> <input
							type="radio"
							name="role"
							value="manager"
							checked
						> <fmt:message key='registration.label.register_new_manager' />
						</label> &nbsp; <label class="radio-inline"> <input
							type="radio"
							name="role"
							value="client"
						> <fmt:message key='registration.label.register_new_client' />
						</label>
					</div>
				</c:if>
				<div class="form-group">
					<div class="col-lg-offset-4 col-lg-3">
						<button
							type="submit"
							class="btn btn-success btn-block btn-xs"
						>
							<h4>
								<fmt:message key='registration.button.register' />
							</h4>
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<%@ include file="/WEB-INF/jspf/layouts/footer.jspf"%>
</body>
</html>