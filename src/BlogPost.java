public class BlogPost {
    private int id;                // Unique identifier for the blog post
    private String title;          // Title of the blog post
    private String content;        // Content of the blog post
    private int authorId;          // ID of the author
    private int categoryId;        // Category ID for the post
    private String categoryName;    // (Optional) Name of the category

    // Constructor
    public BlogPost(int id, String title, String content, int authorId, int categoryId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.categoryId = categoryId;
        this.categoryName = categoryName; // Initialize category name
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() { // Add this method
        return categoryName;
    }

    // toString method for easier representation of the blog post
    @Override
    public String toString() {
        return "BlogPost{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", authorId=" + authorId +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' + // Include in toString if needed
                '}';
    }
}
