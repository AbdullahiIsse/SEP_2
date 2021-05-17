package shared.networking;

import shared.transferobjects.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RMIServer extends Remote {
    //Airport
    Airport CreateAirport(String airportId, String airportName, String airportCity) throws RemoteException;

    //chat
    InputChat createChat(String chat, double star) throws RemoteException;
    List<InputChat> getChat() throws RemoteException;
    int CountChat() throws RemoteException;

    //rating
    public List<Rating> getRatings() throws RemoteException;
    double AverageStars() throws RemoteException;
    int CountRatings(double star) throws RemoteException;

    //user
    InputUser username(String user, String password) throws RemoteException;
    List<InputUser> getUser() throws RemoteException;
    InputUser readUser(String user,String password) throws RemoteException;
    InputUser readUsername(String user) throws RemoteException;
    boolean ValidateUser(String user, String password) throws RemoteException;

    //flight
    List<Flights> getflights() throws RemoteException;
    List<Flights> readByName(String searchString) throws RemoteException;
    Flights CreateFlights(String flightID, String flightName, String price) throws RemoteException;
    void deleteFlight(Flights flights) throws RemoteException;
    List<Flights> getAllTheFLights()throws RemoteException;
    Arrival CreateArrival(String Arrival, String Arrivaldate) throws RemoteException;

    Depature CreateDeparture(String Departure, String DepartureDate) throws RemoteException;

    PlaneType CreatePlane(String PlaneTypes)throws RemoteException;

    List<PlaneType> getAllPlaneType() throws RemoteException;
    List<Depature> getAllDeparture()throws RemoteException;
    List<Arrival> getAllArrival()throws RemoteException;

    //admin

    void deleteArrival(Arrival arrival) throws RemoteException;
    void deleteDeparture(Depature depature) throws RemoteException;
    void deletePlaneType(PlaneType planeType)throws RemoteException;
    List<Seat> getSeats() throws RemoteException;
    List<Airport> getAirport() throws RemoteException;


    //seat
    List<Seat> getSeat(int planeId) throws RemoteException;
    Seat getSeatId(int seatID, String seatNumber, String classType) throws RemoteException;
    Seat CreateSeat(String SeatNumber,String classtype) throws RemoteException;
    void deleteSeat(Seat seat) throws RemoteException;

    //passenger
    Passenger passernger(String FirstName, String LastName, String TelNumber, String email) throws RemoteException;
    Passenger ReadPassenger(String Firstname, String LastName, String TelNumber, String Email) throws RemoteException;
    Passenger readByEmail(String email) throws RemoteException;

    //payment
    Payment payment(String cardholderName, String cardNumber, String CVV, String expirationDate) throws RemoteException;

    //List<myFlightTicket> ReadPriceSUM() throws RemoteException;
    List<myFlightTicket> getflightlist(int userId) throws RemoteException;
    myFlightTicket readUsername_(String user) throws RemoteException;

    //shoppingcart
    Flights readFlightsFromShoppingCart(String flightName, String departure, String arrival) throws RemoteException;
    Seat readSeatFromShoppingCart(String seatNumber,String classType) throws RemoteException;
    Flights readPrice(String price) throws RemoteException;

    //registerToClient
    void registerChatToClient(ClientCallBack client) throws RemoteException;
    void registerUserToClient(ClientCallBack client) throws RemoteException;
    void createTicket(myFlightTicket myFlightTicket) throws RemoteException;
    void registerTicketToClient(ClientCallBack client) throws  RemoteException;
    void registerFlightToClient(ClientCallBack client) throws  RemoteException;
    void registerArrivalToClient(ClientCallBack client) throws  RemoteException;
    void registerDepartureToClient(ClientCallBack client) throws  RemoteException;
    void registerPlaneToClient(ClientCallBack client) throws  RemoteException;
    void registerSeatToClient(ClientCallBack client) throws  RemoteException;
    void registerAirportToClient(ClientCallBack client) throws  RemoteException;





}
