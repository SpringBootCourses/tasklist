package com.example.tasklist.web.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Schema(description = "Task Image DTO")
public class TaskImageDto {

    @NotNull(
            message = "Image must be not null."
    )
    private MultipartFile file;

}
