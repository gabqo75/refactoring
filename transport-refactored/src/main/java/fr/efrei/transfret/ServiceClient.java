package fr.efrei.transfret;

public class ServiceClient {

    private final ValidateurClient validateur;
    private final ClientRepository clientRepository;
    private final CanalNotification canalEmail;

    public ServiceClient(ValidateurClient validateur, ClientRepository clientRepository, CanalNotification canalEmail) {
        this.validateur = validateur;
        this.clientRepository = clientRepository;
        this.canalEmail = canalEmail;
    }

    public Client creerClient(String nom, String email, String telephone, String adresse, TypeClient type, String siret) {
        if (!validateur.valider(nom, email, type, siret)) {
            return null;
        }
        Client c = new Client(nom, email, telephone, adresse, type, siret);
        clientRepository.sauvegarder(c);
        canalEmail.envoyer(c.getEmail(), "Bienvenue chez TransFret", "Bonjour " + c.getNom() + ", votre compte " + c.getType().name().toLowerCase() + " a été créé.");
        return c;
    }
}
