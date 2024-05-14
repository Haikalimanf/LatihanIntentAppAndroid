package hakif.latihanintentappandroid

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var tvResult: TextView

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result -> if (result.resultCode == MoveForResultActivity.RESULT_CODE && result.data != null) {
        val selectedValue =
            result.data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0)
        tvResult.text = "Hasil : $selectedValue"
    }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonMoveActivity: Button = findViewById(R.id.btn_move_activity)
        buttonMoveActivity.setOnClickListener(this)

        val buttonMoveWithDataActivity: Button = findViewById(R.id.btn_move_activity_data)
        buttonMoveWithDataActivity.setOnClickListener(this)

        val buttonMoveWithObject: Button = findViewById(R.id.btn_move_activity_object)
        buttonMoveWithObject.setOnClickListener(this)

        val buttonDialPhone: Button = findViewById(R.id.btn_dial_number)
        buttonDialPhone.setOnClickListener(this)

        val buttonMoveForResult: Button = findViewById(R.id.btn_move_for_result)
        buttonMoveForResult.setOnClickListener(this)

        tvResult = findViewById(R.id.tv_result)


    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_move_activity -> {
                val moveIntent = Intent(this@MainActivity,MoveActivity::class.java)
                startActivity(moveIntent)
            }

            R.id.btn_move_activity_data -> {
                val moveWithDataIntent = Intent(this@MainActivity, MoveWithDataActivity::class.java)
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME,"DicodingAcademy")
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE,15)
                startActivity(moveWithDataIntent)
            }

            R.id.btn_move_activity_object -> {
                val person = Person("Hakif", 19, "haikalimanf@gmail.com", "Bandung")

                val moveWithObjectActivity = Intent(this@MainActivity, MoveWithObjectActivity::class.java)
                moveWithObjectActivity.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person)
                startActivity(moveWithObjectActivity)
            }

            R.id.btn_dial_number -> {
                val phoneNumber = "10101010101"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }


            R.id.btn_move_for_result -> {
                val moveForResultIntent = Intent(this@MainActivity,MoveForResultActivity::class.java)
                resultLauncher.launch(moveForResultIntent)
            }
        }
    }
}