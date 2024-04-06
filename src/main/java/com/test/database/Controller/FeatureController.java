package com.test.database.Controller;


import com.test.database.Model.FeatureEntity;
import com.test.database.Repo.FeatureRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/feature")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FeatureController {
    private final FeatureRepo featureRepo;


    @PostMapping("/add")
    public ResponseEntity<?> addFeaure(@RequestBody FeatureEntity feature){
        featureRepo.save(feature);
        return ResponseEntity.ok(feature);
    }


    @GetMapping("/getall")
    public ResponseEntity<?> getAllFeature(){
        return ResponseEntity.ok(featureRepo.findAll());
    }


    @DeleteMapping("/delete/{featureId}")
    public ResponseEntity<?> deleteFeature(@PathVariable int featureId){
        FeatureEntity feature = featureRepo.getReferenceById(featureId);
        feature.getAddon().clear();
        featureRepo.saveAndFlush(feature);
        featureRepo.deleteById(featureId);
        return ResponseEntity.ok("deleted");
    }




}
