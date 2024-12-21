package test;

import frames.LoginFrame;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginFrameTest {

    @Test
    public void testLoginFrameCreation() {
        LoginFrame loginFrame = new LoginFrame();
        assertNotNull(loginFrame);
    }

    @Test
    public void testUsernameField() {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.usernameField.setText("testUser");
        assertEquals("testUser", loginFrame.usernameField.getText());
    }

    @Test
    public void testPasswordField() {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.passwordField.setText("testPassword");
        assertEquals("testPassword", new String(loginFrame.passwordField.getPassword()));
    }

}