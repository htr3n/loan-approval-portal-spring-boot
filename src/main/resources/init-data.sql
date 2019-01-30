-- Demo data
DELETE FROM agency;
DELETE FROM customer;
DELETE FROM role;
DELETE FROM country;
--
-- country
--

INSERT INTO country(code, name) VALUES ('AU', 'Australia');
INSERT INTO country(code, name) VALUES ('DE', 'Germany');
INSERT INTO country(code, name) VALUES ('NZ', 'New Zealand');
INSERT INTO country(code, name) VALUES ('SG', 'Singapore');
INSERT INTO country(code, name) VALUES ('UK', 'United Kingdom');
INSERT INTO country(code, name) VALUES ('US', 'United States');

--
-- example agency
--
INSERT INTO agency(code, name, street, zipcode, city, state, country) VALUES ('VIC001', 'WB South Yarra', '3 Queen St', '3141', 'South Yarra', 'Victoria', 'AU');
INSERT INTO agency(code, name, street, zipcode, city, state, country) VALUES ('VIC002', 'WB Mount Waverley', '86 Huntingdale Rd', '3149', 'Mount Waverley', 'Victoria', 'AU');
INSERT INTO agency(code, name, street, zipcode, city, state, country) VALUES ('VIC003', 'WB Box Hill', '7 Box Hill Rd', '3140', 'Box Hill', 'Victoria', 'AU');

--
-- role
--
-- manager
INSERT INTO role(id, name) VALUES('MGR', 'Manager');
-- supervisor
INSERT INTO role(id, name) VALUES('SPR', 'Supervisor');
-- manager
INSERT INTO role(id, name) VALUES('PPC', 'Post-Processing Clerk');
-- manager
INSERT INTO role(id, name) VALUES('CBR', 'Credit Broker');

-- customer
INSERT INTO customer(username,email,password,firstname,lastname,income,dateofbirth,personalid,lengthofservice,occupation,mobilephone,maritalstatus,title,city,country,state,street,zipcode,numberofchildren)
VALUES ('alice','alice@test.com','abcd1234','Alice','Power','90000','1984-01-01','205026069','10','Secretaty','0421587937','2','Mrs.','Glen Iris','AU','Victoria','123 King St.', '3146', 2);

INSERT INTO customer(username,email,password,firstname,lastname,income,dateofbirth,personalid,lengthofservice,occupation,mobilephone,maritalstatus,title,city,country,state,street,zipcode,numberofchildren)
VALUES ('bob','bob@test.com','abcd1234','Bob','Smith','200000','1970-01-01','205026010','12','CTO','0421587937','2','Mr.','South Yarra','AU','Victoria','9 Mavern Rd.', '3201', 3);

-- staff
INSERT INTO staff(id, email, password, firstname, lastname, role_id) VALUES('clerk', 'clerk@westbank.com.au', 'abcd1234', 'Clerk', 'Jones', 'PPC');
INSERT INTO staff(id, email, password, firstname, lastname, role_id) VALUES('broker', 'broker@westbank.com.au', 'abcd1234', 'Broker', 'Smith', 'CBR');
INSERT INTO staff(id, email, password, firstname, lastname, role_id) VALUES('supervisor', 'supervisor@westbank.com.au', 'abcd1234', 'Supervisor', 'Miller', 'SPR');
INSERT INTO staff(id, email, password, firstname, lastname, role_id) VALUES('manager', 'manager@westbank.com.au', 'abcd1234', 'Manager', 'Brown', 'MGR');

-- staff_role
-- INSERT INTO staff_role(staff_id, role_id) VALUES ('clerk', 'PPC');
-- INSERT INTO staff_role(staff_id, role_id) VALUES ('broker', 'CBR');
-- INSERT INTO staff_role(staff_id, role_id) VALUES ('supervisor', 'SPR');
-- INSERT INTO staff_role(staff_id, role_id) VALUES ('manager', 'MGR');

-- loan file
--INSERT INTO `LOANCONTRACT`(settlementdate, agency) VALUES(CURRENT_DATE(), 'VIC001');

-- loan file
--INSERT INTO `LOANFILE` (loanfileid, borrower, createdby, createddate, estatelocation, estatetype, hascoborrower, interestrate, loanamount, loanterm, personalcapitalcontribution, residencetype, status, totalpurchaseprice, risk, accesssensitivedata, contract)
--VALUES ('abc123', '1', 'broker', '2019-01-14', '123 King Rd', '0', '0.5', '0', '6000000', '10', '400000', '1', '0','1000000', '0', '1', '1');
