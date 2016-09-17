package controller;

/*
 * Copyright (c) Yulia Buranova
 */

import model.HHStrategy;
import model.Model;
import model.Provider;
import view.HtmlView;

/**
 * Created by yulia on 11.09.16.
 */
public class Aggregator
{
    public static void main(String[] args)
    {
        HtmlView view = new HtmlView();
        Provider provider = new Provider(new HHStrategy());

        Model model = new Model(view, new Provider[]{provider});
        Controller controller = new Controller(model);

        view.setController(controller);
        view.userCitySelectEmulationMethod();
    }
}
