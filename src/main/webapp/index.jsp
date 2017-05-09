<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>JSchool</title>
		<style>
			.username.ng-valid {
				background-color: lightgreen;
			}
			
			.username.ng-dirty.ng-invalid-required {
				background-color: red;
			}
			
			.username.ng-dirty.ng-invalid-minlength {
				background-color: yellow;
			}
			
			
			.email.ng-valid {
		        background-color: lightgreen;
		    }
		    .email.ng-dirty.ng-invalid-required {
		        background-color: red;
		    }
		    .email.ng-dirty.ng-invalid-email {
		        background-color: yellow;
		    }
		</style>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"/>
        <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
	</head>
	<body ng-app="jschoolApp" class="ng-cloak">
		<div class="generic-container" ng-controller="UserController as ctrl">
			<div class="panel panel-default">
				<div class="panel-heading"><span class="lead">User Registration Form </span></div>
				<div class="formcontainer">
					<form ng-submit="ctrl.submit()" name="userForm" class="form-horizontal">
						<input type="hidden" ng-model="ctrl.user.id" />
						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-2 control-lable" for="file">First name *</label>
								<div class="col-md-7">
									<input type="text" ng-model="ctrl.user.firstName" name="firstName" class="firstName form-control input-sm" placeholder="Enter your first name" required ng-minlength="1"/>
									<div class="has-error" ng-show="userForm.$dirty">
                                    	<span ng-show="userForm.firstName.$error.required">This is a required field</span>
                                    	<span ng-show="userForm.firstName.$error.minlength">Minimum length required is 1</span>
                                    	<span ng-show="userForm.firstName.$invalid">This field is invalid </span>
                                  	</div>
								</div>
							</div>
						</div>
						
						
						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-2 control-lable" for="file">Last name *</label>
								<div class="col-md-7">
									<input type="text" ng-model="ctrl.user.lastName" name="lastName" class="lastName form-control input-sm" placeholder="Enter your last name" required ng-minlength="1"/>
									<div class="has-error" ng-show="userForm.$dirty">
                                    	<span ng-show="userForm.lastName.$error.required">This is a required field</span>
                                    	<span ng-show="userForm.lastName.$error.minlength">Minimum length required is 1</span>
                                    	<span ng-show="userForm.lastName.$invalid">This field is invalid </span>
                                  	</div>
								</div>
							</div>
						</div>
						
						
						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-2 control-lable" for="file">Birthday</label>
								<div class="col-md-7">
									<input type="date" ng-model="ctrl.user.birthDay" name="birthDay" class="form-control input-sm" placeholder="Enter your birthday"/>
									
								</div>
							</div>
						</div>
						
						
						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-2 control-lable" for="file">Login *</label>
								<div class="col-md-7">
									<input type="text" ng-model="ctrl.user.login" name="login" class="login form-control input-sm" placeholder="Enter your login" required ng-minlength="3"/>
									<div class="has-error" ng-show="userForm.$dirty">
	                                   	<span ng-show="userForm.login.$error.required">This is a required field</span>
	                                   	<span ng-show="userForm.login.$error.minlength">Minimum length required is 3</span>
	                                   	<span ng-show="userForm.login.$invalid">This field is invalid </span>
	                               	</div>
                               	</div>
							</div>
						</div>
						
						
						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-2 control-lable" for="file">Password *</label>
								<div class="col-md-7">
									<input type="password" ng-model="ctrl.user.password" name="password" class="password form-control input-sm" placeholder="Enter your password" required ng-minlength="6"/>
									<div class="has-error" ng-show="userForm.$dirty">
	                                   	<span ng-show="userForm.password.$error.required">This is a required field</span>
	                                   	<span ng-show="userForm.password.$error.minlength">Minimum length required is 6</span>
	                                   	<span ng-show="userForm.password.$invalid">This field is invalid </span>
	                               	</div>
                               	</div>
							</div>
						</div>
						
						
						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-2 control-lable" for="file">About yourself</label>
								<div class="col-md-7">
									<input type="text" ng-model="ctrl.user.about" name="about" class="about form-control input-sm"/>
                               	</div>
							</div>
						</div>
						
						
						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-2 control-lable" for="file">Address</label>
								<div class="col-md-7">
									<input type="text" ng-model="ctrl.user.address" name="address" class="address form-control input-sm" placeholder="Enter your address"/>
                               	</div>
							</div>
						</div>
						
						
						<div class="row">
	                        <div class="form-actions floatRight">
	                            <input type="submit"  value="{{!ctrl.user.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="userForm.$invalid">
	                            <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="userForm.$pristine">Reset Form</button>
	                        </div>
                      	</div>
						
					</form>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading"><span class="lead">List of Users </span></div>
				<div class="tablecontainer">
					<table class="table table-hover">
						<thead>
	                        <tr>
	                            <th>ID.</th>
	                            <th>Name</th>
	                            <th>Login</th>
	                            <th>Address</th>
	                            <th width="20%"></th>
	                        </tr>
                    	</thead>
                    	<tbody>
                    		<tr ng-repeat="u in ctrl.users">
                    			<td><span ng-bind="u.id"></span></td>
                    			<td><span ng-bind="u.firstName"></span> <span ng-bind="u.lastName"></span></td>
                    			<td><span ng-bind="u.login"></span></td>
                    			<td><span ng-bind="u.address"></span></td>
                    			<td>
                              		<button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width">Edit</button>
                              		<button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Remove</button>
                              	</td>
                    		</tr>
                    	</tbody>
					</table>
				</div>
			</div>
		</div>
		
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
		<script src="<c:url value='/static/js/app.js' />"></script>
		<script src="<c:url value='/static/js/service/user_service.js' />"></script>
		<script src="<c:url value='/static/js/controller/user_controller.js' />"></script>
	</body>
</html>
