package com.example.dani.gas;


import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.hamcrest.object.HasToString.hasToString;

/**
 * Created by dani on 19/11/17.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest

public class LoginTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(
            LoginActivity.class);


    @Test
    public void executarTesteLogin01() {

        onView(withId(R.id.btnlogar)).perform(click());

        //Insere o usuário no campo user

        onView(withId(R.id.email)).perform(typeText("danielmatheuskuhn@gmail.com"), closeSoftKeyboard());

        //Insere a senha no campo pass
        onView(withId(R.id.password)).perform(typeText("12345"), closeSoftKeyboard());

        //Clica no botao submit
        onView(withId(R.id.email_sign_in_button)).perform(click());

        //Verifica se o texto é exibido no textView da SecondActivity
        // onView(withId(R.id.success)).check(matches(withText("Produtos")));

        onView(allOf(withId(R.id.toolbar), (withText("PRODUTOS"))));

    }

    @Test
    public void executarTesteLogin02() {

        onView(withId(R.id.btnlogar)).perform(click());
        //Insere o usuário no campo user

        onView(withId(R.id.email)).perform(typeText("danielmatheuskuhn@gmail.com"), closeSoftKeyboard());

        //Insere a senha no campo pass
        onView(withId(R.id.password)).perform(typeText(""), closeSoftKeyboard());

        //Clica no botao submit
        onView(withId(R.id.email_sign_in_button)).perform(click());

        onView(allOf(withId(R.id.email_sign_in_button), (withText("entrar"))));
    }

    @Test
    public void executarTesteLogin03() {

        onView(withId(R.id.btnlogar)).perform(click());
        // Insere o usuário no campo user

        onView(withId(R.id.email)).perform(typeText("danielmatheuskuhn@gmail.com"), closeSoftKeyboard());

        // Insere a senha no campo pass
        onView(withId(R.id.password)).perform(typeText("12345"), closeSoftKeyboard());

        // Clica no botao submit
        onView(withId(R.id.email_sign_in_button)).perform(click());


        // Clica no botao submit
        onView(withId(R.id.men)).perform(click());


        // Clica no botao submit
      //   onView(withId(R.id.menu_item2)).perform(click());

    }


    @Test
    public void executarTesteLogin04() {

        onView(withId(R.id.btnlogar)).perform(click());
        // Insere o usuário no campo user

        onView(withId(R.id.email)).perform(typeText("danielmatheuskuhn@gmail.com"), closeSoftKeyboard());

        // Insere a senha no campo pass
        onView(withId(R.id.password)).perform(typeText("12345"), closeSoftKeyboard());

        // Clica no botao submit
        onView(withId(R.id.email_sign_in_button)).perform(click());


        onView(ViewMatchers.withText("PEDIDOS")).perform(ViewActions.click());
    }



    @Test
    public void executarTesteLogin05() {

        onView(withId(R.id.email)).perform(typeText("danielmatheuskuhn@gmail.com"), closeSoftKeyboard());

        //Insere a senha no campo pass
        onView(withId(R.id.password)).perform(typeText("12345"), closeSoftKeyboard());

        //Clica no botao submit
        onView(withId(R.id.email_sign_in_button)).perform(click());

        //Verifica se o texto é exibido no textView da SecondActivity
        // onView(withId(R.id.success)).check(matches(withText("Produtos")));

        onView(allOf(withId(R.id.toolbar), (withText("PRODUTOS"))));

        onView(ViewMatchers.withTagValue(Matchers.is((Object)"PEDIDOS"))).perform(ViewActions.click());

       // onData(anything()).inAdapterView(withId(R.id.listviewprodutos)).atPosition(0).perform(click());

       // onData(anything()).inAdapterView(withId(R.id.listviewenderecos)).onChildView(withId(R.id.btSelecionar)).atPosition(0).perform(click());


        // onData(hasToString(startsWith("ASDF"))).inAdapterView(withId(R.id.myListView)).perform(click());

        // onData(anything()).inAdapterView(withContentDescription("ENTREGAR AQUI")).atPosition(0).perform(click());

     //   onData(anything()).atPosition(0).check(matches(withText("ENTREGAR AQUI")));

    }


 /*
    @Test
    public void executarTesteLogin04() {

        onView(withId(R.id.email)).perform(typeText("danielmatheuskuhn@gmail.com"), closeSoftKeyboard());

        //Insere a senha no campo pass
        onView(withId(R.id.password)).perform(typeText("12345"), closeSoftKeyboard());

        //Clica no botao submit
        onView(withId(R.id.email_sign_in_button)).perform(click());

        //Verifica se o texto é exibido no textView da SecondActivity
        // onView(withId(R.id.success)).check(matches(withText("Produtos")));

        onView(allOf(withId(R.id.toolbar), (withText("PRODUTOS"))));

      //  onView(allOf(withId(R.id.toolbar), (withText("PEDIDOS"))));

        onView((withText("PEDIDOS"))).perform(click());


        // onData(hasToString(startsWith("ASDF"))).inAdapterView(withId(R.id.myListView)).perform(click());

        // onData(anything()).inAdapterView(withContentDescription("ENTREGAR AQUI")).atPosition(0).perform(click());

        //   onData(anything()).atPosition(0).check(matches(withText("ENTREGAR AQUI")));

    }
*/
}
