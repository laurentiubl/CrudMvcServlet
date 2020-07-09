<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<head>
<title>New account</title>

</head>
<body>
<h2>Login</h2>



<main class="login-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">Register</div>
                    <div class="card-body">
                        <form action="Controller" method="POST" name="dati">
                        <input type=hidden name="op" value="register">
                            <div class="form-group row">
                                <label for="acc" class="col-md-4 col-form-label text-md-right">User</label>
                                <div class="col-md-6">
                                    <input type="text" class="form-control" name="acc" required autofocus>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
                                <div class="col-md-6">
                                    <input type="password" class="form-control" name="pass" required>
                                </div>
                            </div>
                            
                            <div class="form-group row">
                                <label for="acc" class="col-md-4 col-form-label text-md-right">Email</label>
                                <div class="col-md-6">
                                    <input type="text" class="form-control" name="email" required autofocus>
                                </div>
                            </div>

                            <div class="form-group row">
                                <div class="col-md-6 offset-md-4">
                                  
                                </div>
                            </div>

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" value="register" name= "register_submit" class="btn btn-primary">
                                    Login
                                </button>
                               
                            </div>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    </div>

</main>



</body>
</html>
