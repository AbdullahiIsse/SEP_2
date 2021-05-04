package client.model;

import shared.transferobjects.*;
import shared.util.Subject;

import java.rmi.RemoteException;
import java.util.List;

public interface ClientText extends Subject {

    //chat
    InputChat createChat(String chat, int star);
    List<InputChat> getChat();
    int CountChat();

    //user
    InputUser username(String user, String password);
    boolean ValidateUser(String user, String password) ;
    List<InputUser> getUser();
    InputUser readUser(String user,String password);

    //ratings
    List<Rating> getRatings();

    //average
    int AverageStars();

    //flight
    List<flights> getflights();
    List<flights> readByName(String searchString);

    //seat
    List<Seat> getSeat(int planeId);
    Seat getSeatId(int seatID, String seatNumber, String classType);
    //Seat seat(String seatNumber, String classType);
    //passenger

    Passenger Createpassernger(String FirstName, String LastName, String TelNumber, String email);
    Passenger ReadPassenger(String Firstname, String LastName, String TelNumber, String Email);

    //payment
    Payment payment(String cardholderName, String cardNumber, String CVV, String expirationDate);

    //shoppingCart
    flights readFlightsFromShoppingCart(String flightName, String departure, String arrival);
    Seat readSeatFromShoppingCart(String seatNumber,String classType);

    //ticket
    /* List<myFlightTicket> ReadPriceSUM();*/
    List<myFlightTicket> getflightlist(int userId);
    void createTicket(myFlightTicket myFlightTicket);








}


