import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BlogPostDAO {
    public void createBlogPost(String title, String content, int authorId, int categoryId) {
        String sql = "INSERT INTO BlogPosts (title, content, author_id, category_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, content);
            stmt.setInt(3, authorId);
            stmt.setInt(4, categoryId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteBlogPost(int postId, int authorId) {
        String sql = "DELETE FROM BlogPosts WHERE post_id = ? AND author_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, postId);
            stmt.setInt(2, authorId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Returns true if a row was deleted
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Returns false if an error occurred
        }
    }

    public List<BlogPost> getAllBlogPosts() {
        List<BlogPost> blogPosts = new ArrayList<>();
        String sql = "SELECT * FROM BlogPosts"; // Adjust this if you have specific conditions

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                BlogPost post = new BlogPost(
                        rs.getInt("post_id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getInt("author_id"),
                        rs.getInt("category_id")
                );
                blogPosts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogPosts; // Return the list of blog posts
    }
    public BlogPost getBlogPost(int postId) {
        String sql = "SELECT * FROM BlogPosts WHERE post_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, postId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new BlogPost(
                        rs.getInt("post_id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getInt("author_id"),
                        rs.getInt("category_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if not found
    }

}