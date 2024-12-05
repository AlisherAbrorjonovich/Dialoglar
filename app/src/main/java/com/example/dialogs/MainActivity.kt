package com.example.dialogs

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dialogs.databinding.ActivityMainBinding
import com.example.dialogs.databinding.ItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnAlert.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Ogohlantirish")
            dialog.setMessage("Rostdan ham o'chirasizmi?")
            dialog.setPositiveButton("Ha", object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    Toast.makeText(this@MainActivity, "O'chirildi", Toast.LENGTH_SHORT).show()
                }
            })
            dialog.setNegativeButton("Yo'q", object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    Toast.makeText(this@MainActivity, "Bekor qilindi", Toast.LENGTH_SHORT).show()
                }
            })
            dialog.setNeutralButton("Qaytish", object :DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {

                }
            })
            dialog.show()
        }
        binding.btnCustom.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            val itemDialog = ItemBinding.inflate(layoutInflater)

            itemDialog.btnLike.setOnClickListener {
                itemDialog.tv.text = "Raxmat kattakon"
            }
            itemDialog.btnDislike.setOnClickListener {
                itemDialog.imgItem.visibility = View.GONE
            }
            dialog.setView(itemDialog.root)
            dialog.setNeutralButton("Qaytish", object :DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {

                }
            })
            dialog.show()
        }
        binding.btnFragment.setOnClickListener {
            val dialog = MyDialogFragment()
            dialog.show(supportFragmentManager,"Alisher")
        }
        binding.btnDate.setOnClickListener {
            val dialog = DatePickerDialog(this)
            dialog.setOnDateSetListener { view, year, month, dayOfMonth ->
                Toast.makeText(this, "${dayOfMonth}.${month+1}.$year ", Toast.LENGTH_SHORT).show()
            }
            dialog.show()
        }
        binding.btnTime.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                this,
                object : TimePickerDialog.OnTimeSetListener {
                    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                        Toast.makeText(this@MainActivity, "$hourOfDay:$minute", Toast.LENGTH_SHORT).show()
                    }

                },
                24,
                60,
                true
            )
            timePickerDialog.updateTime(12, 50)
            timePickerDialog.show()
        }
        binding.btnBottomSheet.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            val itemDialog = ItemBinding.inflate(layoutInflater)
            dialog.setContentView(itemDialog.root)
            dialog.show()
        }
        binding.btnSnackbar.setOnClickListener {
            val snackbar = Snackbar.make(it, "Salom snackbar", Snackbar.LENGTH_LONG)

            snackbar.setAction("Click", object : View.OnClickListener {
                override fun onClick(v: View?) {
                    Toast.makeText(this@MainActivity, "Click", Toast.LENGTH_SHORT).show()
                }
            })
            snackbar.show()
        }

    }
}