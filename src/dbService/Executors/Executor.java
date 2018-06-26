package dbService.Executors;

import java.sql.*;
import java.util.function.Function;

public class Executor {

    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public <T> T execCreateStatementQuery(String query, Function<ResultSet, T> function) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(query);
        ResultSet resultSet = statement.getResultSet();
        T value = function.apply(resultSet);
        resultSet.close();
        statement.close();

        return value;
    }

    public int execUpdatePreparedStatementQuery(String query, Object... array) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        for (int i = 0; i < array.length; i++) {
            preparedStatement.setObject(i + 1, array[i]);
        }

        return preparedStatement.executeUpdate();

    }

    public <T> T execSelectPreparedStatementQuery(String query, Function<ResultSet, T> function, Object... array)
            throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        for (int i = 0; i < array.length; i++) {
            preparedStatement.setObject(i + 1, array[i]);
        }
        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.getResultSet();

        T value = function.apply(resultSet);

        resultSet.close();
        preparedStatement.close();

        return value;
    }
}




