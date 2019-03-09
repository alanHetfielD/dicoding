package com.madukubah.katalogfilm2.view.activity.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.madukubah.katalogfilm2.R;
import com.madukubah.katalogfilm2.view.fragment.favorite.FavoriteFragment;
import com.madukubah.katalogfilm2.view.fragment.now_playing.NowPlayingkFragment;
import com.madukubah.katalogfilm2.view.fragment.search.SearchFragment;
import com.madukubah.katalogfilm2.view.fragment.up_coming.UpComingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity
        extends
            AppCompatActivity
        implements
            BottomNavigationView.OnNavigationItemSelectedListener

{
    public static String FRAGMENT_STATE = "FRAGMENT_STATE";
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
//    declaration
    FragmentManager manager = getSupportFragmentManager();
    NowPlayingkFragment nowPlayingkFragment = NowPlayingkFragment.create();
    SearchFragment searchFragment = SearchFragment.create();
    UpComingFragment upComingFragment = UpComingFragment.create();
    FavoriteFragment favoriteFragment = FavoriteFragment.create();
    Fragment active ;
//    declaration
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        navigation.setOnNavigationItemSelectedListener(this);
         if( savedInstanceState != null ){
//             Toast.makeText(this , ""+manager.getFragments().size(), Toast.LENGTH_SHORT).show();
//             for (Fragment fragment:manager.getFragments()) {
//                 manager.beginTransaction().remove(fragment).commit();
//             }
//
//             int itemId = savedInstanceState.getInt(FRAGMENT_STATE, R.id.navigation_now_playing);
//             active = nowPlayingkFragment;
//             manager.beginTransaction().add(R.id.frame , upComingFragment).hide(upComingFragment).commit();
//             manager.beginTransaction().add(R.id.frame , favoriteFragment).hide(upComingFragment).commit();
//             manager.beginTransaction().add(R.id.frame , searchFragment).hide(upComingFragment).commit();
//             manager.beginTransaction().add(R.id.frame , nowPlayingkFragment).hide(upComingFragment).commit();
//
//             setFragment( itemId );
//             navigation.setSelectedItemId( R.id.navigation_now_playing );

        }else{
//             active = nowPlayingkFragment;
//             manager.beginTransaction().add(R.id.frame , upComingFragment).hide(upComingFragment).commit();
//             manager.beginTransaction().add(R.id.frame , favoriteFragment).hide(favoriteFragment).commit();
//             manager.beginTransaction().add(R.id.frame , searchFragment).hide(searchFragment).commit();
//             manager.beginTransaction().add(R.id.frame , nowPlayingkFragment).commit();
//             Toast.makeText(this , ""+manager.getFragments().size(), Toast.LENGTH_SHORT).show();

             navigation.setSelectedItemId( R.id.navigation_now_playing );
         }

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(FRAGMENT_STATE, navigation.getSelectedItemId() );
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = null;
        switch ( item.getItemId() )
        {
            case R.id.navigation_now_playing :
                fragment = nowPlayingkFragment;
//                manager.beginTransaction().hide(active).show(nowPlayingkFragment).commit();
//                active = nowPlayingkFragment;
                break;
            case R.id.navigation_upcoming :
                fragment = upComingFragment;
//                manager.beginTransaction().hide(active).show(upComingFragment).commit();
//                active = upComingFragment;
                break;
            case R.id.navigation_search :
                fragment = searchFragment;
//                manager.beginTransaction().hide(active).show(searchFragment).commit();
//                active = searchFragment;
                break;
            case R.id.navigation_favorite :
                fragment = favoriteFragment;
//                manager.beginTransaction().hide(active).show(favoriteFragment).commit();
//                active = favoriteFragment;
                break;
        }
        if( fragment != null )
        {
            fragmentTransaction.replace(R.id.frame, fragment ).commit();
            return true;
        }

        return true;
    }

    public void setFragment( int itemId )
    {
        switch ( itemId )
        {
            case R.id.navigation_now_playing :
                manager.beginTransaction().hide(active).show(nowPlayingkFragment).commit();
                active = nowPlayingkFragment;
                break;
            case R.id.navigation_upcoming :
                manager.beginTransaction().hide(active).show(upComingFragment).commit();
                active = upComingFragment;
                break;
            case R.id.navigation_search :
                manager.beginTransaction().hide(active).show(searchFragment).commit();
                active = searchFragment;
                break;
            case R.id.navigation_favorite :
                manager.beginTransaction().hide(active).show(favoriteFragment).commit();
                active = favoriteFragment;
                break;
        }
    }
}
