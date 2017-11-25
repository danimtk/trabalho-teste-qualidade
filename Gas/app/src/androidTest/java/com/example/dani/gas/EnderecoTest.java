package com.example.dani.gas;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.not;

/**
 * Created by dani on 20/11/17.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class EnderecoTest {

    @Rule
    public ActivityTestRule<NovoEnderecoActivity> mActivityRule = new ActivityTestRule<>(
            NovoEnderecoActivity.class);

    @Test
    public void t1() {

        onView(withId(R.id.etCep)).perform(typeText("98200000"), closeSoftKeyboard());

        onView(withId(R.id.etCidade)).perform(typeText("Ibiruba"), closeSoftKeyboard());

        onView(withId(R.id.etRua)).perform(typeText("Rua do teste"), closeSoftKeyboard());

        onView(withId(R.id.etBairro)).perform(typeText("Bairro"), closeSoftKeyboard());

        onView(withId(R.id.etNumero)).perform(typeText("3333"), closeSoftKeyboard());

        onView(withId(R.id.etComplemento)).perform(typeText("Apto 123"), closeSoftKeyboard());

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        onView(withId(R.id.action_salvar)).perform(click());

        onView(allOf(withId(R.id.action_salvar), not(withText("salvar"))));
    }


    @Test
    public void t2() {

        onView(withId(R.id.etCep)).perform(typeText("98200000"), closeSoftKeyboard());

        onView(withId(R.id.etRua)).perform(typeText("Rua do teste"), closeSoftKeyboard());

        onView(withId(R.id.etBairro)).perform(typeText("Bairro"), closeSoftKeyboard());

        onView(withId(R.id.etNumero)).perform(typeText("3333"), closeSoftKeyboard());

        onView(withId(R.id.etComplemento)).perform(typeText("Apto 123"), closeSoftKeyboard());

        onView(withId(R.id.action_salvar)).perform(click());

        onView(allOf(withId(R.id.action_salvar), (withText("salvar"))));
    }

    @Test
    public void t3() {

        onView(withId(R.id.etCep)).perform(typeText("98200000"), closeSoftKeyboard());

        onView(withId(R.id.etCidade)).perform(typeText("Ibiruba"), closeSoftKeyboard());

        onView(withId(R.id.etBairro)).perform(typeText("Bairro"), closeSoftKeyboard());

        onView(withId(R.id.etNumero)).perform(typeText("3333"), closeSoftKeyboard());

        onView(withId(R.id.etComplemento)).perform(typeText("Apto 123"), closeSoftKeyboard());

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        onView(withId(R.id.action_salvar)).perform(click());

        onView(allOf(withId(R.id.action_salvar), (withText("salvar"))));
    }

    @Test
    public void t4() {

        onView(withId(R.id.etRua)).perform(typeText("Rua do teste"), closeSoftKeyboard());

        onView(withId(R.id.etCidade)).perform(typeText("Ibiruba"), closeSoftKeyboard());

        onView(withId(R.id.etBairro)).perform(typeText("Bairro"), closeSoftKeyboard());

        onView(withId(R.id.etComplemento)).perform(typeText("Apto 123"), closeSoftKeyboard());

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        onView(withId(R.id.action_salvar)).perform(click());

        onView(allOf(withId(R.id.action_salvar), (withText("salvar"))));
    }

    @Test
    public void t5() {

        onView(withId(R.id.etRua)).perform(typeText("Rua do teste"), closeSoftKeyboard());

        onView(withId(R.id.etCidade)).perform(typeText("Ibiruba"), closeSoftKeyboard());

        onView(withId(R.id.etComplemento)).perform(typeText("Apto 123"), closeSoftKeyboard());

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        onView(withId(R.id.action_salvar)).perform(click());

        onView(allOf(withId(R.id.action_salvar), (withText("salvar"))));
    }
}
