package fr.host_dcode.vlib.service;

import fr.host_dcode.vlib.model.Station;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RealTimeService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public RealTimeService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public void realTimeData(Station station) {
        try {
            String url = "https://opendata.paris.fr/api/records/1.0/search/?dataset=velib-disponibilite-en-temps-reel&rows=1&q=stationcode:" + station.getStationCode();

            String response = restTemplate.getForObject(url, String.class);
            JsonNode rootNode = objectMapper.readTree(response);

            if (rootNode.has("records") && rootNode.get("records").size() > 0) {
                JsonNode record = rootNode.get("records").get(0);
                JsonNode fields = record.get("fields");

                // Retrieve only the number of available bikes and docks
                station.setAvailableBikes(fields.has("numbikesavailable") ?
                        fields.get("numbikesavailable").asInt() : null);
                station.setAvailableDocks(fields.has("numdocksavailable") ?
                        fields.get("numdocksavailable").asInt() : null);
            }
        } catch (Exception e) {
            System.err.println("Erreur récupération temps réel pour station " + station.getStationCode() + ": " + e.getMessage());
        }
    }
}