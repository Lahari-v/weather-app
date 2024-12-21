package test;

import frames.AddFavoriteFrame;
import model.User;
import org.junit.Test;
import static org.junit.Assert.*;

public class AddFavoriteFrameTest {

    @Test
    public void testAddFavoriteFrameCreation() {
        User user = new User();
        AddFavoriteFrame addFavoriteFrame = new AddFavoriteFrame(user);
        assertNotNull(addFavoriteFrame);
    }

    @Test
    public void testLocationField() {
        User user = new User();
        AddFavoriteFrame addFavoriteFrame = new AddFavoriteFrame(user);
        addFavoriteFrame.locationField.setText("Test Location");
        assertEquals("Test Location", addFavoriteFrame.locationField.getText());
    }

    @Test
    public void testAddFavoriteButton() {
        User user = new User();
        AddFavoriteFrame addFavoriteFrame = new AddFavoriteFrame(user);
        assertNotNull(addFavoriteFrame.addFavoriteButton);
    }
}

