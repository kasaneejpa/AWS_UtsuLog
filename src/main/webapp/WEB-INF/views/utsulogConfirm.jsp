<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>判定結果</title>
</head>
<body>
	<h1>AWS_UtsuLogアプリケーション</h1>
	<hr />
	<h2>判定結果</h2>
	<hr />
	<form:form modelAttribute="utsulogModel">
	<table border="2" width="1000">
		<tr>
			<td>選択した大項目</td><td>${bigQuestion}</td>
		</tr>
		<tr>
			<td>選択した小項目</td><td>${smallQuestion}</td>
		</tr>
		<tr>
			<td>コメント</td>
			<td>${comment}</td>
		</tr>
		<tr>
			<form>
				<input type="button" value="戻る" onClick="history.back()">
			</form>
		</tr>
	</table>
	</form:form>
</body>
</html>
