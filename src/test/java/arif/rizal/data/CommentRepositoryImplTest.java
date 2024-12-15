package arif.rizal.data;

import arif.rizal.data.ConnectionUtil;
import arif.rizal.data.database.entity.Comment;
import arif.rizal.data.database.repository.CommentRepository;
import arif.rizal.data.database.repository.CommentRepositoryImpl;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CommentRepositoryImplTest {

    private static CommentRepository commentRepository;

    @BeforeAll
    public static void setUp() throws SQLException {
        commentRepository = new CommentRepositoryImpl();

        // Set up database
        try (Connection connection = ConnectionUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS comments (id SERIAL PRIMARY KEY, email VARCHAR(255), comment TEXT)");
        }
    }

    @AfterEach
    public void cleanUp() throws SQLException {
        // Clean up database after each test
        try (Connection connection = ConnectionUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM comments");
        }
    }

    @Test
    public void testInsert() {
        Comment comment = new Comment(null, "test@example.com", "This is a test comment");
        commentRepository.insert(comment);

        assertNotNull(comment.getId());
    }

    @Test
    public void testFindById() {
        Comment comment = new Comment(null, "test@example.com", "This is a test comment");
        commentRepository.insert(comment);

        Comment foundComment = commentRepository.findById(comment.getId());

        assertNotNull(foundComment);
        assertEquals(comment.getId(), foundComment.getId());
        assertEquals(comment.getEmail(), foundComment.getEmail());
        assertEquals(comment.getComment(), foundComment.getComment());
    }

    @Test
    public void testFindAll() {
        Comment comment1 = new Comment(null, "test1@example.com", "This is a test comment 1");
        Comment comment2 = new Comment(null, "test2@example.com", "This is a test comment 2");
        commentRepository.insert(comment1);
        commentRepository.insert(comment2);

        List<Comment> comments = commentRepository.findAll();

        assertEquals(2, comments.size());
    }

    @Test
    public void testFindAllByEmail() {
        Comment comment1 = new Comment(null, "test@example.com", "This is a test comment 1");
        Comment comment2 = new Comment(null, "test@example.com", "This is a test comment 2");
        Comment comment3 = new Comment(null, "other@example.com", "This is another comment");
        commentRepository.insert(comment1);
        commentRepository.insert(comment2);
        commentRepository.insert(comment3);

        List<Comment> comments = commentRepository.findAllByEmail("test@example.com");

        assertEquals(2, comments.size());
    }
}