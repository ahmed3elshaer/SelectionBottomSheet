package com.ahmed3elshaer.selectionbottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.selection_bottom_sheet.*
import kotlinx.android.synthetic.main.single_choice_item.view.*

class SelectionBuilder<T>(private val activity: AppCompatActivity) {
    private var itemsList: List<T> = mutableListOf()
    private var title: String = ""
    private var selectionItemBinder: (item: T) -> String = { "" }
    private var callback: (item: T) -> Unit = {}

    /**
     * Call to pass the list of models that gonna be shown in the
     * RecyclerView.  it's implementation helps us use whatever model,
     * we want so we can get the same model on the selection callback.
     * @param itemsList sets the list of the models to the RecyclerView
     */
    fun list(itemsList: List<T>):SelectionBuilder<T> {
        this.itemsList = itemsList
        return this
    }

    /**
     * Call to pass title of the bottom sheet
     * @param title bottom sheet title
     */
    fun title(title: String) :SelectionBuilder<T> {
        this.title = title
        return this
    }
    /**
     * Call to determine which property of the model is gonna
     * be shown in the RecyclerView's item TextView
     * by passing the Model itself to you so you can just return the String type
     * property that your models hold to be shown in the selection
     * @param selectionItemBinder lambda expression has your model
     * as a param so you can specify the string property on it as a return type.
     */
    fun itemBinder(selectionItemBinder: (item: T) -> String):SelectionBuilder<T> {
        this.selectionItemBinder = selectionItemBinder
        return this
    }

    /**
     * Call to initialize and show BottomSheetDialogFragment
     * @param tag is optional param, if you are not gonna use the tag eg. for
     * reusing the BottomSheet again you can skip it, and a random String would
     * be set
     * @param callback is a lambda expression that would trigger if user selected
     * any item of the RecyclerView with the selected item model as a param
     */
    fun show( tag: String = getRandomString(4),callback: (item: T ) -> Unit):BottomSheetDialogFragment {
        this.callback = callback
        val dialog = SelectionBottomSheet(this)
        dialog.show(activity.supportFragmentManager, tag)
       return dialog
    }


     class SelectionBottomSheet<T>(
        val builder: SelectionBuilder<T>
    ) : BottomSheetDialogFragment() {


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            return inflater.inflate(R.layout.selection_bottom_sheet, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            rvSingleChoice.layoutManager = LinearLayoutManager(activity)
            rvSingleChoice.itemAnimator = DefaultItemAnimator()
            rvSingleChoice.addItemDecoration(
                DefaultDividerItemDecoration(
                    activity,
                    DividerItemDecoration.VERTICAL,
                    36
                )
            )
            val adapter = object : SelectionAdapter() {
                override fun onBindData(item: T, itemView: TextView) {
                    itemView.text = builder.selectionItemBinder(item)
                }

            }
            tvTitle.text = builder.title
            rvSingleChoice.adapter = adapter
            bConfirm.setOnClickListener {
                dismiss()
            }


        }

        abstract inner class SelectionAdapter :
            RecyclerView.Adapter<SelectionAdapter.VisaTypesViewHolder>() {
            private var lastSelectionView: View? = null

            inner class VisaTypesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
                fun bindData(item: T) {
                    onBindData(item, itemView.tvName)
                }

                init {

                    view.setOnClickListener {
                        lastSelectionView?.let {
                            lastSelectionView!!.tvName.setTextColor(context!!.getColorCompat(R.color.colorBlack))
                            lastSelectionView!!.ivSelected.hide()
                        }
                        builder.callback(builder.itemsList[adapterPosition])
                        context?.let {
                            view.tvName.setTextColor(context!!.getColorCompat(R.color.colorGreen))
                            view.ivSelected.show()
                            lastSelectionView = view
                        }

                    }
                }
            }

            abstract fun onBindData(item: T, itemView: TextView)


            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisaTypesViewHolder {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.single_choice_item, parent, false)

                return VisaTypesViewHolder(itemView)
            }

            override fun onBindViewHolder(holder: VisaTypesViewHolder, position: Int) {
                holder.bindData(builder.itemsList[position])

            }

            override fun getItemCount(): Int {
                return builder.itemsList.size
            }


        }

    }
}