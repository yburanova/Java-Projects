/*
 * Copyright (c) Yulia Buranova
 */

package model;

import vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yulia on 13.09.16.
 */
public class HHStrategy implements Strategy
{
    private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=java+%s&page=%d";
    private String userAgent = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:40.0) Gecko/20100101 Firefox/40.0";
    private String referrer = "http://google.de";


    @Override
    public List<Vacancy> getVacancies(String searchString)
    {
        List<Vacancy> vacancies = new ArrayList<>();

        try
        {
            Document document;
            int page = 0;

            while(true)
            {
                // open the new page
                document = getDocument(searchString, page++);
                if (document == null)
                    break;

                // searching the tag with a vacancy
                Elements elements = document.select("[data-qa=vacancy-serp__vacancy]");
                if(elements.size() == 0)
                    break;

                // for each page check all elements
                for(Element element: elements)
                {
                    // new Vacancy
                    Vacancy vacancy = new Vacancy();

                    // vacancy title
                    Element title = element.select("[data-qa=vacancy-serp__vacancy-title]").first();
                    vacancy.setTitle(title.text());

                    // vacancy salary
                    Element salary = element.select("[data-qa=vacancy-serp__vacancy-compensation]").first();
                    if(salary == null)
                        vacancy.setSalary("");
                    else
                        vacancy.setSalary(salary.text());

                    // vacancy city
                    Element city = element.select("[data-qa=vacancy-serp__vacancy-address]").first();
                    vacancy.setCity(city.text());

                    // vacancy - company name
                    Element company = element.select("[data-qa=vacancy-serp__vacancy-employer]").first();
                    vacancy.setCompanyName(company.text());

                    // vacancy website
                    vacancy.setSiteName("http://hh.ru/");

                    // vacancy url
                    vacancy.setUrl(title.attr("href"));

                    vacancies.add(vacancy);
                }
            }
        }
        catch (Exception e)
        {

        }

        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException
    {
        Document document = null;
        String url = String.format(URL_FORMAT, searchString, page);

        document = Jsoup.connect(url).userAgent(userAgent).referrer(referrer).get();

        return document;
    }

}
