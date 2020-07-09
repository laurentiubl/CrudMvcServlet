<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <%@include file="/WEB-INF/template/header.jsp"%>



<html>
<head>
<title>Ordini</title>
</head>
<body>

<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>All Products</h1>

            <p class="lead">Visualizza Ordini</p>
        </div>

        <table class="table table-striped table-hover">
            <thead>
            <tr class="bg-success">
             
                <th>Name</th>
                <th>Description</th>
            	<th>Quantity</th>
                <th>Location</th>
                 <th>Edit/delete</th>
            </tr>
            </thead>
            <c:forEach items="${listProducts}" var="product">
                <tr>
                    
                    <td>${product.name}</td>
                    <td>${product.description}</td>
                    <td>${product.quantity}</td>
                    <td>${product.location}</td>
                   <td><a href="Controller?op=delete&opp=<c:out value='${product.id}' />">Delete</a>
                   <a href="Controller?op=edit&opp=<c:out value='${product.id}' />">Edit</a>
                    
                    <span class="glyphicon glyphicon-remove-sign"></span></a></td>
                    
                   
                </tr>
            </c:forEach>
        </table>

 <div class=" ult-main-seperator-inner">

 </div>





</body>
</html>