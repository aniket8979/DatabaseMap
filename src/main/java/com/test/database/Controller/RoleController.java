package com.test.database.Controller;


import com.test.database.Model.AddonEntity;
import com.test.database.Model.RoleEntity;
import com.test.database.Repo.AddonRepo;
import com.test.database.Repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RoleController {

    private final RoleRepo roleRepo;
    private final AddonRepo addonRepo;


    @PostMapping("/add")
    public ResponseEntity<?> addRole(@RequestBody RoleEntity role){

        AddonEntity addon = addonRepo.getReferenceById(role.getAddons().stream().findFirst().get().getAddonId());
        Set<AddonEntity> thisAddon = new HashSet<>();
        thisAddon.add(addon);
        role.setAddons(thisAddon);
        roleRepo.save(role);

        return ResponseEntity.ok("Saved");
    }



//    @PostMapping("/addon/{planId}")
//    public ResponseEntity<?> mapAddon(@PathVariable int planId, @RequestBody List<Integer> addon){
//        PlanEntity plan = roleRepo.findById(planId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plan Not Found"));
//        List<AddonEntity> allAddon = addonRepo.findAllById(addon);
//        plan.getUsedAddons().addAll(allAddon);
//        roleRepo.save(plan);
//        return ResponseEntity.ok(plan);
//    }



    @GetMapping("/getall")
    public ResponseEntity<?> getAllRole(){
        return ResponseEntity.ok(roleRepo.findAll());
    }



    @DeleteMapping("/delete/{roleId}")
    public ResponseEntity<?> deleteRole(@PathVariable int roleId){
        roleRepo.deleteById(roleId);
        return ResponseEntity.ok("deleted");
    }


}