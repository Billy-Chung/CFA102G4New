<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>

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
  <link rel="shortcut icon" type="image/png" href="../back_CSS/images/avatar/1.jpg" />
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
     <%@ include file="../backend_page/Header.jsp"%>
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
                        <img class="img-fluid" src="../back_CSS/images/product/2.jpg" alt="">
                      </div>
                    </div>
                  </div>
                  <div class="col-md-7 col-xxl-12">
                    <div class="new-arrival-content position-relative">
                      <h4><a href="ecom-product-detail.html">Solid Women's V-neck Dark T-Shirt</a></h4>
                      <div class="comment-review star-rating">
                        <ul>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star-half-empty"></i></li>
                          <li><i class="fa fa-star-half-empty"></i></li>
                        </ul>
                        <span class="review-text">(34 reviews) / </span><a class="product-review" href=""
                          data-bs-toggle="modal" data-bs-target="#reviewModal">Write a review?</a>
                        <p class="price">$320.00</p>
                      </div>
                      <p>Availability: <span class="item"> In stock <i
                            class="fa fa-check-circle text-success"></i></span></p>
                      <p>Product code: <span class="item">0405689</span> </p>
                      <p>Brand: <span class="item">Lee</span></p>
                      <p class="text-content">There are many variations of passages of Lorem Ipsum available, but the
                        majority have suffered alteration in some form, by injected humour, or randomised words.</p>
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
                        <img class="img-fluid" src="../back_CSS/images/product/3.jpg" alt="">
                      </div>
                    </div>
                  </div>
                  <div class="col-md-7 col-xxl-12">
                    <div class="new-arrival-content position-relative">
                      <h4><a href="ecom-product-detail.html">Solid Women's V-neck Dark T-Shirt</a></h4>
                      <div class="comment-review star-rating">
                        <ul>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star-half-empty"></i></li>
                          <li><i class="fa fa-star-half-empty"></i></li>
                        </ul>
                        <span class="review-text">(34 reviews) / </span><a class="product-review" href=""
                          data-bs-toggle="modal" data-bs-target="#reviewModal">Write a review?</a>
                        <p class="price">$325.00</p>
                      </div>
                      <p>Availability: <span class="item"> In stock <i
                            class="fa fa-check-circle text-success"></i></span></p>
                      <p>Product code: <span class="item">0405689</span> </p>
                      <p>Brand: <span class="item">Lee</span></p>
                      <p class="text-content">There are many variations of passages of Lorem Ipsum available, but the
                        majority have suffered alteration in some form, by injected humour, or randomised words.</p>
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
                        <img class="img-fluid" src="../back_CSS/images/product/4.jpg" alt="">
                      </div>
                    </div>
                  </div>
                  <div class="col-md-7 col-xxl-12">
                    <div class="new-arrival-content position-relative">
                      <h4><a href="ecom-product-detail.html">Solid Women's V-neck Dark T-Shirt</a></h4>
                      <div class="comment-review star-rating">
                        <ul>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                        </ul>
                        <span class="review-text">(34 reviews) / </span><a class="product-review" href=""
                          data-bs-toggle="modal" data-bs-target="#reviewModal">Write a review?</a>
                        <p class="price">$480.00</p>
                      </div>
                      <p>Availability: <span class="item"> In stock <i
                            class="fa fa-check-circle text-success"></i></span></p>
                      <p>Product code: <span class="item">0405689</span> </p>
                      <p>Brand: <span class="item">Lee</span></p>
                      <p class="text-content">There are many variations of passages of Lorem Ipsum available, but the
                        majority have suffered alteration in some form, by injected humour, or randomised words.</p>
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
                        <img class="img-fluid" src="../back_CSS/images/product/5.jpg" alt="">
                      </div>
                    </div>
                  </div>
                  <div class="col-md-7 col-xxl-12">
                    <div class="new-arrival-content position-relative">
                      <h4><a href="ecom-product-detail.html">Solid Women's V-neck Dark T-Shirt</a></h4>
                      <div class="comment-review star-rating">
                        <ul>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                        </ul>
                        <span class="review-text">(34 reviews) / </span><a class="product-review" href=""
                          data-bs-toggle="modal" data-bs-target="#reviewModal">Write a review?</a>
                        <p class="price">$658.00</p>
                      </div>
                      <p>Availability: <span class="item"> In stock <i
                            class="fa fa-check-circle text-success"></i></span></p>
                      <p>Product code: <span class="item">0405689</span> </p>
                      <p>Brand: <span class="item">Lee</span></p>
                      <p class="text-content">There are many variations of passages of Lorem Ipsum available, but the
                        majority have suffered alteration in some form, by injected humour, or randomised words.</p>
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
                        <img class="img-fluid" src="../back_CSS/images/product/6.jpg" alt="">
                      </div>
                    </div>
                  </div>
                  <div class="col-md-7 col-xxl-12">
                    <div class="new-arrival-content position-relative">
                      <h4><a href="ecom-product-detail.html">Solid Women's V-neck Dark T-Shirt</a></h4>
                      <div class="comment-review star-rating">
                        <ul>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                        </ul>
                        <span class="review-text">(34 reviews) / </span><a class="product-review" href=""
                          data-bs-toggle="modal" data-bs-target="#reviewModal">Write a review?</a>
                        <p class="price">$280.00</p>
                      </div>
                      <p>Availability: <span class="item"> In stock <i
                            class="fa fa-check-circle text-success"></i></span></p>
                      <p>Product code: <span class="item">0405689</span> </p>
                      <p>Brand: <span class="item">Lee</span></p>
                      <p class="text-content">There are many variations of passages of Lorem Ipsum available, but the
                        majority have suffered alteration in some form, by injected humour, or randomised words.</p>
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
                        <img class="img-fluid" src="../back_CSS/images/product/7.jpg" alt="">
                      </div>
                    </div>
                  </div>
                  <div class="col-md-7 col-xxl-12">
                    <div class="new-arrival-content position-relative">
                      <h4><a href="ecom-product-detail.html">Solid Women's V-neck Dark T-Shirt</a></h4>
                      <div class="comment-review star-rating">
                        <ul>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                        </ul>
                        <span class="review-text">(34 reviews) / </span><a class="product-review" href=""
                          data-bs-toggle="modal" data-bs-target="#reviewModal">Write a review?</a>
                        <p class="price">$600.00</p>
                      </div>
                      <p>Availability: <span class="item"> In stock <i
                            class="fa fa-check-circle text-success"></i></span></p>
                      <p>Product code: <span class="item">0405689</span> </p>
                      <p>Brand: <span class="item">Lee</span></p>
                      <p class="text-content">There are many variations of passages of Lorem Ipsum available, but the
                        majority have suffered alteration in some form, by injected humour, or randomised words.</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- review -->
          <div class="modal fade" id="reviewModal">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title">Review</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal">
                  </button>
                </div>
                <div class="modal-body">
                  <form>
                    <div class="text-center mb-4">
                      <img class="img-fluid rounded" width="78" src="./images/avatar/1.jpg" alt="DexignZone">
                    </div>
                    <div class="mb-3">
                      <div class="rating-widget mb-4 text-center">
                        <!-- Rating Stars Box -->
                        <div class="rating-stars">
                          <ul id="stars">
                            <li class="star" title="Poor" data-value="1">
                              <i class="fa fa-star fa-fw"></i>
                            </li>
                            <li class="star" title="Fair" data-value="2">
                              <i class="fa fa-star fa-fw"></i>
                            </li>
                            <li class="star" title="Good" data-value="3">
                              <i class="fa fa-star fa-fw"></i>
                            </li>
                            <li class="star" title="Excellent" data-value="4">
                              <i class="fa fa-star fa-fw"></i>
                            </li>
                            <li class="star" title="WOW!!!" data-value="5">
                              <i class="fa fa-star fa-fw"></i>
                            </li>
                          </ul>
                        </div>
                      </div>
                    </div>
                    <div class="mb-3">
                      <textarea class="form-control" placeholder="Comment" rows="5"></textarea>
                    </div>
                    <button class="btn btn-success btn-block">RATE</button>
                  </form>
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