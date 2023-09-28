
-- DATA SEED

-- USER (lösen = Password!123)'
-- ADMIN (lösen = 1234) --

insert into users(id, username, password) values
  (1001L, 'Bobo', '$2a$10$S1rbsrCMAj738jKMpRXfYu9A5uvzgG0dejWA.jLqDar3qMqmcwyTO'),
                                              (1002L, 'admin','$2a$10$zmIYgxZNGf9FIsLCXL7gQOJBchmFU4SYVe81gPMOmb3/uE19hbLTK'),
                                              (1003L, 'apa', '$2a$04$ifqJKvCb31AXYt/Q8prVre2BxoBClBlqjNRZ4W1FC8J1NO3aV.kZC'),
                                              (1004L, 'apa2', '$2a$15$92rBTf0TFiMwfeCbmS23WOxnZe8teu7l5k4FjHMBX2mFVgYaD.Mb6');