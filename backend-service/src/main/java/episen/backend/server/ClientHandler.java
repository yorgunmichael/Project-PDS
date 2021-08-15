package episen.backend.server;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private final Connection connection;
    private static String[] requestList = new String[10];
    private final static Logger log = LoggerFactory.getLogger(ClientHandler.class.getName());


    // Constructor
    public ClientHandler(Socket socket, Connection connection) {
        this.clientSocket = socket;
        this.connection = connection;


    }

    public void run() {


        ObjectMapper mapper = new ObjectMapper(new JsonFactory());

        try {
            OutputStream out = clientSocket.getOutputStream();
            InputStream in = clientSocket.getInputStream();
            DataOutputStream ds = new DataOutputStream(out);
            DataInputStream di = new DataInputStream(in);

            String request = di.readUTF();
            System.out.println(request);


            Map<String, String> map = mapper.readValue(request.split("@")[1], new TypeReference<Map<String, String>>() {
            });
            if (request.split("@")[0].equals("requestBuilding")) {
                ds.writeUTF(requestbuilding(connection, map).toString());
            }

            if (request.split("@")[0].equals("requestFloor")) {
                ds.writeUTF(requestFloor(connection, map).toString());
            }

            if (request.split("@")[0].equals("requestRoom")) {
                ds.writeUTF(requestRoom(connection, map).toString());
            }

            if (request.split("@")[0].equals("requestCompany")) {
                ds.writeUTF(requestCompany(connection, map).toString());
            }

            if (request.split("@")[0].equals("request_id_building")) {
                ds.writeUTF(requestgetBuilding(connection, map).toString());
            }

            if (request.split("@")[0].equals("request_id_floor")) {
                ds.writeUTF(requestgetFloor(connection, map).toString());
            }

            if (request.split("@")[0].equals("requestgetListBuilding")) {
                ds.writeUTF(requestgetListBuilding(connection, map).toString());
            }

            if (request.split("@")[0].equals("requestGetIdRoom")) {
                ds.writeUTF(requestGetIdRoom(connection, map).toString());
            }

            if (request.split("@")[0].equals("requestUpdate")) {
                ds.writeUTF(requestUpdate(connection, map).toString());
            }
            if (request.split("@")[0].equals("requestScreenIsEmpty")) {
                ds.writeUTF(requestScreenIsEmpty(connection, map).toString());
            }

            if (request.split("@")[0].equals("requestUpdatePrise")) {
                ds.writeUTF(requestUpdatePrise(connection, map).toString());
            }

            if (request.split("@")[0].equals("requestPriseIsEmpty")) {
                ds.writeUTF(requestPriseIsEmpty(connection, map).toString());
            }

            if (request.split("@")[0].equals("requestUpdateSensor")) {
                ds.writeUTF(requestUpdateSensor(connection, map).toString());
            }

            if (request.split("@")[0].equals("requestSensorIsEmpty")) {
                ds.writeUTF(requestSensorIsEmpty(connection, map).toString());
            }
            if (request.split("@")[0].equals("comboxCompany")){
                ds.writeUTF(comboxNameCompany(connection, map).toString());
            }
            if (request.split("@")[0].equals("requestUpdateWindows")) {
                ds.writeUTF(requestUpdateWindows(connection, map).toString());
            }

            if (request.split("@")[0].equals("requestWindowsIsEmpty")) {
                ds.writeUTF(requestWindowsIsEmpty(connection, map).toString());
            }

            if (request.split("@")[0].equals("requestGetEquipment")) {
                ds.writeUTF(requestGetEquipment(connection, map).toString());
            }

            if (request.split("@")[0].equals("requestUpdateEquipment")) {
                ds.writeUTF(requestUpdateEquipment(connection, map).toString());
            }

            if (request.split("@")[0].equals("requestGetLocalisation")) {
                ds.writeUTF(requestGetLocalisation(connection, map).toString());
            }




/******************************************starting condition for indicators***********************/

            /*****************his the start of general information of the indicators***************/
            if (request.split("@")[0].equals("rateOccupation")) {
                ds.writeUTF(rateOccupation(connection, map).toString());
            }
            if (request.split("@")[0].equals("connectedObject")) {
                ds.writeUTF(getConnectedObjets(connection, map).toString());
            }
            if (request.split("@")[0].equals("allEquipment")) {
                ds.writeUTF(getAllEquipments(connection, map).toString());
            }
            if (request.split("@")[0].equals("allSensor")) {
                ds.writeUTF(getAllSensor(connection, map).toString());
            }
            if (request.split("@")[0].equals("allCompany")) {
                ds.writeUTF(getAllCompany(connection, map).toString());
            }
            if (request.split("@")[0].equals("energyConsommation")) {
                ds.writeUTF(getEnergy(connection, map).toString());
            }
            /***************this the end of general information for indicators***************/

            /*****************************staring for company indicators***********************************/

            if (request.split("@")[0].equals("CompanyConnectedObject")) {
                ds.writeUTF(objectCompany(connection, map).toString());
            }
            if (request.split("@")[0].equals("AllEquipmentCompany")) {
                ds.writeUTF(equipmentCompany(connection, map).toString());
            }
            if (request.split("@")[0].equals("allSensorCompany")) {
                ds.writeUTF(sensorCompany(connection, map).toString());
            }
            if (request.split("@")[0].equals("usedBatiment")) {
                ds.writeUTF(usedBatiment(connection, map).toString());
            }
/************************* ending company indicators ****************************/
/***********starting for building conditions indicators***************************/

            if (request.split("@")[0].equals("rateBuilding")) {
                ds.writeUTF(rateBuilding(connection, map).toString());
            }
            if (request.split("@")[0].equals("objectBuilding")) {
                ds.writeUTF(objectBuilding(connection, map).toString());
            }
            if (request.split("@")[0].equals("equipmentBuilding")) {
                ds.writeUTF(equipmentBuilding(connection, map).toString());
            }
            if (request.split("@")[0].equals("sensorBuilding")) {
                ds.writeUTF(sensorBuilding(connection, map).toString());
            }
            if (request.split("@")[0].equals("companyBuilding")) {
                ds.writeUTF(companyInBuilding(connection, map).toString());
            }
            if (request.split("@")[0].equals("energyBuilding")) {
                ds.writeUTF(energyBuilding(connection, map).toString());
            }
            if (request.split("@")[0].equals("allFloor")) {
                ds.writeUTF(getAllfloor(connection, map).toString());
            }
            /*******************ending building indicators****************************/

/*******************ending conditions for all indicators**********************************/

            // LOCATION


            if (request.split("@")[0].equals("initPlanLocation")) {
                ds.writeUTF(initPlanLocation(connection, map).toString());
            }
            if (request.split("@")[0].equals("getPlace")) {
                ds.writeUTF(getPlace(connection, map).toString());
            }
            if (request.split("@")[0].equals("getListBuilding")) {
                ds.writeUTF(getListBuilding(connection, map).toString());
            }
            if (request.split("@")[0].equals("getListFloor")) {
                ds.writeUTF(getListFloor(connection, map).toString());
            }
            if (request.split("@")[0].equals("getNameBuilding")) {
                ds.writeUTF(getNameBuilding(connection, map).toString());
            }
            if (request.split("@")[0].equals("getNbFloorFree")) {
                ds.writeUTF(getNbFloorFree(connection, map).toString());
            }
            if (request.split("@")[0].equals("getFloorStatu")) {
                ds.writeUTF(getFloorStatu(connection, map).toString());
            }
            if (request.split("@")[0].equals("setFloorStatu")) {
                ds.writeUTF(setFloorStatu(connection, map).toString());
            }
            if (request.split("@")[0].equals("getDispoBat")) {
                ds.writeUTF(getDispoBat(connection, map).toString());
            }
            if (request.split("@")[0].equals("setStatuResa")) {
                ds.writeUTF(setStatuResa(connection, map).toString());
            }
            if (request.split("@")[0].equals("insertCompany")) {
                ds.writeUTF(insertCompany(connection, map).toString());
            }




        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*************** starting method request for building indicators*********************/

    private String getAllfloor(Connection connection, Map<String, String> map) {
        String value = "";
        try {
            String sql ="select count(name_floor) from floor " +
                    "inner join building on floor.id_building = building.id_building " +
                    "where building_name='"+ map.get("building_name") + "'";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next())
                value = rs.getString(1);
        }catch (SQLException e) {
            log.error("la requête est nulle");
        }catch (NullPointerException e){
            log.error("la valeur est null");
        }
        return value;
    }

    private String equipmentBuilding(Connection connection, Map<String, String> map) {
        String value ="";
        try {
            String sql ="select count(position_windows) from room inner join floor " +
                    "on floor.id_floor = room.id_floor inner join building on " +
                    "building.id_building = floor.id_building where " +
                    "position_windows = true and building_name = '"+ map.get("building_name") + "'";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next())
                value = rs.getString(1);


        }catch (SQLException e) {
            log.error("la requête est nulle");
        }catch (NullPointerException e){
            log.error("la valeur est null");
        }
        return value;
    }

    private Integer objectBuilding(Connection connection, Map<String, String> map) {
        int val1 = 0;
        try {
            String sql ="select sum(n) from (select count(name_room) n from room inner join floor " +
                    "on floor.id_floor = room.id_floor inner join building on " +
                    "building.id_building = floor.id_building where " +
                    "position_plug = true and building_name = '"+map.get("building_name")+"' " +
                    "union " +
                    "select count(name_room) from room inner join floor " +
                    "on floor.id_floor = room.id_floor inner join building on " +
                    "building.id_building = floor.id_building where " +
                    "position_screen = true and building_name = '"+map.get("building_name")+"' ) as res";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next())
                val1 = rs.getInt(1);
            log.info("je suis val1 "+val1);
        }catch (SQLException e) {
            log.error("la requête est nulle");
        }catch (NullPointerException e){
            log.error("la valeur est null");
        }
        return val1;
    }

    private String rateBuilding(Connection connection, Map<String, String> map) {
        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumFractionDigits(2);
        double d = 0;
        double d2 =0.0;
        String value ="";

        try {
            String sql = "select  count(name_room) from room inner join floor " +
                    "on room.id_floor = floor.id_floor inner join building " +
                    "on floor.id_building = building.id_building " +
                    "where status = 'booked' and building_name = '"+map.get("building_name")+"'";

            String sql2 ="select count(name_room) from room inner join floor " +
                    "on room.id_floor = floor.id_floor inner join building " +
                    "on floor.id_building = building.id_building " +
                    "where building_name = '"+map.get("building_name")+"'";

            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next())
                d = rs.getDouble(1);
            log.info("je suis d "+d);
            ResultSet rs2 = connection.createStatement().executeQuery(sql2);
            while (rs2.next())
                d2 = rs2.getDouble(1);
            log.info("je suis d2 "+ d2);
            value = format.format((d/d2) * 100);
            log.info("je suis val "+value);

        }catch (SQLException e){
            log.error("la requête est nulle");
        }catch (NullPointerException e){
            log.error("la valeur est null");
        }
        return value+"%";
    }

    private String energyBuilding(Connection connection, Map<String, String> map) {
        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumFractionDigits(2);
        String value = "";
        double resp = 0.0;
        try{
            String sql = "select energy from building where " +
                    "building_name = '"+ map.get("building_name") + "'";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next())
                resp = rs.getDouble(1);
            value = format.format(resp);
        }catch (SQLException e){
            log.error("la requête est nulle");
        }catch (NullPointerException e){
            log.error("la valeur est null");
        }
        return value;
    }

    private String sensorBuilding(Connection connection, Map<String, String> map) {
        String value = "";
        try {

            String sql = "select count(position_sensor) from room inner join floor " +
                    "on floor.id_floor = room.id_floor inner join building on " +
                    "building.id_building = floor.id_building where " +
                    "position_sensor = true and building_name = '"+ map.get("building_name") + "'";

            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next())
                value = rs.getString(1);
        }catch (SQLException e){
            log.error("la requête est nulle");
        }catch (NullPointerException e){
            log.error("la valeur est null");
        }
        return value;
    }

    private Integer companyInBuilding(Connection connection, Map<String, String> map) {
        int value = 0;
        try {

            String sql = "select count(distinct company_name) from company inner join location " +
                    "on company.company_id = location.id_location inner join room " +
                    "on room.id_location = location.id_location inner join floor " +
                    "on floor.id_floor = room.id_floor inner join building " +
                    "on building.id_building = floor.id_building " +
                    "where building_name = '"+ map.get("building_name") +"'";

            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next())
                value = rs.getInt(1);
        }catch (SQLException e){
            log.error("la requête est nulle");
        }
        return value;
    }
/********************** end of method for building indicators******************/
    /************************starting request methods for company indicators*****************************/


    private String usedBatiment(Connection connection, Map<String, String> map) {
        String value = "";
        try{
            String sql = "select distinct building_name from building inner join floor " +
                    "on building.id_building = floor.id_building inner join room " +
                    "on floor.id_floor = room.id_floor inner join location on " +
                    "room.id_location = location.id_location inner join company on " +
                    "company.company_id = location.id_location " +
                    "where company_name = '" + map.get("company_name") + "'";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next())
                value = rs.getString(1);
            log.info(value);
        }catch (SQLException e){
            log.error("la requête est nulle");
        }catch (NullPointerException e){
            log.error("la valeur est null");
        }
        return value;

    }
    private Integer equipmentCompany(Connection connection, Map<String, String> map) {
        int value = 0;
        try{
            String sql = "select count(position_windows) from room inner join " +
                    "location on room.id_location = location.id_location " +
                    "INNER join company on location.company_id = company.company_id where " +
                    "position_windows = true and company_name = '"+map.get("company_name")+"'";

            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next())
                value = rs.getInt(1);
        }catch (SQLException e){
            log.error("la requête est nulle");
        }
        return value;
    }

    private String sensorCompany(Connection connection, Map<String, String> map) {
        String value = "";
        try{
            String sql = "select count(position_sensor) from room inner join " +
                    "location on room.id_location = location.id_location " +
                    "INNER join company on location.company_id = company.company_id " +
                    "where position_sensor = true and company_name = '"+map.get("company_name")+"'";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next())
                value = rs.getString(1);
            log.info(value);
        }catch (SQLException e){
            log.error("la requête est nulle");
        }catch (NullPointerException e){
            log.error("la valeur est null");
        }
        return value;
    }


    private Integer objectCompany(Connection connection, Map<String, String> map) {
        int value = 0;
        try{
            String sql = "select sum(n) from ( select count(name_room) n from room inner join " +
                    "location on room.id_location = location.id_location " +
                    "INNER join company on location.company_id = company.company_id " +
                    "where position_screen = true and company_name = '"+map.get("company_name")+"' " +
                    "union " +
                    "select count(name_room) from room inner join " +
                    "location on room.id_location = location.id_location " +
                    "INNER join company on location.company_id = company.company_id " +
                    "where position_plug = true and company_name = '"+map.get("company_name")+"') as req";

            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next())
                value = rs.getInt(1);
        }catch (SQLException e){
            log.error("la requête est nulle");
        }
        return value;
    }

/** end of company indicators**/

    /** information general*/
    private String getEnergy(Connection connection, Map<String, String> map) {
        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumFractionDigits(2);
        String s = "" ;
        double d = 0.0;
        try{
            String sql = "select sum(energy) from building";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next())
                d = rs.getDouble(1);
            s=format.format(d);
        }catch (SQLException e){
            log.error("la requête est nulle");
        }catch (NullPointerException e){
            log.error("la valeur est null");
        }
        return s;
    }

    private String getAllCompany(Connection connection, Map<String, String> map) {
        String value = "";
        try{
            String sql = "select count(*) from company where begin_location is not NULL";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next())
                value = rs.getString(1);
            log.info(value);
        }catch (SQLException e){
            log.error("la requête est nulle");
        }catch (NullPointerException e){
            log.error("la valeur est null");
        }
        return value;

    }

    private String getAllSensor(Connection connection, Map<String, String> map) {
        String value = "";
        String value2 = "";
        String resp = "";
        try{
            String sql = "select count(position_sensor) from room where position_sensor=true ";
            String sql2 = "select count(position_sensor) from room";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next())
                value = rs.getString(1);
            ResultSet rs2 = connection.createStatement().executeQuery(sql2);
            while (rs2.next())
                value2 = rs2.getString(1);

            resp = value+"/"+value2;
        }catch (SQLException e){
            log.error("la requête est nulle");
        }catch (NullPointerException e){
            log.error("la valeur est null");
        }
        return resp;
    }

    private String getAllEquipments(Connection connection, Map<String, String> map) {

        String value = "";
        String value2 = "";
        String resp = "";
        try{
            String sql = "select count(position_windows) from room where position_windows=true";
            String sql2 = "select count(position_windows) from room";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next())
                value = rs.getString(1);
            ResultSet rs2 = connection.createStatement().executeQuery(sql2);
            while (rs2.next())
                value2 = rs2.getString(1);
            resp = value+"/"+value2;
        }catch (SQLException e){
            log.error("la requête est nulle");
        }catch (NullPointerException e){
            log.error("la valeur est null");
        }
        return resp;
    }

    private Integer getConnectedObjets(Connection connection, Map<String, String> map) {
        int load = 0;

        try {
            String sql = "select sum(m) from " +
                    "(select count(position_screen) m from room " +
                    "where position_screen=true UNION "+
                    "select count(position_plug) FROM room where position_plug=true) as sum";
            ResultSet rs = connection.createStatement().executeQuery(sql);

            while (rs.next())
                load = rs.getInt(1);
        }catch (SQLException e){
            log.error("la requête est nulle");
        }
        return load;
    }

    private String rateOccupation(Connection connection, Map<String, String> map) {
        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumFractionDigits(2);
        double d = 0.0;
        double d2 =0.0;
        String value ="";

        try {
            String sql = "select count(*) from room where status = 'booked'";
            String sql2 ="select count(*) from room";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next())
                d =rs.getDouble(1);
            ResultSet rs2 = connection.createStatement().executeQuery(sql2);
            while (rs2.next())
                d2 =rs2.getDouble(1);
            value = format.format((d/d2) * 100);

        }catch (SQLException e){
            log.error("la requête est nulle");
        }catch (NullPointerException e){
            log.error("la valeur est null");
        }
        return value+"%";
    }
    /******************************************* end  of information general*****************************/
    /* for selecting company in to a combobox**/
    private List<String> comboxNameCompany(Connection connection, Map<String, String> map)  {
        List<String> name = new ArrayList<>();
        try {
            String request = "select company_name from company";
            ResultSet rs = connection.createStatement().executeQuery(request);
            while (rs.next()) {
                name.add(rs.getString(1));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return name;
    }
    /* end of combobox**/
    public static StringBuilder requestbuilding(Connection connection, Map<String, String> map) {

        StringBuilder sb = null;
        try {
            String sql = "SELECT building_name FROM building";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            while (rs.next()) {
                sb.append(rs.getString(1) + "@");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return sb;
    }
    /************************** end of request method of general information***********************/

    /************************** began of request of mapping ***************************************/

    public static StringBuilder requestFloor(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;


        try {

            String sql = "SELECT DISTINCT name_floor " +
                    "FROM floor " +
                    "INNER JOIN building  " +
                    "ON building.id_building = floor.id_building " +
                    "INNER JOIN room " +
                    "ON room.id_floor = floor.id_floor " +
                    "InNER JOIN location " +
                    "ON room.id_location = location.id_location " +
                    "INNER JOIN company " +
                    "ON company.company_id = location.company_id" +
                    " WHERE company.company_name = '"+map.get("company_name")+"' AND building.building_name = '"+map.get("building_name")+"'";
            System.out.println(sql);
            ResultSet rs = connection.createStatement().executeQuery(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            while (rs.next()) {
                sb.append(rs.getString(1) + "@");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;

    }

    public StringBuilder requestRoom(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;

        try {
            String sql = "SELECT name_room FROM company INNER JOIN LOCATION ON company.company_id = location.company_id " +
                    "INNER JOIN room ON room.id_location = location.id_location INNER JOIN floor ON floor.id_floor = room.id_floor " +
                    "INNER JOIN building ON building.id_building = floor.id_building WHERE company.company_name = '" + map.get("company_name") +
                    "' AND floor.name_floor = '" + map.get("floor_name") +
                    "' AND building.building_name = '" + map.get("building_name") +
                    "' AND room.status = 'booked'";


            ResultSet rs = connection.createStatement().executeQuery(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            while (rs.next()) {
                sb.append(rs.getString(1) + "@");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;

    }

    public StringBuilder requestCompany(Connection connection, Map<String, String> map) {

        StringBuilder sb = null;

        try {

            String sql = "SELECT company_name FROM room INNER JOIN company ON  company.company_id = room.id_room ORDER BY company_name";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            while (rs.next()) {
                sb.append(rs.getString(1) + "@");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;

    }

    public StringBuilder requestgetBuilding(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;

        try {


            String sql = "SELECT id_building FROM building WHERE building_name = '" + map.get("name_building") + "'";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            while (rs.next()) {
                sb.append(rs.getString(1) + "@");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;

    }

    public StringBuilder requestgetFloor(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;

        try {

            String sql = "SELECT id_floor FROM floor WHERE name_floor = '" + map.get("name_floor") + "'";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            while (rs.next()) {
                sb.append(rs.getString(1) + "@");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;

    }

    public StringBuilder requestgetListBuilding(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;

        try {

            String sql = "SELECT DISTINCT building_name FROM company INNER JOIN location ON" +
                    " company.company_id = location.company_id INNER JOIN room" +
                    " ON location.id_location = room.id_location INNER JOIN floor ON" +
                    " floor.id_floor = room.id_floor INNER JOIN building ON" +
                    " building.id_building = floor.id_building " +
                    "WHERE company.company_name = '" + map.get("company_name") + "'";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            while (rs.next()) {
                sb.append(rs.getString(1) + "@");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }

    public StringBuilder requestGetIdRoom(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;

        try {

            String sql = "SELECT id_room FROM company INNER JOIN LOCATION " +
                    "ON company.company_id = location.company_id INNER JOIN room " +
                    "ON room.id_location = location.id_location INNER JOIN floor " +
                    "ON floor.id_floor = room.id_floor INNER JOIN building " +
                    "ON building.id_building = floor.id_building WHERE room.name_room = '" + map.get("name_room") + "'";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            while (rs.next()) {
                sb.append(rs.getString(1) + "@");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }

    public StringBuilder requestUpdate(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;

        try {

            String sql = "UPDATE room set position_screen = '" + map.get("value") +
                    "'" + "WHERE id_room = " + map.get("id_room") + "";

            connection.createStatement().executeUpdate(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            sb.append("Update done");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }

    public StringBuilder requestScreenIsEmpty(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;

        try {

            String sql = "SELECT position_screen FROM room" +
                    "    WHERE id_room = '" + map.get("id_room") + "'";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            while (rs.next()) {
                sb.append(rs.getString(1) + "@");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }

    public StringBuilder requestUpdatePrise(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;

        try {

            String sql = "UPDATE room set position_plug = '" + map.get("value") +
                    "'" + "WHERE id_room = " + map.get("id_room") + "";

            connection.createStatement().executeUpdate(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            sb.append("Update done");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }

    public StringBuilder requestPriseIsEmpty(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;

        try {

            String sql = "SELECT position_plug FROM room" +
                    "    WHERE id_room = '" + map.get("id_room") + "'";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            while (rs.next()) {
                sb.append(rs.getString(1) + "@");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }

    public StringBuilder requestUpdateSensor(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;

        try {

            String sql = "UPDATE room set position_sensor = '" + map.get("value") +
                    "'" + "WHERE id_room = " + map.get("id_room") + "";

            connection.createStatement().executeUpdate(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            sb.append("Update done");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }

    public StringBuilder requestSensorIsEmpty(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;

        try {

            String sql = "SELECT position_sensor FROM room" +
                    "    WHERE id_room = '" + map.get("id_room") + "'";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            while (rs.next()) {
                sb.append(rs.getString(1) + "@");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }

    public StringBuilder requestUpdateWindows(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;

        try {

            String sql = "UPDATE room set position_windows = '" + map.get("value") + "'" + "WHERE id_room = " + map.get("id_room") + "";

            connection.createStatement().executeUpdate(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            sb.append("Update done");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }

    public StringBuilder requestWindowsIsEmpty(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;

        try {

            String sql = "SELECT position_windows FROM room" +
                    "    WHERE id_room = '" + map.get("id_room") + "'";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            while (rs.next()) {
                sb.append(rs.getString(1) + "@");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }

    public StringBuilder requestGetEquipment(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;

        try {

            String sql = "SELECT name_equipment FROM equipment" +
                    "    WHERE availablity = TRUE";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            while (rs.next()) {
                sb.append(rs.getString(1) + "@");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }

    public StringBuilder requestUpdateEquipment(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;

        try {

            String sql = "UPDATE equipment" +
                    " set availablity = "+map.get("availablity") +", id_localisation = "+map.get("id_localisation") +"" +
                    " WHERE name_equipment = '"+map.get("name_equipment") +"'" ;


            connection.createStatement().executeUpdate(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            sb.append("Update done");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }

    public StringBuilder requestGetLocalisation(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;

        try {

            String sql = "SELECT id_localisation" +
                    " FROM localisation " +
                    "WHERE id_room = "+map.get("id_room")+" "+" AND position_x = "+map.get("positionX")+" "+" AND position_y = "+map.get("positionY")+"";
            System.out.println(sql);
            ResultSet rs = connection.createStatement().executeQuery(sql);

            sb = new StringBuilder();
            while (rs.next()) {
                sb.append(rs.getString(1) + "@");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }



    // LOCATION

    public StringBuilder initPlanLocation(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;
        String str = "";
        try {

            String sql = "SELECT status FROM room r " +
                    " inner join floor f on r.id_floor = f.id_floor" +
                    " inner join building b on b.id_building = f.id_building" +
                    "    WHERE b.building_name = '" + map.get("rl_building") + "'" +
                    " and f.name_floor='" + map.get("rl_floor") + "';";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            while (rs.next()) {
                str += (!rs.isLast()) ? rs.getString("status") + "-" : rs.getString("status");
            }
            sb.append(str);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }

    public StringBuilder getPlace(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;
        int nb = 0;
        try {

            String sql = "SELECT count(r.id_room) as TOTAL FROM room r " +
                    " inner join floor f on r.id_floor = f.id_floor" +
                    " inner join building b on b.id_building = f.id_building" +
                    "    WHERE b.building_name = '" + map.get("rl_building") + "'" +
                    " and r.status='free';";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            while (rs.next()) {
                nb = rs.getInt("TOTAL");
            }
            sb.append(nb);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }

    public StringBuilder getListBuilding(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;
        String str = "";
        try {

            String sql = "SELECT building_name FROM building order by id_building";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            while (rs.next()) {
                str += (!rs.isLast()) ? rs.getString("building_name") + "-" : rs.getString("building_name");
            }
            sb.append(str);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }

    public StringBuilder getListFloor(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;
        String str = "";
        try {

            String sql = "SELECT distinct(f.name_floor) FROM floor f"+
                    " inner join building b on b.id_building = f.id_building" +
                    " where b.building_name='" + map.get("rl_building") + "'" +
                    " order by f.name_floor";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            while (rs.next()) {
                str += (!rs.isLast()) ? rs.getString("name_floor") + "-" : rs.getString("name_floor");
            }
            sb.append(str);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }

    public StringBuilder getNameBuilding(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;
        String str = "";
        try {

            String sql = "SELECT building_name FROM building where id_building='" + map.get("rl_building") + "'";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            while (rs.next()) {
                str = rs.getString("building_name");
            }
            sb.append(str);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }

    public StringBuilder getNbFloorFree(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;
        int nb = 0;
        try {

            String sql = "SELECT count(r.id_room) from room r"+
                    " inner join floor f on r.id_floor = f.id_floor" +
                    " inner join building b on b.id_building = f.id_building" +
                    "    WHERE b.building_name = '" + map.get("rl_building") + "'" +
                    " and f.name_floor='" + map.get("rl_floor") + "'" +
                    " and r.status='free'";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            while (rs.next()) {
                nb++;
            }
            sb.append(nb);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }

    public StringBuilder getFloorStatu(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;
        String str = "";
        try {

            String sql = "SELECT status from room r"+
                    " inner join floor f on r.id_floor = f.id_floor" +
                    " inner join building b on b.id_building = f.id_building" +
                    "    WHERE b.building_name = '" + map.get("rl_building") + "'" +
                    " and f.name_floor='" + map.get("rl_floor") + "'";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            while (rs.next()) {
                str += (!rs.isLast()) ? rs.getString("status") + "-" : rs.getString("status");
            }
            sb.append(str);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }

    public StringBuilder setFloorStatu(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;
        String str = "";
        String[] f = map.get("rl_floor_to_set").split("-");
        int index = 0;
        try {
            for(int i = 0; i < 10; i++) {
                index = i+1;
                String sql = "update room as r" +
                        " set status = '" + f[i] + "'" +
                        " from floor as f" +
                        " join building as b on b.id_building = f.id_building" +
                        "  where f.id_floor = r.id_floor " +
                        " and b.building_name = '" + map.get("rl_building") + "'" +
                        " and f.name_floor='" + map.get("rl_floor") + "'" +
                        " and RIGHT(cast(id_room as varchar),1)='" + index + "';";
                connection.createStatement().executeQuery(sql);
                System.out.println(sql);
                sb = new StringBuilder();
                sb.append("Update done");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }

    public StringBuilder getDispoBat(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;
        String str = "";
        try {

            String sql = "select id_room, name_floor from room r"+
                    " inner join floor f on r.id_floor = f.id_floor"+
                    " inner join building b on b.id_building = f.id_building"+
                    " WHERE b.building_name = '" + map.get("rl_building") + "' and r.status='free' " +
                    " order by id_room" +
                    " LIMIT " + map.get("rl_nb_loc");
            ResultSet rs = connection.createStatement().executeQuery(sql);
            System.out.println(sql);
            sb = new StringBuilder();
            while (rs.next()) {
                str += (!rs.isLast()) ? rs.getString("id_room") + "//" + rs.getString("name_floor") + "-" :
                        rs.getString("id_room") + "//" + rs.getString("name_floor");
            }
            sb.append(str);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }

    public StringBuilder setStatuResa(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;
        String str = "";
        String[] f = map.get("rl_resa").split("-");
        try {
            for(int i = 0; i < f.length; i++) {
                String idRoom = f[i].split("//")[0];
                String sql = "update room" +
                        " set position_sensor = 't'," +
                        " position_screen = 't'," +
                        " position_plug = 't'," +
                        " position_windows = 't'" +
                        " where id_room='" + idRoom + "';";
                connection.createStatement().executeUpdate(sql);
                System.out.println(sql);
                sb = new StringBuilder();
                sb.append("Update done");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }

    public StringBuilder insertCompany(Connection connection, Map<String, String> map) {
        StringBuilder sb = null;
        String str = "";
        int idCompany = 0;
        try {

            String sql = "select MAX(company_id) as max from company";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            System.out.println(sql);
            while (rs.next()) {
                idCompany = rs.getInt("max");
            }

            String sql2 = "insert into company (company_id, company_name, begin_location) " +
                    "values (" + idCompany+1 +
                    ",'" + map.get("rl_company_name") + "',now());";
            connection.createStatement().executeUpdate(sql2);
            System.out.println(sql2);
            sb = new StringBuilder();
            sb.append("Insert done");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }

}
