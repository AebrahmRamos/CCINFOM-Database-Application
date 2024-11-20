CREATE SCHEMA IF NOT EXISTS [ccinfom-db]
GO

CREATE TABLE [ccinfom-db].[Patient] (
                                                    patient_id INT IDENTITY(1,1) PRIMARY KEY,
                                                    firstName VARCHAR(45) NULL,
                                                    lastName VARCHAR(45) NULL,
                                                    birthDate DATETIME NULL,
                                                    HMO VARCHAR(45) NULL,
                                                    medicalHistory VARCHAR(45) NULL,
                                                    assignedDoctor_id INT NULL
)
GO

CREATE TABLE [ccinfom-db].[Doctor] (
                                                   doctor_id INT IDENTITY(1,1) PRIMARY KEY,
                                                   firstName VARCHAR(45) NULL,
                                                   lastName VARCHAR(45) NULL,
                                                   specialization VARCHAR(45) NULL,
                                                   department VARCHAR(45) NULL
)
GO

CREATE TABLE [ccinfom-db].[Room] (
                                                 room_id INT IDENTITY(1,1) PRIMARY KEY,
                                                 room_type VARCHAR(45) NULL,
                                                 is_available BIT NULL,
                                                 last_maintenance DATETIME NULL
)
GO

CREATE TABLE [ccinfom-db].[Appointment] (
                                                        appointment_id INT IDENTITY(1,1) PRIMARY KEY,
                                                        patient_id INT NULL,
                                                        doctor_id INT NULL,
                                                        room_id INT NULL,
                                                        appointment_time DATETIME NULL,
                                                        status VARCHAR(45) NULL,
                                                        appointment_type VARCHAR(45) NULL,
                                                        priority INT NULL,
                                                        CONSTRAINT FK_Appointment_Patient FOREIGN KEY (patient_id) REFERENCES [ccinfom-db].[Patient](patient_id),
                                                        CONSTRAINT FK_Appointment_Doctor FOREIGN KEY (doctor_id) REFERENCES [ccinfom-db].[Doctor](doctor_id),
                                                        CONSTRAINT FK_Appointment_Room FOREIGN KEY (room_id) REFERENCES [ccinfom-db].[Room](room_id)
)
GO

CREATE TABLE [ccinfom-db].[Admission] (
                                                      admission_id INT IDENTITY(1,1) PRIMARY KEY,
                                                      patient_id INT NULL,
                                                      room_id INT NULL,
                                                      admission_date DATETIME NULL,
                                                      admission_type VARCHAR(45) NULL,
                                                      status VARCHAR(45) NULL,
                                                      CONSTRAINT FK_Admission_Patient FOREIGN KEY (patient_id) REFERENCES [ccinfom-db].[Patient](patient_id),
                                                      CONSTRAINT FK_Admission_Room FOREIGN KEY (room_id) REFERENCES [ccinfom-db].[Room](room_id)
)
GO

CREATE TABLE [ccinfom-db].[Treatment] (
                                                      treatment_id INT IDENTITY(1,1) PRIMARY KEY,
                                                      patient_id INT NULL,
                                                      doctor_id INT NULL,
                                                      room_id INT NULL,
                                                      admission_date DATETIME NULL,
                                                      treatment_type VARCHAR(45) NULL,
                                                      description TEXT NULL, -- Changed to TEXT for longer descriptions
                                                      cost DECIMAL(10,2) NULL, -- Changed to DECIMAL for currency
                                                      CONSTRAINT FK_Treatment_Patient FOREIGN KEY (patient_id) REFERENCES [ccinfom-db].[Patient](patient_id),
                                                      CONSTRAINT FK_Treatment_Doctor FOREIGN KEY (doctor_id) REFERENCES [ccinfom-db].[Doctor](doctor_id)
)
GO

CREATE TABLE [ccinfom-db].[Maintenance] (
                                                        maintenance_id INT IDENTITY(1,1) PRIMARY KEY,
                                                        room_id INT NULL,
                                                        schedule_date DATETIME NULL,
                                                        completion_date DATETIME NULL,
                                                        status VARCHAR(45) NULL,
                                                        notes VARCHAR(45) NULL,
                                                        CONSTRAINT FK_Maintenance_Room FOREIGN KEY (room_id) REFERENCES [ccinfom-db].[Room](room_id)
)
GO

CREATE TABLE [ccinfom-db].[Bill] (
                                                 bill_id INT IDENTITY(1,1) PRIMARY KEY,
                                                 patient_id INT NULL,
                                                 admission_id INT NULL,
                                                 bill_date DATETIME NULL,
                                                 total_amount DECIMAL(10,2) NULL, -- Changed to DECIMAL for currency
                                                 payment_status VARCHAR(45) NULL,
                                                 payment_method VARCHAR(45) NULL,
                                                 CONSTRAINT FK_Bill_Patient FOREIGN KEY (patient_id) REFERENCES [ccinfom-db].[Patient](patient_id)
)
GO