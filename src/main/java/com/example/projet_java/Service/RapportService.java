package com.example.projet_java.Service;

import com.example.projet_java.Model.Rapport;

import java.util.List;

public interface RapportService {

String addRapport(Rapport rapportRequest);
List<Rapport>getAllRapports();
String updateRapport(String NomRapport, Rapport rapportRequest);
Rapport getRapportByNom(String NomRapport);
List<Rapport> getRapportsByDate(String date);
String DeleteRapport(String nomRapport);
}
