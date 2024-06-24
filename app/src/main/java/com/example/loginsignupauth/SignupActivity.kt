package com.example.loginsignupauth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginsignupauth.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySignupBinding
    private lateinit var firebaseAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth=FirebaseAuth.getInstance()

        binding.signupButton.setOnClickListener{
            val email= binding.signupemail.text.toString()
            val password= binding.signupPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this){task ->
                        if (task.isSuccessful){
                            Toast.makeText(this,"Signup Successful",Toast.LENGTH_LONG).show()
                            val intent=Intent(this,LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this,"Signup Unsuccessful",Toast.LENGTH_LONG).show()

                        }
                    }
            }else{
                Toast.makeText(this,"Enter email and password",Toast.LENGTH_LONG).show()

            }
        }
        binding.loginText.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }


    }
}