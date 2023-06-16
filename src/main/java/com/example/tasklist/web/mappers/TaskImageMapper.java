package com.example.tasklist.web.mappers;

import com.example.tasklist.domain.task.TaskImage;
import com.example.tasklist.web.dto.task.TaskImageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskImageMapper extends Mappable<TaskImage, TaskImageDto> {
}
