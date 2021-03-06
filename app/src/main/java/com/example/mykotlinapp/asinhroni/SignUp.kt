package com.example.mykotlinapp.asinhroni

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.mykotlinapp.automobili.Auto
import com.example.mykotlinapp.automobili.AutomobiliAdapter
import com.example.mykotlinapp.automobili.MySignUpParams
import com.example.mykotlinapp.automobili.MyTaskParams
import com.example.mykotlinapp.contract.Task
import com.google.gson.Gson
import kotlinx.android.synthetic.main.automobili_content.*
import org.json.JSONObject
import java.io.IOException
import java.net.MalformedURLException

 class SignUpTask internal constructor() : AsyncTask<MySignUpParams, Int, String>() {

     private lateinit var mQueue: RequestQueue

     var gson = Gson()
     lateinit var username: String
     lateinit var prezime: String
     lateinit var ime: String
     lateinit var password: String
     lateinit var ctx:Context
     lateinit var myFilterResponse: Intent
     lateinit  var String_za_slanje: String

     override fun onPreExecute() {
         super.onPreExecute()

         // progressBar.setProgress(0);

     }


     override fun doInBackground(vararg params: MySignUpParams): String? {

         username = params[0].username
        prezime =  params[0].prezime
         ime= params[0].ime
         password = params[0].password
         ctx = params [0].ctx;
         myFilterResponse= params[0].intent


        try{

             mQueue= Volley.newRequestQueue(ctx)


             var url = "http://192.168.1.109:1120/sajam/korisnici?username="+username+"&password="+password+"&ime="+ime+"&prezime="+prezime

             var jsonObjectRequest = JsonObjectRequest(
                 Request.Method.POST, url, null,
                 Response.Listener { response ->
                     var json: JSONObject
                     var string= response.toString()
                   //  Toast.makeText(ctx,string,Toast.LENGTH_SHORT).show()
                     json= JSONObject(string)
                     this.String_za_slanje=json.get("korisnik").toString()

                   //  Toast.makeText(ctx,String_za_slanje,Toast.LENGTH_SHORT).show()
                     Log.e("String za slanjeee",String_za_slanje);
                   System.out.println(String_za_slanje)

                     myFilterResponse.putExtra("serviceDataSignUp", String_za_slanje)

                     ctx.sendBroadcast(myFilterResponse)

                 },
                 Response.ErrorListener { error ->
                     error.printStackTrace()
                 }

             )

             mQueue.add(jsonObjectRequest)
             return null


     } catch ( e:Exception) {
         e.printStackTrace();
     }
     return null
 }


    fun update(string:String)
    {
        this.String_za_slanje=string
    }










     override fun onProgressUpdate(vararg values: Int?) {
         super.onProgressUpdate(*values)


         //   counter++
         //    var gson = Gson()
         //  var editor = getActivity()?.getSharedPreferences("MyPrefsIgor", MODE_PRIVATE)?.edit()

         //   var jsonString = gson.toJson(values)
         // Save the serialized data into a shared preference


         //  editor?.putString(counter.toString(), jsonString)
         //   editor?.apply()


     }


     override fun onPostExecute(result: String?) {
         super.onPostExecute(result)
         if (result=="false")
         {
            Toast.makeText(ctx,"Neusposno",Toast.LENGTH_SHORT).show()
         }
         else (result=="succes")
         {
             Toast.makeText(ctx,"USPESNO",Toast.LENGTH_SHORT).show()
         }
         // list_view.setAdapter(result)


         //   editor?.apply();

         /*  var obj=MainActivityModel()

          var complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "object_prefs", 0);
  complexPreferences.putObject("object_value", obj);
  complexPreferences.commit();

*/
         //   Toast.makeText(context,result.count.toString(),Toast.LENGTH_SHORT).show()
         //list_view.setAdapter(result)
         // Thread.sleep(3000)
         // textBeju.setText("sdsd")
         // var pc =  context.
         //  pc.setText("SSSSSSSSSSSSS")
         /* activity.runOnUiThread(Runnable() {
             fun run() {
               //  pc.setText("SDsd" + " %");
                 Toast.makeText(context?.applicationContext,result.count.toString(),Toast.LENGTH_SHORT).show()

             }
         });
          // Toast.makeText(context,result.count.toString(),Toast.LENGTH_SHORT).show()
       var handler = Handler()
         var bus=TextView(context);
        var data = "hello, world!";
         handler.post( Runnable() {
             bus.post(Runnable()
             {
                 bus.setText(data)
             });
         });*/
         //activity.setContentView(R.layout.automobili_content)
         //context.setContentView(R.layout.main);
         /*   activity.runOnUiThread(Runnable() {
            Toast.makeText(activity, result.count.toString(), Toast.LENGTH_SHORT).show()
            if(result==null)
                textBeju.setText("prazno")
            else
            textBeju.setText("radiii")
        })
        /*   var pc =  TextView(context);
            var t =Thread(){
              fun run() {
                  activity.runOnUiThread( Runnable() {
                        fun run() {
                            // pc.setText("test");
                            Toast.makeText(activity,result.count.toString(),Toast.LENGTH_SHORT).show()
                             pc.setText(result.count.toString())
                         }
                    });
                }
            }
        t.start();
    }*/


    }*/
     }
 }