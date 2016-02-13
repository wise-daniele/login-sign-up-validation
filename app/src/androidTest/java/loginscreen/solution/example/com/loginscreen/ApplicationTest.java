package loginscreen.solution.example.com.loginscreen;

import android.content.ComponentName;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)

public class ApplicationTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule(MainActivity.class);

    public void perform_signup(String name, String email, String password, String phone) {
        onView(withId(R.id.bt_signup)).perform(click());
        onView(withId(R.id.et_name)).perform(replaceText(name), closeSoftKeyboard());
        onView(withId(R.id.et_email)).perform(replaceText(email), closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(replaceText(password), closeSoftKeyboard());
        onView(withId(R.id.et_phone)).perform(replaceText(phone), closeSoftKeyboard());
        onView(withId(R.id.bt_create)).perform(click());
    }

    public void perform_login(String email, String password) {
        onView(withId(R.id.bt_login)).perform(click());
        onView(withId(R.id.et_email)).perform(replaceText(email), closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(replaceText(password), closeSoftKeyboard());
        onView(withId(R.id.bt_sign_in)).perform(click());
    }

    public boolean verify_post_login(String name, String email, String phone) {
        try {
            // Verify if the current activity is WelcomeActivity
            intended(hasComponent(new ComponentName(getTargetContext(), WelcomeActivity.class)));
            // Verify if required fields are set successful login
            onView(withId(R.id.tv_name)).check(matches(withText(name)));
            onView(withId(R.id.tv_email)).check(matches(withText(email)));
            onView(withId(R.id.tv_phone)).check(matches(withText(phone)));
        } catch (AssertionError ae) {
            return false;
        }

        return true;
    }

    @Test
    public void verify_successful_signup() {
        perform_signup("Arpith Patil", "arpith@hackerrank.com", "Patil@123", "9872938476");
        perform_signup("Arpith Patil1", "arpith1@hackerrank.com", "Patil@123", "9872938477");
        perform_login("arpith@hackerrank.com", "Patil@123");
        boolean success = verify_post_login("Arpith Patil", "arpith@hackerrank.com", "9872938476");
        Assert.assertTrue(success);
    }

    @Test
    public void verify_user_not_exist() {
        perform_login("xyz@hackerrank.com", "P@ssw0rd");
        boolean success = verify_post_login("", "xyz@hackerrank.com", "");
        Assert.assertFalse(success);
    }

    @Test
    public void verify_invalid_email() {
        perform_signup("Adam", "adamhackerrank.com", "Adam@123", "9872938476");
        perform_login("adamhackerrank.com", "Adam@123");
        boolean success = verify_post_login("Adam", "adamhackerrank.com", "9872938476");
        Assert.assertFalse(success);
    }


    @Test
    public void verify_invalid_phone() {
        perform_signup("Adam1", "adam1@hackerrank.com", "Adam@123", "55555");
        perform_login("adam1@hackerrank.com", "Adam@123");
        boolean success = verify_post_login("Adam1", "adam1@hackerrank.com", "55555");
        Assert.assertFalse(success);
    }

    @Test
    public void verify_invalid_password_rule() {
        perform_signup("Adam2", "adam2@hackerrank.com", "Adam", "9999999999");
        perform_login("adam2@hackerrank.com", "Adam");
        boolean success = verify_post_login("Adam2", "adam2@hackerrank.com", "9999999999");
        Assert.assertFalse(success);
    }

}
