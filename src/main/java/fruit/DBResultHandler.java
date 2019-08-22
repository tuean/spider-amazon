package fruit;

import parser.ProductDetail;

import java.sql.*;
import java.util.Calendar;
import java.util.List;

public class DBResultHandler implements ResultHandler{

    private static Connection con;

    public void init() throws ClassNotFoundException {
        System.out.println("Connecting database...");

        String url = "jdbc:mysql://localhost:3306/amazon?useSSL=false";
        String username = "root";
        String password = "tuean330";

        Class.forName("com.mysql.cj.jdbc.Driver");

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected!");
            con = connection;
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    private String insertSql = "insert into product(" +
            "asin, product_name, price, commentNum, grade, picUrl, salesNum, productDetailUrl, search_key, id) " +
            "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private String updateSql = "update product set price = ? where asin = ?";

    private String selectSql = "select count(1) from product where asin = ?";

    public void handleResult(List<ProductDetail> list) throws ClassNotFoundException, SQLException {
        if (con == null) {
            init();
        }
        Statement smt = con.createStatement();
        for (ProductDetail detail : list) {
            var statement = con.prepareStatement(selectSql);
            statement.setString(1, detail.getAsin());
            ResultSet resultSet = statement.executeQuery();

            int count = 0;
            while (resultSet.next()) {
                 count = resultSet.getInt(1);
            }

            if (count < 1) {
                // insert;
                PreparedStatement insertSmt = con.prepareStatement(insertSql);
                insertSmt.setString(1, detail.getAsin());
                insertSmt.setString(2, detail.getProductName());
                insertSmt.setString(3, detail.getPrice());
                insertSmt.setInt(4, detail.getCommentNum() == null ? 0 : detail.getCommentNum());
                insertSmt.setString(5, detail.getGrade());
                insertSmt.setString(6, detail.getPicUrl());
                insertSmt.setInt(7, detail.getSalesNum() == null ? 0 : detail.getSalesNum());
                insertSmt.setString(8, detail.getProductDetailUrl());
                insertSmt.setString(9, detail.getSearchKey());
                insertSmt.setInt(10, 0);
                insertSmt.execute();
            } else {
                // update price
                // todo
                PreparedStatement updateSmt = con.prepareStatement(updateSql);
                updateSmt.setString(1, detail.getPrice());
                updateSmt.setString(2, detail.getAsin());
                updateSmt.execute();
            }

            PreparedStatement hisInsertSmt = con.prepareStatement(price_histroy_insert_sql);
            Calendar calendar = Calendar.getInstance();
            java.sql.Date recordDate = new java.sql.Date(calendar.getTime().getTime());
            hisInsertSmt.setString(1, detail.getAsin());
            hisInsertSmt.setString(2, detail.getPrice());
            hisInsertSmt.setDate(3, recordDate);
            hisInsertSmt.setInt(4, 0);
            hisInsertSmt.execute();
        }
    }

    private String price_histroy_insert_sql = "insert into price_history(asin, price, create_at, id) values(?, ?, ?, ?)";
}
