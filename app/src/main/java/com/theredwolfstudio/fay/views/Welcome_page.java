package com.theredwolfstudio.fay.views;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.Window;
import android.view.WindowManager;

import com.theredwolfstudio.fay.MainActivity;
import com.theredwolfstudio.fay.R;


/**
 * Created by Juan on 16/1/18.
 */

public class Welcome_page extends Activity {
    /**
     * Variable that will reference the TextView containing the welcome message.
     */
    private TextView welcome;
    /**
     * Variable that will reference the TextView containing the user's name.
     */
    private TextView name;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            super.onCreate(savedInstanceState);

                // As a wellcome page, we don't want it to have the title. It makes de welcome page
            this.requestWindowFeature(Window.FEATURE_NO_TITLE); //The feature 'no title' is requested
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //The current window gets flagged as 'fullscreen'
                //The welcome_page will be inflated, adding all top-level views to the activity.
            setContentView(R.layout.welcome_page);
                //Class variables are linked to their equivalents on the xml page.
            welcome = findViewById(R.id.wellcomeMessage); //TextView with the welcome message.
            name = findViewById(R.id.nameField);    //TextView with the name 'field'.

                //Since the text will be fading in, the initial state of these will be `Invisible`.
            welcome.setVisibility(View.INVISIBLE);  //Welcome message hidden, ready to fade in.
            name.setVisibility(View.INVISIBLE); //Name `field` hidden, ready to fade in.
                //Finally, we launch an asynchronous task that will animate the text
            new InitialMessage().execute();

        }

        /**
         * This is a private class that implements the text animations.
         *
         * It's a very basic version, but it manages the timing of the animations and generates
         * the next activity after the animation is done.
         *
         *
         * `InitialMessage` is the core of the `Loading Screen`
         */
        private class InitialMessage extends AsyncTask<Void, Integer, Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(1000); // One second of no text.
                    publishProgress(0); // Initiates the first animation.
                    Thread.sleep(2000); // Two seconds before the second animation starts. It may begin before the first one starts.
                    publishProgress(1); // Second animation started.
                    Thread.sleep(4000); // Final four seconds to execute the second animation plus some time to read the complete message.

                    if (isCancelled()) return null; // Escape early if cancel() is called

                } catch(InterruptedException e){
                    e.printStackTrace(); //If the sleep method throws an exception, it's caught and registered on log
                }
                return null;
            }

            @Override
            protected void onPreExecute() {/*Nothing is executed before the AsyncTask*/}

            @Override
            protected void onProgressUpdate(Integer... voids) {

                final Animation in = new AlphaAnimation(0.0f, 1.0f); //the animation is declared, specifying the initial and final opacity.
                in.setDuration(3000); // animation duration specified on millis

                switch (voids[0]){ //In this implementation, the only useful parameter is the first one
                    case 0: { //First animation. An enum is needed to replace the meaningless numbers.
                        welcome.startAnimation(in); //The animation for the welcome starts.
                        welcome.setVisibility(View.VISIBLE); //Since the TextView was invisible, now is the moment to make it visible.
                    } break;
                    case 1: { //Same as before, but for the second animation on the name textView
                        name.startAnimation(in);
                        name.setVisibility(View.VISIBLE);
                    } break;
                    default: {}
                }
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                //After the main method is executed (doInBackground), a final piece of code is executed. In this case, we 'redirect' to another activity.
                Intent intent = new Intent(Welcome_page.this,MainActivity.class); //The intent is created, preparing the main activity.
                startActivity(intent); //It should be the last line of code executed on this class. It starts the next activity. in this case, the main one.
            }
        }

}