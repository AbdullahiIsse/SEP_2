package server.model;

import server.model.database.*;
import shared.transferobjects.*;
import shared.util.utils;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class TextManagerImpl implements TextManager{

    private PropertyChangeSupport support;
    private List<InputChat> chatListe;
    private List<InputUser> userNameList;
    private List<Passenger> passengersList;
    private List<Seat> seatsList;
    private List<Payment> paymentList;

    private FlightDao dao;
    private InputChatDao inputChatDao;
    private InputUserDao inputUserDao;
    private MyFlightTicketDao myFlightTicketDao;
    private PassengerDao passengerDao;
    private SeatDao seatDao;
    private PaymentDao paymentDao;
    private ShoppingCartDao shoppingCartDao;

    public TextManagerImpl() {
        support = new PropertyChangeSupport(this);
        chatListe = new ArrayList<>();
        userNameList = new ArrayList<>();
        passengersList = new ArrayList<>();
        seatsList = new ArrayList<>();
        paymentList = new ArrayList<>();
        dao = FlightImpl.getInstance();
        inputChatDao = InputChatImpl.getInstance();
        inputUserDao = InputUserImpl.getInstance();
        myFlightTicketDao = MyFlightTicketImpl.getInstance();
        passengerDao = PassengerImpl.getInstance();
        seatDao = SeatImpl.getInstance();
        paymentDao = PaymentImpl.getInstance();
        shoppingCartDao = ShoppingCartImpl.getInstance();

    }

    @Override
    public InputUser CreateUser(String user, String password) {
        InputUser inputUser = new InputUser(user, password);
        userNameList.add(inputUserDao.createUser(user,password));
        support.firePropertyChange(utils.NEWUSER, null, inputUser);
        System.out.println("support.getPropertyChangeListeners().length:" + support.getPropertyChangeListeners().length);
        return inputUser;
    }



    @Override
    public InputChat sendMsg(String str) {
        InputChat inputChat = new InputChat(str);
        chatListe.add(inputChatDao.createChar(str));
        support.firePropertyChange(utils.NEWCHAT, null, inputChat);
        System.out.println("support.getPropertyChangeListeners().length:" + support.getPropertyChangeListeners().length);
        return inputChat;
    }

    @Override
    public List<InputUser> getUser() {
        return new ArrayList<>(inputUserDao.readUser());
    }

    @Override
    public List<Seat> getSeat() {
        return new ArrayList<>(seatDao.getSeat());
    }

    @Override
    public boolean ValidateUser(String user, String password) {
        return inputUserDao.ValidateUser(user,password);
    }

    /*@Override
    public Seat seat(String seatNumber, String classType) {
        Seat seat;
        seat = seatDao.CreateSeat(seatNumber,classType);
        seatsList.add(seat);
        return seat;
    }*/

    @Override
    public List<InputChat> getChat() {
       return new ArrayList<>(inputChatDao.readChat());
    }

    @Override
    public List<flights> getflights() {

        return new ArrayList<>(dao.getflights());
    }

    @Override
    public List<flights> readByName(String searchString) {
        return dao.readByName(searchString);
    }

    @Override public Passenger passernger(String FirstName, String LastName, String TelNumber, String email) {
        Passenger passenger;
        passenger = passengerDao.CreatePassengers(FirstName, LastName, TelNumber,email);
        passengersList.add(passenger);
        System.out.println("Passenger id er : " +passenger);
        return passenger;
    }

    @Override
    public Passenger ReadPassenger(String Firstname, String LastName, String TelNumber, String Email) {
        return passengerDao.ReadPassenger(Firstname,LastName,TelNumber,Email);
    }



   /* @Override
    public List<myFlightTicket> ReadPriceSUM() {
        return  new ArrayList<>(myFlightTicketDao.ReadPriceSUM());
    }*/

    @Override
    public void createTicket(myFlightTicket myFlightTicket) {
        myFlightTicketDao.createTicket(myFlightTicket);
        support.firePropertyChange(utils.NEWTICKET, null, myFlightTicket);

    }

    @Override
    public flights readFlightsFromShoppingCart(String flightName, String from, String to) {
        return shoppingCartDao.readFlightsFromShoppingCart(flightName,from,to);
    }

    @Override
    public Seat readSeatFromShoppingCart(String seatNumber, String classType) {
        return shoppingCartDao.readSeatFromShoppingCart(seatNumber,classType);
    }


    @Override
    public List<myFlightTicket> getflightlist() {
        return new ArrayList<>(myFlightTicketDao.ReadFlightList());
    }

    @Override
    public Payment payment(String cardholderName, String cardNumber, String CVV, String expirationDate) {
        Payment payment;
        payment = paymentDao.CreatePayment(cardholderName, cardNumber, CVV, expirationDate);
        paymentList.add(payment);
        return payment;

    }


    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
