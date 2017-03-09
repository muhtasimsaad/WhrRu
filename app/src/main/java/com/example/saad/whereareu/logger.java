package com.example.saad.whereareu;

        import android.app.Activity;
        import android.app.AlertDialog;
        import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.AsyncTask;

        import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.OutputStream;
        import java.io.OutputStreamWriter;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.net.URLEncoder;

public class logger extends AsyncTask<String,Void,String> {
    String temp="";
    String flag="";
    private Activity activity;
    private ProgressDialog dialog;
    private Context context;
    AlertDialog alertDialog;
    Context contex;
    logger(Context cxt,Activity activity,String asd){
        contex=cxt;

    }


    @Override
    protected String doInBackground(String... params) {

        String login_url = "https://tbone.000webhostapp.com/login.php";

        try {


            //conditions here

            flag=params[0];
            String user_name = params[1];
            String password = params[2];

            if(flag.equals("dlFriendList")){

                login_url = "http://203.76.221.58/downloadfriendlist.php";}
            if(flag.equals("dlMarkers")){

                login_url = "http://203.76.221.58/downloadlocations.php";}
            if(flag.equals("updateLocation")){

                login_url = "http://203.76.221.58/updateLocation.php";}
            if(flag.equals("dlAll")){

                login_url = "http://203.76.221.58/downloadAllPeople.php";}


            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));





            //conditions here

            if(flag.equals("login"))
            {
                String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
            }
/*
            if(flag.equals("dlFriendList")){



                String post_data = URLEncoder.encode("userID", "UTF-8") + "=" + URLEncoder.encode(params[1]+"", "UTF-8") ;
                bufferedWriter.write(post_data);


            }
            if(flag.equals("dlMarkers")){



                String post_data = URLEncoder.encode("ids", "UTF-8") + "=" + URLEncoder.encode(params[1]+"", "UTF-8") ;
                bufferedWriter.write(post_data);


            }


            if(flag.equals("updateLocation")){


                String post_data =URLEncoder.encode("userID", "UTF-8") + "=" + URLEncoder.encode(MainActivity.userID+"", "UTF-8") + "&"
                        +
                        URLEncoder.encode("lattitude", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&"
                        + URLEncoder.encode("longitude", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);


                //    if(params[2].length()>3 && params[1].length()>3){Intent myIntent = new Intent(contex, dummy.class);
                //    contex.startActivity(myIntent);}


            }
*/

            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            String result = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;temp+=line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if(flag.equals("login"))
        {
            alertDialog = new AlertDialog.Builder(contex).create();
            alertDialog.setTitle("login Status");}



    }

    @Override
    protected void onPostExecute(String result) {


        //conditions here


        // prasing the value came in
        if (flag.equals("login")) {
            try {
                int c = result.indexOf(",");
                LoginActivity.userID = Integer.parseInt(result.substring(0, c));
                AlertDialog.Builder builder = new AlertDialog.Builder(contex);
                builder.setTitle("login Status");
                builder.setMessage("Logged in");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent myIntent = new Intent(contex, MapsActivity.class);
                        contex.startActivity(myIntent);


                    }
                });
                builder.show();
            } catch (Exception rr) {
                AlertDialog.Builder builder = new AlertDialog.Builder(contex);
                builder.setTitle("login Status");
                builder.setMessage(temp);
                builder.setPositiveButton("Retry", null);
                builder.setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                });
                builder.show();

            }
        }
/*
        if (flag.equals("dlFriendList")) {
            dummy.frndlst=result;
            if(result.length()>0){MainActivity.ialog.dismiss();}
        }
        if (flag.equals("dlMarkers")) {
            dummy.markers=result;
            if(result.length()>0){MainActivity.pdMarkers.dismiss();}
        }
        if (flag.equals("dlAll")) {
            // dummy.markers=result;
            Accounts.name=result;
            Accounts.pdMarkers.dismiss();
        }
*/
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

