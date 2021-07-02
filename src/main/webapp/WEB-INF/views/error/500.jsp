<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Minishop</title>

    <link href="https://fonts.googleapis.com/css?family=Cabin:400,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:900" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/4.6.0/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
    <script src="${pageContext.request.contextPath}/webjars/jquery/3.5.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/webjars/bootstrap/4.6.0/js/bootstrap.min.js"></script>

    <style>
        * {
            -webkit-box-sizing: border-box;
            box-sizing: border-box;
        }

        body {
            padding: 0;
            margin: 0;
        }

        #error {
            position: relative;
            height: 100vh;
        }

        #error .error {
            position: absolute;
            left: 50%;
            top: 50%;
            -webkit-transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
        }

        .error {
            max-width: 520px;
            width: 100%;
            line-height: 1.4;
            text-align: center;
        }

        .error .error-content {
            position: relative;
            height: 240px;
        }

        .error .error-content h1 {
            font-family: 'Montserrat', sans-serif;
            position: absolute;
            left: 50%;
            top: 50%;
            -webkit-transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
            font-size: 252px;
            font-weight: 900;
            margin: 0px;
            color: #262626;
            text-transform: uppercase;
            letter-spacing: -40px;
            margin-left: -20px;
        }

        .error .error-content h1>span {
            text-shadow: -8px 0px 0px #fff;
        }

        .error .error-content h3 {
            font-family: 'Cabin', sans-serif;
            position: relative;
            font-size: 16px;
            font-weight: 700;
            text-transform: uppercase;
            color: #262626;
            margin: 0px;
            letter-spacing: 3px;
            padding-left: 6px;
        }

        .error h2 {
            font-family: 'Cabin', sans-serif;
            font-size: 20px;
            font-weight: 400;
            text-transform: uppercase;
            color: #000;
            margin-top: 0px;
            margin-bottom: 25px;
        }

        @media only screen and (max-width: 767px) {
            .error .error-content {
                height: 200px;
            }
            .error .error-content h1 {
                font-size: 200px;
            }
        }

        @media only screen and (max-width: 480px) {
            .error .error-content {
                height: 162px;
            }
            .error .error-content h1 {
                font-size: 162px;
                height: 150px;
                line-height: 162px;
            }
            .error h2 {
                font-size: 16px;
            }
        }
    </style>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/nav.jsp" />

    <div id="error">
        <div class="error">
            <div class="error-content">
                <h3>Oops! Internal Server Error</h3>
                <h1><span>5</span><span>0</span><span>0</span></h1>
            </div>
            <h2>Unfortunately we're having trouble loading the page you are looking for. Please come back in a while.</h2>
        </div>
    </div>
</body>
</html>