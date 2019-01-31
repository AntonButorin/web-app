<%@ page errorPage="error_page.jsp"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set
	var="title"
	value="login"
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
			<div class="col-lg-offset-3 col-lg-6">
				<h1 class="text-center">
					<fmt:message key='login.title.login' />
				</h1>
				<form
					id="login_form"
					action="controller"
					method="post"
				>
					<input
						type="hidden"
						name="command"
						value="login"
					/>
					<div class="form-group">
						<label><fmt:message key='login.label.login' /></label> <input
							name="login"
							type="text"
							class="form-control"
							id="login"
							placeholder="<fmt:message key='login.placeholder.enter_login' />"
						>
					</div>
					<div class="form-group">
						<label><fmt:message key='login.label.password' /></label> <input
							name="password"
							type="password"
							class="form-control"
							placeholder="<fmt:message key='login.placeholder.enter_password' />"
						>
					</div>
					<div class="col-md-offset-4 col-md-4">
						<button
							type="submit"
							class="btn btn-success btn-block btn-xs"
						>
							<h4>
								<fmt:message key='login.button.log_in' />
							</h4>
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/jspf/layouts/footer.jspf"%>
</body>
</html>