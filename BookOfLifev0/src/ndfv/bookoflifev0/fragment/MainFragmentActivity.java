package ndfv.bookoflifev0.fragment;

import java.util.List;
import java.util.Vector;

import ndfv.bookoflifev0.bookoflifev0.R;
import ndfv.bookoflifev0.entity.ViewPagerInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

public class MainFragmentActivity extends FragmentActivity {
	private PagerAdapter mPagerAdapter;
	private SelectionCountersFragment fragmentSelection;
	private ListOfCountersFragment fragmentListOfCounters;
	private ViewPager pager;
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.view_pager);
 
        // Création de la liste de Fragments que fera défiler le PagerAdapter
        List fragments = new Vector();
 
        // Ajout des Fragments dans la liste
        fragmentListOfCounters = (ListOfCountersFragment) Fragment.instantiate(this,ListOfCountersFragment.class.getName());
        fragments.add(fragmentListOfCounters);
        fragmentSelection = (SelectionCountersFragment) Fragment.instantiate(this,SelectionCountersFragment.class.getName());
        fragments.add(fragmentSelection);
 
        // Création de l'adapter qui s'occupera de l'affichage de la liste de
        // Fragments
        this.mPagerAdapter = new MyPagerAdapter(super.getSupportFragmentManager(), fragments);
 
        pager = (ViewPager) super.findViewById(R.id.viewpager);
        pager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				ViewPagerInterface fragment = (ViewPagerInterface) mPagerAdapter.instantiateItem(pager,arg0);
                if (fragment != null) {
                    fragment.fragmentBecameVisible();
                } 
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        // Affectation de l'adapter au ViewPager
        pager.setAdapter(this.mPagerAdapter);
    }
}
