package ca.on.sudbury.hojat.smartcamera

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ca.on.sudbury.hojat.smartcamera.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Asking for permissions
        if (allPermissionsGranted()) {
            Toast.makeText(this, "we have permissions", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(
                this,
                Constants.REQUIRED_PERMISSIONS,
                Constants.REQUEST_CODE_PERMISSIONS
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                // our code
            } else {
                Toast.makeText(this, "Permissions not granted by the user", Toast.LENGTH_SHORT)
                    .show()
                finish()
            }
        }

    }

    private fun allPermissionsGranted() = Constants.REQUIRED_PERMISSIONS.all { permission ->
        ContextCompat.checkSelfPermission(
            baseContext, permission
        ) == PackageManager.PERMISSION_GRANTED

    }
}