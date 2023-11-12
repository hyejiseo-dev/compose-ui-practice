package com.kakaohealthcare.compose_ui_practice.data

import com.kakaohealthcare.compose_ui_practice.R

class ContentRepository {
    fun getAllData(): List<Contents> {
        return listOf(
            Contents(
                ldapId = "annie_ble",
                title = "Mar 13, Fri",
                content = "Sometimes when we're feeling depressed, negative though...",
                imgUrl = "https://www.kasandbox.org/programming-images/avatars/marcimus-orange.png"
            ),
            Contents(
                ldapId = "ellen_lim",
                title = "Mar 14, Sat",
                content = "Sometimes when we're feeling like I am alone say strange enough Sometimes when we're feeling depressed, negative though..",
                imgUrl = "https://www.kasandbox.org/programming-images/avatars/duskpin-tree.png"
            )
        )
    }
}