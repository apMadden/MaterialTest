package com.andy.keyme;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.transition.Explode;
import android.transition.Fade;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.andy.keyme.CustomAdapters.DrawerAdapter;
import com.andy.keyme.CustomAdapters.KeyChainAdapter;

public class MainActivity extends AppCompatActivity {
    private View mFabButton;
    private View mHeader;
    private Toolbar mToolbar;
    //First We Declare Titles And Icons For Our Navigation Drawer List View
    //This Icons And Titles Are holded in an Array as you can see
    String KEY_NAMES[] = {"Deka's house", "Home", "Work", "test", "test 9", "Front Door", "back door"};
    int KEY_IMAGES[] = {R.drawable.key_flat, R.drawable.key_flat, R.drawable.key_flat, R.drawable.key_flat, R.drawable.key_flat, R.drawable.key_flat, R.drawable.key_flat};
    int KEY_CARD_COLORS[] = {R.color.color_card_one, R.color.color_card_two, R.color.color_card_three, R.color.color_card_four, R.color.color_card_one, R.color.color_card_two, R.color.color_card_three};
    int ICONS[] = {R.drawable.ic_home_black_24dp, R.drawable.ic_map_black_24dp, R.drawable.ic_store_black_24dp, R.drawable.ic_shopping_cart_black_24dp, R.drawable.ic_account_box_black_24dp, R.drawable.ic_help_black_24dp, R.drawable.ic_build_black_24dp};
    String NAME = "Test name";
    String EMAIL = "test@email.com";

    RecyclerView rvNavDrawer;                           // Declaring RecyclerView
    DrawerAdapter navDrawerAdapter;
    RecyclerView.LayoutManager navDrawerLayoutManager;
    DrawerLayout Drawer;                                  // Declaring DrawerLayout
    ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle

    RecyclerView rvMainContainer;
    RecyclerView.Adapter mainContainerAdapter;
    RecyclerView.LayoutManager mainContainerLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);

        // Check if we're running on Android 5.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Call some material design APIs here
        } else {
            // Implement this feature without material design
        }
        setContentView(R.layout.activity_main);
        setupWindowAnimations();

        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);

        initNavDrawer();
        initMainContainer();

/*
        mFabButton = findViewById(R.id.fab_button);
        mHeader = findViewById(R.id.activity_transition_header);

        Slide slideExitTransition = new Slide(Gravity.BOTTOM);
        slideExitTransition.excludeTarget(android.R.id.navigationBarBackground, true);
        slideExitTransition.excludeTarget(android.R.id.statusBarBackground, true);
        slideExitTransition.excludeTarget(R.id.activity_transition_header, true);
        getWindow().setExitTransition(slideExitTransition);*/
    }
    private void setupWindowAnimations() {
        Explode explode = new Explode();
        explode.setDuration(10000);
        getWindow().setExitTransition(explode);

        Fade fade = new Fade();
        getWindow().setReenterTransition(fade);
    }

    public void cardLogInClick(View view) {
        openLoginAccountDialogBox(false);
    }
    public void cardCreateAccountClick(View view) { openLoginAccountDialogBox(true); }
    public void cardKioskClick(View view) {Toast.makeText(this, "Find Kiosk", Toast.LENGTH_SHORT).show(); }
    public void cardKeyClick(View view) {
        Toast.makeText(this, "Card", Toast.LENGTH_SHORT).show();
    }

    private void openLoginAccountDialogBox(boolean isCreateAccount) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptView = layoutInflater.inflate(R.layout.dialog_login, null);
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        String positiveButtonText;
        if( isCreateAccount ) {
            alert.setTitle("Create account");
            positiveButtonText = "CREATE ACCOUNT";
        } else {
            alert.setTitle("Login");
            positiveButtonText = "LOGIN";
        }
        alert.setView(promptView);

        final EditText email = (EditText) promptView.findViewById(R.id.edittext_login_email);
        final EditText password = (EditText) promptView.findViewById(R.id.edittext_login_password);

        email.requestFocus();

        password.requestFocus();

        alert.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                // Do something with value!
                Toast.makeText(getApplicationContext(),"Login: email:" + emailText + " pass:"+passwordText, Toast.LENGTH_SHORT).show();


                }

        });

        alert.setNegativeButton("CANCEL",
            new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            // Canceled.
            Toast.makeText(getApplicationContext(),
                    "Cancel Clicked", Toast.LENGTH_SHORT).show();

            }
        });

        // create an alert dialog
        AlertDialog alert1 = alert.create();
        alert1.show();
    }

    /* **************************************************************************
       **************************************************************************
       **************************************************************************
       *                         Navigation Drawer
       **************************************************************************
       **************************************************************************
       **************************************************************************
    */
    private void initNavDrawer() {
        rvNavDrawer = (RecyclerView) findViewById(R.id.rv_main_nav);                                              // Assigning the RecyclerView Object to the xml View
        rvNavDrawer.setHasFixedSize(true);                                                                         // Letting the system know that the list objects are of fixed size
        navDrawerAdapter = new DrawerAdapter(getResources().getStringArray(R.array.drawer_list),ICONS,NAME,EMAIL);  // Creating the Adapter of adapter class(which we are going to see in a bit)
        rvNavDrawer.setAdapter(navDrawerAdapter);                                                                   // Setting the adapter to RecyclerView
        final GestureDetector mGestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
        rvNavDrawer.addOnItemTouchListener(new OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if(child!=null && mGestureDetector.onTouchEvent(motionEvent)){
                    Drawer.closeDrawers();
                    selectNavDrawerItem(recyclerView.getChildPosition(child));
                    return true;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        navDrawerLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager
        rvNavDrawer.setLayoutManager(navDrawerLayoutManager);                 // Setting the layout Manager
        Drawer = (DrawerLayout) findViewById(R.id.dl_main);
        mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,mToolbar,R.string.drawer_open,R.string.drawer_closed){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);                // code here will execute once the drawer is opened
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);                // Code here will execute once drawer is closed
            }
        };
        // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();               // Finally we set the drawer toggle sync State
    }

    public void selectNavDrawerItem(int position) {
        Toast.makeText(MainActivity.this, "The Item Clicked is: " + position, Toast.LENGTH_SHORT).show();
        Intent intent;
        switch(position) {
            case 0: // Acount header
                break;
            case 1: // Your keys
                break;
            case 2: // Find a kiosk or Locksmith
                intent = new Intent(this, MapActivity.class);
                startActivity(intent);
                break;
            case 3: // Store
                intent = new Intent(this, StoreActivity.class);
                startActivity(intent);
                break;
            case 4: // Your Cart
                break;
            case 5: // Your Account
                break;
            case 6: // Settings
                break;
            case 7: // Contact us
                break;
        }
    }
    /* **************************************************************************
       **************************************************************************
       **************************************************************************
       *                           Toolbar
       **************************************************************************
       **************************************************************************
       **************************************************************************
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*  **************************************************************************
        **************************************************************************
        **************************************************************************
        *                       Main Container
        **************************************************************************
        **************************************************************************
        **************************************************************************
     */
    public void initMainContainer() {
        rvMainContainer = (RecyclerView)findViewById(R.id.rv_main_container);
        rvMainContainer.setHasFixedSize(true);
        mainContainerAdapter = new KeyChainAdapter(KEY_NAMES, KEY_IMAGES, KEY_CARD_COLORS);
        rvMainContainer.setAdapter(mainContainerAdapter);

        mainContainerLayoutManager = new LinearLayoutManager(this);
        rvMainContainer.setLayoutManager( mainContainerLayoutManager);
    }

    public void onFabPressed(View view) {

        //Intent i  = new Intent (TransitionFirstActivity.this, TransitionSecondActivity.class);

        //ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(TransitionFirstActivity.this, Pair.create(mFabButton, "fab"), Pair.create(mHeader, "holder1"));

        //startActivity(i, transitionActivityOptions.toBundle());
    }
}
