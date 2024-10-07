<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/layouts/user/taglib.jsp"%>
<header id="header">
	<div class="row">
		<div class="span4">
			<h1>
				<a class="logo" href=""><span>Twitter Bootstrap
						ecommerce template</span> <img
					src="<c:url value = "/assets/user/img/logo-bootstrap-shoping-cart.png"/>"
					alt="bootstrap sexy shop"> </a>
			</h1>
		</div>
		<div class="span4">
			<div class="offerNoteWrapper"></div>
		</div>
		<div class="span4 alignR">
			<p>
				<br> <strong> Support (24/7) : 0800 1234 678 </strong><br>
				<br>
			</p>
			<span class="btn btn-mini">[ ${TotalQuantityCart} ] <span
				class="icon-shopping-cart"></span>
			</span>
		</div>
	</div>
</header>

<!--
Navigation Bar Section 
-->
<div class="navbar">
	<div class="navbar-inner">
		<div class="container">
			<a data-target=".nav-collapse" data-toggle="collapse"
				class="btn btn-navbar"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a>
			<div class="nav-collapse">
				<ul class="nav">
					<c:forEach var="item" items="${menus}">
						<li><a href="${item.url}">${item.name}</a></li>
					</c:forEach>
				</ul>

				<form action="/search" method="GET" class="navbar-search pull-left">
					<input type="text" name="query" placeholder="Tìm kiếm"
						class="search-query span2" required>
				</form>
				<ul class="nav pull-right">
					<c:if test="${not empty Login}">
						<li><a href="">${Login.display_name} </a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
</div>