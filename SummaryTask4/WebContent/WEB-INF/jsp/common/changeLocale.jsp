<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set
	var="title"
	value="changeLocale"
	scope="page"
/>
<%@ include file="/WEB-INF/jspf/layouts/head.jspf"%>
<!-- set the locale -->
<fmt:setLocale
	value="${param.locale}"
	scope="session"
/>
<!-- load the bundle (by locale) -->
<fmt:setBundle basename="resources" />
<!-- set current locale to session -->
<c:set
	var="currentLocale"
	value="${param.locale}"
	scope="session"
/>
<!-- goto back to the settings -->
<jsp:forward page="settings.jsp" />
</html>