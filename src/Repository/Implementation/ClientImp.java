package Repository.Implementation;


import Model.Client;
import Repository.Interface.IClient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientImp implements IClient {

    private Connection connection;

    public ClientImp(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Client> findAllClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Client client = new Client(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("address"),
                        resultSet.getString("telephone"),
                        resultSet.getBoolean("estProfessionel")
                );
                clients.add(client);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clients;
    }

    @Override
    public Client findClientById(Integer id) {
        String sql = " SELECT * FROM client WHERE id = ? ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            statement.setInt(1, id);
            if (resultSet.next()) {
                return new Client(resultSet.getInt("id"),
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
            ResultSet resultSet = statement.executeQuery();
            statement.setString(2, name);
            if (resultSet.next()) {
                return new Client(
                        resultSet.getInt("id"),
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
    public void addClient(Client client) {
        String sql = "INSERT INTO client (nom , address , telephone , estProfessionel) VALUES (?,?,?,?) ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getNom());
            statement.setString(2, client.getAddress());
            statement.setString(3, client.getTelephone());
            statement.setBoolean(4, client.isEstProfessionel());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

