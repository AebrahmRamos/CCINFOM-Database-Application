import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DiseaseActivityReport {
	private DiseaseDAO diseaseDAO;
	
	public DiseaseActivityReport(){
		diseaseDAO = new DiseaseDAO();
	}
	public void generateReport(int month, int year) {
		try { 
			List<Disease> diseases = diseaseDAO.getAllDiseases(); 
			System.out.println("Disease Activity Report"); 
			System.out.println("Month: " + month); System.out.println("Year: " + year); 
			System.out.println(); System.out.println("Disease | Total Cases"); 
			System.out.println("---------------------------"); 
			for (Disease disease : diseases) { 
				int totalCases = 0; 
				try (Connection connection = HospitalManagementDB.getConnection(); 
						PreparedStatement statement = connection.prepareStatement( 
								"SELECT COUNT(*) FROM Diagnosis " + "WHERE diseaseID = ? AND MONTH(diagnosisDate) = ? AND YEAR(diagnosisDate) = ?")) { 
					
					statement.setInt(1, disease.getDiseaseID()); 
					statement.setInt(2, month); 
					statement.setInt(3, year); 
					try (ResultSet resultSet = statement.executeQuery()) { 
						if (resultSet.next()) { 
							totalCases = resultSet.getInt(1);
							} 
						} 
					} catch (SQLException e) { 
						System.err.println("Error retrieving diagnosis data: " + e.getMessage()); 
						} 
				System.out.printf("%-12s | %-10d%n", disease.getName(), totalCases);
				} 
			} catch (Exception e) { 
				System.err.println("Error generating report: " + e.getMessage()); 
				} 
		} 
	}