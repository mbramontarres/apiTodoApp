package com.exerciciApi.todoApi.service;

import com.exerciciApi.todoApi.model.Todo;
import com.exerciciApi.todoApi.model.User;
import com.exerciciApi.todoApi.repository.TodoRepository;
import com.exerciciApi.todoApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class TodoService implements TodoRepository {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public List<Todo> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Todo> findAll(Pageable pageable) {
        return todoRepository.findAll(PageRequest.of(1,1));
    }

    @Override
    public List<Todo> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public List<Todo> findByUser(Long id) {

        return todoRepository.findByUser(id);
    }

    public Todo updateTodo(Long id, Todo newTodo) {
        //Optional<User> user = userRepository.findById(id);
        Optional<Todo> todo = todoRepository.findById(id);
        if(!todo.isEmpty()){
            todo.get().setTitle(newTodo.getTitle());
            todo.get().setCompleted(newTodo.isCompleted());
            todo.get().setUser(newTodo.getUser());
            return todoRepository.save(todo.get());
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "todo not found");
        }
    }
    @Override
    public long count() {
        return todoRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public void delete(Todo entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Todo> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Todo> S save(S todo) {
        return todoRepository.save(todo);
    }

    @Override
    public <S extends Todo> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Todo> findById(Long id) {
        return todoRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Todo> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Todo> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Todo> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Todo getOne(Long aLong) {
        return null;
    }

    @Override
    public Todo getById(Long aLong) {
        return null;
    }

    @Override
    public Todo getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Todo> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Todo> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Todo> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Todo> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Todo> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Todo> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Todo, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    public  List<Todo> findByTitle(String title) {
        return todoRepository.findByTitle(title);
    }

    @Override
    public List<Todo> findPaginatedAsc(int pageNumber, int pageLength, String sortBy) {
        return todoRepository.findAll(PageRequest.of(pageNumber,pageLength,Sort.by(Sort.Direction.ASC, sortBy))).getContent();
    }

    public List<Todo> findPaginatedDesc(int pageNumber, int pageLength, String sortBy) {

        return todoRepository.findAll(PageRequest.of(pageNumber,pageLength,Sort.by(Sort.Direction.DESC, sortBy))).getContent();
    }

}
