package com.test.database.Controller;


import com.test.database.Model.SchoolEntity;
import com.test.database.Model.UserEntity;
import com.test.database.Repo.SchoolRepo;
import com.test.database.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SchoolController {
    private final SchoolRepo schoolRepo;
    private final UserRepo userRepo;


    @PostMapping("/add")
    public ResponseEntity<?> addschool(@RequestBody SchoolEntity school){
        schoolRepo.save(school);
        return ResponseEntity.ok(school);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllSchool(){
        return ResponseEntity.ok(schoolRepo.findAll());
    }


    @PostMapping("/mapStudent/{schoolId}/{studentId}")
    public ResponseEntity<?> mapStudentSchool(@PathVariable int schoolId, @PathVariable int studentId){
        SchoolEntity school = schoolRepo.getReferenceById(schoolId);
        UserEntity student = userRepo.getReferenceById(studentId);
        school.getStudents().add(student);
        schoolRepo.save(school);
        return ResponseEntity.ok(schoolRepo.findById(schoolId).get());
    }


    @PatchMapping("/removeStudent/{schoolId}/{studentId}")
    public ResponseEntity<?> removeStudentSchool(@PathVariable int schoolId, @PathVariable int studentId){
        SchoolEntity school = schoolRepo.getReferenceById(schoolId);
        UserEntity student = userRepo.getReferenceById(studentId);
        school.getStudents().removeIf(userEntity -> userEntity.getUserId()==studentId);
        schoolRepo.save(school);
        SchoolEntity school1 = schoolRepo.findById(schoolId).get();
        return ResponseEntity.ok(school1);
    }


}