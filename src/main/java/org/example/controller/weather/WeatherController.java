package org.example.controller.weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.forecast.ForecastDto;
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
    private final String URI = "https://api.openweathermap.org/data/2.5/weather";
    private final String API_KEY = "";
    private final String UNITS = "metric";
    private final String LANG = "kr";

    @GetMapping("/test/{city}")
    public ResponseEntity<String> test(@PathVariable String city) {
        String url = UriComponentsBuilder.fromHttpUrl(URI)
                .queryParam("q", city)
                .queryParam("units", UNITS)
                .queryParam("APPID", API_KEY)
                .queryParam("lang", LANG)
                .toUriString();
        return ResponseEntity.ok(url);
    }


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

    private final String FORECAST_URI = "http://api.openweathermap.org/data/2.5/forecast";

    @GetMapping("/forecast/{city}")
    public ResponseEntity<ForecastDto> getForecast(@PathVariable String city) {
        String url = UriComponentsBuilder.fromHttpUrl(FORECAST_URI)
                .queryParam("q", city)
                .queryParam("units", UNITS)
                .queryParam("APPID", API_KEY)
                .queryParam("lang", LANG)
                .toUriString();

        ForecastDto forecast = restTemplate.getForObject(url, ForecastDto.class);
        return ResponseEntity.ok(forecast);
    }
}
