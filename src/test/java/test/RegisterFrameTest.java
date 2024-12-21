package test;

import frames.RegisterFrame;
import org.junit.Test;


import static org.junit.Assert.*;

public class RegisterFrameTest {

    @Test
    public void testRegisterFrameCreation() {
        RegisterFrame registerFrame = new RegisterFrame();
        assertNotNull(registerFrame);
    }

    @Test
    public void testUsernameField() {
        RegisterFrame registerFrame = new RegisterFrame();
        registerFrame.usernameField.setText("Test Username");
        assertEquals("Test Username", registerFrame.usernameField.getText());
    }

    @Test
    public void testEmailField() {
        RegisterFrame registerFrame = new RegisterFrame();
        registerFrame.emailField.setText("test@example.com");
        assertEquals("test@example.com", registerFrame.emailField.getText());
    }

    @Test
    public void testPasswordField() {
        RegisterFrame registerFrame = new RegisterFrame();
        registerFrame.passwordField.setText("Test Password");
        assertEquals("Test Password", new String(registerFrame.passwordField.getPassword()));
    }

    @Test
    public void testDefaultLocationField() {
        RegisterFrame registerFrame = new RegisterFrame();
        registerFrame.defaultLocationField.setText("Test Location");
        assertEquals("Test Location", registerFrame.defaultLocationField.getText());
    }

    @Test
    public void testRegisterButton() {
        RegisterFrame registerFrame = new RegisterFrame();
        assertNotNull(registerFrame.registerButton);
    }


}