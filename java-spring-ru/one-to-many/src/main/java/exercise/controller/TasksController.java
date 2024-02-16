package exercise.controller;

import java.util.ArrayList;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.TaskMapper;
import exercise.mapper.UserMapper;
import exercise.model.Task;
import exercise.repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import exercise.repository.TaskRepository;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    // BEGIN

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    UserMapper userMapper;

    @GetMapping(path = "")
    public ArrayList<TaskDTO> showAllTasks(){
        var taskList = taskRepository.findAll();
        ArrayList<TaskDTO> taskDTOArrayList = new ArrayList<>();

        for(Task task:taskList){
            taskDTOArrayList.add(taskMapper.map(task));
        }
        return taskDTOArrayList;
    }


    @GetMapping(path = "/{id}")
    public TaskDTO showTaskById(@PathVariable long id){
        var task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task with id" + id + " not found"));
        return taskMapper.map(task);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO createTask(@RequestBody TaskCreateDTO taskCreateDTO){
        var task = taskMapper.map(taskCreateDTO);
        taskRepository.save(task);
        return taskMapper.map(task);
    }

    @PutMapping(path = "/{id}")
    public TaskDTO updateTask(@RequestBody TaskUpdateDTO taskUpdateDTO, @PathVariable long id){
        var task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task with id" + id + " not found"));

        task.setTitle(taskUpdateDTO.getTitle());
        task.setDescription(taskUpdateDTO.getDescription());
        task.setAssignee(userRepository.findById(taskUpdateDTO.getAssigneeId()).get());
        taskRepository.save(task);

        return taskMapper.map(task);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable long id){
        //var task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task with id" + id + " not found"));
        taskRepository.deleteById(id);
    }
    // END
}
