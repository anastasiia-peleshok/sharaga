package com.example.sharagasystem.controller;

import com.example.sharagasystem.utility.ProjectNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectNumberGenerator projectNumberGenerator;

  @PostMapping("/{projectType}")
    public String createProject(@PathVariable String projectType) {
      return projectNumberGenerator.addProjectNumber(projectType);
  }
}
