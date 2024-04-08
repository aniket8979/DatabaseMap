package com.test.database.Controller;


import com.test.database.Model.AddonEntity;
import com.test.database.Model.RoleEntity;
import com.test.database.Model.UserEntity;
import com.test.database.Repo.RoleRepo;
import com.test.database.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserController {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
//    private final AddonRepo addonRepo;


    @PostMapping("/add")
    public ResponseEntity<?> addPlan(@RequestBody UserEntity user){
        RoleEntity role = roleRepo.findById(user.getRole().getRoleId()).get();
        user.setRole(role);
        userRepo.save(user);
        return ResponseEntity.ok("saved");
    }



//    @PostMapping("/addon/{planId}")
//    public ResponseEntity<?> mapAddon(@PathVariable int planId, @RequestBody List<Integer> addon){
//        PlanEntity plan = planRepo.findById(planId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plan Not Found"));
//        List<AddonEntity> allAddon = addonRepo.findAllById(addon);
//        plan.getUsedAddons().addAll(allAddon);
//        planRepo.save(plan);
//        return ResponseEntity.ok(plan);
//    }

    @PostMapping("/mapRole/{userId}/{roleId}")
    public ResponseEntity<?> mapAddon(@PathVariable int userId, @PathVariable int roleId){
        RoleEntity role = roleRepo.findById(roleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role Not Found"));

        UserEntity user = userRepo.findById(userId).get();

        user.setRole(role);

        userRepo.save(user);

        return ResponseEntity.ok(user);
    }



    @GetMapping("/getall")
    public ResponseEntity<?> getAllPlan(){
        return ResponseEntity.ok(userRepo.findAll());
    }



    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deletePlan(@PathVariable int userId){
        userRepo.deleteById(userId);
        return ResponseEntity.ok("deleted");
    }





















}