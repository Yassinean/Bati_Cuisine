package Repository.Implementation;

import Config.DbConfig;
import Model.Client;
import Model.EtatProjet;
import Model.Projet;
import Repository.Interface.IClient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientImp implements IClient {

    private Connection connection;

    public ClientImp() {
        try {
            this.connection = DbConfig.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    @Override
    public void updateClient(Integer id, Client client) {
        String sql = "UPDATE TABLE client SET nom = ? , address = ? , telephone = ? , estProfessionel = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getNom());
            statement.setString(2, client.getAddress());
            statement.setString(3, client.getTelephone());
            statement.setObject(4, client.isEstProfessionel());
            statement.setObject(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}

