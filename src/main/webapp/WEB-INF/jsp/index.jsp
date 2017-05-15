<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="frmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Document management</title>
		<meta charset="utf-8"/>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"/>
		<!-- Latest compiled and minified JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
		<style>
			input[type=file] {
				display: inline;
				width: 85%;
			}
			
			table tbody tr td:first-child {
				text-align: center;
			}
		</style>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-md-8 col-md-offset-2">
					<h1 style="text-align: center;">Document Management</h1>
					<form action="${pageContext.request.contextPath }/delete" method="post">
					<table class="table table-bordered table-striped">
						<thead>
							<tr style="background-color: blue; color: white;">
								<th></th>
								<th>File Name</th>
								<th>File Type</th>
								<th>Upload Date</th>
								<th>Description</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${files.size() == 0 }">
								<tr>
									<td colspan="5">
									There is no file
									</td>
								</tr>
							</c:if>
							<c:forEach items="${files }" var="file">
							<tr>
								<td>
									<input type="checkbox" name="id" value="${file.id }"/>
								</td>
								<td>${file.fileName }</td>
								<td>${file.fileType }</td>
								<td><frmt:formatDate value="${file.uploadDate }" pattern="dd/MM/yyyy HH:mm"/></td>
								<td>${file.description }</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					<a class="btn btn-default" href="#updateFile">Add</a>
					<input class="btn btn-default" value="Delete" type="submit"/>
					
					</form>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-8 col-md-offset-2">
					<div class="panel panel-default" id="updateFile">
						<div class="panel-heading">Document management - upload</div>
						<div class="panel-body">
							<c:if test="${error != null }">${error }</c:if>
							<form action="${pageContext.request.contextPath }/upload" method="post" class="form-horizontal" enctype="multipart/form-data">
								<div class="form-group">
									<label for="file" class="col-md-1 control-label">File</label>
									<div class="col-md-11">
										<input type="file" name="files" multiple="multiple" class="form-control" id="file"/>
										<input type="submit" class="btn btn-default" value="Upload" style="margin-bottom: 5px;"/>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>