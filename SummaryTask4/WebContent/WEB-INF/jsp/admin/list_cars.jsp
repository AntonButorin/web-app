<%@ page errorPage="error_page.jsp"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set
	var="title"
	value="list_cars"
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
		<ul class="nav navbar-nav">
			<li class="dropdown"><a
				href="#"
				class="dropdown-toggle"
				data-toggle="dropdown"
			><fmt:message key="list_cars.link.select_by_category" /> <b
					class="caret"
				></b></a>
				<ul
					class="dropdown-menu"
					role="menu"
					aria-labelledby="dropdownMenu"
				>
					<c:forEach
						begin="0"
						end="${fn:length(categoryList) - 1}"
						var="categoryId"
					>
						<li><a
							href="controller?command=selectCar&categoryId=${categoryId}"
						>${categoryList[categoryId]}</a></li>
					</c:forEach>
				</ul></li>
			<li class="dropdown"><a
				href="#"
				class="dropdown-toggle"
				data-toggle="dropdown"
			><fmt:message key="list_cars.link.select_by_brand" /> <b
					class="caret"
				></b></a>
				<ul
					class="dropdown-menu"
					role="menu"
					aria-labelledby="dropdownMenu"
				>
					<c:forEach
						items="${brandList}"
						var="carBrand"
					>
						<li><a
							href="controller?command=selectCar&carBrand=${carBrand.id}"
						>${carBrand.name}</a></li>
					</c:forEach>
				</ul></li>
			<li class="dropdown"><a
				class="dropdown-toggle"
				data-toggle="dropdown"
				href=""
			><fmt:message key='list_cars.link.sort_by' /><span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="controller?command=sortCar&sortBy=price"><fmt:message
								key='list_cars.link.price'
							/></a></li>
					<li><a href="controller?command=sortCar&sortBy=model"><fmt:message
								key='list_cars.link.model'
							/></a></li>
				</ul></li>
		</ul>
	</div>
	<div class="container">
		<h3 class="text-center">
			<fmt:message key="list_cars.title.list_of_cars" />
		</h3>
		<c:choose>
			<c:when test="${fn:length(listCars) == 0}">
				<h5 class="text-center">
					<fmt:message key="list_cars.text.no_cars_in_system" />
				</h5>
			</c:when>
			<c:otherwise>
				<form
					id="list_car"
					action="controller"
					method="post"
				>
					<c:if test="${userRole.name == 'admin'}">
						<input
							type="hidden"
							name="command"
							value="viewCarSettings"
						/>
					</c:if>
					<c:if test="${userRole.name == 'client'}">
						<input
							type="hidden"
							name="command"
							value="viewMakeOrder"
						/>
					</c:if>
					<table
						class="table table-bordered"
						id="cars_table"
					>
						<thead>
							<tr>
								<th><fmt:message key="list_cars.head_table.num" /></th>
								<th><fmt:message key="list_cars.head_table.brand" /></th>
								<th><fmt:message key="list_cars.head_table.model" /></th>
								<th><fmt:message key="list_cars.head_table.category" /></th>
								<th><fmt:message key="list_cars.head_table.seat_amount" /></th>
								<th><fmt:message key="list_cars.head_table.fuel" /></th>
								<th><fmt:message key="list_cars.head_table.air_condition" /></th>
								<th><fmt:message
										key="list_cars.head_table.automatic_transmission"
									/></th>
								<th><fmt:message key="list_cars.head_table.price" /></th>
								<th><fmt:message
										key="list_cars.head_table.guarantee_amount"
									/></th>
							</tr>
						</thead>
						<tbody>
							<c:set
								var="k"
								value="0"
							/>
							<c:forEach
								items="${listCars}"
								var="car"
							>
								<c:set
									var="k"
									value="${k+1}"
								/>
								<tr>
									<td><c:out value="${k}" /></td>
									<td>${brandList[car.brandId-1].name}</td>
									<td>${car.model}</td>
									<td>${categoryList[car.categoryId].name}</td>
									<td>${car.seatAmount}</td>
									<td>${fuelList[car.fuelId].name}</td>
									<td>${car.airCondition}</td>
									<td>${car.automaticTransmission}</td>
									<td>${car.price}</td>
									<td>${car.guaranteeAmount}</td>
									<td><c:if test="${userRole.name == 'admin'}">
											<button
												type="submit"
												value="${car.id}"
												name="carId"
												class="btn btn-info btn-sm"
											>
												<fmt:message key="list_cars.button.car_settings" />
											</button>
										</c:if> <c:if test="${userRole.name == 'client'}">
											<button
												type="submit"
												value="${car.id}"
												name="carId"
												class="btn btn-info btn-sm"
											>
												<fmt:message key="list_cars.button.make_order" />
											</button>
										</c:if></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<c:if test="${userRole.name == 'admin'}">
						<div class="col-md-offset-5 col-md-4">
							<button
								type="submit"
								name="actionMessage"
								value="create"
								class="btn btn-info btn-sm"
							>
								<fmt:message key="list_cars.button.add_new_car" />
							</button>
						</div>
					</c:if>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
	<%@ include file="/WEB-INF/jspf/layouts/footer.jspf"%>
</body>
</html>