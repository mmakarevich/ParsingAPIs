import com.example.*;
import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Gson gson = new Gson();

        URL url = null;
        try {
            url = new URL("https://api.rasp.yandex.net/v3.0/search/?apikey=64d2c4dc-e05a-4574-b51a-bdc03b2bc8a3&format=json&from=c193&to=c213&lang=ru_RU&page=1&date=2018-01-20");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            /*достаем json из url и запихиваем в объект*/
//            Date date = new Date();
            InputStreamReader reader = new InputStreamReader(url.openStream());
//            System.out.println((new Date().getTime()-date.getTime()));
            YandexRasp rasp = gson.fromJson(reader, YandexRasp.class);


            /* дальше делаем с ним что угодно
            *  напимер выводим на экран тип транспрота, название, цену за билет
            *  примечание: выведет только если есть данные о цене билета!*/
            System.out.println(rasp.getSearch().getFrom().getPopularTitle()+"   -   "+
                               rasp.getSearch().getTo().getPopularTitle()+"    "+
                               rasp.getSearch().getDate()
                               );

            System.out.println("____________________________________________");
            rasp.getSegments()
                    .forEach(s -> s.getTicketsInfo()
                                    .getPlaces()
                                    .forEach(li -> System.out.println(s.getFrom().getTransportType()+"   ("+
                                                                      s.getThread().getTitle()+")  "+
                                                                      li.getPrice().getWhole()+"-"+li.getPrice().getCents()+"   " +
                                                                      li.getCurrency()
                                                   )
                                    )
                    );


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
