package controller;/*
 * Copyright (c) Yulia Buranova
 */

import model.Model;

/**
 * Created by yulia on 13.09.16.
 */
public class Controller
{
    private Model model;

    public Controller(Model model)
    {
        if(model == null)
            throw new IllegalArgumentException();

        this.model = model;
    }

    public void onCitySelect(String cityName)
    {
        model.selectCity(cityName);
    }
}
