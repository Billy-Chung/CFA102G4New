<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" class="h-100">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="keywords" content="" />
  <meta name="author" content="" />
  <meta name="robots" content="" />
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="Innap : Hotel Admin Template" />
  <meta property="og:title" content="Innap : Hotel Admin Template" />
  <meta property="og:description" content="Innap : Hotel Admin Template" />
  <meta property="og:image" content="social-image.png" />
  <meta name="format-detection" content="telephone=no">

  <!-- PAGE TITLE HERE -->
  <title>寵一而忠登入頁</title>

  <!-- FAVICONS ICON -->
  <link rel="shortcut icon" type="image/png" href="images/favicon.png" />
  <link href="<%=request.getContextPath()%>/back_end/back_CSS/css/style.css" rel="stylesheet">

</head>

<body class="vh-100">
  <div class="authincation h-100">
    <div class="container h-100">
      <div class="row justify-content-center h-100 align-items-center">
        <div class="col-md-6">
          <div class="authincation-content">
            <div class="row no-gutters">
              <div class="col-xl-12">
                <div class="auth-form">
                  <div class="text-center mb-3">
                    <a href="index.html"><img src="images/logo-full.png" alt=""></a>
                  </div>
                  <h4 class="text-center mb-4">後台登入</h4>
                  <form action="<%=request.getContextPath()%>/loginhandler" method="post">
                    <div class="mb-3">
                      <label class="mb-1"><strong>帳號</strong></label>
                      <input type="text" class="form-control" value="請輸入帳號">
                    </div>
                    <div class="mb-3">
                      <label class="mb-1"><strong>密碼</strong></label>
                      <input type="password" class="form-control" value="請輸入密碼">
                    </div>

                    <div class="text-center">
                      <button type="submit" class="btn btn-primary btn-block">登入</button>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>


  <!--**********************************
        Scripts
    ***********************************-->
  <!-- Required vendors -->
  <script src="<%=request.getContextPath()%>/back_end/back_CSS/vendor/global/global.min.js"></script>
  <script src="<%=request.getContextPath()%>/back_end/back_CSS/js/custom.min.js"></script>
  <script src="<%=request.getContextPath()%>/back_end/back_CSS/js/deznav-init.js"></script>
  <script src="<%=request.getContextPath()%>/back_end/back_CSS/js/styleSwitcher.js"></script>
</body>

</html>