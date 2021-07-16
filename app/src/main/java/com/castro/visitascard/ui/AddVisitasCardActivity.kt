package com.castro.visitascard.ui


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.telephony.PhoneNumberFormattingTextWatcher
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.castro.visitascard.App
import com.castro.visitascard.R
import com.castro.visitascard.data.VisitasCard
import com.castro.visitascard.databinding.ActivityAddVisitasCardBinding
import com.castro.visitascard.util.MaskEditUtil
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.imagepicker.ImagePicker


class AddVisitasCardActivity : AppCompatActivity() {
    private val binding by lazy{ ActivityAddVisitasCardBinding.inflate(layoutInflater)}
    var profile = ""
    private val mainViewModel: MainViewModel by viewModels{
        MainViewModelFactory((application as App).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners(){
        var colorBackground = "#9E9E9E"

        binding.btClose.setOnClickListener {
            finish()
        }

        binding.btConfirm.setOnClickListener {
            if(checkFields()) {
                val visitasCard = VisitasCard(
                    name = binding.tilName?.editText?.text.toString(),
                    empresa = binding.tilEmpresa.editText?.text.toString(),
                    telefone = binding.tilTelefone.editText?.text.toString(),
                    email = binding.tilEmail.editText?.text.toString(),
                    background = colorBackground,
                    profile = profile

                )
                mainViewModel.insert(visitasCard)
                Toast.makeText(this, R.string.label_show_success, Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        binding.btColorPicker.setOnClickListener {
            MaterialColorPickerDialog
                .Builder(this!!)
                .setTitle("Selecione uma cor")
                .setDefaultColor(R.color.grey_500)
                .setColorRes(resources.getIntArray(R.array.themeColors))
                .setColorListener { color, colorHex ->
                    // Handle Color Selection
                    binding.btColorPicker.setBackgroundColor(color)
                    colorBackground = colorHex
                }
                .show()


        }
        binding.tilTelefone.editText?.addTextChangedListener(MaskEditUtil.mask(binding.tilTelefone.editText!!, MaskEditUtil.FORMAT_FONE));


        binding.imvProfilePreview.setOnClickListener{
            ImagePicker.with(this)
                .saveDir(getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!)
                .start()	//Default Request Code is ImagePicker.REQUEST_CODE

        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val uri: Uri = data?.data!!

            // Use Uri object instead of File to avoid storage permissions
           // Log.d("TAG", "${uri.toString()}")
            profile = uri.toString()
            binding.imvProfilePreview.setImageURI(uri)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
    private fun checkFields(): Boolean{
        if(binding.tilName.editText?.text.toString().length == 0){
            binding.tilName.error = getString(R.string.emptyName)
            return false
        }
        if(binding.tilTelefone.editText?.text.toString().length < 15){
            binding.tilTelefone.error = getString(R.string.emptyTelefone)
            return false
        }
        if(binding.tilEmpresa.editText?.text.toString().length == 0){
            binding.tilEmpresa.error = getString(R.string.emptyEmpresa)
            return false
        }
        if(binding.tilEmail.editText?.text.toString().length == 0){
            binding.tilEmail.error = getString(R.string.emptyEmail)
            return false
        }
        return true

    }
}