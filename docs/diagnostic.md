# Étape 1 – Diagnostic

## Rôle et dépandances de chaque classe

| Classe | Ce qu'elle fait | Dépend de |
|---|---|---|
| `ServiceClient` | Valide les données d'un client, l'enregistre et envoie l'email de bienvenue | `Client`, `Database`, `EmailService` |
| `ServiceExpedition` | Valide et crée une expédition, calcule devis et délais, gère le cycle de vie, envoie les notifications | `Database`, `CalculateurPrix`, `AffectationVehicule`, `Utils`, `EmailService`, `SmsService`, `Expedition`, `Colis`, `Vehicule` |
| `CalculateurPrix` | Calcule le prix HT : tarif par mode, suppléments, urgence, remise, assurance, arrondi | `Colis` |
| `AffectationVehicule` | Choisit le premier véhicule disponible et compatible | `Flotte`, `Colis`, `Camion`, `CamionFrigorifique`, `Train`, `Bateau`, `Avion` |
| `Flotte` | Liste statique des véhicules | `Camion`, `CamionFrigorifique`, `Train`, `Bateau`, `Avion` |
| `IVehicule` | Interface commune à tous les véhicules | `Colis` |
| `Vehicule` | Classe abstraite : capacité, charge, disponibilité, chargement | `IVehicule`, `Colis` |
| `Camion`, `CamionFrigorifique`, `Train`, `Bateau`, `Avion` | Véhicules concrets | `Vehicule`, `Colis` |
| `Client` | Modèle client (type en `String`) | — |
| `Colis` | Modèle colis (type en `int`) et son libellé | — |
| `Expedition` | Modèle expédition (mode et statut en `String`) et génération de la facture | `Client`, `Colis`, `Vehicule`, `Utils` |
| `Database` | Singleton de persistance simulée, maps statiques publiques | `Client`, `Expedition` |
| `EmailService` | Envoi d'email simulé (méthode statique, journal statique) | — |
| `SmsService` | Envoi de SMS simulé (journal statique) | — |
| `Utils` | Arrondi, formatage de dates et de prix, validation d'email, table des distances | — |
| `Main` | Démo : construit les services et déroule un scénario | `Flotte`, `ServiceClient`, `ServiceExpedition`, `Colis`, `Expedition` |

## Défauts : emplacement et principe violé

| Défaut | Emplacement | Principe violé |
|---|---|---|
| Le service crée l'expédition, valide, calcule les délais, gère le cycle de vie et notifie | `ServiceExpedition` (toute la classe) | **S** – responsabilité unique |
| 30 lignes de validation au début de `creerExpedition` | `ServiceExpedition.creerExpedition` l. 17-51 | **S** |
| Calcul des délais dans le service | `ServiceExpedition.calculerDelai` l. 101-127 | **S** |
| Envoi des emails et SMS dans le service | `ServiceExpedition.notifier` l. 204-209 | **S** |
| L'entité métier met en forme sa facture | `Expedition.genererFacture` l. 52-82 | **S** |
| Validation et enregistrement dans la même méthode | `ServiceClient.creerClient` | **S** (à discuter) |
| `Utils` mélange dates, prix, emails et distances | `Utils` | **S** |
| `if/else` sur le mode : ajouter un mode = modifier la classe | `CalculateurPrix.calculerPrixHT` l. 25-41 | **O** – ouvert/fermé |
| `if/else` sur le type de client pour la remise | `CalculateurPrix.calculerPrixHT` l. 50-58 | **O** |
| Un `if` par supplément : ajouter un supplément = modifier la classe | `CalculateurPrix.calculerPrixHT` l. 44-47, 61-65 | **O** |
| `switch` sur le mode pour le délai | `ServiceExpedition.calculerDelai` l. 103-124 | **O** |
| `if` sur le mode pour faire partir le véhicule | `ServiceExpedition.demarrerExpedition` l. 142-149 | **O** |
| Cascade d'`instanceof` et de `if` sur le mode | `AffectationVehicule.trouverVehicule` l. 20-52 | **O** |
| Règles d'acceptation codées en dur sur le mode | `ServiceExpedition.creerExpedition` l. 35-46 | **O** |
| `switch` sur le type de colis pour le libellé | `Colis.getLibelleType` | **O** |
| `CamionFrigorifique.charger()` refuse les colis non réfrigérés : il ne remplace pas un `Camion` | `CamionFrigorifique.charger` l. 10-16 | **L** – substitution de Liskov |
| D'où le `continue` « sinon ça plante » | `AffectationVehicule` l. 26-28 | **L** |
| `Train.faireLePlein()` lève une exception, cachée par un `catch` vide | `Train.faireLePlein`, `ServiceExpedition.demarrerExpedition` l. 138-141 | **L** |
| `rouler`/`naviguer`/`voler`/`activerRefrigeration` lèvent `UnsupportedOperationException` | `Camion`, `Train`, `Bateau`, `Avion` | **L** et **I** |
| Interface trop large imposée à tous les véhicules | `IVehicule` | **I** – ségrégation des interfaces |
| Singleton `Database.getInstance()` | `Database`, `ServiceClient` l. 23, `ServiceExpedition` l. 12 | **D** – inversion des dépendances |
| Méthode statique `EmailService.envoyer()` | `ServiceClient` l. 24, `ServiceExpedition` l. 205 | **D** |
| `new SmsService()` en dur | `ServiceExpedition.notifier` l. 207 | **D** |
| `new CalculateurPrix()` et `new AffectationVehicule()` en dur | `ServiceExpedition` l. 13-14 | **D** |
| État statique modifiable (`Flotte.vehicules`, `Database.expeditions/clients`, `journal`, `compteur`) que l'adaptateur doit vider | `Flotte`, `Database`, `EmailService`, `SmsService`, `ServiceExpedition`, `NouveauSystemeAdapter.reinitialiser` | **D** |
| `AffectationVehicule` dépend des classes concrètes de véhicules | `AffectationVehicule` | **D** |

## Endroits à modifier pour ajouter le mode `FLUVIAL`

1. `CalculateurPrix.calculerPrixHT` : nouvelle branche de tarif (0,40 €/km + 0,02 €/kg, minimum 300 €).
2. `ServiceExpedition.calculerDelai` : nouveau `case` (distance / 360 arrondi au supérieur + 1).
3. `ServiceExpedition.creerExpedition` : règle « pas d'urgence » (condition l. 43, qui ne vise que `MER`). La règle « réfrigéré interdit » est déjà respectée par la condition l. 39 (seuls `ROUTE` et `MER` sont acceptés).
4. `ServiceExpedition.demarrerExpedition` : nouvelle branche pour faire partir le véhicule.
5. `AffectationVehicule.trouverVehicule` : nouvelle branche `instanceof Peniche`.
6. Nouvelle classe `Peniche` qui doit implémenter toutes les méthodes de `IVehicule`, dont `rouler`, `voler` et `activerRefrigeration` en `UnsupportedOperationException`.
7. `Flotte.initialiser` : ajouter la péniche `PEN-0001` de 2 500 t.
8. `Utils.getDistance` : ajouter le trajet Paris → Le Havre (365 km).

## Endroits à modifier pour ajouter le supplément « hors gabarit »

1. `CalculateurPrix.calculerPrixHT` : détecter un colis de plus de 200 cm dans la boucle sur les colis.
2. `CalculateurPrix.calculerPrixHT` : ajouter une ligne `if (horsGabarit) prix = prix * 1.10;` au bon endroit dans l'enchaînement des suppléments.
