Tables

Patient,
Room,
Treatment,
Admission,
Appointment,
Bill,
Diagnosis,
Disease,
Doctor,
LabActivity,
Laboratory,
LabRequest,
LabStaff,
Maintenance


# AdmissionService
Handles both ER Out-Patient and In-Patient admissions.
Responsibilities:
- Validating patient information.
- Checking room availability (for in-patients).
- Assigning doctors (potentially based on specialization or availability).
- Creating the admission record.
- Updating patient status.
- Potentially generating an initial bill.

# TreatmentService
Manages treatment for both out-patients and in-patients.
Responsibilities:
- Scheduling treatments.
- Recording treatment details and outcomes.
- Updating patient records.
- Potentially updating billing information.


LabRequestService
Handles requests and performance of lab procedures.
Responsibilities:
- Creating lab requests.
- Assigning requests to labs and technicians.
- Tracking the status of requests.
- Recording lab results.


DischargeService
Manages patient discharge for all admission types.
Updating patient status.
Generating final bills.
Handling discharge logistics (e.g., freeing up rooms).