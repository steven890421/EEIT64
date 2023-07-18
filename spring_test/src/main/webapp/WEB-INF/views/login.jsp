<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous" />
  <link rel="stylesheet" type="text/css"
	href="<c:url value='/css/login.css' />">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
    crossorigin="anonymous"></script>

  <title>食材行情小幫手</title>
</head>

<body>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="index.html">
        <img src="<c:url value="/images/logo.jpg"/>" style="height : 60px;">  
          <span class=" navbar-text">食材行情小幫手</span>
      </a>

      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item me-4">
            <a class="nav-link" href="select">農產行情</a>
          </li>
         
          <li class="nav-item me-4">
            <a class="nav-link" href="forum">討論區</a>
          </li>
          <li class="nav-item me-4">
            <a class="nav-link" href="store">商城</a>
          </li>

        </ul>
      </div>

      <div class="ml-auto">
        <a class="nav-link" href="login"> 登入|註冊 </a>
      </div>
    </div>
  </nav>

  <div class="bar">
    <span class="bar-text">會員登入</span>
  </div>
 

	<div class="container " style="height: 70vh;">
		<div style="height: 100%;"
			class="row justify-content-center align-items-center">
			<div id="formbk" style="height: 400px; width: 400px; padding: 10px;"
				class="col-4 ">

				<form class="flex-column d-flex " method="post" id="myForm">
					<h4 class="">會員登入</h4>
					<input type="email" class="form-control " id="email"
						placeholder="請輸入電子信箱" name="email"> <input type="password"
						name="password" class="form-control " id="password"
						placeholder="請輸入密碼">
					<button type="submit" class="btn " id="login">登入</button>
					<div style="margin-top: 15px;"
						class="d-flex justify-content-center">
						<a href="forgetPW">忘記密碼</a>|<a href="register">加入會員</a>
					</div>


				</form>
				<button id="GOOGLE_login" class="btn btn-large btn-primary">GOOGLE
					登入</button>



			</div>
		</div>
	</div>







</body>



<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>



<!--登入、登出按鈕-->



<script src="https://accounts.google.com/gsi/client" async defer></script>
<script>
  window.onload = function () {

    function startSignIn() {
      google.accounts.id.initialize({
        client_id: "897223670846-rrjt29t4ovjp1bl2ribk3i9olke40cgt.apps.googleusercontent.com",
        callback: onSignIn3,

        // prompt_parent_id: "GOOGLE_login" // 設定登入視窗的位置, 若不設定此參數則預設出現在網頁右上角
      });

      google.accounts.id.prompt((notification) => {
        // 如果無法彈出登入視窗 紀錄錯誤訊息
        if (notification.isNotDisplayed() || notification.isSkippedMoment()) {
          console.log(notification);
          console.log("無彈出視窗")
        }
      });

    }

    // 處理登入取得的資訊
    function onSignIn3(response) {
      var credential = response.credential,
        profile = JSON.parse(decodeURIComponent(escape(window.atob(credential.split(".")[1].replace(/-/g, "+").replace(/_/g, "/")))));


      console.log(profile);
      sessionStorage.setItem("googletoken", credential)
      window.location.href = "http://localhost:8080/index.html";
    }

    // 點擊登入
    $("#GOOGLE_login").click(function () {
      // 進行登入程序
      console.log("OK");
      startSignIn();

    });

    // 點擊登出
    $("#GOOGLE_logout").click(function () {
      google.accounts.id.disableAutoSelect();

      // 登出後的動作
      $("#GOOGLE_STATUS_3").html("已登出");
    });
    
    
    const form = document.getElementById("myForm");

    form.addEventListener('submit', (e) => {
      e.preventDefault(); // 阻止默认表单提交行为
      
      const formData = new FormData(form);
      
      fetch('/login', {
        method: 'POST',
        body: formData
      })
        .then(response => response.json())
        .then(data => {
          // 处理后端返回的数据
          console.log(data);
          let token = data.token; 
          sessionStorage.setItem("token", token);
          window.location.href = "http://localhost:8080/index.html";

          console.log("SS");
        })
        .catch(error => {
          // 处理请求错误
          console.error(error);
        });
    });

  }

</script>








</html>