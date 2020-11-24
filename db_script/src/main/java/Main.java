import org.apache.commons.csv.CSVRecord;
import org.postgresql.util.PSQLException;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class Main {
    private static String url = "jdbc:postgresql://127.0.0.1:5432/il311ci";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";
    private static long reportCount = 0;
    private static long authorityCount = 0;
    private static long locationCount = 0;
    private static long typeCount = 0;
    private static long abandonedCount = 0;
    private static long garbageCount = 0;
    private static long graffitiCount = 0;
    private static long potHolesCount = 0;
    private static long rodentCount = 0;
    private static long sanitationCount = 0;
    private static long treesCount = 0;

    public static void main(String[] args) throws SQLException, IOException {
        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASSWORD);
        Connection connectionURI;
        try {
            connectionURI = DriverManager.getConnection(url, props);
        } catch (PSQLException e) {
            String dbURI = "jdbc:postgresql://127.0.0.1:5432/";
            Connection dbConnection = DriverManager.getConnection(dbURI, props);
            createDB(dbConnection);
            dbConnection.close();
            connectionURI = DriverManager.getConnection(url, props);
        }

        System.out.println("Database management systems course.");
        System.out.println("Aristotelis Pozidis");
        System.out.println("AM: CS1200002\n");

        System.out.println("Persisting data to database...");
        persist(connectionURI, "Data\\311-service-requests-abandoned-vehicles.csv");
        persist(connectionURI, "Data\\311-service-requests-garbage-carts.csv");
        persist(connectionURI, "Data\\311-service-requests-graffiti-removal.csv");
        persist(connectionURI, "Data\\311-service-requests-pot-holes-reported.csv");
        persist(connectionURI, "Data\\311-service-requests-rodent-baiting.csv");
        persist(connectionURI, "Data\\311-service-requests-sanitation-code-complaints.csv");
        persist(connectionURI, "Data\\311-service-requests-street-lights-all-out.csv");
        persist(connectionURI, "Data\\311-service-requests-street-lights-one-out.csv");
        persist(connectionURI, "Data\\311-service-requests-tree-debris.csv");
        persist(connectionURI, "Data\\311-service-requests-tree-trims.csv");
        connectionURI.close();
    }

    public static void createDB(Connection connectionURI) throws SQLException {
        Statement statement = connectionURI.createStatement();

        String createDB = "CREATE DATABASE il311ci " +
                " WITH OWNER = \"postgres\" " +
                " TEMPLATE = template0 " +
                " ENCODING = 'UTF8' " +
                " LC_COLLATE = 'C' " +
                " LC_CTYPE = 'C' " +
                " TABLESPACE = 'pg_default' " +
                " CONNECTION LIMIT = -1;";
        statement.executeUpdate(createDB);

        String timezone = "ALTER DATABASE il311ci SET timezone TO 'UTC';";
        statement.executeUpdate(timezone);
    }

    public static void persist(Connection connectionURI, String path) throws IOException, SQLException {
        CsvParser parser = new CsvParser(path);
        List<CSVRecord> records = parser.read();

        String reportInsert = "INSERT INTO public.Report(report_id, completion_date, creation_date, service_request_number, status, authority, location, type) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        String authorityInsert = "INSERT INTO public.Authority(authority_id, census_tracts, community_area, community_areas, historical_wards, police_district, ssa, ward, wards, zip_codes) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String typeInsert = "INSERT INTO public.Type(type_id, service_type) VALUES(?, ?)";
        String locationInsert = "INSERT INTO public.Location(location_id, latitude, location_log_lat, longitude, street_address, x_coordinate, y_coordinate, zip_code) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        String abandonedInsert = "INSERT INTO public.Abandoned_Vehicles(id, current_activity, days_parked, license_plate, most_recent_action, vehicle_color, vehicle_made_model, report) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        String garbageInsert = "INSERT INTO public.Garbage_Carts(id, current_activity, number_of_blank_carts, most_recent_action, report) VALUES(?, ?, ?, ?, ?)";
        String graffitiInsert = "INSERT INTO public.Graffiti_Removal(id, graffiti_location, type_of_surface, report) VALUES(?, ?, ?, ?)";
        String potHolesInsert = "INSERT INTO public.Pot_Holes(id, current_activity, most_recent_action, num_of_pot_holes_filled_on_block, report) VALUES(?, ?, ?, ?, ?)";
        String rodentInsert = "INSERT INTO public.Rodent_Baiting(id, current_activity, most_recent_action, num_of_premises_baited, num_of_premises_with_garbage, num_of_premises_with_rats, report) VALUES(?, ?, ?, ?, ?, ?, ?)";
        String sanitationInsert = "INSERT INTO public.Sanitation_Code(id, nature_of_code_violation, report) VALUES(?, ?, ?)";
        String treesInsert = "INSERT INTO public.Trees(id, current_activity, trees_location, most_recent_action, report) VALUES(?, ?, ?, ?, ?)";

        PreparedStatement reportStatement = connectionURI.prepareStatement(reportInsert);
        PreparedStatement authorityStatement = connectionURI.prepareStatement(authorityInsert);
        PreparedStatement typeStatement = connectionURI.prepareStatement(typeInsert);
        PreparedStatement locationStatement = connectionURI.prepareStatement(locationInsert);
        PreparedStatement abandonedStatement = connectionURI.prepareStatement(abandonedInsert);
        PreparedStatement garbageStatement = connectionURI.prepareStatement(garbageInsert);
        PreparedStatement graffitiStatement = connectionURI.prepareStatement(graffitiInsert);
        PreparedStatement potHolesStatement = connectionURI.prepareStatement(potHolesInsert);
        PreparedStatement rodentStatement = connectionURI.prepareStatement(rodentInsert);
        PreparedStatement sanitationStatement = connectionURI.prepareStatement(sanitationInsert);
        PreparedStatement treeStatement = connectionURI.prepareStatement(treesInsert);

        typeStatement.setLong(1, ++typeCount);
        String type = records.get(0).get("Type Of Service Request");
        typeStatement.setString(2, type);
        typeStatement.addBatch();

        long start = System.currentTimeMillis();
        for (CSVRecord record : records) {
            try {
                reportStatement.setLong(1, ++reportCount);
                reportStatement.setString(2, record.get("Completion Date"));
                reportStatement.setString(3, record.get("Creation Date"));
                reportStatement.setString(4, record.get("Service Request Number"));
                reportStatement.setString(5, record.get("Status"));
                authorityStatement.setLong(1, ++authorityCount);
                if (!type.equals("Street Light Out")) {
                    authorityStatement.setInt(2, record.get("Census Tracts").isEmpty() ? 0 : Integer.parseInt(record.get("Census Tracts")));
                    authorityStatement.setInt(4, record.get("Community Areas").isEmpty() ? 0 : Integer.parseInt(record.get("Community Areas")));
                    authorityStatement.setInt(5, record.get("Historical Wards 2003-2015").isEmpty() ? 0 : Integer.parseInt(record.get("Historical Wards 2003-2015")));
                    authorityStatement.setInt(9, record.get("Wards").isEmpty() ? 0 : Integer.parseInt(record.get("Wards")));
                    authorityStatement.setInt(10, record.get("Zip Codes").isEmpty() ? 0 : Integer.parseInt(record.get("Zip Codes")));
                } else {
                    authorityStatement.setInt(2, 0);
                    authorityStatement.setInt(4, 0);
                    authorityStatement.setInt(5, 0);
                    authorityStatement.setInt(9, 0);
                    authorityStatement.setInt(10, 0);
                }
                authorityStatement.setInt(3, record.get("Community Area").isEmpty() ? 0 : Integer.parseInt(record.get("Community Area")));
                authorityStatement.setInt(6, record.get("Police District").isEmpty() ? 0 : Integer.parseInt(record.get("Police District")));
                if (type.equals("Abandoned Vehicle Complaint") || type.equals("Garbage Cart Black Maintenance/Replacement") || type.equals("Graffiti Removal")) {
                    authorityStatement.setInt(7, record.get("SSA").isEmpty() ? 0 : Integer.parseInt(record.get("SSA")));
                } else {
                    authorityStatement.setInt(7, 0);
                }
                authorityStatement.setInt(8, record.get("Ward").isEmpty() ? 0 : Integer.parseInt(record.get("Ward")));
                reportStatement.setLong(6, authorityCount);
                locationStatement.setLong(1, ++locationCount);
                locationStatement.setDouble(2, record.get("Latitude").isEmpty() ? 0.0 : Double.parseDouble(record.get("Latitude")));
                locationStatement.setString(3, record.get("Location"));
                locationStatement.setDouble(4, record.get("Longitude").isEmpty() ? 0.0 : Double.parseDouble(record.get("Longitude")));
                locationStatement.setString(5, record.get("Street Address"));
                locationStatement.setDouble(6, record.get("X Coordinate").isEmpty() ? 0.0 : Double.parseDouble(record.get("X Coordinate")));
                locationStatement.setDouble(7, record.get("Y Coordinate").isEmpty() ? 0.0 : Double.parseDouble(record.get("Y Coordinate")));
                try {
                    locationStatement.setString(8, record.get("Zip Code"));
                } catch (IllegalArgumentException e) {
                    locationStatement.setString(8, record.get("Zip"));
                }
                reportStatement.setLong(7, locationCount);
                reportStatement.setLong(8, typeCount);

                switch (type) {
                    case "Abandoned Vehicle Complaint":
                        abandonedStatement.setLong(1, ++abandonedCount);
                        abandonedStatement.setString(2, record.get("Current Activity"));
                        abandonedStatement.setDouble(3, record.get("How Many Days Has the Vehicle Been Reported as Parked?").isEmpty() ? 0 : Double.parseDouble(record.get("How Many Days Has the Vehicle Been Reported as Parked?")));
                        abandonedStatement.setString(4, record.get("License Plate"));
                        abandonedStatement.setString(5, record.get("Most Recent Action"));
                        abandonedStatement.setString(6, record.get("Vehicle Color"));
                        abandonedStatement.setString(7, record.get("Vehicle Make/Model"));
                        abandonedStatement.setLong(8, reportCount);
                        break;
                    case "Garbage Cart Black Maintenance/Replacement":
                        garbageStatement.setLong(1, ++garbageCount);
                        garbageStatement.setString(2, record.get("Current Activity"));
                        garbageStatement.setDouble(3, record.get("Number of Black Carts Delivered").isEmpty() ? 0 : Double.parseDouble(record.get("Number of Black Carts Delivered")));
                        garbageStatement.setString(4, record.get("Most Recent Action"));
                        garbageStatement.setLong(5, reportCount);
                        break;
                    case "Graffiti Removal":
                        graffitiStatement.setLong(1, ++graffitiCount);
                        graffitiStatement.setString(2, record.get("What Type of Surface is the Graffiti on?"));
                        graffitiStatement.setString(3, record.get("Where is the Graffiti located?"));
                        graffitiStatement.setLong(4, reportCount);
                        break;
                    case "Pothole in Street":
                        potHolesStatement.setLong(1, ++potHolesCount);
                        potHolesStatement.setString(2, record.get("Current Activity"));
                        potHolesStatement.setString(3, record.get("Most Recent Action"));
                        potHolesStatement.setDouble(4, record.get("NUMBER OF POTHOLES FILLED ON BLOCK").isEmpty() ? 0 : Double.parseDouble(record.get("NUMBER OF POTHOLES FILLED ON BLOCK")));
                        potHolesStatement.setLong(5, reportCount);
                        break;
                    case "Rodent Baiting/Rat Complaint":
                        rodentStatement.setLong(1, ++rodentCount);
                        rodentStatement.setString(2, record.get("Current Activity"));
                        rodentStatement.setString(3, record.get("Most Recent Action"));
                        rodentStatement.setDouble(4, record.get("Number of Premises Baited").isEmpty() ? 0 : Double.parseDouble(record.get("Number of Premises Baited")));
                        rodentStatement.setDouble(5, record.get("Number of Premises with Garbage").isEmpty() ? 0 : Double.parseDouble(record.get("Number of Premises with Garbage")));
                        rodentStatement.setDouble(6, record.get("Number of Premises with Rats").isEmpty() ? 0 : Double.parseDouble(record.get("Number of Premises with Rats")));
                        rodentStatement.setLong(7, reportCount);
                        break;
                    case "Sanitation Code Violation":
                        sanitationStatement.setLong(1, ++sanitationCount);
                        sanitationStatement.setString(2, record.get("What is the Nature of this Code Violation?"));
                        sanitationStatement.setLong(3, reportCount);
                        break;
                    case "Tree Debris":
                    case "Tree Trim":
                        treeStatement.setLong(1, ++treesCount);
                        try {
                            treeStatement.setString(2, record.get("Current Activity"));
                            treeStatement.setString(3, record.get("If Yes, where is the debris located?"));
                            treeStatement.setString(4, record.get("Most Recent Action"));
                        } catch (IllegalArgumentException e) {
                            treeStatement.setString(2, "");
                            treeStatement.setString(3, record.get("Location of Trees"));
                            treeStatement.setString(4, "");
                        }
                        treeStatement.setLong(5, reportCount);
                        break;
                }

                authorityStatement.addBatch();
                locationStatement.addBatch();
                reportStatement.addBatch();

                switch (type) {
                    case "Abandoned Vehicle Complaint":
                        abandonedStatement.addBatch();
                        break;
                    case "Garbage Cart Black Maintenance/Replacement":
                        garbageStatement.addBatch();
                        break;
                    case "Graffiti Removal":
                        graffitiStatement.addBatch();
                        break;
                    case "Pothole in Street":
                        potHolesStatement.addBatch();
                        break;
                    case "Rodent Baiting/Rat Complaint":
                        rodentStatement.addBatch();
                        break;
                    case "Sanitation Code Violation":
                        sanitationStatement.addBatch();
                        break;
                    case "Tree Debris":
                    case "Tree Trim":
                        treeStatement.addBatch();
                        break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        authorityStatement.executeBatch();
        locationStatement.executeBatch();
        typeStatement.executeBatch();
        reportStatement.executeBatch();

        abandonedStatement.executeBatch();
        garbageStatement.executeBatch();
        graffitiStatement.executeBatch();
        potHolesStatement.executeBatch();
        rodentStatement.executeBatch();
        sanitationStatement.executeBatch();
        treeStatement.executeBatch();
        System.out.println("Duration of " + path + ": " + ((System.currentTimeMillis() - start) / 1000) + "s");
    }
}