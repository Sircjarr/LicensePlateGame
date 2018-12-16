package edu.okstate.cs.bem.listintentstransitions.Model

// val in parameter turns parameter into property
class State(override val stateName: String?, override val stateNickname: String?): ListItem() {

    override val type = TYPE_STATE
    override var isScored = false

    override val separatorLabel: String?
        get() = null
}

