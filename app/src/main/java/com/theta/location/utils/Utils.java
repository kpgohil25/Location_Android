package com.theta.location.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.theta.location.R;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utils
 * <p>
 * Pruthviraj Gohil
 * <p>
 * Purpose : Define static method and constant filed
 */
public class Utils {

    //Preference Data Store Key
    public static final String IS_LOGIN = "is_login";
    public static final String USER_NAME = "user_name";

    //Validation Pattern
    public static final Pattern hasUppercase = Pattern.compile("[A-Z]");
    public static final Pattern hasLowercase = Pattern.compile("[a-z]");
    public static final Pattern hasNumber = Pattern.compile("\\d");
    public static final Pattern hasSpecialChar = Pattern.compile("[^a-zA-Z0-9 ]");

    //Alert Dialg
    private static Dialog dialog;

    /**
     * Gets the version code of the application. For e.g. 1.0
     **/
    public static String getApplicationVersionCode(Context context) {

        String versionName = null;

        if (context == null) {
            return versionName;
        }

        try {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionName;
    }

    /**
     * Gets the version name of the application. For e.g. 1.0
     **/
    public static int getApplicationVersionNumber(Context context) {

        int versionNumber = -1;

        try {
            versionNumber = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionNumber;
    }

    /**
     * Checks if the input parameter is a valid email.
     *
     * @param email
     * @return
     */
    public static boolean isValidEmail(String email) {

        if (email == null) {
            return false;
        }

        final String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Matcher matcher;
        Pattern pattern = Pattern.compile(emailPattern);

        matcher = pattern.matcher(email);

        if (matcher != null) {
            return matcher.matches();
        } else {
            return false;
        }
    }

    /**
     * Mobile Number Validation
     *
     * @param phone
     * @return
     */
    public static boolean isValidMobile(String phone) {
        boolean check = false;

        String pattern = "^[0-9]{5,15}$";

        if (Pattern.matches(pattern, phone)) {
            if (phone.length() < 5) {
                check = false;
            } else {
                check = true;
            }
        } else {
            check = false;
        }

        return check;
    }

    /**
     * Check Name Validation
     *
     * @param strName
     * @return
     */
    public static boolean isValidName(String strName) {

        Pattern p = Pattern.compile("^[a-zA-Z ]{2,30}$");
        Matcher m = p.matcher(strName);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Validate New Pass
     *
     * @param pass1
     * @return
     */
    public static String validateNewPass(Context context, String pass1) {

        StringBuilder retVal = new StringBuilder();
        if (pass1.equals("")) {
            retVal.append(context.getString(R.string.txt_pass_1) + "\n");
            return retVal.toString();
        } else {

            if (!hasUppercase.matcher(pass1).find() || !hasLowercase.matcher(pass1).find() || !hasNumber.matcher(pass1).find() || !hasSpecialChar.matcher(pass1).find()) {

                retVal.append(context.getString(R.string.txt_pass_2));
                if (!hasUppercase.matcher(pass1).find())
                    retVal.append(" " + context.getString(R.string.txt_pass_3));

                if (!hasLowercase.matcher(pass1).find())
                    retVal.append(" " + context.getString(R.string.txt_pass_4));

                if (!hasNumber.matcher(pass1).find())
                    retVal.append(" " + context.getString(R.string.txt_pass_5));

                if (!hasSpecialChar.matcher(pass1).find())
                    retVal.append(" " + context.getString(R.string.txt_pass_6));

                retVal.append("\n" + context.getString(R.string.txt_pass_7));

                return retVal.toString();
            } else if (pass1.length() < 8) {
                retVal.append(context.getString(R.string.txt_pass_8) + "\n");
                return retVal.toString();
            } else if (pass1.contains(" ")) {
                retVal.append(context.getString(R.string.txt_pass_9) + "\n");
                return retVal.toString();
            } else {
                return "";
            }
        }
    }

    /**
     * Shows the message passed in the parameter in the Toast.
     *
     * @param context the context
     * @param msg     Message to be show in the toast.
     * @return Toast object just shown
     */
    public static void showToast(final Context context, CharSequence msg, boolean isHelp) {

        try {

            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.custom_message_alert_dialog_layout);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setCancelable(false);

            //Set Bottom Screen
            WindowManager.LayoutParams wlp = dialog.getWindow().getAttributes();
            wlp.gravity = Gravity.BOTTOM;
            wlp.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            dialog.getWindow().setAttributes(wlp);

            dialog.show();

            TextView txtYes = dialog.findViewById(R.id.txtYes);
            TextView txtMessage = dialog.findViewById(R.id.txtMessage);
            txtMessage.setText(msg);

            TextView txtNeedHelp = dialog.findViewById(R.id.txtNeedHelp);
            View view_divi = dialog.findViewById(R.id.view_divi);

            txtYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            if (isHelp) {
                txtNeedHelp.setVisibility(View.VISIBLE);
                view_divi.setVisibility(View.VISIBLE);
            } else {
                txtNeedHelp.setVisibility(View.GONE);
                view_divi.setVisibility(View.GONE);
            }

            txtNeedHelp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Open Progress Dialog
     *
     * @param context
     */
    public static void openDialog(Context context) {

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false);

        if (context != null && dialog != null && !dialog.isShowing())
            dialog.show();
    }

    /**
     * Close Dialog
     */
    public static void closeDialog() {

        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

    /**
     * Get 6 Digit After Point
     *
     * @param val
     * @return
     */
    public static String getDecimalValue(double val) {
        String value = new DecimalFormat("##.######").format(val);

        return value;
    }

    /**
     * Permission
     *
     * @param context
     * @param permissions
     * @return
     */
    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}