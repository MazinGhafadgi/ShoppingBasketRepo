package uk.adthena.shoppingbasket

object ShoppingBasketAppWithDiscount {

  def main(args: Array[String]): Unit = {
    object ShoppingAppWithDiscount extends ShoppingBasket with DiscountOnPrice
    import ShoppingAppWithDiscount._
    addToBasket(args)
    println(s"Total price: £${"%.2f".format(checkout())}")
  }

}
