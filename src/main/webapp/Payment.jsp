<%@page import="KDU.IS.Models.Course"%>
<%@page import="KDU.IS.Models.LoginUser"%>
<%@page import="java.util.ArrayList"%>
<%@page import="KDU.IS.Services.CourseServiceImpl"%>
<%@page import="KDU.IS.Services.ICourseService"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Self Defense Learning | KDU</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
</head>
<style>
	.payment-info {
    background: blue;
    padding: 10px;
    border-radius: 6px;
    color: #fff;
    font-weight: bold
}

.product-details {
    padding: 10px
}

body {
    background: #eee
}

.p-about {
    font-size: 12px
}

.table-shadow {
    -webkit-box-shadow: 5px 5px 15px -2px rgba(0, 0, 0, 0.42);
    box-shadow: 5px 5px 15px -2px rgba(0, 0, 0, 0.42)
}

.type {
    font-weight: 400;
    font-size: 10px
}

label.radio {
    cursor: pointer
}

label.radio input {
    position: absolute;
    top: 0;
    left: 0;
    visibility: hidden;
    pointer-events: none
}

label.radio span {
    padding: 1px 12px;
    border: 2px solid #ada9a9;
    display: inline-block;
    color: #8f37aa;
    border-radius: 3px;
    text-transform: uppercase;
    font-size: 11px;
    font-weight: 300
}

label.radio input:checked+span {
    border-color: #fff;
    background-color: blue;
    color: #fff
}

.credit-inputs {
    background: rgb(102, 102, 221);
    color: #fff !important;
    border-color: rgb(102, 102, 221)
}

.credit-inputs::placeholder {
    color: #fff;
    font-size: 13px
}

.credit-card-label {
    font-size: 9px;
    font-weight: 300
}

.form-control.credit-inputs:focus {
    background: rgb(102, 102, 221);
    border: rgb(102, 102, 221)
}

.line {
    border-bottom: 1px solid rgb(102, 102, 221)
}

.information span {
    font-size: 12px;
    font-weight: 500
}

.information {
    margin-bottom: 5px
}
</style>
<body id="page-top">
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
		<div class="container">
			<a class="navbar-brand" href="#page-top"><img
				src="assets/img/navbar-logo.jfif" alt="..." /></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				Menu <i class="fas fa-bars ms-1"></i>
			</button>
			<a class="nav-link"><%=LoginUser.Email%></a>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
					<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- Masthead-->
	<header class="masthead">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="card">

						<div class="card-header text-dark">
							<h1>Payment Gateway</h1>
						</div>
						<div class="card-body text-dark">
							<div class="row">
								<div class="col-md-12">
									<div class="row">
										<div class="col-md-2"></div>
										<div class="col-md-8">
											<div class="container mt-5 p-3 rounded ">
												<div class="row no-gutters d-flex justify-content-center">
													<div class="col-md-5">
														<div class="payment-info">
															<div
																class="d-flex justify-content-between align-items-center">
																<span>Card details</span><img class="rounded"
																	src="https://i.imgur.com/Ke8mQmo.jpg" width="30">
															</div>
															<span class="type d-block mt-3 mb-1">Card type</span><label
																class="radio"> <input type="radio" name="card"
																value="payment" checked> <span><img
																	width="30"
																	src="https://img.icons8.com/color/48/000000/mastercard.png" /></span>
															</label> <label class="radio"> <input type="radio"
																name="card" value="payment"> <span><img
																	width="30"
																	src="https://img.icons8.com/officel/48/000000/visa.png" /></span>
															</label> <label class="radio"> <input type="radio"
																name="card" value="payment"> <span><img
																	width="30"
																	src="https://img.icons8.com/ultraviolet/48/000000/amex.png" /></span>
															</label> <label class="radio"> <input type="radio"
																name="card" value="payment"> <span><img
																	width="30"
																	src="https://img.icons8.com/officel/48/000000/paypal.png" /></span>
															</label>
															<div>
																<label class="credit-card-label">Name on card</label><input
																	type="text" class="form-control credit-inputs"
																	placeholder="Name">
															</div>
															<div>
																<label class="credit-card-label">Card number</label><input
																	type="text" class="form-control credit-inputs"
																	placeholder="0000 0000 0000 0000">
															</div>
															<div class="row">
																<div class="col-md-6">
																	<label class="credit-card-label">Date</label><input
																		type="text" class="form-control credit-inputs"
																		placeholder="12/24">
																</div>
																<div class="col-md-6">
																	<label class="credit-card-label">CVV</label><input
																		type="text" class="form-control credit-inputs"
																		placeholder="342">
																</div>
															</div>
															<hr class="line">
															<div class="d-flex justify-content-between information">
																<span>Subtotal</span><span>$3000.00</span>
															</div>
															<div class="d-flex justify-content-between information">
																<span>Shipping</span><span>$20.00</span>
															</div>
															<div class="d-flex justify-content-between information">
																<span>Total(Incl. taxes)</span><span>$3020.00</span>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="card-footer text-dark">
							<form action="AddStudentCourseServlet" method="POST">
								<input type="hidden" name="studentID" value="<%=request.getParameter("studentID")%>"> 
								<input type="hidden" name="courseID" value="<%=request.getParameter("courseID")%>"> 
								<input type="hidden" name="progress" value="<%=request.getParameter("progress")%>"> 
								<input type="hidden" name="status" value="<%=request.getParameter("status")%>">
								<button type="submit" class="btn btn-info"><span>$3020.00</span><span>Checkout<i
																	class="fa fa-long-arrow-right ml-1"></i></span></button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>



	<!-- Footer-->
	<footer class="footer py-4">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-4 text-lg-start">Copyright &copy; Your
					Website 2021</div>
				<div class="col-lg-4 my-3 my-lg-0">
					<a class="btn btn-dark btn-social mx-2" href="#!"><i
						class="fab fa-twitter"></i></a> <a
						class="btn btn-dark btn-social mx-2" href="#!"><i
						class="fab fa-facebook-f"></i></a> <a
						class="btn btn-dark btn-social mx-2" href="#!"><i
						class="fab fa-linkedin-in"></i></a>
				</div>
				<div class="col-lg-4 text-lg-end">
					<a class="link-dark text-decoration-none me-3" href="#!">Privacy
						Policy</a> <a class="link-dark text-decoration-none" href="#!">Terms
						of Use</a>
				</div>
			</div>
		</div>
	</footer>
	<!-- Portfolio Modals-->

	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- * *                               SB Forms JS                               * *-->
	<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
</html>
