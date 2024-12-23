package test;

import frames.DeleteFavoriteFrame;
import model.FavoriteLocation;
import model.User;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeleteFavoriteFrameTest {

    @Test
    public void testDeleteFavoriteFrameConstructor() {
        User user = new User(1, "John Doe");
        DeleteFavoriteFrame frame = new DeleteFavoriteFrame(user);
        assertNotNull(frame);
        assertEquals("Delete Favorite Location", frame.getTitle());
        assertEquals(350, frame.getWidth());
        assertEquals(180, frame.getHeight());
    }

}