package de.tum.in.repobruegge.pom17aprilsnow.flightapp.Network;

/**
 *
 * Created by yesika on 29.06.17.
 */

class Endpoints {

    static final String WEATHER_ENDPOINT = "http://api.worldweatheronline.com/premium/v1/weather.ashx?key=07da2631a9bb444498b130104172906&format=json&num_of_days=1&date=today&q=";
    static final String TIMEZONE_ENDPOINT = "https://maps.googleapis.com/maps/api/timezone/json?location=39.6034810,-119.6822510&timestamp=1331161200&key=AIzaSyCg-Q8JrBlw-88_Ig_FB0xXbn00WcNWah4";

    static final String MAPS_ENDPOINT = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?key=AIzaSyD7ubjF0zQSwAV_5ovYAhA97L8nqehC7lo&location=%s,%s&radius=%s";
    static final String FLIGHTS_ENDPOINT = "https://www.googleapis.com/qpxExpress/v1/trips/search?key=AIzaSyD7ubjF0zQSwAV_5ovYAhA97L8nqehC7lo";
    static final String PHOTO_ENDPOINT = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=%s&key=AIzaSyD7ubjF0zQSwAV_5ovYAhA97L8nqehC7lo";


    static final String CUSTOMER_REST_ROOT = "https://i-wish-this-were-already-implemented.wot/the-best-customer-api-ever/";
    static final String LOGIN_ENDPOINT = CUSTOMER_REST_ROOT + "/login";
    static final String REGISTER_ENDPOINT = CUSTOMER_REST_ROOT + "/users";
}
