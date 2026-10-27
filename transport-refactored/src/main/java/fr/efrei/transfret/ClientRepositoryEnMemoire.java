package fr.efrei.transfret;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClientRepositoryEnMemoire implements ClientRepository {

    private final Map<String, Client> clients = new HashMap<String, Client>();

    public void sauvegarder(Client client) {
        clients.put(client.getEmail(), client);
    }

    public Client trouver(String email) {
        return clients.get(email);
    }
}
