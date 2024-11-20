CREATE DATABASE hospital_management_database;
USE hospital_management_database;

-- Create the Patient table
CREATE TABLE Patient (
    patientId INT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    birthDate DATE,
    HMO VARCHAR(255),
    medicalHistory TEXT
);

-- Create the Doctor table
CREATE TABLE Doctor (
    doctorId INT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    specialization VARCHAR(255),
    department VARCHAR(255)
);

-- Create the Room table
CREATE TABLE Room (
    roomId INT PRIMARY KEY AUTO_INCREMENT,
    roomType VARCHAR(255),
    isAvailable BOOLEAN,
    lastMaintenance DATE
);

-- Create the Admission table
CREATE TABLE Admission (
    admissionId INT PRIMARY KEY AUTO_INCREMENT,
    patientId INT,
    roomId INT,
    doctorId INT,
    admissionDate DATETIME,
    admissionType VARCHAR(255),  -- e.g., In-patient, Out-patient, ER
    status VARCHAR(255),
    FOREIGN KEY (patientId) REFERENCES Patient(patientId),
    FOREIGN KEY (roomId) REFERENCES Room(roomId),
    FOREIGN KEY (doctorId) REFERENCES Doctor(doctorId) 
);
-- Create the Bill table
CREATE TABLE Bill (
    billId INT PRIMARY KEY AUTO_INCREMENT,
    admissionId INT,
    patientId INT,
    billDate DATETIME,
    totalAmount DECIMAL(10, 2),
    paymentStatus VARCHAR(255),
    paymentMethod VARCHAR(255),
    FOREIGN KEY (admissionId) REFERENCES Admission(admissionId),
    FOREIGN KEY (patientId) REFERENCES Patient(patientId)
);

-- Create the Treatment table
CREATE TABLE Treatment (
    treatmentId INT PRIMARY KEY AUTO_INCREMENT,
    patientId INT,
    doctorId INT,
    roomId INT,
    admissionDate DATETIME,  -- Might be redundant, consider linking to Admission
    treatmentType VARCHAR(255),
    description TEXT,
    cost DECIMAL(10, 2),
    FOREIGN KEY (patientId) REFERENCES Patient(patientId),
    FOREIGN KEY (doctorId) REFERENCES Doctor(doctorId),
    FOREIGN KEY (roomId) REFERENCES Room(roomId)
);

-- Create the Appointment table
CREATE TABLE Appointment (
    appointmentId INT PRIMARY KEY AUTO_INCREMENT,
    patientId INT,
    doctorId INT,
    roomId INT,
    appointmentTime DATETIME,
    status VARCHAR(255),
    appointmentType VARCHAR(255),
    priority VARCHAR(255),
    FOREIGN KEY (patientId) REFERENCES Patient(patientId),
    FOREIGN KEY (doctorId) REFERENCES Doctor(doctorId),
    FOREIGN KEY (roomId) REFERENCES Room(roomId)
);

-- Create the Maintenance table
CREATE TABLE Maintenance (
    maintenanceId INT PRIMARY KEY AUTO_INCREMENT,
    roomId INT,
    scheduleDate DATETIME,
    completionDate DATETIME,
    status VARCHAR(255),
    notes TEXT,
    FOREIGN KEY (roomId) REFERENCES Room(roomId)
);

-- Create the Laboratory table
CREATE TABLE Laboratory (
    laboratoryId INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    address VARCHAR(255),
    contactNumber VARCHAR(20)
);

-- Create the LabRequest table
CREATE TABLE LabRequest (
    requestId INT PRIMARY KEY AUTO_INCREMENT,
    patientId INT,
    doctorId INT,
    laboratoryId INT,
    requestDate DATETIME,
    FOREIGN KEY (patientId) REFERENCES Patient(patientId),
    FOREIGN KEY (doctorId) REFERENCES Doctor(doctorId),
    FOREIGN KEY (laboratoryId) REFERENCES Laboratory(laboratoryId)
);

-- Create the Disease table
CREATE TABLE Disease (
    diseaseId INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    category VARCHAR(255),
    icdCode VARCHAR(255),
    description TEXT
);

-- Create the Diagnosis table
CREATE TABLE Diagnosis (
    diagnosisId INT PRIMARY KEY AUTO_INCREMENT,
    diseaseId INT,
    admissionId INT,
    admissionType VARCHAR(255), -- To specify if it's ER, In-patient, etc.
    diagnosisDate DATETIME,
    severity VARCHAR(255),
    status VARCHAR(255),
    FOREIGN KEY (diseaseId) REFERENCES Disease(diseaseId)
    -- No direct FK to Admission due to multiple admission types, handle in application logic
);

-- Create the LabStaff table
CREATE TABLE LabStaff (
    staffId INT PRIMARY KEY AUTO_INCREMENT,
    laboratoryId INT,
    name VARCHAR(255),
    role VARCHAR(255),
    shift VARCHAR(255),
    FOREIGN KEY (laboratoryId) REFERENCES Laboratory(laboratoryId)
);

-- Create the LabActivity table
CREATE TABLE LabActivity (
    activityId INT PRIMARY KEY AUTO_INCREMENT,
    labRequestId INT,
    labStaffId INT,
    startTime DATETIME,
    endTime DATETIME,
    activityType VARCHAR(255),
    complexity INT,
    status VARCHAR(255),
    FOREIGN KEY (labRequestId) REFERENCES LabRequest(requestId),
    FOREIGN KEY (labStaffId) REFERENCES LabStaff(staffId)
);
