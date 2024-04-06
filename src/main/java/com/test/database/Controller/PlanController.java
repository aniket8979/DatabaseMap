package com.test.database.Controller;


import com.test.database.Model.AddonEntity;
import com.test.database.Model.PlanEntity;
import com.test.database.Model.RoleEntity;
import com.test.database.Repo.AddonRepo;
import com.test.database.Repo.FeatureRepo;
import com.test.database.Repo.PlanRepo;
import com.test.database.Repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PlanController {
    private final PlanRepo planRepo;
    private final AddonRepo addonRepo;
    private final FeatureRepo featureRepo;
    private final RoleRepo roleRepo;


    @PostMapping("/add")
    public ResponseEntity<?> addPlan(@RequestBody PlanEntity plan){
        planRepo.save(plan);
        return ResponseEntity.ok(plan);
    }



    @PostMapping("/addon/{planId}")
    public ResponseEntity<?> mapAddon(@PathVariable int planId, @RequestBody List<Integer> addon){
        PlanEntity plan = planRepo.findById(planId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plan Not Found"));
        List<AddonEntity> allAddon = addonRepo.findAllById(addon);
        plan.getUsedAddons().addAll(allAddon);
        planRepo.save(plan);
        return ResponseEntity.ok(plan);
    }



    @GetMapping("/getall")
    public ResponseEntity<?> getAllPlan(){
        return ResponseEntity.ok(planRepo.findAll());
    }



    @DeleteMapping("/delete/{planId}")
    public ResponseEntity<?> deletePlan(@PathVariable int planId){
        planRepo.deleteById(planId);
        return ResponseEntity.ok("deleted");
    }


    @DeleteMapping("removeAddon/{planId}/{addonId}")
    public ResponseEntity<?> deleteAddon(@PathVariable int planId,@PathVariable int addonId ){

        PlanEntity plan = planRepo.getReferenceById(planId);

        AddonEntity addon = addonRepo.getReferenceById(addonId);


//        plan.getUsedAddons().remove(addon);


        plan.getUsedAddons()
                .removeIf(r -> r.getAddonId()==addonId);

//        System.out.println(addon.getMappedroles());;
        Set<RoleEntity> mappedRoles = addon.getMappedroles();
        for(RoleEntity role : mappedRoles){
            System.out.println("itr round 1 "+ role.getRoleName());
//            role.getMappedUser().stream()
//                    .filter(userEntity -> userEntity.getRole().getAddons()
//                            .removeIf(addonEntity -> addonEntity.getAddonId()==addonId));
            role.getAddons().removeIf(addonEntity -> addonEntity.getAddonId()==addonId);
            roleRepo.save(role);
            roleRepo.flush();
        }

        addon.getMappedroles().clear();
        addonRepo.save(addon);
        addonRepo.delete(addon);

        planRepo.saveAndFlush(plan);
        return ResponseEntity.ok("deleted");
    }



    @DeleteMapping("removeFeature/{planId}/{addonId}/{featureId}")
    public ResponseEntity<?> deleteFeature(
            @PathVariable int planId,
            @PathVariable int addonId,
            @PathVariable int featureId ){

//        FeatureEntity feature = featureRepo.getReferenceById(featureId);
        AddonEntity addon = addonRepo.getReferenceById(addonId);
        addon.getFeatures().removeIf(r->r.getFeatureId()==featureId);

//        addon.getMappedroles().clear();

        featureRepo.deleteById(featureId);
        addonRepo.save(addon);



//        PlanEntity plan = planRepo.getReferenceById(planId);
//        plan.getUsedAddons().stream()
//                .filter(e->e.getFeatures()
//                        .removeIf(f->f.getFeatureId()==featureId));
//
////        addonRepo.saveAndFlush(plan.get)
//        planRepo.saveAndFlush(plan);

//        for(AddonEntity usedAddon : plan.getUsedAddons()) {
//            System.out.println(usedAddon.getFeatures().stream().toList().toString());
//
//        }
//        newPlan.setUsedAddons(newAddon);



        return ResponseEntity.ok("removed");

    }


}