DROP SEQUENCE Recruit_wish_id_seq;

CREATE SEQUENCE Recruit_wish_id_seq
	INCREMENT BY 1
	START WITH 1;

DROP SEQUENCE Studyid_seq;

CREATE SEQUENCE Studyid_seq
	INCREMENT BY 1
	START WITH 1;

DROP SEQUENCE TO_DO_id_seq;

CREATE SEQUENCE TO_DO_id_seq
	INCREMENT BY 1
	START WITH 1;

DROP TABLE TO_DO_LIST CASCADE CONSTRAINTS PURGE;

DROP TABLE Study_wish CASCADE CONSTRAINTS PURGE;

DROP TABLE Study CASCADE CONSTRAINTS PURGE;

DROP TABLE Recruit_wish CASCADE CONSTRAINTS PURGE;

DROP TABLE Member CASCADE CONSTRAINTS PURGE;

CREATE TABLE Member
(
	memberId             VARCHAR2(20) NOT NULL ,
	name                 VARCHAR2(40) NULL ,
	phone                VARCHAR2(20) NULL ,
	email                VARCHAR2(40) NULL ,
	age                  INT NULL ,
	password             VARCHAR2(20) NULL 
);

ALTER TABLE Member
	ADD CONSTRAINT  XPKMember PRIMARY KEY (memberId);

CREATE TABLE TO_DO_LIST
(
	memberId             VARCHAR2(20) NOT NULL ,
	TO_DO                VARCHAR2(50) NULL ,
	TO_DO_id             INT NOT NULL 
);

ALTER TABLE TO_DO_LIST
	ADD CONSTRAINT  XPKTO_DO_LIST PRIMARY KEY (TO_DO_id,memberId);

CREATE TABLE Recruit_wish
(
	company_name         VARCHAR2(40) NULL ,
	recruit_url          VARCHAR2(2100) NULL ,
	company_form         VARCHAR2(10) NULL ,
	title                VARCHAR2(100) NULL ,
	workingType          VARCHAR2(40) NULL ,
	regDate              DATE NULL ,
	deadLine             DATE NULL ,
	memberId             VARCHAR2(20) NOT NULL ,
	recruit_wish_id      INT NOT NULL 
);

ALTER TABLE Recruit_wish
	ADD CONSTRAINT  XPKRecruit_wish PRIMARY KEY (recruit_wish_id,memberId);

CREATE TABLE Study
(
	Study_id             INT NOT NULL ,
	title                VARCHAR2(50) NULL ,
	category             VARCHAR2(15) NULL ,
	location             VARCHAR2(10) NULL ,
	age                  VARCHAR2(10) NULL ,
	companyName          VARCHAR2(50) NULL ,
	maxheadcount         INT NULL ,
	period               INT NULL ,
	reportingdate        DATE NULL ,
	currheadcount        INT NULL ,
	wirter               VARCHAR2(20) NULL 
);

ALTER TABLE Study
	ADD CONSTRAINT  XPKStudy PRIMARY KEY (Study_id);

CREATE TABLE Study_wish
(
	memberId             VARCHAR2(20) NOT NULL ,
	Study_id             INT NOT NULL 
);

ALTER TABLE Study_wish
	ADD CONSTRAINT  XPKStudy_wish PRIMARY KEY (memberId,Study_id);

ALTER TABLE TO_DO_LIST
	ADD (CONSTRAINT R_8 FOREIGN KEY (memberId) REFERENCES Member (memberId));

ALTER TABLE Recruit_wish
	ADD (CONSTRAINT R_38 FOREIGN KEY (memberId) REFERENCES Member (memberId));

ALTER TABLE Study_wish
	ADD (CONSTRAINT R_36 FOREIGN KEY (memberId) REFERENCES Member (memberId));

ALTER TABLE Study_wish
	ADD (CONSTRAINT R_37 FOREIGN KEY (Study_id) REFERENCES Study (Study_id));
