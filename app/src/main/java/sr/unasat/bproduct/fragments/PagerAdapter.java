package sr.unasat.bproduct.fragments;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    int mNumOftabs;

    public PagerAdapter(FragmentManager fm,int NumOfTabs) {
        super(fm);
        this.mNumOftabs = NumOfTabs;
    }
    @Override
    public Fragment getItem (int position) {
        switch (position) {
            case 0:
                FragmentOne tab1 = new FragmentOne();
                return  tab1;
            case 1: FragmentTwo tab2 = new FragmentTwo();
                return  tab2;
            case 2: FragmentThree tab3 = new FragmentThree();
                return  tab3;
                default:
                    return null;
        }

        }
           @Override
     public int getCount() {
        return mNumOftabs;
           }
    }

