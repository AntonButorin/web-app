<%@ page errorPage="error_page.jsp"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set
	var="title"
	value="order"
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
	<div class="col-lg-offset-4 col-lg-4">
		<h3 class="text-center">
			<fmt:message key="list_order.title.list_order_on_car" />
		</h3>
		<h3 class="text-center">${brandList[car.brandId-1].name}
			${car.model}</h3>
		<form
			id="List_order"
			action="controller"
			method="post"
		>
			<input
				type="hidden"
				name="command"
				value="makeOrder"
			/> <input
				type="hidden"
				name="price"
				value="${car.price}"
			/> <input
				type="hidden"
				name="carId"
				value="${car.id}"
			/> <input
				type="hidden"
				name="userId"
				value="${user.id}"
			/> <br> <br> <br>
			<div class="form-group">
				<label for="passport"><fmt:message
						key='list_order.lebel.passport'
					/></label> <input
					name="passport"
					type="text"
					class="form-control"
					id="passport"
					pattern="[a-zA-Z]{2,2}[0-9]{6,6}$"
					title="Enter 2 letter and 6 digits"
					placeholder="<fmt:message key='list_order.placeholder.passport' />"
				>
			</div>
			<br>
			<div class="form-group">
				<label><fmt:message key='list_order.lebel.driver' /></label> <select
					name="driver"
				>
					<option value="true"><fmt:message
							key='list_order.select.true'
						/></option>
					<option
						value="false"
						selected
					><fmt:message key='list_order.select.false' /></option>
				</select>
			</div>
			<br>
			<div class="form-group">
				<label for="period"><fmt:message
						key='list_order.lebel.period'
					/></label> <input
					name="period"
					type="number"
					class="form-control"
					id="period"
					pattern="[1-9]{1,2}$"
					min="1"
					title="Enter number"
					placeholder="<fmt:message key='list_order.placeholder.period' />"
				>
			</div>
			<br>
			<div class="form-group">
				<div class="col-md-offset-3 col-md-5">
					<button
						type="submit"
						class="btn btn-success btn-block btn-xs"
					>
						<h4>
							<fmt:message key='list_order.button.make_order' />
						</h4>
					</button>
				</div>
			</div>
		</form>
	</div>
	<%@ include file="/WEB-INF/jspf/layouts/footer.jspf"%>
</body>
</html>