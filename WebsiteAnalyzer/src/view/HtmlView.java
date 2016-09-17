/*
 * Copyright (c) Yulia Buranova
 */

package view;

import controller.Controller;
import vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by yulia on 15.09.16.
 */
public class HtmlView implements View
{
    private Controller controller;

    private final String filePath = "./src/" + this.getClass().getPackage().getName().replace(".", "/") + "/vacancies.html";


    @Override
    public void update(List<Vacancy> vacancies)
    {
        try
        {
            updateFile(getUpdatedFileContent(vacancies));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod()
    {
        controller.onCitySelect("Moscow");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies)
    {
        String fileContent = null;

        try
        {
            Document document = getDocument();

            Element element = document.getElementsByClass("template").first();

            // template for the new list with vacancies
            Element elementClone = element.clone();
            elementClone.removeAttr("style");
            elementClone.removeClass("template");

            document.select("tr[class=vacancy]").remove();

            for(Vacancy vacancy: vacancies)
            {
                Element newElement = elementClone.clone();

                // write city
                newElement.getElementsByClass("city").first().text(vacancy.getCity());

                // company
                newElement.getElementsByClass("companyName").first().text(vacancy.getCompanyName());

                // salary
                newElement.getElementsByClass("salary").first().text(vacancy.getSalary());

                // title + url
                Element link = newElement.getElementsByTag("a").first();
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());
                element.before(newElement.outerHtml());

            }

            fileContent = document.html();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Some exception occurred");
        }


        return fileContent;
    }

    private void updateFile(String fileContent)
    {
        try
        {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
            bufferedWriter.write(fileContent);
            bufferedWriter.close();
        }
        catch (IOException e)
        {

        }
    }

    protected Document getDocument() throws IOException
    {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }
}
