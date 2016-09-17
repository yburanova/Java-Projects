/*
 * Copyright (c) Yulia Buranova
 */

package model;

import vo.Vacancy;

import java.util.List;

/**
 * Created by yulia on 13.09.16.
 */
public class Provider
{
    private Strategy strategy;

    public Provider(Strategy strategy)
    {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy)
    {
        this.strategy = strategy;
    }

    public List<Vacancy> getJavaVacancies(String searchString)
    {
        return strategy.getVacancies(searchString);
    }
}
