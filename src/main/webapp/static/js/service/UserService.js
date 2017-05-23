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
        //выполняем запрос на получение списка пользователей
        //если данные успешно приняты выполним  deferred.resolve()
        //если произошла ошибка выполним deferred.reject()
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data); //
            },
            function(errResponse){
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    
    /** Запрос создания пользователя **/
    
    function createUser(user) {
        var deferred = $q.defer();
        //выполняем запрос на создание нового пользователя
        //если пользователь успешно создан выполним  deferred.resolve()
        //если произошла ошибка выполним deferred.reject()
        $http.post(REST_SERVICE_URI, user)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    
    /** Запрос обновления пользователя **/

    function updateUser(user, id) {
        var deferred = $q.defer();
        //выполняем запрос на обновление пользователя
        //если пользователь обновлен успешно выполним  deferred.resolve()
        //если произошла ошибка выполним deferred.reject()
        $http.put(REST_SERVICE_URI + id, user)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    
    /** Запрос удаления пользователя **/

    function deleteUser(id) {
        var deferred = $q.defer();
        //выполняем запрос на удаление пользователя
        //если пользователь удален успешно выполним  deferred.resolve()
        //если произошла ошибка выполним deferred.reject()
        $http.delete(REST_SERVICE_URI + id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
	
}]);