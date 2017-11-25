package com.example.dani.gas;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

/**
 * Created by dani on 20/11/17.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest

public class BemVindoActivityTest {


    @Rule
    public ActivityTestRule<BemVindoActivity> mActivityRule = new ActivityTestRule<>(
            BemVindoActivity.class);


    @Test
    public void t01() {

        onView(withId(R.id.btncadastrar)).perform(click());
        //Insere o usuário no campo user

        onView(withId(R.id.etNome)).perform(typeText("Dani Teste"), closeSoftKeyboard());

        onView(withId(R.id.etEmail)).perform(typeText("danielteste@gmail.com"), closeSoftKeyboard());

        //Insere a senha no campo pass
        onView(withId(R.id.etSenha)).perform(typeText("54321"), closeSoftKeyboard());

        //Clica no botao submit
        onView(withId(R.id.email_sign_in_button)).perform(click());
    }


    @Test
    public void t02() {

        onView(withId(R.id.btncadastrar)).perform(click());
        //Insere o usuário no campo user

        onView(withId(R.id.etNome)).perform(typeText("Dani Teste"), closeSoftKeyboard());

        onView(withId(R.id.etEmail)).perform(typeText("danieltestegmail.com"), closeSoftKeyboard());

        //Insere a senha no campo pass
        onView(withId(R.id.etSenha)).perform(typeText("54321"), closeSoftKeyboard());

        //Clica no botao submit
        onView(withId(R.id.email_sign_in_button)).perform(click());

        onView(allOf(withId(R.id.email_sign_in_button), (withText("cadastrar"))));
    }

    @Test
    public void t03() {

        onView(withId(R.id.btncadastrar)).perform(click());
        //Insere o usuário no campo user

        onView(withId(R.id.etNome)).perform(typeText("Dani Teste"), closeSoftKeyboard());

        //Insere a senha no campo pass
        onView(withId(R.id.etSenha)).perform(typeText("54321"), closeSoftKeyboard());

        //Clica no botao submit
        onView(withId(R.id.email_sign_in_button)).perform(click());

        onView(allOf(withId(R.id.email_sign_in_button), (withText("cadastrar"))));
    }


    @Test
    public void t04() {

        onView(withId(R.id.btncadastrar)).perform(click());
        //Insere o usuário no campo user

        onView(withId(R.id.etEmail)).perform(typeText("danielteste@gmail.com"), closeSoftKeyboard());

        //Insere a senha no campo pass
        onView(withId(R.id.etSenha)).perform(typeText("54321"), closeSoftKeyboard());

        //Clica no botao submit
        onView(withId(R.id.email_sign_in_button)).perform(click());

        onView(allOf(withId(R.id.email_sign_in_button), (withText("cadastrar"))));
    }


    @Test
    public void t05() {

        onView(withId(R.id.btncadastrar)).perform(click());
        //Insere o usuário no campo user

        onView(withId(R.id.etNome)).perform(typeText("Dani Teste"), closeSoftKeyboard());

        onView(withId(R.id.etEmail)).perform(typeText("danielteste@gmail.com"), closeSoftKeyboard());

        //Clica no botao submit
        onView(withId(R.id.email_sign_in_button)).perform(click());

        onView(allOf(withId(R.id.email_sign_in_button), (withText("cadastrar"))));
    }

    @Test
    public void t06() {

        onView(withId(R.id.btncadastrar)).perform(click());
        //Insere o usuário no campo user

        onView(withId(R.id.etEmail)).perform(typeText("danielteste@gmail.com"), closeSoftKeyboard());

        //Clica no botao submit
        onView(withId(R.id.email_sign_in_button)).perform(click());

        onView(allOf(withId(R.id.email_sign_in_button), (withText("cadastrar"))));
    }

    @Test
    public void t07() {

        onView(withId(R.id.btncadastrar)).perform(click());
        //Insere o usuário no campo user

        onView(withId(R.id.etNome)).perform(typeText("Dani Teste"), closeSoftKeyboard());

        //Clica no botao submit
        onView(withId(R.id.email_sign_in_button)).perform(click());

        onView(allOf(withId(R.id.email_sign_in_button), (withText("cadastrar"))));
    }


    @Test
    public void t08() {

        onView(withId(R.id.btncadastrar)).perform(click());
        //Insere o usuário no campo user

        onView(withId(R.id.etSenha)).perform(typeText("54321"), closeSoftKeyboard());

        //Clica no botao submit
        onView(withId(R.id.email_sign_in_button)).perform(click());

        onView(allOf(withId(R.id.email_sign_in_button), (withText("cadastrar"))));
    }


    @Test
    public void t09() {

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
    public void t10() {

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
    public void t11() {

        onView(withId(R.id.btnlogar)).perform(click());
        // Insere o usuário no campo user

        onView(withId(R.id.email)).perform(typeText("teste@gmail.com"), closeSoftKeyboard());

        // Insere a senha no campo pass
        onView(withId(R.id.password)).perform(typeText("12345"), closeSoftKeyboard());

        // Clica no botao submit
        onView(withId(R.id.email_sign_in_button)).perform(click());

        // Clica no botao submit
        onView(withId(R.id.men)).perform(click());
    }


    @Test
    public void t12() {

        onView(withId(R.id.btnlogar)).perform(click());
        // Insere o usuário no campo user

        onView(withId(R.id.email)).perform(typeText("teste@gmail.com"), closeSoftKeyboard());

        // Insere a senha no campo pass
        onView(withId(R.id.password)).perform(typeText("12345"), closeSoftKeyboard());

        // Clica no botao submit
        onView(withId(R.id.email_sign_in_button)).perform(click());
    }
}
