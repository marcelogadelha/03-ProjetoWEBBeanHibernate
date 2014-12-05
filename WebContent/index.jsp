<%@ page
	info="Desenvolvido por Marcelo Gadelha"
	language="java"
	session="true"
	buffer="10kb"
	autoFlush="true"
	isErrorPage="false"
	errorPage="paginas/erros/erro.html"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index JSP</title>
</head>
<body>

    <form action="${pageContext.request.contextPath}/ServletLogIn" method="post">
	<p>
		<label>numero</label>
		<input type="text" id="numero" name="numero" placeholder="numero" tabindex="1" autofocus="autofocus" />
		<label>nome</label>
		<input type="text" id="nome" name="nome" placeholder="nome" tabindex="2" />
		<label>data</label>
		<input type="date" id="data" name="data" placeholder="00/00/0000" tabindex="3" />
		<!-- type="tel" type="email" -->
		<select name="acao" required>
			<option selected value="inserir">Inserir</option>
			<option value="alterar">Alterar</option>
			<option value="excluir">Excluir</option>
			<option value="consultar">Consultar</option>
		</select><br />

	</p>
	<input type="reset" value="Limpar">
	<input type="submit" value="OK" tabindex="4" />
	</form>
	
	<!--<a href="ServletControle?escolha=listar">listar</a>-->

</body>
</html>