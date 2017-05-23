'use strict';

angular.module('jschoolApp').controller('UserController', ['$scope', 'UserService', function($scope, UserService) {
	var self = this;
    self.user={id:null,firstName:'',lastName:'',firstName:'',birthday:'',login:'',password:'',about:'',address:''};
    self.users=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    
    //сразу запросим список пользователей
    fetchAllUsers();
    
    
    /** Получение списка всех пользователей **/
    
    function fetchAllUsers() {
    	//запросим список пользователей
    	//в случае успеха сохраним данные
    	//в случае ошибки выведем сообщение об ошибке
        UserService.fetchAllUsers()
            .then( 
            	function(d) {
                   self.users = d;
            	},
            	function(errResponse ){
                	var msg = 'Возникла ошибка при запросе списка пользователей';
                    console.error(msg);
                    alert(msg);
            	}
            );
    }
    
    
    /** Создание нового пользователя **/
    
    function createUser(user) {
    	//создадим нового пользователя
    	//в случае успеха обновим список
    	//в случае ошибки выведем сообщение об ошибке
        UserService.createUser(user)
            .then(
            fetchAllUsers,
            function(errResponse ){
            	var msg = 'Возникла ошибка при создании пользователя';
                console.error(msg);
                alert(msg);
            }
        );
    }
    
    
    /** Обновление пользователя **/
    
    function updateUser(user, id) {
    	//обновим пользователя
    	//в случае успеха обновим список
    	//в случае ошибки выведем сообщение об ошибке
        UserService.updateUser(user, id)
            .then(
            fetchAllUsers,
            function(errResponse) {
            	var msg = 'Возникла ошибка при обновлении пользователя';
                console.error(msg);
                alert(msg);
            }
        );
    }
    
    
    /** Удаление пользователя **/
    
    function deleteUser(id) {
    	//удалим пользователя
    	//в случае успеха обновим список
    	//в случае ошибки выведем сообщение об ошибке
        UserService.deleteUser(id)
            .then(
            fetchAllUsers,
            function(errResponse) {
            	var msg = 'Возникла ошибка при удалении пользователя';
                console.error(msg);
                alert(msg);
            }
        );
    }
    
    
    /** Обработка пользователя **/
    
    function submit() {
    	//если пользователь не имеет id, то создадим его
    	//иначе обновим пользователя
    	//после очистим данные формы
        if (self.user.id === null) {
            console.log('Saving new user', self.user);
            createUser(self.user);
        } else {
            console.log('Updating user with id =', self.user.id);
            updateUser(self.user, self.user.id);
        }
        reset();
    }
    
    
    /** Редактирование пользователя **/
    
    function edit(id) {
    	//найдем пользователя с таким id и поместим его в форму
        console.log('edit: id =', id);
        for (var i = 0; i < self.users.length; i++) {
        	if (self.users[i].id === id) {
            	self.user = angular.copy(self.users[i]);
                break;
            }
        }
    }
    
    
    /** Удаление пользователя **/
    
    function remove(id) {
    	//удалим пользователя по id
        console.log('id to be deleted', id);
        if (self.user.id === id) {
            reset();
        }
        deleteUser(id);
    }
    
    
    /** Очистка данных пользователя **/
    
    function reset() {
    	//очистим данные формы и user
        self.user={id:null,firstName:'',lastName:'',firstName:'',birthday:'',login:'',password:'',about:'',address:''};
        $scope.userForm.$setPristine();
    }
    
}]);