package com.test.database.Controller;


import com.test.database.Model.AddonEntity;
import com.test.database.Model.FeatureEntity;
import com.test.database.Repo.AddonRepo;
import com.test.database.Repo.FeatureRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/addon")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddonController {
    private final AddonRepo addonRepo;
    private final FeatureRepo featureRepo;


    @PostMapping("/add")
    public ResponseEntity<?> addAddon(@RequestBody AddonEntity addon){
        addonRepo.save(addon);
        return ResponseEntity.ok(addon);
    }


    @PostMapping("/feature/{addonId}")
    public ResponseEntity<?> mapAddon(@PathVariable int addonId, @RequestBody List<Integer> features){
        AddonEntity addon = addonRepo.findById(addonId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Addon Not Found"));
        List<FeatureEntity> allfeature = featureRepo.findAllById(features);
        addon.getFeatures().addAll(allfeature);
        addonRepo.save(addon);
        return ResponseEntity.ok(addon);
    }


    @GetMapping("/getall")
    public ResponseEntity<?> getAllAddon(){
        return ResponseEntity.ok(addonRepo.findAll());
    }


    @DeleteMapping("/delete/{addonId}")
    public ResponseEntity<?> deleteAddon(@PathVariable int addonId){
        AddonEntity addon = addonRepo.getReferenceById(addonId);
        addonRepo.deleteById(addonId);
        return ResponseEntity.ok("deleted");
    }












}
