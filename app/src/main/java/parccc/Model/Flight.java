package parccc.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Vincent Bode on 15.06.2017.
 */

public class Flight implements Serializable {

    private String originName;
    private String destinationName;

    private String originAbbreviation;
    private String destinationAbbreviation;
    private Date startDate;
    private Date endDate;
    private int numberOfStops;
    private String originTerminal;
    private String flightNumber;
    private String airlineName;

    public Flight(String originName, String destinationName, String originAbbreviation, String destinationAbbreviation, Date startDate, Date endDate, int numberOfStops, String originTerminal, String flightNumber, String airlineName) {
        this.originName = originName;
        this.destinationName = destinationName;
        this.originAbbreviation = originAbbreviation;
        this.destinationAbbreviation = destinationAbbreviation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfStops = numberOfStops;
        this.originTerminal = originTerminal;
        this.flightNumber = flightNumber;
        this.airlineName = airlineName;
    }

    public Flight() {
    }

    public String getNumberOfStopsFormatted() {
        switch (getNumberOfStops()) {
            case 0:
                return "non-stop";
            case 1:
                return "1 stop";
            default:
                return getNumberOfStops() + " stops";
        }
    }

    public int getNumberOfStops() {
        return numberOfStops;
    }

    public void setNumberOfStops(int numberOfStops) {
        this.numberOfStops = numberOfStops;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getOriginAbbreviation() {
        return originAbbreviation;
    }

    public void setOriginAbbreviation(String originAbbreviation) {
        this.originAbbreviation = originAbbreviation;
    }

    public String getDestinationAbbreviation() {
        return destinationAbbreviation;
    }

    public void setDestinationAbbreviation(String destinationAbbreviation) {
        this.destinationAbbreviation = destinationAbbreviation;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getOriginTerminal() {
        return originTerminal;
    }

    public void setOriginTerminal(String originTerminal) {
        this.originTerminal = originTerminal;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

}
