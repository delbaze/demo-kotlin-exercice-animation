package com.david.demo_fevrier_2023_student

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager


class MainActivity : AppCompatActivity() {
    private lateinit var logger: Logger
    private lateinit var root: ConstraintLayout
    private lateinit var menuButton: ImageButton
    private lateinit var movieDetails: ImageButton
    private lateinit var status: TextView
    private lateinit var movieTitle: TextView
    private lateinit var movieDesc: TextView
    private lateinit var movieRating: TextView

    private var isOnDetails: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.exo_advanced_0)
        logger = LogManager.getLogger(this)


        root = findViewById(R.id.root)
        menuButton = findViewById(R.id.menu_button)
        status =  findViewById(R.id.status)
        movieTitle = findViewById(R.id.movie_title)
        movieDesc = findViewById(R.id.desc)
        movieRating = findViewById(R.id.rating)
        movieDetails = findViewById(R.id.imageButton)


        val initialConstraint = ConstraintSet()
        initialConstraint.clone(root)

        val coverConstraint = ConstraintSet()
        coverConstraint.clone(this, R.layout.exo_advanced_1)

        val anim = ValueAnimator()
        anim.setIntValues(Color.BLACK, Color.WHITE)
        anim.setEvaluator(ArgbEvaluator())
        anim.addUpdateListener {
            menuButton.setColorFilter(it.animatedValue as Int)
            movieDetails.setColorFilter(it.animatedValue as Int)
            status.setTextColor(it.animatedValue as Int)
            movieTitle.setTextColor(it.animatedValue as Int)
            movieDesc.setTextColor(it.animatedValue as Int)
            movieRating.setTextColor(it.animatedValue as Int)
        }

        anim.duration = 600

        menuButton.setOnClickListener {
            startTransition(initialConstraint, coverConstraint, anim)
        }
    }

    private fun startTransition(initialConstraint: ConstraintSet, coverConstraint: ConstraintSet, anim: ValueAnimator){
        TransitionManager.beginDelayedTransition(root)

        if (isOnDetails){
            initialConstraint.applyTo(root)
            anim.start()
        }else {
            coverConstraint.applyTo(root)
            anim.reverse()

        }
        isOnDetails = !isOnDetails
    }
}