package server.model.database;

import shared.transferobjects.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class daoImpl implements daoInterface  {

  private static daoImpl daoInstance;
  private daoConnection daoconnection;


  private daoImpl() {
    try {
      DriverManager.registerDriver(new org.postgresql.Driver());
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    daoconnection = daoConnection.getInstance();
  }

     public static synchronized daoImpl getInstance(){

          if (daoInstance == null){

            daoInstance = new daoImpl();

          }

          return daoInstance;
     }

  public InputChat createChar(String str) {
    try {
      try (Connection connection = daoConnection.getConnection()) {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO InputChat(chat) VALUES (?) ");
        statement.setString(1, str);
        statement.executeUpdate();
        return new InputChat(str);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return null;
  }

  public List<InputChat> readChat() {
    try {
      try (Connection connection =  daoConnection.getConnection()) {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM InputChat");
        ResultSet resultSet = statement.executeQuery();
        ArrayList<InputChat> result = new ArrayList<>();
        while (resultSet.next()) {
          String str = resultSet.getString("chat");
          InputChat inputChat = new InputChat(str);
          result.add(inputChat);

        }
        return result;
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return null;
  }


  public InputUser createUser(String txt) {
    try {
      try (Connection connection =  daoConnection.getConnection()) {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO InputUser(user_) VALUES (?) ", PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, txt);
        statement.executeUpdate();
        ResultSet key = statement.getGeneratedKeys();

        if (key.next()) {

          return new InputUser(key.getInt(1), txt);
        } else {

          throw new SQLException("hej med dig");

        }
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return null;
  }


  public List<InputUser> readUser() {
    try {
      try (Connection connection =  daoConnection.getConnection()) {

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM InputUser ");

        ResultSet resultSet = statement.executeQuery();
        ArrayList<InputUser> result = new ArrayList<>();
        while (resultSet.next()) {
          String txt = resultSet.getString("user_");
          int id = resultSet.getInt("id");
          InputUser inputUser = new InputUser(id, txt);
          result.add(inputUser);

        }
        return result;
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return null;
  }


 /** --------------------------------------------------------------------------------------------------------------------**/

 @Override
 public List<flights> getflights() {
   try {

     try (Connection connection = daoConnection.getConnection()) {
       PreparedStatement statement = connection.prepareStatement("SELECT * FROM flights ");
       ResultSet resultSet = statement.executeQuery();

       ArrayList<flights> flightlist = new ArrayList<>();
       while (resultSet.next()) {
         String flightID = resultSet.getString("flightID");
         String flightName = resultSet.getString("flightName");
         String departure = resultSet.getString("departure");
         String arrival = resultSet.getString("arrival");
         String from = resultSet.getString("from_");
         String to = resultSet.getString("to_");
         flights flights = new flights(flightID, flightName, departure, arrival, from, to);
         flightlist.add(flights);
       }
       return flightlist;
     }
   } catch (Exception e) {
     e.printStackTrace();
   }
   return null;
 }

  @Override
  public List<seat> getSeat() {
    try {

      try (Connection connection = daoConnection.getConnection()) {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM seat");
        ResultSet resultSet = statement.executeQuery();

        ArrayList<seat> seats = new ArrayList<>();
        while (resultSet.next()) {
          String seatID = resultSet.getString("seatID");
          String seatNumber = resultSet.getString("seatNumber");
          String classType = resultSet.getString("classType");
          seat seat = new seat(seatID, seatNumber, classType);
          seats.add(seat);
        }
        return seats;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<passenger> getPassenger() {
    try {

      try (Connection connection = daoConnection.getConnection()) {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM passenger");
        ResultSet resultSet = statement.executeQuery();

        ArrayList<passenger> passengers = new ArrayList<>();
        while (resultSet.next()) {
          String passengerID = resultSet.getString("passengerID");
          String name = resultSet.getString("name");
          passenger passenger = new passenger(passengerID, name);
          passengers.add(passenger);
        }
        return passengers;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<Myflightlist> ReadFlightList()  {

    try{

      try (Connection connection = daoConnection.getConnection()) {
        PreparedStatement statement = connection.prepareStatement("select * from flights join myFlightTicket mFT on flights.flightID = mFT.flightID join passenger p on mFT.passengerid = p.passengerid join seat s on s.seatid = mFT.seatid where ticketID = 1 ");
        ResultSet resultSet = statement.executeQuery();

        ArrayList<Myflightlist> myflightlists = new ArrayList<>();
        while (resultSet.next()) {
          String FlightId = resultSet.getString("flightid");
          String Flightname = resultSet.getString("flightname");
          String departure = resultSet.getString("departure");
          String arrival = resultSet.getString("arrival");
          String from = resultSet.getString("from_");
          String to = resultSet.getString("to_");
          String ticketid = resultSet.getString("ticketid");
          String price = resultSet.getString("price");
          String passengerID = resultSet.getString("passengerid");
          String seatId = resultSet.getString("seatid");
          String passengerName = resultSet.getString("name");
          String seatNumber = resultSet.getString("seatNumber");
          String classtype = resultSet.getString("classType");
          Myflightlist myflightlist = new Myflightlist(ticketid,price,new passenger(passengerID,passengerName),new flights(FlightId,Flightname,departure,arrival,from,to),new seat(seatId,seatNumber,classtype));

        myflightlists.add(myflightlist);
        }
        return myflightlists;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
    }

  @Override
  public void getfinish(Myflightlist myflightlist) {
    try {
      try (Connection connection =  daoConnection.getConnection()) {
        PreparedStatement statement = connection.prepareStatement(" UPDATE myFlightTicket SET flightID = ?, seatID = ?, passengerID = ? ");

        statement.setString(1, myflightlist.getFlights().getFlightID());
        statement.setString(2, myflightlist.getSeat().getSeatID());
        statement.setString(3, myflightlist.getPassenger().getPassengerID());

        statement.executeUpdate();
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }
}


