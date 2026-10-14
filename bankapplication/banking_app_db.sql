-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 25, 2026 at 01:45 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `banking_app_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `balance` decimal(38,2) DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `name`, `account_number`, `balance`, `created_at`, `updated_at`) VALUES
(1, 'John Edrich C. Viray', '100001', 30355.00, '2026-09-24 21:45:59.000000', '2026-09-25 19:12:44.000000'),
(2, 'Michael A. Ampo', '100002', 100203000.00, '2026-09-24 21:46:27.000000', '2026-09-24 22:09:26.000000'),
(3, 'Doniel P. Buccat', '100003', 62579.00, '2026-09-25 19:10:23.000000', '2026-09-25 19:12:44.000000');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `id` bigint(20) NOT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `amount` decimal(38,2) DEFAULT NULL,
  `balance_after` decimal(38,2) DEFAULT NULL,
  `reference` varchar(255) DEFAULT NULL,
  `reference_account_number` varchar(255) DEFAULT NULL,
  `transaction_date` datetime(6) NOT NULL,
  `type` enum('DEPOSIT','TRANSFER','WITHDRAW') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`id`, `account_number`, `amount`, `balance_after`, `reference`, `reference_account_number`, `transaction_date`, `type`) VALUES
(1, '100001', 123456.00, 223456.00, 'TXN-9CC60FC8', NULL, '2026-09-24 21:50:22.000000', 'DEPOSIT'),
(2, '100001', 100.00, 223356.00, 'TXN-AB749B48', NULL, '2026-09-24 21:51:12.000000', 'WITHDRAW'),
(3, '100001', 100000.00, 123356.00, 'TXN-675A714D', '100002', '2026-09-24 21:53:31.000000', 'TRANSFER'),
(4, '100001', 100000.00, 23356.00, 'TXN-11DC1F6B', '100002', '2026-09-24 21:54:51.000000', 'TRANSFER'),
(5, '100001', 1000.00, 22356.00, 'TXN-F797C005', '100002', '2026-09-24 22:03:46.000000', 'TRANSFER'),
(6, '100001', 1000.00, 21356.00, 'TXN-838F9A99', '100002', '2026-09-24 22:08:34.000000', 'TRANSFER'),
(7, '100001', 1000.00, 20356.00, 'TXN-D0762922', '100002', '2026-09-24 22:09:26.000000', 'TRANSFER'),
(8, '100003', 12345.00, 79345.00, 'TXN-BA9AF050', NULL, '2026-09-25 19:12:11.000000', 'DEPOSIT'),
(9, '100003', 6767.00, 72578.00, 'TXN-E1D3C139', NULL, '2026-09-25 19:12:25.000000', 'WITHDRAW'),
(10, '100003', 9999.00, 62579.00, 'TXN-747A4692', '100001', '2026-09-25 19:12:44.000000', 'TRANSFER');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
