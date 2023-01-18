package com.healthfirst.classesservice.services;import com.healthfirst.classesservice.exceptions.ClassInfoNotFoundException;import com.healthfirst.classesservice.models.ClassInfo;import com.healthfirst.classesservice.repositories.ClassesRepo;import java.util.List;import java.util.Optional;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;@Servicepublic class ClassesService {  private final ClassesRepo classesRepo;  @Autowired  public ClassesService(ClassesRepo classesRepo) {    this.classesRepo = classesRepo;  }  public List<ClassInfo> getAllClasses(){    return classesRepo.findAll();  }  public ClassInfo getClassById(Long id) throws ClassNotFoundException {    Optional<ClassInfo> ClassInfo=  classesRepo.findAll().stream()        .filter(m -> m.getId() == id)        .findFirst();    if(ClassInfo.isPresent() ){      return ClassInfo.get();    }    throw new ClassNotFoundException("ClassInfo not found with id " + id);  }  public ClassInfo addClass(ClassInfo ClassInfo) {    ClassInfo savedClassInfo = classesRepo.save(ClassInfo);    return savedClassInfo;  }  public void deleteClass(Long id) {    classesRepo.deleteById(id);  }  public ClassInfo updateClass(Long id, ClassInfo ClassInfo) {    Optional<ClassInfo> curr = classesRepo.findAll().stream().filter(m -> m.getId() == id).findFirst();    if (curr.isPresent()){      curr.get().setClassName(ClassInfo.getClassName());      curr.get().setInstructor(ClassInfo.getInstructor());      curr.get().setClassType(ClassInfo.getClassType());      curr.get().setClassTime(ClassInfo.getClassTime());      classesRepo.save(curr.get());      return curr.get();    }    throw new ClassInfoNotFoundException("ClassInfo not found with id " + id);  }}