package org.example.domain.forecast;

import lombok.Data;

@Data
public class Wind{
	private int deg;
	private double speed;
	private double gust;
}