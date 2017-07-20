-- phpMyAdmin SQL Dump
-- version 4.5.4.1
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Jeu 20 Juillet 2017 à 10:24
-- Version du serveur :  5.7.11
-- Version de PHP :  5.6.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `javaee`
--

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` varchar(200) NOT NULL,
  `login` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `statut` varchar(50) NOT NULL,
  `activated` tinyint(1) DEFAULT NULL,
  `confirmAccount` varchar(200) NOT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`id`, `email`, `login`, `password`, `statut`, `activated`, `confirmAccount`, `created_date`) VALUES
(1, 'francois@yopmail.com', 'François', 'QL^ \Z> l$<;', 'Particulier', 1, 'CKA', '2017-07-18 19:10:03'),
(2, 'xavier@yopmail.com', 'Xavier', '@/X\'fXn1l~?', 'Entreprise', 1, 'KBA', '2017-07-18 19:14:36'),
(3, 'test@yopmail.com', 'TEST', 'J?Ls??/', 'Particulier', 1, 'H6A', '2017-07-18 20:02:32');

-- --------------------------------------------------------

--
-- Structure de la table `url`
--

CREATE TABLE `url` (
  `id` int(11) NOT NULL,
  `url_long` varchar(255) NOT NULL,
  `url_short` varchar(255) NOT NULL,
  `date_created` datetime DEFAULT CURRENT_TIMESTAMP,
  `utilisation` int(11) DEFAULT NULL,
  `date_expiration` date DEFAULT NULL,
  `captcha` tinyint(1) DEFAULT NULL,
  `period_start` date DEFAULT NULL,
  `email` varchar(250) DEFAULT NULL,
  `password` varchar(250) DEFAULT NULL,
  `max_use` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `url`
--

INSERT INTO `url` (`id`, `url_long`, `url_short`, `date_created`, `utilisation`, `date_expiration`, `captcha`, `period_start`, `email`, `password`, `max_use`, `user_id`) VALUES
(1, 'https://www.youtube.com/watch?v=LtrhCw2lm8I', 'FDA', '2017-07-18 19:11:53', 5, '2017-08-27', 1, '2017-06-26', NULL, NULL, 120, 1),
(2, 'http://www.hotnewhiphop.com/jay-305s-essential-tracks-new-video.42293.html', '552', '2017-07-18 19:12:58', 1, NULL, NULL, NULL, NULL, 'francois', NULL, NULL),
(3, 'https://trello.com/signup?returnUrl=%2F', '50B', '2017-07-18 19:13:54', 1, NULL, NULL, NULL, 'francois@yopmail.com', 'francois', 120, 1),
(4, 'https://www.youtube.com/watch?v=w6iGAxXZqxI', '2G3', '2017-07-18 19:16:07', 1, '2017-08-06', NULL, NULL, 'xavier@yopmail.com', NULL, 120, 2),
(5, 'http://localhost/mysql/tbl_operations.php?db=javaee&table=users&token=e03a20ca5965b087ce553e6c39cff80f', 'CEC', '2017-07-18 19:16:40', 12, '2017-08-30', NULL, NULL, 'xavier@yopmail.com', NULL, 10, 2),
(6, 'http://www.hotnewhiphop.com/floyd-mayweather-harassed-by-conor-mcgregor-fans-in-london-video-news.35193.html', '1W4', '2017-07-18 19:17:42', 52, NULL, NULL, NULL, NULL, NULL, 200, 2);

-- --------------------------------------------------------

--
-- Structure de la table `urlclick`
--

CREATE TABLE `urlclick` (
  `id` int(11) NOT NULL,
  `url_id` int(11) NOT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `urlclick`
--

INSERT INTO `urlclick` (`id`, `url_id`, `created_date`) VALUES
(1, 1, '2017-06-12 19:12:04'),
(2, 1, '2017-06-12 19:12:09'),
(3, 1, '2017-07-18 19:12:11'),
(4, 1, '2017-07-18 19:12:23'),
(5, 1, '2017-07-18 19:12:23'),
(6, 2, '2017-07-18 19:13:14'),
(7, 3, '2017-07-18 19:14:00'),
(8, 5, '2017-06-11 19:16:49'),
(9, 5, '2017-06-19 19:16:50'),
(10, 5, '2017-07-18 19:16:51'),
(11, 5, '2017-06-27 19:16:52'),
(12, 5, '2017-07-18 19:16:52'),
(13, 5, '2017-07-18 19:16:52'),
(14, 5, '2017-07-18 19:16:53'),
(15, 5, '2017-06-05 19:16:53'),
(16, 5, '2017-06-18 19:16:53'),
(17, 5, '2017-07-18 19:16:53'),
(18, 5, '2017-07-18 19:16:55'),
(19, 5, '2017-07-18 19:16:56'),
(20, 6, '2017-05-22 19:17:59'),
(21, 6, '2017-06-11 19:18:03'),
(22, 6, '2017-06-12 19:18:03'),
(23, 6, '2017-06-13 19:18:04'),
(24, 6, '2017-06-08 19:18:04'),
(25, 6, '2017-07-18 19:18:05'),
(26, 6, '2017-07-18 19:18:05'),
(27, 6, '2017-07-18 19:18:06'),
(28, 6, '2017-07-18 19:18:06'),
(29, 6, '2017-07-24 19:18:06'),
(30, 6, '2017-05-22 19:18:07'),
(31, 6, '2017-07-18 19:18:07'),
(32, 6, '2017-07-18 19:18:07'),
(33, 6, '2017-07-09 19:18:07'),
(34, 6, '2017-07-18 19:18:08'),
(35, 6, '2017-07-18 19:18:08'),
(36, 6, '2017-07-18 19:18:08'),
(37, 6, '2017-07-18 19:18:09'),
(38, 6, '2017-07-18 19:18:09'),
(39, 6, '2017-05-15 19:18:09'),
(40, 6, '2017-07-18 19:18:10'),
(41, 6, '2017-07-18 19:18:10'),
(42, 6, '2017-07-18 19:18:10'),
(43, 6, '2017-07-18 19:18:10'),
(44, 6, '2017-04-10 19:18:10'),
(45, 6, '2017-07-18 19:18:11'),
(46, 6, '2017-07-18 19:18:11'),
(47, 6, '2017-05-14 19:18:11'),
(48, 6, '2017-07-18 19:18:11'),
(49, 6, '2017-07-18 19:18:12'),
(50, 6, '2017-07-18 19:18:15'),
(51, 6, '2017-07-18 19:18:15'),
(52, 6, '2017-07-18 19:18:15'),
(53, 6, '2017-07-18 19:18:16'),
(54, 6, '2017-07-18 19:18:16'),
(55, 6, '2017-07-18 19:18:16'),
(56, 6, '2017-07-18 19:18:16'),
(57, 6, '2017-07-18 19:18:17'),
(58, 6, '2017-07-18 19:18:17'),
(59, 6, '2017-07-18 19:18:17'),
(60, 6, '2017-07-18 19:18:17'),
(61, 6, '2017-07-18 19:18:18'),
(62, 6, '2017-07-18 19:18:18'),
(63, 6, '2017-07-18 19:18:18'),
(64, 6, '2017-07-18 19:18:18'),
(65, 6, '2017-07-18 19:18:18'),
(66, 6, '2017-07-18 19:18:19'),
(67, 6, '2017-07-18 19:18:19'),
(68, 6, '2017-07-18 19:18:19'),
(69, 6, '2017-07-18 19:18:19'),
(70, 6, '2017-07-18 19:18:20'),
(71, 6, '2017-07-18 20:04:15'),
(72, 4, '2017-07-18 20:05:22');


--
-- Index pour les tables exportées
--

--
-- Index pour la table `url`
--
ALTER TABLE `url`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `urlclick`
--
ALTER TABLE `urlclick`
  ADD PRIMARY KEY (`id`),
  ADD KEY `url_id` (`url_id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `url`
--
ALTER TABLE `url`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT pour la table `urlclick`
--
ALTER TABLE `urlclick`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=73;
--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `urlclick`
--
ALTER TABLE `urlclick`
  ADD CONSTRAINT `urlclick_ibfk_1` FOREIGN KEY (`url_id`) REFERENCES `url` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
