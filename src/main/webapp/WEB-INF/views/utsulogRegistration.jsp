<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>AWS_UtsuLogアプリケーション</title>
</head>
<body>
	<h1>AWS_UtsuLogアプリケーション（うつ度判定WEBアプリ）</h1>
	<hr />
	<h2>
	最近の２週間を振り返って当てはまる項目にチェックをつけてください。<br />
	「このアプリはWEBフレームワークのJAVA SPRING　FWを使って書かれています。 <br />
	このアプリはAMAZON　WEB　SERVICEクラウド上に設置してあります。 <br />
	田篠昭洋　2017/4/29　GW休暇に趣味で作成　現在RDS DB接続機能をHIBERNATEで実装計画中」<br />
	</h2>
	<hr />
	<form:form modelAttribute="utsulogModel">
	<table border="2" width="1000">
	<tr>
		<td width="120">お名前</td><td><form:input path="name" size="50" /></td>
	</tr>
	<tr>
		<td>年齢</td><td><form:input path="age" size="10" /></td>
	</tr>
	<tr>
		<td>質問大項目</td>
		<td>
			<form:checkboxes items="${bigQuestionLists}" path="bigQuestion" itemLabel="label" itemValue="data" delimiter="<br />" />
		</td>
	</tr>
	<tr>
		<td>質問小項目</td>
		<td>
			<form:checkboxes items="${smallQuestionLists}" path="smallQuestion" itemLabel="label" itemValue="data" delimiter="<br />" />
		</td>
	</tr>
	<tr>
		<td><form:checkbox path="agreement" label="同意します" /></td>
		<td>
		このうつ傾向を測るアプリは医師の診断を代用するものではありません、診断行為を行いません<br />
		少しでも心の状態が気になったら、専門の医師の診断を受けてください。<br />
		このアプリを使用したことによるいかなる事も責任を負いません<br />
		上記の事を同意していただける場合のみこのアプリを使用してください。<br />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="判定" />
		</td>
	</tr>
	</table>
	</form:form>
</body>
</html>