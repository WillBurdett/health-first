package com.healthfirst.classesservice.controllers;import com.healthfirst.classesservice.models.ClassInfo;import com.healthfirst.classesservice.services.ClassesService;import java.net.URI;import java.util.List;import javax.servlet.http.HttpServletResponse;import javax.validation.Valid;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.MediaType;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.DeleteMapping;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PathVariable;import org.springframework.web.bind.annotation.PostMapping;import org.springframework.web.bind.annotation.PutMapping;import org.springframework.web.bind.annotation.RequestBody;import org.springframework.web.bind.annotation.RestController;import org.springframework.web.servlet.support.ServletUriComponentsBuilder;@RestControllerpublic class ClassesController {  private final ClassesService classesService;  @Autowired  public ClassesController(ClassesService classesService) {    this.classesService = classesService;  }  @GetMapping("classes")  public List<ClassInfo> getAllClasses(){    return classesService.getAllClasses();  }  @GetMapping("classes/{id}")  public ClassInfo getClassById(@PathVariable Long id) throws ClassNotFoundException {    return classesService.getClassById(id);  }  @PostMapping(      value = "classes",      consumes = MediaType.APPLICATION_JSON_VALUE,      produces = MediaType.APPLICATION_JSON_VALUE  )  public ResponseEntity<ClassInfo> addClass(@Valid @RequestBody ClassInfo classInfo){    ClassInfo savedClass = classesService.addClass(classInfo);    URI location = ServletUriComponentsBuilder.fromCurrentRequest()        .path("/{id}")        .buildAndExpand(savedClass.getId())        .toUri();    return ResponseEntity.created(location).build();  }  @DeleteMapping("classes/{id}")  public void deleteClass(@PathVariable Long id, HttpServletResponse response){    classesService.deleteClass(id);    response.setStatus(204);  }  @PutMapping("classes/{id}")  public void updateClass(@PathVariable Long id, @RequestBody ClassInfo classInfo, HttpServletResponse response){    ClassInfo savedClass = classesService.updateClass(id, classInfo);    response.setStatus(200);  }}