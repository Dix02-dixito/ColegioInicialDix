<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel= "stylesheet" href="${pageContext.request.contextPath}/Contenido/estilos/login.css">
</head>

<body>

<div class="layout">

    <!-- IZQUIERDA: colegio -->
    <div class="lado-izq"></div>

    <!-- DERECHA: insignia + tu login -->
    <div class="lado-der">

        <div class="logo-box">
            <img src="${pageContext.request.contextPath}/Contenido/Multimedia/Imagen/insignia.jpg" alt="Insignia">
        </div>

        <!-- TU LOGIN (intacto) -->
        <div class="container d-flex justify-content-center align-items-center">
            <div class="card login-card p-4" style="width: 420px;">

                <h3 class="text-center mb-3">🔐 Login System</h3>
                <p class="text-center text-success">Acceso seguro al sistema</p>

                <form action="Login" method="post">

                    <div class="mb-3">
                        <label class="form-label">Usuario</label>
                        <input type="text" name="usuario" action="Login" method="post" autocomplete="off" class="form-control" placeholder="Ingresa código" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Contraseña</label>
                        <input type="password" name="contrasena" class="form-control" type="password" name="contrasena" autocomplete="new-password" placeholder="••••••••" required>
                    </div>

                    <button class="btn btn-login w-100">
                        Ingresar
                    </button>

                </form>

                <div class="text-danger mt-3 text-center">
                    <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
                </div>

            </div>
        </div>

        <footer>
            Dix Academy Inicial / Todos los derechos reservados © 2026
        </footer>

    </div>

</div>

</body>

</html>