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
import java.util.stream.Collectors;

@Service
public class VelibService {
    public final WebClient webClient;
    private final StationRepository stationRepository;

    public VelibService(WebClient webClient, StationRepository stationRepository) {
        this.webClient = webClient;
        this.stationRepository = stationRepository;
    }

    public List<Station> fetchAndSaveVelibData() {

        String queryUri = "?dataset=velib-disponibilite-en-temps-reel&rows=50";

        VelibApiResponse apiResponse = webClient.get()
                .uri(queryUri)
                .retrieve()
                .bodyToMono(VelibApiResponse.class)
                .block();

        if (apiResponse == null || apiResponse.getRecords() == null || apiResponse.getRecords().isEmpty()) {
            System.out.println("Aucune donnée Velib reçue.");
            return List.of();
        }

        List<Station> stationsToSave = apiResponse.getRecords().stream()
                .map(record -> {
                    VelibFields fields = record.getFields();

                    List<Double> coords = fields.getCoordonnees_geo();
                    double latitude = (coords != null && coords.size() >= 1) ? coords.get(0) : 0.0;
                    double longitude = (coords != null && coords.size() >= 2) ? coords.get(1) : 0.0;

                    String description = fields.getName() + ". Dernière mise à jour: " + fields.getDuedate();

                    Station station = new Station(
                            fields.getName(),
                            record.getRecordId(),
                            fields.getStation_code(),
                            latitude,
                            longitude,
                            description
                    );

                    return station;
                })
                .collect(Collectors.toList());

        if (!stationsToSave.isEmpty()) {
            System.out.println("Tentative d'enregistrement de " + stationsToSave.size() + " stations.");
            return stationRepository.saveAll(stationsToSave);
        }

        return List.of();
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