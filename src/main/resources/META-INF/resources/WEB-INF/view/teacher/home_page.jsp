<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Home</title>
		<%@include file='../components/link.html'%>
	</head>
	<body>
		<%@include file="../components/header.jsp"%>
		<c:if test="${not empty success}">
		<div class="alert alert-success alert-dismissible fade show" role="alert">
			${success}
			<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		</div>
	</c:if>
		<a href="<%=request.getContextPath()%>/quiz/addQuiz">Add a new Quiz</a >
	<c:choose>
	<c:when test="${quizzes != null}">
		<table cellpadding="5" cellspacing="5">
			<thead>
				<tr>
					<th>id</th>
					<th>title</th>
					<th>duration</th>
					<th>Subject</th>
					<th>maximum marks</th>
					<th>avg score</th>
					<th>Edit</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="q" items="${quizzes}">
					<tr>
						<td>${q.quiz_id}</td>
						<td>${q.quiz_title}</td>
						<td>${q.duration} min</td>
						<td>${q.subject}</td>
						<td>${q.total_max_marks}</td>
						<td>${q.avg_score}</td>
						<td><a href="<%=request.getContextPath()%>/quiz/${q.quiz_id}">View Quiz</a></td>
						<td><a href="<%=request.getContextPath()%>/quiz/update/${q.quiz_id}">Update</a>
							<a href="<%=request.getContextPath()%>/quiz/delete/${q.quiz_id}"
							   onclick="return confirm('You are Deleting ${q.quiz_title} Confirm?')">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>

		<%@include file="../components/footer.html"%>
	</body>
</html>