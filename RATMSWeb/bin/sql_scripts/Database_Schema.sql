-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 20, 2020 at 05:07 AM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `report_gen`
--

-- --------------------------------------------------------

--
-- Table structure for table `assessement_candidate_rel`
--

CREATE TABLE `assessement_candidate_rel` (
  `grp_technology_id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL,
  `obtained_marks` int(3) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `percentage_marks` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `assessment`
--

CREATE TABLE `assessment` (
  `assessment_id` int(10) NOT NULL,
  `assessment_name` varchar(30) NOT NULL,
  `creation_date` date NOT NULL DEFAULT current_timestamp(),
  `expiry_date` date NOT NULL,
  `created_by` int(10) NOT NULL,
  `assessment_type_id` int(10) NOT NULL,
  `active_status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `assessment_type`
--

CREATE TABLE `assessment_type` (
  `assessment_type_id` int(11) NOT NULL,
  `assessment_type_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `batch`
--

CREATE TABLE `batch` (
  `batch_id` int(10) NOT NULL,
  `batch_name` varchar(30) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `batch_description` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `document`
--

CREATE TABLE `document` (
  `user_id` int(10) NOT NULL,
  `verification_status` tinyint(1) NOT NULL,
  `verified_by` int(10) NOT NULL,
  `verified_date` datetime NOT NULL,
  `verification_remarks` varchar(100) NOT NULL,
  `document_path` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `evaluation_criteria`
--

CREATE TABLE `evaluation_criteria` (
  `criteria_id` int(10) NOT NULL,
  `grp_technology_id` int(10) NOT NULL,
  `criteria_name` varchar(30) NOT NULL,
  `criteria_minmarks` int(10) NOT NULL,
  `criteria_maxmarks` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `feature`
--

CREATE TABLE `feature` (
  `feature_id` int(10) NOT NULL,
  `feature_name` varchar(50) NOT NULL,
  `module_id` int(10) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `active_status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `feature`
--

INSERT INTO `feature` (`feature_id`, `feature_name`, `module_id`, `creation_date`, `active_status`) VALUES
(1, 'Login using valid credentials', 1, '2019-12-04 06:36:35', 1),
(2, 'Change password', 1, '2019-12-04 06:36:35', 1),
(3, 'Create a new role', 2, '2019-12-04 06:36:35', 1),
(4, 'View all available roles', 2, '2019-12-04 06:36:35', 1),
(5, 'Delete a role', 2, '2019-12-04 06:36:35', 1),
(6, 'Update a role', 2, '2019-12-04 06:36:35', 1),
(7, 'Show features for role', 2, '2019-12-15 20:12:51', 1),
(8, 'Create a user ', 3, '2019-12-15 20:13:03', 1),
(9, 'View user status', 3, '2019-12-04 06:36:35', 1),
(10, 'Users training information', 3, '2019-12-15 20:13:32', 1),
(11, 'View project allocation', 3, '2019-12-15 20:13:42', 1),
(12, 'Delete user', 3, '2019-12-15 20:13:56', 1),
(13, 'Update user details', 3, '2019-12-04 06:36:35', 1),
(14, 'Upload project allocation details ', 3, '2019-12-16 05:16:59', 1),
(15, 'Documents of a user', 4, '2019-12-15 20:14:49', 1),
(16, 'Upload documents of a user', 4, '2019-12-04 06:36:35', 1),
(17, 'Update document verification status', 4, '2019-12-15 20:15:59', 1),
(18, 'Upload course plan', 5, '2019-12-04 06:36:35', 1),
(19, 'Delete course plan', 5, '2019-12-04 06:36:35', 1),
(20, 'View course plan', 5, '2019-12-04 06:36:35', 1),
(21, 'Upload weekly training plan', 5, '2019-12-04 06:36:35', 1),
(22, 'Delete training plan', 5, '2019-12-04 06:36:35', 1),
(23, 'Update training plan', 5, '2019-12-04 06:36:35', 1),
(24, 'View training plan', 5, '2019-12-15 20:16:20', 1),
(25, 'Create training group', 5, '2019-12-16 05:18:20', 1),
(26, 'Assign Users to training group', 5, '2019-12-04 06:36:35', 1),
(27, 'Update training group', 5, '2019-12-04 06:36:35', 1),
(28, 'View training group details', 5, '2019-12-04 06:36:35', 1),
(29, 'Delete training group ', 5, '2019-12-15 20:18:05', 1),
(30, 'Create technology', 6, '2019-12-04 06:36:35', 1),
(31, 'Delete Technology', 6, '2019-12-04 06:36:35', 1),
(32, 'Update Technology', 6, '2019-12-04 06:36:35', 1),
(33, 'View Technology List', 6, '2019-12-04 06:36:35', 1),
(34, 'Create Assessment', 7, '2019-12-04 06:36:35', 1),
(35, 'Delete Assessment', 7, '2019-12-04 06:36:35', 1),
(36, 'Update Assessment', 7, '2019-12-04 06:36:35', 1),
(37, 'View all Assessments', 7, '2019-12-04 06:36:35', 1),
(38, 'Create Evaluation Criteria', 7, '2019-12-04 06:36:35', 1),
(39, 'Delete Evaluation Criteria', 7, '2019-12-04 06:36:35', 1),
(40, 'Update Evaluation Criteria', 7, '2019-12-04 06:36:35', 1),
(41, 'Assign Assessment to trainees', 7, '2019-12-04 06:36:35', 1),
(42, 'Share assessment to trainers', 7, '2019-12-04 06:36:35', 1),
(43, 'Upload batch assessment details', 7, '2019-12-16 05:14:33', 1),
(44, 'View individual report', 8, '2019-12-15 20:17:10', 1),
(45, 'View Batchwise report', 8, '2019-12-15 20:17:21', 1),
(46, 'Training Group status report', 8, '2019-12-15 20:17:52', 1),
(47, 'Batchwise training status report', 8, '2019-12-04 06:36:35', 1),
(48, 'Upload documents ', 4, '2019-12-04 06:36:35', 1);

-- --------------------------------------------------------

--
-- Table structure for table `module`
--

CREATE TABLE `module` (
  `module_id` int(10) NOT NULL,
  `module_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `module`
--

INSERT INTO `module` (`module_id`, `module_name`) VALUES
(7, 'Assessment'),
(1, 'Login Module'),
(4, 'On Boarding'),
(8, 'Report'),
(2, 'Role Management'),
(6, 'Technology'),
(5, 'Training Management'),
(3, 'User Management');

-- --------------------------------------------------------

--
-- Table structure for table `project_allocation`
--

CREATE TABLE `project_allocation` (
  `project_id` int(10) NOT NULL,
  `project_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` int(10) NOT NULL,
  `role_name` varchar(30) NOT NULL,
  `active_status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role_name`, `active_status`) VALUES
(1, 'Admin', 1),
(3, 'Trainer', 1),
(4, 'Trainee', 1);

-- --------------------------------------------------------

--
-- Table structure for table `role_feature_rel`
--

CREATE TABLE `role_feature_rel` (
  `role_id` int(10) NOT NULL,
  `feature_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role_feature_rel`
--

INSERT INTO `role_feature_rel` (`role_id`, `feature_id`) VALUES
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(1, 11),
(1, 12),
(1, 13),
(1, 14),
(1, 15),
(1, 16),
(1, 17),
(1, 18),
(1, 19),
(1, 20),
(1, 21),
(1, 22),
(1, 23),
(1, 24),
(1, 25),
(1, 26),
(1, 27),
(1, 28),
(1, 29),
(1, 30),
(1, 31),
(1, 32),
(1, 33),
(1, 34),
(1, 35),
(1, 36),
(1, 37),
(1, 38),
(1, 39),
(1, 40),
(1, 41),
(1, 42),
(1, 43),
(1, 44),
(1, 45),
(1, 46),
(1, 47),
(3, 10),
(3, 20),
(3, 21),
(3, 22),
(3, 23),
(3, 24),
(3, 25),
(3, 26),
(3, 28),
(3, 33),
(3, 34),
(3, 35),
(3, 36),
(3, 37),
(3, 38),
(3, 39),
(3, 40),
(3, 41),
(3, 42),
(3, 44),
(3, 45),
(3, 46),
(3, 47),
(4, 44);

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE `status` (
  `status_id` int(10) NOT NULL,
  `status_name` varchar(30) NOT NULL,
  `status_type` int(1) NOT NULL,
  `active_status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `technology`
--

CREATE TABLE `technology` (
  `technology_id` int(10) NOT NULL,
  `technology_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `training_group`
--

CREATE TABLE `training_group` (
  `training_group_id` int(10) NOT NULL,
  `training_group_name` varchar(30) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `course_plan_path` varchar(100) NOT NULL,
  `type_id` int(10) NOT NULL,
  `active_status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `training_group_type`
--

CREATE TABLE `training_group_type` (
  `type_id` int(10) NOT NULL,
  `type_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `training_grp_technology_rel`
--

CREATE TABLE `training_grp_technology_rel` (
  `grp_technology_id` int(10) NOT NULL,
  `technology_id` int(10) NOT NULL,
  `training_group_id` int(11) NOT NULL,
  `assessment_id` int(10) NOT NULL,
  `assessment_minmarks` int(10) NOT NULL,
  `assessment_maxmarks` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(10) NOT NULL,
  `employee_id` varchar(20) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL DEFAULT 'Password',
  `active_status` tinyint(1) NOT NULL DEFAULT 1,
  `creation_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user_batch_rel`
--

CREATE TABLE `user_batch_rel` (
  `user_id` int(10) NOT NULL,
  `batch_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user_evaluation_criteria_rel`
--

CREATE TABLE `user_evaluation_criteria_rel` (
  `criteria_id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL,
  `obtained_marks` int(10) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user_project_rel`
--

CREATE TABLE `user_project_rel` (
  `user_id` int(10) NOT NULL,
  `project_id` int(10) NOT NULL,
  `mentor_id` int(10) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `status_id` int(10) NOT NULL,
  `feedback_comments` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user_role_rel`
--

CREATE TABLE `user_role_rel` (
  `user_id` int(10) NOT NULL,
  `role_id` int(10) NOT NULL,
  `status_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user_training_group_rel`
--

CREATE TABLE `user_training_group_rel` (
  `training_group_id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL,
  `role_id` int(10) NOT NULL,
  `status_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `assessement_candidate_rel`
--
ALTER TABLE `assessement_candidate_rel`
  ADD KEY `assessement_candidate_rel_fk1` (`user_id`),
  ADD KEY `grp_technology_id` (`grp_technology_id`);

--
-- Indexes for table `assessment`
--
ALTER TABLE `assessment`
  ADD PRIMARY KEY (`assessment_id`),
  ADD UNIQUE KEY `assessment_name` (`assessment_name`),
  ADD KEY `assessment_fk0` (`created_by`),
  ADD KEY `assessment_fk1` (`assessment_type_id`);

--
-- Indexes for table `assessment_type`
--
ALTER TABLE `assessment_type`
  ADD PRIMARY KEY (`assessment_type_id`),
  ADD UNIQUE KEY `assessment_type_name` (`assessment_type_name`);

--
-- Indexes for table `batch`
--
ALTER TABLE `batch`
  ADD PRIMARY KEY (`batch_id`),
  ADD UNIQUE KEY `batch_name` (`batch_name`);

--
-- Indexes for table `document`
--
ALTER TABLE `document`
  ADD KEY `document_fk0` (`user_id`),
  ADD KEY `document_fk1` (`verified_by`);

--
-- Indexes for table `evaluation_criteria`
--
ALTER TABLE `evaluation_criteria`
  ADD PRIMARY KEY (`criteria_id`),
  ADD KEY `grp_technology_id` (`grp_technology_id`);

--
-- Indexes for table `feature`
--
ALTER TABLE `feature`
  ADD PRIMARY KEY (`feature_id`),
  ADD UNIQUE KEY `feature_name` (`feature_name`),
  ADD KEY `feature_fk0` (`module_id`);

--
-- Indexes for table `module`
--
ALTER TABLE `module`
  ADD PRIMARY KEY (`module_id`),
  ADD UNIQUE KEY `module_name` (`module_name`);

--
-- Indexes for table `project_allocation`
--
ALTER TABLE `project_allocation`
  ADD PRIMARY KEY (`project_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`),
  ADD UNIQUE KEY `role_name` (`role_name`);

--
-- Indexes for table `role_feature_rel`
--
ALTER TABLE `role_feature_rel`
  ADD KEY `role_feature_rel_fk0` (`role_id`),
  ADD KEY `role_feature_rel_fk1` (`feature_id`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`status_id`),
  ADD UNIQUE KEY `status_name` (`status_name`);

--
-- Indexes for table `technology`
--
ALTER TABLE `technology`
  ADD PRIMARY KEY (`technology_id`);

--
-- Indexes for table `training_group`
--
ALTER TABLE `training_group`
  ADD PRIMARY KEY (`training_group_id`),
  ADD UNIQUE KEY `training_group_name` (`training_group_name`),
  ADD KEY `training_group_fk0` (`type_id`);

--
-- Indexes for table `training_group_type`
--
ALTER TABLE `training_group_type`
  ADD PRIMARY KEY (`type_id`);

--
-- Indexes for table `training_grp_technology_rel`
--
ALTER TABLE `training_grp_technology_rel`
  ADD PRIMARY KEY (`grp_technology_id`),
  ADD KEY `training_grp_technology_rel_fk0` (`technology_id`),
  ADD KEY `training_grp_technology_rel_fk1` (`training_group_id`),
  ADD KEY `assessment_id` (`assessment_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `employee_id` (`employee_id`);

--
-- Indexes for table `user_batch_rel`
--
ALTER TABLE `user_batch_rel`
  ADD KEY `user_batch_rel_fk0` (`user_id`),
  ADD KEY `user_batch_rel_fk1` (`batch_id`);

--
-- Indexes for table `user_evaluation_criteria_rel`
--
ALTER TABLE `user_evaluation_criteria_rel`
  ADD KEY `user_evaluation_criteria_rel_fk0` (`criteria_id`),
  ADD KEY `user_evaluation_criteria_rel_fk1` (`user_id`);

--
-- Indexes for table `user_project_rel`
--
ALTER TABLE `user_project_rel`
  ADD KEY `user_project_rel_fk0` (`user_id`),
  ADD KEY `user_project_rel_fk1` (`project_id`),
  ADD KEY `user_project_rel_fk2` (`mentor_id`),
  ADD KEY `user_project_rel_fk3` (`status_id`);

--
-- Indexes for table `user_role_rel`
--
ALTER TABLE `user_role_rel`
  ADD KEY `user_role_rel_fk0` (`user_id`),
  ADD KEY `user_role_rel_fk1` (`role_id`),
  ADD KEY `user_role_rel_fk2` (`status_id`);

--
-- Indexes for table `user_training_group_rel`
--
ALTER TABLE `user_training_group_rel`
  ADD KEY `user_training_group_rel_fk0` (`training_group_id`),
  ADD KEY `user_training_group_rel_fk1` (`user_id`),
  ADD KEY `user_training_group_rel_fk2` (`role_id`),
  ADD KEY `user_training_group_rel_fk3` (`status_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `assessment`
--
ALTER TABLE `assessment`
  MODIFY `assessment_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `assessment_type`
--
ALTER TABLE `assessment_type`
  MODIFY `assessment_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `batch`
--
ALTER TABLE `batch`
  MODIFY `batch_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `evaluation_criteria`
--
ALTER TABLE `evaluation_criteria`
  MODIFY `criteria_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=131;

--
-- AUTO_INCREMENT for table `feature`
--
ALTER TABLE `feature`
  MODIFY `feature_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT for table `module`
--
ALTER TABLE `module`
  MODIFY `module_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `project_allocation`
--
ALTER TABLE `project_allocation`
  MODIFY `project_id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `status`
--
ALTER TABLE `status`
  MODIFY `status_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `technology`
--
ALTER TABLE `technology`
  MODIFY `technology_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `training_group`
--
ALTER TABLE `training_group`
  MODIFY `training_group_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `training_group_type`
--
ALTER TABLE `training_group_type`
  MODIFY `type_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `training_grp_technology_rel`
--
ALTER TABLE `training_grp_technology_rel`
  MODIFY `grp_technology_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `assessement_candidate_rel`
--
ALTER TABLE `assessement_candidate_rel`
  ADD CONSTRAINT `assessement_candidate_rel_fk1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `assessement_candidate_rel_ibfk_1` FOREIGN KEY (`grp_technology_id`) REFERENCES `training_grp_technology_rel` (`grp_technology_id`);

--
-- Constraints for table `assessment`
--
ALTER TABLE `assessment`
  ADD CONSTRAINT `assessment_fk0` FOREIGN KEY (`created_by`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `assessment_fk1` FOREIGN KEY (`assessment_type_id`) REFERENCES `assessment_type` (`assessment_type_id`);

--
-- Constraints for table `document`
--
ALTER TABLE `document`
  ADD CONSTRAINT `document_fk0` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `document_fk1` FOREIGN KEY (`verified_by`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `evaluation_criteria`
--
ALTER TABLE `evaluation_criteria`
  ADD CONSTRAINT `evaluation_criteria_ibfk_1` FOREIGN KEY (`grp_technology_id`) REFERENCES `training_grp_technology_rel` (`grp_technology_id`);

--
-- Constraints for table `feature`
--
ALTER TABLE `feature`
  ADD CONSTRAINT `feature_fk0` FOREIGN KEY (`module_id`) REFERENCES `module` (`module_id`);

--
-- Constraints for table `role_feature_rel`
--
ALTER TABLE `role_feature_rel`
  ADD CONSTRAINT `role_feature_rel_fk0` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  ADD CONSTRAINT `role_feature_rel_fk1` FOREIGN KEY (`feature_id`) REFERENCES `feature` (`feature_id`);

--
-- Constraints for table `training_group`
--
ALTER TABLE `training_group`
  ADD CONSTRAINT `training_group_fk0` FOREIGN KEY (`type_id`) REFERENCES `training_group_type` (`type_id`);

--
-- Constraints for table `training_grp_technology_rel`
--
ALTER TABLE `training_grp_technology_rel`
  ADD CONSTRAINT `training_grp_technology_rel_fk0` FOREIGN KEY (`technology_id`) REFERENCES `technology` (`technology_id`),
  ADD CONSTRAINT `training_grp_technology_rel_fk1` FOREIGN KEY (`training_group_id`) REFERENCES `training_group` (`training_group_id`),
  ADD CONSTRAINT `training_grp_technology_rel_ibfk_1` FOREIGN KEY (`assessment_id`) REFERENCES `assessment` (`assessment_id`);

--
-- Constraints for table `user_batch_rel`
--
ALTER TABLE `user_batch_rel`
  ADD CONSTRAINT `user_batch_rel_fk0` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `user_batch_rel_fk1` FOREIGN KEY (`batch_id`) REFERENCES `batch` (`batch_id`);

--
-- Constraints for table `user_evaluation_criteria_rel`
--
ALTER TABLE `user_evaluation_criteria_rel`
  ADD CONSTRAINT `user_evaluation_criteria_rel_fk0` FOREIGN KEY (`criteria_id`) REFERENCES `evaluation_criteria` (`criteria_id`),
  ADD CONSTRAINT `user_evaluation_criteria_rel_fk1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `user_project_rel`
--
ALTER TABLE `user_project_rel`
  ADD CONSTRAINT `user_project_rel_fk0` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `user_project_rel_fk1` FOREIGN KEY (`project_id`) REFERENCES `project_allocation` (`project_id`),
  ADD CONSTRAINT `user_project_rel_fk2` FOREIGN KEY (`mentor_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `user_project_rel_fk3` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`);

--
-- Constraints for table `user_role_rel`
--
ALTER TABLE `user_role_rel`
  ADD CONSTRAINT `user_role_rel_fk0` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `user_role_rel_fk1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  ADD CONSTRAINT `user_role_rel_fk2` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`);

--
-- Constraints for table `user_training_group_rel`
--
ALTER TABLE `user_training_group_rel`
  ADD CONSTRAINT `user_training_group_rel_fk0` FOREIGN KEY (`training_group_id`) REFERENCES `training_group` (`training_group_id`),
  ADD CONSTRAINT `user_training_group_rel_fk1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `user_training_group_rel_fk2` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  ADD CONSTRAINT `user_training_group_rel_fk3` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
