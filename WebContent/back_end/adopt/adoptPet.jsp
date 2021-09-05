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

  <style>
    .toServlet {
      display: inline-block;
    }
  </style>

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
          <li class="nav-item">
            <div class="input-group search-area">
              <input type="text" class="form-control" placeholder="Search here">
              <span class="input-group-text"><a href="javascript:void(0)"><i
                    class="flaticon-381-search-2"></i></a></span>
            </div>
          </li>
        </div>
        <div class="row">
          <div class="col-lg-12 col-xl-6">
            <div class="card">
              <div class="card-body">
                <div class="row m-b-30">
                  <div class="col-md-5 col-xxl-12">
                    <div class="new-arrival-product mb-4 mb-xxl-4 mb-md-0">
                      <div class="new-arrivals-img-contnent">
                        <img class="img-fluid" src="./image/dog1.jpg" alt="">
                      </div>
                    </div>
                  </div>
                  <div class="col-md-7 col-xxl-12">
                    <div class="new-arrival-content position-relative">
                      <h2>台灣土狗</h2>
                      <p>來源: <span class="item">0405689</span> </p>
                      <p>晶片號碼: <span class="item">0405689</span> </p>
                      <p>收容編號: <span class="item">0405689</span> </p>
                      <p>性別: <span class="item">Lee</span></p>

                      <div class="rounded-button">

                        <form class="toServlet">
                          <button type="button" class="btn btn-rounded btn-outline-secondary"><span
                              class="btn-icon-start text-secondary"><i class="fa fa-plus color-info"></i>
                            </span>修改詳細資料</button>
                        </form>


                        <form class="toServlet">
                          <button type="button" class="btn btn-rounded btn-outline-success"><span
                              class="btn-icon-start text-success"><i class="fa fa-plus color-info"></i>
                            </span>修改領養狀態</button>

                          <!-- <button type="button" class="btn btn-rounded btn-outline-light"><span
                              class="btn-icon-start text-light"><i class="fa fa-plus color-info"></i>
                            </span>修改領養狀態</button> -->
                        </form>


                        <button type="button" class="btn btn-rounded btn-outline-info" data-bs-toggle="modal"
                          data-bs-target="#modal123"><span class="btn-icon-start text-info"><i
                              class="fa fa-upload color-success"></i>
                          </span>新增寵物照片</button>

                        <div class="modal fade bd-example-modal-lg" id="modal123" tabindex="-1" role="dialog"
                          aria-hidden="true">
                          <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h5 class="modal-title">上傳新圖片</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal">
                                </button>
                              </div>
                             <%@ include file="addPhoto.jsp"%>
                            </div>
                          </div>
                        </div>

                        <from class="toServlet">
                          <button type="button" class="btn btn-rounded btn-outline-warning"><span
                              class="btn-icon-start text-warning"><i class="fa fa-share-alt color-secondary"></i>
                            </span>查看所有照片</button>
                        </from>

                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-12 col-xl-6">
            <div class="card">
              <div class="card-body">
                <div class="row m-b-30">
                  <div class="col-md-5 col-xxl-12">
                    <div class="new-arrival-product mb-4 mb-xxl-4 mb-md-0">
                      <div class="new-arrivals-img-contnent">
                        <img class="img-fluid" src="./image/hi.jpg" alt="">
                      </div>
                    </div>
                  </div>
                  <div class="col-md-7 col-xxl-12">
                    <div class="new-arrival-content position-relative">
                      <h2>台灣黑人</h2>
                      <p>來源: <span class="item">0405689</span> </p>
                      <p>晶片號碼: <span class="item">0405689</span> </p>
                      <p>收容編號: <span class="item">0405689</span> </p>
                      <p>性別: <span class="item">Lee</span></p>

                      <div class="rounded-button">

                        <form class="toServlet">
                          <button type="button" class="btn btn-rounded btn-outline-secondary"><span
                              class="btn-icon-start text-secondary"><i class="fa fa-plus color-info"></i>
                            </span>修改詳細資料</button>
                        </form>


                        <form class="toServlet">
                          <button type="button" class="btn btn-rounded btn-outline-success"><span
                              class="btn-icon-start text-success"><i class="fa fa-plus color-info"></i>
                            </span>修改領養狀態</button>

                          <!-- <button type="button" class="btn btn-rounded btn-outline-light"><span
                              class="btn-icon-start text-light"><i class="fa fa-plus color-info"></i>
                            </span>修改領養狀態</button> -->
                        </form>

							<button type="button" class="btn btn-rounded btn-outline-info" data-bs-toggle="modal"
                          data-bs-target="#modal124"><span class="btn-icon-start text-info"><i
                              class="fa fa-upload color-success"></i>
                          </span>新增寵物照片</button>

                        <div class="modal fade bd-example-modal-lg" id="modal124" tabindex="-1" role="dialog"
                          aria-hidden="true">
                          <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h5 class="modal-title">上傳新圖片</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal">
                                </button>
                              </div>
                              
                              <%@ include file="addPhoto.jsp"%>
                            </div>
                          </div>
                        </div>
                        

                        <from class="toServlet">
                          <button type="button" class="btn btn-rounded btn-outline-warning"><span
                              class="btn-icon-start text-warning"><i class="fa fa-share-alt color-secondary"></i>
                            </span>查看所有照片</button>
                        </from>

                      </div>
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