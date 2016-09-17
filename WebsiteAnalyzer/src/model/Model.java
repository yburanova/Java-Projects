/*
 * Copyright (c) Yulia Buranova
 */

package model;

import view.View;
import vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yulia on 15.09.16.
 */
public class Model
{
    private View view;
    private Provider[] providers;

    public Model(View view, Provider[] providers)
    {
        if(view == null || providers == null || providers.length == 0)
            throw new IllegalArgumentException();

        this.view = view;
        this.providers = providers;
    }

    public void selectCity(String city)
    {
        List<Vacancy> vacancies = new ArrayList<>();

        for(Provider provider: providers)
        {
            vacancies.addAll(provider.getJavaVacancies(city));
        }

        view.update(vacancies);
    }
}
