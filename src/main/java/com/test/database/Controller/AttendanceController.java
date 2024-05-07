package com.test.database.Controller;

import com.test.database.Model.Attendance;
import com.test.database.Repo.AttendanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceRepo attendanceRepo;

    @PostMapping("/create")
    public Attendance createAttendance(@RequestBody Attendance attendance) {
        return attendanceRepo.save(attendance);
    }

    @GetMapping("/read")
    public List<Attendance> readAllAttendances() {
        List<Attendance> all = attendanceRepo.findAll();
        LocalDate date = all.get(0).getDate().stream().findFirst().get();
        List<LocalTime> times = all.get(0).getTimes().get(date);
        System.out.println("time1: "+ times.get(0));
        System.out.println("time2: "+ times.get(1));
        return attendanceRepo.findAll();
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Attendance> readAttendance(@PathVariable Long id) {
        Attendance attendance = attendanceRepo.findById(id).orElseThrow(() -> new RuntimeException("Attendance not found"));
        return ResponseEntity.ok(attendance);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Attendance> updateAttendance(@PathVariable Long id, @RequestBody Attendance attendanceDetails) {
        Attendance attendance = attendanceRepo.findById(id).orElseThrow(() -> new RuntimeException("Attendance not found"));
        attendance.setDate(attendanceDetails.getDate());
        attendance.setTimes(attendanceDetails.getTimes());
        Attendance updatedAttendance = attendanceRepo.save(attendance);
        return ResponseEntity.ok(updatedAttendance);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id) {
        Attendance attendance = attendanceRepo.findById(id).orElseThrow(() -> new RuntimeException("Attendance not found"));
        attendanceRepo .delete(attendance);
        return ResponseEntity.ok().build();
    }


}
