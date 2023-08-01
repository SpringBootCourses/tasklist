package com.example.tasklist.web.controller;

import com.example.tasklist.domain.task.Task;
import com.example.tasklist.domain.task.TaskImage;
import com.example.tasklist.service.TaskService;
import com.example.tasklist.web.dto.task.TaskDto;
import com.example.tasklist.web.dto.task.TaskImageDto;
import com.example.tasklist.web.dto.validation.OnUpdate;
import com.example.tasklist.web.mappers.TaskImageMapper;
import com.example.tasklist.web.mappers.TaskMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Validated
@Tag(name = "Task Controller", description = "Task API")
public class TaskController {

    private final TaskService taskService;

    private final TaskMapper taskMapper;
    private final TaskImageMapper taskImageMapper;

    @PutMapping
    @MutationMapping(name = "updateTask")
    @Operation(summary = "Update task")
    @PreAuthorize("canAccessTask(#dto.id)")
    public TaskDto update(@Validated(OnUpdate.class)
                          @RequestBody @Argument final TaskDto dto) {
        Task task = taskMapper.toEntity(dto);
        Task updatedTask = taskService.update(task);
        return taskMapper.toDto(updatedTask);
    }

    @GetMapping("/{id}")
    @QueryMapping(name = "taskById")
    @Operation(summary = "Get TaskDto by id")
    @PreAuthorize("canAccessTask(#id)")
    public TaskDto getById(@PathVariable @Argument final Long id) {
        Task task = taskService.getById(id);
        return taskMapper.toDto(task);
    }

    @DeleteMapping("/{id}")
    @MutationMapping(name = "deleteTask")
    @Operation(summary = "Delete task")
    @PreAuthorize("canAccessTask(#id)")
    public void deleteById(@PathVariable @Argument final Long id) {
        taskService.delete(id);
    }

    @PostMapping("/{id}/image")
    @Operation(summary = "Upload image to task")
    @PreAuthorize("canAccessTask(#id)")
    public void uploadImage(@PathVariable final Long id,
                            @Validated
                            @ModelAttribute final TaskImageDto imageDto) {
        TaskImage image = taskImageMapper.toEntity(imageDto);
        taskService.uploadImage(id, image);
    }

}
