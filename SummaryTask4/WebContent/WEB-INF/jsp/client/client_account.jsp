<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set
	var="title"
	value="my_account"
	scope="page"
/>
<%@ include file="/WEB-INF/jspf/layouts/head.jspf"%>
<body>
	<%@ include file="/WEB-INF/jspf/layouts/header.jspf"%>
	<div class="container">
		<div class="row">
			<c:forEach
				items="${billList}"
				var="bill"
			>
				<c:if test="${bill.state == true}">
					<form
						id="bill_form"
						action="controller"
						method="post"
					>
						<input
							type="hidden"
							name="command"
							value="payBill"
						/>
						<h3 class="text-center">
							<fmt:message key="client_account.title.bill_for_pay" />
						</h3>
						<h4 class="text-center">
							<c:out value="# ${bill.id}"></c:out>
						</h4>
						<div
							class="form-group"
							align="center"
						>
							<label><fmt:message
									key='client_account.lebel.payment_reference'
								/></label> <label><c:out value="${bill.operationName}" /></label>
						</div>
						<div
							class="form-group"
							align="center"
						>
							<label><fmt:message key='client_account.lebel.name' /></label> <label><c:out
									value="${user.firstName} ${user.lastName}"
								/></label>
						</div>
						<div
							class="form-group"
							align="center"
						>
							<label><fmt:message key='client_account.lebel.amount' /></label>
							<label><c:out value="${bill.amount}" /></label>
						</div>
						<div
							class="form-group"
							align="center"
						>
							<input
								type="hidden"
								name="billId"
								value="${bill.id}"
							/> <input
								type="hidden"
								name="statusId"
								value="3"
							/> <input
								class="btn btn-danger btn-sm"
								type="submit"
								value="<fmt:message key='client_account.button.payment'/>"
							>
						</div>
					</form>
				</c:if>
			</c:forEach>
		</div>
		<h3 class="text-center">
			<fmt:message key="client_account.title.my_orders_list" />
		</h3>
		<c:choose>
			<c:when test="${fn:length(userOrderBeanList) == 0}">
				<h5 class="text-center">
					<fmt:message key="client_account.text.no_orders" />
				</h5>
			</c:when>
			<c:otherwise>
				<form
					id="orders_list"
					action="controller"
					method="post"
				>
					<input
						type="hidden"
						name="command"
						value="payOrder"
					/>
					<table
						class="table table-bordered"
						id="my_orders_table"
					>
						<thead>
							<tr>
								<th><fmt:message key="client_account.head_table.num" /></th>
								<th><fmt:message key="client_account.head_table.car" /></th>
								<th><fmt:message
										key="client_account.head_table.driver_service"
									/></th>
								<th><fmt:message key="client_account.head_table.price" /></th>
								<th><fmt:message
										key="client_account.head_table.rent_period"
									/></th>
								<th><fmt:message key="client_account.head_table.amount" /></th>
								<th><fmt:message
										key="client_account.head_table.order_status"
									/></th>
								<th><fmt:message
										key="client_account.head_table.order_comments"
									/></th>
						</thead>
						<tbody>
							<c:set
								var="k"
								value="0"
							/>
							<c:forEach
								items="${userOrderBeanList}"
								var="bean"
							>
								<c:set
									var="k"
									value="${k+1}"
								/>
								<tr>
									<td><c:out value="${k}" /></td>
									<td>${brandList[bean.carBrandId-1].name}&nbsp;${bean.carModel}</td>
									<td>${bean.driver}</td>
									<td>${bean.price}</td>
									<td>${bean.rentPeriod}</td>
									<td>${bean.price*bean.rentPeriod}</td>
									<td>${bean.orderStatusName}</td>
									<td><c:if test="${bean.orderStatusName == 'canceled'}">
											<c:out value="${bean.orderComments}" />
										</c:if></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
	<%@ include file="/WEB-INF/jspf/layouts/footer.jspf"%>
</body>
</html>