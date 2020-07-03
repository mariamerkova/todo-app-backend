package com.todoapp.api;

import com.todoapp.entity.TodoList;
import com.todoapp.entity.model.TodoListDTO;
import com.todoapp.service.TodoListService;
import com.todoapp.service.TodoListServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/todo-list")
@AllArgsConstructor
public class TodoListRestApi {

    private TodoListService todoListService;

    @GetMapping("/{id}")
    public ResponseEntity<TodoListDTO> getSingleTodolist(@PathVariable Long id) {
      Optional<TodoListDTO> todoListDTO = todoListService.findById(id);

      if(todoListDTO.isPresent()) {
          return ResponseEntity.ok(todoListDTO.get());
      }
      return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<TodoListDTO>> getAllTodoList() {
        return ResponseEntity.ok(todoListService.findAll());
    }

    @PostMapping
    public ResponseEntity<TodoListDTO> saveTodoList(@RequestBody TodoListDTO todoListDTO) {
          return ResponseEntity.ok(todoListService.save(todoListDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoList(@PathVariable long id) {
        todoListService.delete(id);
      return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<TodoListDTO> replaceTodoList(@PathVariable Long id,
                                                    @RequestBody TodoListDTO newTodoList) {
       todoListService.save(newTodoList);
       return ResponseEntity.ok().build();


    }
}
