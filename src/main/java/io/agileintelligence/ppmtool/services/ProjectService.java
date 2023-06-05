package io.agileintelligence.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.exceptions.ProjectIdException;
import io.agileintelligence.ppmtool.repostories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository; 
	
	public Project saveOrUpdateProject(Project project) {
		
		try {
			project.setProjecIdentifier(project.getProjecIdentifier().toUpperCase());
			return projectRepository.save(project);
			
		}  catch (Exception e) {
			throw new ProjectIdException("Project ID: '" + project.getProjecIdentifier().toUpperCase()+"' already exists");
		}
	}
	
	public Project findByProjectIdentifier(String projectId) {
		
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		
		if(project == null) {
			throw new ProjectIdException("Project ID does not exist");
		}
		
		return project;
	}
}
