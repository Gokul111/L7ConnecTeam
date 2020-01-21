-- Changing table name  --
-- mysql -u root -p <'DBNAME'test_ra> < D:/tableNameChange.sql --force

-- assessment_candidate_rel --
ALTER TABLE assessement_candidate_rel
RENAME TO user_assessment_rel;


-- training_grp_technology_rel --
ALTER TABLE training_grp_technology_rel
RENAME TO training_grp_tech_assessment_rel;


-- Changing Field name  --


-- training_grp_technology_rel --
ALTER TABLE training_grp_tech_assessment_rel CHANGE COLUMN grp_technology_id  grp_tech_assessment_id int(10) AUTO_INCREMENT;
-- assessment_candidate_rel --
ALTER TABLE user_assessment_rel CHANGE COLUMN grp_technology_id  grp_tech_assessment_id int(10);



-- Add field name  --


-- training_grp_technology_rel --

-- creation Date --
  ALTER TABLE training_grp_tech_assessment_rel
  ADD COLUMN creation_date DATETIME NOT NULL DEFAULT NOW();

-- created by --
 ALTER TABLE training_grp_tech_assessment_rel
  ADD COLUMN created_by int(10)  NOT NULL;
ALTER TABLE training_grp_tech_assessment_rel ADD CONSTRAINT  FOREIGN KEY (created_by) REFERENCES user(user_id);

-- max attempts --
 ALTER TABLE training_grp_tech_assessment_rel
  ADD COLUMN max_attempts int(3)  NOT NULL;




-- user_assessment_rel --
-- creation Date --
  ALTER TABLE user_assessment_rel
  ADD COLUMN creation_date DATETIME NOT NULL DEFAULT NOW();

-- created by --
 ALTER TABLE user_assessment_rel
  ADD COLUMN created_by int(10)  NOT NULL;
ALTER TABLE training_grp_tech_assessment_rel ADD CONSTRAINT  FOREIGN KEY (created_by) REFERENCES user(user_id);


-- attempt --
 ALTER TABLE user_assessment_rel
  ADD COLUMN attempt int(3)  NOT NULL;

-- assessment Date --
  ALTER TABLE user_assessment_rel
  ADD COLUMN assessment_date DATETIME NOT NULL;


-- Add composite name  --

-- user_assessment_rel --

ALTER TABLE user_assessment_rel  ADD PRIMARY KEY( grp_tech_assessment_id , user_id, attempt);
