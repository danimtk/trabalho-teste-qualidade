package com.example.dani.gas.slidingtab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dani.gas.view.TabProdutos;
import com.example.dani.gas.view.TabPedidos;


/**
 * Created by dani on 26/03/16.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter implements SlidingTabLayout.IconTabProvider {

    int tabIcons[];
    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
   public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }



    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if (position == 0) // if the position is 0 we are returning the First tab
        {
            TabProdutos tab3 = new TabProdutos();
            return tab3;
        } else {
            // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
            TabPedidos tab4 = new TabPedidos();
            return tab4;
        }
    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }

    @Override
    public int getPageIconResId(int position) {
        return tabIcons[position];
    }


}