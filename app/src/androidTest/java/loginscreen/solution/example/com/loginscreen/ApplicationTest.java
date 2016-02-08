package loginscreen.solution.example.com.loginscreen;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)

public class ApplicationTest  {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);
    ActivityTestRule<NewSignup> signup =
            new ActivityTestRule(NewSignup.class, true, false);


    @Test
    public void test1()
    {
        onView(withId(R.id.signup)).perform(click());
        onView(withId(R.id.firstname)).perform(typeText("Arpith"), closeSoftKeyboard());
        onView(withId(R.id.lastname)).perform(click(),replaceText("Patil"),closeSoftKeyboard());
        onView(withId(R.id.email1)).perform(click(),replaceText("arpith@hackerrank.com"),closeSoftKeyboard());
        onView(withId(R.id.pass1)).perform(click(),replaceText("Patil@123"),closeSoftKeyboard());
        onView(withId(R.id.phone)).perform(typeText("9872938476"),closeSoftKeyboard());
        onView(withId(R.id.create)).perform(click());

        onView(withId(R.id.textView)).check(matches(withText("Hi Arpith Patil you have signed up successfully")));
    }
    @Test
    public void test2()
    {
        onView(withId(R.id.signup)).perform(click());
        onView(withId(R.id.firstname)).perform(typeText("Sam"), closeSoftKeyboard());
        onView(withId(R.id.lastname)).perform(click(),replaceText("Clerk"),closeSoftKeyboard());
        onView(withId(R.id.email1)).perform(click(),replaceText(""),closeSoftKeyboard());
        onView(withId(R.id.pass1)).perform(click(),replaceText("aA123"),closeSoftKeyboard());
        onView(withId(R.id.phone)).perform(typeText("8888888888"),closeSoftKeyboard());
        onView(withId(R.id.create)).perform(click());
        onView(withId(R.id.email1)).check(matches(withError("Invalid Email")));
        onView(withId(R.id.pass1)).check(matches(withError("Password must contain one uppercase,one lowercase,1 digit and one special character")));




    }
    @Test
    public void test3()
    {
        onView(withId(R.id.signup)).perform(click());
        onView(withId(R.id.firstname)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.lastname)).perform(click(),replaceText(""),closeSoftKeyboard());
        onView(withId(R.id.email1)).perform(click(),replaceText("jkajs"),closeSoftKeyboard());
        onView(withId(R.id.pass1)).perform(click(),replaceText("shydh"),closeSoftKeyboard());
        onView(withId(R.id.phone)).perform(typeText(""),closeSoftKeyboard());
        onView(withId(R.id.create)).perform(click());
        onView(withId(R.id.firstname)).check(matches(withError("First Name cant be empty")));
        onView(withId(R.id.lastname)).check(matches(withError("Last name cant be empty")));
        onView(withId(R.id.email1)).check(matches(withError("Invalid Email")));
        onView(withId(R.id.pass1)).check(matches(withError("Password must contain one uppercase,one lowercase,1 digit and one special character")));
        onView(withId(R.id.phone)).check(matches(withError("Invalid Phone")));

    }

    @Test
    public void test4()
    {
        onView(withId(R.id.signup)).perform(click());
        onView(withId(R.id.firstname)).perform(typeText("sdsdd"), closeSoftKeyboard());
        onView(withId(R.id.lastname)).perform(click(),replaceText("sdsdsd"),closeSoftKeyboard());
        onView(withId(R.id.email1)).perform(click(),replaceText(""),closeSoftKeyboard());
        onView(withId(R.id.pass1)).perform(click(),replaceText(""),closeSoftKeyboard());
        onView(withId(R.id.phone)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.create)).perform(click());
        onView(withId(R.id.email1)).check(matches(withError("Invalid Email")));
        onView(withId(R.id.pass1)).check(matches(withError("Password must contain one uppercase,one lowercase,1 digit and one special character")));
        onView(withId(R.id.phone)).check(matches(withError("Invalid Phone")));
        onView(withId(R.id.firstname)).perform(click(),replaceText("Aakansha"), closeSoftKeyboard());
        onView(withId(R.id.lastname)).perform(click(), replaceText("Doshi"), closeSoftKeyboard());
        onView(withId(R.id.email1)).perform(click(), replaceText("aakansha1216@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.pass1)).perform(click(), replaceText("HelloWorld@123"), closeSoftKeyboard());
        onView(withId(R.id.phone)).perform(typeText("8916778293"), closeSoftKeyboard());
        onView(withId(R.id.create)).perform(click());
        onView(withId(R.id.textView)).check(matches(withText("Hi Aakansha Doshi you have signed up successfully")));



    }
    @Test
    public void test5()
    {
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.email1)).perform(click(),typeText("aakansha1216"),closeSoftKeyboard());
        onView(withId(R.id.pass1)).perform(click(),typeText("HelloWorld@123"),closeSoftKeyboard());
        onView(withId(R.id.login1)).perform(click());
        onView(withId(R.id.email1)).check(matches(withError("Invalid Email")));
        onView(withId(R.id.email1)).perform(click(), replaceText("aakansha1216@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.login1)).perform(click());
        onView(withId(R.id.welcometext)).check(matches(withText("Name:\tAakansha Doshi\nEmail:\taakansha1216@gmail.com\nphone:\t8916778293")));



    }

    @Test
    public void test6()
    {
        onView(withId(R.id.email1)).perform(click(), typeText("arpith@hackerrank.com"), closeSoftKeyboard());
        onView(withId(R.id.pass1)).perform(click(),typeText("HelloWorld@123"),closeSoftKeyboard());
        onView(withId(R.id.login1)).perform(click());
        onView(withText(R.string.loginfail)).inRoot(withDecorView(not((mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

    }

    @Test
    public void test7()
    {

        onView(withId(R.id.email1)).perform(click(),typeText("hari@hackerrank.com"),closeSoftKeyboard());
        onView(withId(R.id.pass1)).perform(click(),typeText("Hari@123"),closeSoftKeyboard());
        onView(withId(R.id.login1)).perform(click());
        onView(withText(R.string.loginfail)).inRoot(withDecorView(not((mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        onView(withId(R.id.signup)).perform(click());
        onView(withId(R.id.firstname)).perform(click(),replaceText("Hari"), closeSoftKeyboard());
        onView(withId(R.id.lastname)).perform(click(),replaceText("Karunanidhi"),closeSoftKeyboard());
        onView(withId(R.id.email1)).perform(click(),replaceText("hari@hackerrank.com"),closeSoftKeyboard());
        onView(withId(R.id.pass1)).perform(click(),replaceText("HAri@hacker1"),closeSoftKeyboard());
        onView(withId(R.id.phone)).perform(typeText("9902538776"),closeSoftKeyboard());
        onView(withId(R.id.create)).perform(click());
        onView(withId(R.id.textView)).check(matches(withText("Hi Hari Karunanidhi you have signed up successfully")));


    }
    @Test
    public void test8()
    {
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.email1)).perform(click(),typeText("hari@hackerrank.com"),closeSoftKeyboard());
        onView(withId(R.id.pass1)).perform(click(),typeText(""),closeSoftKeyboard());
        onView(withId(R.id.login1)).perform(click());
        onView(withId(R.id.pass1)).check(matches(withError("Password can be empty!!")));
        onView(withId(R.id.pass1)).perform(click(), typeText("HAri@hacker1"), closeSoftKeyboard());
        onView(withId(R.id.login1)).perform(click());
        onView(withId(R.id.welcometext)).check(matches(withText("Name:\tHari Karunanidhi\nEmail:\thari@hackerrank.com\nphone:\t9902538776")));



    }
    private static Matcher<View> withError(final String expected) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof EditText)) {
                    return false;
                }
                EditText editText = (EditText) view;
                return editText.getError().toString().equals(expected);
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }
}
