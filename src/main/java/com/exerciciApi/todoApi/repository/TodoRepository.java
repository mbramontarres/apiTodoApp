package com.exerciciApi.todoApi.repository;

import com.exerciciApi.todoApi.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query("SELECT t FROM Todo t WHERE t.user.id = ?1")
    List<Todo> findByUser(Long id);

    @Query("SELECT t FROM Todo t WHERE t.title like %?1%")
    List<Todo> findByTitle(String title);


    @Query(nativeQuery=true, value="SELECT * FROM Todo  ORDER BY ?3 ASC LIMIT ?2 OFFSET ?1 ")
    List<Todo> findPaginatedAsc(int i, int pageLength,String sortBy);

    Page<Todo> findAll(Pageable pageable);
}
