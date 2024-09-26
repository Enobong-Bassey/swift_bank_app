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
    private fun signupUser(custname : String, custemail : String, username: String, password: String) {
        databaseReference.orderByChild("username").equalTo(username)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        // Username already exists
                        Toast.makeText(this@SignupActivity, "Username already exists", Toast.LENGTH_SHORT).show()
                    } else {
                        // Proceed with saving the new user to the database
                        val userId = databaseReference.push().key // Generate unique ID
                        val user = UserData(userId, custname, custemail, username, password) // Create UserData object

                        userId?.let {
                            databaseReference.child(it).setValue(user)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(this@SignupActivity, "Signup Successful", Toast.LENGTH_SHORT).show()
                                        startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
                                        finish()
                                    } else {
                                        Toast.makeText(this@SignupActivity, "Signup Failed", Toast.LENGTH_SHORT).show()
                                    }
                                }
                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Toast.makeText(this@SignupActivity, "Database Error: ${databaseError.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }
}