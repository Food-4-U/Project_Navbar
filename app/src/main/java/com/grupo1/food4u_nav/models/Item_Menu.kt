package projeto.ipca.food4u.grupoI.models

class Item_Menu {

    constructor(description: String, time_prepare: Int, evaluation: Float, price: Float) {
        this.description = description
        this.time_prepare = time_prepare
        this.evaluation = evaluation
        this.price = price
    }

    var description: String
    var time_prepare: Int
    var evaluation: Float
    var price : Float

}