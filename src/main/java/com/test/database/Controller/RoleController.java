package com.test.database.Controller;


import com.test.database.Model.AddonEntity;
import com.test.database.Model.RoleEntity;
import com.test.database.Repo.AddonRepo;
import com.test.database.Repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.LexerInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
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



    @PostMapping("/mappedAddon/{roleId}")
    public ResponseEntity<?> mapAddon(@PathVariable int roleId, @RequestBody List<Integer> addons){
        RoleEntity role = roleRepo.findById(roleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role Not Found"));

        List<AddonEntity> allAddon = addonRepo.findAllById(addons);
        role.getAddons().addAll(allAddon);
        roleRepo.save(role);

        return ResponseEntity.ok(role);
    }



    @GetMapping("/getall")
    public ResponseEntity<List<RoleEntity>> getAllRole(){
        List<RoleEntity> all = roleRepo.findAll();
//        System.out.println(all.get(0).getAddons().stream().findFirst().get().getAddonId());
        return ResponseEntity.ok(all);
    }



    @DeleteMapping("/delete/{roleId}")
    public ResponseEntity<?> deleteRole(@PathVariable int roleId){
        roleRepo.deleteById(roleId);
        return ResponseEntity.ok("deleted");
    }


}