package pl.obol.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pl.obol.model.Book;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Repository
@Qualifier("JDBC")
public class BookJDBCImpl implements IntfBook {

    private static final String URL = "jdbc:mysql://localhost:3306/bookStore?serverTimezone=UTC&characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASSWORD = "coderslab";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @Override
    public void saveBook(Book book) {

        try(Connection conn = DriverManager.getConnection(URL,USER, PASSWORD)) {
            String saveSql = "INSERT INTO books (author, book_title, rating, created_on) values (?,?,?,?)";

            PreparedStatement preStmt = conn.prepareStatement(saveSql, Statement.RETURN_GENERATED_KEYS);
            preStmt.setString(1, book.getAuthor());
            preStmt.setString(2, book.getTitle());
            preStmt.setBigDecimal(3, book.getRating());
            preStmt.setString(4, formatter.format(LocalDateTime.now()));
            preStmt.executeUpdate();
            ResultSet rs = preStmt.getGeneratedKeys();
            if (rs.next()) {
                book.setId(rs.getLong(1));
                book.setDateCreated();
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
                String dateCreated = resultSet.getString("created_on");
                String dateUpdated = resultSet.getString("updated_on");

                if(dateUpdated != null){
                    book.setUpdated(LocalDateTime.parse(dateUpdated,formatter));
                }
                book.setCreated(LocalDateTime.parse(dateCreated,formatter));
                return book;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Book b) {
        String updateSql = "UPDATE books SET author=?, rating=?, book_title=?,updated_on=now() WHERE id=?";
        try(Connection conn = DriverManager.getConnection(URL,USER,PASSWORD)){
            PreparedStatement stat = conn.prepareStatement(updateSql);
            stat.setString(1,b.getAuthor());
            stat.setBigDecimal(2,b.getRating());
            stat.setString(3,b.getTitle());
            stat.setInt(4, (int) b.getId());
            stat.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Book book) {
        String deleteSql = "DELETE FROM books WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL,USER, PASSWORD)){
            PreparedStatement stat = conn.prepareStatement(deleteSql);
            stat.setLong(1,book.getId());
            stat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
