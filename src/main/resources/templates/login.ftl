<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        html, body {
            height: 100%;
        }
        body {
            background: lightcyan !important;
        }
    </style>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css" type="text/css">

    <title>Login</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light" style="background-color: lightskyblue">
    <div class="container">
        <a class="navbar-brand" href="#">Web Market</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

    </div>
</nav>

<div class="input_product d-flex h-40 justify-content-center p-0">
    <div class="row bg-white shadow-lg-3">
        <div class="col-sm"><div>
                <div class="tableName col-sm" style="margin: 10px; padding: 10px">
                    <form action="/login" method="post">
                        <div>
                            <label for="login" class="form-label">Login:</label>
                            <input type="text" name="login" class="form-control" id="login">
                        </div>
                        <div>
                            <label for="password" class="form-label">Password:</label>
                            <input type="password" name="password" class="form-control" id="password">
                        </div>
                        <div a href="/products" style="margin-top: 20px">
                            <button type="submit" class="btn btn-primary w-100">Login</button>
                        </div>
                    </form>
        </div>
        <div class="col-sm"><div>
    </div>
</div>


                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>