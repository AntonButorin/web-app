<%@ page errorPage="error_page.jsp"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set
	var="title"
	value="car_settings"
	scope="page"
/>
<%@ include file="/WEB-INF/jspf/layouts/head.jspf"%>
<body>
	<%@ include file="/WEB-INF/jspf/layouts/header.jspf"%>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h5 style="color: red; text-align: center">${errors}</h5>
			</div>
		</div>
	</div>
	<c:choose>
		<c:when test="${actionMessage == 'create'}">
			<div class="container">
				<div class="row">
					<div class="col-lg-offset-3 col-lg-6">
						<h3 class="text-center">
							<fmt:message key='car_settings.title.add_new_car' />
						</h3>
						<form
							id="add_new_car_form"
							action="controller"
							method="post"
						>
							<input
								type="hidden"
								name="command"
								value="updateCarSettings"
							/> <input
								type="hidden"
								name="message"
								value="create"
							/>
							<div class="form-group">
								<label><fmt:message key='car_settings.label.model' /></label> <input
									name="model"
									type="text"
									pattern="[a-zA-Zа-яА-Я0-9._+-]{1,}$"
									title="Enter more then 1 bymbol"
									class="form-control"
									id="model"
									placeholder="<fmt:message key='car_settings.placeholder.model' />"
								>
							</div>
							<div class="form-group">
								<label><fmt:message key='car_settings.label.category' /></label>
								<select name="categoryId">
									<c:forEach
										begin="0"
										end="${fn:length(categoryList) - 1}"
										var="categoryId"
									>
										<option value="${categoryId}">${categoryList[categoryId]}</option>
									</c:forEach>
								</select> <label><fmt:message key='car_settings.label.brand' /></label>
								<select name="carBrand">
									<c:forEach
										items="${brandList}"
										var="carBrand"
									>
										<option value="${carBrand.id}">${carBrand.name}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label><fmt:message key='car_settings.label.seat_amount' /></label>
								<input
									name="seatAmount"
									type="number"
									pattern="[1-9]{1,2}$"
									title="Enter more 1 digit till 2"
									class="form-control"
									id="seatAmount"
									min="1"
									placeholder="<fmt:message key='car_settings.placeholder.seat_amount' />"
								>
							</div>
							<div class="form-group">
								<label><fmt:message key='car_settings.label.fuel' /></label> <select
									name="fuelId"
								>
									<c:forEach
										begin="0"
										end="${fn:length(fuelList) - 1}"
										var="fuelId"
									>
										<option value="${fuelId}">${fuelList[fuelId]}</option>
									</c:forEach>
								</select> <label><fmt:message
										key='car_settings.label.air_condition'
									/></label> <select name="airCondition">
									<option value="true"><fmt:message
											key='car_settings.select.true'
										/></option>
									<option
										value="false"
										selected
									><fmt:message key='car_settings.select.false' /></option>
								</select> <label><fmt:message
										key='car_settings.label.automatic_transmission'
									/></label> <select name="automaticTransmission">
									<option value="true"><fmt:message
											key='car_settings.select.true'
										/></option>
									<option
										value="false"
										selected
									><fmt:message key='car_settings.select.false' /></option>
								</select>
							</div>
							<div class="form-group">
								<label><fmt:message key='car_settings.label.price' /></label> <input
									name="price"
									type="number"
									class="form-control"
									id="price"
									pattern="[0-9]{1,}$"
									min="1"
									placeholder="<fmt:message key='car_settings.placeholder.price' />"
								>
							</div>
							<div class="form-group">
								<label><fmt:message
										key='car_settings.label.guarantee_amount'
									/></label> <input
									name="guaranteeAmount"
									type="number"
									class="form-control"
									pattern="[0-9]{1,}$"
									id="guaranteeAmount"
									min="1"
									placeholder="<fmt:message key='car_settings.placeholder.guarantee_amount' />"
								>
							</div>
							<div class="col-md-offset-4 col-md-4">
								<input
									type="submit"
									class="btn btn-success btn-block btn-sm"
									value="<fmt:message key='car_settings.button.create' />"
								/>
							</div>
						</form>
					</div>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="col-md-offset-4 col-md-5">
				<h1 class="text-center">
					<fmt:message key='car_settings.title.update_data_car' />
				</h1>
				<form
					id="update_data_car_form"
					action="controller"
					method="post"
				>
					<input
						type="hidden"
						name="command"
						value="updateCarSettings"
					/> <input
						type="hidden"
						name="message"
						value="update"
					/>
					<div class="form-group">
						<label for="price"><fmt:message
								key='car_settings.label.price'
							/></label> <input
							name="price"
							type="number"
							class="form-control"
							id="price"
							min="1"
							placeholder="<fmt:message key='car_settings.placeholder.price' />"
						>
					</div>
					<div class="form-group">
						<label for="guaranteeAmount"><fmt:message
								key='car_settings.label.guarantee_amount'
							/></label> <input
							name="guaranteeAmount"
							type="number"
							class="form-control"
							id="guaranteeAmount"
							min="1"
							placeholder="<fmt:message key='car_settings.placeholder.guarantee_amount' />"
						>
					</div>
					<br> <input
						type="hidden"
						name="carId"
						value="${carId}"
					/>
					<div class="col-lg-offset-4 col-lg-4">
						<input
							class="btn btn-block btn-success btn-sm"
							type="submit"
							value="<fmt:message key='car_settings.button.update' />"
						>
					</div>
				</form>
				<br> <br>
				<div class="col-lg-offset-4 col-lg-4">
					<form
						id="delete_car_form"
						action="controller"
						method="post"
					>
						<input
							type="hidden"
							name="command"
							value="updateCarSettings"
						/> <input
							type="hidden"
							name="message"
							value="delete"
						/> <input
							type="hidden"
							name="carId"
							value="${carId}"
						/> <input
							class="btn btn-block btn-warning btn-sm"
							type="submit"
							value="<fmt:message key='car_settings.button.detete' />"
						>
					</form>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	<%@ include file="/WEB-INF/jspf/layouts/footer.jspf"%>
</body>
</html>
