<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set
	var="title"
	value="list_orders"
	scope="page"
/>
<%@ include file="/WEB-INF/jspf/layouts/head.jspf"%>
<body>
	<%@ include file="/WEB-INF/jspf/layouts/header.jspf"%>
	<div class="col-lg-12">
		<h4 style="color: red; text-align: center">${errors}</h4>
	</div>
	<div class="container">
		<ul class="nav navbar-nav">
			<li class="dropdown"><a
				href="#"
				class="dropdown-toggle"
				data-toggle="dropdown"
			><fmt:message key="list_orders.link.select_by_status" /> <b
					class="caret"
				></b></a>
				<ul
					class="dropdown-menu"
					role="menu"
					aria-labelledby="dropdownMenu"
				>
					<c:forEach
						begin="0"
						end="${fn:length(statusList) - 1}"
						var="statusId"
					>
						<li><a
							href="controller?command=selectOrders&statusId=${statusId}"
						>${statusList[statusId]}</a></li>
					</c:forEach>
				</ul></li>
		</ul>
	</div>
	<div class="container">
		<h3 class="text-center">
			<fmt:message key="list_orders.title.orders_list" />
		</h3>
		<c:choose>
			<c:when test="${fn:length(userOrderBeanList) == 0}">
				<h5 class="text-center">
					<fmt:message key="list_orders.text.no_orders_in_system" />
				</h5>
				<br>
			</c:when>
			<c:otherwise>
				<table
					class="table table-bordered"
					id="orders_table"
				>
					<thead>
						<tr>
							<th><fmt:message key="list_orders.head_table.num" /></th>
							<th><fmt:message key="list_orders.head_table.client" /></th>
							<th><fmt:message key="list_orders.head_table.car" /></th>
							<th><fmt:message key="list_orders.head_table.price" /></th>
							<th><fmt:message key="list_orders.head_table.period" /></th>
							<th><fmt:message key="list_orders.head_table.order_status" /></th>
							<th><fmt:message key="list_orders.head_table.change_status" /></th>
							<th><fmt:message
									key="list_orders.head_table.compensation_sum"
								/></th>
							<th><fmt:message key="list_orders.head_table.comments" /></th>
					</thead>
					<c:set
						var="k"
						value="0"
					/>
					<c:forEach
						items="${userOrderBeanList}"
						var="bean"
					>
						<tbody>
							<form
								id="orders_list"
								action="controller"
								method="post"
							>
								<input
									type="hidden"
									name="command"
									value="updateOrderStatus"
								/>
								<c:set
									var="k"
									value="${k+1}"
								/>
								<tr>
									<td><c:out value="${k}" /></td>
									<td>${bean.userFirstName}&nbsp;${bean.userLastName}</td>
									<td>${brandList[bean.carBrandId-1].name}&nbsp;${bean.carModel}</td>
									<td>${bean.price}</td>
									<td>${bean.rentPeriod}</td>
									<td>${bean.orderStatusName}</td>
									<c:if test="${bean.orderStatusName ne 'closed'}">
										<c:if test="${bean.orderStatusName ne 'canceled'}">
											<td><select name="statusId">
													<c:if test="${bean.orderStatusName ne 'paid'}">
														<option value="1"><fmt:message
																key='list_orders.select_status.confirm'
															/></option>
														<option value="2"><fmt:message
																key='list_orders.select_status.cancel'
															/></option>
													</c:if>
													<option value="4"><fmt:message
															key='list_orders.select_status.complain'
														/></option>
													<option value="5"><fmt:message
															key='list_orders.select_status.close'
														/></option>
											</select></td>
											<td><input
												name="compensationSum"
												type="number"
												min="0"
												id="compensationSum"
											/></td>
											<td><input
												name="comments"
												type="text"
												id="comments"
											/></td>
											<td><input
												name="id"
												type="hidden"
												value="${bean.orderId}"
											/> <input
												class="btn btn-danger btn-sm"
												type="submit"
												value="<fmt:message key='list_orders.button.submit'/>"
											></td>
										</c:if>
									</c:if>
								</tr>
							</form>
						</tbody>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
	<%@ include file="/WEB-INF/jspf/layouts/footer.jspf"%>
</body>
</html>