package com.example.tasklist.domain.task;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class TaskImage {

    private MultipartFile file;

}
