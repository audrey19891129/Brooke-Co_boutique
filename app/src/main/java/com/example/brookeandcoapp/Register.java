package com.example.brookeandcoapp;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.brookeandcoapp.Interface.SoapRequest;
import com.example.brookeandcoapp.model.Address;
import com.example.brookeandcoapp.model.Client;

import org.ksoap2.serialization.SoapObject;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class Register extends Fragment {
    EditText txtFirstnameForm, txtLastnameForm, txtEmailForm, txtPasswordForm, txtPasswordConfirmForm, txtStreet, txtCity, txtCivic, txtApp, txtZip;
    Spinner spinnerCountry, spinnerProvince;
    Button btnCancelForm, btnRegisterForm;
    AlertDialog alert;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public Register() {

    }

    public static Register newInstance(String param1, String param2) {
        Register fragment = new Register();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        alert = new AlertDialog.Builder(getContext())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("")
                .setNeutralButton("OK", (dialog, which) -> { }).create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        btnCancelForm = view.findViewById(R.id.btnCancelForm);
        btnRegisterForm = view.findViewById(R.id.btnRegisterForm);

        txtFirstnameForm = view.findViewById(R.id.txtFirstnameForm);
        txtLastnameForm = view.findViewById(R.id.txtLastnameForm);
        txtEmailForm = view.findViewById(R.id.txtEmailForm);
        txtPasswordForm = view.findViewById(R.id.txtPasswordForm);
        txtPasswordConfirmForm = view.findViewById(R.id.txtPasswordConfirmForm);
        //
        txtStreet=view.findViewById(R.id.txtStreet);
        txtCivic=view.findViewById(R.id.txtStreetNumber);
        txtApp=view.findViewById(R.id.txtApp);
        txtCity=view.findViewById(R.id.txtCity);
        txtZip = view.findViewById(R.id.txtPostalCode);
        spinnerCountry=view.findViewById(R.id.spinnerCountry);
        spinnerProvince=view.findViewById(R.id.spinnerProvince);



        spinnerCountry = view.findViewById(R.id.spinnerCountry);
        spinnerProvince = view.findViewById(R.id.spinnerProvince);
        DropDownMenu();

        btnRegisterForm.setOnClickListener(v -> {
            if (txtFirstnameForm.getText().toString().isEmpty()) { alert.setMessage("Veuillez remplir le champ pour le Prenom"); alert.show();}
            else if (txtLastnameForm.getText().toString().isEmpty()) { alert.setMessage("Veuillez remplir le champ pour le Nom"); alert.show();}
            else if (txtEmailForm.getText().toString().isEmpty()) { alert.setMessage("Veuillez remplir le champ pour le Courriel"); alert.show();}
            else if (txtPasswordForm.getText().toString().isEmpty()) { alert.setMessage("Veuillez remplir le champ pour le Mot de passe"); alert.show();}
            else if(txtPasswordConfirmForm.getText().toString().isEmpty()){ alert.setMessage("Veuillez remplir le champ pour la Confirmation de votre mot de passe"); alert.show();}
            else if(!txtPasswordForm.getText().toString().equals(txtPasswordConfirmForm.getText().toString())){ alert.setMessage("Votre Mot de pass ne corespond pas avec la confirmation"); alert.show();}
            //
            else if (txtStreet.getText().toString().isEmpty()) { alert.setMessage("Veuillez remplir le champ pour la Rue"); alert.show();}
            else if (txtCivic.getText().toString().isEmpty()) { alert.setMessage("Veuillez remplir le champ pour le numero de rue"); alert.show();}
            else if (txtZip.getText().toString().isEmpty()) { alert.setMessage("Veuillez remplir le champ pour le code postal"); alert.show();}
            else if (txtCity.getText().toString().isEmpty()) { alert.setMessage("Veuillez remplir le champ pour la Ville"); alert.show();}
            else {

                Client client = new Client(0, txtEmailForm.getText().toString(), txtPasswordForm.getText().toString(), txtFirstnameForm.getText().toString(), txtLastnameForm.getText().toString());
                //int id, String country, String province, String city, String street, int civicnumber, String appartment, String zipcode, int clientId
                String app = txtApp.getText().toString();
                if (app.isEmpty()) {
                    app = "none";
                }
                Address address = new Address(0, spinnerCountry.getSelectedItem().toString(), spinnerProvince.getSelectedItem().toString(), txtCity.getText().toString(), txtStreet.getText().toString(), Integer.parseInt(txtCivic.getText().toString()), app, txtZip.getText().toString(), 0);
                try {
                    SoapRequest.register(client, address);
                    Log.e("SUCCESS :", "client created");
                    alert.setMessage("Compte Créer avec success");
                    alert.show();
                    Navigation.findNavController(view).navigate(R.id.action_register_to_login);
                } catch (IOException | XmlPullParserException e) {
                    Log.e("ERROR :", e.getMessage());
                }
            }
        });
        btnCancelForm.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_register_to_login));
        return view;
    }

    public void DropDownMenu(){
        ArrayAdapter<CharSequence> adapterCountry = ArrayAdapter.createFromResource(getContext(), R.array.country, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapterProvince = ArrayAdapter.createFromResource(getContext(), R.array.provinceCanada, android.R.layout.simple_spinner_dropdown_item);
        this.spinnerCountry.setAdapter(adapterCountry);
        this.spinnerProvince.setAdapter(adapterProvince);
        this.spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<CharSequence> adapterProvince;
                if(parent.getItemAtPosition(position).toString().equals("États-Unis")){
                    adapterProvince = ArrayAdapter.createFromResource(getContext(), R.array.provinceEtatsUnis, android.R.layout.simple_spinner_dropdown_item);
                }else{
                    adapterProvince = ArrayAdapter.createFromResource(getContext(), R.array.provinceCanada, android.R.layout.simple_spinner_dropdown_item);
                }
                spinnerProvince.setAdapter(adapterProvince);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){}
        });
    }
}