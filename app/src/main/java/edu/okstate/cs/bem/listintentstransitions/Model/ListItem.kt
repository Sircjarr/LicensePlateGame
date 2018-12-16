package edu.okstate.cs.bem.listintentstransitions.Model

// allows adapter to access all properties in Separator and State classes

abstract class ListItem {

    abstract val type: Int
    abstract val stateName: String?
    abstract val stateNickname: String?
    abstract val separatorLabel: String?
    abstract var isScored: Boolean

    // static variables
    companion object {
        const val TYPE_STATE = 0
        const val TYPE_SEPARATOR = 1
    }

}