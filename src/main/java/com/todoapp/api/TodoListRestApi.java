package com.todoapp.api;

import com.todoapp.entity.model.TodoListDTO;
import com.todoapp.repository.TodoListRepository;
import com.todoapp.service.TodoListService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/todo-list")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class TodoListRestApi {

    private TodoListService todoListService;

    private TodoListRepository todoListRepository;

    @GetMapping("/{id}")
    public ResponseEntity<TodoListDTO> getSingleTodolist(@PathVariable Long id) {
      Optional<TodoListDTO> todoListDTO = todoListService.findById(id);

      if(todoListDTO.isPresent()) {
          return ResponseEntity.ok(todoListDTO.get());
      }
      return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/owned-to/{name}")
    public ResponseEntity<List<TodoListDTO>> getAllTodoList(@PathVariable String name) {
        return ResponseEntity.ok(todoListService.findAll(name));
    }

    @PostMapping
    public ResponseEntity<TodoListDTO> saveTodoList(Principal principal, @RequestBody TodoListDTO todoListDTO) throws NotFoundException {
        return ResponseEntity.ok(todoListService.save(principal.getName(), todoListDTO));
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
