package edu.okstate.cs.bem.listintentstransitions.Model

class Separator(override val separatorLabel: String): ListItem() {

    override val type = TYPE_SEPARATOR

    override val stateName: String?
        get() = null
    override val stateNickname: String?
        get() = null
    override var isScored: Boolean = false
}