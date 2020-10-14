package ua.ies.mec93019.weather_radar;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ua.ies.mec93019.weather_radar.ipma_client.IpmaCityForecast;
import ua.ies.mec93019.weather_radar.ipma_client.IpmaService;
import java.util.logging.Logger;
import com.google.gson.*;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * demonstrates the use of the IPMA API for weather forecast
 */
public class WeatherStarter {
    // private static final int CITY_ID_AVEIRO = 1010500;
    /*
    loggers provide a better alternative to System.out.println
    https://rules.sonarsource.com/java/tag/bad-practice/RSPEC-106
     */
    private static final Logger logger = Logger.getLogger(WeatherStarter.class.getName());
	private static String CITY_NAME ="Aveiro";
	private static int CITY_ID = 0;

    public static void  main(String[] args ) {
        CITY_NAME = args[0];
        /*
        get a retrofit instance, loaded with the GSon lib to convert JSON into objects
         */
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.ipma.pt/open-data/").addConverterFactory(GsonConverterFactory.create()).build();
		String sURL = "https://api.ipma.pt/open-data/distrits-islands.json"; //just a string

		try {
			URL url = new URL(sURL);
			URLConnection con = url.openConnection();
			con.connect();

			JsonParser parser = new JsonParser();

			JsonElement root = parser.parse(new InputStreamReader((InputStream) con.getContent()));

			JsonObject root_object = root.getAsJsonObject();

			JsonArray data = root_object.getAsJsonArray("data");
			
			Iterator<JsonElement> city_iterator = data.iterator();

			while (city_iterator.hasNext()){
				JsonElement elem = city_iterator.next();

				JsonObject city = elem.getAsJsonObject();

				int id = city.get("globalIdLocal").getAsInt();

				String name = city.get("local").getAsString();

				if (name.equals(CITY_NAME)){
					CITY_ID = id;
					break;
				}
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
        IpmaService service = retrofit.create(IpmaService.class);
        Call<IpmaCityForecast> callSync = service.getForecastForACity(CITY_ID);

        try {
            Response<IpmaCityForecast> apiResponse = callSync.execute();
            IpmaCityForecast forecast = apiResponse.body();

            if (forecast != null) {
                logger.info( "max temp for today: " + forecast.getData().
                        listIterator().next().getTMax());
            } else {
                logger.info( "No results!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
