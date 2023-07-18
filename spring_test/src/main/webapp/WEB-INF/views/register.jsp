<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>會員註冊</title>
<link rel="stylesheet" href="/css/register1.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
	crossorigin="anonymous"></script>
</head>
<script>
   
function submitForm(event) {
    event.preventDefault();

    const form = document.getElementById("registerForm");
    const formData = new FormData(form);

    fetch('/register', {
        method: 'POST',
        body: formData
    })
        .then(response => {
            if (response.ok) {
                // 成功處理，可根據需要進行後續操作
                console.log("註冊成功");
            } else {
                // 錯誤處理，獲取錯誤訊息並顯示在頁面上
                response.text().then(errorMessage => {
                   console.log(errorMessage); 
                });
            }
        })
        .catch(error => {
            console.error(error);
        });
}
        function check(){
        	 var username = document.getElementById('name').value;
             var password = document.getElementById('password').value;
             var email = document.getElementById('email').value;

             var specialCharRegex = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/;
             var passwordRegex = /^[a-zA-Z0-9]+$/;
             var emailRegex =  /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;

             if(!specialCharRegex.test(username)){
                 if(emailRegex.test(email)){
                     if(passwordRegex.test(password)){

                         console.log("註冊成功")
							return true;
                     }else{
                    	 alert("密碼只能包含英文數字") 
                     }
                 }else{
                	 alert("email格式錯誤 範例 xxx@xxx.xx")
                	 }
             }else{
                alert ("名稱不能包含特殊字元")
             }
             return false;
        }
           
           
       
    

</script>


<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="index.html"> <img
				src="<c:url value="/images/logo.jpg"/>" style="height: 60px;">
				<span class=" navbar-text">食材行情小幫手</span>
			</a>

			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse justify-content-center"
				id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item me-4"><a class="nav-link" href="select">農產行情</a>
					</li>
					<li class="nav-item me-4"><a class="nav-link" href="select">漁產行情</a>
					</li>

					<li class="nav-item me-4"><a class="nav-link" href="forum">討論區</a>
					</li>
					<li class="nav-item me-4"><a class="nav-link" href="store">商城</a>
					</li>

				</ul>
			</div>

			<div class="ml-auto">
				<a class="nav-link" href="login"> 登入|註冊 </a>
			</div>
		</div>
	</nav>


	<div class="bar">
		<span class="bar-text">會員註冊</span>
	</div>

	<div class="container " style="height: 70vh;">
		<div style="height: 100%;"
			class="row justify-content-center align-items-center">
			<div style="height: 400px; width: 400px; padding: 10px;"
				class="col-4 " id="formbk">
				<form action="register" class="flex-column d-flex "
					id="registerForm" method="post" onsubmit="false"
					enctype="multipart/form-data">
					<h4 class="">會員註冊</h4>
					<input name="name" type="text" class="form-control" id="name"
						placeholder="請輸入姓名" required> <input name="email"
						type="email" class="form-control " id="email"
						placeholder="請輸入電子信箱" required> <input name="password"
						type="password" class="form-control " id="password"
						placeholder="請輸入密碼" required> <label for="photo"
						class="form-label">上傳照片</label> <input type="file"
						class="form-control" id="photo" name="photo" accept="image/*"
						multiple="false">
					<button type="submit" class="btn" onclick="submitForm(event)"
						id="register">註冊</button>


				</form>

			</div>
		</div>
	</div>

</body>



</html>