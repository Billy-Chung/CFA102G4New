<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!DOCTYPE html>
<html lang="en">

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
  <title>寵一而忠</title>

  <!-- FAVICONS ICON -->
  <link rel="shortcut icon" type="image/png" href="images/favicon.png" />
  <link rel="stylesheet" type="text/css" href="../back_CSS/vendor/star-rating/star-rating-svg.css">
  <link href="../back_CSS/vendor/jquery-nice-select/css/nice-select.css" rel="stylesheet">
  <link href="../back_CSS/css/style.css" rel="stylesheet">

</head>

<body>

  <!--*******************
        Preloader start
    ********************-->
  <%@ include file="../backend_page/Preloader.jsp"%>
  <!--*******************
        Preloader end
    ********************-->


  <!--**********************************
        Main wrapper start
    ***********************************-->
  <div id="main-wrapper">

    <!--**********************************
            Nav header start
        ***********************************-->
     <%@ include file="../backend_page/nav-header.jsp"%>
    <!--**********************************
            Nav header end
        ***********************************-->

    <!--**********************************
            Chat box start
        ***********************************-->
    <%@ include file="../backend_page/chatbox.jsp"%>
    <!--**********************************
            Chat box End
        ***********************************-->




    <!--**********************************
            Header start
        ***********************************-->
   <%@ include file="../backend_page/head.jsp"%>
    <!--**********************************
            Header end ti-comment-alt
        ***********************************-->

    <!--**********************************
            Sidebar start
        ***********************************-->
     <%@ include file="../backend_page/Sidebar.jsp"%>
    <!--**********************************
            Sidebar end
        ***********************************-->

    <!--**********************************
            Content body start
        ***********************************-->
    <div class="content-body">
      <div class="container-fluid">

        <div class="row page-titles">
          <a href=""><button type="button" class="btn light btn-dark">回首頁<span class="btn-icon-end"><i
                  class="fa fa-star"></i></span>
            </button></a>
        </div>

        <div class="row">

          <div class="col-xl-3">
            <div class="card">
              <img class="card-img-top img-fluid" src="./image/dog1.jpg" alt="Card image cap">

              <div class="card-footer">
                <from class="card-link float-end">
                  <button type="button" class="btn btn-rounded btn-outline-danger"><span
                      class="btn-icon-start text-danger"><i class="fas fa-times"></i>
                    </span>刪除照片</button>
                </from>
                <form>
                  <button type="button" class="btn btn-rounded btn-outline-secondary"><span
                      class="btn-icon-start text-secondary"><i class="fa fa-plus color-info"></i>
                    </span>設為封面</button>
                </form>
                <!-- <form>
                  <button type="button" class="btn btn-rounded btn-outline-light"><span
                      class="btn-icon-start text-light"><i class="fa fa-plus color-info"></i>
                    </span>取消封面</button>
                </form> -->
              </div>
            </div>
          </div>




        </div>
      </div>
    </div>
    <!--**********************************
            Content body end
        ***********************************-->


    <!--**********************************
            Footer start
        ***********************************-->
    <%@ include file="../backend_page/footer.jsp"%>
    <!--**********************************
            Footer end
        ***********************************-->


  </div>
  <!--**********************************
        Main wrapper end
    ***********************************-->

  <!--**********************************
        Scripts
    ***********************************-->

  <!-- Rating -->
  <script src="../back_CSS/vendor/global/global.min.js"></script>
  <script src="../back_CSS/vendor/jquery-nice-select/js/jquery.nice-select.min.js"></script>
  <script src="../back_CSS/js/custom.min.js"></script>
  <script src="../back_CSS/js/deznav-init.js"></script>
  <script src="../back_CSS/js/demo.js"></script>
  <script src="../back_CSS/js/styleSwitcher.js"></script>
</body>

</html>