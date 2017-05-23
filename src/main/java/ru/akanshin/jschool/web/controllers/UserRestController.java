package ru.akanshin.jschool.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ru.akanshin.jschool.data.model.User;
import ru.akanshin.jschool.service.IUserService;

/**
 * @author akanshin
 * @version 1.0
 */
@RestController
public class UserRestController {

	@Autowired
	IUserService userService;

	
	/** Получение списка всех пользователей **/

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.getAllUsers(); // Запрашиваем список всех пользователей
		if (users == null) { // В случае пустого списка
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT); // Возвращаем статус NO_CONTENT
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK); // Возвращаем список
	}

	
	/** Получение пользователя по id **/

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {
		User user = userService.getUserById(id); // Запрашиваем пользователя по id
		if (user == null) { // В случае отсутсвия такого пользователя
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND); // Возвращаем статус NOT_FOUND
		}
		return new ResponseEntity<User>(user, HttpStatus.OK); // Возвращаем результат
	}

	
	/** Создание пользователя **/

	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		if (userService.isUserExist(user)) { // Если такой пользователь с таким логином существует
			return new ResponseEntity<Void>(HttpStatus.CONFLICT); // Возвращаем статус  CONFLICT
		}

		userService.createUser(user); // Создаем пользователя

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED); // Возвращаем статус CREATED
	}

	
	/** Обновление пользователя **/
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		if (userService.getUserById(id) == null) { // Если такого пользователя нет
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND); // Возвращаем статус NOT_FOUND
		}
		
		userService.updateUser(user); // Обновляем пользователя
		return new ResponseEntity<User>(user, HttpStatus.OK); // Возвращаем статус OK
	}

	
	/** Удаление пользователя **/

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
		if (userService.getUserById(id) == null) { // Если такого пользователя нет
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND); // Возвращаем статус NOT_FOUND
		}

		userService.deleteUserById(id); // Удаляем пользователя
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT); // Возвращаем статус NO_CONTENT
	}
}
