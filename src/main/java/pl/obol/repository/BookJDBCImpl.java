package pl.obol.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pl.obol.model.Book;
import pl.obol.utils.JDBConnection;

import java.sql.*;

@Repository
@Qualifier("JDBC")
public class BookJDBCImpl implements IntfBook {

    private static final String URL = "jdbc:mysql://localhost:3306/bookStore?serverTimezone=UTC&characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASSWORD = "coderslab";


    @Override
    public void saveBook(Book book) {

        try(Connection conn = DriverManager.getConnection(URL,USER, PASSWORD)) {
            String saveSql = "INSERT INTO books (author, book_title, rating) values (?,?,?)";

            PreparedStatement preStmt = conn.prepareStatement(saveSql, Statement.RETURN_GENERATED_KEYS);
            preStmt.setString(1, book.getAuthor());
            preStmt.setString(2, book.getTitle());
            preStmt.setBigDecimal(3, book.getRating());
            preStmt.executeUpdate();
            ResultSet rs = preStmt.getGeneratedKeys();
            if (rs.next()) {
                book.setId(rs.getLong(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public Book findById(long id) {
        try(Connection conn = DriverManager.getConnection(URL,USER, PASSWORD)) {
            String getSql = "SELECT * FROM books WHERE id = ?";
            PreparedStatement preStmt = conn.prepareStatement(getSql);
            preStmt.setInt(1, (int) id);
            ResultSet resultSet = preStmt.executeQuery();
            if(resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setTitle(resultSet.getString("book_title"));
                book.setAuthor(resultSet.getString("author"));
                book.setRating(resultSet.getBigDecimal("rating"));
                return book;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void delete(Book book) {

    }
}
