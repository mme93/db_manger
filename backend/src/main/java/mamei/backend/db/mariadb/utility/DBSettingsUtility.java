package mamei.backend.db.mariadb.utility;

import org.springframework.stereotype.Service;
import java.sql.*;
import java.util.List;

@Service
public class DBSettingsUtility {


    public String preparedStatementWithOneParameter(String query, Connection connection, int index){
        StringBuilder stringBuilder = new StringBuilder();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                stringBuilder.append(resultSet.getString(index)+"\n");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public String preparedStatement(String query, Connection connection)  {
        StringBuilder stringBuilder = new StringBuilder();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                stringBuilder.append(resultSet.getString(0)+"\n");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public String preparedStatement(String query, Connection connection, List<Integer> columnIndexList)  {
        StringBuilder stringBuilder = new StringBuilder();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (columnIndexList.size()==1) {
                while (resultSet.next()) {
                    stringBuilder.append(resultSet.getString(0)+"\n");
                }
            }else  if (columnIndexList.size()>1){
                while (resultSet.next()) {
                    for(int i=0;i<columnIndexList.size();i++){
                        stringBuilder.append(resultSet.getString(columnIndexList.get(i))+"\n");
                    }
                    if (columnIndexList.size()!=1) {
                        stringBuilder.append("<-----Next Row ----> \n");
                    }
                }
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}
