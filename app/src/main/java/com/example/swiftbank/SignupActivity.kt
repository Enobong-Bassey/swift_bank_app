package com.example.swiftbank

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.example.swiftbank.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Database
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("user")

        binding.signupButton.setOnClickListener {
            val signupCustName = binding.signupName.text.toString()
            val signupCustEmail = binding.signupEmail.text.toString()
            val signupUsername = binding.signupUsername.text.toString()
            val signupPassword = binding.signupPassword.text.toString()

            if (signupCustName.isNotEmpty() && signupCustEmail.isNotEmpty() && signupUsername.isNotEmpty() && signupPassword.isNotEmpty()) {
                signupUser(signupCustName, signupCustEmail, signupUsername, signupPassword)
            } else {
                Toast.makeText(this@SignupActivity, "All fields are mandatory", Toast.LENGTH_SHORT).show()
            }
        }

        binding.loginRedirectText.setOnClickListener {
            startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
            finish()
        }
    }

    // Function to handle user signup
    private fun signupUser(custemail : String, custname : String, username: String, password: String) {
        databaseReference.orderByChild("username").equalTo(username)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (!dataSnapshot.exists()) {

                        val id = databaseReference.push().key // Generate unique ID
                        val userData = UserData(id, custemail, custname, password, username)
                        databaseReference.child(id!!).setValue(userData)
                        Toast.makeText(this@SignupActivity, "Signup Successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
                        finish()
                        // Username already exists

                    } else {
                        Toast.makeText(this@SignupActivity, "User already exists", Toast.LENGTH_SHORT).show()
                        // Proceed with saving the new user to the database
                         // Create UserData object
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Toast.makeText(this@SignupActivity, "Database Error: ${databaseError.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }
}