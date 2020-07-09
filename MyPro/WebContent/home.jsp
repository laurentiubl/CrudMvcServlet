 <%@include file="/WEB-INF/template/header.jsp"%>

<%@page import="java.util.Calendar" %>    

  
<html>
<head>
<title>Ordini</title>
</head>
<body>

                       

<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img class="first-slide" src="resources/img/venice.jpg" alt="First slide">
            <div class="container">
                <div class="carousel-caption">
                    <h1></h1>
                    <p> </p>
                   
                </div>
            </div>
        </div>
        <div class="item">
            <img class="second-slide" src="resources/img/london.jpg" alt="Second slide">
            <div class="container">
                <div class="carousel-caption">
                    <h1>Mihai2</h1>
                    <p>Carousel2.</p>
                    <p><a class="btn btn-lg btn-primary" href="#" role="button">Learn more</a></p>
                </div>
            </div>
        </div>
        <div class="item">
            <img class="third-slide" src="resources/img/milan.jpg" alt="Third slide">
            <div class="container">
                <div class="carousel-caption">
                    <h1>Mihai3</h1>
                    <p>Text</p>
                    <p><a class="btn btn-lg btn-primary" href="#" role="button">Browse gallery</a></p>
                </div>
            </div>
        </div>
    </div>
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div><!-- /.carousel -->




	 <script src="resources/js/jquery-1.11.3.min.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
        
        </body>
        </html>