package com.example.projet_java.Controller;

import com.example.projet_java.Model.Rapport;
import com.example.projet_java.Repository.RapportRepository;
import com.example.projet_java.Service.Impl.RapportServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/rapport")
@PreAuthorize("hasAuthority('Admin')")
public class RapportController {

    private final RapportServiceImpl rapportServiceImpl;
public RapportController(RapportServiceImpl rapportServiceImpl) {
    this.rapportServiceImpl = rapportServiceImpl;
}
    @PostMapping()
    public ResponseEntity<String> generateRapport(@RequestBody Rapport rapportRequest) {
    try{
 return ResponseEntity.ok(rapportServiceImpl.addRapport(rapportRequest)) ;

    }catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating report: " + e.getMessage() );
    }
}
@GetMapping
    public ResponseEntity<?> getAllRapports() {
        try {
            return ResponseEntity.ok(rapportServiceImpl.getAllRapports());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
@GetMapping("{NomRapport}")
    public ResponseEntity<?> getRapportByNom(@PathVariable String NomRapport) {
        try {
            return ResponseEntity.ok(rapportServiceImpl.getRapportByNom(NomRapport));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{NomRapport}")
    public ResponseEntity<String> updateRapport(@PathVariable String NomRapport, @RequestBody Rapport rapportRequest) {
        try {
            return ResponseEntity.ok(rapportServiceImpl.updateRapport(NomRapport, rapportRequest));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/date/{date}")
    public ResponseEntity<?> getRapportsByDate(@PathVariable String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            return ResponseEntity.ok(rapportServiceImpl.getRapportsByDate(localDate.toString()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete/{nomRapport}")
    public ResponseEntity<String> deleteRapport(@PathVariable String nomRapport) {
        try {
            return ResponseEntity.ok(rapportServiceImpl.DeleteRapport(nomRapport));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
