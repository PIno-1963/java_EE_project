<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Form</title>
</head>
<link rel="stylesheet" type="text/css" href="styles.css">
<body>
<div class="form">
    <div class="title">Welcome</div>
    <div class="subtitle">Enter your information </div>
    <form method="post" action="hello-servlet">
        <div class="input-container ic1">
            <input id="firstname" class="input" type="text" name="firstname" placeholder=" " />
            <div class="cut"></div>
            <label for="firstname" class="placeholder">First name</label>
        </div>
        <div class="input-container ic2">
            <input id="lastname" class="input" type="text" name="lastname" placeholder=" " />
            <div class="cut"></div>
            <label for="lastname" class="placeholder">Last name</label>
        </div>
        <div class="input-container ic2">
            <input id="email" class="input" type="text" name="email" placeholder=" " />
            <div class="cut cut-short"></div>
            <label for="email" class="placeholder">Email</label>
        </div>
        <button type="submit" class="submit">Submit</button>
    </form>
</div>

</body>
</html>

