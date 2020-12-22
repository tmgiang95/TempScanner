package com.poa.tempscanner.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.poa.tempscanner.R;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public abstract class BaseActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener, INavigableActivity {

    private String mCurrentFragmentTag = "";

    protected FragmentManager mFragmentManager;

    protected ArrayList<String> NAVIGABLE_FRAGMENTS = new ArrayList<>();

    public static boolean isAppWentToBackground = false;

    public static boolean isWindowFocused = false;

    public static boolean isBackPressed = false;

    public abstract int getLayoutRedId();

    public abstract int getFragmentContainerResId();

    protected BaseActivity getActivity() {
        return this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        setContentView(getLayoutRedId());
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.addOnBackStackChangedListener(this);
    }

    @Override
    public void showScreen(String screenTag, Bundle data) {
        addFragment(getFragmentContainerResId(), screenTag, data);
    }

    public void addFragment(int containerViewId, String fragmentTag, Bundle data) {
        if (fragmentTag.equals(mCurrentFragmentTag)) {
            return;
        }
        Fragment frag = mFragmentManager.findFragmentByTag(fragmentTag);
        if (frag != null) {
            if (NAVIGABLE_FRAGMENTS.contains(fragmentTag)) {
                mFragmentManager.popBackStack(fragmentTag, 0);
                frag.onResume();
                return;
            }
        }

        // Create new fragment
        try {
            frag = FragmentFactory.INSTANCE.createFragment(fragmentTag, data);
        } catch (Exception e) {
            Timber.d(e);
        }
        if (frag != null && !isFinishing() && !isDestroyed()) {
            mCurrentFragmentTag = fragmentTag;
            mFragmentManager.beginTransaction()
                    .add(containerViewId, frag, fragmentTag)
                    .addToBackStack(fragmentTag)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        isBackPressed = true;
        Fragment fragment = this.getSupportFragmentManager().findFragmentById(R.id.container);
        if (fragment instanceof IOnBackPressed) {
            if (((IOnBackPressed) fragment).onBackPressed()) {
                doBackPressed();
            }
        } else {
            doBackPressed();
        }
    }

    private void doBackPressed() {
        int fragmentCount = mFragmentManager.getBackStackEntryCount();
        if (fragmentCount > 1) {
            mFragmentManager.popBackStack();
        } else {
            finish();
        }
    }

    private Fragment getCurrentFragmentOnScreen() {
        List<Fragment> fragments = this.getSupportFragmentManager().getFragments();
        if (fragments.size() > 0)
            return fragments.get(fragments.size() - 1);
        else return null;
    }

    public void markNavigableFragment(String tag) {
        NAVIGABLE_FRAGMENTS.add(tag);
    }

    @Override
    public void onBackStackChanged() {
        if (mFragmentManager.getBackStackEntryCount() >= 1) {
            String temp = mFragmentManager.getBackStackEntryAt(
                    mFragmentManager.getBackStackEntryCount() - 1).getName();
            /** update tool bar **/
            mCurrentFragmentTag = temp;
//            updateToolbar(mCurrentFragmentTag, mTitleMap.get(mCurrentFragmentTag));
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        isWindowFocused = hasFocus;
        if (isBackPressed && !hasFocus) {
            isBackPressed = false;
            isWindowFocused = true;
        }

        super.onWindowFocusChanged(hasFocus);
    }

}
