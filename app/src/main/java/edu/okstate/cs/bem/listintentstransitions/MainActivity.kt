package edu.okstate.cs.bem.listintentstransitions

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import edu.okstate.cs.bem.listintentstransitions.Model.ListItem
import edu.okstate.cs.bem.listintentstransitions.Model.Separator
import edu.okstate.cs.bem.listintentstransitions.Model.State
import edu.okstate.cs.bem.listintentstransitions.Util.Adapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog.view.*
import org.jetbrains.anko.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val listItems: ArrayList<ListItem> = ArrayList()

    private var score = 0

    lateinit var dialog: DialogInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initList()

        btnResetGame.setOnClickListener {
            showResetGameDialog()
        }
    }

    // create and show the RecyclerView
    private fun initList() {

        fillList()

        rv.layoutManager = LinearLayoutManager(this)

        // keep RecyclerView from recycling the state views
        rv.recycledViewPool.setMaxRecycledViews(ListItem.TYPE_STATE, 0)

        rv.adapter = Adapter(listItems, this)
    }

    // called in adapter when a state list item is clicked
    fun handleStateClick(itemClicked: State, containerView: LinearLayout) {

        if (itemClicked.isScored) {
            showConfirmResetDialog(itemClicked, containerView)
        }
        else {
            // score the item
            containerView.setBackgroundColor(ContextCompat.getColor(this, R.color.isScored))
            itemClicked.isScored = true
            score++
            updateScoreTextView()
        }
    }

    fun showConfirmResetDialog(itemClicked: State, containerView: LinearLayout){
        dialog = alert {

            val v = layoutInflater.inflate(R.layout.dialog, null)


            val r = "Do you want to reset " + itemClicked.stateName + "?"
            v.tvReset.text = r


            v.btnNo.setOnClickListener {
                dialog.dismiss()
            }
            v.btnYes.setOnClickListener {
                dialog.dismiss()
                containerView.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.isNotScored))
                itemClicked.isScored = false
                score--
                updateScoreTextView()
                toast(itemClicked.stateName + " reset")
            }
            customView = v

        }.show()
    }

    fun showResetGameDialog() {
        dialog = alert {

            val v = layoutInflater.inflate(R.layout.dialog, null)


            val r = "Do you want to reset the game?"
            v.tvReset.text = r


            v.btnNo.setOnClickListener {
                dialog.dismiss()
            }
            v.btnYes.setOnClickListener {
                score = 0
                updateScoreTextView()
                for(i in listItems) {
                    i.isScored = false
                }

                rv.adapter = Adapter(listItems, this@MainActivity)
                dialog.dismiss()
                longToast("Game reset")
            }
            customView = v

        }.show()
    }

    private fun updateScoreTextView() {

        val scoreLabel = "Score $score/50"
        tvScore.text = scoreLabel
    }

    // fill list with States and Separator ListItems
    private fun fillList() {

        listItems.add(Separator("A"))
        listItems.add(State("Alabama", "Cotton State"))
        listItems.add(State("Alaska", "The Last Frontier"))
        listItems.add(State("Arizona", "Grand Canyon State"))
        listItems.add(State("Arkansas", "Natural State"))
        listItems.add(Separator("C"))
        listItems.add(State("California", "Golden State"))
        listItems.add(State("Colorado", "Centennial State"))
        listItems.add(State("Connecticut", "Constitution State"))
        listItems.add(Separator("D"))
        listItems.add(State("Delaware", "First State"))
        listItems.add(Separator("F"))
        listItems.add(State("Florida", "Sunshine State"))
        listItems.add(Separator("G"))
        listItems.add(State("Georgia", "Peach State"))
        listItems.add(Separator("H"))
        listItems.add(State("Hawaii", "Aloha State"))
        listItems.add(Separator("I"))
        listItems.add(State("Idaho", "Gem State"))
        listItems.add(State("Illinois", "Prairie State"))
        listItems.add(State("Indiana", "Crossroads of America"))
        listItems.add(State("Iowa", "Hawkeye State"))
        listItems.add(Separator("K"))
        listItems.add(State("Kansas", "Sunflower State"))
        listItems.add(State("Kentucky", "Bluegrass State"))
        listItems.add(Separator("L"))
        listItems.add(State("Louisiana", "Pelican State"))
        listItems.add(Separator("M"))
        listItems.add(State("Maine", "Pine Tree State"))
        listItems.add(State("Maryland", "Old Line State"))
        listItems.add(State("Massachusetts", "Bay State"))
        listItems.add(State("Michigan", "Great Lakes State"))
        listItems.add(State("Minnesota", "North Star State"))
        listItems.add(State("Mississippi", "Magnolia State"))
        listItems.add(State("Missouri", "Show Me State"))
        listItems.add(State("Montana", "Treasure State"))
        listItems.add(Separator("N"))
        listItems.add(State("Nebraska", "Cornhusker State"))
        listItems.add(State("Nevada", "Silver State"))
        listItems.add(State("New Hampshire", "Granite State"))
        listItems.add(State("New Jersey", "Garden State"))
        listItems.add(State("New Mexico", "The Land of Enchantment"))
        listItems.add(State("New York", "Empire State"))
        listItems.add(State("North Carolina", "Tar Heel State"))
        listItems.add(State("North Dakota", "Peace Garden State"))
        listItems.add(Separator("O"))
        listItems.add(State("Ohio", "Buckeye State"))
        listItems.add(State("Oklahoma", "Sooner State"))
        listItems.add(State("Oregon", "Beaver State"))
        listItems.add(Separator("P"))
        listItems.add(State("Pennsylvania", "Keystone State"))
        listItems.add(Separator("R"))
        listItems.add(State("Rhode Island", "Ocean State"))
        listItems.add(Separator("S"))
        listItems.add(State("South Carolina", "Palmetto State"))
        listItems.add(State("South Dakota", "Mount Rushmore State"))
        listItems.add(Separator("T"))
        listItems.add(State("Tennessee", "Volunteer State"))
        listItems.add(State("Texas", "Lone Star State"))
        listItems.add(Separator("U"))
        listItems.add(State("Utah", "Behive State"))
        listItems.add(Separator("V"))
        listItems.add(State("Vermont", "Green Mountain State"))
        listItems.add(State("Virginia", "Old Dominion"))
        listItems.add(Separator("W"))
        listItems.add(State("Washington", "Evergreen State"))
        listItems.add(State("West Virginia", "Mountain State"))
        listItems.add(State("Wisconsin", "Badger State"))
        listItems.add(State("Wyoming", "Equality State"))
    }
}
