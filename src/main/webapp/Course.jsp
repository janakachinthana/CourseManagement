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
	<header class="mastheadcourse">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="card">
						<%
						String courseID = request.getParameter("courseID");
						ICourseService courseService = new CourseServiceImpl();

						Course course = courseService.getCourseByID(courseID);
						%>
						<div class="card-header text-dark">
							<h1><%=course.getCourseName()%>
							</h1>
						</div>
						<div class="card-body text-dark">
							<div class="row">
								<div class="col-md-12">
									<div class="row">
										<div class="col-md-6">
											<div class="row">
												<div class="col-md-3">Specification</div>
												<div class="col-md-9">
													<h4>
														For
														<%=course.getSpecification()%></h4>
												</div>
											</div>
											<div class="row">
												<div class="col-md-3">Description</div>
												<div class="col-md-9">
													<h4><%=course.getDescription()%></h4>
												</div>
											</div>
											<div class="row">
												<div class="col-md-3">Status</div>
												<div class="col-md-9">
													<h4><%=course.getStatus()%></h4>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="card-footer text-dark">
							<form action="Payment.jsp" method="POST">
								<input type="hidden" name="studentID"
									value="<%=LoginUser.LoginUserID%>"> <input
									type="hidden" name="courseID" value="<%=course.getCourseID()%>">
								<input type="hidden" name="progress" value="0"> <input
									type="hidden" name="status" value="Enrolled">
								<button type="submit" class="btn btn-info">Enroll Now</button>
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
