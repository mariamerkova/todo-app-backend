package com.todoapp.api;

import com.todoapp.entity.model.TodoListDTO;
import com.todoapp.service.TodoListService;
import lombok.AllArgsConstructor;
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
        Optional<TodoListDTO> todoListDTO = todoListService.findById(id);

        if (todoListDTO.isPresent()) {
            todoListService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<TodoListDTO> updateTodoList(@RequestBody TodoListDTO newTodoList) {

        Optional<TodoListDTO> todoListDTO = todoListService.findById(newTodoList.getId());

        if (todoListDTO.isPresent()) {
            return ResponseEntity.ok(todoListService.update(newTodoList));
        }

        return ResponseEntity.notFound().build();
    }
}
