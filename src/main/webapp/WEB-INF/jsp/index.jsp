<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<title>JSchool</title>
	  	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
	</head>
	<body ng-app="jschoolApp" class="ng-cloak">
		<div class="generic-container" ng-controller="UserController as ctrl">
			<div class="user-form">
				<form name="userForm" ng-submit="ctrl.submit()">
					<input type="hidden" ng-model="ctrl.user.id"/>
					<table class="user-table">
                        <caption>Пользователь</caption>
						<tr>
							<td valign="middle" style="width:20%">Имя *</td>
							<td valign="middle" style="width:60%">
								<input type="text" name="firstName" ng-model="ctrl.user.firstName" placeholder="Введите ваше имя" required>
							</td>
                            <td style="width:20%">
                                <div class="has-error" ng-show="userForm.firstName.$dirty">
                                    <span ng-show="userForm.firstName.$error.required"> Это поле необходимо</span>
                                </div>
                            </td>
						</tr>
						<tr>
							<td valign="middle">Фамилия *</td>
							<td valign="middle">
								<input type="text" name="lastName" ng-model="ctrl.user.lastName" placeholder="Введите вашу фамилию" required>
							</td>
                            <td style="width:20%">
                                <div class="has-error" ng-show="userForm.lastName.$dirty">
                                    <span ng-show="userForm.lastName.$error.required"> Это поле необходимо</span>
                                </div>
                            </td>
						</tr>
						<tr>
							<td valign="middle">День рождения </td>
							<td valign="middle">
								<input type="text" name="birthday" pattern="[0-9]{4}-[0-1]{1}[0-9]{1}-[0-3]{1}[0-9]{1}" ng-model="ctrl.user.birthday" placeholder="гггг-мм-дд">
							</td>
						</tr>
						<tr>
							<td valign="middle">Логин *</td>
							<td valign="middle">
								<input type="text" name="login" ng-model="ctrl.user.login" placeholder="Введите логин" required ng-minlength="3">
							</td>
                            <td>
                                <div class="has-error" ng-show="userForm.login.$dirty">
                                    <span ng-show="userForm.login.$error.minlength"> Минимум 3 символа</span>
                                    <span ng-show="userForm.login.$error.required"> Это поле необходимо</span>
                                </div>
                            </td>
						</tr>
						<tr>
							<td valign="middle">Пароль *</td>
							<td valign="middle">
								<input type="password" name="password" ng-model="ctrl.user.password" placeholder="Введите пароль" required ng-minlength="6">
							</td>
                            <td>
                                <div class="has-error" ng-show="userForm.password.$dirty">
                                    <span ng-show="userForm.password.$error.minlength"> Минимум 6 символов</span>
                                    <span ng-show="userForm.password.$error.required"> Это поле необходимо</span>
                                </div>
                            </td>
						</tr>
						<tr>
							<td valign="middle">Адрес проживания </td>
							<td valign="middle">
								<input type="text" name="address" ng-model="ctrl.user.address" placeholder="Введите ваш адрес проживания">
							</td>
						</tr>
						<tr>
							<td valign="top">О себе </td>
							<td valign="middle">
								<textarea name="about" wrap="soft" ng-model="ctrl.user.about" placeholder="Напишите что-нибудь о себе"></textarea>
							</td>
						</tr>
					</table >
					<div align="center" class="user-table submit-user-form">
						<input type="submit" class="input-submit" value="{{!ctrl.user.id ? 'Добавить' : 'Обновить'}}" ng-disabled="userForm.$invalid">
						<button type="button" ng-click="ctrl.reset()">Очистить форму</button>
					</div>	
				</form>
			</div>
			<div class="users-field">
				<table class="users-table">
					<caption>Список пользователей</caption>
					<thead>
	                    <tr class="tr">
	                        <th class="th">ID.</th>
	                        <th class="th">Имя Фамилия</th>
	                        <th class="th">Дата рождения</th>
	                        <th class="th">Логин</th>
	                        <th class="th">Адрес</th>
	                        <th class="th prelast">О себе</th>
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
	                        				<button type="button" ng-click="ctrl.edit(u.id)">Редактировать</button>
	                        			</td>
	                        			<td>
	                        				<button type="button" ng-click="ctrl.remove(u.id)">Удалить</button>
	                        			</td>
	                        		</tr>
	                        	</table>
	                        </td>
	                    </tr>
	                </tbody>
				</table>
			</div>
		</div>
		<script src="<c:url value='/static/js/angularjs/angular.min.js' />"></script>
		<script src="<c:url value='/static/js/app.js' />"></script>
		<script src="<c:url value='/static/js/service/UserService.js' />"></script>
		<script src="<c:url value='/static/js/controller/UserController.js' />"></script>
	</body>
</html>
