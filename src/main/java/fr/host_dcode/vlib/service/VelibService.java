package fr.host_dcode.vlib.service;

import fr.host_dcode.vlib.model.Station;
import fr.host_dcode.vlib.model.VelibApiResponse;
import fr.host_dcode.vlib.model.VelibFields;
import fr.host_dcode.vlib.repository.StationRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class VelibService {
    private final WebClient webClient;
    private final StationRepository stationRepository;

    public VelibService(WebClient webClient, StationRepository stationRepository) {
        this.webClient = webClient;
        this.stationRepository = stationRepository;
    }

    public void fetchAndSaveVelibData() {

        String queryUri = "?dataset=velib-disponibilite-en-temps-reel&rows=50";

        VelibApiResponse apiResponse = webClient.get()
                .uri(queryUri)
                .retrieve()
                .bodyToMono(VelibApiResponse.class)
                .block(java.time.Duration.ofSeconds(30));

        if (apiResponse == null || apiResponse.getRecords() == null || apiResponse.getRecords().isEmpty()) {
            System.out.println("Aucune donnée Velib reçue.");
            return;
        }

        apiResponse.getRecords().stream()
                .map(record -> {
                    VelibFields fields = record.getFields();

                    List<Double> coords = fields.getCoordonnees_geo();
                    double latitude = (coords != null && coords.size() >= 1) ? coords.get(0) : 0.0;
                    double longitude = (coords != null && coords.size() >= 2) ? coords.get(1) : 0.0;

                    String description = fields.getName() + ". Dernière mise à jour: " + (fields.getDuedate() != null ? fields.getDuedate() : "inconnue");

                    Station station = new Station(
                            fields.getName(),
                            record.getRecordId(),
                            fields.getStation_code(),
                            fields.getCity(),
                            latitude,
                            longitude,
                            description
                    );

                    return station;
                })
                .forEach(station ->{
                    Station existingStation = stationRepository.findByStationCode(station.getStationCode());
                    if(existingStation == null){
                        stationRepository.save(station);
                    } else {
                        existingStation.setName(station.getName());
                        //ADD ALL UPDATES
                        stationRepository.save(existingStation);
                    }
                });
    }


    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        System.out.println("🚀 Démarrage de l'application détecté. Lancement de la récupération des données Velib...");

        try {
            fetchAndSaveVelibData();
            System.out.println("✅ Récupération et sauvegarde initiales terminées.");
        } catch (Exception e) {
            System.err.println("❌ Erreur critique lors de la récupération initiale des données Velib : " + e.getMessage());
        }
    }
}