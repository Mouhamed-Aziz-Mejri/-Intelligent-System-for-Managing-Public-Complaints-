package com.example.projet_java.Controller;

import com.example.projet_java.Model.Reclamation;
import com.example.projet_java.Model.ReclamationCategory;
import com.example.projet_java.Service.Impl.ReclamationServiceImpl;
import com.example.projet_java.Service.ReclamationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1")
public class ReclamationController {

    public ReclamationController(ReclamationServiceImpl reclamationServiceImpl) {
        this.reclamationServiceimpl = reclamationServiceImpl;
    }

    private final ReclamationServiceImpl reclamationServiceimpl;

    //@PreAuthorize("hasAuthority('Citoyen')or hasAuthority('Repair_Team')or hasAuthority('Municipality_Staff')")

    @PostMapping("/reclamation/add")
    @PreAuthorize("hasAuthority('Citoyen')or hasAuthority('Admin')")
    public ResponseEntity<String> createReclamation(@RequestBody Reclamation reclamation) {
        String result = reclamationServiceimpl.addReclamation(reclamation);

        if (result.contains("successfully")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

    @GetMapping("/reclamation/getById/{id}")
    @PreAuthorize("hasAuthority('Citoyen')or hasAuthority('Admin')")
    public ResponseEntity<?> getReclamationById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reclamationServiceimpl.getReclamationById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/reclamation/getByNom/{nom}")
    @PreAuthorize("hasAuthority('Citoyen')or hasAuthority('Admin')")
    public ResponseEntity<?> getReclamationByNom(@PathVariable String nom) {
        try {
            return ResponseEntity.ok(reclamationServiceimpl.getReclamationByNom(nom));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/reclamation/getByCategorie/{categorie}")
    @PreAuthorize(" hasAuthority('Admin')")
    public ResponseEntity<?> getReclamationByCategorie(@PathVariable ReclamationCategory categorie) {
        try {
            return ResponseEntity.ok(reclamationServiceimpl.getReclamationByCategorie(categorie));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/reclamation/getAll")
    @PreAuthorize("hasAuthority('Municipality_Staff')or hasAuthority('Admin')")
    public ResponseEntity<?> getAllReclamations() {
try {
            return ResponseEntity.ok(reclamationServiceimpl.getAllReclamations());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

     }

    @GetMapping("/reclamation/getOwnReclamations")
    @PreAuthorize("hasAuthority('Citoyen')or hasAuthority('Admin')")
    public ResponseEntity<?> getOwnReclamations() {
        try {
            return ResponseEntity.ok(reclamationServiceimpl.getOwnReclamations());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

     @PutMapping("/reclamation/update/{id}")
     @PreAuthorize("hasAuthority('Admin')")
     public ResponseEntity<?> updateReclamation(@PathVariable String nom, @RequestBody Reclamation reclamation) {
         try {
             return ResponseEntity.ok(reclamationServiceimpl.updateReclamation(nom, reclamation));
         } catch (RuntimeException e) {
             return ResponseEntity.badRequest().body(e.getMessage());
         }
     }

    @PutMapping("/reclamation/updateOwnReclamation/{ownerName}")
    @PreAuthorize("hasAuthority('Citoyen')or hasAuthority('Admin')")
    public ResponseEntity<?> updateOwnReclamation(@PathVariable String ownerName, @RequestBody Reclamation reclamation) {
        try {
            return ResponseEntity.ok(reclamationServiceimpl.UpdateOwnReclamation(ownerName, reclamation));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/reclamation/delete/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> deleteReclamation(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reclamationServiceimpl.deleteReclamation(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/reclamation/deleteOwnReclamation/{id}")
    @PreAuthorize("hasAuthority('Citoyen')or hasAuthority('Admin')")
    public ResponseEntity<?> deleteOwnReclamation(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reclamationServiceimpl.deleteOwnReclamation(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
