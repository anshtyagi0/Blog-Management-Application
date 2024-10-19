import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BlogManagementApp extends JFrame {
    private UserDAO userDAO;
    private BlogPostDAO blogPostDAO;
    private CommentDAO commentDAO;
    private int currentUserID;

    public BlogManagementApp() {
        userDAO = new UserDAO();
        blogPostDAO = new BlogPostDAO();
        commentDAO = new CommentDAO();
        currentUserID = -1; // No user logged in

        // Set up the frame
        setTitle("Blog Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        // Create a login panel
        JPanel loginPanel = new JPanel(new GridLayout(4, 2));
        JLabel userLabel = new JLabel("Username:");
        JTextField userText = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passText = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        loginPanel.add(userLabel);
        loginPanel.add(userText);
        loginPanel.add(passLabel);
        loginPanel.add(passText);
        loginPanel.add(new JLabel()); // empty space
        loginPanel.add(loginButton);
        loginPanel.add(new JLabel()); // empty space
        loginPanel.add(registerButton);

        // Add the login panel to the frame
        add(loginPanel);

        // Login action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passText.getPassword());

                if (userDAO.loginUser(username, password)) {
                    currentUserID = userDAO.userId(username, password);
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    showBlogOptions(); // Show blog management options
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password!");
                }
            }
        });

        // Register action
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        setVisible(true);
    }

    // Show blog management options after login
    private void showBlogOptions() {
        // Clear the current panel and replace with blog options
        getContentPane().removeAll();
        JPanel blogPanel = new JPanel(new GridLayout(5, 1));
        JButton createPostButton = new JButton("Create Blog Post");
        JButton viewPostsButton = new JButton("View All Posts");
        JButton addCommentButton = new JButton("Add Comment");
        JButton deletePostButton = new JButton("Delete Post");

        blogPanel.add(createPostButton);
        blogPanel.add(viewPostsButton);
        blogPanel.add(addCommentButton);
        blogPanel.add(deletePostButton);

        add(blogPanel);

        // Action for creating a blog post
        createPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createBlogPost();
            }
        });

        // Action for viewing all blog posts
        viewPostsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAllPosts();
            }
        });

        // Action for adding a comment
        addCommentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addComment();
            }
        });

        // Action for deleting a blog post
        deletePostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletePost();
            }
        });

        revalidate();
        repaint();
    }

    // Register User Logic
    private void registerUser() {
        String username = JOptionPane.showInputDialog("Enter Username:");
        String password = JOptionPane.showInputDialog("Enter Password:");
        String email = JOptionPane.showInputDialog("Enter Email:");
        String role = "user"; // Default role for now

        userDAO.registerUser(username, password, email, role);
        JOptionPane.showMessageDialog(null, "User Registered Successfully!");
    }

    // Create Blog Post Logic (UI + Data Insertion)
    private void createBlogPost() {
        String title = JOptionPane.showInputDialog("Enter Post Title:");
        String content = JOptionPane.showInputDialog("Enter Post Content:");
        String categoryStr = JOptionPane.showInputDialog("Enter Category ID:");
        int categoryId = Integer.parseInt(categoryStr);

        blogPostDAO.createBlogPost(title, content, currentUserID, categoryId);
        JOptionPane.showMessageDialog(null, "Blog Post Created Successfully!");
    }

    // View all posts in a table-like format (UI)
    private void viewAllPosts() {
        java.util.List<BlogPost> posts = blogPostDAO.getAllBlogPosts();

        // Create a table model
        String[][] data = new String[posts.size()][5]; // Adjust size based on the number of columns
        String[] columnNames = {"ID", "Title", "Content", "Author ID", "Category ID"}; // Adjust if you add category name

        for (int i = 0; i < posts.size(); i++) {
            BlogPost post = posts.get(i);
            data[i][0] = String.valueOf(post.getId());
            data[i][1] = post.getTitle();
            data[i][2] = post.getContent();
            data[i][3] = String.valueOf(post.getAuthorId());
            data[i][4] = String.valueOf(post.getCategoryId()); // Or use post.getCategoryName() if you want category name
        }

        // Create a table and display it in a JOptionPane
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        JOptionPane.showMessageDialog(null, scrollPane, "All Blog Posts", JOptionPane.INFORMATION_MESSAGE);
    }

    // Add comment to a blog post
    private void addComment() {
        String postIdStr = JOptionPane.showInputDialog("Enter Post ID:");
        int postId = Integer.parseInt(postIdStr);
        String commentText = JOptionPane.showInputDialog("Enter Comment:");

        commentDAO.addComment(postId, currentUserID, commentText);
        JOptionPane.showMessageDialog(null, "Comment Added Successfully!");
    }

    // Delete blog post (only by the author)
    private void deletePost() {
        String postIdStr = JOptionPane.showInputDialog("Enter Post ID to Delete:");
        int postId = Integer.parseInt(postIdStr);

        boolean isDeleted = blogPostDAO.deleteBlogPost(postId, currentUserID);
        if (isDeleted) {
            JOptionPane.showMessageDialog(null, "Post Deleted Successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to Delete Post! (Only author can delete)");
        }
    }

    public static void main(String[] args) {
        new BlogManagementApp(); // Run the UI
    }
}
