<%@ page isErrorPage="true"%>
<%@ page import="java.io.PrintWriter"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set
	var="title"
	value="errorPage"
	scope="page"
/>
<%@ include file="/WEB-INF/jspf/layouts/head.jspf"%>
<body>
	<%@ include file="/WEB-INF/jspf/layouts/header.jspf"%>
	<table id="main-container">
		<tr>
			<td class="content">
				<h3 class="error">The following error occurred:</h3> <c:set
					var="code"
					value="${requestScope['javax.servlet.error.status_code']}"
				/> <c:set
					var="message"
					value="${requestScope['javax.servlet.error.message']}"
				/> <c:set
					var="exception"
					value="${requestScope['javax.servlet.error.exception']}"
				/> <c:if test="${not empty code}">
					<h4 style="color: red; text-align: center">Error code: ${code}</h4>
				</c:if> <c:if test="${not empty message}">
					<h4 style="color: red; text-align: center">${message}</h4>
				</c:if> <c:if test="${not empty exception}">
					<%
						exception.printStackTrace(new PrintWriter(out));
					%>
				</c:if> <c:if test="${not empty requestScope.errorMessage}">
					<h4 style="color: red; text-align: center">${requestScope.errorMessage}</h4>
				</c:if>
			</td>
		</tr>
	</table>
	<%@ include file="/WEB-INF/jspf/layouts/footer.jspf"%>
</body>
</html>