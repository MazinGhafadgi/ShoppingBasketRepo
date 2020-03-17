package uk.adthena.shoppingbasket

object ShoppingBasketApp {

  def main(args: Array[String]): Unit = {
    object ShoppingApp extends ShoppingBasket
    import ShoppingApp._
    addToBasket(args)
    println(s"Total price: £${"%.2f".format(checkout())}")
  }

}
