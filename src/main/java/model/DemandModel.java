package model;

import Entity.Demand;

import java.util.List;

public class DemandModel {

    private final String TABLE = "CREATE TABLE IF NOT EXISTS Demand(" +
            "idDemand int PRIMARY KEY AUTOINCREMENT," +
            "dateD date NOT NULL," +
            "quantityPerDay int NOT NULL," +
            "idProduct int FOREIGNT KEY REFERENCES(idProduct))";
    private final String SELECT = "SELECT * FROM Demand";
    private final String WHERE = " WHERE idDemand = ?";
    private final String INSERT = "INSERT INTO Demand VALUES (?, ?, ?)";
    private final String UPDATE = "UPDATE Demand SET dateD = ?," +
            "quantityPerDay = ?," +
            "idProduct = ?" + WHERE;
    private final String DELETE = "DELETE FROM Demand" + WHERE;

    public static List<Demand> getDemands() {
        return null;
    }

    public static Demand getDemandById(int id) {
        return null;
    }

    public static void setDemand(Demand demand) {

    }

    public static void updateDemand(Demand demand) {

    }

    public static void deleteDemand(int idDemand) {

    }
}
