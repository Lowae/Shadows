package com.lowae.shadows

import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.lowae.shadows.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val shadowLayout = binding.shadowLayout
        binding.shadowRadius.apply {
            seekbar.progress = 20
            seekbar.max = 32
            current.text = "ShadowRadius: ${seekbar.progress}"
            seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    current.text = "ShadowRadius: $progress"
                    shadowLayout.updateShadowRadius(progress.pxF)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }

            })
            max.text = seekbar.max.toString()
        }

        binding.shadowCorner.apply {
            seekbar.progress = 0
            seekbar.max = 50
            current.text = "ShadowCorner: ${seekbar.progress}"
            seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    current.text = "ShadowCorner: $progress"
                    shadowLayout.updateShadowCorners(progress.pxF)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }

            })
            max.text = seekbar.max.toString()
        }
        binding.shadowDx.apply {
            seekbar.progress = 25
            seekbar.max = 50
            current.text = "ShadowDx: ${shadowLayout.shadowSpec.shadowDX}"
            seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    val dx = (progress - seekBar!!.max / 2)
                    current.text = "ShadowDx: $dx"
                    shadowLayout.updateShadowOffsetX(dx.pxF)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }

            })
            max.text = seekbar.max.toString()
        }

        binding.shadowDy.apply {
            seekbar.progress = 25
            seekbar.max = 50
            current.text = "ShadowDx: ${shadowLayout.shadowSpec.shadowDY}"
            seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    val dy = (progress - seekBar!!.max / 2)
                    current.text = "ShadowDy: $dy"
                    shadowLayout.updateShadowOffsetY(dy.pxF)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }

            })
            max.text = seekbar.max.toString()
        }
    }
}