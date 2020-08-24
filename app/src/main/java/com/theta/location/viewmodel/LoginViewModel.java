package com.theta.location.viewmodel;

import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;

import androidx.lifecycle.ViewModel;

import com.theta.location.R;
import com.theta.location.app.AppClass;
import com.theta.location.interfaces.LoginAuthentication;
import com.theta.location.utils.Utils;

/**
 * Login View Model
 * <p>
 * Pruthviraj Gohil
 * <p>
 * Purpose :- Bind Data and Perform Login Here
 */
public class LoginViewModel extends ViewModel {

    public String email = "", password = "";
    public LoginAuthentication loginAuthentication;
    private boolean visibility_status = false;

    public void onAuthChecking() {

        if (email.equalsIgnoreCase("")) {
            loginAuthentication.onFail(AppClass.context.getResources().getString(R.string.email_require_msg));
        } else if (!Utils.isValidEmail(email)) {
            loginAuthentication.onFail(AppClass.context.getResources().getString(R.string.email_not_valid_format_msg));
        } else if (password.equalsIgnoreCase("")) {
            loginAuthentication.onFail(AppClass.context.getResources().getString(R.string.password_require_msg));
        } else if (password.length() < 8 || password.length() > 14) {
            loginAuthentication.onFail(AppClass.context.getResources().getString(R.string.password_validation_error_msg));
        } else if (Utils.validateNewPass(AppClass.context, password).length() != 0) {
            loginAuthentication.onFail(Utils.validateNewPass(AppClass.context, password));
        } else if (email.equals("maitrey@thetatechnolabs.com") && password.equals("Maitrey@123")) {
            //Success
            loginAuthentication.onSuccess();
        } else {
            //Login Fail
            loginAuthentication.onFail(AppClass.context.getResources().getString(R.string.email_and_password_not_valid));
        }
    }
}
