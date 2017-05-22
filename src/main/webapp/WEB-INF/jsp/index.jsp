<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>JSchool</title>
	  	<style type="text/css">
	  		body {
	  			background-color: #9A9797;
	  		}
			.generic-container {
				font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
				margin: auto;
				margin-top: 20px;
				margin-bottom: 20px;
				padding: 20px;
			}
			table {
				width: 100%;
				margin: auto;
			}
			input {
				width: 100%;
			}
			textarea {
				width: 100%;
				height: 100px;
			}
			.uTab {
				background-color: #9A9797;
				margin: auto;
				margin-top: 20px;
				margin-bottom: 20px;
				border-radius: 4px;
			}
			.usersTable {
				width:80%;
				font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
				font-size: 14px;
				border-radius: 4px;
				border-spacing: 0;
				
				margin: auto;
			}
			.th {
				background: #E7EAE7;
				color: black;
				text-shadow: 0 1px 1px #2D2020;
				padding: 10px 20px;
			}
			.th, .td {
				border-style: solid;
				border-width: 0 1px 1px 0;
				border-color: #9A9797;
			}
			.th:first-child, .td:first-child {
				text-align: left;
			}
			.th:first-child {
				border-top-left-radius: 5px;
				border-bottom-left-radius: 5px;
			}
			.th:last-child {
				border-top-right-radius: 5px;
				border-right: none;
				background: #9A9797;
			}
			.td {
				padding: 10px 20px;
				background: #E7EAE7;
			}
			.tr .td:first-child {
				border-top-left-radius: 5px;
				border-bottom-left-radius: 5px;
			}
			.tr .td:last-child {
				border-top-right-radius: 5px;
				border-bottom-right-radius: 5px;
			}
			.prelast {
				border-top-right-radius: 5px;
			}
		</style>
	</head>
	<body ng-app="jschoolApp" class="ng-cloak">
		<div class="generic-container" ng-controller="UserController as ctrl">
						<div style="width:50%;background-color: #E7EAE7;margin: auto;margin-top: 20px;margin-bottom: 20px;border-radius: 4px">
							<form name="userForm" ng-submit="ctrl.submit()" class="userForm">
								<input type="hidden" ng-model="ctrl.user.id"/>
								<table>
									<caption style="font-size:32">Пользователь</caption>
									<tr>
										<td valign="middle" style="width:20%">Имя *</td>
										<td valign="middle" style="width:100%">
											<input type="text" name="firstName" ng-model="ctrl.user.firstName" class="firstName" placeholder="Введите ваше имя" required>
										</td>
									</tr>
									<tr>
										<td valign="middle">Фамилия *</td>
										<td valign="middle" style="width:100%">
											<input type="text" name="lastName" ng-model="ctrl.user.lastName" class="firstName" placeholder="Введите вашу фамилию" required>
										</td>
									</tr>
									<tr>
										<td valign="middle">День рождения </td>
										<td valign="middle" style="width:100%">
											<input type="date" name="birthday" ng-model="ctrl.user.birthday"data-date-format="dd.MM.yyyy"  class="birthday">
										</td>
									</tr>
									<tr>
										<td valign="middle">Логин *</td>
										<td valign="middle" style="width:100%">
											<input type="text" name="login" ng-model="ctrl.user.login" class="login" placeholder="Введите логин" required ng-minlength="3">
										</td>
									</tr>
									<tr>
										<td valign="middle">Пароль *</td>
										<td valign="middle" style="width:100%">
											<input type="password" name="password" ng-model="ctrl.user.password" class="password" placeholder="Введите пароль" required ng-minlength="6">
										</td>
									</tr>
									<tr>
										<td valign="middle">Адрес проживания </td>
										<td valign="middle" style="width:100%">
											<input type="text" name="address" ng-model="ctrl.user.address" class="address" placeholder="Введите ваш адрес проживания">
										</td>
									</tr>
									<tr>
										<td valign="top">О себе </td>
										<td valign="middle" style="width:100%">
											<textarea name="about" wrap="soft" ng-model="ctrl.user.about" class="about" placeholder="Напишите что-нибудь о себе"></textarea>
										</td>
									</tr>
									
								</table >
								
								<div align="center">
									<!-- style="background:#A5A5EA;width:100px;height:30px;border-radius:4px;border:1px solid #A5A5EA"-->
									<!-- style="background:#EAA5A5;width:100px;height:30px;border-radius:4px;border:1px solid #EAA5A5" -->
									<input type="submit" style="width:130px;height:30px;" value="{{!ctrl.user.id ? 'Добавить' : 'Обновить'}}" ng-disabled="userForm.$invalid">
									<button type="button" style="width:130px;height:30px;" ng-click="ctrl.reset()" ng-disabled="userForm.$pristine">Очистить форму</button>
								</div>	
								
							</form>
						</div>

						<div class="uTab">
							<table class="usersTable">
								<caption style="font-size:32">Список пользователей</caption>
								<thead>
				                    <tr class="tr">
				                        <th class="th">ID.</th>
				                        <th class="th">Имя Фамилия</th>
				                        <th class="th">Дата рождения</th>
				                        <th class="th">Логин</th>
				                        <th class="th">Адрес</th>
				                        <th class="th prelast" id="last">О себе</th>
				                        <th class="th" ></th>
				                    </tr>
				                </thead>
				                <tbody>
				                    <tr class="tr" ng-repeat="u in ctrl.users">
				                        <td class="td"><span ng-bind="u.id"></span></td>
				                        <td class="td">
				                        	<span ng-bind="u.firstName"></span> <span ng-bind="u.lastName"></span>
				                        </td>
				                        <td class="td"><span ng-bind="u.birthday"></span></td>
				                        <td class="td"><span ng-bind="u.login"></span></td>
				                        <td class="td"><span ng-bind="u.address"></span></td>
				                        <td class="td"><span ng-bind="u.about"></span></td>
				                        <td class="td.buttons td">
				                        	<table style="width:280px; height:40px;">
				                        		<tr>
				                        			<td>
				                        				<button type="button" ng-click="ctrl.edit(u.id)" style="width:130px;height:30px;">Редактировать</button>
				                        			</td>
				                        			<td>
				                        				<button type="button" ng-click="ctrl.remove(u.id)" style="width:130px;height:30px;">Удалить</button>
				                        			</td>
				                        		</tr>
				                        	</table>
				                        	
				                        	
				                        </td>
				                    </tr>
				                </tbody>
							</table>
						</div>

		</div>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
		<script src="<c:url value='/static/js/app.js' />"></script>
		<script src="<c:url value='/static/js/service/user_service.js' />"></script>
		<script src="<c:url value='/static/js/controller/user_controller.js' />"></script>
	</body>
</html>
