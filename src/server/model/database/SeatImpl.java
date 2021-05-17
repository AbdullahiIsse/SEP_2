
package server.model.database;

import client.model.SaveInfo;
import shared.transferobjects.Flights;
import shared.transferobjects.Passenger;
import shared.transferobjects.PlaneType;
import shared.transferobjects.Seat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeatImpl implements SeatDao {

    private static SeatImpl daoInstance;

    public static synchronized SeatImpl getInstance(){

        if (daoInstance == null){
            daoInstance = new SeatImpl();
        }
        return daoInstance;
    }

    @Override
    public List<Seat> getSeat(int planeId) {
        try {

            try (Connection connection = daoConnection.getConnection()) {
                PreparedStatement statement = connection.prepareStatement("select * from seat where planeID = ?");
                statement.setInt(1,planeId);
                ResultSet resultSet = statement.executeQuery();

                ArrayList<Seat> Seats = new ArrayList<>();
                while (resultSet.next()) {

                    //seat
                    int seatID = resultSet.getInt("seatID");
                    String seatNumber = resultSet.getString("seatNumber");
                    String classType = resultSet.getString("classType");
                    int getplaneId = resultSet.getInt("planeId");
                    //Linje 38 skal måske kun have seat ID
                    Seat seat = new Seat(seatID, seatNumber, classType, new PlaneType(planeId));
                    Seats.add(seat);
                }
                return Seats;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override public Seat getSeatId(int seatID, String seatNumber,
        String classType)
    {
        return null;
    }

    @Override public Seat CreateSeat( String SeatNumber,
        String classtype)
    {
        try {
            try (Connection connection = daoConnection.getConnection()) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO seat(seatNumber,classType) VALUES (?,?) ", PreparedStatement.RETURN_GENERATED_KEYS);

                //seat

                statement.setString(1, SeatNumber);
                statement.setString(2,classtype);

                statement.executeUpdate();
                ResultSet key = statement.getGeneratedKeys();

                if (key.next()) {

                    return new Seat(key.getInt(1),SeatNumber,classtype);
                } else {

                    throw new SQLException("Her bliver det testet på at lave et nyt seat");

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Seat> getSeats() {
        try {

            try (Connection connection = daoConnection.getConnection()) {
                PreparedStatement statement = connection.prepareStatement("select * from seat");

                ResultSet resultSet = statement.executeQuery();

                ArrayList<Seat> Seats = new ArrayList<>();
                while (resultSet.next()) {

                    //seat
                    int seatID = resultSet.getInt("seatID");
                    String seatNumber = resultSet.getString("seatNumber");
                    String classType = resultSet.getString("classType");
                    int planeID = resultSet.getInt("planeID");

                    //Linje 38 skal måske kun have seat ID
                    Seat seat = new Seat(seatID, seatNumber, classType,new PlaneType(planeID));
                    Seats.add(seat);
                }
                return Seats;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }







}





