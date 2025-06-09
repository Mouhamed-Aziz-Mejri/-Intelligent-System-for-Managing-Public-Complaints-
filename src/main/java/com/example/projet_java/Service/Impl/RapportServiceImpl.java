package com.example.projet_java.Service.Impl;


import com.example.projet_java.Model.Rapport;
import com.example.projet_java.Repository.RapportRepository;
import com.example.projet_java.Service.RapportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.List;

@Service
public class RapportServiceImpl implements RapportService{
private final RapportRepository rapportRepository;
    public RapportServiceImpl(RapportRepository rapportRepository) {
        this.rapportRepository = rapportRepository;
    }


        @Override
        public String addRapport(Rapport rapportRequest) {
            if (rapportRequest == null) {
                throw new IllegalArgumentException("Rapport request must not be null.");
            }
            if (rapportRepository.existsByNom(rapportRequest.getNom())) {
                throw new IllegalArgumentException("Rapport With name " + rapportRequest.getNom() + " already exists.");
            }
            if (rapportRequest.getNom() == null || rapportRequest.getNom().isBlank()) {
                throw new IllegalArgumentException("Rapport name must not be empty.");
            }
            if (rapportRequest.getContent() == null) {
                throw new IllegalArgumentException("Rapport content must not be null.");
            }
            String fileName = rapportRequest.getNom() + ".txt";
            Path directoryPath = Paths.get(System.getProperty("user.dir"), "Rapport Files");
            Path filePath = directoryPath.resolve(fileName);
            try {
                if (!Files.exists(directoryPath)) {
                    Files.createDirectories(directoryPath);
                }
                Files.write(filePath, rapportRequest.getContent().getBytes(StandardCharsets.UTF_8),
                        StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                rapportRequest.setDate(LocalDate.now());
                rapportRepository.save(rapportRequest);

                return "Report generated successfully at: " + filePath;
            } catch (AccessDeniedException e) {
                throw new RuntimeException("Permission denied when writing to: " + filePath, e);
            } catch (NoSuchFileException e) {
                throw new RuntimeException("Directory does not exist and could not be created: " + directoryPath, e);
            } catch (FileAlreadyExistsException e) {
                throw new RuntimeException("File already exists and cannot be overwritten: " + filePath, e);
            } catch (IOException e) {
                throw new RuntimeException("I/O error while writing the file: " + filePath, e);
            } catch (SecurityException e) {
                throw new RuntimeException("Security manager denied file access: " + filePath, e);
            }
        }

    @Override
    public List<Rapport> getAllRapports() {
        List<Rapport> rapports = rapportRepository.findAll();
        if (rapports.isEmpty()) {
            throw new RuntimeException("No reports found.");
        }
        return rapports;
    }

    @Override
    public String updateRapport(String NomRapport , Rapport rapportRequest) {

        if (rapportRequest == null) {
            throw new IllegalArgumentException("Rapport request must not be null.");
        }
        if (rapportRequest.getNom() == null || rapportRequest.getNom().isBlank()) {
            throw new IllegalArgumentException("Rapport name must not be empty.");
        }
        if (rapportRequest.getContent() == null) {
            throw new IllegalArgumentException("Rapport content must not be null.");
        }
        Rapport existingRapport = rapportRepository.findByNom(NomRapport)
                .orElseThrow(() -> new RuntimeException("Rapport not found with id: " + NomRapport));
        existingRapport.setNom(rapportRequest.getNom());
        existingRapport.setContent(rapportRequest.getContent());
        existingRapport.setDate(LocalDate.now());
        rapportRepository.save(existingRapport);
        return "Rapport updated successfully.";
    }

    @Override
    public Rapport getRapportByNom(String NomRapport) {
    return rapportRepository.findByNom(NomRapport)
                .orElseThrow(() -> new RuntimeException("Rapport not found with Name: " + NomRapport));
    }

    @Override
    public List<Rapport> getRapportsByDate(String date) {

        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format. Please use 'yyyy-MM-dd'.");
        }
        List<Rapport> rapports = rapportRepository.findByDate(localDate);
        if (rapports.isEmpty()) {
            throw new RuntimeException("No reports found for the date: " + date);
        }
        return rapports;
    }

    @Override
    public String DeleteRapport(String nomRapport) {

        if (nomRapport == null || nomRapport.isBlank()) {
            throw new IllegalArgumentException("Rapport name must not be empty.");
        }
        Rapport existingRapport = rapportRepository.findByNom(nomRapport)
                .orElseThrow(() -> new RuntimeException("Rapport not found with name: " + nomRapport));
        rapportRepository.delete(existingRapport);
        String fileName = existingRapport.getNom() + ".txt";
        Path directoryPath = Paths.get(System.getProperty("user.dir"), "Rapport Files");
        Path filePath = directoryPath.resolve(fileName);
        try {
            Files.deleteIfExists(filePath);
            return "Rapport deleted successfully.";
        } catch (IOException e) {
            throw new RuntimeException("Error deleting the file: " + filePath, e);
        }
    }


}
