<%@page import="KDU.IS.Models.Course"%>
<%@page import="KDU.IS.Models.LoginUser"%>
<%@page import="java.util.ArrayList"%>
<%@page import="KDU.IS.Services.CourseServiceImpl"%>
<%@page import="KDU.IS.Services.ICourseService"%>
<%@page import="KDU.IS.Models.Lecture"%>
<%@page import="KDU.IS.Services.LectureServiceImpl"%>
<%@page import="KDU.IS.Services.ILectureService"%>
<%@page import="java.util.ArrayList"%>
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
<body id="page-top ">
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
		String CourseID = request.getParameter("courseID");
		ICourseService courseService = new CourseServiceImpl();
		Course course = courseService.getCourseByID(CourseID);
		%>
		<div class="page-header min-height-150 border-radius-xl mt-4 ">
			<h2 class="mask  bg-info  text-center opacity-6 text-light">
				<%=course.getCourseName()%></h2>
		</div>
		<%
		ILectureService lectureService = new LectureServiceImpl();

		ArrayList<Lecture> lectureList = lectureService.getLectures();
		for (Lecture lecture : lectureList) {
			if (course.getCourseID().equals(lecture.getCourseID())) {
		%>
	<br> <br>
		<div class="container-fluid px-2 px-md-4">

			<div class="card card-body mx-3 mx-md-4 mt-n6">

				<div class="row">
					<div class="row">
						<div class="col-12 mt-4">
							<div class="row">

								<div class=" col-md-12 mb-xl-0 mb-4">
									<div class="card card-blog card-plain">
										<div class="card-header p-0 mt-n4 mx-3">
											<h1 class="text-center bg-dark text-light"><%=lecture.getLectureName()%></h1>
											<a class="d-block shadow-xl border-radius-xl"> 
											<%
 												if (lecture.getFileUrl().endsWith("pdf") ) {
 											%> 
 											
 											<object data="assets/Files/<%=lecture.getFileUrl()%>#page=2"
													type="application/pdf" width="100%" height="1000px">
													<p>
														Your browser does not support PDFs. <a
															href="https://example.com/test.pdf">Download the PDF</a>
														.
													</p>
											</object> 
											<%
												 }else if(lecture.getFileUrl().endsWith("png") || lecture.getFileUrl().endsWith("jpg") ||lecture.getFileUrl().endsWith("jpeg")){
											 %>
											  <img class="mx-auto d-block"  src="assets/Files/<%=lecture.getFileUrl()%>">
											
											<%
												 }else{
											 %>
											  <video width="100%" controls>
													<source src="assets/Files/<%=lecture.getFileUrl()%>"
														type="video/mp4">
													Your browser does not support HTML video.
											</video>
											<%
												}
											%>
											</a>
										</div>
										<div class="card-body p-3">
											<h3 class="text-center text-dark">
												<b><%=lecture.getStatus()%></b>
											</h3>
											<div
												class="d-flex align-items-center justify-content-between">
												<form action="QuizView.jsp" method="POST">
													<input type="hidden" name="lectureID"
														value="<%=lecture.getLectureID()%>">
													<button type="submit"
														class="btn btn-outline-info btn-sm mb-0">Go to
														Quiz</button>
												</form>
											</div>
											<p class="mb-0 text-sm text-dark">
												<b><%=lecture.getDescription()%></b>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%
		}
		}
		%>
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
