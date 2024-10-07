<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/layouts/user/taglib.jsp"%>
<head>
<meta charset="UTF-8">
<title>Chi tiết sản phẩm</title>
<style>
.product-content {
	overflow-x: hidden;
}
</style>
</head>
<body>
	<div class="row product-content">
		<div id="sidebar" class="span3">
			<ul class="nav nav-list promowrapper">
				<li>
					<div class="thumbnail">
						<img src="<c:url value="/assets/user/img/g.jpg"/>"
							alt="bootstrap ecommerce templates">
					</div>
				</li>
				<li style="border: 0">&nbsp;</li>
				<li>
					<div class="thumbnail">
						<img src="<c:url value="/assets/user/img/h.jpg"/>"
							alt="shopping cart template">
					</div>
				</li>
				<li style="border: 0">&nbsp;</li>
				<li>
					<div class="thumbnail">
						<img src="<c:url value="/assets/user/img/i.jpg"/>"
							alt="bootstrap template">
					</div>
				</li>
				<li style="border: 0">&nbsp;</li>
				<li>
					<div class="thumbnail">
						<img src="<c:url value="/assets/user/img/j.jpg"/>"
							alt="bootstrap template">
					</div>
				</li>
				<li style="border: 0">&nbsp;</li>
				<li>
					<div class="thumbnail">
						<img src="<c:url value="/assets/user/img/c.jpg"/>"
							alt="bootstrap template">
					</div>
				</li>
			</ul>
		</div>
		<div class="span9">
			<ul class="breadcrumb">
				<li><a href="javascript:void(0);"
					onclick="window.location.href='/DiamonShop/trang-chu';"> Trang
						chủ</a> <span class="divider">/</span></li>
				<li><a href="javascript:void(0);"
					onclick="window.location.href='/DiamonShop/san-pham';"> Sản
						phẩm</a> <span class="divider">/</span></li>
				<li class="active">Chi tiết sản phẩm</li>
			</ul>
			<div class="well well-small">
				<div class="row-fluid">
					<div class="span5">
						<div id="myCarousel" class="carousel slide cntr">
							<div class="carousel-inner">
								<div class="item active">
									<a href="<c:url value="/assets/user/img/${ product.img }"/>"> <img
										src="<c:url value="/assets/user/img/${ product.img }"/>"
										alt="" style="width: 100%"></a>
								</div>
							</div>
						</div>
					</div>
					<div class="span7">
						<h3>${ product.name }</h3>
						<hr class="soft" />

						<form class="form-horizontal qtyFrm" method="get"
							action="<c:url value="/AddCart/${ product.id_product }"/>">
							<div class="control-group">
								<label class="control-label"><span><fmt:formatNumber
											type="number" groupingUsed="true" value="${ product.price }" />
										₫</span></label>
								<div class="controls">
									<input type="number" min="0" value="0" class="span6">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label"><span>Color</span></label>
								<div class="controls">
									<select class="span11">
										<option>Red</option>
										<option>Purple</option>
										<option>Pink</option>
										<option>Red</option>
									</select>
								</div>
							</div>
							<h4></h4>
							<p>${ product.title }</p>
							<button type="submit" class="shopBtn">
								<span class=" icon-shopping-cart"></span> Thêm vào giỏ hàng
							</button>
						</form>
					</div>
				</div>
				<hr class="softn clr" />

				<ul id="productDetail" class="nav nav-tabs">
					<li class="active"><a href="#home" data-toggle="tab">Chi
							tiết sản phẩm</a></li>
					<li class=""><a href="#profile" data-toggle="tab">Sản phẩm
							liên quan</a></li>
				</ul>
				<div id="myTabContent" class="tab-content tabWrapper">
					<div class="tab-pane fade active in" id="home">${ product.detail }

					</div>
					<div class="tab-pane fade" id="profile">
						<c:set var="countList" value="${productByIDCategory.size()}" />
						<c:if test="${productByIDCategory.size() > 6}">
							<c:set var="countList" value="6" />
						</c:if>

						<c:forEach var="item" items="${productByIDCategory}" begin="1"
							end="${ countList }" varStatus="loop">
							<div class="row-fluid">
								<div class="span2">
									<img src="<c:url value="/assets/user/img/${ item.img }"/>"
										alt="">
								</div>
								<div class="span6">
									<h5>${ item.name }</h5>
									<p>${ item.title }</p>
								</div>
								<div class="span4 alignR">
									<form class="form-horizontal qtyFrm">
										<h3>
											<fmt:formatNumber type="number" groupingUsed="true"
												value="${ product.price }" />
											₫
										</h3>
										<div class="btn-group">
											<a href="<c:url value="/chi-tiet-san-pham/${item.id_product}"/>" class="shopBtn">Xem</a>
										</div>
									</form>
								</div>
							</div>
							<hr class="soft">
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Body wrapper -->
</body>
</html>