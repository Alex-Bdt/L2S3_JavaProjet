-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 06 jan. 2025 à 23:51
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `jeu_java`
--

-- --------------------------------------------------------

--
-- Structure de la table `apprend`
--

CREATE TABLE `apprend` (
  `nomMonstre` varchar(50) NOT NULL,
  `nomCapacite` varchar(50) NOT NULL,
  `niveau` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `apprend`
--

INSERT INTO `apprend` (`nomMonstre`, `nomCapacite`, `niveau`) VALUES
('ABZ', 'Charme', 9),
('ABZ', 'Coup Croix', 2),
('ABZ', 'Lame Sainte', 17),
('Ash', 'Acide', 5),
('Ash', 'Frappe Atlas', 12),
('Ash', 'Puissance Cachée', 18),
('Bubblefrog', 'Chaîne Glacée', 8),
('Bubblefrog', 'Coqui-Lame', 4),
('Bubblefrog', 'Laser Glace', 14),
('Colossimiaou', 'Griffe', 3),
('Colossimiaou', 'Lance Flamme', 18),
('Colossimiaou', 'Morsure', 6),
('Cutiscrawl', 'Griffe', 4),
('Cutiscrawl', 'Laser Glace', 13),
('Cutiscrawl', 'Parabocharge', 8),
('Demonicwind', 'Griffe', 5),
('Demonicwind', 'Mort\'Ailes', 21),
('Demonicwind', 'Puissance Cachée', 10),
('Glaglanoix', 'Griffe', 3),
('Glaglanoix', 'Laser Glace', 13),
('Glaglanoix', 'Morsure', 6),
('Harshiager', 'Chaîne Glacée', 9),
('Harshiager', 'Laser Glace', 18),
('Harshiager', 'Morsure', 4),
('Id-Ro', 'Lance Draco', 25),
('Id-Ro', 'Morsure', 5),
('Id-Ro', 'Mort\'Ailes', 15),
('Madeline', 'Chaîne Glacée', 13),
('Madeline', 'Charme', 2),
('Madeline', 'Mort\'Ailes', 21),
('Seamachtiger', 'Coup Croix', 9),
('Seamachtiger', 'Griffe', 4),
('Seamachtiger', 'Tonnerre', 18),
('Snakeblitz', 'Acide', 5),
('Snakeblitz', 'Puissance Cachée', 12),
('Snakeblitz', 'Tonnerre', 15),
('Spektacoon', 'Acide', 3),
('Spektacoon', 'Anneau de Feu', 10),
('Spektacoon', 'Lance Flamme', 18),
('Tip-Taupe', 'Charme', 2),
('Tip-Taupe', 'Coup Croix', 5),
('Tip-Taupe', 'Lame Sainte', 10),
('Twoflamjaw', 'Lance Flamme', 18),
('Twoflamjaw', 'Morsure', 5),
('Twoflamjaw', 'Tonnerre', 17),
('Wishmaus', 'Charme', 4),
('Wishmaus', 'Danse Plumes', 8),
('Wishmaus', 'Frappe Atlas', 14),
('Wolfbone', 'Griffe', 4),
('Wolfbone', 'Morsure', 7),
('Wolfbone', 'Tacle Lourd', 16),
('Wulfbone', 'Griffe', 4),
('Wulfbone', 'Morsure', 7),
('Wulfbone', 'Tacle Lourd', 16);

-- --------------------------------------------------------

--
-- Structure de la table `capacite`
--

CREATE TABLE `capacite` (
  `nomCapacite` varchar(50) NOT NULL,
  `puissance` int(11) NOT NULL,
  `precis` int(11) NOT NULL,
  `cout` int(11) NOT NULL,
  `effet` varchar(50) DEFAULT NULL,
  `description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `capacite`
--

INSERT INTO `capacite` (`nomCapacite`, `puissance`, `precis`, `cout`, `effet`, `description`) VALUES
('Acide', 0, 100, 15, 'MD6', NULL),
('Anneau de Feu', 0, 100, 20, 'BA5', NULL),
('Aurasphère', 75, 100, 14, NULL, NULL),
('Bang Sonique', 90, 80, 18, NULL, NULL),
('Chaîne Glacée', 0, 95, 20, 'MV4', NULL),
('Charge', 40, 100, 0, NULL, NULL),
('Charme', 0, 90, 25, 'MA8', NULL),
('Coqui-Lame', 75, 100, 0, NULL, NULL),
('Coup Croix', 55, 100, 0, NULL, NULL),
('Danse Plumes', 0, 100, 16, 'BV5', NULL),
('Frappe Atlas', 110, 85, 0, NULL, NULL),
('Griffe', 50, 100, 0, NULL, NULL),
('Lame Sainte', 125, 95, 0, NULL, NULL),
('Lance Draco', 100, 100, 30, NULL, NULL),
('Lance Flamme', 85, 95, 10, NULL, NULL),
('Laser Glace', 85, 95, 10, NULL, NULL),
('Morsure', 60, 100, 0, NULL, NULL),
('Mort\'Ailes', 100, 100, 30, NULL, NULL),
('Parabocharge', 0, 100, 12, 'BS5', NULL),
('Piqûre', 60, 100, 0, NULL, NULL),
('Puissance Cachée', 70, 100, 15, NULL, NULL),
('Tacle Lourd', 90, 85, 0, NULL, NULL),
('Tonnerre', 85, 95, 10, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `item`
--

CREATE TABLE `item` (
  `idItem` smallint(6) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `efficacite` varchar(50) NOT NULL,
  `prix` int(11) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `item`
--

INSERT INTO `item` (`idItem`, `nom`, `efficacite`, `prix`, `description`) VALUES
(1, 'Gourde du Brave', 'BA8', 25, 'Augmente fortement l\'Attaque'),
(3, 'Soupe Suspecte', 'MS4', 5, 'Soupe peu ragoutante'),
(5, 'Whisky Cuirassé', 'BD8', 18, 'Augment fortement la Défense'),
(6, 'Potion', 'BP3', 12, 'Redonne 30PV'),
(7, 'Super Potion', 'BP6', 20, 'Redonne 60PV'),
(8, 'Hyper Potion', 'BP9', 30, 'Redonne 90PV'),
(9, 'Monster Energie', 'BE4', 14, 'Redonne 40PE');

-- --------------------------------------------------------

--
-- Structure de la table `joueur`
--

CREATE TABLE `joueur` (
  `id` int(11) NOT NULL,
  `roundMax` int(11) NOT NULL,
  `dateSave` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `joueur`
--

INSERT INTO `joueur` (`id`, `roundMax`, `dateSave`) VALUES
(1, 0, '2025-01-06');

-- --------------------------------------------------------

--
-- Structure de la table `monstre`
--

CREATE TABLE `monstre` (
  `nomMonstre` varchar(50) NOT NULL,
  `PV` int(11) NOT NULL,
  `PE` int(11) NOT NULL,
  `attaque` double NOT NULL,
  `special` double NOT NULL,
  `defense` double NOT NULL,
  `vitesse` double NOT NULL,
  `tauxCapture` double NOT NULL,
  `description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `monstre`
--

INSERT INTO `monstre` (`nomMonstre`, `PV`, `PE`, `attaque`, `special`, `defense`, `vitesse`, `tauxCapture`, `description`) VALUES
('ABZ', 100, 100, 120, 120, 120, 120, 0, 'Le joueur'),
('Ash', 100, 70, 100, 60, 80, 110, 0, 'Un monstre électrique rapide et adorable.'),
('Bubblefrog', 80, 100, 40, 120, 65, 80, 190, ''),
('Bulbizarre', 120, 60, 65, 75, 70, 60, 0, 'Une créature plante/poison dotée de lianes.'),
('Colossimiaou', 100, 50, 110, 45, 65, 70, 150, 'Etre féline à la fourre de flamme qui embrasse le sol quand il marche'),
('Cutiscrawl', 60, 50, 70, 30, 60, 65, 220, ''),
('Demonicwind', 70, 90, 40, 95, 65, 95, 170, ''),
('Dracofeu', 150, 80, 95.5, 100, 85, 90, 0, 'Un dragon puissant maîtrisant le feu.'),
('Glaglanoix', 65, 60, 50, 70, 40, 90, 230, ''),
('Harshiager', 70, 140, 70, 90, 60, 100, 160, 'Traque sans relâche, personne ne survie après avoir était visée'),
('Id-Ro', 130, 70, 150, 80, 90, 70, 60, ''),
('Madeline', 120, 90, 60, 100, 70, 95, 0, 'Un fantôme espiègle capable de lancer des malédictions.'),
('Pyukumuku', 200, 140, 30, 30, 200, 10, 10, 'Master'),
('Ronflex', 300, 40, 110, 70, 120, 20, 0, 'Un géant paresseux avec une défense solide.'),
('Seamachtiger', 110, 20, 160, 30, 110, 55, 80, ''),
('Snakeblitz', 80, 120, 70, 100, 50, 85, 140, ''),
('Spektacoon', 60, 110, 60, 110, 60, 110, 170, ''),
('Tip-Taupe', 65, 55, 90, 60, 80, 80, 210, ''),
('Twoflamjaw', 110, 70, 80, 130, 90, 75, 100, ''),
('Wishmaus', 80, 100, 30, 80, 65, 95, 200, ''),
('Wolfbone', 80, 90, 50, 100, 70, 75, 140, ''),
('Wulfbone', 70, 30, 120, 30, 80, 75, 130, '');

-- --------------------------------------------------------

--
-- Structure de la table `monstreacquis`
--

CREATE TABLE `monstreacquis` (
  `idjoueur` int(11) NOT NULL,
  `nomMonstre` varchar(50) NOT NULL,
  `move1` varchar(50) DEFAULT NULL,
  `move2` varchar(50) DEFAULT NULL,
  `move3` varchar(50) DEFAULT NULL,
  `move4` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `monstreacquis`
--

INSERT INTO `monstreacquis` (`idjoueur`, `nomMonstre`, `move1`, `move2`, `move3`, `move4`) VALUES
(1, 'Glaglanoix', 'Charge', 'Acide', NULL, NULL),
(1, 'Tip-Taupe', 'Charge', NULL, NULL, NULL);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `apprend`
--
ALTER TABLE `apprend`
  ADD PRIMARY KEY (`nomMonstre`,`nomCapacite`),
  ADD KEY `nomCapacite` (`nomCapacite`);

--
-- Index pour la table `capacite`
--
ALTER TABLE `capacite`
  ADD PRIMARY KEY (`nomCapacite`);

--
-- Index pour la table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`idItem`);

--
-- Index pour la table `joueur`
--
ALTER TABLE `joueur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `monstre`
--
ALTER TABLE `monstre`
  ADD PRIMARY KEY (`nomMonstre`);

--
-- Index pour la table `monstreacquis`
--
ALTER TABLE `monstreacquis`
  ADD PRIMARY KEY (`idjoueur`,`nomMonstre`),
  ADD KEY `move1` (`move1`),
  ADD KEY `move2` (`move2`),
  ADD KEY `move3` (`move3`),
  ADD KEY `move4` (`move4`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `item`
--
ALTER TABLE `item`
  MODIFY `idItem` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `joueur`
--
ALTER TABLE `joueur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `apprend`
--
ALTER TABLE `apprend`
  ADD CONSTRAINT `APPREND_ibfk_1` FOREIGN KEY (`nomMonstre`) REFERENCES `monstre` (`nomMonstre`),
  ADD CONSTRAINT `APPREND_ibfk_2` FOREIGN KEY (`nomCapacite`) REFERENCES `capacite` (`nomCapacite`);

--
-- Contraintes pour la table `monstreacquis`
--
ALTER TABLE `monstreacquis`
  ADD CONSTRAINT `monstreacquis_ibfk_1` FOREIGN KEY (`move1`) REFERENCES `capacite` (`nomCapacite`),
  ADD CONSTRAINT `monstreacquis_ibfk_2` FOREIGN KEY (`move2`) REFERENCES `capacite` (`nomCapacite`),
  ADD CONSTRAINT `monstreacquis_ibfk_3` FOREIGN KEY (`move3`) REFERENCES `capacite` (`nomCapacite`),
  ADD CONSTRAINT `monstreacquis_ibfk_4` FOREIGN KEY (`move4`) REFERENCES `capacite` (`nomCapacite`),
  ADD CONSTRAINT `monstreacquis_ibfk_5` FOREIGN KEY (`idjoueur`) REFERENCES `joueur` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
