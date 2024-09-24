package Repository.Implementation;


import Model.Client;
import Repository.Interface.IClient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientImp implements IClient {

    private Connection connection;

    public ClientImp(Connection connection) {
        this.connection = connection;
    }

    public ClientImp(){}


    @Override
    public Client findClientById(Integer id) {
        String sql = " SELECT * FROM client WHERE id = ? ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Client(
                        resultSet.getString("nom"),
                        resultSet.getString("address"),
                        resultSet.getString("telephone"),
                        resultSet.getBoolean("estProfessionel")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Client findClientByName(String name) {
        String sql = " SELECT * FROM client WHERE nom = ? ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Client client = new Client(
                        resultSet.getString("nom"),
                        resultSet.getString("address"),
                        resultSet.getString("telephone"),
                        resultSet.getBoolean("estProfessionel")
                );
                client.setId(resultSet.getInt("id"));
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Client addClient(Client client) {
        String sql = "INSERT INTO client (nom, address, telephone, estProfessionel) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, client.getNom());
            statement.setString(2, client.getAddress());
            statement.setString(3, client.getTelephone());
            statement.setBoolean(4, client.isEstProfessionel());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                client.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

}

