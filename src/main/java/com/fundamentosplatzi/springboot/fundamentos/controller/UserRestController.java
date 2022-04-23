package com.fundamentosplatzi.springboot.fundamentos.controller;

import com.fundamentosplatzi.springboot.fundamentos.caseuse.CreateUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.DeleteUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.GetUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.UpdateUser;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api/users")
@RestController  // Todos los metodos que creamos aca se formateen en Json
public class UserRestController {

    //create ,get,delete,update, creamos paquete caseuse
private GetUser getUser;
private CreateUser createUser;
private DeleteUser deleteUser;
private UpdateUser updateUser;

    public UserRestController(GetUser getUser, CreateUser createUser, DeleteUser deleteUser,UpdateUser updateUser) {
        this.getUser = getUser;
        this.createUser=createUser;
        this.deleteUser=deleteUser;
        this.updateUser=updateUser;
    }

    @GetMapping("/")
    List <User> get(){
return getUser.getAll();

    }
@PostMapping("/")
    ResponseEntity <User>newUser(@RequestBody User newUser){
return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);
}

@DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable Long id){
        deleteUser.remove(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT); // no content es para no responder ningun servicio

}

@PutMapping("/{id}")
ResponseEntity remplaceUser(@RequestBody User newUser, @PathVariable Long id){
        return new ResponseEntity<>( updateUser.udpate(newUser,id),HttpStatus.OK);

}

}