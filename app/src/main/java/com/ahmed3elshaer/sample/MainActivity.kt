package com.ahmed3elshaer.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmed3elshaer.selectionbottomsheet.SelectionBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            renderSelectionSheet()
        }
    }

    private fun renderSelectionSheet() {
        //prepare list of your models
        val selectionList =
            mutableListOf(
                SelectionModel("Selection 1", "1"),
                SelectionModel("Selection 2", "2"),
                SelectionModel("Selection 3", "3"),
                SelectionModel("Selection 4", "4")
            )
        //start using the Builder for the BottomSheet
        //You should pass your type of model to the Builder Class first
        //And your activity reference
        SelectionBuilder<SelectionModel>(this)
            //passing the list of models that gonna be shown as the selection list
            .list(selectionList)
            //set the title for the BottomSheet
            .title("Title")
            //this is a lambda expression with the model type that you passed for the Builder
            //and it's required for specifying(returning) which property of your model
            //would be shown as a text for Selections
            .itemBinder { model ->
                model.title
            }
            //shows the BottomSheet and returns a lambda callback that triggers
            //when user selects any item with selected model
            .show {selectedModel ->

            }

    }

    data class SelectionModel(val title: String, val id: String)


}
