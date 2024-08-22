package org.example.domain.forecast;

import java.util.List;
import lombok.Data;

@Data
public class ListItem{
	private int dt;
	private double pop;
	private Rain rain;
	private int visibility;
	private String dtTxt;
	private List<WeatherItem> weather;
	private Main main;
	private Clouds clouds;
	private Sys sys;
	private Wind wind;
}