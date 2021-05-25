package ar.edu.unq.desapp.grupoj.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Decade;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.RateType;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles.Genre;

import java.util.List;

public class InverseReq {

    public List<String> decade;
    public RateType rating;
    public Integer minStars;
    public List<Integer> actors;
    public List<Genre> genres;

}
