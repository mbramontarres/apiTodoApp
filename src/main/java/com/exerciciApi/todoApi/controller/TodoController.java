package com.exerciciApi.todoApi.controller;

import com.exerciciApi.todoApi.model.Todo;
import com.exerciciApi.todoApi.model.User;
import com.exerciciApi.todoApi.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/count")
    public ResponseEntity<Long> count () {
        return  ResponseEntity.ok(todoService.count());
    }

    //Get All
    @GetMapping("/all")
    public ResponseEntity<List<Todo>> getTodos () {
        return  ResponseEntity.ok(todoService.findAll());
    }
    //Get Paginated
    @GetMapping
    public ResponseEntity<List<Todo>> getTodosPaginated (@RequestParam(defaultValue="0") int pageNumber,
                                                         @RequestParam(defaultValue="10") int pageLength,
                                                         @RequestParam(defaultValue="id") String sortBy,
                                                         @RequestParam(defaultValue="DESC") String sortDir) {
        if(Objects.equals(sortDir, "ASC")){
            return  ResponseEntity.ok(todoService.findPaginatedAsc(pageNumber,pageLength,sortBy));

        }
        else{
            return  ResponseEntity.ok(todoService.findPaginatedDesc(pageNumber,pageLength,sortBy));
        }

    }

    //Get single todo
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodo (@PathVariable(value = "id") Long id) {
        Optional<Todo> todo = todoService.findById(id);
        if(todo.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"todo Not Found");
        return ResponseEntity.ok(todo.get());
    }

    //Get user todos
    @GetMapping("user/{id}")
    public ResponseEntity<List<Todo>> getUserTodos (@PathVariable(value = "id") Long id) {
        return  ResponseEntity.ok(todoService.findByUser(id));
    }

    //Get todos by title
    @GetMapping("filter={title}")
    public ResponseEntity<List<Todo>> getTodosTitle (@PathVariable(value = "title") String title) {
        return  ResponseEntity.ok(todoService.findByTitle(title));
    }

    //Add todo
    @PostMapping
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {
        try {
            Todo newTodo = todoService.save(todo);
            return ResponseEntity.created(new URI("api/todo" + newTodo.getId())).body(newTodo);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"error adding todo");
        }
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<Todo> putTodo (@PathVariable(value = "id") Long id, @RequestBody Todo newTodo){
        try {
            Todo updatedTodo = todoService.updateTodo(id, newTodo);
            return ResponseEntity.created(new URI("api/todo" + newTodo.getId())).body(updatedTodo);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"error updating todo");
        }
    }
    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Todo> deleteTodo (@PathVariable(value = "id") Long id){
        try {
            todoService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"error deleting todo");
        }
    }
}
