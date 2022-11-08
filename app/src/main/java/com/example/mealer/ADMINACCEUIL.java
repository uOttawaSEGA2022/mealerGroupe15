package com.example.mealer;

import static com.example.mealer.MainActivity.admin;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mealer.databinding.ActivityAdminacceuilBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ADMINACCEUIL extends AppCompatActivity {

    private ActivityAdminacceuilBinding binding;
    private Button disconnectAdBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAdminacceuilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.lesplaintes)
                .build();


        NavController navController = getNavController();
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_adminacceuil);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    private NavController getNavController() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_adminacceuil);
        if (!(fragment instanceof NavHostFragment)) {
            throw new IllegalStateException("Activity " + this
                    + " does not have a NavHostFragment");
        }
        return ((NavHostFragment) fragment).getNavController();
    }
    public void OnAdDeconnecter(View view) {
        admin.disconnect();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent, 0);
    }


    private void showUpdateDeleteDialog() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_dialogue, null);
        dialogBuilder.setView(dialogView);


        final Button button1 = (Button) dialogView.findViewById(R.id.button);
        final Button button2 = (Button) dialogView.findViewById(R.id.button2);
        final Button button3 = (Button) dialogView.findViewById(R.id.button3);
        final Button button4 = (Button) dialogView.findViewById(R.id.datePickerButton);

        ;
        final AlertDialog b = dialogBuilder.create();
        b.show();
    }
}