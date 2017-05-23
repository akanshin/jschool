'use strict';

/** Сервис **/

angular.module("jschoolApp").factory('UserService', ['$http', '$q', function($http, $q){
	
	var REST_SERVICE_URI = 'http://localhost:8080/jschool/user/';
	
	var factory = {
	        fetchAllUsers: fetchAllUsers,
	        createUser: createUser,
	        updateUser: updateUser,
	        deleteUser: deleteUser
	    };
	
	return factory;
	
	
	/** Запрос списка всех пользователей **/
	
    function fetchAllUsers() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI) // Выполняем GET запрос
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
            	var msg = 'Возникла ошибка при запросе списка пользователей';
                console.error(msg);
                alert(msg);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    
    /** Запрос создания пользователя **/
    
    function createUser(user) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, user)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
            	var msg = 'Возникла ошибка при создании пользователя';
                console.error(msg);
                alert(msg);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    
    /** Запрос обновления пользователя **/

    function updateUser(user, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI + id, user)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
            	var msg = 'Возникла ошибка при обновлении пользователя';
                console.error(msg);
                alert(msg);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    
    /** Запрос удаления пользователя **/

    function deleteUser(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI + id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
            	var msg = 'Возникла ошибка при удалении пользователя';
                console.error(msg);
                alert(msg);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
	
}]);