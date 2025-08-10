<jsp:useBean id='errorBean' scope='request' class='com.thinking.machines.hr.beans.ErrorBean' />
<!DOCTYPE HTML>
<html lang = 'en'>
<head>
<title>HR Application</title>
<link rel='stylesheet' type='text/css' href='/styletwo/css/loginFormStyles.css'></link>
</head>
<body>
<!-- Main container starts here -->
<div class='main-container'>
<!-- Header container starts here -->
<div class='header-container'>
<img src='/styletwo/images/logo.png' class='logo'><div class='brand-name'>Thinking Machines</div>
</div><!-- Header container ends here -->
<!-- content-section starts here -->
<div class='content'>

<div class='content-panel'><!-- content panel starts here -->

<! -- Panel title starts here -->
<span class='login-panel-title'>
Administrator
</span>
<! -- Panel title ends here -->
<! -- Login section starts here -->
<div class='login-module'>
<span class='error' id='loginErrorSection'>
${errorBean.error}
<br><br>
</span>
<form method='post' action='Login.jsp' >
Username: <input type='text' id='username' name='username' placeholder='Username' maxlength='15' required><br><br>
Password: <input type='password' id='password' name='password' placeholder='Password' maxlength='15' required><br><br>
<button type='submit' class='login-button'>Login </button>

</form>	
</div>
<! -- Login section ends here -->


</div><!-- content panel ends here -->


<!-- footer starts here -->
<div class='footer'>
&copy; Thinking Machines 2025
</div> <!-- footer ends here -->
</div> <!-- Main container ends here -->
</body>
</html>
