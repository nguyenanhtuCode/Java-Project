<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@include file="/WEB-INF/views/layouts/user/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<style>
body {
	background-color: #f8f9fa; /* Màu nền nhẹ */
}

.contact-form {
	background-color: #ffffff;
	padding: 30px;
	border-radius: 5px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
	margin-top: 30px;
}

.contact-form h2 {
	margin-bottom: 20px;
	text-align: center;
}

.copyright {
	margin-top: 40px;
	text-align: center;
	padding: 20px 0;
}
</style>
</head>
<body>
	<div class="container mt-5">
		<div class="contact-form">
			<h2>Liên Hệ Với Chúng Tôi</h2>
			<form action="<c:url value='/submit-contact'/>" method="POST">
				<div class="form-group">
					<label for="name">Tên:</label> <input type="text"
						class="form-control" id="name" name="name" required>
				</div>
				<div class="form-group">
					<label for="email">Email:</label> <input type="email"
						class="form-control" id="email" name="email" required>
				</div>
				<div class="form-group">
					<label for="phone">Số điện thoại (tùy chọn):</label> <input
						type="tel" class="form-control" id="phone" name="phone">
				</div>
				<div class="form-group">
					<label for="message">Tin nhắn:</label>
					<textarea class="form-control" id="message" name="message" rows="5"
						required></textarea>
				</div>
				<button type="submit" class="btn btn-primary">Gửi</button>
			</form>
		</div>
	</div>
</body>
</html>