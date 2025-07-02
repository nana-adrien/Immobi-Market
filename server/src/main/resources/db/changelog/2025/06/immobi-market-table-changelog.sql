-- liquibase formatted sql

-- changeset LENOVO_P51S:1751199609870-1
CREATE TABLE app_user
(
    id           UUID NOT NULL,
    email        VARCHAR(255),
    mot_de_passe VARCHAR(255),
    role         INTEGER,
    CONSTRAINT pk_app_user PRIMARY KEY (id)
);

-- changeset LENOVO_P51S:1751199609870-2
CREATE TABLE arrondissement
(
    id       UUID NOT NULL,
    nom      VARCHAR(255),
    ville_id UUID,
    CONSTRAINT pk_arrondissement PRIMARY KEY (id)
);

-- changeset LENOVO_P51S:1751199609870-3
CREATE TABLE carateristiques_offre_construction
(
    id UUID NOT NULL,
    CONSTRAINT pk_carateristiques_offre_construction PRIMARY KEY (id)
);

-- changeset LENOVO_P51S:1751199609870-4
CREATE TABLE carateristiques_offre_construction_habitation
(
    id UUID NOT NULL,
    CONSTRAINT pk_carateristiques_offre_construction_habitation PRIMARY KEY (id)
);

-- changeset LENOVO_P51S:1751199609870-5
CREATE TABLE carateristiques_offre_terrain
(
    id UUID NOT NULL,
    CONSTRAINT pk_carateristiques_offre_terrain PRIMARY KEY (id)
);

-- changeset LENOVO_P51S:1751199609870-6
CREATE TABLE commentaire
(
    id               UUID    NOT NULL,
    note             INTEGER NOT NULL,
    contenu          VARCHAR(255),
    date_publication TIMESTAMP WITHOUT TIME ZONE,
    offre_id         UUID,
    app_user_id      UUID,
    CONSTRAINT pk_commentaire PRIMARY KEY (id)
);

-- changeset LENOVO_P51S:1751199609870-7
CREATE TABLE consultation
(
    id                   UUID NOT NULL,
    date_de_consultation TIMESTAMP WITHOUT TIME ZONE,
    offre_id             UUID,
    app_user_id          UUID,
    CONSTRAINT pk_consultation PRIMARY KEY (id)
);

-- changeset LENOVO_P51S:1751199609870-8
CREATE TABLE conversation
(
    id               UUID NOT NULL,
    participant_id   UUID,
    createur_id      UUID,
    date_de_creation TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_conversation PRIMARY KEY (id)
);

-- changeset LENOVO_P51S:1751199609870-9
CREATE TABLE departement
(
    id        UUID NOT NULL,
    nom       VARCHAR(255),
    region_id UUID,
    CONSTRAINT pk_departement PRIMARY KEY (id)
);

-- changeset LENOVO_P51S:1751199609870-10
CREATE TABLE equipement
(
    id                    UUID NOT NULL,
    nom                   VARCHAR(255),
    type_de_bien_associer SMALLINT,
    CONSTRAINT pk_equipement PRIMARY KEY (id)
);

-- changeset LENOVO_P51S:1751199609870-11
CREATE TABLE favori
(
    id         UUID NOT NULL,
    date_ajout TIMESTAMP WITHOUT TIME ZONE,
    offre_id   UUID,
    user_id    UUID,
    CONSTRAINT pk_favori PRIMARY KEY (id)
);

-- changeset LENOVO_P51S:1751199609870-12
CREATE TABLE image
(
    id           UUID    NOT NULL,
    url          TEXT,
    is_principal BOOLEAN NOT NULL,
    CONSTRAINT pk_image PRIMARY KEY (id)
);

-- changeset LENOVO_P51S:1751199609870-13
CREATE TABLE message
(
    id                   UUID    NOT NULL,
    conversation_id      UUID,
    emetteur_id          UUID,
    contenu              VARCHAR(255),
    image                VARCHAR(255),
    recu                 BOOLEAN NOT NULL,
    principal_message_id UUID,
    consulte             BOOLEAN NOT NULL,
    date_envoi           TIMESTAMP WITHOUT TIME ZONE,
    date_reception       TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_message PRIMARY KEY (id)
);

-- changeset LENOVO_P51S:1751199609870-14
CREATE TABLE offre_carateristiques
(
    id UUID NOT NULL,
    CONSTRAINT pk_offre_carateristiques PRIMARY KEY (id)
);

-- changeset LENOVO_P51S:1751199609870-15
CREATE TABLE offre_immobiliere
(
    id                 UUID             NOT NULL,
    titre              VARCHAR(255),
    description        VARCHAR(255),
    superficie         DOUBLE PRECISION NOT NULL,
    type_de_bien       INTEGER,
    etat               INTEGER,
    type_doffre        INTEGER,
    prix               INTEGER          NOT NULL,
    caracteristique_id UUID,
    createur_id        UUID,
    date_creation      TIMESTAMP WITHOUT TIME ZONE,
    date_publication   TIMESTAMP WITHOUT TIME ZONE,
    date_expiration    TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_offreimmobiliere PRIMARY KEY (id)
);

-- changeset LENOVO_P51S:1751199609870-16
CREATE TABLE offre_immobiliere_liste_images
(
    offre_immobiliere_id UUID NOT NULL,
    liste_images_id      UUID NOT NULL
);

-- changeset LENOVO_P51S:1751199609870-17
CREATE TABLE pays
(
    id  UUID NOT NULL,
    nom VARCHAR(255),
    CONSTRAINT pk_pays PRIMARY KEY (id)
);

-- changeset LENOVO_P51S:1751199609870-18
CREATE TABLE quartier
(
    id                UUID NOT NULL,
    nom               VARCHAR(255),
    arrondissement_id UUID,
    CONSTRAINT pk_quartier PRIMARY KEY (id)
);

-- changeset LENOVO_P51S:1751199609870-19
CREATE TABLE region
(
    id      UUID NOT NULL,
    nom     VARCHAR(255),
    pays_id UUID,
    CONSTRAINT pk_region PRIMARY KEY (id)
);

-- changeset LENOVO_P51S:1751199609870-20
CREATE TABLE rue
(
    id          UUID NOT NULL,
    nom         VARCHAR(255),
    quartier_id UUID,
    CONSTRAINT pk_rue PRIMARY KEY (id)
);

-- changeset LENOVO_P51S:1751199609870-21
CREATE TABLE studio
(
    id              UUID             NOT NULL,
    superficie      DOUBLE PRECISION NOT NULL,
    typedhabitation INTEGER,
    CONSTRAINT pk_studio PRIMARY KEY (id)
);

-- changeset LENOVO_P51S:1751199609870-22
CREATE TABLE user_address
(
    id        UUID             NOT NULL,
    latitude  DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    rue_id    UUID,
    CONSTRAINT pk_useraddress PRIMARY KEY (id)
);

-- changeset LENOVO_P51S:1751199609870-23
CREATE TABLE user_awaiting_verification
(
    id           UUID    NOT NULL,
    code         VARCHAR(255),
    identifier   VARCHAR(255),
    password     VARCHAR(255),
    country_code VARCHAR(255),
    region_code  VARCHAR(255),
    subject      VARCHAR(255),
    is_email     BOOLEAN NOT NULL,
    attempts     INTEGER NOT NULL,
    operation    VARCHAR(255),
    generated_at TIMESTAMP WITHOUT TIME ZONE,
    expires_at   TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_userawaitingverification PRIMARY KEY (id)
);

-- changeset LENOVO_P51S:1751199609870-24
CREATE TABLE user_phone_number
(
    user_profile_user_id UUID NOT NULL,
    phone_number         VARCHAR(255),
    country_code         VARCHAR(255),
    region               VARCHAR(255),
    CONSTRAINT pk_userphonenumber PRIMARY KEY (user_profile_user_id)
);

-- changeset LENOVO_P51S:1751199609870-25
CREATE TABLE user_profile
(
    user_id                  UUID    NOT NULL,
    nom                      VARCHAR(255),
    prenom                   VARCHAR(255),
    url_profil               TEXT,
    date_naissance           TIMESTAMP WITHOUT TIME ZONE,
    numero_telephone         VARCHAR(255),
    genre                    VARCHAR(255),
    telephone_utilisateur_id UUID,
    telephone_verifie        BOOLEAN NOT NULL,
    profil_complet           BOOLEAN NOT NULL,
    en_ligne                 BOOLEAN NOT NULL,
    date_creation            TIMESTAMP WITHOUT TIME ZONE,
    dernier_temps_connexion  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_userprofile PRIMARY KEY (user_id)
);



-- changeset LENOVO_P51S:1751199609870-26
CREATE TABLE user_settings
(
    user_id                            UUID    NOT NULL,
    enabled_two_factor_authentication  BOOLEAN NOT NULL,
    prefer_language_tag                VARCHAR(255),
    enable_light_mode                  BOOLEAN NOT NULL,
    notification_canal                 INTEGER,
    user_can_change_notification_canal BOOLEAN NOT NULL,
    CONSTRAINT pk_usersettings PRIMARY KEY (user_id)
);

-- changeset LENOVO_P51S:1751199609870-27
CREATE TABLE user_token
(
    id            UUID NOT NULL,
    access_token  TEXT,
    refresh_token TEXT,
    user_id       UUID,
    CONSTRAINT pk_usertoken PRIMARY KEY (id)
);

-- changeset LENOVO_P51S:1751199609870-28
CREATE TABLE ville
(
    id             UUID NOT NULL,
    nom            VARCHAR(255),
    departement_id UUID,
    CONSTRAINT pk_ville PRIMARY KEY (id)
);

-- changeset LENOVO_P51S:1751199609870-29
ALTER TABLE offre_immobiliere_liste_images
    ADD CONSTRAINT uc_offre_immobiliere_liste_images_listeimages UNIQUE (liste_images_id);

-- changeset LENOVO_P51S:1751199609870-30
ALTER TABLE arrondissement
    ADD CONSTRAINT FK_ARRONDISSEMENT_ON_VILLE FOREIGN KEY (ville_id) REFERENCES ville (id);

-- changeset LENOVO_P51S:1751199609870-31
ALTER TABLE carateristiques_offre_construction_habitation
    ADD CONSTRAINT FK_CARATERISTIQUES_OFFRE_CONSTRUCTION_HABITATION_ON_ID FOREIGN KEY (id) REFERENCES carateristiques_offre_construction (id);

-- changeset LENOVO_P51S:1751199609870-32
ALTER TABLE carateristiques_offre_construction
    ADD CONSTRAINT FK_CARATERISTIQUES_OFFRE_CONSTRUCTION_ON_ID FOREIGN KEY (id) REFERENCES offre_carateristiques (id);

-- changeset LENOVO_P51S:1751199609870-33
ALTER TABLE carateristiques_offre_terrain
    ADD CONSTRAINT FK_CARATERISTIQUES_OFFRE_TERRAIN_ON_ID FOREIGN KEY (id) REFERENCES offre_carateristiques (id);

-- changeset LENOVO_P51S:1751199609870-34
ALTER TABLE commentaire
    ADD CONSTRAINT FK_COMMENTAIRE_ON_APP_USER FOREIGN KEY (app_user_id) REFERENCES app_user (id);

-- changeset LENOVO_P51S:1751199609870-35
ALTER TABLE commentaire
    ADD CONSTRAINT FK_COMMENTAIRE_ON_OFFRE FOREIGN KEY (offre_id) REFERENCES offre_immobiliere (id);

-- changeset LENOVO_P51S:1751199609870-36
ALTER TABLE consultation
    ADD CONSTRAINT FK_CONSULTATION_ON_APP_USER FOREIGN KEY (app_user_id) REFERENCES app_user (id);

-- changeset LENOVO_P51S:1751199609870-37
ALTER TABLE consultation
    ADD CONSTRAINT FK_CONSULTATION_ON_OFFRE FOREIGN KEY (offre_id) REFERENCES offre_immobiliere (id);

-- changeset LENOVO_P51S:1751199609870-38
ALTER TABLE conversation
    ADD CONSTRAINT FK_CONVERSATION_ON_CREATEUR FOREIGN KEY (createur_id) REFERENCES app_user (id);

-- changeset LENOVO_P51S:1751199609870-39
ALTER TABLE conversation
    ADD CONSTRAINT FK_CONVERSATION_ON_PARTICIPANT FOREIGN KEY (participant_id) REFERENCES app_user (id);

-- changeset LENOVO_P51S:1751199609870-40
ALTER TABLE departement
    ADD CONSTRAINT FK_DEPARTEMENT_ON_REGION FOREIGN KEY (region_id) REFERENCES region (id);

-- changeset LENOVO_P51S:1751199609870-41
ALTER TABLE favori
    ADD CONSTRAINT FK_FAVORI_ON_OFFRE FOREIGN KEY (offre_id) REFERENCES offre_immobiliere (id);

-- changeset LENOVO_P51S:1751199609870-42
ALTER TABLE favori
    ADD CONSTRAINT FK_FAVORI_ON_USER FOREIGN KEY (user_id) REFERENCES app_user (id);

-- changeset LENOVO_P51S:1751199609870-43
ALTER TABLE message
    ADD CONSTRAINT FK_MESSAGE_ON_CONVERSATION FOREIGN KEY (conversation_id) REFERENCES conversation (id);

-- changeset LENOVO_P51S:1751199609870-44
ALTER TABLE message
    ADD CONSTRAINT FK_MESSAGE_ON_EMETTEUR FOREIGN KEY (emetteur_id) REFERENCES conversation (id);

-- changeset LENOVO_P51S:1751199609870-45
ALTER TABLE message
    ADD CONSTRAINT FK_MESSAGE_ON_PRINCIPAL_MESSAGE FOREIGN KEY (principal_message_id) REFERENCES message (id);

-- changeset LENOVO_P51S:1751199609870-46
ALTER TABLE offre_immobiliere
    ADD CONSTRAINT FK_OFFREIMMOBILIERE_ON_CARACTERISTIQUE FOREIGN KEY (caracteristique_id) REFERENCES offre_carateristiques (id);

-- changeset LENOVO_P51S:1751199609870-47
ALTER TABLE offre_immobiliere
    ADD CONSTRAINT FK_OFFREIMMOBILIERE_ON_CREATEUR FOREIGN KEY (createur_id) REFERENCES app_user (id);

-- changeset LENOVO_P51S:1751199609870-48
ALTER TABLE quartier
    ADD CONSTRAINT FK_QUARTIER_ON_ARRONDISSEMENT FOREIGN KEY (arrondissement_id) REFERENCES arrondissement (id);

-- changeset LENOVO_P51S:1751199609870-49
ALTER TABLE region
    ADD CONSTRAINT FK_REGION_ON_PAYS FOREIGN KEY (pays_id) REFERENCES pays (id);

-- changeset LENOVO_P51S:1751199609870-50
ALTER TABLE rue
    ADD CONSTRAINT FK_RUE_ON_QUARTIER FOREIGN KEY (quartier_id) REFERENCES quartier (id);

-- changeset LENOVO_P51S:1751199609870-51
ALTER TABLE studio
    ADD CONSTRAINT FK_STUDIO_ON_ID FOREIGN KEY (id) REFERENCES carateristiques_offre_construction_habitation (id);

-- changeset LENOVO_P51S:1751199609870-52
ALTER TABLE user_address
    ADD CONSTRAINT FK_USERADDRESS_ON_RUE FOREIGN KEY (rue_id) REFERENCES rue (id);

-- changeset LENOVO_P51S:1751199609870-53
ALTER TABLE user_phone_number
    ADD CONSTRAINT FK_USERPHONENUMBER_ON_USERPROFILE_USER FOREIGN KEY (user_profile_user_id) REFERENCES user_profile (user_id);

-- changeset LENOVO_P51S:1751199609870-54
ALTER TABLE user_profile
    ADD CONSTRAINT FK_USERPROFILE_ON_TELEPHONEUTILISATEUR FOREIGN KEY (telephone_utilisateur_id) REFERENCES user_phone_number (user_profile_user_id);

-- changeset LENOVO_P51S:1751199609870-55
ALTER TABLE user_profile
    ADD CONSTRAINT FK_USERPROFILE_ON_USER FOREIGN KEY (user_id) REFERENCES app_user (id);

-- changeset LENOVO_P51S:1751199609870-56
ALTER TABLE user_settings
    ADD CONSTRAINT FK_USERSETTINGS_ON_USER FOREIGN KEY (user_id) REFERENCES app_user (id);

-- changeset LENOVO_P51S:1751199609870-57
ALTER TABLE user_token
    ADD CONSTRAINT FK_USERTOKEN_ON_USER FOREIGN KEY (user_id) REFERENCES app_user (id);

-- changeset LENOVO_P51S:1751199609870-58
ALTER TABLE ville
    ADD CONSTRAINT FK_VILLE_ON_DEPARTEMENT FOREIGN KEY (departement_id) REFERENCES departement (id);

-- changeset LENOVO_P51S:1751199609870-59
ALTER TABLE offre_immobiliere_liste_images
    ADD CONSTRAINT fk_offimmlisima_on_image FOREIGN KEY (liste_images_id) REFERENCES image (id);

-- changeset LENOVO_P51S:1751199609870-60
ALTER TABLE offre_immobiliere_liste_images
    ADD CONSTRAINT fk_offimmlisima_on_offre_immobiliere FOREIGN KEY (offre_immobiliere_id) REFERENCES offre_immobiliere (id);

