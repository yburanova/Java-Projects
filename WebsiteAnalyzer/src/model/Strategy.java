/*
 * Copyright (c) Yulia Buranova
 */

package model;

import vo.Vacancy;

import java.util.List;

/**
 * Created by yulia on 13.09.16.
 */
public interface Strategy
{
    List<Vacancy> getVacancies(String searchString);
}
