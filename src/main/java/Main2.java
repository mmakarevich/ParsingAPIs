import com.google.gson.Gson;
import com.kiwi.*;

import java.io.IOException;
import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

public class Main2 {

    public static void main(String[] args) {

        /*получаем json из kiwi парсим и выводим что хотим
        * например колличество рейсов*/

        Date date = new Date();
        Client client = ClientBuilder.newClient();
        Response response = client.target("https://api.skypicker.com/flights?flyFrom=voronezh&to=moscow&dateFrom=20/01/2018&dateTo=20/01/2018&partner=picky&partner_market=us")
                .request(MediaType.APPLICATION_JSON)
                .get();


        Gson gson = new Gson();

        KiwiFlights flights = gson.fromJson(response.readEntity(String.class),KiwiFlights.class);

        System.out.println(new Date().getTime() - date.getTime());
//        System.out.println(flights.getResults());

        /*достаем список рейсов и цен*/
        System.out.println(flights.getCurrency());

        flights.getData().forEach(d -> System.out.println(d.getPrice()+"" +
                "   "+d.getCityFrom()+" - "+d.getCityTo()));



//        System.out.println("status: " + response.getStatus());
//        System.out.println("headers: " + response.getHeaders());
//        System.out.println("body:" + response.readEntity(String.class));

    }
}
