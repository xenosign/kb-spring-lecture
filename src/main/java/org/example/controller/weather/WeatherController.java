package org.example.controller.weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.weather.WeatherDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/weather")
public class WeatherController {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_KEY = "590c223343799397b190b4fa8894ebf2";
    private final String URI = "https://api.openweathermap.org/data/2.5/weather";
    private final String UNITS = "metric";
    private final String LANG = "kr";

    @GetMapping("/{city}")
    public ResponseEntity<WeatherDto> getWeather(@PathVariable String city) {
        String url = UriComponentsBuilder.fromHttpUrl(URI)
                .queryParam("q", city)
                .queryParam("units", UNITS)
                .queryParam("APPID", API_KEY)
                .queryParam("lang", LANG)
                .toUriString();

        WeatherDto weather = restTemplate.getForObject(url, WeatherDto.class);
        return ResponseEntity.ok(weather);
    }
}
