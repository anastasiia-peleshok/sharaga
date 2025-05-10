package com.example.sharagasystem.utility;

import com.example.sharagasystem.model.ProjectNumber;
import com.example.sharagasystem.repository.ProjectNumberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectNumberGenerator {
    private final ProjectNumberRepository projectNumberRepository;

    public String addProjectNumber(String projectType) {
        if (projectNumberRepository.findProjectNumberByType(projectType).isPresent()) {
            throw new IllegalArgumentException("Project number already exists");
        }
        ProjectNumber projectNumber = new ProjectNumber();
        projectNumber.setType(projectType);
        projectNumber.setLastNumber(0L);
        projectNumberRepository.save(projectNumber);
        return projectNumber.getType();
    }

    public String generateProjectNumber(String projectType) {
        ProjectNumber projectNumber = projectNumberRepository.findProjectNumberByType(projectType)
                .orElseThrow(() -> new IllegalArgumentException("Project number not found"));
        projectNumber.setLastNumber(projectNumber.getLastNumber() + 1);
        projectNumberRepository.save(projectNumber);
        return projectNumber.getType() + "-" + projectNumber.getLastNumber();

    }
}
