/*
 * Copyright (c) Yulia Buranova
 */

package view;

import controller.Controller;
import vo.Vacancy;

import java.util.List;

/**
 * Created by yulia on 15.09.16.
 */
public interface View
{
    void update(List<Vacancy> vacancies);

    void setController(Controller controller);
}
