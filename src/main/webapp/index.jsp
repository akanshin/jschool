<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>JSchool</title>
	  	<style type="text/css">
			.generic-container {
				width:80%;
				margin: auto;
				margin-top: 20px;
				margin-bottom: 20px;
				padding: 20px;
				background-color: #EAE7E7;
				border: 1px solid #ddd;
				border-radius: 10px;
				box-shadow: 0 0 30px black;
			}
			.userForm{
				width:80%;
				margin: auto;
				margin-top: 20px;
				margin-bottom: 20px;
				padding: 20px;
				background-color: #E7EAE7;
				border: 1px solid #ddd;
				border-radius: 10px;
			}
			.user-input {
				width:80%;
				margin:5px;
			}
			.userForm-button {
				width:100px;
				height:30px;
				border-radius: 4px;
			}
			.usersTable-panel {
				width:85%;
				margin: auto;
				margin-top: 20px;
				margin-bottom: 20px;
				padding: 20px;
				
			}
			.usersTable {
				width:100%;
				font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
				font-size: 14px;
				border-radius: 10px;
				border-spacing: 0;
				text-align: center;
			}
			.th {
				background: #BCEBDD;
				color: white;
				text-shadow: 0 1px 1px #2D2020;
				padding: 10px 20px;
			}
			.th, .td {
				border-style: solid;
				border-width: 0 1px 1px 0;
				border-color: #EAE5E5;
			}
			.th:first-child, .td:first-child {
				text-align: left;
			}
			.th:first-child {
				border-top-left-radius: 10px;
			}
			.th:last-child {
				border-top-right-radius: 10px;
				border-right: none;
			}
			.td {
				padding: 10px 20px;
				background: #F8E391;
			}
			.tr:last-child td:first-child {
				border-radius: 0 0 0 10px;
			}
			.tr:last-child .td:last-child {
				border-radius: 0 0 10px 0;
			}
			.tr .td:last-child {
				border-right: none;
			}
			
		</style>
	</head>
	<body ng-app="jschoolApp" class="ng-cloak">
		<div class="generic-container" ng-controller="UserController as ctrl">
			<form name="userForm" ng-submit="ctrl.submit()" class="userForm">
				
				<input type="hidden" ng-model="ctrl.user.id"/>
				<table style="width:100%;">
				<caption style="font-size:32">User registration form</caption>
					<tr>
						<td valign="middle">First name *</td>
						<td valign="middle">
							<input type="text" name="firstName" ng-model="ctrl.user.firstName" class="firstName user-input" placeholder="Enter your first name" required>
							<div style="color:red" ng-show="userForm.$dirty">
		                        <span ng-show="userForm.firstName.$error.required">This is a required field</span>
		                        <span ng-show="userForm.firstName.$invalid">This field is invalid </span>
		          			</div>
						</td>
					</tr>
					<tr>
						<td valign="middle">Last name *</td>
						<td valign="middle">
							<input type="text" name="lastName" ng-model="ctrl.user.lastName" class="firstName user-input" placeholder="Enter your last name" required>
							<div style="color:red" ng-show="userForm.$dirty">
		                        <span ng-show="userForm.lastName.$error.required">This is a required field</span>
		                        <span ng-show="userForm.lastName.$invalid">This field is invalid </span>
		          			</div>
						</td>
					</tr>
					<tr>
						<td valign="middle">Birthday </td>
						<td valign="middle">
							<input type="date" name="birthDay" ng-model="ctrl.user.birthDay" class="birthDay user-input" norequired>
						</td>
					</tr>
					<tr>
						<td valign="middle">Login *</td>
						<td valign="middle">
							<input type="text" name="login" ng-model="ctrl.user.login" class="login user-input" placeholder="Enter your login" required ng-minlength="3">
							<div style="color:red" ng-show="userForm.$dirty">
		                        <span ng-show="userForm.login.$error.required">This is a required field</span>
		                        <span ng-show="userForm.login.$error.minlength">Minimum length required is 3</span>
		                        <span ng-show="userForm.login.$invalid">This field is invalid </span>
		          			</div>
						</td>
					</tr>
					<tr>
						<td valign="middle">Password *</td>
						<td valign="middle">
							<input type="password" name="password" ng-model="ctrl.user.password" class="password user-input" placeholder="Enter your password" required ng-minlength="6">
							<div style="color:red" ng-show="userForm.$dirty">
		                        <span ng-show="userForm.password.$error.required">This is a required field</span>
		                        <span ng-show="userForm.password.$error.minlength">Minimum length required is 6</span>
		                        <span ng-show="userForm.password.$invalid">This field is invalid </span>
		          			</div>
						</td>
					</tr>
					<tr>
						<td valign="middle">About </td>
						<td valign="middle">
							<input type="text" name="about" ng-model="ctrl.user.about" class="about user-input" placeholder="Enter about yourself" norequired>
						</td>
					</tr>
					<tr>
						<td valign="middle">Address </td>
						<td valign="middle">
							<input type="text" name="address" ng-model="ctrl.user.address" class="address user-input" placeholder="Enter your address" norequired>
						</td>
					</tr>
					

				</table >
				
				<div align="right">
					<input type="submit"  value="{{!ctrl.user.id ? 'Add' : 'Update'}}" style="background:#A5A5EA;width:100px;height:30px;border-radius:4px;border:1px solid #A5A5EA" class="userForm-button" ng-disabled="userForm.$invalid">
						
					<button type="button" ng-click="ctrl.reset()" class="userForm-button" style="background:#EAA5A5;width:100px;height:30px;border-radius:4px;border:1px solid #EAA5A5" ng-disabled="userForm.$pristine">Reset Form</button>
						
				</div>	
				
			</form>
		
			<div class="usersTable-panel">
			
				<table class="usersTable">
					<caption style="font-size:32">List of users</caption>
					<thead>
	                    <tr class="tr">
	                        <th class="th">ID.</th>
	                        <th class="th">Name</th>
	                        <th class="th">Birthday</th>
	                        <th class="th">Login</th>
	                        <th class="th">Address</th>
	                        <th class="th">About</th>
	                        <th class="th" ></th>
	                    </tr>
	                </thead>
	                <tbody>
	                    <tr class="tr" ng-repeat="u in ctrl.users">
	                        <td class="td"><span ng-bind="u.id"></span></td>
	                        <td class="td">
	                        	<span ng-bind="u.firstName"></span> <span ng-bind="u.lastName"></span>
	                        </td>
	                        <td class="td"><span ng-bind="u.birthDay"></span></td>
	                        <td class="td"><span ng-bind="u.login"></span></td>
	                        <td class="td"><span ng-bind="u.address"></span></td>
	                        <td class="td"><span ng-bind="u.about"></span></td>
	                        <td class="td" style="width:120px!important">
	                        	<button type="button" ng-click="ctrl.edit(u.id)" style="background:#A5EAA5;width:33%;height:30px;border-radius:4px;border:1px solid #A5EAA5">Edit</button>
	                        	<button type="button" ng-click="ctrl.remove(u.id)" style="background:#EAA5A5;width:53%;height:30px;border-radius:4px;border:1px solid #EAA5A5">Remove</button>
	                        </td>
	                    </tr>
	                </tbody>
				</table>
			</div>
		</div>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
		<script src="<c:url value='/static/js/app.js' />"></script>
		<script src="<c:url value='/static/js/service/user_service.js' />"></script>
		<script src="<c:url value='/static/js/controller/user_controller.js' />"></script>
	</body>
</html>
