<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
</style>
</head>
<body>
	<h1>Upload with Ajax</h1>

	<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple>
	</div>

	<button id="uploadBtn">Upload</button>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

	<script>
		$(document).ready(function() {

			$("#uploadBtn").on("click", function(e) {

				var formData = new FormData(); // JQuery를 이용하는 경우에 파일 업로드는 FormData라는 객체를 이용하게 된다.
				// FormData는 쉽게 말해서 가상의 <form> 태그와 같다고 생각하면 된다.
				var inputFile = $("input[name='uploadFile']");

				var files = inputFile[0].files;

				console.log(files);

				// add filedate to formdata
				for (var i = 0; i < files.length; i++) {

					formData.append("uploadFile", files[i]);

				}

				$.ajax({
					url : '/uploadAjaxAction',
					processData : false,		// 반드시 false로 지정해야만 전송됨.
					contentType : false,		// 반드시 false로 지정해야만 전송됨.
					data : formData,
					type : 'POST',
					success : function(result) {
						alert("Uploaded");
					}
				}); // $.ajax
			}); //uploadbutton
		}); //end javascript
	</script>

</body>
</html>